package net.moddingforreal.ntmtool.hazard;

import com.hbm.hazard.HazardData;
import com.hbm.hazard.HazardEntry;
import com.hbm.hazard.HazardRegistry;
import com.hbm.hazard.HazardSystem;
import com.hbm.hazard.type.HazardTypeBase;
import com.hbm.hazard.type.HazardTypeRadiation;
import com.hbm.util.ContaminationUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name="HazardItem")
@XmlType(propOrder = {"item", "ore", "meta", "hazards"})
@XmlSeeAlso({XMLHazard.class})
public class XMLHazardItem implements INtmSpecialItem {
    @XmlAttribute(name = "item" ,required = false)
    public String item = ""; // Will always be preferred over ore
    @XmlAttribute(name = "ore" ,required = false)
    public String ore = "";
    @XmlAttribute(name = "meta", required = false)
    public int meta;
    @XmlAttribute(name = "override", required = false) // If this is true, then if an item already has hazards, these will be deleted / overridden
    public boolean override = false;
    @XmlElement(name = "hazard", nillable = true) // If you want to clear an item of its hazards, then just supply an empty list and set override to true
    // @XmlElementWrapper(name = "hazards")
    public List<XMLHazard> hazards;


    public XMLHazardItem() {

    }

    public XMLHazardItem(String item, XMLHazard[] hazards) {
        this.item = item;
        this.hazards = Arrays.asList(hazards);
    }

    public XMLHazardItem(String item, XMLHazard[] hazards, int meta) {
        this.item = item;
        this.hazards = Arrays.asList(hazards);
        this.meta = meta;
    }

    @Override
    public void registerSelf(FMLPreInitializationEvent event) {
        System.out.println("Adding item to hazard system: " + this.item + " @ " + this.meta + " | " + this.ore);
        if (Objects.equals(this.item, "") && Objects.equals(this.ore, ""))
            return;
        Object toRegister;
        Class<?> toRegisterClazz = Object.class;
        if (!Objects.equals(this.item, "")) {
            Item item = Item.REGISTRY.getObject(new ResourceLocation(this.item));
            if (item == null)
                return;
            toRegister = item;
            toRegisterClazz = Item.class;
            if (meta != 0) {
                ItemStack stack = item.getDefaultInstance();
                stack.setItemDamage(meta);
                toRegister = stack;
                toRegisterClazz = ItemStack.class;
            }
        } else {
            toRegister = ore;
            toRegister = String.class;
        }
        boolean blacklist = false;
        HazardData hazardData = new HazardData();
        for (XMLHazard xmlHazard : hazards) {
            HazardTypeBase type = null;
            System.out.println(xmlHazard.hazard.toLowerCase());
            switch (xmlHazard.hazard.toLowerCase()) {
                case "radiation":
                    type = HazardRegistry.RADIATION;
                    break;
                case "digamma":
                    type = HazardRegistry.DIGAMMA;
                    break;
                case "hot":
                    type = HazardRegistry.HOT;
                    break;
                case "blinding":
                    type = HazardRegistry.BLINDING;
                    break;
                case "asbestos":
                    type = HazardRegistry.ASBESTOS;
                    break;
                case "coal":
                    type = HazardRegistry.COAL;
                    break;
                case "hydroactive":
                    type = HazardRegistry.HYDROACTIVE;
                    break;
                case "explosive":
                    type = HazardRegistry.EXPLOSIVE;
                    break;
                case "blacklist":
                    blacklist = true;
                    break;
                default:
                    System.out.println("Hey! Invalid hazard type! Hazard type specified: " + xmlHazard.hazard);
                    break;
            }
            if (type == null)
                continue;
            hazardData.addEntry(type, xmlHazard.level, xmlHazard.override);
        }
        System.out.println("Item made it through the checks! Class: " + toRegisterClazz.getCanonicalName() + "Item type: " + toRegister.getClass().getCanonicalName());
        for (XMLHazard hazard : this.hazards) {
            System.out.println("\r" + hazard.hazard + " @ " + hazard.level);
        }
        HazardSystem.register(Item.REGISTRY.getObject(new ResourceLocation("lava_bucket")),
                new HazardData().addEntry(HazardRegistry.HOT, 1.0F));
        System.out.println(HazardSystem.getHazardsFromStack(new ItemStack((Item) toRegister)).toString());
        System.out.println();
        HazardSystem.register(toRegisterClazz.cast(toRegister), hazardData);
        if (blacklist && (toRegisterClazz == Item.class || toRegisterClazz == ItemStack.class)) {
            if (toRegisterClazz == Item.class)
                HazardSystem.blacklist(new ItemStack((Item) toRegister));
            if (toRegisterClazz == ItemStack.class)
                HazardSystem.blacklist((ItemStack) toRegister);
        }
    }
}

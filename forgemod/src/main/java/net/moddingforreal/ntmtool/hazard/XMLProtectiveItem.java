package net.moddingforreal.ntmtool.hazard;

import com.hbm.util.ArmorRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name="ProtectiveItem")
@XmlType(propOrder = {"item", "meta", "protections"})
@XmlSeeAlso({XMLProtection.class})
public class XMLProtectiveItem implements INtmSpecialItem {
    @XmlAttribute(name = "item" ,required = true)
    public String item;
    @XmlAttribute(name = "meta", required = false)
    public int meta;
    @XmlAttribute(name = "override", required = false) // If this is true, then if an item already has hazards, these will be deleted / overridden
    public boolean override = false;
    @XmlElement(name = "protection", nillable = true) // If you want to clear an item of its hazards, then just supply an empty list and set override to true
    // @XmlElementWrapper(name = "protections")
    public List<XMLProtection> protections;

    public XMLProtectiveItem() {

    }

    public XMLProtectiveItem(String item, XMLProtection[] protections) {
        this.item = item;
        this.protections = Arrays.asList(protections);
    }

    public XMLProtectiveItem(String item, XMLProtection[] protections, int meta) {
        this.item = item;
        this.protections = Arrays.asList(protections);
        this.meta = meta;
    }

    @Override
    public void registerSelf(FMLPreInitializationEvent event) {
        // Do nothing, I didn't find a way to do this.
    }
}

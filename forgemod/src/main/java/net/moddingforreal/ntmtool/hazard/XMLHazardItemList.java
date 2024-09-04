package net.moddingforreal.ntmtool.hazard;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.moddingforreal.ntmtool.ConfigHolder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "HazardItems")
@XmlSeeAlso({XMLHazardItem.class, XMLProtection.class})
public class XMLHazardItemList {
    @XmlElement(name = "HazardItem", required = false)
    public List<XMLHazardItem> hazardItems;
    @XmlElement(name = "ProtectiveItem", required = false)
    public List<XMLProtectiveItem> protectiveItems;

    public XMLHazardItemList() {
        hazardItems = new ArrayList<>();
        protectiveItems = new ArrayList<>();
    }

    public void registerAll(FMLPreInitializationEvent event) {
        if (!ConfigHolder.hazardConfig.applyHazardConfigs)
            return;
        for (XMLHazardItem item : hazardItems) {
            item.registerSelf(event);
        }
        for (XMLProtectiveItem item : protectiveItems) {
            item.registerSelf(event);
        }
    }
}

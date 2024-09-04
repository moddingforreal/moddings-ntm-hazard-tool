package net.moddingforreal.ntmtool.hazard;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.xml.bind.annotation.XmlTransient;

public interface INtmSpecialItem {
    @XmlTransient
    void registerSelf(FMLPreInitializationEvent event);
}

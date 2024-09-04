package net.moddingforreal.ntmtool.hazard;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Hazard")
@XmlType(propOrder = {"hazard", "level", "override"})
public class XMLHazard {
    @XmlAttribute(name = "hazard", required = true)
    public String hazard;
    @XmlAttribute(name = "level", required = true)
    public float level = 1.0F;
    @XmlAttribute(name = "override", required = false)
    public boolean override;

    public XMLHazard() {

    }
    public XMLHazard(String name, float level) {
        this.hazard = name;
        this.level = level;
    }
}

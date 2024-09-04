package net.moddingforreal.ntmtool.hazard;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Protection")
@XmlType(propOrder = {"protection", "level", "override"})
public class XMLProtection {
    @XmlAttribute(name = "protection", required = true)
    public String protection;
    @XmlAttribute(name = "level", required = false)
    public float level;
    @XmlAttribute(name = "override", required = false)
    public boolean override;

    public XMLProtection() {

    }
    public XMLProtection(String name, float level) {
        this.protection = name;
        this.level = level;
    }
}

package net.moddingforreal.ntmtool;

import net.minecraftforge.common.config.Config;

import java.io.File;

@Config(modid = NTMTool.MODID, name = NTMTool.MODID + "/" + NTMTool.MODID)
public class ConfigHolder {
    public static HazardConfig hazardConfig = new HazardConfig();
    public static class HazardConfig {
        @Config.Comment({"Whether to add the items in the XML file",
                "This config must be changed on the logical server!",
                "Default: true"})
        @Config.RequiresMcRestart
        public boolean applyHazardConfigs = true;

        @Config.Comment({"Alternative Config Path",
                "This alternative path within the config/ folder",
                "Default: \"" + NTMTool.MODID + "/hazarditems.xml\""})
        @Config.RequiresMcRestart
        public String pathToXml = NTMTool.MODID + "/hazarditems.xml";
    }
}

package net.moddingforreal.ntmtool;

import com.hbm.hazard.HazardRegistry;
import com.hbm.hazard.HazardSystem;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.moddingforreal.ntmtool.util.ConfigsLoader;
import org.apache.logging.log4j.Logger;

@Mod(modid = NTMTool.MODID, name = NTMTool.NAME, version = NTMTool.VERSION, dependencies = "after:hbm;after:gregtech;after:*")
public class NTMTool
{
    public static final String MODID = "ntmtool";
    public static final String NAME = "Modding's NTM Tool";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        try {
            ConfigsLoader.loadAllPreInit(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}

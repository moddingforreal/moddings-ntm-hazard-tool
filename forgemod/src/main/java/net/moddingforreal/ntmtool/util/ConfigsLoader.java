package net.moddingforreal.ntmtool.util;

import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.moddingforreal.ntmtool.ConfigHolder;
import net.moddingforreal.ntmtool.hazard.XMLHazardItemList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;

public class ConfigsLoader {
    public static void loadAllPreInit(FMLPreInitializationEvent event) throws IOException, JAXBException {
        String path = event.getModConfigurationDirectory().getAbsolutePath() + "/" + ConfigHolder.hazardConfig.pathToXml;
        System.out.println(path);
        File xmlFile = new File(path);
        if(!xmlFile.exists() || xmlFile.isDirectory()) {
            xmlFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<HazardItems>\n" +
                    "\t<HazardItem item=\"minecraft:lava_bucket\" meta=\"0\" override=\"false\">\n" +
                    "\t\t<hazard hazard=\"hot\" level=\"1.0\" override=\"false\"/>\n" +
                    "\t</HazardItem>\n" +
                    "</HazardItems>\n");
            writer.close();
        }
        JAXBContext context = JAXBContext.newInstance(XMLHazardItemList.class);
        Unmarshaller unmar = context.createUnmarshaller();
        XMLHazardItemList list = (XMLHazardItemList) unmar.unmarshal(xmlFile);
        list.registerAll(event);
    }
}

package com.deliciousbread481.valkyriedebug;  
  
import net.minecraftforge.common.MinecraftForge;  
import net.minecraftforge.fml.common.Mod;  
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;  
import org.apache.logging.log4j.LogManager;  
import org.apache.logging.log4j.Logger;  
  
@Mod(modid = "valkyriedebug", name = "Valkyrie Debug", version = "1.0.0")  
public class ValkyrieDebugMod {  
  
    public static final Logger LOGGER = LogManager.getLogger("ValkyrieDebug");  
  
    @Mod.EventHandler  
    public void preInit(FMLPreInitializationEvent event) {  
        MinecraftForge.EVENT_BUS.register(new ValkyrieSpawnDebugHandler());  
        LOGGER.info("[ValkyrieDebug] Debug mod loaded. Listening for Valkyrie spawn events.");  
    }  
}
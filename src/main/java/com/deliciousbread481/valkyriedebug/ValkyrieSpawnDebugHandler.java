package com.deliciousbread481.valkyriedebug;  
  
import net.minecraft.entity.Entity;  
import net.minecraftforge.event.entity.EntityJoinWorldEvent;  
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;  
import net.minecraftforge.fml.common.eventhandler.EventPriority;  
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;  
import org.apache.logging.log4j.Logger;  
  
import java.io.PrintWriter;  
import java.io.StringWriter;  
  
public class ValkyrieSpawnDebugHandler {  
  
    private static final Logger LOG = ValkyrieDebugMod.LOGGER;  
  
    private static boolean isTargetEntity(Entity entity) {  
        if (entity == null) return false;  
        String className = entity.getClass().getName();  
        return className.contains("Valkyrie") || className.contains("valkyrie");  
    }  
  
    private static String getStackTrace() {  
        StringWriter sw = new StringWriter();  
        new Throwable("--- Stack Trace Capture ---").printStackTrace(new PrintWriter(sw));  
        return sw.toString();  
    }  
  
    @SubscribeEvent(priority = EventPriority.HIGHEST)  
    public void onEntityJoinWorldEarliest(EntityJoinWorldEvent event) {  
        if (!isTargetEntity(event.getEntity())) return;  
  
        LOG.warn("========== [EntityJoinWorldEvent - HIGHEST priority] ==========");  
        LOG.warn("Entity: {}", event.getEntity().getClass().getName());  
        LOG.warn("Event canceled at HIGHEST priority: {}", event.isCanceled());  
        LOG.warn("================================================================");  
    }  
  
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)  
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {  
        if (!isTargetEntity(event.getEntity())) return;  
  
        Entity entity = event.getEntity();  
        LOG.warn("========== [EntityJoinWorldEvent - LOWEST] ==========");  
        LOG.warn("Entity: {} ({})", entity.getClass().getName(), entity);  
        LOG.warn("Position: x={}, y={}, z={}", entity.posX, entity.posY, entity.posZ);  
        LOG.warn("World: {} (remote={})", entity.world.provider.getDimensionType().getName(), entity.world.isRemote);  
        LOG.warn("Event canceled: {}", event.isCanceled());  
        LOG.warn("Call stack:\n{}", getStackTrace());  
        LOG.warn("=============================================");  
    }  
  
    @SubscribeEvent(priority = EventPriority.HIGHEST)  
    public void onCheckSpawnEarliest(LivingSpawnEvent.CheckSpawn event) {  
        if (!isTargetEntity(event.getEntityLiving())) return;  
  
        LOG.warn("========== [CheckSpawn - HIGHEST priority] ==========");  
        LOG.warn("Entity: {}", event.getEntityLiving().getClass().getName());  
        LOG.warn("Event result at HIGHEST: {}", event.getResult());  
        LOG.warn("=====================================================");  
    }  
  
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)  
    public void onCheckSpawn(LivingSpawnEvent.CheckSpawn event) {  
        if (!isTargetEntity(event.getEntityLiving())) return;  
  
        LOG.warn("========== [CheckSpawn - LOWEST] ==========");  
        LOG.warn("Entity: {}", event.getEntityLiving().getClass().getName());  
        LOG.warn("Position: x={}, y={}, z={}", event.getX(), event.getY(), event.getZ());  
        LOG.warn("Event result: {}", event.getResult());  
        LOG.warn("Call stack:\n{}", getStackTrace());  
        LOG.warn("==================================");  
    }  
  
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)  
    public void onSpecialSpawn(LivingSpawnEvent.SpecialSpawn event) {  
        if (!isTargetEntity(event.getEntityLiving())) return;  
  
        LOG.warn("========== [SpecialSpawn - LOWEST] ==========");  
        LOG.warn("Entity: {}", event.getEntityLiving().getClass().getName());  
        LOG.warn("Position: x={}, y={}, z={}", event.getX(), event.getY(), event.getZ());  
        LOG.warn("Event canceled: {}", event.isCanceled());  
        LOG.warn("Call stack:\n{}", getStackTrace());  
        LOG.warn("====================================");  
    }  
  
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)  
    public void onAllowDespawn(LivingSpawnEvent.AllowDespawn event) {  
        if (!isTargetEntity(event.getEntityLiving())) return;  
  
        LOG.warn("========== [AllowDespawn - LOWEST] ==========");  
        LOG.warn("Entity: {}", event.getEntityLiving().getClass().getName());  
        LOG.warn("Event result: {}", event.getResult());  
        LOG.warn("Call stack:\n{}", getStackTrace());  
        LOG.warn("====================================");  
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)  
    public void onLivingDeath(LivingDeathEvent event) {  
        if (!isTargetEntity(event.getEntityLiving())) return;  
  
        LOG.warn("========== [LivingDeath] ==========");  
        LOG.warn("Entity: {}", event.getEntityLiving().getClass().getName());  
        LOG.warn("Damage source: {}", event.getSource());  
        LOG.warn("Source entity: {}", event.getSource().getTrueSource());  
        LOG.warn("Immediate source: {}", event.getSource().getImmediateSource());  
        LOG.warn("Position: x={}, y={}, z={}", event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ);  
        LOG.warn("Call stack:\n{}", getStackTrace());  
        LOG.warn("====================================");  
    }  
  
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)  
    public void onLivingHurt(LivingHurtEvent event) {  
        if (!isTargetEntity(event.getEntityLiving())) return;  
  
        LOG.warn("========== [LivingHurt] ==========");  
        LOG.warn("Entity: {}", event.getEntityLiving().getClass().getName());  
        LOG.warn("Damage: {} from {}", event.getAmount(), event.getSource());  
        LOG.warn("Health before: {}", event.getEntityLiving().getHealth());  
        LOG.warn("Source entity: {}", event.getSource().getTrueSource());  
        LOG.warn("Call stack:\n{}", getStackTrace());  
        LOG.warn("==================================");  
    }
}
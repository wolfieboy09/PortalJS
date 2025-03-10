package dev.wolfieboy09.portaljs;

import dev.wolfieboy09.portaljs.events.CreatedPortalEvent;
import dev.wolfieboy09.portaljs.kubeevents.PortalEvents;
import dev.wolfieboy09.portaljs.kubeevents.PortalJSSpawnEvent;
import net.kyrptonaught.customportalapi.util.CustomPortalHelper;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class PortalJSEvents {
    public static void vanillaPortalSpawn(@NotNull BlockEvent.PortalSpawnEvent event) {
        Block frameBlock = event.getLevel().getBlockState(event.getPos()).getBlock();
        event.setCanceled(sendEvent(frameBlock));
    }

    public static void customPortalSpawn(@NotNull CreatedPortalEvent event) {
        Block frameblock = CustomPortalHelper.getPortalBase(event.getLevel(), event.getPos());
        event.setCanceled(sendEvent(frameblock));
    }

    private static boolean sendEvent(Block foundation) {
        if (PortalEvents.PORTAL_SPAWN.hasListeners()) {
            PortalJSSpawnEvent portalSpawnEvent = new PortalJSSpawnEvent(foundation);
            PortalEvents.PORTAL_SPAWN.post(portalSpawnEvent);
            return portalSpawnEvent.isCancelled();
        }
        return false;
    }
}

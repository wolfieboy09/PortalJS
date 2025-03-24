package dev.wolfieboy09.portaljs;

import dev.wolfieboy09.portaljs.events.CreatedPortalEvent;
import dev.wolfieboy09.portaljs.kubeevents.PortalEvents;
import dev.wolfieboy09.portaljs.kubeevents.PortalJSSpawnEvent;
import net.kyrptonaught.customportalapi.util.CustomPortalHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class PortalJSEvents {
    public static void vanillaPortalSpawn(@NotNull BlockEvent.PortalSpawnEvent event) {
        // This event only gets fired from nether portals... so I could just... hard code it...
        event.setCanceled(sendEvent(Blocks.OBSIDIAN, event.getPos()));
    }

    public static void customPortalSpawn(@NotNull CreatedPortalEvent event) {
        if (!event.isVanilla) {
            event.setCanceled(sendEvent(CustomPortalHelper.getPortalBase(event.getLevel(), event.getPos().below()), event.getPos()));
        } else {
            event.setCanceled(sendEvent(event.getFoundationBlock(), event.getPos()));
        }
    }

    private static boolean sendEvent(Block foundation, BlockPos blockPos) {
        if (PortalEvents.PORTAL_SPAWN.hasListeners()) {
            // The block pos where the portal was ignited
            PortalJSSpawnEvent portalSpawnEvent = new PortalJSSpawnEvent(foundation, blockPos);
            PortalEvents.PORTAL_SPAWN.post(portalSpawnEvent);
            return portalSpawnEvent.isCancelled();
        }
        return false;
    }
}

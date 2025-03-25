package dev.wolfieboy09.portaljs;

import dev.wolfieboy09.portaljs.events.CreatedPortalEvent;
import dev.wolfieboy09.portaljs.kubeevents.PortalEvents;
import dev.wolfieboy09.portaljs.kubeevents.PortalJSSpawnEvent;
import net.kyrptonaught.customportalapi.util.CustomPortalHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class PortalJSEvents {
    public static void vanillaPortalSpawn(@NotNull BlockEvent.PortalSpawnEvent event) {
        // This event only gets fired from nether portals... so I could just... hard code it...
        if (!event.getLevel().isClientSide()) {
            event.setCanceled(sendEvent(Blocks.OBSIDIAN, event.getPos(), (ServerLevel) event.getLevel()));
        }
    }

    public static void customPortalSpawn(@NotNull CreatedPortalEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (!event.isVanilla) {
                event.setCanceled(sendEvent(CustomPortalHelper.getPortalBase(event.getLevel(), event.getPos().below()), event.getPos(), (ServerLevel) event.getLevel()));
            } else {
                event.setCanceled(sendEvent(event.getFoundationBlock(), event.getPos(), (ServerLevel) event.getLevel()));
            }
        }
    }

    private static boolean sendEvent(Block foundation, BlockPos blockPos, ServerLevel serverLevel) {
        if (PortalEvents.PORTAL_SPAWN.hasListeners()) {
            // The block pos where the portal was ignited
            PortalJSSpawnEvent portalSpawnEvent = new PortalJSSpawnEvent(foundation, blockPos, serverLevel);
            PortalEvents.PORTAL_SPAWN.post(portalSpawnEvent);
            return portalSpawnEvent.isCancelled();
        }
        return false;
    }
}

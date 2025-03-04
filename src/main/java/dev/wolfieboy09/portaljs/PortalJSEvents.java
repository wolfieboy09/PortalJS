package dev.wolfieboy09.portaljs;

import dev.wolfieboy09.portaljs.events.PortalEvents;
import dev.wolfieboy09.portaljs.events.PortalJSSpawnEvent;
import net.kyrptonaught.customportalapi.util.CustomPortalHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class PortalJSEvents {
    public static void portalSpawn(@NotNull BlockEvent.PortalSpawnEvent event) {
        Block frameblock = CustomPortalHelper.getPortalBase((Level) event.getLevel(), event.getPos());

        PortalJSSpawnEvent portalSpawnEvent = new PortalJSSpawnEvent(frameblock);
        PortalEvents.PORTAL_SPAWN.post(portalSpawnEvent);

        event.setCanceled(portalSpawnEvent.isCancelled());
    }
}

package dev.wolfieboy09.portaljs.events;

import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.level.block.Block;

public class PortalJSSpawnEvent implements KubeEvent {
    public final Block frameBlock;
    @HideFromJS
    private boolean isCancelled = false;

    public PortalJSSpawnEvent(Block frameBlock) {
        this.frameBlock = frameBlock;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    @HideFromJS
    public boolean isCancelled() {
        return this.isCancelled;
    }
}

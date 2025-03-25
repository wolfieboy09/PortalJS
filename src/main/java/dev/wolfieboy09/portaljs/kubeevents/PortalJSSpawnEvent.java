package dev.wolfieboy09.portaljs.kubeevents;

import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;

public class PortalJSSpawnEvent implements KubeEvent {
    public final Block frameBlock;
    public final BlockPos blockPos;

    @HideFromJS
    private final ServerLevel serverLevel;
    @HideFromJS
    private boolean isCancelled = false;

    public PortalJSSpawnEvent(Block frameBlock, BlockPos blockPos, ServerLevel server) {
        this.frameBlock = frameBlock;
        this.blockPos = blockPos;
        this.serverLevel = server;
    }

    public ServerLevel getServer() {
        return this.serverLevel;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    @HideFromJS
    public boolean isCancelled() {
        return this.isCancelled;
    }
}

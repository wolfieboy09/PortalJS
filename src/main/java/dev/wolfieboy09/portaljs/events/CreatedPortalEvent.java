package dev.wolfieboy09.portaljs.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.fml.event.IModBusEvent;

public class CreatedPortalEvent extends Event implements IModBusEvent, ICancellableEvent {
    private final Level world;
    private final BlockPos pos;
    private final Block foundationBlock;

    public CreatedPortalEvent(Level world, BlockPos pos, Block foundationBlock) {
        this.world = world;
        this.pos = pos;
        this.foundationBlock = foundationBlock;
    }

    public Level getLevel() {
        return this.world;
    }

    public Block getFoundationBlock() {
        return this.foundationBlock;
    }

    public BlockPos getPos() {
        return this.pos;
    }
}

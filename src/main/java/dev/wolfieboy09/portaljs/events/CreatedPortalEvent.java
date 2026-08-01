package dev.wolfieboy09.portaljs.events;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.fml.event.IModBusEvent;
import org.jetbrains.annotations.Nullable;

public class CreatedPortalEvent extends Event implements IModBusEvent, ICancellableEvent {
    private final Level world;
    private final BlockPos pos;
    private final Block foundationBlock;
    @HideFromJS
    public final boolean isVanilla;
    @Nullable
    private final BlockPos framePos;
    @Nullable
    private final Player player;

    public CreatedPortalEvent(Level world, BlockPos pos, Block foundationBlock, boolean isVanilla) {
        this(world, pos, foundationBlock, isVanilla, null, null);
    }

    public CreatedPortalEvent(Level world, BlockPos pos, Block foundationBlock, boolean isVanilla, @Nullable BlockPos framePos, @Nullable Player player) {
        this.world = world;
        this.pos = pos;
        this.foundationBlock = foundationBlock;
        this.isVanilla = isVanilla;
        this.framePos = framePos;
        this.player = player;
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

    @Info("The position of the frame block that ignited the portal, if known")
    @Nullable
    public BlockPos getFramePos() {
        return this.framePos;
    }

    @Info("The player who ignited the portal, if the ignition source was a player")
    @Nullable
    public Player getPlayer() {
        return this.player;
    }
}
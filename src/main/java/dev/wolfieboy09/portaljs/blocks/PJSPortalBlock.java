package dev.wolfieboy09.portaljs.blocks;

import dev.wolfieboy09.portaljs.context.PortalBlockContext;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class PJSPortalBlock extends CustomPortalBlock {
    private Consumer<PortalBlockContext> context;

    public PJSPortalBlock() {
        super(Block.Properties.ofFullCopy(Blocks.NETHER_PORTAL).noCollission().strength(-1).sound(SoundType.GLASS).lightLevel(state -> 11));
    }

    public PJSPortalBlock withConsumer(Consumer<PortalBlockContext> context) {
        this.context = context;
        return this;
    }

    @Override
    protected void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource randomSource) {
        if (this.context != null && !level.isClientSide()) {
            this.context.accept(new PortalBlockContext(blockState, level, pos, randomSource));
        }
    }
}

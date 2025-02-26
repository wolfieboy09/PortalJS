package dev.wolfieboy09.portaljs.blocks;

import dev.wolfieboy09.portaljs.context.PortalBlockContext;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class PJSPortalBlock extends CustomPortalBlock {
    private final Consumer<PortalBlockContext> context;

    public PJSPortalBlock(Properties properties) {
        super(properties);
        this.context = null;
    }

    public PJSPortalBlock(Properties settings, @Nullable Consumer<PortalBlockContext> consumer) {
        super(settings);
        this.context = consumer;
    }

    @Override
    protected void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource randomSource) {
        if (this.context != null) {
            this.context.accept(new PortalBlockContext(blockState, level, pos, randomSource));
        }
    }
}

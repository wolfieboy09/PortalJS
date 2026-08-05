package dev.wolfieboy09.portaljs.blocks;

import dev.wolfieboy09.portaljs.context.PortalBlockContext;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class PJSPortalBlock extends CustomPortalBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
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

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, RandomSource random) {
        if (random.nextInt(100) == 0) {
            double pX = pos.getX() + 0.5;
            double pY = pos.getY() + 0.5;
            double pZ = pos.getZ() + 0.5;
            level.playLocalSound(
                pX, pY, pZ,
                SoundEvents.PORTAL_AMBIENT,
                SoundSource.BLOCKS,
                0.5F,
                random.nextFloat() * 0.4F + 0.8F,
                false
            );
        }
        int kParticlesPerBlock = 1;
        for (int i = 0; i < kParticlesPerBlock; ++i) {
            // Center particle randomly within the block
            double pX = pos.getX() + random.nextDouble();
            double pY = pos.getY() + random.nextDouble();
            double pZ = pos.getZ() + random.nextDouble();
            // Generate random speeds from [-0.25, 0.25]
            double vX = 0.5 * random.nextDouble() - 0.25;
            double vY = 0.5 * random.nextDouble() - 0.25;
            double vZ = 0.5 * random.nextDouble() - 0.25;
            // Scale velocity perpendicular to the portal's normal vector
            switch (state.getValue(AXIS)) {
                // The direction is a bit confusing
                // Axis is not the normal vector it seems
                case X:
                    // X means the portal normal is +/-Z
                    vZ *= 8.0;
                    break;
                case Y:
                    // Y means the portal normal is +/-Y
                    vY *= 8.0;
                    break;
                case Z:
                    // Z means the portal normal is +/-X
                    vX *= 8.0;
                    break;
            }
            // TODO inherit portal color
            // it was implemented in CustomPortals but got lost in the neoforged port
            level.addParticle(ParticleTypes.PORTAL, pX, pY, pZ, vX, vY, vZ);
        }
    }
}

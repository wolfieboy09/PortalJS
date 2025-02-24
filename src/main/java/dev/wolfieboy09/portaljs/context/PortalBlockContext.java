package dev.wolfieboy09.portaljs.context;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public record PortalBlockContext(@Info("The block state of the portal") BlockState state,
                                 @Info("The level where the portal exists") Level level,
                                 @Info("The position of the portal block") BlockPos pos,
                                 @Info("The random source used for effects or behavior") RandomSource randomSource) {
}
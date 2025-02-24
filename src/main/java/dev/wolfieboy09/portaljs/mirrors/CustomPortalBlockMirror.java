package dev.wolfieboy09.portaljs.mirrors;

import dev.latvian.mods.kubejs.typings.Info;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CustomPortalBlockMirror extends CustomPortalBlock {
    public CustomPortalBlockMirror(Properties settings) {
        super(settings);
    }
}

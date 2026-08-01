package dev.wolfieboy09.portaljs.context;

import dev.latvian.mods.kubejs.typings.Info;
import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public record PortalIgniteContext(@Info("The player igniting the portal, if the ignition source is a player-driven one (e.g. an item)")
                                  @Nullable Player player,
                                  @Info("The level the portal is being ignited in")
                                  Level level,
                                  @Info("The position of the portal block being ignited")
                                  BlockPos portalPos,
                                  @Info("The position of the frame block that triggered the ignition")
                                  BlockPos framePos,
                                  @Info("The ignition source used to light the portal")
                                  PortalIgnitionSource ignitionSource) {}
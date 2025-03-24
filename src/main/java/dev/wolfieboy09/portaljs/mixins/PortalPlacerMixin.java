package dev.wolfieboy09.portaljs.mixins;

import dev.wolfieboy09.portaljs.events.CreatedPortalEvent;
import net.kyrptonaught.customportalapi.portal.PortalPlacer;
import net.kyrptonaught.customportalapi.util.PortalLink;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PortalPlacer.class)
public abstract class PortalPlacerMixin {
    @Inject(method = "createPortal", at = @At(value = "INVOKE", target = "Lnet/kyrptonaught/customportalapi/portal/frame/PortalFrameTester;lightPortal(Lnet/minecraft/world/level/block/Block;)V"), cancellable = true)
    private static void sendEvent(PortalLink link, Level world, BlockPos pos, Block foundationBlock, CallbackInfoReturnable<Boolean> cir) {
        CreatedPortalEvent event = new CreatedPortalEvent(world, pos, foundationBlock, false);
        ModLoader.postEvent(event);
        if (event.isCanceled()) {
            cir.setReturnValue(true);
        }
     }
}

package dev.wolfieboy09.portaljs.mixins;


import dev.wolfieboy09.portaljs.events.CreatedPortalEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.ModLoader;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public abstract class EnderEyeItemMixin extends Item {
    public EnderEyeItemMixin(Properties props) {
        super(props);
    }

    @Inject(method = "useOn", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/pattern/BlockPattern$BlockPatternMatch;getFrontTopLeft()Lnet/minecraft/core/BlockPos;"))
    public void callback(@NotNull UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        BlockPos blockpos = context.getClickedPos();
        CreatedPortalEvent event = new CreatedPortalEvent(context.getLevel(), blockpos, Blocks.END_PORTAL_FRAME, true);
        ModLoader.postEvent(event);
        if (event.isCanceled()) {
            cir.cancel();
        }
     }
}

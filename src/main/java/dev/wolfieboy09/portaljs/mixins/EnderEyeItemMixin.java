package dev.wolfieboy09.portaljs.mixins;


import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.Portal;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnderEyeItem.class)
public abstract class EndPortalBlockMixin extends Item {

}

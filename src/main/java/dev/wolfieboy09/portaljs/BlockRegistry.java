package dev.wolfieboy09.portaljs;

import dev.wolfieboy09.portaljs.blocks.PJSPortalBlock;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PortalJS.MODID);

    public static final DeferredBlock<PJSPortalBlock> PORTAL_BLOCK = DeferredBlock.createBlock(ResourceLocation.fromNamespaceAndPath(PortalJS.MODID, "pjs_portal_block"));
}

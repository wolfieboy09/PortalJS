package dev.wolfieboy09.portaljs;

import dev.wolfieboy09.portaljs.blocks.PJSPortalBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PortalJS.MOD_ID);

    public static final DeferredBlock<PJSPortalBlock> PORTAL_BLOCK = BLOCKS.register("portal_block", registryName -> new PJSPortalBlock(null));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}

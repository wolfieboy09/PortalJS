package dev.wolfieboy09.portaljs.mixins;

import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.util.PortalLink;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.concurrent.ConcurrentHashMap;

@Mixin(CustomPortalApiRegistry.class)
public interface PortalRegistryMapAccessor {
    @Accessor("portals")
    static ConcurrentHashMap<Block, PortalLink> getPortalRegistry() {
        // The byte code will change for me to get the portal registry, so this will do for the mean time
        throw new IllegalStateException("You should not be able to see this. Report this bug to the PortalJS dev. THE MIXIN SHOULD HAVE CHANGED THE BYTE CODE FOR THIS ACCESSOR");
    }
}

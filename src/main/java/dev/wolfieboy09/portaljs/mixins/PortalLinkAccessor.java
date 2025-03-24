package dev.wolfieboy09.portaljs.mixins;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.kyrptonaught.customportalapi.util.PortalLink;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = CustomPortalBuilder.class, remap = false)
public interface PortalLinkAccessor {
    @Accessor("portalLink")
    PortalLink getPortalLink();
}

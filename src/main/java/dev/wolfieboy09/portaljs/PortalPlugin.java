package dev.wolfieboy09.portaljs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.wolfieboy09.portaljs.kubeevents.PortalEvents;
import dev.wolfieboy09.portaljs.mirrors.SHOULDTPMirror;
import org.jetbrains.annotations.NotNull;

public class PortalPlugin implements KubeJSPlugin {
    @Override
    public void registerEvents(@NotNull EventGroupRegistry registry) {
        registry.register(PortalEvents.GROUP);
    }

    @Override
    public void registerBindings(@NotNull BindingRegistry bindings) {
        bindings.add("ShouldTP", SHOULDTPMirror.class);
    }
}

package dev.wolfieboy09.portaljs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.wolfieboy09.portaljs.events.PortalBuilder;
import dev.wolfieboy09.portaljs.events.PortalEvents;
import dev.wolfieboy09.portaljs.mirrors.SHOULDTPMirror;

public class PortalPlugin implements KubeJSPlugin {
    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(PortalEvents.GROUP);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("ShouldTP", SHOULDTPMirror.class);
    }
}

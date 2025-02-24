package dev.wolfieboy09.portaljs.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface PortalEvents {
    EventGroup GROUP = EventGroup.of("PortalEvents");

    EventHandler REGISTER_PORTAL = GROUP.startup("register", () -> PortalBuilder.class);
}

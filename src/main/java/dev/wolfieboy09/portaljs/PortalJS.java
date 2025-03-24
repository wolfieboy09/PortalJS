package dev.wolfieboy09.portaljs;

import com.mojang.logging.LogUtils;
import dev.latvian.mods.kubejs.script.ConsoleJS;
import dev.wolfieboy09.portaljs.kubeevents.PortalBuilder;
import dev.wolfieboy09.portaljs.kubeevents.PortalEvents;
import dev.wolfieboy09.portaljs.mixins.PortalLinkAccessor;
import dev.wolfieboy09.portaljs.mixins.PortalRegistryMapAccessor;
import net.kyrptonaught.customportalapi.CustomPortalsMod;
import net.kyrptonaught.customportalapi.util.PortalLink;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(PortalJS.MOD_ID)
public class PortalJS {
    public static final String MOD_ID = "portaljs";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PortalJS(@NotNull IEventBus modEventBus, ModContainer modContainer) {
        BlockRegistry.register(modEventBus);
        modEventBus.addListener(this::registerPortals);
        //NeoForge.EVENT_BUS.addListener(PortalJSEvents::onServerReload);

        NeoForge.EVENT_BUS.addListener(PortalJSEvents::vanillaPortalSpawn);
        modEventBus.addListener(PortalJSEvents::customPortalSpawn);

        LOGGER.info("It's time to portal!");
    }

    private void registerPortals(FMLCommonSetupEvent  event) {
        if (PortalEvents.REGISTER_PORTAL.hasListeners()) {

            PortalEvents.REGISTER_PORTAL.post(new PortalBuilder());
            for (PortalBuilder.PortalMaker maker : PortalBuilder.createdPortals) {
                // Portal API 1.2.1 changes. Need to tell the user these errors
                try {
                    maker.register();
                } catch (RuntimeException e) {
                    PortalLink link = ((PortalLinkAccessor) maker.builder).getPortalLink();
                    if (link.block == null) {
                        ConsoleJS.STARTUP.error("Error registering portal for an unset frame");

                        // Clear the portal map and return because the block is null, and you can't .toString() it
                        PortalBuilder.createdPortals.clear();
                        return;
                    } else if (PortalRegistryMapAccessor.getPortalRegistry().containsKey(BuiltInRegistries.BLOCK.get(link.block))) {
                        ConsoleJS.STARTUP.error("A portal of the frame '" + link.block + "' is already registered");
                    }
                    // This should never be reached, but just in case...
                    if (link.getPortalBlock() == null) {
                        ConsoleJS.STARTUP.error("[REPORT TO PORTALJS] Portal block is null for portal frame: " + link.block);
                    }
                    if (link.portalIgnitionSource == null) {
                        ConsoleJS.STARTUP.error("Custom ignition source is unset for portal frame: " + link.block);
                    }
                    if (link.dimID == null) {
                        ConsoleJS.STARTUP.error("Destination dimension is unset for portal frame: " + link.block);
                    }
                    if (!CustomPortalsMod.dims.isEmpty() && !CustomPortalsMod.dims.containsKey(link.dimID)) {
                        ConsoleJS.STARTUP.error("Dimension was not found: " + link.dimID);
                    }
                    if (CustomPortalsMod.getDefaultPortalBlock() == null) {
                        ConsoleJS.STARTUP.error("[REPORT TO PORTAL API] Built in CustomPortalBlock is unset");
                    }
                    if (link.block.toString().equals("minecraft:obsidian")) {
                        // The API won't approve obsidian at all
                        ConsoleJS.STARTUP.error("You can't create a portal with an obsidian base");
                    }
                }
            }
            // Needed for one time use, and to save memory
            PortalBuilder.createdPortals.clear();
        }
    }
}

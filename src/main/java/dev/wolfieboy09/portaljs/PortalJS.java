package dev.wolfieboy09.portaljs;

import com.mojang.logging.LogUtils;
import dev.wolfieboy09.portaljs.kubeevents.PortalBuilder;
import dev.wolfieboy09.portaljs.kubeevents.PortalEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.RegisterEvent;
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

    private void registerPortals(RegisterEvent event) {
        if (PortalEvents.REGISTER_PORTAL.hasListeners()) {
            PortalEvents.REGISTER_PORTAL.post(new PortalBuilder());
            for (PortalBuilder.PortalMaker maker : PortalBuilder.createdPortals) {
                maker.register();
            }
            // Needed for one time use, and to save memory
            PortalBuilder.createdPortals.clear();
        }
    }
}

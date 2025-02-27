package dev.wolfieboy09.portaljs;

import com.mojang.logging.LogUtils;
import dev.wolfieboy09.portaljs.events.PortalBuilder;
import dev.wolfieboy09.portaljs.events.PortalEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(PortalJS.MOD_ID)
public class PortalJS {
    public static final String MOD_ID = "portaljs";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PortalJS(@NotNull IEventBus modEventBus, ModContainer modContainer) {
        //BlockRegistry.register(modEventBus);
        modEventBus.addListener(this::registerPortals);
        LOGGER.info("It's time to portal!");
    }

    private void registerPortals(FMLCommonSetupEvent event) {
        PortalEvents.REGISTER_PORTAL.post(new PortalBuilder());
        for (PortalBuilder.PortalMaker maker : PortalBuilder.createdPortals) {
            maker.register();
        }
        // Needed for one time use, and to save memory
        PortalBuilder.createdPortals.clear();
    }
}

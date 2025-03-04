package dev.wolfieboy09.portaljs.kubeevents;

import dev.latvian.mods.kubejs.event.KubeStartupEvent;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.kyrptonaught.customportalapi.event.CPASoundEventData;
import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.kyrptonaught.customportalapi.util.SHOULDTP;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PortalBuilder implements KubeStartupEvent {
    @HideFromJS
    public static final List<PortalMaker> createdPortals = new ArrayList<>();

    public PortalBuilder() {}

    public PortalMaker create() {
        return new PortalMaker();
    }

    @SuppressWarnings("unused")
    public static class PortalMaker {
        private final CustomPortalBuilder builder;
        //private Consumer<PortalBlockContext> randomConsumer = null;

        public PortalMaker() {
            PortalBuilder.createdPortals.add(this);
            this.builder = CustomPortalBuilder.beginPortal();
        }

        @Info("The block for the portal frame")
        public PortalMaker frameBlock(Block block) {
            this.builder.frameBlock(block);
            return this;
        }


        @Info("The item to light the portal with")
        public PortalMaker lightWithItem(Item item) {
            this.builder.lightWithItem(item);
            return this;
        }

        @Info("Light the portal with a fluid")
        public PortalMaker lightWithWater(Fluid fluid) {
            this.builder.lightWithFluid(fluid);
            return this;
        }

        @Info("Light the portal with pure water")
        public PortalMaker lightWithWater() {
            this.builder.lightWithWater();
            return this;
        }

        @Info("(Optional) Set a forced size for the portal")
        public PortalMaker forcedSize(int width, int height) {
            this.builder.forcedSize(width, height);
            return this;
        }

        @Info("(Optional) Set a custom block for the portal itself using CustomPortalBlock")
        public PortalMaker customPortalBlock(Supplier<CustomPortalBlock> block) {
            this.builder.customPortalBlock(block);
            return this;
        }

        @Info("(Optional) Configure the portal's return dimension. `onlyIgniteInReturnDimension` specifies if the portal can only be ignited in the return dimension")
        public PortalMaker returnDim(ResourceLocation dimension, boolean onlyIgniteInReturnDimension) {
            this.builder.returnDim(dimension, onlyIgniteInReturnDimension);
            return this;
        }

        @Info("(Optional) Configure the portal's return dimension.")
        public PortalMaker returnDim(ResourceLocation dimension) {
            return returnDim(dimension, false);
        }

        @Info("(Optional) Restrict portal ignition to the Overworld. Use this if you want the portal to be ignitable only in the Overworld")
        public PortalMaker onlyLightInOverworld() {
            this.builder.onlyLightInOverworld();
            return this;
        }

        @Info("(Optional) Use the flat portal style, similar to the End Portal")
        public PortalMaker flatPortal() {
            this.builder.flatPortal();
            return this;
        }

        @Info("Set the dimension to travel to when the portal is used")
        public PortalMaker setDestination(ResourceLocation dimension) {
            this.builder.destDimID(dimension);
            return this;
        }

        @Info("(Optional) Set the RGB color for the portal's tint")
        public PortalMaker tint(int r, int g, int b) {
            this.builder.tintColor(r, g, b);
            return this;
        }

        @Info("(Optional) Set the RGB color for the portal's tint")
        public PortalMaker tint(int hex) {
            this.builder.tintColor(hex);
            return this;
        }

//        public PortalMaker generateAndSet(String name, ResourceLocation dimension) {
//            InfiniverseAPI.get().getOrCreateLevel(
//                    Minecraft.getInstance().level.getServer(),
//                    ResourceKey.create(Registries.DIMENSION, dimension,
//                            () -> create)
//            );
//            return this;
//        }

        @Info("(Optional) Set the RGB color for the portal's tint")
        public PortalMaker tintColor(int r, int g, int b) {
            this.builder.tintColor(r, g, b);
            return this;
        }

        @Info("(Optional) Custom ignition source by the namespace and path (as in mod_id:some_source)")
        public PortalMaker customIgnitionSource(ResourceLocation source) {
            this.builder.customIgnitionSource(source);
            return this;
        }

        @Info("(Optional) Custom ignition source by PortalIgnitionSource")
        public PortalMaker customIgnitionSource(PortalIgnitionSource source) {
            this.builder.customIgnitionSource(source);
            return this;
        }

        @Info("(Optional) Event handling before the entity gets teleported")
        public PortalMaker beforeTeleportation(Function<Entity, SHOULDTP> entity) {
            this.builder.registerBeforeTPEvent(entity);
            return this;
        }

        @Info("(Optional) Event handling after the entity gets teleported")
        public PortalMaker afterTeleportation(Consumer<Entity> entity) {
            this.builder.registerPostTPEvent(entity);
            return this;
        }

        @Info("(Optional) Create a sound from sound event data to play to the player when in the portal")
        public PortalMaker inPortalAmbienceSound(SoundEvent event, float pitch, float volume) {
            this.builder.registerInPortalAmbienceSound(player -> new CPASoundEventData(event, pitch, volume));
            return this;
        }

        @Info("(Optional) Create a sound from sound event data to play to the player after teleportation")
        public PortalMaker afterTeleportationAmbienceSound(SoundEvent event, float pitch, float volume) {
            this.builder.registerPostTPPortalAmbience(player -> new CPASoundEventData(event, pitch, volume));
            return this;
        }

//        public PortalMaker onRandomTick(Consumer<PortalBlockContext> context) {
//            this.randomConsumer = context;
//            return this;
//        }

        @HideFromJS
        public void register() {
            //this.builder.customPortalBlock(() -> new PJSPortalBlock(this.randomConsumer));
            this.builder.registerPortal();
        }
    }
}

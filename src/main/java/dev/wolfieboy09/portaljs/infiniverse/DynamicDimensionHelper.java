package dev.wolfieboy09.portaljs.infiniverse;

public final class DynamicDimensionHelper {
//    public static @NotNull ResourceKey<Level> createKey(String dimId) {
//        return ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(KubeJS.MOD_ID, dimId));
//    }
//
//    public static void removeDimension(ResourceKey<Level> key) {
//        if (Minecraft.getInstance().level == null) return;
//        InfiniverseAPI.get().markDimensionForUnregistration(Minecraft.getInstance().level.getServer(), key);
//    }
//
//    public static void createDimension(ResourceKey<Level> key) {
//        if (Minecraft.getInstance().level == null) return;
//        InfiniverseAPI.get().getOrCreateLevel(Minecraft.getInstance().level.getServer(), key, () -> createLevel(Objects.requireNonNull(Minecraft.getInstance().level.getServer())));
//    }
//
//    public static @NotNull LevelStem createLevel(@NotNull MinecraftServer server) {
//        ServerLevel oldLevel = server.overworld();
//        DynamicOps<Tag> ops = RegistryOps.create(NbtOps.INSTANCE, server.registryAccess());
//        ChunkGenerator oldChunkGenerator = oldLevel.getChunkSource().getGenerator();
//        ChunkGenerator newChunkGenerator = ChunkGenerator.CODEC.encodeStart(ops, oldChunkGenerator)
//                .flatMap(nbt -> ChunkGenerator.CODEC.parse(ops, nbt))
//                .getOrThrow(s -> new RuntimeException(String.format("Error copying dimension: %s", s)));
//        Holder<DimensionType> typeHolder = oldLevel.dimensionTypeRegistration();
//        return new LevelStem(typeHolder, newChunkGenerator);
//    }
}
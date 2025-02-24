package dev.wolfieboy09.portaljs.helpers;

import net.kyrptonaught.customportalapi.event.CPASoundEventData;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class CPASoundEventDataHelper {
    @Contract(value = "_, _, _ -> new", pure = true)
    public static @NotNull CPASoundEventData of(SoundEvent soundEvent, float pitch, float volume) {
        return new CPASoundEventData(soundEvent, pitch, volume);
    }
}

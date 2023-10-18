package net.mukundshyam.afkhealth.sound;

import net.minecraft.client.sound.Sound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.mukundshyam.afkhealth.AFKHealth;

public class ModSounds {
    public static final SoundEvent LOW_HEALTH = registerSoundEvent("low_health");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(AFKHealth.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerSounds() {
        AFKHealth.LOGGER.info("Registering sounds for " + AFKHealth.MOD_ID);
    }
}

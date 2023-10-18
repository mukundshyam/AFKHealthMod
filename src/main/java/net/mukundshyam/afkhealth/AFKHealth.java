package net.mukundshyam.afkhealth;

import net.fabricmc.api.ModInitializer;

import net.mukundshyam.afkhealth.item.ModItems;
import net.mukundshyam.afkhealth.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AFKHealth implements ModInitializer {
	public static final String MOD_ID = "afk-health";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.registerModItems();
		ModSounds.registerSounds();
		LOGGER.info("Hello Fabric world!");
	}
}
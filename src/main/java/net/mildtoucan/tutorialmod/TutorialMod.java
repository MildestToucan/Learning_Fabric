package net.mildtoucan.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.mildtoucan.tutorialmod.block.ModBlocks;
import net.mildtoucan.tutorialmod.item.ModItemGroups;
import net.mildtoucan.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();

	}
}
package net.mildtoucan.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
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

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);
		/* Look for AbstractFurnaceBlockEntity in the Minecraft libraries to find the fuel times of the Vanilla items.
		 * The standard item smelts/cooks in 200 Ticks!
		 * So your fuel time divided by 200 is the number of items it can smelt in a standard furnace recipe.
		 * In the case of having to add several Fuel items, you can make a special ModFuelItems Class that you can then
		 * call upon here. This will make the onInitialize Method much cleaner.*/
	}
}
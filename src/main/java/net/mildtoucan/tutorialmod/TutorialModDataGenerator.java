package net.mildtoucan.tutorialmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.mildtoucan.tutorialmod.datagen.*;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}

//Once you've run datagen, a generated directory will appear with all of your generated data at main.generated
//Remember to add that directory and everything in it to Git!
//Datagen can be used with languages, but it's not necessarily recommended.
//Always double-check everything works out.
//When switching from JSON files to datagen, make backups of the JSON files.

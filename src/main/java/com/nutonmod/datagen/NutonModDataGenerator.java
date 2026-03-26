package com.nutonmod.datagen;

import com.nutonmod.NutonMod;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class NutonModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		
		NutonMod.LOGGER.info("Initializing data generators for " + NutonMod.MOD_ID);
		
		// Block Tags
		pack.addProvider(ModBlockTagsProvider::new);
		NutonMod.LOGGER.info("Added ModBlockTagsProvider provider");
		
		// Item Tags  
		pack.addProvider(ModItemTagsProvider::new);
		NutonMod.LOGGER.info("Added ModItemTagsProvider provider");
		
		NutonMod.LOGGER.info("All data generators initialized!");
	}
}

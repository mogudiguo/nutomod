package com.nutonmod;

import com.nutonmod.datagen.ModENUSLanProvider;
import com.nutonmod.datagen.ModLootTableProvider;
import com.nutonmod.datagen.ModModelsProvider;
import com.nutonmod.datagen.ModRecipesProvider;
import com.nutonmod.datagen.ModBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class NutonModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
	pack.addProvider(ModModelsProvider::new);
		pack.addProvider(ModRecipesProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModENUSLanProvider::new);
		pack.addProvider(ModBlockTagProvider::new);

	}
}

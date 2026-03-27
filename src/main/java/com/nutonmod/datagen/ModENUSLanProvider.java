package com.nutonmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModENUSLanProvider extends FabricLanguageProvider {
    public ModENUSLanProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("item.nutonmod.energy_core", "Energy Core");
        translationBuilder.add("item.nutonmod.energy_helmet", "Energy Helmet");
        translationBuilder.add("item.nutonmod.energy_leggings", "Energy Leggings");
        translationBuilder.add("item.nutonmod.energy_boots", "Energy Boots");
        translationBuilder.add("block.nutonmod.energy_block", "Energy Block");
        translationBuilder.add("item.nutonmod.energy_sword", "Energy Sword");
        translationBuilder.add("item.nutonmod.energy_chestplate", "Energy Chestplate");
        translationBuilder.add("item.nutonmod.energy_being_spawn_egg", "Energy Being Spawn Egg");
        translationBuilder.add("item.nutonmod.holy_helmet", "Holy Helmet");
        translationBuilder.add("item.nutonmod.holy_chestplate", "Holy Chestplate");
        translationBuilder.add("item.nutonmod.holy_leggings", "Holy Leggings");
        translationBuilder.add("item.nutonmod.holy_boots", "Holy Boots");
        translationBuilder.add("item.nutonmod.energy_apple", "Energy Apple");
        translationBuilder.add("item.nutonmod.energy_potato", "Energy Potato");



    }
}

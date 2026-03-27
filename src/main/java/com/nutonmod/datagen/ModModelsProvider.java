package com.nutonmod.datagen;

import com.nutonmod.block.ModBlocks;
import com.nutonmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENERGY_BLOCK);
        // 能量核心的方块状态模型在 resources 中手动配置
        // blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENERGY_CORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ENERGY_SWORD, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENERGY_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENERGY_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENERGY_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENERGY_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENERGY_BEING_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOLY_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOLY_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOLY_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOLY_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENERGY_APPLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENERGY_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANTHRACITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PROSPECTOR, Models.GENERATED);

    }
}

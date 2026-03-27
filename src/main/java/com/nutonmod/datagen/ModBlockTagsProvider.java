package com.nutonmod.datagen;

import com.nutonmod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
	public ModBlockTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	/**
	 * 配置
	 *
	 * @param arg 参数
	 */
	@Override
	public void configure(RegistryWrapper.WrapperLookup arg) {
		// 可开采标签
		getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
				.add(ModBlocks.ENERGY_CORE)
				.add(ModBlocks.ENERGY_BLOCK)
				.add(ModBlocks.ANTHRACITE_BLOCK);

		getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
				.add(ModBlocks.ENERGY_CORE)
				.add(ModBlocks.ANTHRACITE_BLOCK);


		// 其他方块标签可以在这里添加
	}
}

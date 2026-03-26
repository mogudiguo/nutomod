package com.nutonmod.datagen;

import com.nutonmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {
	public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
		super(output, completableFuture, null);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
		// 剑标签
		getOrCreateTagBuilder(ItemTags.SWORDS)
			.add(ModItems.ENERGY_SWORD);
		
		// 护甲标签
		getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
			.add(ModItems.ENERGY_CHESTPLATE);
				
		getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
			.add(ModItems.ENERGY_HELMET);
				
		getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
			.add(ModItems.ENERGY_LEGGINGS);
				
		getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
			.add(ModItems.ENERGY_BOOTS);
				
		// 可锻造的护甲（可以使用锻造模板）
		getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
			.add(ModItems.ENERGY_CHESTPLATE)
			.add(ModItems.ENERGY_HELMET)
			.add(ModItems.ENERGY_LEGGINGS)
			.add(ModItems.ENERGY_BOOTS);

		
		// 其他物品标签可以在这里添加
	}
}

package com.nutonmod.datagen;

import com.nutonmod.NutonMod;
import com.nutonmod.block.ModBlocks;
import com.nutonmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        // 9 个能量方块 (物品) -> 1 个能量核心 (方块)
        offerReversibleCompactingRecipes(recipeExporter, 
            RecipeCategory.MISC, ModItems.ENERGY_BLOCK,           // 9 个能量方块
            RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENERGY_CORE);  // 1 个能量核心
    }
}

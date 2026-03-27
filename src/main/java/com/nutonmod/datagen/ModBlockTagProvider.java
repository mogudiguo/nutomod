package com.nutonmod.datagen;

import com.nutonmod.tags.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // 注册所有可探测的矿石到 prospector_ores 标签
        getOrCreateTagBuilder(ModBlockTags.PROSPECTOR_ORES)
            .add(Blocks.DIAMOND_ORE)
            .add(Blocks.IRON_ORE)
            .add(Blocks.GOLD_ORE)
            .add(Blocks.COAL_ORE)
            .add(Blocks.REDSTONE_ORE)
            .add(Blocks.LAPIS_ORE)
            .add(Blocks.EMERALD_ORE)
            .add(Blocks.COPPER_ORE)
            
            // 深层矿石也添加进去
            .add(Blocks.DEEPSLATE_DIAMOND_ORE)
            .add(Blocks.DEEPSLATE_IRON_ORE)
            .add(Blocks.DEEPSLATE_GOLD_ORE)
            .add(Blocks.DEEPSLATE_COAL_ORE)
            .add(Blocks.DEEPSLATE_REDSTONE_ORE)
            .add(Blocks.DEEPSLATE_LAPIS_ORE)
            .add(Blocks.DEEPSLATE_EMERALD_ORE)
            .add(Blocks.DEEPSLATE_COPPER_ORE);
    }
}

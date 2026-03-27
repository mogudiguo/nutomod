package com.nutonmod.block;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.Registries;
import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
    private static final Map<Block, BlockFamily.Builder> BASE_BLOCKS_TO_BUILDERS = Maps.newHashMap();
    public static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();

    public static final BlockFamily ENERGY = register(ModBlocks.ENERGY_BLOCK)
            .stairs(ModBlocks.ENERGY_STAIRS)
            .slab(ModBlocks.ENERGY_SLAB)
            .button(ModBlocks.ENERGY_BUTTON)
            .pressurePlate(ModBlocks.ENERGY_PRESSURE_PLATE)
            .fence(ModBlocks.ENERGY_FENCE)
            .fenceGate(ModBlocks.ENERGY_FENCE_GATE)
            .wall(ModBlocks.ENERGY_WALL)
            .door(ModBlocks.ENERGY_DOOR)
            .trapdoor(ModBlocks.ENERGY_TRAPDOOR)
            .unlockCriterionName("has_energy_block")
            .build();
    
    static {
        // 在静态块中将构建好的 BlockFamily 注册到 Map 中
        BASE_BLOCKS_TO_FAMILIES.put(ModBlocks.ENERGY_BLOCK, ENERGY);
    }

    public static BlockFamily.Builder register(Block baseBlock) {
        if (BASE_BLOCKS_TO_BUILDERS.containsKey(baseBlock)) {
            throw new IllegalStateException("Duplicate block family registration for block: " + Registries.BLOCK.getId(baseBlock));
        }
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BASE_BLOCKS_TO_BUILDERS.put(baseBlock, builder);
        return builder;
    }
    
    public static Stream<BlockFamily> getBlockFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }
}

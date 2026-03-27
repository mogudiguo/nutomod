package com.nutonmod.block;

import com.nutonmod.NutonMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    
    // 能量核心方块 - 可以放置，右键切换激活状态，激活时发光等级 15
    public static final Block ENERGY_CORE = register("energy_core", 
        new EnergyCoreBlock(Block.Settings.create().requiresTool()
            .strength(3.0f, 6.0f)  // 硬度 3.0，爆炸抗性 6.0（类似石头）
            .luminance(state -> state.get(EnergyCoreBlock.ACTIVATED) ? 15 : 0))); // 激活时发光等级 15

    //能量方块
    public static final Block ENERGY_BLOCK = register("energy_block", new Block(AbstractBlock.Settings.create()
            .requiresTool()
            .strength(3.0f, 6.0f)));

    // 无烟煤块 - 由 9 个无烟煤合成，可反向分解
    public static final Block ANTHRACITE_BLOCK = register("anthracite_block", new Block(AbstractBlock.Settings.create()
            .requiresTool()
            .strength(5.0f, 6.0f))); // 比石头更硬


    private static <T extends Block> T register(String id, T block) {
        Identifier identifier = Identifier.of(NutonMod.MOD_ID, id);
        Registry.register(Registries.BLOCK, identifier, block);
        Registry.register(Registries.ITEM, identifier, new BlockItem(block, new Item.Settings()));
        return block;
    }
    
    public static void registerModBlocks() {
        NutonMod.LOGGER.info("Registering Mod Blocks for " + NutonMod.MOD_ID);
        NutonMod.LOGGER.info("Registered: energy_core (可激活方块), energy_block, anthracite_block");
    }
}

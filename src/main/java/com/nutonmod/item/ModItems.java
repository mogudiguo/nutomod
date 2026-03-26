package com.nutonmod.item;

import com.nutonmod.NutonMod;
import com.nutonmod.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import com.nutonmod.entity.ModEntities;

public class ModItems {

    // 基础物品 - 能量核心（用于放置的方块）
    public static final Item ENERGY_CORE = registerBlockItem("energy_core", ModBlocks.ENERGY_CORE);
    
    // 基础物品 - 能量方块（用于放置的方块）
    public static final Item ENERGY_BLOCK = registerBlockItem("energy_block", ModBlocks.ENERGY_BLOCK);
    
    // Rare 物品 - 能量剑（需要激活才能发挥威力）
    public static final Item ENERGY_SWORD = registerItem("energy_sword", new EnergySwordItem());
    
    // Epic 物品 - 能量胸甲（需要激活才能发挥全部效果）
    public static final Item ENERGY_CHESTPLATE = registerItem("energy_chestplate", new EnergyChestplateItem());
    
    // Epic 物品 - 能量头盔（需要激活才能发挥全部效果）
    public static final Item ENERGY_HELMET = registerItem("energy_helmet", new EnergyHelmetItem());
    
    //能量裤子
     public static final Item ENERGY_LEGGINGS = registerItem("energy_leggings", new EnergyLeggingsItem());
    
    //能量鞋子
     public static final Item ENERGY_BOOTS = registerItem("energy_boots", new EnergyBootsItem());

    // 能量人生成蛋
    public static final Item ENERGY_BEING_SPAWN_EGG = registerItem("energy_being_spawn_egg", 
        new SpawnEggItem(
            ModEntities.ENERGY_BEING,
            0x00FFFF,  // 主颜色 - 青色
            0xFFFFFF,  // 斑点颜色 - 白色
            new Item.Settings()
        )
    );


    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NutonMod.MOD_ID, id), item);
    }
    
    private static Item registerBlockItem(String id, net.minecraft.block.Block block) {
        BlockItem item = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, Identifier.of(NutonMod.MOD_ID, id), item);
        return item;
    }
    
    public static void registerModItems() {
         NutonMod.LOGGER.info("Registering Mod Items for " + NutonMod.MOD_ID);
         NutonMod.LOGGER.info("Registered: energy_core (block), energy_block (block), energy_sword, energy_chestplate, energy_helmet, energy_leggings, energy_boots, energy_being_spawn_egg");
    }
}

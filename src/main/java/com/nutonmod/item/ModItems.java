package com.nutonmod.item;

import com.nutonmod.NutonMod;
import com.nutonmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    // 基础物品 - 能量核心（用于放置的方块）
    public static final Item ENERGY_CORE = registerBlockItem("energy_core", ModBlocks.ENERGY_CORE);
    
    // Rare 物品 - 能量剑（需要激活才能发挥威力）
    public static final Item ENERGY_SWORD = registerItem("energy_sword", new EnergySwordItem());
    
    // Epic 物品 - 能量胸甲（需要激活才能发挥全部效果）
    public static final Item ENERGY_CHESTPLATE = registerItem("energy_chestplate", new EnergyChestplateItem());

    // 创建自定义创造模式标签页
    public static final ItemGroup NUTON_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        Identifier.of(NutonMod.MOD_ID, "nuton_group"),
        FabricItemGroup.builder()
            .displayName(Text.literal("\u00a7bNoton 模组"))
            .icon(() -> ENERGY_CORE.getDefaultStack())
            .entries((context, entries) -> {
                entries.add(ENERGY_CORE);
                entries.add(ENERGY_SWORD);
                entries.add(ENERGY_CHESTPLATE);
            })
            .build()
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
         NutonMod.LOGGER.info("Registered: energy_core (block), energy_sword, energy_chestplate");
    }
}

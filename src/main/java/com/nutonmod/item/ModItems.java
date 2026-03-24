package com.nutonmod.item;

import com.nutonmod.NutonMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    // 基础物品 - 最大堆叠 64，普通稀有度
    public static final Item NUTON_ITEM = registerItem("nuton_item", new Item(new Item.Settings().maxCount(64)));
    
    // 稀有物品 - 最大堆叠 16，稀有品质
    public static final Item RARE_NUTON_ITEM = registerItem("rare_nuton_item", new Item(new Item.Settings().maxCount(16).rarity(Rarity.RARE)));
    
    // 史诗物品 - 不能堆叠，史诗品质
    public static final Item EPIC_NUTON_ITEM = registerItem("epic_nuton_item", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

    // 创建自定义创造模式标签页
    public static final ItemGroup NUTON_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        Identifier.of(NutonMod.MOD_ID, "nuton_group"),
        FabricItemGroup.builder()
            .displayName(Text.literal("§bNoton 模组"))
            .icon(() -> NUTON_ITEM.getDefaultStack())
            .entries((context, entries) -> {
                entries.add(NUTON_ITEM);
                entries.add(RARE_NUTON_ITEM);
                entries.add(EPIC_NUTON_ITEM);
            })
            .build()
    );

    private static Item registerItem(String id,Item item){
        //return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(NutonMod.MOD_ID,id)), item);
        return Registry.register(Registries.ITEM, Identifier.of(NutonMod.MOD_ID,id), item);
    }
    public static void registerModItems(){
         NutonMod.LOGGER.info("Registering Mod Items for " + NutonMod.MOD_ID);
         NutonMod.LOGGER.info("Registered: nuton_item, rare_nuton_item, epic_nuton_item");
    }
}

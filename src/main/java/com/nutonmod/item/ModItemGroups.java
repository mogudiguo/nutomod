package com.nutonmod.item;

import com.nutonmod.NutonMod;
import com.nutonmod.block.ModBlocks;
import com.nutonmod.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    
    public static final ItemGroup NUTON_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        Identifier.of(NutonMod.MOD_ID, "nuton_group"),
        FabricItemGroup.builder()
            .displayName(Text.literal("\u00a7bNoton \u6a21\u7ec4"))
            .icon(() -> ModItems.ENERGY_CORE.getDefaultStack())
            .entries((context, entries) -> {
                entries.add(ModItems.ENERGY_CORE);
                entries.add(ModItems.ENERGY_SWORD);
                entries.add(ModItems.ENERGY_CHESTPLATE);
                entries.add(ModItems.ENERGY_HELMET);
                entries.add(ModItems.ENERGY_LEGGINGS);
                entries.add(ModItems.ENERGY_BOOTS);
                entries.add(ModItems.ENERGY_BEING_SPAWN_EGG);
                entries.add(Items.DIAMOND);
                entries.add(ModBlocks.ENERGY_BLOCK.asItem());
            })
            .build()
    );

    public static void registerItemGroups() {
        NutonMod.LOGGER.info("Registering Item Groups for " + NutonMod.MOD_ID);
    }
}

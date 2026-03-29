package com.nutonmod.util;

import com.nutonmod.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public class ModCustomTrades {
    public static void registerModCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER,1,factories -> {
            factories.add((new TradeOffers.BuyItemFactory(ModItems.CORN,5,12,5,2)));
            factories.add((new TradeOffers.SellItemFactory(ModItems.CORN_SEEDS,2,5,12,2,0.5f)));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER,1,factories -> {
            factories.add((new TradeOffers.BuyItemFactory(ModItems.ENERGY_APPLE,5,12,5,2)));
            factories.add((new TradeOffers.ProcessItemFactory(Items.MILK_BUCKET,1,2, ModItems.ENERGY_POTATO,3,16,3,0.5f)));
        });
    }
}


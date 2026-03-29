package com.nutonmod.villager;

import com.google.common.collect.ImmutableSet;
import com.nutonmod.NutonMod;
import com.nutonmod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;

import net.minecraft.registry.Registry;

public class Modvillagers {
    public static final VillagerProfession ENERGY_MASTER = register("energy_master",
            ModPointOfIntersetTypes.ENERGY_KEY, SoundEvents.ENTITY_VILLAGER_WORK_ARMORER);
    public static final PointOfInterestType ENERGY_POI= registerPointOfInterstType("energy_poi", ModBlocks.ENERGY_CORE);
    private static VillagerProfession register(String id, RegistryKey<PointOfInterestType>heldWorkstation, @Nullable SoundEvent worksound) {
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(NutonMod.MOD_ID, id),
                new VillagerProfession(id, entry-> entry.matchesKey(heldWorkstation), entry -> entry.matchesKey(heldWorkstation),
                        ImmutableSet.of(), ImmutableSet.of(), worksound));
    }
    private static PointOfInterestType registerPointOfInterstType(String id, Block block){
        return PointOfInterestHelper.register(Identifier.of(NutonMod.MOD_ID, id), 1, 1, block);
    }
    public static void registerVillagers() {
        NutonMod.LOGGER.info("Registering Villagers for " + NutonMod.MOD_ID);
    }
}

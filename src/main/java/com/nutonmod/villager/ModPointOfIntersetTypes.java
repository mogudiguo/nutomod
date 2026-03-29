package com.nutonmod.villager;

import com.nutonmod.NutonMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestType;

import java.rmi.registry.Registry;

public class ModPointOfIntersetTypes {
    public static final RegistryKey<PointOfInterestType> ENERGY_KEY = of("energy_poi");
    private static RegistryKey<PointOfInterestType> of(String id){
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(NutonMod.MOD_ID, id));
    }
}

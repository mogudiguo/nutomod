package com.nutonmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.nutonmod.NutonMod;

public class ModEntities {
    
    public static final EntityType<EnergyBeing> ENERGY_BEING = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(NutonMod.MOD_ID, "energy_being"),
        EntityType.Builder.create(EnergyBeing::new, SpawnGroup.MONSTER)
            .dimensions(0.6f, 1.95f)
            .eyeHeight(1.74f)
            .maxTrackingRange(10)
            .trackingTickInterval(10)
            .build()
    );
    
    public static void register() {
        NutonMod.LOGGER.info("Registering entities for " + NutonMod.MOD_ID);
        
        // 注册实体属性
        FabricDefaultAttributeRegistry.register(ENERGY_BEING, EnergyBeing.createMobAttributes());
    }
}

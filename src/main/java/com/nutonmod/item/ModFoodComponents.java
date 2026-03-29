package com.nutonmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent ENERGY_APPLE = new FoodComponent.Builder().nutrition(8).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600), 0.8f)
            .build();
    public static final FoodComponent ENERGY_POTATO = new FoodComponent.Builder().nutrition(5).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600), 0.5f)
            .build();
    public static final FoodComponent CORN = new FoodComponent.Builder().nutrition(8).saturationModifier(0.4f).build();
}

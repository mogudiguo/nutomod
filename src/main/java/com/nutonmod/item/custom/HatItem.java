package com.nutonmod.item.custom;

import com.mojang.serialization.Codec;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.processor.ProtectedBlocksStructureProcessor;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * 帽子物品 - 装饰性头盔
 */
public class HatItem extends Item implements Equipment {
    protected final Type type;

    public HatItem(Type type, Settings settings) {
        super(settings);
        this.type = type;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return this.equipAndSwap(this,world,user,hand);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return this.type.getEquipmentSlot();
    }


    public static enum Type implements StringIdentifiable {
        HAT(EquipmentSlot.HEAD, 11, "hat"),
        CHESTPLATE(EquipmentSlot.CHEST, 16, "chestplate"),
        LEGGINGS(EquipmentSlot.LEGS, 15, "leggings"),
        BOOTS(EquipmentSlot.FEET, 13, "boots"),
        BODY(EquipmentSlot.BODY, 16, "body");

        public static final Codec<ArmorItem.Type> CODEC = StringIdentifiable.createBasicCodec(ArmorItem.Type::values);
        private final EquipmentSlot equipmentSlot;
        private final String name;
        private final int baseMaxDamage;

        private Type(final EquipmentSlot equipmentSlot, final int baseMaxDamage, final String name) {
            this.equipmentSlot = equipmentSlot;
            this.name = name;
            this.baseMaxDamage = baseMaxDamage;
        }

        public int getMaxDamage(int multiplier) {
            return this.baseMaxDamage * multiplier;
        }

        public EquipmentSlot getEquipmentSlot() {
            return this.equipmentSlot;
        }

        public String getName() {
            return this.name;
        }

        public boolean isTrimmable() {
            return this == HAT || this == CHESTPLATE || this == LEGGINGS || this == BOOTS;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }
}


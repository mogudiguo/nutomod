package com.nutonmod.item;

import com.nutonmod.block.EnergyCoreBlock;
import com.nutonmod.block.ModBlocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumMap;
import java.util.List;

/**
 * 光明胸甲 - 神圣能量套装
 * 提供强大的神圣保护和生命恢复
 */
public class HolyChestplateItem extends BaseElementArmor {
    
    public HolyChestplateItem() {
        super(HolyHelmetItem.HOLY_MATERIAL, Type.CHESTPLATE, new Settings()
            .maxDamage(2048)
            .rarity(Rarity.EPIC)
            .fireproof());
    }
    
    @Override
    public void clientTick(ItemStack stack, PlayerEntity player) {
        if (!player.getWorld().isClient) {
            // 检查附近是否有激活的能量核心（10 格范围）
            if (isNearActivatedCore(player.getWorld(), player.getBlockPos())) {
                // 激活状态：提供强大的神圣增益
                if (player.age % 100 == 0) {
                    // 1. 生命恢复 II（强力治疗）
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.REGENERATION, 
                        100,
                        1,   // 等级 II
                        false, false, true
                    ));
                    
                    // 2. 防火效果（神圣庇护）
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.FIRE_RESISTANCE, 
                        220,
                        0,
                        false, false, true
                    ));
                    
                    // 3. 抗性提升 I（减少伤害）
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.RESISTANCE, 
                        100,
                        0,
                        false, false, true
                    ));
                }
            }
        }
    }
    
    /**
     * 检查附近是否有激活的能量核心
     */
    private boolean isNearActivatedCore(World world, BlockPos pos) {
        for (int x = -10; x <= 10; x++) {
            for (int y = -10; y <= 10; y++) {
                for (int z = -10; z <= 10; z++) {
                    BlockPos checkPos = pos.add(x, y, z);
                    if (world.getBlockState(checkPos).getBlock() == ModBlocks.ENERGY_CORE) {
                        if (world.getBlockState(checkPos).get(EnergyCoreBlock.ACTIVATED)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

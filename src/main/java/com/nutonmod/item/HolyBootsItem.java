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
 * 光明靴子 - 神圣能量套装
 * 提供跳跃提升和移动速度
 */
public class HolyBootsItem extends BaseElementArmor {
    
    public HolyBootsItem() {
        super(HolyHelmetItem.HOLY_MATERIAL, Type.BOOTS, new Settings()
            .maxDamage(1456)
            .rarity(Rarity.EPIC)
            .fireproof());
    }
    
    @Override
    public void clientTick(ItemStack stack, PlayerEntity player) {
        if (!player.getWorld().isClient) {
            // 检查附近是否有激活的能量核心（7 格范围）
            if (isNearActivatedCore(player.getWorld(), player.getBlockPos())) {
                // 激活状态：提供神圣轻盈
                if (player.age % 100 == 0) {
                    // 1. 跳跃提升 II（神圣轻盈）
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.JUMP_BOOST, 
                        220,
                        1,   // 等级 II
                        false, false, true
                    ));
                    
                    // 2. 速度提升 I
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SPEED, 
                        220,
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
        for (int x = -7; x <= 7; x++) {
            for (int y = -7; y <= 7; y++) {
                for (int z = -7; z <= 7; z++) {
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

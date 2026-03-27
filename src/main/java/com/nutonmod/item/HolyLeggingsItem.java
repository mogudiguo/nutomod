package com.nutonmod.item;

import com.nutonmod.block.EnergyCoreBlock;
import com.nutonmod.block.ModBlocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * 光明护腿 - 神圣能量套装
 * 提供速度加成和敏捷
 */
public class HolyLeggingsItem extends BaseElementArmor {
    
    public HolyLeggingsItem() {
        super(ArmorMaterials.NETHERITE, Type.LEGGINGS, new Settings()
            .maxDamage(1876)
            .rarity(Rarity.EPIC)
            .fireproof());
    }
    
    @Override
    public void clientTick(ItemStack stack, PlayerEntity player) {
        if (!player.getWorld().isClient) {
            // 检查附近是否有激活的能量核心（9 格范围）
            if (isNearActivatedCore(player.getWorld(), player.getBlockPos())) {
                // 激活状态：提供神圣敏捷
                if (player.age % 100 == 0) {
                    // 1. 速度提升 II（神圣迅捷）
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SPEED, 
                        220,
                        1,   // 等级 II
                        false, false, true
                    ));
                    
                    // 2. 生命恢复 I
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.REGENERATION, 
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
        for (int x = -9; x <= 9; x++) {
            for (int y = -9; y <= 9; y++) {
                for (int z = -9; z <= 9; z++) {
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

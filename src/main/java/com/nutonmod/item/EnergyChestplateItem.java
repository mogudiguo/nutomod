package com.nutonmod.item;

import com.nutonmod.block.EnergyCoreBlock;
import com.nutonmod.block.ModBlocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyChestplateItem extends ArmorItem {
    
    public EnergyChestplateItem() {
        super(ArmorMaterials.NETHERITE, Type.CHESTPLATE, new Settings()
            .maxDamage(2048)
            .rarity(Rarity.EPIC)
            .fireproof());
    }
    
    // 当玩家穿着胸甲时，每 tick 检测一次附近是否有激活的能量核心
    public void clientTick(ItemStack stack, PlayerEntity player) {
        if (!player.getWorld().isClient) {
            // 检查附近是否有激活的能量核心（10 格范围）
            if (isNearActivatedCore(player.getWorld(), player.getBlockPos())) {
                // 激活状态：提供强大增益效果（每 5 秒一次）
                if (player.age % 100 == 0) {
                    // 1. 生命恢复 II
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.REGENERATION, 
                        100, // 持续 5 秒
                        1,   // 等级 II
                        false, false, true
                    ));
                    
                    // 2. 防火效果
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.FIRE_RESISTANCE, 
                        220, // 持续 11 秒
                        0, 
                        false, false, true
                    ));
                    
                    // 3. 速度提升 I
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SPEED, 
                        220, // 持续 11 秒
                        0,   // 等级 I
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

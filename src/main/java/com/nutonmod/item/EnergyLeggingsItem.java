package com.nutonmod.item;

import com.nutonmod.block.EnergyCoreBlock;
import com.nutonmod.block.ModBlocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class EnergyLeggingsItem extends ArmorItem {
    
    public EnergyLeggingsItem() {
        super(ArmorMaterials.NETHERITE, Type.LEGGINGS, new Settings()
            .maxDamage(1876)
            .rarity(Rarity.EPIC)
            .fireproof());
    }
    
    // 当玩家穿着护腿时，每 tick 检测一次附近是否有激活的能量核心
    public void clientTick(ItemStack stack, PlayerEntity player) {
        if (!player.getWorld().isClient) {
            // 检查附近是否有激活的能量核心（9 格范围）
            if (isNearActivatedCore(player.getWorld(), player.getBlockPos())) {
                // 激活状态：提供增益效果（每 5 秒一次）
                if (player.age % 100 == 0) {
                    // 1. 速度提升 II
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SPEED, 
                        220, // 持续 11 秒
                        1,   // 等级 II
                        false, false, true
                    ));
                    
                    // 2. 生命恢复 I
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.REGENERATION, 
                        100, // 持续 5 秒
                        0,   // 等级 I
                        false, false, true
                    ));
                }
            }
        }
    }
    
    public void appendTooltip(ItemStack stack, List<Text> tooltip) {
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§5§l👖 能量护腿").formatted(Formatting.DARK_PURPLE, Formatting.BOLD));
        tooltip.add(Text.literal("§7 需要激活的能量核心才能发挥全部效果").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§e● 基础属性:").formatted(Formatting.YELLOW));
        tooltip.add(Text.literal("   - 护甲值：7 点（下界合金级别）").formatted(Formatting.GREEN));
        tooltip.add(Text.literal("   - 耐久度：1876（钻石的 3.5 倍）").formatted(Formatting.GREEN));
        tooltip.add(Text.literal("   - 防火、防爆炸").formatted(Formatting.GREEN));
        tooltip.add(Text.literal("§b● 激活时效果:").formatted(Formatting.AQUA));
        tooltip.add(Text.literal("   - 💨 速度提升 II（快速移动）").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("   - ❤️ 生命恢复 I（缓慢回血）").formatted(Formatting.RED));
        tooltip.add(Text.literal("§d● 检测范围：9 格").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
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

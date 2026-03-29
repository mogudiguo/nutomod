package com.nutonmod.item;

import com.nutonmod.block.EnergyCoreBlock;
import com.nutonmod.block.ModBlocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
            // 只有穿着全套光明盔甲时才激活效果
            if (hasFullSet(player)) {
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
    
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        addTooltipLines(tooltip);
    }
    
    private void addTooltipLines(List<Text> tooltip) {
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§5§l🛡️ 光明胸甲").formatted(Formatting.DARK_PURPLE, Formatting.BOLD));
        tooltip.add(Text.literal("§7 神圣能量套装，提供强大保护").formatted(Formatting.GRAY));
        
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal("").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("§e● 基础属性:").formatted(Formatting.YELLOW));
            tooltip.add(Text.literal("   - 护甲值：8 点").formatted(Formatting.GREEN));
            tooltip.add(Text.literal("   - 耐久度：2048").formatted(Formatting.GREEN));
            tooltip.add(Text.literal("   - 防火、防爆炸").formatted(Formatting.GREEN));
            tooltip.add(Text.literal("§d● 套装效果:").formatted(Formatting.LIGHT_PURPLE));
            tooltip.add(Text.literal("   - 需要穿着全套盔甲才能激活").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("   - 需要能量核心激活状态").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("§b● 全套激活时效果:").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("   - ❤️ 生命恢复 II").formatted(Formatting.RED));
            tooltip.add(Text.literal("   - 🔥 防火效果").formatted(Formatting.DARK_RED));
            tooltip.add(Text.literal("   - 🛡️ 抗性提升 I").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("§d● 检测范围：10 格").formatted(Formatting.LIGHT_PURPLE));
            tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.literal("§7 按 §eShift§7 查看详细信息 §r").formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}

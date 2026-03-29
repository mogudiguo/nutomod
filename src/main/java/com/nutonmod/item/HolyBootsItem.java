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
            // 只有穿着全套光明盔甲时才激活效果
            if (hasFullSet(player)) {
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
    
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        addTooltipLines(tooltip);
    }
    
    private void addTooltipLines(List<Text> tooltip) {
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§5§l👢 光明靴子").formatted(Formatting.DARK_PURPLE, Formatting.BOLD));
        tooltip.add(Text.literal("§7 神圣能量套装，轻盈如风").formatted(Formatting.GRAY));
        
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal("").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("§e● 基础属性:").formatted(Formatting.YELLOW));
            tooltip.add(Text.literal("   - 护甲值：4 点").formatted(Formatting.GREEN));
            tooltip.add(Text.literal("   - 耐久度：1456").formatted(Formatting.GREEN));
            tooltip.add(Text.literal("   - 防火、防爆炸").formatted(Formatting.GREEN));
            tooltip.add(Text.literal("§d● 套装效果:").formatted(Formatting.LIGHT_PURPLE));
            tooltip.add(Text.literal("   - 需要穿着全套盔甲才能激活").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("   - 需要能量核心激活状态").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("§b● 全套激活时效果:").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("   - 🦘 跳跃提升 II").formatted(Formatting.GOLD));
            tooltip.add(Text.literal("   - 💨 速度提升 I").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("§d● 检测范围：7 格").formatted(Formatting.LIGHT_PURPLE));
            tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.literal("§7 按 §eShift§7 查看详细信息 §r").formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}

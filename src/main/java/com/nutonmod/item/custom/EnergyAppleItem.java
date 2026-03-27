package com.nutonmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * 能量苹果 - 提供强大增益效果的食物
 */
public class EnergyAppleItem extends Item {
    
    public EnergyAppleItem() {
        super(new Settings().food(new net.minecraft.component.type.FoodComponent.Builder()
            .nutrition(8)
            .saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2400, 0), 1.0f)
            .alwaysEdible()
            .build()));
    }
    
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        
        if (!world.isClient) {
            // 给予额外增益效果
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2400, 0));
        }
        
        return result;
    }
    
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§b§l🍎 能量苹果").formatted(Formatting.AQUA, Formatting.BOLD));
        tooltip.add(Text.literal("§7 充满能量的神奇苹果").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§e● 食用效果:").formatted(Formatting.YELLOW));
        tooltip.add(Text.literal("   - ❤️ 生命恢复 II\n").formatted(Formatting.RED));
        tooltip.add(Text.literal("   - 🛡️ 抗性提升").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("   - 💙 伤害吸收").formatted(Formatting.BLUE));
        tooltip.add(Text.literal("   - 💨 速度提升").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§d● 总是可以食用").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
    }
}

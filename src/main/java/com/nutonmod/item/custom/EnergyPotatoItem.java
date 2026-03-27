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
 * 能量马铃薯 - 提供持久能量的食物
 */
public class EnergyPotatoItem extends Item {
    
    public EnergyPotatoItem() {
        super(new Settings().food(new net.minecraft.component.type.FoodComponent.Builder()
            .nutrition(5)
            .saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 0), 0.5f)
            .alwaysEdible()
            .build()));
    }
    
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        
        if (!world.isClient) {
            // 给予额外增益效果
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1200, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 1200, 0));
        }
        
        return result;
    }
    
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§e§l🥔 能量马铃薯").formatted(Formatting.YELLOW, Formatting.BOLD));
        tooltip.add(Text.literal("§7 富含能量的营养马铃薯").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§e● 食用效果:").formatted(Formatting.YELLOW));
        tooltip.add(Text.literal("   - 💨 速度提升").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("   - ⛏️ 急迫提升").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("   - ⚡ 能量充沛").formatted(Formatting.AQUA));
        tooltip.add(Text.literal("§d● 总是可以食用").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
    }
}

package com.nutonmod.item.custom;

import com.nutonmod.block.ModBlocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

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
    public TypedActionResult<ItemStack> use(World world, net.minecraft.entity.player.PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        HitResult hitResult = player.raycast(5.0, 1.0f, false);
        
        // 只有在点击到方块时才检查种植
        if (hitResult.getType() == HitResult.Type.BLOCK && hitResult instanceof BlockHitResult blockHitResult) {
            BlockPos pos = blockHitResult.getBlockPos();
            
            // 检查是否在耕地上使用
            if (world.getBlockState(pos).getBlock() == net.minecraft.block.Blocks.FARMLAND) {
                // 检查上方是否有空间
                if (world.isAir(pos.up()) || world.getBlockState(pos.up()).isReplaceable()) {
                    // 放置作物方块
                    world.setBlockState(pos.up(), ModBlocks.ENERGY_POTATO_CROP.getDefaultState());
                    
                    // 播放种植音效
                    world.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 0.8f, 1.2f);
                    
                    // 触发游戏事件
                    world.emitGameEvent(GameEvent.BLOCK_PLACE, pos.up(), GameEvent.Emitter.of(player, ModBlocks.ENERGY_POTATO_CROP.getDefaultState()));
                    
                    // 消耗一个物品
                    if (!player.isCreative()) {
                        stack.decrement(1);
                    }
                    
                    return TypedActionResult.success(stack);
                }
            }
        }
        
        // 其他情况交给父类处理（包括食用）
        return super.use(world, player, hand);
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

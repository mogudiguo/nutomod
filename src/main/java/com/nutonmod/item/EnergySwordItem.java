package com.nutonmod.item;

import com.nutonmod.block.EnergyCoreBlock;
import com.nutonmod.block.ModBlocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class EnergySwordItem extends SwordItem {
    
    public EnergySwordItem() {
        super(ToolMaterials.DIAMOND, new Settings()
            .maxDamage(1024)
            .rarity(Rarity.RARE)
            .fireproof());
    }
    
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        
        // 检查附近是否有激活的能量核心（5 格范围）
        if (isNearActivatedCore(world, attacker.getBlockPos())) {
            // 激活状态：造成额外伤害（总共约 14 点）
            // 钻石剑基础伤害是 7 点，这里造成双倍伤害
            target.damage(world.getDamageSources().playerAttack((PlayerEntity)attacker), 7.0F);
            
            // 播放蓝色粒子效果
            if (!world.isClient) {
                world.syncWorldEvent(2001, attacker.getBlockPos(), 57);
            }
            return true;
        } else {
            // 未激活状态：只造成轻微伤害
            // 不造成额外伤害，只使用基础伤害
            if (!world.isClient && attacker instanceof PlayerEntity player) {
                player.sendMessage(
                    Text.literal("\u00a7b⚠️ 需要能量核心激活！").formatted(Formatting.AQUA), 
                    true
                );
            }
            return false;
        }
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("\u00a7b\u00a7l⚔️ 能量剑").formatted(Formatting.AQUA, Formatting.BOLD));
        tooltip.add(Text.literal("\u00a77 需要激活的能量核心才能发挥威力").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("\u00a7e● 激活时:").formatted(Formatting.YELLOW));
        tooltip.add(Text.literal("   - 伤害：14 点 ⭐⭐⭐⭐⭐").formatted(Formatting.GREEN));
        tooltip.add(Text.literal("   - 蓝色粒子特效").formatted(Formatting.DARK_PURPLE));
        tooltip.add(Text.literal("\u00a7c● 未激活:").formatted(Formatting.RED));
        tooltip.add(Text.literal("   - 伤害：7 点 ⭐").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("\u00a7d● 检测范围：5 格").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
    }
    
    /**
     * 检查附近是否有激活的能量核心
     */
    private boolean isNearActivatedCore(World world, BlockPos pos) {
        for (int x = -5; x <= 5; x++) {
            for (int y = -5; y <= 5; y++) {
                for (int z = -5; z <= 5; z++) {
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

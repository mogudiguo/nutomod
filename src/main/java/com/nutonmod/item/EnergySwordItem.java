package com.nutonmod.item;

import com.nutonmod.block.EnergyCoreBlock;
import com.nutonmod.block.ModBlocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
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

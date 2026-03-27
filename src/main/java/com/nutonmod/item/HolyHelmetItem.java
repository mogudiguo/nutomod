package com.nutonmod.item;

import com.nutonmod.block.EnergyCoreBlock;
import com.nutonmod.block.ModBlocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
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
 * 光明头盔 - 神圣能量套装
 * 克制亡灵生物，提供神圣保护
 */
public class HolyHelmetItem extends BaseElementArmor {
    
    public HolyHelmetItem() {
        super(ArmorMaterials.NETHERITE, Type.HELMET, new Settings()
            .maxDamage(1638)
            .rarity(Rarity.EPIC)
            .fireproof());
    }
    
    @Override
    public void clientTick(ItemStack stack, PlayerEntity player) {
        if (!player.getWorld().isClient) {
            // 检查附近是否有激活的能量核心（8 格范围）
            if (isNearActivatedCore(player.getWorld(), player.getBlockPos())) {
                // 激活状态：提供神圣增益
                if (player.age % 100 == 0) {
                    // 1. 夜视效果（永恒光明）
                    player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.NIGHT_VISION, 
                        220,
                        0,
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
     * 对亡灵生物造成伤害加成
     */
    public float getAttackDamage(PlayerEntity player, float baseDamage) {
        if (isNearActivatedCore(player.getWorld(), player.getBlockPos())) {
            // 检查周围 10 格内是否有亡灵生物
            List<MobEntity> nearbyMobs = player.getWorld().getEntitiesByClass(
                MobEntity.class,
                player.getBoundingBox().expand(10.0),
                entity -> isUndeadMob(entity)
            );
            
            if (!nearbyMobs.isEmpty()) {
                return baseDamage + 5.0f; // 对亡灵额外伤害
            }
        }
        return baseDamage;
    }
    
    /**
     * 判断是否为亡灵生物
     */
    private boolean isUndeadMob(MobEntity entity) {
        String entityType = entity.getType().toString();
        return entityType.contains("skeleton") ||
               entityType.contains("zombie") ||
               entityType.contains("wither") ||
               entityType.contains("phantom") ||
               entityType.contains("drowned") ||
               entityType.contains("husk") ||
               entityType.contains("stray") ||
               entityType.contains("zoglin");
    }
    
    /**
     * 检查附近是否有激活的能量核心
     */
    private boolean isNearActivatedCore(World world, BlockPos pos) {
        for (int x = -8; x <= 8; x++) {
            for (int y = -8; y <= 8; y++) {
                for (int z = -8; z <= 8; z++) {
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

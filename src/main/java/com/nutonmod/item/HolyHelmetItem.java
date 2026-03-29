package com.nutonmod.item;

import com.nutonmod.block.EnergyCoreBlock;
import com.nutonmod.block.ModBlocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
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
 * 光明头盔 - 神圣能量套装
 * 克制亡灵生物，提供神圣保护
 */
public class HolyHelmetItem extends BaseElementArmor {
    
    // 光明盔甲材料（自定义）
    public static final RegistryEntry<ArmorMaterial> HOLY_MATERIAL = createHolyMaterial();
    
    public HolyHelmetItem() {
        super(HOLY_MATERIAL, Type.HELMET, new Settings()
            .maxDamage(1638)
            .rarity(Rarity.EPIC)
            .fireproof());
    }
    
    /**
     * 创建光明盔甲材料
     */
    private static RegistryEntry<ArmorMaterial> createHolyMaterial() {
        EnumMap<ArmorItem.Type, Integer> defense = new EnumMap<>(ArmorItem.Type.class);
        defense.put(ArmorItem.Type.BOOTS, 4);
        defense.put(ArmorItem.Type.LEGGINGS, 7);
        defense.put(ArmorItem.Type.CHESTPLATE, 9);
        defense.put(ArmorItem.Type.HELMET, 4);
        defense.put(ArmorItem.Type.BODY, 11);
        
        List<ArmorMaterial.Layer> layers = List.of(
            new ArmorMaterial.Layer(Identifier.of("nutonmod", "holy"))
        );
        
        return Registry.registerReference(
            Registries.ARMOR_MATERIAL,
            Identifier.of("nutonmod", "holy"),
            new ArmorMaterial(
                defense,
                9,  // 附魔能力
                SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                () -> Ingredient.ofItems(Items.DIAMOND),
                layers,
                3.0f,  // 韧性
                0.1f   // 击退抗性
            )
        );
    }
    
    @Override
    public void clientTick(ItemStack stack, PlayerEntity player) {
        if (!player.getWorld().isClient) {
            // 只有穿着全套光明盔甲时才激活效果
            if (hasFullSet(player)) {
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
    
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        addTooltipLines(tooltip);
    }
    
    private void addTooltipLines(List<Text> tooltip) {
        tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("§5§l⛑️ 光明头盔").formatted(Formatting.DARK_PURPLE, Formatting.BOLD));
        tooltip.add(Text.literal("§7 神圣能量套装，克制亡灵生物").formatted(Formatting.GRAY));
        
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal("").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("§e● 基础属性:").formatted(Formatting.YELLOW));
            tooltip.add(Text.literal("   - 护甲值：4 点").formatted(Formatting.GREEN));
            tooltip.add(Text.literal("   - 耐久度：1638").formatted(Formatting.GREEN));
            tooltip.add(Text.literal("   - 防火、防爆炸").formatted(Formatting.GREEN));
            tooltip.add(Text.literal("§d● 套装效果:").formatted(Formatting.LIGHT_PURPLE));
            tooltip.add(Text.literal("   - 需要穿着全套盔甲才能激活").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("   - 需要能量核心激活状态").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("§b● 全套激活时效果:").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("   - 👁️ 夜视效果").formatted(Formatting.DARK_GRAY));
            tooltip.add(Text.literal("   - ❤️ 生命恢复 I").formatted(Formatting.RED));
            tooltip.add(Text.literal("⚔️ ● 特殊能力:").formatted(Formatting.RED));
            tooltip.add(Text.literal("   - 对亡灵生物额外 +5 伤害").formatted(Formatting.GOLD));
            tooltip.add(Text.literal("§d● 检测范围：8 格").formatted(Formatting.LIGHT_PURPLE));
            tooltip.add(Text.literal("").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.literal("§7 按 §eShift§7 查看详细信息 §r").formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}

package com.nutonmod.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * 元素盔甲抽象基类
 * 所有元素套装都继承自这个类
 */
public abstract class AbstractElementArmor {
    
    // 基础属性（所有元素套共有）
    public static final int BASE_DURABILITY_MULTIPLIER = 15;
    public static final float BASE_TOUGHNESS = 2.0f;
    public static final float BASE_KNOCKBACK_RESISTANCE = 0.1f;
    public static final int BASE_ENCHANTABILITY = 9;
    
    // 元素类型
    private final Element element;
    
    // 盔甲材料
    protected final RegistryEntry<ArmorMaterial> material;
    
    // 元素-specific 属性
    protected final float toughness;
    protected final float knockbackResistance;
    protected final int enchantability;
    protected final int durabilityMultiplier;
    
    public AbstractElementArmor(Element element, 
                                float toughness, 
                                float knockbackResistance, 
                                int enchantability,
                                int durabilityMultiplier) {
        this.element = element;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.enchantability = enchantability;
        this.durabilityMultiplier = durabilityMultiplier;
        
        // 注册盔甲材料
        this.material = registerArmorMaterial(element, toughness, knockbackResistance, enchantability, durabilityMultiplier);
    }
    
    /**
     * 注册盔甲材料
     */
    private RegistryEntry<ArmorMaterial> registerArmorMaterial(Element element, 
                                                               float toughness, 
                                                               float knockbackResistance, 
                                                               int enchantability,
                                                               int durabilityMultiplier) {
        EnumMap<ArmorItem.Type, Integer> defense = getDefenseValues();
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(
            Identifier.of("nutonmod", element.getName())
        ));
        
        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            enumMap.put(type, defense.get(type));
        }
        
        return Registry.registerReference(
            Registries.ARMOR_MATERIAL,
            Identifier.of("nutonmod", element.getName()),
            new ArmorMaterial(
                enumMap, 
                enchantability, 
                SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 
                () -> Ingredient.ofItems(Items.DIAMOND), // 基础修复材料，子类可覆盖
                layers, 
                toughness, 
                knockbackResistance
            )
        );
    }
    
    /**
     * 获取防御值（子类可以自定义）
     */
    protected EnumMap<ArmorItem.Type, Integer> getDefenseValues() {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 4);
            map.put(ArmorItem.Type.LEGGINGS, 7);
            map.put(ArmorItem.Type.CHESTPLATE, 9);
            map.put(ArmorItem.Type.HELMET, 4);
            map.put(ArmorItem.Type.BODY, 11);
        });
    }
    
    /**
     * 获取元素类型
     */
    public Element getElement() {
        return element;
    }
    
    /**
     * 获取盔甲材料
     */
    public RegistryEntry<ArmorMaterial> getMaterial() {
        return material;
    }
    
    /**
     * 获取韧性
     */
    public float getToughness() {
        return toughness;
    }
    
    /**
     * 获取击退抗性
     */
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
    
    /**
     * 获取附魔能力
     */
    public int getEnchantability() {
        return enchantability;
    }
    
    /**
     * 获取耐久倍率
     */
    public int getDurabilityMultiplier() {
        return durabilityMultiplier;
    }
    
    /**
     * 元素枚举
     */
    public enum Element {
        ENERGY("energy", "能量", 0x00FFFF),
        FIRE("fire", "火焰", 0xFF4500),
        ICE("ice", "冰霜", 0x00BFFF),
        LIGHTNING("lightning", "雷电", 0xFFD700),
        NATURE("nature", "自然", 0x228B22),
        SHADOW("shadow", "暗影", 0x4B0082),
        HOLY("holy", "神圣", 0xFFD700),
        EARTH("earth", "大地", 0x8B4513),
        STORM("storm", "风暴", 0x708090);
        
        private final String name;
        private final String chineseName;
        private final int color;
        
        Element(String name, String chineseName, int color) {
            this.name = name;
            this.chineseName = chineseName;
            this.color = color;
        }
        
        public String getName() {
            return name;
        }
        
        public String getChineseName() {
            return chineseName;
        }
        
        public int getColor() {
            return color;
        }
    }
}

package com.nutonmod.armor;

import com.nutonmod.item.EnergyBootsItem;
import com.nutonmod.item.EnergyChestplateItem;
import com.nutonmod.item.EnergyHelmetItem;
import com.nutonmod.item.EnergyLeggingsItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * 能量盔甲 - 基础元素套装
 * 继承自 AbstractElementArmor
 */
public class EnergyArmor extends AbstractElementArmor {
    
    // 能量盔甲的材料（单例）
    public static final RegistryEntry<ArmorMaterial> MATERIAL;
    
    static {
        // 创建能量盔甲实例来注册材料
        EnergyArmor instance = new EnergyArmor();
        MATERIAL = instance.getMaterial();
    }
    
    private EnergyArmor() {
        super(
            Element.ENERGY,
            3.0f,   // 韧性（比基础高）
            0.1f,   // 击退抗性
            9,      // 附魔能力
            15      // 耐久倍率
        );
    }
    
    /**
     * 获取能量盔甲的材料
     */
    public static RegistryEntry<ArmorMaterial> getArmorMaterial() {
        return MATERIAL;
    }
    
    /**
     * 创建能量头盔
     */
    public static EnergyHelmetItem createHelmet() {
        return new EnergyHelmetItem();
    }
    
    /**
     * 创建能量胸甲
     */
    public static EnergyChestplateItem createChestplate() {
        return new EnergyChestplateItem();
    }
    
    /**
     * 创建能量护腿
     */
    public static EnergyLeggingsItem createLeggings() {
        return new EnergyLeggingsItem();
    }
    
    /**
     * 创建能量靴子
     */
    public static EnergyBootsItem createBoots() {
        return new EnergyBootsItem();
    }
}

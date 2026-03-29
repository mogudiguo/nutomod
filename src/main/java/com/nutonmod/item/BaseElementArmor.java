package com.nutonmod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

/**
 * 元素盔甲基类
 * 所有能量盔甲都继承自这个类，提供通用功能
 */
public abstract class BaseElementArmor extends ArmorItem {
    
    public BaseElementArmor(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }
    
    /**
     * 客户端 tick - 子类可以实现盔甲的主动效果
     * @param stack 盔甲的物品栈
     * @param player 穿着的玩家
     */
    public void clientTick(ItemStack stack, PlayerEntity player) {
        // 默认空实现，子类根据需要覆盖
    }
    
    /**
     * 服务器 tick - 子类可以实现盔甲的主动效果
     * @param stack 盔甲的物品栈
     * @param player 穿着的玩家
     */
    public void serverTick(ItemStack stack, PlayerEntity player) {
        // 默认空实现，子类根据需要覆盖
    }
    
    /**
     * 检查玩家是否穿着全套盔甲
     * @param player 玩家
     * @return 是否穿着全套
     */
    protected boolean hasFullSet(PlayerEntity player) {
        ItemStack helmet = player.getInventory().getArmorStack(3);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack boots = player.getInventory().getArmorStack(0);
        
        if (helmet.isEmpty() || chestplate.isEmpty() || leggings.isEmpty() || boots.isEmpty()) {
            return false;
        }
        
        // 检查所有盔甲是否都是同一类型（能量套或光明套）
        String setType = getArmorSetType(helmet);
        if (setType == null) {
            return false;
        }
        
        return setType.equals(getArmorSetType(chestplate)) &&
               setType.equals(getArmorSetType(leggings)) &&
               setType.equals(getArmorSetType(boots));
    }
    
    private String getArmorSetType(ItemStack stack) {
        Identifier id = Registries.ITEM.getId(stack.getItem());
        if (id == null) {
            return null;
        }
        String path = id.getPath();
        if (path.contains("energy_")) {
            return "energy";
        } else if (path.contains("holy_")) {
            return "holy";
        }
        return null;
    }
}

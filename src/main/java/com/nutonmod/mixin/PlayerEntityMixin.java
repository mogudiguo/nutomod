package com.nutonmod.mixin;

import com.nutonmod.item.HolyHelmetItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @ModifyVariable(
        method = "attack",
        at = @At("HEAD"),
        argsOnly = true
    )
    public float modifyAttackDamage(float baseDamage) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack helmet = player.getInventory().getArmorStack(3);
        
        if (!helmet.isEmpty() && helmet.getItem() instanceof HolyHelmetItem) {
            HolyHelmetItem holyHelmet = (HolyHelmetItem) helmet.getItem();
            return holyHelmet.getAttackDamage(player, baseDamage);
        }
        
        return baseDamage;
    }
}

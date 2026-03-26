package com.nutonmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class EnergyBeing extends MobEntity {
    
    public EnergyBeing(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 初始化目标
     *
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new LookAtEntityGoal(this, LivingEntity.class, 8.0F));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, LivingEntity.class, true));
    }
    
    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5);
    }

    /**
     * 损害
     *
     * @param source 源
     * @param amount 量
     * @return boolean
     */
    @Override
    public boolean damage(DamageSource source, float amount) {
        // 能量人被攻击时产生粒子效果
        if (!this.getWorld().isClient) {
            for (int i = 0; i < 10; i++) {
                ((net.minecraft.server.world.ServerWorld)this.getWorld()).spawnParticles(
                    ParticleTypes.END_ROD,
                    this.getX(),
                    this.getY() + this.getHeight() / 2.0,
                    this.getZ(),
                    1,
                    0.3,
                    0.3,
                    0.3,
                    0.0
                );
            }
        }
        return super.damage(source, amount);
    }

    /**
     * 获取环境声音
     *
     * @return {@link SoundEvent}
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLOCK_END_PORTAL_SPAWN;
    }

    /**
     * 受伤声
     *
     * @param source 源
     * @return {@link SoundEvent}
     */
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {return SoundEvents.ENTITY_GENERIC_HURT; }

    /**
     * 得到死亡的声音
     *
     * @return {@link SoundEvent}
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GENERIC_DEATH;
    }
}

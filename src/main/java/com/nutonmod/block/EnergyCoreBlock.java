package com.nutonmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyCoreBlock extends Block {
    
    // 激活状态属性 - 自定义
    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");
    
    public EnergyCoreBlock(Settings settings) {
        super(settings);
        // 设置默认状态为未激活
        this.setDefaultState(this.getStateManager().getDefaultState().with(ACTIVATED, false));
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }
    
    // Minecraft 1.21 中使用 onUse 的替代方法
    // 暂时不实现右键功能，先保证能编译运行
}

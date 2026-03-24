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
    
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            // 切换激活状态
            boolean isActivated = state.get(ACTIVATED);
            world.setBlockState(pos, state.with(ACTIVATED, !isActivated));
            
            // 播放音效
            world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 0.5f, 1.0f);
            
            // 发送提示信息
            String status = !isActivated ? "§b§l⚡ 能量核心已激活！" : "§7§o能量核心已关闭";
            player.sendMessage(net.minecraft.text.Text.literal(status), true);
        }
        return ActionResult.SUCCESS;
    }
}

package com.nutonmod.block.custom;

import com.mojang.serialization.MapCodec;
import com.nutonmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class EnergyPotatoCropBlock extends CropBlock {
    public static final MapCodec<EnergyPotatoCropBlock> CODEC = createCodec(EnergyPotatoCropBlock::new);
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)
    };

    @Override 
    public MapCodec<EnergyPotatoCropBlock> getCodec() {
        return CODEC;
    }
    
    public EnergyPotatoCropBlock(Settings settings) {
        super(settings.noCollision());
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.ENERGY_POTATO;
    }

    @Override
    public int getMaxAge() {
        return 3; // 4 个生长阶段 (0-3)
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }
    
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (this.isMature(state)) {
            // 成熟时收获作物
            int age = this.getAge(state);
            int bonusLevel = player.getWorld().random.nextInt(4); // 幸运加成
            
            // 掉落物品
            dropStack(world, pos, new ItemStack(ModItems.ENERGY_POTATO, age >= 3 ? 2 + bonusLevel : 1));
            
            // 重置生长阶段
            world.setBlockState(pos, this.withAge(0), Block.NOTIFY_ALL);
            
            // 播放音效和粒子效果
            world.playSound(null, pos, SoundEvents.BLOCK_CROP_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(player, state));
            
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hit);
    }
}

package com.nutonmod.block.custom;

import com.nutonmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;


public class CornCropBlock extends CropBlock {

    public static final int FIRST_STAGE_AGE = 7;
    public static final int SECOND_STAGE_AGE = 1;
    public static final  IntProperty AGE = IntProperty.of("age", 0, 8);
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.5, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 13.5, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 15.5, 15.5, 15.5),
            Block.createCuboidShape(0.0, 0.0, 0.0, 14.5, 15.5, 14.5),
            Block.createCuboidShape(0.0, 0.0, 0.0, 14.5, 8, 14.5)
    };

    public CornCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(AGE)];
    }



    @Override
    public int getMaxAge() {
        return FIRST_STAGE_AGE + SECOND_STAGE_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CORN_SEEDS;
    }
    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }
    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());
        return super.canPlaceAt(state, world, pos)|| blockState.isOf(this) && blockState.get(AGE) == 7;
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state) + this.getGrowthAmount(world);
        int maxAge = this.getMaxAge();
        if (nextAge > maxAge) {
            nextAge = maxAge;
        }
        BlockState upStage = world.getBlockState(pos.up());
        if (this.getAge(state) == 7 && upStage.isOf(Blocks.AIR)) {
            world.setBlockState(pos.up(), this.withAge(nextAge),Block.NOTIFY_ALL);
        } else {
            world.setBlockState(pos, this.withAge(nextAge-1),Block.NOTIFY_ALL);
        }
    }


    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = this.getAge(state);
        float f = getAvailableMoisture(this, world, pos);
        if(world.getBaseLightLevel(pos,0) >= 9 && random.nextInt((int)(25.0F / f) + 1) ==0 && age < this.getMaxAge()) {

           if (age == FIRST_STAGE_AGE){
               BlockState upStage = world.getBlockState(pos.up());
               if(upStage.isOf(Blocks.AIR)) {
                   world.setBlockState(pos.up(), this.withAge(age + 1), Block.NOTIFY_ALL);
               }
           }else {
               world.setBlockState(pos, this.withAge(age+1),Block.NOTIFY_ALL);
           }
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int age = this.getAge(state);
        BlockPos basePos = pos;
        BlockState baseState = state;
        BlockState below = world.getBlockState(pos.down());
        BlockState above = world.getBlockState(pos.up());

        boolean isTop = age == this.getMaxAge() && below.isOf(this) && below.get(AGE) == FIRST_STAGE_AGE;
        boolean isBottomMature = age == FIRST_STAGE_AGE && above.isOf(this) && above.get(AGE) == this.getMaxAge();

        if (isTop) {
            basePos = pos.down();
            baseState = below;
        }

        if (isTop || isBottomMature || age == this.getMaxAge()) {
            if (!world.isClient) {
                Block.dropStacks(this.withAge(this.getMaxAge()), world, basePos, null, player, player.getMainHandStack());
                world.setBlockState(basePos, this.withAge(0), Block.NOTIFY_ALL);
                if (world.getBlockState(basePos.up()).isOf(this)) {
                    world.setBlockState(basePos.up(), Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                }
                world.playSound(null, basePos, SoundEvents.BLOCK_CROP_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.emitGameEvent(GameEvent.BLOCK_DESTROY, basePos, GameEvent.Emitter.of(player, baseState));
            }
            return ActionResult.SUCCESS;
        }

        return super.onUse(state, world, pos, player, hit);
    }
}

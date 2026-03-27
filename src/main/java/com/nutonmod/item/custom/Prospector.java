package com.nutonmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Prospector extends Item {

    public Prospector(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        // 客户端直接返回，不执行逻辑
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }

        boolean foundBlock = false;
        boolean isPrecise = player.isSneaking();

        if (!isPrecise) {
            // 模糊搜索 - 3x3 范围
            for (int i = 0; i < 64; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        BlockPos posToCheck = pos.down(i).add(j, 0, k);
                        BlockState blockState = world.getBlockState(posToCheck);

                        if (isRightBlock(blockState)) {
                            String name = blockState.getBlock().getName().getString();
                            player.sendMessage(Text.literal("§a 发现矿藏：" + name + "§r"), true);
                            foundBlock = true;
                            break;
                        }
                    }
                    if (foundBlock) break;
                }
                if (foundBlock) break;
            }
        } else {
            // 精确搜索 - 正下方 1x1 范围
            for (int i = 0; i < 64; i++) {
                BlockPos posToCheck = pos.down(i);
                BlockState blockState = world.getBlockState(posToCheck);

                if (isRightBlock(blockState)) {
                    String name = blockState.getBlock().getName().getString();
                    player.sendMessage(Text.literal("§a 发现矿藏：" + name + "§r"), true);
                    foundBlock = true;
                    break;
                }
            }
        }

        if (!foundBlock) {
            player.sendMessage(Text.literal("§c 未发现任何矿藏§r"), true);
        }

        // 损坏工具
        context.getStack().damage(1, player, EquipmentSlot.MAINHAND);
        
        return ActionResult.SUCCESS;
    }

    private boolean isRightBlock(BlockState blockState) {
        return blockState.isOf(Blocks.DIAMOND_ORE) || 
               blockState.isOf(Blocks.IRON_ORE) ||
               blockState.isOf(Blocks.GOLD_ORE) ||
               blockState.isOf(Blocks.COAL_ORE) ||
               blockState.isOf(Blocks.REDSTONE_ORE) ||
               blockState.isOf(Blocks.LAPIS_ORE) ||
               blockState.isOf(Blocks.EMERALD_ORE) ||
               blockState.isOf(Blocks.COPPER_ORE);
    }
}

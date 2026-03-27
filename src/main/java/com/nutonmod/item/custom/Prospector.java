package com.nutonmod.item.custom;

import com.nutonmod.tags.ModBlockTags;
import net.minecraft.block.BlockState;
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
                            
                            // 聊天框显示详细信息（可历史记录）
                            player.sendMessage(Text.literal("§a§l探矿器报告：§r发现 §6" + name + "§r"), true);
                            
                            // ActionBar 显示简洁提示（醒目）
                            player.sendMessage(Text.literal("§e§l⚠ 发现矿藏！§r"), false);
                            
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
                    
                    // 聊天框显示详细信息
                    player.sendMessage(Text.literal("§a§l探矿器报告：§r精确位置发现 §6" + name + "§r"), true);
                    
                    // ActionBar 显示提示
                    player.sendMessage(Text.literal("§b§l⬇ 正下方有矿！§r"), false);
                    
                    foundBlock = true;
                    break;
                }
            }
        }

        if (!foundBlock) {
            // 只在聊天框显示未找到消息
            player.sendMessage(Text.literal("§7§l探矿器扫描完成§r - §c未发现任何矿藏§r"), true);
        }

        // 损坏工具
        context.getStack().damage(1, player, EquipmentSlot.MAINHAND);
        
        return ActionResult.SUCCESS;
    }

    private boolean isRightBlock(BlockState blockState) {
        // 使用标签系统判断是否为可探测矿石
        return blockState.isIn(ModBlockTags.PROSPECTOR_ORES);
    }
}

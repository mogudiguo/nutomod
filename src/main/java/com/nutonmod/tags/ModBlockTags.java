package com.nutonmod.tags;

import com.nutonmod.NutonMod;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    
    // 可探测的矿石标签
    public static final TagKey<Block> PROSPECTOR_ORES = createTag("prospector_ores");
    
    private static TagKey<Block> createTag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(NutonMod.MOD_ID, name));
    }
}

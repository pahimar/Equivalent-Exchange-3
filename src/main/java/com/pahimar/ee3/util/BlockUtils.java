package com.pahimar.ee3.util;

import com.pahimar.ee3.block.base.BlockEnum;
import com.pahimar.ee3.item.base.ItemBlockEnum;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class BlockUtils {

    private BlockUtils() {}

    public static ItemBlock getItemBlockFor(Block block) {
        return block instanceof BlockEnum ? new ItemBlockEnum((BlockEnum) block) : new ItemBlock(block);
    }
}

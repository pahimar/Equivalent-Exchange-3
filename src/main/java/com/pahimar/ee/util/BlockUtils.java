package com.pahimar.ee.util;

import com.pahimar.ee.block.base.BlockEnum;
import com.pahimar.ee.item.base.ItemBlockEnum;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class BlockUtils {

    private BlockUtils() {}

    public static ItemBlock getItemBlockFor(Block block) {
        return block instanceof BlockEnum ? new ItemBlockEnum((BlockEnum) block) : new ItemBlock(block);
    }
}

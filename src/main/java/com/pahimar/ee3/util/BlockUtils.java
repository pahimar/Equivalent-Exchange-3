package com.pahimar.ee3.util;

import com.pahimar.ee3.block.base.BlockEnumBase;
import com.pahimar.ee3.item.base.ItemBlockEnumEE;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class BlockUtils {

    private BlockUtils() {}

    public static ItemBlock getItemBlockFor(Block block) {
        return block instanceof BlockEnumBase ? new ItemBlockEnumEE((BlockEnumBase) block) : new ItemBlock(block);
    }
}

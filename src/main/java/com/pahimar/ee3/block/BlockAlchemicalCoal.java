package com.pahimar.ee3.block;

import com.pahimar.ee3.lib.Strings;
import net.minecraft.block.Block;

// TODO Finish
public class BlockAlchemicalCoal extends BlockEE
{
    public BlockAlchemicalCoal(int id)
    {
        super(id);
        setHardness(5.0F);
        setResistance(10.0F);
        setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_COAL_NAME);
        Block.setBurnProperties(id, 33, 5);
    }
}

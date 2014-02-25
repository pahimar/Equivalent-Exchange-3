package com.pahimar.ee3.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.lib.Strings;

public class ItemBlockAlchemicalFuel extends ItemMultiTexture
{
    public ItemBlockAlchemicalFuel(Block b)
    {
        super(b, ModBlocks.alchemicalFuel, Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES);
    }
}

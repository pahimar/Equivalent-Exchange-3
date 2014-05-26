package com.pahimar.ee3.item;

import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class ItemBlockAlchemicalFuel extends ItemMultiTexture
{
    public ItemBlockAlchemicalFuel(Block block)
    {
        super(ModBlocks.alchemicalFuelBlock, ModBlocks.alchemicalFuelBlock, Names.Items.ALCHEMICAL_FUEL_SUBTYPES);
    }
}

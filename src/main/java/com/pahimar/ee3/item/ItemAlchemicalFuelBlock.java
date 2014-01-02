package com.pahimar.ee3.item;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.lib.Strings;
import net.minecraft.item.ItemMultiTextureTile;

public class ItemAlchemicalFuelBlock extends ItemMultiTextureTile
{
    public ItemAlchemicalFuelBlock(int id)
    {
        super(id, ModBlocks.alchemicalFuel, Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES);
    }
}

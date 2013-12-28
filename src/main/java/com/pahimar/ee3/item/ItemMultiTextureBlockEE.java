package com.pahimar.ee3.item;

import com.pahimar.ee3.block.BlockEE;
import net.minecraft.item.ItemMultiTextureTile;

public class ItemMultiTextureBlockEE extends ItemMultiTextureTile
{
    public ItemMultiTextureBlockEE(int id, BlockEE block, String[] unlocalizedNameArray)
    {
        super(id, block, unlocalizedNameArray);
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}

package com.pahimar.ee3.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

import com.pahimar.ee3.block.ModBlocks;

public class ItemBlockAlchemicalChest extends ItemBlock
{
    public ItemBlockAlchemicalChest(Block b)
    {
        super(b);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag)
    {
        // TODO Localize and add more descriptive text
        int metaData = itemStack.getItemDamage();

        if (metaData == 0)
        {
            list.add("Small");
        }
        else if (metaData == 1)
        {
            list.add("Medium");
        }
        else if (metaData == 2)
        {
            list.add("Large");
        }
    }
}

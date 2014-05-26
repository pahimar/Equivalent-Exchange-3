package com.pahimar.ee3.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemBlockAlchemicalChest extends ItemBlock
{
    public ItemBlockAlchemicalChest(Block block)
    {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag)
    {
        // TODO Localize and add more descriptive text
        int metaData = itemStack.getItemDamage();

        if (metaData == 0)
        {
            list.add(StatCollector.translateToLocal("tooltip.ee3:alchemicalChestPrefix.small"));
        }
        else if (metaData == 1)
        {
            list.add(StatCollector.translateToLocal("tooltip.ee3:alchemicalChestPrefix.medium"));
        }
        else if (metaData == 2)
        {
            list.add(StatCollector.translateToLocal("tooltip.ee3:alchemicalChestPrefix.large"));
        }
    }
}

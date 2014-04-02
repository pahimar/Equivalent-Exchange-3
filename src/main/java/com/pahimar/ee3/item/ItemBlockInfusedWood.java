package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Messages;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemBlockInfusedWood extends ItemBlock
{
    public ItemBlockInfusedWood(Block block)
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
        int dustMetaData = (itemStack.getItemDamage() % 3) + 1;
        list.add(String.format("%s %s", StatCollector.translateToLocal(Messages.TOOLTIP_INFUSED_WITH), new ItemStack(ModItems.alchemicalDust, 1, dustMetaData).getDisplayName()));
    }
}

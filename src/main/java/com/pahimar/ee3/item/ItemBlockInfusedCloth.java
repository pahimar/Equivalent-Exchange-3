package com.pahimar.ee3.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockInfusedCloth extends ItemBlock
{
    public ItemBlockInfusedCloth(Block b)
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
        int dustMetaData = (itemStack.getItemDamage() % 3) + 1;
        list.add(String.format("%s %s", StatCollector.translateToLocal(Strings.TOOLTIP_INFUSED_WITH), new ItemStack(ModItems.alchemicalDust, 1, dustMetaData).getDisplayName()));
    }
}

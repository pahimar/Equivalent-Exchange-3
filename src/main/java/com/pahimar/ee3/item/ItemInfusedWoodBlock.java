package com.pahimar.ee3.item;

import com.pahimar.ee3.lib.ItemIds;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemInfusedWoodBlock extends ItemBlock
{
    public ItemInfusedWoodBlock(int id)
    {
        super(id);
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
        list.add(String.format("%s %s", StatCollector.translateToLocal(Strings.TOOLTIP_INFUSED_WITH), new ItemStack(ItemIds.ALCHEMICAL_DUST, 1, dustMetaData).getDisplayName()));
    }
}

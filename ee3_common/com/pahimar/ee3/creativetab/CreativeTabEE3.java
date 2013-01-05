package com.pahimar.ee3.creativetab;

import net.minecraft.creativetab.CreativeTabs;

import com.pahimar.ee3.lib.ItemIds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabEE3 extends CreativeTabs {

    public CreativeTabEE3(int par1, String par2Str) {

        super(par1, par2Str);
    }

    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {

        return ItemIds.MINIUM_SHARD;
    }

}

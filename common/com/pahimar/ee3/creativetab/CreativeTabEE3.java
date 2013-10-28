package com.pahimar.ee3.creativetab;

import net.minecraft.creativetab.CreativeTabs;

import com.pahimar.ee3.lib.ItemIds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * CreativeTabEE3
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CreativeTabEE3 extends CreativeTabs {

    public CreativeTabEE3(int tabID, String tabLabel) {

        super(tabID, tabLabel);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {

        return ItemIds.MINIUM_SHARD;
    }

}

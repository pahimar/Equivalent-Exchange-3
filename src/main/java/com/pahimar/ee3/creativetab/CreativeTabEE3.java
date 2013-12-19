package com.pahimar.ee3.creativetab;

import com.pahimar.ee3.lib.ItemIds;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CreativeTabEE3
 *
 * @author pahimar
 */
public class CreativeTabEE3 extends CreativeTabs
{

    public CreativeTabEE3(int tabID, String tabLabel)
    {

        super(tabID, tabLabel);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {

        return ItemIds.MINIUM_SHARD;
    }
}

package com.pahimar.ee3.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.pahimar.ee3.item.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CreativeTabEE3
 *
 * @author pahimar
 */
public class CreativeTabEE3 extends CreativeTabs
{
    public CreativeTabEE3(int tabID)
    {
        super(tabID, com.pahimar.ee3.lib.Reference.MOD_ID);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return "Equivalent Exchange 3";
    }

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() 
	{
		// TODO Auto-generated method stub
		return ModItems.miniumShard;
	}
}

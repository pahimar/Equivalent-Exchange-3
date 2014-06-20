package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Names;

public class ItemDarkMatter extends ItemEE 
{
	public ItemDarkMatter()
	{
		  super();
	      this.setUnlocalizedName(Names.Items.DARK_MATTER);
	      
	      //override stack size setting
	      this.maxStackSize = 64;
	}
}

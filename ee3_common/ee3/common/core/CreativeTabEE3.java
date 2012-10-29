package ee3.common.core;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import ee3.common.item.ModItems;
import ee3.common.lib.ItemIds;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

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

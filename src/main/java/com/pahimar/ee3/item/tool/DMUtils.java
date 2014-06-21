package com.pahimar.ee3.item.tool;

import com.pahimar.ee3.reference.IChargeable;
import com.pahimar.ee3.reference.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Common functions for the Dark Matter tools
 *
 */
public class DMUtils 
{
	/**
	 * Increases the charge on an ee3 power item if the provided
	 * ItemStack is a power item
	 * @param itemStack
	 */
	public static void bumpChargeOnItem(ItemStack itemStack) 
	{
		//make sure that we're acting on a chargeable item
		assert(itemStack.getItem() instanceof IChargeable);
		
		if(itemStack.getItemDamage() > 0)
		{
			itemStack.setItemDamage(itemStack.getItemDamage() - 1);
		}
		else
		{
			//reset charge to default
			itemStack.setItemDamage(Reference.MAX_ITEM_CHARGES);
		}
		
	}
	
	/**
	 * material for Dark Matter
	 */
	public static final Item.ToolMaterial MATERIALDARKMATTER = EnumHelper.addToolMaterial("DARKMATTER", 3, Reference.MAX_ITEM_CHARGES, 4.0F, 4.0F, 0);
	
	
	/**
	 * Computes the efficiency bonus of a DM tool from its durability
	 * @param durability
	 * @return
	 */
	public static int computeEfficiencyBonus(int durability)
	{
		return (4 * (Reference.MAX_ITEM_CHARGES - durability));
	}
	
}

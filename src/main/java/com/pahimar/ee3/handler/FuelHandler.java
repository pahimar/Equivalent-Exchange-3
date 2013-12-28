package com.pahimar.ee3.handler;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.item.ModItems;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class FuelHandler implements IFuelHandler
{
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if (fuel.itemID == ModItems.alchemicalCoal.itemID)
        {
            return 8 * TileEntityFurnace.getItemBurnTime(new ItemStack(Item.coal));
        }
        else if (fuel.itemID == ModBlocks.alchemicalCoal.blockID)
        {
            return 9 * getBurnTime(new ItemStack(ModItems.alchemicalCoal));
        }
        else if (fuel.itemID == ModItems.mobiusFuel.itemID)
        {
            return 8 * getBurnTime(new ItemStack(ModItems.alchemicalCoal));
        }
        else if (fuel.itemID == ModBlocks.mobiusFuel.blockID)
        {
            return 9 * getBurnTime(new ItemStack(ModItems.mobiusFuel));
        }
        else if (fuel.itemID == ModItems.aeternalisFuel.itemID)
        {
            return 8 * getBurnTime(new ItemStack(ModItems.mobiusFuel));
        }
        else if (fuel.itemID == ModBlocks.aeternalisFuel.blockID)
        {
            return 9 * getBurnTime(new ItemStack(ModItems.aeternalisFuel));
        }

        return 0;
    }
}

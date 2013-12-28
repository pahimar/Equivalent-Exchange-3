package com.pahimar.ee3.handler;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.item.ModItems;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class FuelHandler implements IFuelHandler
{
    private static final ItemStack ALCHEMICAL_COAL_STACK = new ItemStack(ModItems.alchemicalFuel, 1, 0);
    private static final ItemStack MOBIUS_FUEL_STACK = new ItemStack(ModItems.alchemicalFuel, 1, 1);
    private static final ItemStack AETERNALIS_FUEL_STACK = new ItemStack(ModItems.alchemicalFuel, 1, 2);

    private static final ItemStack ALCHEMICAL_COAL_BLOCK_STACK = new ItemStack(ModBlocks.alchemicalFuel, 1, 0);
    private static final ItemStack MOBIUS_FUEL_BLOCK_STACK = new ItemStack(ModBlocks.alchemicalFuel, 1, 1);
    private static final ItemStack AETERNALIS_FUEL_BLOCK_STACK = new ItemStack(ModBlocks.alchemicalFuel, 1, 2);

    @Override
    public int getBurnTime(ItemStack fuel)
    {
        /**
         * Alchemical Coal
         */
        if (fuel.itemID == ALCHEMICAL_COAL_STACK.itemID && fuel.getItemDamage() == ALCHEMICAL_COAL_STACK.getItemDamage())
        {
            return 8 * TileEntityFurnace.getItemBurnTime(new ItemStack(Item.coal));
        }
        else if (fuel.itemID == ALCHEMICAL_COAL_BLOCK_STACK.itemID && fuel.getItemDamage() == ALCHEMICAL_COAL_BLOCK_STACK.getItemDamage())
        {
            return 9 * getBurnTime(ALCHEMICAL_COAL_STACK);
        }
        /**
         * Mobius Fuel
         */
        else if (fuel.itemID == MOBIUS_FUEL_STACK.itemID && fuel.getItemDamage() == MOBIUS_FUEL_STACK.getItemDamage())
        {
            return 8 * getBurnTime(ALCHEMICAL_COAL_STACK);
        }
        else if (fuel.itemID == MOBIUS_FUEL_BLOCK_STACK.itemID && fuel.getItemDamage() == MOBIUS_FUEL_BLOCK_STACK.getItemDamage())
        {
            return 9 * getBurnTime(MOBIUS_FUEL_STACK);
        }
        /**
         * Aeternalis Fuel
         */
        else if (fuel.itemID == AETERNALIS_FUEL_STACK.itemID && fuel.getItemDamage() == AETERNALIS_FUEL_STACK.getItemDamage())
        {
            return 8 * getBurnTime(MOBIUS_FUEL_STACK);
        }
        else if (fuel.itemID == AETERNALIS_FUEL_BLOCK_STACK.itemID && fuel.getItemDamage() == AETERNALIS_FUEL_BLOCK_STACK.getItemDamage())
        {
            return 9 * getBurnTime(AETERNALIS_FUEL_STACK);
        }

        return 0;
    }
}

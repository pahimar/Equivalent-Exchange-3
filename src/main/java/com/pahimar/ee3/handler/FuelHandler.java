package com.pahimar.ee3.handler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.item.ModItems;

import cpw.mods.fml.common.IFuelHandler;

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
        if (fuel.getItem() == ALCHEMICAL_COAL_STACK.getItem() && fuel.getItemDamage() == ALCHEMICAL_COAL_STACK.getItemDamage())
        {
            return 8 * TileEntityFurnace.getItemBurnTime(new ItemStack(Items.coal));
        }
        else if (fuel.getItem() == ALCHEMICAL_COAL_BLOCK_STACK.getItem() && fuel.getItemDamage() == ALCHEMICAL_COAL_BLOCK_STACK.getItemDamage())
        {
            return 9 * getBurnTime(ALCHEMICAL_COAL_STACK);
        }
        /**
         * Mobius Fuel
         */
        else if (fuel.getItem() == MOBIUS_FUEL_STACK.getItem() && fuel.getItemDamage() == MOBIUS_FUEL_STACK.getItemDamage())
        {
            return 8 * getBurnTime(ALCHEMICAL_COAL_STACK);
        }
        else if (fuel.getItem() == MOBIUS_FUEL_BLOCK_STACK.getItem() && fuel.getItemDamage() == MOBIUS_FUEL_BLOCK_STACK.getItemDamage())
        {
            return 9 * getBurnTime(MOBIUS_FUEL_STACK);
        }
        /**
         * Aeternalis Fuel
         */
        else if (fuel.getItem() == AETERNALIS_FUEL_STACK.getItem() && fuel.getItemDamage() == AETERNALIS_FUEL_STACK.getItemDamage())
        {
            return 8 * getBurnTime(MOBIUS_FUEL_STACK);
        }
        else if (fuel.getItem() == AETERNALIS_FUEL_BLOCK_STACK.getItem() && fuel.getItemDamage() == AETERNALIS_FUEL_BLOCK_STACK.getItemDamage())
        {
            return 9 * getBurnTime(AETERNALIS_FUEL_STACK);
        }

        return 0;
    }
}

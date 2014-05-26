package com.pahimar.ee3.handler;

import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.item.ItemAlchemicalFuel;
import com.pahimar.ee3.item.ItemBlockAlchemicalFuel;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class FuelHandler implements IFuelHandler
{
    private static final ItemStack ALCHEMICAL_COAL = new ItemStack(ModItems.alchemicalFuel, 1, 0);
    private static final ItemStack MOBIUS_FUEL = new ItemStack(ModItems.alchemicalFuel, 1, 1);
    private static final ItemStack AETERNALIS_FUEL = new ItemStack(ModItems.alchemicalFuel, 1, 2);

    private static final ItemStack ALCHEMICAL_COAL_BLOCK = new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 0);
    private static final ItemStack MOBIUS_FUEL_BLOCK = new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 1);
    private static final ItemStack AETERNALIS_FUEL_BLOCK = new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 2);

    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if (fuel.getItem() instanceof ItemAlchemicalFuel)
        {
            if (fuel.getItemDamage() == ALCHEMICAL_COAL.getItemDamage())
            {
                return 8 * TileEntityFurnace.getItemBurnTime(new ItemStack(Items.coal));
            }
            else if (fuel.getItemDamage() == MOBIUS_FUEL.getItemDamage())
            {
                return 8 * getBurnTime(ALCHEMICAL_COAL);
            }
            else if (fuel.getItemDamage() == AETERNALIS_FUEL.getItemDamage())
            {
                return 8 * getBurnTime(MOBIUS_FUEL);
            }
        }
        else if (fuel.getItem() instanceof ItemBlockAlchemicalFuel)
        {
            if (fuel.getItemDamage() == ALCHEMICAL_COAL_BLOCK.getItemDamage())
            {
                return 9 * getBurnTime(ALCHEMICAL_COAL);
            }
            else if (fuel.getItemDamage() == MOBIUS_FUEL_BLOCK.getItemDamage())
            {
                return 9 * getBurnTime(MOBIUS_FUEL);
            }
            else if (fuel.getItemDamage() == AETERNALIS_FUEL_BLOCK.getItemDamage())
            {
                return 9 * getBurnTime(AETERNALIS_FUEL);
            }
        }

        return 0;
    }
}

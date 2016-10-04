package com.pahimar.ee3.handler;

import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.reference.Comparators;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.common.IFuelHandler;

import java.util.Map;
import java.util.TreeMap;

public class FuelHandler implements IFuelHandler {

    private static final Map<ItemStack, Integer> fuelBurnTimeMap = new TreeMap<>(Comparators.ID_COMPARATOR);

    private static final ItemStack ALCHEMICAL_COAL = new ItemStack(ModItems.ALCHEMICAL_FUEL, 1, 0);
    private static final ItemStack MOBIUS_FUEL = new ItemStack(ModItems.ALCHEMICAL_FUEL, 1, 1);
    private static final ItemStack AETERNALIS_FUEL = new ItemStack(ModItems.ALCHEMICAL_FUEL, 1, 2);
    private static final ItemStack ALCHEMICAL_COAL_BLOCK = new ItemStack(ModBlocks.ALCHEMICAL_FUEL, 1, 0);
    private static final ItemStack MOBIUS_FUEL_BLOCK = new ItemStack(ModBlocks.ALCHEMICAL_FUEL, 1, 1);
    private static final ItemStack AETERNALIS_FUEL_BLOCK = new ItemStack(ModBlocks.ALCHEMICAL_FUEL, 1, 2);

    @Override
    public int getBurnTime(ItemStack itemStack) {

        if (itemStack != null && fuelBurnTimeMap.get(itemStack) != null) {
            return fuelBurnTimeMap.get(itemStack);
        }

        return 0;
    }

    static {
        fuelBurnTimeMap.put(ALCHEMICAL_COAL, 8 * TileEntityFurnace.getItemBurnTime(new ItemStack(Items.COAL)));
        fuelBurnTimeMap.put(MOBIUS_FUEL, 8 * fuelBurnTimeMap.get(ALCHEMICAL_COAL));
        fuelBurnTimeMap.put(AETERNALIS_FUEL, 8 * fuelBurnTimeMap.get(MOBIUS_FUEL));
        fuelBurnTimeMap.put(ALCHEMICAL_COAL_BLOCK, 9 * fuelBurnTimeMap.get(ALCHEMICAL_COAL));
        fuelBurnTimeMap.put(MOBIUS_FUEL_BLOCK, 9 * fuelBurnTimeMap.get(MOBIUS_FUEL));
        fuelBurnTimeMap.put(AETERNALIS_FUEL_BLOCK, 9 * fuelBurnTimeMap.get(AETERNALIS_FUEL));
    }
}

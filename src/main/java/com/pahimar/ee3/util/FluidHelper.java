package com.pahimar.ee3.util;

import com.pahimar.ee3.reference.Names;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.Comparator;

public class FluidHelper
{
    public static Comparator<FluidStack> comparator = new Comparator<FluidStack>()
    {

        public int compare(FluidStack fluidStack1, FluidStack fluidStack2)
        {

            if (fluidStack1 != null)
            {
                if (fluidStack2 != null)
                {
                    //                    if (fluidStack1.fluidID == fluidStack2.fluidID)
                    if (fluidStack1.getFluid().getName().compareToIgnoreCase(fluidStack2.getFluid().getName()) == 0)
                    {
                        if (fluidStack1.amount == fluidStack2.amount)
                        {
                            if (fluidStack1.tag != null)
                            {
                                if (fluidStack2.tag != null)
                                {
                                    return (fluidStack1.tag.hashCode() - fluidStack2.tag.hashCode());
                                }
                                else
                                {
                                    return -1;
                                }
                            }
                            else
                            {
                                if (fluidStack2.tag != null)
                                {
                                    return 1;
                                }
                                else
                                {
                                    return 0;
                                }
                            }
                        }
                        else
                        {
                            return (fluidStack1.amount - fluidStack2.amount);
                        }
                    }
                    else
                    {
                        return (fluidStack1.getFluid().getName().compareToIgnoreCase(fluidStack2.getFluid().getName()));
                    }
                }
                else
                {
                    return -1;
                }
            }
            else
            {
                if (fluidStack2 != null)
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        }
    };

    public static void registerFluids()
    {
        // Register Milk in the FluidRegistry if it hasn't already been done
        if (!FluidRegistry.isFluidRegistered("milk"))
        {
            Fluid milk = new Fluid("milk").setUnlocalizedName(Names.Fluids.MILK);
            if (FluidRegistry.registerFluid(milk))
            {
                FluidContainerRegistry.registerFluidContainer(new FluidStack(milk, 1000), new ItemStack(Items.milk_bucket), new ItemStack(Items.bucket));
            }
        }
    }

    public static int compare(FluidStack fluidStack1, FluidStack fluidStack2)
    {
        return comparator.compare(fluidStack1, fluidStack2);
    }

    public static String toString(FluidStack fluidStack)
    {
        if (fluidStack != null)
        {
            return String.format("%sxfluidStack.%s", fluidStack.amount, fluidStack.getFluid().getName());
        }

        return "fluidStack[null]";
    }
}

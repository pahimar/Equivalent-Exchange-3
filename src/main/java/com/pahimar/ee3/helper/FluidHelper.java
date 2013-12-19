package com.pahimar.ee3.helper;

import com.pahimar.ee3.lib.Compare;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.Comparator;

public class FluidHelper
{

    public static void registerFluids()
    {
        // Register Milk in the FluidRegistry if it hasn't already been done
        if (!FluidRegistry.isFluidRegistered("milk"))
        {
            Fluid milk = new Fluid("milk") {
                @Override
                public String getLocalizedName() {
                    return StatCollector.translateToLocal("item.milk.name");
                }
            }.setUnlocalizedName(Item.bucketMilk.getUnlocalizedName());
            FluidRegistry.registerFluid(milk);
            FluidContainerRegistry.registerFluidContainer(new FluidStack(milk, 1000), new ItemStack(Item.bucketMilk), new ItemStack(Item.bucketEmpty));
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

    public static Comparator<FluidStack> comparator = new Comparator<FluidStack>()
    {

        public int compare(FluidStack fluidStack1, FluidStack fluidStack2)
        {

            if (fluidStack1 != null)
            {
                if (fluidStack2 != null)
                {
                    if (fluidStack1.fluidID == fluidStack2.fluidID)
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
                                    return Compare.LESSER_THAN;
                                }
                            }
                            else
                            {
                                if (fluidStack2.tag != null)
                                {
                                    return Compare.GREATER_THAN;
                                }
                                else
                                {
                                    return Compare.EQUALS;
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
                        return (fluidStack1.fluidID - fluidStack2.fluidID);
                    }
                }
                else
                {
                    return Compare.LESSER_THAN;
                }
            }
            else
            {
                if (fluidStack2 != null)
                {
                    return Compare.GREATER_THAN;
                }
                else
                {
                    return Compare.EQUALS;
                }
            }
        }
    };
}

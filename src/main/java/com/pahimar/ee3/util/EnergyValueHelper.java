package com.pahimar.ee3.util;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

import java.util.List;

public class EnergyValueHelper
{
    public static EnergyValue computeEnergyValueFromList(List<WrappedStack> wrappedStacks)
    {
        float computedValue = 0f;

        for (WrappedStack wrappedStack : wrappedStacks)
        {
            EnergyValue wrappedStackValue;
            int stackSize = -1;
            if (wrappedStack.getWrappedStack() instanceof ItemStack)
            {
                ItemStack itemStack = (ItemStack) wrappedStack.getWrappedStack();

                // Check if we are dealing with a potential fluid
                if (FluidContainerRegistry.getFluidForFilledItem(itemStack) != null)
                {
                    if (itemStack.getItem().getContainerItem(itemStack) != null)
                    {
                        stackSize = FluidContainerRegistry.getFluidForFilledItem(itemStack).amount;
                        wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValue(FluidContainerRegistry.getFluidForFilledItem(itemStack));
                    }
                    else
                    {
                        wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValue(wrappedStack);
                    }
                }
                // If we are dealing with a "tool" (container item), assume it's value is 0 (since it won't be used up in the recipe)
                // TODO Come back and optimize this a bit better - we aren't generating values properly here (we're missing things that should be lower than they are)
                else if (itemStack.getItem() != null && (itemStack.getItem().getContainerItem(itemStack) != null || !itemStack.getItem().doesContainerItemLeaveCraftingGrid(itemStack)))
                {
                    wrappedStackValue = new EnergyValue(0);
                }
                else
                {
                    wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValue(wrappedStack);
                }
            }
            else
            {
                wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValue(wrappedStack);
            }

            if (wrappedStackValue != null)
            {
                if (stackSize == -1)
                {
                    stackSize = wrappedStack.getStackSize();
                }

                computedValue += wrappedStackValue.getEnergyValue() * stackSize;
            }
            else
            {
                return null;
            }
        }

        return new EnergyValue(computedValue);
    }

    public static EnergyValue factorEnergyValue(EnergyValue energyValue, int factor)
    {
        return factorEnergyValue(energyValue, (float) factor);
    }

    public static EnergyValue factorEnergyValue(EnergyValue energyValue, float factor)
    {
        if ((Float.compare(factor, 0f) != 0) && (energyValue != null))
        {
            return new EnergyValue(energyValue.getEnergyValue() * 1f / factor, energyValue.getEnergyType());
        }
        else
        {
            return energyValue;
        }
    }
} 

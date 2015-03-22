package com.pahimar.ee3.util;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class EnergyValueHelper
{
    public static EnergyValue computeEnergyValueFromRecipe(WrappedStack outputStack, List<WrappedStack> inputStacks)
    {
        float computedValue = 0f;

        for (WrappedStack wrappedStack : inputStacks)
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
                else if (itemStack.getItem().getContainerItem(itemStack) != null)
                {
                    ItemStack containerItemStack = itemStack.getItem().getContainerItem(itemStack);

                    if (EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && EnergyValueRegistry.getInstance().hasEnergyValue(containerItemStack))
                    {
                        float itemStackValue = EnergyValueRegistry.getInstance().getEnergyValue(itemStack).getEnergyValue();
                        float containerStackValue = EnergyValueRegistry.getInstance().getEnergyValue(containerItemStack).getEnergyValue();
                        wrappedStackValue = new EnergyValue(itemStackValue - containerStackValue);
                    }
                    else
                    {
                        wrappedStackValue = new EnergyValue(0);
                    }
                }
                else if (!itemStack.getItem().doesContainerItemLeaveCraftingGrid(itemStack))
                {
                    wrappedStackValue = new EnergyValue(0);
                }
                else if (OreDictionary.getOreIDs(itemStack).length > 0)
                {
                    wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValue(wrappedStack, true);
                }
                else
                {
                    wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValue(wrappedStack);
                }
            }
            else if (wrappedStack.getWrappedStack() instanceof OreStack)
            {
                OreStack oreStack = (OreStack) wrappedStack.getWrappedStack();
                wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValue(wrappedStack);
                for (ItemStack itemStack : OreDictionary.getOres(oreStack.oreName))
                {
                    if (!itemStack.getItem().doesContainerItemLeaveCraftingGrid(itemStack))
                    {
                        wrappedStackValue = new EnergyValue(0);
                    }
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

        return factorEnergyValue(new EnergyValue(computedValue), outputStack.getStackSize());
    }

    public static EnergyValue factorEnergyValue(EnergyValue energyValue, int factor)
    {
        return factorEnergyValue(energyValue, (float) factor);
    }

    public static EnergyValue factorEnergyValue(EnergyValue energyValue, float factor)
    {
        if ((Float.compare(factor, 0f) != 0) && (energyValue != null))
        {
            return new EnergyValue(energyValue.getEnergyValue() * 1f / factor);
        }
        else
        {
            return energyValue;
        }
    }
} 

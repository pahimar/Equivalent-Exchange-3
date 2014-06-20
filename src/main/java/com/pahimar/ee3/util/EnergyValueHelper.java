package com.pahimar.ee3.util;

import com.pahimar.ee3.exchange.EnergyType;
import com.pahimar.ee3.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

import java.util.ArrayList;
import java.util.List;

public class EnergyValueHelper
{

    public static List<WrappedStack> filterStacksByEnergyValue(List<WrappedStack> unfilteredStacks, EnergyValue filterValue)
    {
        List<WrappedStack> filteredStacks = new ArrayList<WrappedStack>();

        for (WrappedStack stack : unfilteredStacks)
        {
            if (EnergyValueRegistry.getInstance().hasEnergyValue(stack))
            {
                EnergyValue value = EnergyValueRegistry.getInstance().getEnergyValue(stack);
                boolean satisfiesFilter = true;
                float[] valueSubValues = value.components;
                float[] filterValueSubValues = filterValue.components;

                for (int i = 0; i < valueSubValues.length; i++)
                {
                    if (Float.compare(valueSubValues[i], filterValueSubValues[i]) < 0)
                    {
                        satisfiesFilter = false;
                    }
                }

                if (satisfiesFilter)
                {
                    filteredStacks.add(stack);
                }
            }
        }

        return filteredStacks;
    }

    @SuppressWarnings("unused")
    public static List<WrappedStack> filterStacksByEnergyValue(float start, float end, EnergyValue filterValue)
    {
        return filterStacksByEnergyValue(EnergyValueRegistry.getInstance().getStacksInRange(start, end), filterValue);
    }

    public static EnergyValue computeEnergyValueFromList(List<WrappedStack> wrappedStacks)
    {
        float[] computedSubValues = new float[EnergyType.TYPES.length];

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
                else if (itemStack.getItem() != null && itemStack.getItem().getContainerItem(itemStack) != null)
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

                for (EnergyType energyType : EnergyType.TYPES)
                {
                    computedSubValues[energyType.ordinal()] += wrappedStackValue.components[energyType.ordinal()] * stackSize;
                }
            }
            else
            {
                return null;
            }
        }

        return new EnergyValue(computedSubValues);
    }

    public static EnergyValue factorEnergyValue(EnergyValue ExchangeEnergyValue, int factor)
    {
        return factorEnergyValue(ExchangeEnergyValue, (float) factor);
    }

    public static EnergyValue factorEnergyValue(EnergyValue ExchangeEnergyValue, float factor)
    {

        if ((Float.compare(factor, 0f) != 0) && (ExchangeEnergyValue != null))
        {
            float[] factorSubValues = ExchangeEnergyValue.components;

            for (int i = 0; i < factorSubValues.length; i++)
            {
                factorSubValues[i] = factorSubValues[i] * 1f / factor;
            }

            return new EnergyValue(factorSubValues);
        }
        else
        {
            return ExchangeEnergyValue;
        }
    }
} 

package com.pahimar.ee3.exchange;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

import java.util.ArrayList;
import java.util.List;

public class EnergyHelper
{

    public static List<WrappedStack> filterStacksByEmc(List<WrappedStack> unfilteredStacks, EnergyValue filterValue)
    {
        List<WrappedStack> filteredStacks = new ArrayList<WrappedStack>();

        for (WrappedStack stack : unfilteredStacks)
        {
            if (EnergyRegistry.getInstance().hasEnergyValue(stack))
            {
                EnergyValue value = EnergyRegistry.getInstance().getEnergyValue(stack);
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
    public static List<WrappedStack> filterStacksByEmcAndRange(float start, float end, EnergyValue filterValue)
    {
        return filterStacksByEmc(EnergyRegistry.getInstance().getStacksInRange(start, end), filterValue);
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
                        wrappedStackValue = EnergyRegistry.getInstance().getEnergyValue(FluidContainerRegistry.getFluidForFilledItem(itemStack));
                    }
                    else
                    {
                        wrappedStackValue = EnergyRegistry.getInstance().getEnergyValue(wrappedStack);
                    }
                }
                // If we are dealing with a "tool" (container item), assume it's value is 0 (since it won't be used up in the recipe)
                else if (itemStack.getItem() != null && itemStack.getItem().getContainerItem(itemStack) != null)
                {
                    wrappedStackValue = new EnergyValue(0);
                }
                else
                {
                    wrappedStackValue = EnergyRegistry.getInstance().getEnergyValue(wrappedStack);
                }
            }
            else
            {
                wrappedStackValue = EnergyRegistry.getInstance().getEnergyValue(wrappedStack);
            }

            if (wrappedStackValue != null)
            {
                if (stackSize == -1)
                {
                    stackSize = wrappedStack.getStackSize();
                }

                for (EnergyType emcType : EnergyType.TYPES)
                {
                    computedSubValues[emcType.ordinal()] += wrappedStackValue.components[emcType.ordinal()] * stackSize;
                }
            }
            else
            {
                return null;
            }
        }

        return new EnergyValue(computedSubValues);
    }

    public static EnergyValue factorEnergyValue(EnergyValue EnergyValue, int factor)
    {
        return factorEnergyValue(EnergyValue, (float) factor);
    }

    public static EnergyValue factorEnergyValue(EnergyValue EnergyValue, float factor)
    {

        if ((Float.compare(factor, 0f) != 0) && (EnergyValue != null))
        {
            float[] factorSubValues = EnergyValue.components;

            for (int i = 0; i < factorSubValues.length; i++)
            {
                factorSubValues[i] = factorSubValues[i] * 1f / factor;
            }

            return new EnergyValue(factorSubValues);
        }
        else
        {
            return EnergyValue;
        }
    }
} 

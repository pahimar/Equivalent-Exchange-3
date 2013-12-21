package com.pahimar.ee3.helper;

import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcType;
import com.pahimar.ee3.emc.EmcValue;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

import java.util.ArrayList;
import java.util.List;

public class EmcHelper
{

    public static List<WrappedStack> filterStacksByEmc(List<WrappedStack> unfilteredStacks, EmcValue filterValue)
    {
        List<WrappedStack> filteredStacks = new ArrayList<WrappedStack>();

        for (WrappedStack stack : unfilteredStacks)
        {
            if (EmcRegistry.getInstance().hasEmcValue(stack))
            {
                EmcValue value = EmcRegistry.getInstance().getEmcValue(stack);
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
    public static List<WrappedStack> filterStacksByEmcAndRange(float start, float end, EmcValue filterValue)
    {
        return filterStacksByEmc(EmcRegistry.getInstance().getStacksInRange(start, end), filterValue);
    }

    public static EmcValue computeEmcValueFromList(List<WrappedStack> wrappedStacks)
    {
        float[] computedSubValues = new float[EmcType.TYPES.length];

        for (WrappedStack wrappedStack : wrappedStacks)
        {
            EmcValue wrappedStackValue;
            int stackSize = -1;
            if (wrappedStack.getWrappedStack() instanceof ItemStack)
            {
                ItemStack itemStack = (ItemStack) wrappedStack.getWrappedStack();

                // Check if we are dealing with a potential fluid
                if (FluidContainerRegistry.getFluidForFilledItem(itemStack) != null)
                {
                    if (itemStack.getItem().getContainerItemStack(itemStack) != null)
                    {
                        stackSize = FluidContainerRegistry.getFluidForFilledItem(itemStack).amount;
                        wrappedStackValue = EmcRegistry.getInstance().getEmcValue(FluidContainerRegistry.getFluidForFilledItem(itemStack));
                    }
                    else
                    {
                        wrappedStackValue = EmcRegistry.getInstance().getEmcValue(wrappedStack);
                    }
                }
                // If we are dealing with a "tool" (container item), assume it's value is 0 (since it won't be used up in the recipe)
                else if (itemStack.getItem().getContainerItemStack(itemStack) != null)
                {
                    wrappedStackValue = new EmcValue(0);
                }
                else
                {
                    wrappedStackValue = EmcRegistry.getInstance().getEmcValue(wrappedStack);
                }
            }
            else
            {
                wrappedStackValue = EmcRegistry.getInstance().getEmcValue(wrappedStack);
            }

            if (wrappedStackValue != null)
            {
                if (stackSize == -1)
                {
                    stackSize = wrappedStack.getStackSize();
                }

                for (EmcType emcType : EmcType.TYPES)
                {
                    computedSubValues[emcType.ordinal()] += wrappedStackValue.components[emcType.ordinal()] * stackSize;
                }
            }
            else
            {
                return null;
            }
        }

        return new EmcValue(computedSubValues);
    }

    public static EmcValue factorEmcValue(EmcValue emcValue, int factor)
    {
        return factorEmcValue(emcValue, (float) factor);
    }

    public static EmcValue factorEmcValue(EmcValue emcValue, float factor)
    {

        if ((Float.compare(factor, 0f) != 0) && (emcValue != null))
        {
            float[] factorSubValues = emcValue.components;

            for (int i = 0; i < factorSubValues.length; i++)
            {
                factorSubValues[i] = factorSubValues[i] * 1f / factor;
            }

            return new EmcValue(factorSubValues);
        }
        else
        {
            return emcValue;
        }
    }
} 

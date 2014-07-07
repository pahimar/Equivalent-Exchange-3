package com.pahimar.ee3.recipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

import java.util.Arrays;
import java.util.List;

public class RecipesFluidContainers
{
    private static Multimap<WrappedStack, List<WrappedStack>> fluidContainerRecipes = null;

    public static Multimap<WrappedStack, List<WrappedStack>> getFluidContainerRecipes()
    {
        if (fluidContainerRecipes == null)
        {
            init();
        }
        return fluidContainerRecipes;
    }

    private static void init()
    {
        fluidContainerRecipes = HashMultimap.create();

        for (FluidContainerData fluidContainerData : FluidContainerRegistry.getRegisteredFluidContainerData())
        {
            if (fluidContainerData.fluid != null)
            {
                if (fluidContainerData.filledContainer != null && fluidContainerData.emptyContainer != null)
                {
                    fluidContainerRecipes.put(new WrappedStack(fluidContainerData.filledContainer.copy()), Arrays.asList(new WrappedStack(fluidContainerData.fluid.copy()), new WrappedStack(fluidContainerData.emptyContainer.copy())));
                }
            }
        }
    }
}

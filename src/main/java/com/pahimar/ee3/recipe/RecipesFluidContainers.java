package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.RecipeRegistryProxy;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

import java.util.Arrays;

public class RecipesFluidContainers
{
    public static void registerRecipes()
    {
        for (FluidContainerData fluidContainerData : FluidContainerRegistry.getRegisteredFluidContainerData())
        {
            if (fluidContainerData.fluid != null)
            {
                if (fluidContainerData.filledContainer != null && fluidContainerData.emptyContainer != null)
                {
                    RecipeRegistryProxy.addRecipe(fluidContainerData.filledContainer.copy(), Arrays.asList(fluidContainerData.fluid.copy(), fluidContainerData.emptyContainer.copy()));
                }
            }
        }
    }
}

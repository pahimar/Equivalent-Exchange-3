package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

public class RecipesFluidContainers {

    public void registerRecipes() {

        for (FluidContainerData fluidContainerData : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            if (fluidContainerData.fluid != null && fluidContainerData.filledContainer != null && fluidContainerData.emptyContainer != null) {
                RecipeRegistryProxy.addRecipe(fluidContainerData.filledContainer.copy(), fluidContainerData.fluid.copy(), fluidContainerData.emptyContainer.copy());
            }
        }
    }
}

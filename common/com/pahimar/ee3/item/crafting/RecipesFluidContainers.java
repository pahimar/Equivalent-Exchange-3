package com.pahimar.ee3.item.crafting;

import java.util.Arrays;
import java.util.List;

import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.item.WrappedStack;

public class RecipesFluidContainers
{
    private static Multimap<WrappedStack, List<WrappedStack>> fluidContainerRecipes = null;
    
    public static Multimap<WrappedStack, List<WrappedStack>> getFluidContainerRecipes() {
        
        if (fluidContainerRecipes == null) {
            init();
        }
        return fluidContainerRecipes;
    }
    
    private static void init() {
        
        fluidContainerRecipes = HashMultimap.create();
        
        for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            
            if (data.filledContainer != null && data.fluid != null && data.emptyContainer != null) {
                
                fluidContainerRecipes.put(new WrappedStack(data.filledContainer), Arrays.asList(new WrappedStack(data.fluid), new WrappedStack(data.emptyContainer)));
            }
        }
    }
}

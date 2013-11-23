package com.pahimar.ee3.item.crafting;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipesFluidContainers
{
    private static Multimap<CustomWrappedStack, List<CustomWrappedStack>> fluidContainerRecipes = null;
    
    public static Multimap<CustomWrappedStack, List<CustomWrappedStack>> getFluidContainerRecipes() {
        
        if (fluidContainerRecipes == null) {
            init();
        }
        return fluidContainerRecipes;
    }
    
    private static void init() {
        
        fluidContainerRecipes = HashMultimap.create();
        
        for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            
            if (data.fluid.getFluid().getBlockID() > 0) {
                
                Block fluidBlock = Block.blocksList[data.fluid.getFluid().getBlockID()];
                
                if (fluidBlock != null) {
                    
                    fluidContainerRecipes.put(new CustomWrappedStack(data.filledContainer), Arrays.asList(new CustomWrappedStack(fluidBlock), new CustomWrappedStack(data.emptyContainer)));
                }
            }
        }
    }
}

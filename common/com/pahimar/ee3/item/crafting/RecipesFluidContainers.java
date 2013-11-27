package com.pahimar.ee3.item.crafting;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
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
            
            if (data.fluid.getFluid().getBlockID() > 0) {
                
                Block fluidBlock = Block.blocksList[data.fluid.getFluid().getBlockID()];
                
                if (fluidBlock != null) {
                    
                    fluidContainerRecipes.put(new WrappedStack(data.filledContainer), Arrays.asList(new WrappedStack(fluidBlock), new WrappedStack(data.emptyContainer)));
                }
            }
        }
    }
}

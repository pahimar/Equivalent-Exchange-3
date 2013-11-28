package com.pahimar.ee3.core.helper;

import java.util.Comparator;

import com.pahimar.ee3.item.WrappedStack;
import com.pahimar.ee3.lib.Compare;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidHelper {

    public static int compare(FluidStack fluidStack1, FluidStack fluidStack2) {
        return comparator.compare(fluidStack1, fluidStack2);
    }
    
    public static String toString(FluidStack fluidStack) {
        
        if (fluidStack != null) {
            return String.format("%sxfluidStack.%s", fluidStack.amount, fluidStack.getFluid().getName());
        }
        
        return "fluidStack[null]";
    }
    
    public static Block getFluidBlock(Fluid fluid) {
        
        if (fluid != null && fluid.getBlockID() > 0) {
            
            Block block = Block.blocksList[fluid.getBlockID()];
            if (block != null) {
                
                return block;
            }
        }
        
        return null;
    }
    
    public static WrappedStack getFluidBlock(WrappedStack wrappedStack) {
        
        if (wrappedStack.getWrappedStack() instanceof FluidStack) {
            
            FluidStack fluidStack = (FluidStack)wrappedStack.getWrappedStack();
            if (fluidStack != null) {
                
                Block block = getFluidBlock(fluidStack.getFluid());
                if (block != null)
                {
                    return new WrappedStack(block);
                }
            }
        }
        
        return null;
    }
    
    public static Comparator<FluidStack> comparator = new Comparator<FluidStack>() {

        public int compare(FluidStack fluidStack1, FluidStack fluidStack2) {

            if (fluidStack1 != null) {
                if (fluidStack2 != null) {
                    if (fluidStack1.fluidID == fluidStack2.fluidID) {
                        if (fluidStack1.amount == fluidStack2.amount) {
                            if (fluidStack1.tag != null) {
                                if (fluidStack2.tag != null) {
                                    return (fluidStack1.tag.hashCode() - fluidStack2.tag.hashCode());
                                }
                                else {
                                    return Compare.LESSER_THAN;
                                }
                            }
                            else {
                                if (fluidStack2.tag != null) {
                                    return Compare.GREATER_THAN;
                                }
                                else {
                                    return Compare.EQUALS;
                                }
                            }
                        }
                        else {
                            return (fluidStack1.amount - fluidStack2.amount);
                        }
                    }
                    else {
                        return (fluidStack1.fluidID - fluidStack2.fluidID);
                    }
                }
                else {
                    return Compare.LESSER_THAN;
                }
            }
            else {
                if (fluidStack2 != null) {
                    return Compare.GREATER_THAN;
                }
                else {
                    return Compare.EQUALS;
                }
            }
        }
    };
}

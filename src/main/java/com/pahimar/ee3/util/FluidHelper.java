package com.pahimar.ee3.util;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FluidHelper {

    /**
     *  An awkward fix for the case where milk may not be registered as a liquid but we want to assign
     *  an energy value to milk (the liquid). TLDR we substitute a dummy liquid and assign value/recipes to it
     */
    public static void initMilk() {

        if (!FluidRegistry.isFluidRegistered("milk")) {
            if (FluidRegistry.registerFluid(new Fluid("ee3_milk", ResourceLocationHelper.getResourceLocation("milk_still"), ResourceLocationHelper.getResourceLocation("milk_flow")))) {
                RecipeRegistryProxy.addRecipe(Items.MILK_BUCKET, Items.BUCKET, new FluidStack(FluidRegistry.getFluid("ee3_milk"), Fluid.BUCKET_VOLUME));
                EnergyValueRegistryProxy.setEnergyValue(FluidRegistry.getFluid("ee3_milk"), 0.064, EnergyValueRegistryProxy.Phase.PRE_CALCULATION);
            }
        }
        else {
            RecipeRegistryProxy.addRecipe(Items.MILK_BUCKET, Items.BUCKET, new FluidStack(FluidRegistry.getFluid("milk"), Fluid.BUCKET_VOLUME));
            EnergyValueRegistryProxy.setEnergyValue(FluidRegistry.getFluid("milk"), 0.064, EnergyValueRegistryProxy.Phase.PRE_CALCULATION);
        }
    }
}

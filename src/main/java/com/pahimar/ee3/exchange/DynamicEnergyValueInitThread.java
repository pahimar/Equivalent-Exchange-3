package com.pahimar.ee3.exchange;

import com.pahimar.ee3.recipe.AludelRecipeManager;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.util.LogHelper;

public class DynamicEnergyValueInitThread implements Runnable
{
    private static DynamicEnergyValueInitThread instance = new DynamicEnergyValueInitThread();

    public static void initEnergyValueRegistry()
    {
        new Thread(instance, "DynamicEMC Thread").start();
    }

    @Override
    public void run()
    {
        // Add in recipes to the RecipeRegistry *just* before we do calculations
        RecipeRegistry.getInstance().registerVanillaRecipes();
        AludelRecipeManager.registerRecipes();

        long startTime = System.currentTimeMillis();
        EnergyValueRegistry.getInstance().init();
        LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "DynamicEMC system initialized after {} ms", System.currentTimeMillis() - startTime);
    }
}

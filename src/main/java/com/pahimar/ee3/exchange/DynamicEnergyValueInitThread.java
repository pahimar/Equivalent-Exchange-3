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
        try
        {
            // Add in recipes to the RecipeRegistry *just* before we do calculations
            RecipeRegistry.getInstance().registerVanillaRecipes();
            AludelRecipeManager.registerRecipes();

            long startTime = System.currentTimeMillis();
            EnergyValueRegistry.getInstance().init();
            LogHelper.info(String.format("DynamicEMC system initialized after %s ms", System.currentTimeMillis() - startTime));
        }
        catch (Throwable t)
        {
            EnergyValueRegistry.getInstance().setFailed(t);
        }
        finally
        {
            synchronized (EnergyValueRegistry.getInstance())
            {
                EnergyValueRegistry.getInstance().makeReady();
                EnergyValueRegistry.getInstance().notifyAll();
            }
        }
    }
}

package com.pahimar.ee3.exchange;

import com.pahimar.ee3.recipe.AludelRecipeManager;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.item.crafting.CraftingManager;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class DynamicEnergyValueInitThread implements Runnable
{
    private static DynamicEnergyValueInitThread instance = new DynamicEnergyValueInitThread();
    private RegistrySession session;

    private void init()
    {
        if(this.session != null)
            return;

        this.session = new RegistrySession();
        this.session.init();
    }

    @Override
    public void run()
    {
        // Add in recipes to the RecipeRegistry *just* before we do calculations
        Object[] recipes = this.session.getVanillaRecipeArray();
        RecipeRegistry.getInstance().registerVanillaRecipes(recipes);
        AludelRecipeManager.registerRecipes();

        long startTime = System.currentTimeMillis();
        try
        {
            EnergyValueRegistry.getInstance().init();
        } catch (OperationNotSupportedException e)
        {
            // TODO This should never happen, if it does, what should we do?
            e.printStackTrace();
        }
        LogHelper.info(String.format("DynamicEMC system initialized after %s ms", System.currentTimeMillis() - startTime));
    }

    public static void initEnergyValueRegistry()
    {
        instance.init();
        new Thread(instance, "DynamicEMC Thread").start();
    }

    private final class RegistrySession
    {
        private final List<Object> vanillaRecipeArray;

        private RegistrySession()
        {
            this.vanillaRecipeArray = new ArrayList<Object>();
        }

        public void init()
        {
            // Cache the list here to prevent sync conflicts.
            this.vanillaRecipeArray.addAll(CraftingManager.getInstance().getRecipeList());
        }

        public Object[] getVanillaRecipeArray()
        {
            return vanillaRecipeArray.toArray();
        }
    }
}

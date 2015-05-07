package com.pahimar.ee3.api.recipe;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;
import net.minecraft.item.ItemStack;

// TODO Clean this up and make it more nice for modders. Consider this very volatile for the time being
public class AludelRecipeProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAludelRecipeManager().addRecipe(recipeOutput, recipeInputStack, recipeInputDust);
        }
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getAludelRecipeManager().getResult(recipeInputStack, recipeInputDust);
        }

        return null;
    }


    private static class EE3Wrapper
    {
        private static EquivalentExchange3 ee3mod;
    }

    private static void init()
    {
        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }
}

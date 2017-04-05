package com.pahimar.ee.api.recipe;

import com.pahimar.ee.EquivalentExchange;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

// TODO Clean this up and make it more nice for modders. Consider this very volatile for the time being
public final class AludelRecipeProxy {

    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputDust) {

        init();

        if (mod != null) {
            ModWrapper.mod.getAludelRecipeManager().addRecipe(recipeOutput, recipeInputStack, recipeInputDust);
        }
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputDust) {

        init();

        if (mod != null) {
            return ModWrapper.mod.getAludelRecipeManager().getResult(recipeInputStack, recipeInputDust);
        }

        return null;
    }

    @Mod.Instance("ee")
    private static Object mod;

    private static class ModWrapper {
        private static EquivalentExchange mod;
    }

    private static void init() {
        if (mod != null) {
            ModWrapper.mod = (EquivalentExchange) mod;
        }
    }
}

package com.pahimar.ee3.recipe;

import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.item.crafting.RecipeAludel;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class RecipesAludel {
    private static RecipesAludel aludelRegistry = null;

    private List<RecipeAludel> aludelRecipes;

    private RecipesAludel() {
        aludelRecipes = new ArrayList<RecipeAludel>();
    }

    public static RecipesAludel getInstance() {
        if (aludelRegistry == null) {
            aludelRegistry = new RecipesAludel();
            aludelRegistry.init();
        }

        return aludelRegistry;
    }

    private void init() {
        // Ash + Verdant = Azure
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalDust, 1, 2), new ItemStack(ModItems.alchemicalDust, 1, 0), new ItemStack(ModItems.alchemicalDust, 32, 1));

        // Ash + Azure = Minium
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalDust, 1, 3), new ItemStack(ModItems.alchemicalDust, 1, 0), new ItemStack(ModItems.alchemicalDust, 4, 2));

        // Alchemical Coal
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel, 1, 0), new ItemStack(Items.coal, 1, 0), new ItemStack(ModItems.alchemicalDust, 32, 1));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel, 1, 0), new ItemStack(Items.coal, 1, 0), new ItemStack(ModItems.alchemicalDust, 1, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel, 4, 0), new ItemStack(Items.coal, 4, 0), new ItemStack(ModItems.alchemicalDust, 1, 3));

        // Mobius Fuel
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel, 1, 1), new ItemStack(ModItems.alchemicalFuel, 1, 0), new ItemStack(ModItems.alchemicalDust, 7, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel, 1, 1), new ItemStack(ModItems.alchemicalFuel, 1, 0), new ItemStack(ModItems.alchemicalDust, 2, 3));

        // Aeternalis Fuel
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel, 1, 2), new ItemStack(ModItems.alchemicalFuel, 1, 1), new ItemStack(ModItems.alchemicalDust, 56, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel, 1, 2), new ItemStack(ModItems.alchemicalFuel, 1, 1), new ItemStack(ModItems.alchemicalDust, 14, 3));

        // Alchemical Chest
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 0), new ItemStack(Blocks.chest, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust, 8, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 0), new ItemStack(Blocks.trapped_chest, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust, 8, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 1), new ItemStack(Blocks.chest, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust, 8, 2));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 1), new ItemStack(ModBlocks.alchemicalChest, 1, 0), new ItemStack(ModItems.alchemicalDust, 8, 2));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 2), new ItemStack(Blocks.chest, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust, 8, 3));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 2), new ItemStack(ModBlocks.alchemicalChest, 1, 0), new ItemStack(ModItems.alchemicalDust, 8, 3));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 2), new ItemStack(ModBlocks.alchemicalChest, 1, 1), new ItemStack(ModItems.alchemicalDust, 8, 3));

        // Minium Stone
        aludelRegistry.addRecipe(new ItemStack(ModItems.stoneMinium), new ItemStack(ModItems.stoneInert), new ItemStack(ModItems.alchemicalDust, 8, 3));
    }

    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputDust) {
        addRecipe(new RecipeAludel(recipeOutput, recipeInputStack, recipeInputDust));
    }

    public void addRecipe(ItemStack recipeOutput, OreStack recipeInputStack, ItemStack recipeInputDust) {
        addRecipe(new RecipeAludel(recipeOutput, recipeInputStack, recipeInputDust));
    }

    public void addRecipe(RecipeAludel recipeAludel) {
        if (!aludelRecipes.contains(recipeAludel)) {
            aludelRecipes.add(recipeAludel);
        } else {
            LogHelper.debug(String.format("Attempted to add RecipeAludel '%s' but already exists in the recipe list", recipeAludel));
        }
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputDust) {
        for (RecipeAludel recipeAludel : aludelRecipes) {
            if (recipeAludel.matches(recipeInputStack, recipeInputDust)) {
                return recipeAludel.getRecipeOutput();
            }
        }

        return null;
    }

    public RecipeAludel getRecipe(ItemStack recipeInputStack, ItemStack recipeInputDust) {
        for (RecipeAludel recipeAludel : aludelRecipes) {
            if (recipeAludel.matches(recipeInputStack, recipeInputDust)) {
                return recipeAludel;
            }
        }

        return null;
    }

    public List<RecipeAludel> getRecipes() {
        return aludelRecipes;
    }

    public void debugDumpMap() {
        for (RecipeAludel recipeAludel : aludelRecipes) {
            LogHelper.debug(String.format("Output: %s, Input Stack: %s, Dust Stack: %s", recipeAludel.getRecipeOutput(), recipeAludel.getRecipeInputs()[0], recipeAludel.getRecipeInputs()[1]));
        }
    }
}

package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.core.util.OreStack;
import com.pahimar.ee3.core.util.RecipeHelper;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipeRegistry {

    private static RecipeRegistry recipeRegistry = null;

    private Multimap<CustomWrappedStack, List<CustomWrappedStack>> recipeMap;

    private List<CustomWrappedStack> wildCardList;

    private RecipeRegistry() {

        recipeMap = HashMultimap.create();
        wildCardList = RecipeHelper.populateWildCards();
    }

    public static RecipeRegistry getInstance() {

        if (recipeRegistry == null) {
            recipeRegistry = new RecipeRegistry();
        }

        return recipeRegistry;
    }

    public boolean hasRecipe(CustomWrappedStack customWrappedStack) {

        return recipeMap.containsKey(customWrappedStack);
    }

    public boolean hasRecipe(ItemStack itemStack) {

        return hasRecipe(new CustomWrappedStack(itemStack));
    }

    public int countRecipes(CustomWrappedStack customWrappedStack) {

        Collection<List<CustomWrappedStack>> keys = recipeMap.get(customWrappedStack);

        return keys.size();
    }

    public int countRecipes(ItemStack itemStack) {

        return countRecipes(new CustomWrappedStack(itemStack));
    }

    public Collection<List<CustomWrappedStack>> getRecipes(CustomWrappedStack customWrappedStack) {

        return recipeMap.get(customWrappedStack);
    }

    public Collection<List<CustomWrappedStack>> getRecipes(ItemStack itemStack) {

        return getRecipes(new CustomWrappedStack(itemStack));
    }

    /*
     * Item:
     *  Item (Output) <- { ... }
     * 
     */
    public void addRecipe(CustomWrappedStack recipeOutput, List<?> recipeInputs) {

        ArrayList<CustomWrappedStack> collatedStacks = new ArrayList<CustomWrappedStack>();

        CustomWrappedStack wrappedInputStack = null;

        LogHelper.debug("Recipe Output: " + recipeOutput.toString() + ", size: " + recipeOutput.getStackSize());
        LogHelper.debug("Recipe Inputs: " + recipeInputs.toString());
        
        /**
         * For every input in the input list, check to see if we have discovered
         * it already - If we have, add it to the one we already have - If we
         * have not, add it to the collection of discovered items
         */
        for (Object object : recipeInputs) {

            if (object instanceof ItemStack || object instanceof OreStack) {
                wrappedInputStack = new CustomWrappedStack(object);
            }
            else if (object instanceof CustomWrappedStack) {
                wrappedInputStack = (CustomWrappedStack) object;
            }
            
            for (CustomWrappedStack collatedStack : collatedStacks) {
                
            }
        }
        
        LogHelper.debug("Collated Recipe Inputs: " + collatedStacks.toString());
    }
    
    // TODO Temporary for testing, remove this later
    static {
        recipeRegistry = new RecipeRegistry();
        CustomWrappedStack recipeOutput = new CustomWrappedStack(new ItemStack(Item.stick));
        List<IRecipe> recipes = RecipeHelper.getReverseRecipes(recipeOutput);

        for (IRecipe recipe : recipes) {
            recipeRegistry.addRecipe(new CustomWrappedStack(recipe.getRecipeOutput()), RecipeHelper.getRecipeInputs(recipe));
        }
    }
}

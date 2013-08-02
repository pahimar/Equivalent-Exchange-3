package com.pahimar.ee3.core.util;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.pahimar.ee3.item.CustomWrappedStack;

/**
 * Equivalent-Exchange-3
 * 
 * RecipeHelper
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class RecipeHelper {

    /**
     * Discovers all instances of ItemStacks with wild card meta values in the
     * vanilla Crafting Manager
     * 
     * @return A list of CustomWrappedStacks that contains all wild card meta
     *         ItemStacks in the vanilla Crafting Manager
     */
    public static ArrayList<CustomWrappedStack> populateWildCards() {

        ArrayList<CustomWrappedStack> wildCards = new ArrayList<CustomWrappedStack>();

        for (Object recipe : CraftingManager.getInstance().getRecipeList()) {

            if (recipe instanceof IRecipe) {

                if (((IRecipe) recipe).getRecipeOutput() instanceof ItemStack) {

                    CustomWrappedStack recipeOutput = new CustomWrappedStack(((IRecipe) recipe).getRecipeOutput());
                    ArrayList<CustomWrappedStack> recipeInputs = RecipeHelper.getRecipeInputs((IRecipe) recipe);
                    ItemStack itemStack = null;

                    if (recipeOutput.getWrappedStack() instanceof ItemStack) {

                        itemStack = (ItemStack) recipeOutput.getWrappedStack();

                        if (itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE && !wildCards.contains(recipeOutput)) {
                            wildCards.add(recipeOutput);
                        }
                    }

                    for (CustomWrappedStack inputStack : recipeInputs) {

                        if (inputStack.getWrappedStack() instanceof ItemStack) {

                            itemStack = (ItemStack) inputStack.getWrappedStack();

                            if (itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE && !wildCards.contains(inputStack)) {
                                wildCards.add(inputStack);
                            }
                        }
                    }
                }
            }
        }

        return wildCards;
    }

    /**
     * Returns a list of elements that constitute the input in a crafting recipe
     * 
     * @param recipe
     *            The IRecipe being examined
     * @return List of elements that constitute the input of the given IRecipe.
     *         Could be an ItemStack or an Arraylist
     */
    public static ArrayList<CustomWrappedStack> getRecipeInputs(IRecipe recipe) {

        ArrayList<CustomWrappedStack> recipeInputs = new ArrayList<CustomWrappedStack>();

        if (recipe instanceof ShapedRecipes) {

            ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;

            for (int i = 0; i < shapedRecipe.recipeItems.length; i++) {
                
                if (shapedRecipe.recipeItems[i] instanceof ItemStack) {
                    
                    ItemStack itemStack = shapedRecipe.recipeItems[i].copy();
                    
                    if (itemStack.stackSize > 1) {
                        itemStack.stackSize = 1;
                    }
                    
                    recipeInputs.add(new CustomWrappedStack(itemStack));
                }
            }
        }
        else if (recipe instanceof ShapelessRecipes) {

            ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;

            for (Object object : shapelessRecipe.recipeItems) {
                
                if (object instanceof ItemStack) {
                    
                    ItemStack itemStack = ((ItemStack) object).copy();
                    
                    if (itemStack.stackSize > 1) {
                        itemStack.stackSize = 1;
                    }
                    
                    recipeInputs.add(new CustomWrappedStack(itemStack));
                }
            }
        }
        else if (recipe instanceof ShapedOreRecipe) {

            ShapedOreRecipe shapedOreRecipe = (ShapedOreRecipe) recipe;

            for (int i = 0; i < shapedOreRecipe.getInput().length; i++) {
            	
                /*
                 * If the element is a list, then it is an OreStack
                 */
                if (shapedOreRecipe.getInput()[i] instanceof ArrayList) {
                	CustomWrappedStack oreStack = new CustomWrappedStack(shapedOreRecipe.getInput()[i]);
                	
                	if (oreStack.getWrappedStack() instanceof OreStack) {
                		recipeInputs.add(new CustomWrappedStack(shapedOreRecipe.getInput()[i]));
                	}
                }
                else if (shapedOreRecipe.getInput()[i] instanceof ItemStack) {
                
                    ItemStack itemStack = ((ItemStack) shapedOreRecipe.getInput()[i]).copy();
                    
                    if (itemStack.stackSize > 1) {
                        itemStack.stackSize = 1;
                    }
                    
                    recipeInputs.add(new CustomWrappedStack(itemStack));
                }
            }
        }
        else if (recipe instanceof ShapelessOreRecipe) {

            ShapelessOreRecipe shapelessOreRecipe = (ShapelessOreRecipe) recipe;

            for (Object object : shapelessOreRecipe.getInput()) {
                
                if (object instanceof ArrayList) { 
                    recipeInputs.add(new CustomWrappedStack(object));
                }
                else if (object instanceof ItemStack) {
                    
                    ItemStack itemStack = ((ItemStack) object).copy();
                    
                    if (itemStack.stackSize > 1) {
                        itemStack.stackSize = 1;
                    }
                    
                    recipeInputs.add(new CustomWrappedStack(itemStack));
                }
            }
        }

        return recipeInputs;
    }
}

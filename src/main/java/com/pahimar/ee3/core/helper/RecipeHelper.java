package com.pahimar.ee3.core.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.pahimar.ee3.item.WrappedStack;
import com.pahimar.ee3.item.EnergyStack;
import com.pahimar.ee3.item.OreStack;

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
     * Returns a list of elements that constitute the input in a crafting recipe
     * 
     * @param recipe
     *            The IRecipe being examined
     * @return List of elements that constitute the input of the given IRecipe.
     *         Could be an ItemStack or an Arraylist
     */
    public static ArrayList<WrappedStack> getRecipeInputs(IRecipe recipe) {

        ArrayList<WrappedStack> recipeInputs = new ArrayList<WrappedStack>();

        if (recipe instanceof ShapedRecipes) {

            ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;

            for (int i = 0; i < shapedRecipe.recipeItems.length; i++) {

                if (shapedRecipe.recipeItems[i] instanceof ItemStack) {

                    ItemStack itemStack = shapedRecipe.recipeItems[i].copy();

                    if (itemStack.stackSize > 1) {
                        itemStack.stackSize = 1;
                    }

                    recipeInputs.add(new WrappedStack(itemStack));
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

                    recipeInputs.add(new WrappedStack(itemStack));
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
                    WrappedStack oreStack = new WrappedStack(shapedOreRecipe.getInput()[i]);

                    if (oreStack.getWrappedStack() instanceof OreStack) {
                        recipeInputs.add(new WrappedStack(shapedOreRecipe.getInput()[i]));
                    }
                }
                else if (shapedOreRecipe.getInput()[i] instanceof ItemStack) {

                    ItemStack itemStack = ((ItemStack) shapedOreRecipe.getInput()[i]).copy();

                    if (itemStack.stackSize > 1) {
                        itemStack.stackSize = 1;
                    }

                    recipeInputs.add(new WrappedStack(itemStack));
                }
            }
        }
        else if (recipe instanceof ShapelessOreRecipe) {

            ShapelessOreRecipe shapelessOreRecipe = (ShapelessOreRecipe) recipe;

            for (Object object : shapelessOreRecipe.getInput()) {

                if (object instanceof ArrayList) {
                    recipeInputs.add(new WrappedStack(object));
                }
                else if (object instanceof ItemStack) {

                    ItemStack itemStack = ((ItemStack) object).copy();

                    if (itemStack.stackSize > 1) {
                        itemStack.stackSize = 1;
                    }

                    recipeInputs.add(new WrappedStack(itemStack));
                }
            }
        }

        return recipeInputs;
    }

    /**
     * Collates an uncollated, unsorted List of Objects into a sorted, collated
     * List of WrappedStacks
     * 
     * @param uncollatedStacks
     *            List of objects for collating
     * @return A sorted, collated List of WrappedStacks
     */
    public static List<WrappedStack> collateInputStacks(List<?> uncollatedStacks) {

        List<WrappedStack> collatedStacks = new ArrayList<WrappedStack>();

        WrappedStack stack = null;
        boolean found = false;

        for (Object object : uncollatedStacks) {

            found = false;

            if (WrappedStack.canBeWrapped(object)) {

                stack = new WrappedStack(object);

                if (collatedStacks.isEmpty()) {
                    collatedStacks.add(stack);
                }
                else {

                    for (int i = 0; i < collatedStacks.size(); i++) {
                        if (collatedStacks.get(i).getWrappedStack() != null) {
                            if (stack.getWrappedStack() instanceof ItemStack && collatedStacks.get(i).getWrappedStack() instanceof ItemStack) {
                                if (ItemHelper.equals((ItemStack) stack.getWrappedStack(), (ItemStack) collatedStacks.get(i).getWrappedStack())) {
                                    collatedStacks.get(i).setStackSize(collatedStacks.get(i).getStackSize() + stack.getStackSize());
                                    found = true;
                                }
                            }
                            else if (stack.getWrappedStack() instanceof OreStack && collatedStacks.get(i).getWrappedStack() instanceof OreStack) {
                                if (OreStack.compareOreNames((OreStack) stack.getWrappedStack(), (OreStack) collatedStacks.get(i).getWrappedStack())) {
                                    collatedStacks.get(i).setStackSize(collatedStacks.get(i).getStackSize() + stack.getStackSize());
                                    found = true;
                                }

                            }
                            else if (stack.getWrappedStack() instanceof EnergyStack && collatedStacks.get(i).getWrappedStack() instanceof EnergyStack) {
                                if (EnergyStack.compareEnergyNames((EnergyStack) stack.getWrappedStack(), (EnergyStack) collatedStacks.get(i).getWrappedStack())) {
                                    collatedStacks.get(i).setStackSize(collatedStacks.get(i).getStackSize() + stack.getStackSize());
                                    found = true;
                                }
                            }
                        }
                    }

                    if (!found) {
                        collatedStacks.add(stack);
                    }
                }
            }

        }
        Collections.sort(collatedStacks);
        return collatedStacks;
    }
}

package com.pahimar.ee3.util;

import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.*;
import java.util.stream.Collectors;

public class RecipeHelper {

    /**
     * TODO Finish JavaDoc
     *
     * @param recipe
     * @return
     */
    public static Set<WrappedStack> getRecipeInputs(IRecipe recipe) {

        List<WrappedStack> recipeInputs = new ArrayList<>();

        if (recipe instanceof ShapedRecipes) {

            recipeInputs.addAll(Arrays.asList(((ShapedRecipes) recipe).recipeItems).stream()
                    .map(itemStack -> WrappedStack.wrap(itemStack, 1))
                    .collect(Collectors.toList()));
        }
        else if (recipe instanceof ShapelessRecipes) {

            recipeInputs.addAll(((ShapelessRecipes) recipe).recipeItems.stream()
                    .filter(itemStack -> itemStack != null)
                    .map(itemStack -> WrappedStack.wrap(itemStack, 1))
                    .collect(Collectors.toList()));
        }
        else if (recipe instanceof ShapedOreRecipe) {

            ShapedOreRecipe shapedOreRecipe = (ShapedOreRecipe) recipe;
            List<?> inputObjects = Arrays.asList(shapedOreRecipe.getInput());
            if (validateOreDictionaryRecipe(inputObjects)) {
                recipeInputs.addAll(inputObjects.stream()
                        .filter(recipeInput -> recipeInput instanceof ItemStack || recipeInput instanceof ArrayList)
                        .map(recipeInput -> WrappedStack.wrap(recipeInput, 1))
                        .collect(Collectors.toList()));
            }
        }
        else if (recipe instanceof ShapelessOreRecipe) {

            ShapelessOreRecipe shapelessOreRecipe = ((ShapelessOreRecipe) recipe);
            if (validateOreDictionaryRecipe(shapelessOreRecipe.getInput())) {
                recipeInputs.addAll(shapelessOreRecipe.getInput().stream()
                        .filter(recipeInput -> recipeInput instanceof Item || recipeInput instanceof ArrayList)
                        .map(recipeInput -> WrappedStack.wrap(recipeInput, 1))
                        .collect(Collectors.toList()));
            }
        }

        return collateStacks(recipeInputs);
    }

    /**
     * TODO Finish JavaDoc
     * 
     * @param uncollatedStacks
     * @return
     */
    private static Set<WrappedStack> collateStacks(Collection<?> uncollatedStacks) {

        List<WrappedStack> collatedStacks = new ArrayList<>();
        WrappedStack uncollatedStack;
        boolean found;

        for (Object object : uncollatedStacks) {

            found = false;
            uncollatedStack = WrappedStack.wrap(object);

            if (uncollatedStack != null) {
                if (collatedStacks.isEmpty()) {
                    collatedStacks.add(uncollatedStack);
                }
                else {
                    for (WrappedStack collatedStack : collatedStacks) {
                        if (uncollatedStack.getWrappedObject() instanceof ItemStack && collatedStack.getWrappedObject() instanceof ItemStack) {
                            if (ItemStackUtils.equals((ItemStack) uncollatedStack.getWrappedObject(), (ItemStack) collatedStack.getWrappedObject())) {
                                collatedStack.setStackSize(collatedStack.getStackSize() + uncollatedStack.getStackSize());
                                found = true;
                            }
                        }
                        else if (uncollatedStack.getWrappedObject() instanceof OreStack && collatedStack.getWrappedObject() instanceof OreStack) {
                            if (OreStack.compareOreNames((OreStack) uncollatedStack.getWrappedObject(), (OreStack) collatedStack.getWrappedObject())) {
                                collatedStack.setStackSize(collatedStack.getStackSize() + uncollatedStack.getStackSize());
                                found = true;
                            }
                        }
                    }

                    if (!found) {
                        collatedStacks.add(uncollatedStack);
                    }
                }
            }
        }

        return new TreeSet<>(collatedStacks);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param objects
     * @return
     */
    private static boolean validateOreDictionaryRecipe(Collection<?> objects) {

        for (Object object : objects) {
            if (object != null && !WrappedStack.canBeWrapped(object)) {
                return false;
            }
        }

        return true;
    }
}

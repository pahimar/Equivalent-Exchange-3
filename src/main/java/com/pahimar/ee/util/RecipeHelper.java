package com.pahimar.ee.util;

import com.pahimar.ee.exchange.OreStack;
import com.pahimar.ee.exchange.WrappedStack;
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

            recipeInputs.addAll(Arrays.asList(((ShapedRecipes) recipe).recipeItems)
                    .stream()
                    .filter(itemStack -> itemStack != null && !itemStack.isEmpty())
                    .map(itemStack -> WrappedStack.build(itemStack, 1))
                    .collect(Collectors.toList()));
        }
        else if (recipe instanceof ShapelessRecipes) {

            recipeInputs.addAll(((ShapelessRecipes) recipe).recipeItems.stream()
                    .filter(itemStack -> itemStack != null && !itemStack.isEmpty())
                    .map(itemStack -> WrappedStack.build(itemStack, 1))
                    .collect(Collectors.toList()));
        }
        else if (recipe instanceof ShapedOreRecipe) {

            ShapedOreRecipe shapedOreRecipe = (ShapedOreRecipe) recipe;

            if (validateOreDictionaryRecipe(Arrays.asList(shapedOreRecipe.getInput()))) {
                for (int i = 0; i < shapedOreRecipe.getInput().length; i++) {
                    Object recipeInput = shapedOreRecipe.getInput()[i];
                    if (recipeInput instanceof Collection) {
                        WrappedStack oreStack = WrappedStack.build(recipeInput, 1);
                        if (oreStack != null) {
                            recipeInputs.add(oreStack);
                        }
                    }
                    else if (recipeInput instanceof ItemStack) {
                        recipeInputs.add(WrappedStack.build(recipeInput, 1));
                    }
                }
            }
        }
        else if (recipe instanceof ShapelessOreRecipe) {

            ShapelessOreRecipe shapelessOreRecipe = ((ShapelessOreRecipe) recipe);
            if (validateOreDictionaryRecipe(shapelessOreRecipe.getInput())) {
                recipeInputs.addAll(shapelessOreRecipe.getInput().stream()
                        .filter(recipeInput -> recipeInput instanceof ItemStack || recipeInput instanceof Collection)
                        .map(recipeInput -> WrappedStack.build(recipeInput, 1))
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
            uncollatedStack = WrappedStack.build(object);

            if (uncollatedStack != null) {
                if (collatedStacks.isEmpty()) {
                    collatedStacks.add(uncollatedStack);
                }
                else {
                    for (WrappedStack collatedStack : collatedStacks) {
                        if (uncollatedStack.getObject() instanceof ItemStack && collatedStack.getObject() instanceof ItemStack) {
                            if (ItemStackUtils.equals((ItemStack) uncollatedStack.getObject(), (ItemStack) collatedStack.getObject())) {
                                collatedStack.setStackSize(collatedStack.getStackSize() + uncollatedStack.getStackSize());
                                found = true;
                            }
                        }
                        else if (uncollatedStack.getObject() instanceof OreStack && collatedStack.getObject() instanceof OreStack) {
                            if (OreStack.compareOreNames((OreStack) uncollatedStack.getObject(), (OreStack) collatedStack.getObject())) {
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

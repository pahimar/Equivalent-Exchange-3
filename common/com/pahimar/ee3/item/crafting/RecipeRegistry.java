package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.util.EnergyStack;
import com.pahimar.ee3.core.util.ItemUtil;
import com.pahimar.ee3.core.util.OreStack;
import com.pahimar.ee3.core.util.RecipeHelper;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipeRegistry {

    private static RecipeRegistry recipeRegistry = null;

    private Multimap<CustomWrappedStack, List<CustomWrappedStack>> recipeMap;
    private ArrayList<CustomWrappedStack> discoveredStacks;
    private ArrayList<CustomWrappedStack> recipelessStacks;
    private List<CustomWrappedStack> wildCardStacks;

    private RecipeRegistry() {

        recipeMap = HashMultimap.create();
        wildCardStacks = RecipeHelper.populateWildCards();
        discoveredStacks = new ArrayList<CustomWrappedStack>();
        recipelessStacks = new ArrayList<CustomWrappedStack>();
    }

    public static RecipeRegistry getInstance() {

        if (recipeRegistry == null) {
            recipeRegistry = new RecipeRegistry();
            recipeRegistry.init();
        }

        return recipeRegistry;
    }

    private void init() {

        Multimap<CustomWrappedStack, List<CustomWrappedStack>> recipes = HashMultimap.create();

        // Add potion recipes
        recipes.putAll(RecipesPotions.getPotionRecipes());

        // Add smelting recipes in the vanilla smelting manager
        recipes.putAll(RecipesSmelting.getSmeltingRecipes());

        // Add recipes in the vanilla crafting manager
        recipes.putAll(RecipesVanilla.getVanillaRecipes());

        // Add recipes gathered via IMC
        // TODO Gather IMC recipes

        // Populate the discovered stacks list with all stacks that we are involved in a recipe we are aware of 
        discoverStacks(recipes);

        // Add items that have no recipe, using the list of discovered stacks to determine if it's in a recipe or not
        for (CustomWrappedStack stack : recipelessStacks) {
            recipes.put(stack, new ArrayList<CustomWrappedStack>());
        }

        // Iterate through every recipe in the map, and add them to the registry
        Set<CustomWrappedStack> recipeKeySet = recipes.keySet();
        Iterator<CustomWrappedStack> recipeKeySetIterator = recipeKeySet.iterator();
        CustomWrappedStack recipeOutput = null;

        while (recipeKeySetIterator.hasNext()) {
            recipeOutput = recipeKeySetIterator.next();

            for (List<CustomWrappedStack> recipeInputs : recipes.get(recipeOutput)) {
                addRecipe(recipeOutput, recipeInputs);
            }
        }
    }

    private void discoverStacks(Multimap<CustomWrappedStack, List<CustomWrappedStack>> recipes) {

        Set<CustomWrappedStack> recipeKeySet = recipes.keySet();
        Iterator<CustomWrappedStack> recipeKeySetIterator = recipeKeySet.iterator();
        CustomWrappedStack recipeOutput = null;

        // Discover all stacks involved in the recipes we know about
        while (recipeKeySetIterator.hasNext()) {
            recipeOutput = recipeKeySetIterator.next();

            if (!discoveredStacks.contains(new CustomWrappedStack(recipeOutput.getWrappedStack())) && recipeOutput.getWrappedStack() != null) {
                discoveredStacks.add(new CustomWrappedStack(recipeOutput.getWrappedStack()));
            }

            for (List<CustomWrappedStack> recipeInputs : recipes.get(recipeOutput)) {
                for (CustomWrappedStack recipeInput : recipeInputs) {

                    CustomWrappedStack unwrappedRecipeInput = new CustomWrappedStack(recipeInput.getWrappedStack());

                    if (!discoveredStacks.contains(unwrappedRecipeInput) && recipeInput.getWrappedStack() != null) {
                        discoveredStacks.add(unwrappedRecipeInput);
                    }
                }
            }
        }

        CustomWrappedStack customWrappedStack;

        // Discover all stacks from the vanilla Items array
        for (int i = 0; i < Item.itemsList.length; i++) {

            if (Item.itemsList[i] != null) {

                if (Item.itemsList[i].getHasSubtypes()) {

                    for (int meta = 0; meta < 16; meta++) {

                        customWrappedStack = new CustomWrappedStack(new ItemStack(Item.itemsList[i].itemID, 1, meta));

                        if (!discoveredStacks.contains(customWrappedStack)) {
                            discoveredStacks.add(customWrappedStack);
                        }
                    }
                }
                else {

                    customWrappedStack = new CustomWrappedStack(new ItemStack(Item.itemsList[i]));

                    if (!discoveredStacks.contains(customWrappedStack)) {
                        discoveredStacks.add(customWrappedStack);
                    }
                }
            }
        }

        /*
         * For every stack we have discovered, check to see if we know a recipe
         * for it. If we don't and we haven't already added it to the recipeless
         * stack list, add it to the recipeless stack list
         */
        for (CustomWrappedStack discoveredStack : discoveredStacks) {

            if (recipes.get(discoveredStack).size() == 0 && !recipelessStacks.contains(discoveredStack)) {
                recipelessStacks.add(discoveredStack);
            }
        }
    }

    public boolean hasRecipe(CustomWrappedStack customWrappedStack) {

        return recipeMap.containsKey(customWrappedStack);
    }

    public boolean hasRecipe(ItemStack itemStack) {

        return hasRecipe(new CustomWrappedStack(itemStack));
    }

    public int countRecipesFor(CustomWrappedStack customWrappedStack) {

        Collection<List<CustomWrappedStack>> keys = recipeMap.get(customWrappedStack);

        return keys.size();
    }

    public int countRecipesFor(ItemStack itemStack) {

        return countRecipesFor(new CustomWrappedStack(itemStack));
    }

    public Collection<List<CustomWrappedStack>> getRecipesFor(CustomWrappedStack customWrappedStack) {

        return recipeMap.get(customWrappedStack);
    }

    public Collection<List<CustomWrappedStack>> getRecipesFor(ItemStack itemStack) {

        return getRecipesFor(new CustomWrappedStack(itemStack));
    }

    /*
     * Item: Item (Output) <- { ... }
     */
    public void addRecipe(CustomWrappedStack recipeOutput, List<?> recipeInputs) {

        ArrayList<CustomWrappedStack> collatedStacks = new ArrayList<CustomWrappedStack>();

        CustomWrappedStack wrappedInputStack = null;
        boolean found = false;

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

            if (wildCardStacks.contains(wrappedInputStack)) {
                Iterator<CustomWrappedStack> wildIter = wildCardStacks.iterator();
                while (wildIter.hasNext()) {
                    CustomWrappedStack wildCard = wildIter.next();
                    if (wildCard.equals(wrappedInputStack)) {
                        wrappedInputStack = wildCard;
                        break;
                    }
                }
            }

            if (collatedStacks.size() == 0) {
                collatedStacks.add(wrappedInputStack);
            }
            else {
                found = false;

                for (int i = 0; i < collatedStacks.size(); i++) {
                    if (collatedStacks.get(i) != null) {
                        if (wrappedInputStack.getWrappedStack() instanceof ItemStack && collatedStacks.get(i).getWrappedStack() instanceof ItemStack) {
                            if (ItemUtil.compare((ItemStack) wrappedInputStack.getWrappedStack(), (ItemStack) collatedStacks.get(i).getWrappedStack())) {
                                collatedStacks.get(i).setStackSize(collatedStacks.get(i).getStackSize() + wrappedInputStack.getStackSize());
                                found = true;
                            }
                        }
                        else if (wrappedInputStack.getWrappedStack() instanceof OreStack && collatedStacks.get(i).getWrappedStack() instanceof OreStack) {
                            if (OreStack.compareOreNames((OreStack) wrappedInputStack.getWrappedStack(), (OreStack) collatedStacks.get(i).getWrappedStack())) {
                                collatedStacks.get(i).setStackSize(collatedStacks.get(i).getStackSize() + wrappedInputStack.getStackSize());
                                found = true;
                            }
                        }
                        else if (wrappedInputStack.getWrappedStack() instanceof EnergyStack && collatedStacks.get(i).getWrappedStack() instanceof EnergyStack) {
                            if (((EnergyStack) wrappedInputStack.getWrappedStack()).energyName.equalsIgnoreCase(((EnergyStack) collatedStacks.get(i).getWrappedStack()).energyName)) {
                                collatedStacks.get(i).setStackSize(collatedStacks.get(i).getStackSize() + wrappedInputStack.getStackSize());
                                found = true;
                            }
                        }
                    }
                }

                if (!found) {
                    collatedStacks.add(wrappedInputStack);
                }
            }
        }

        if (!recipeMap.containsEntry(recipeOutput, collatedStacks)) {
            recipeMap.put(recipeOutput, collatedStacks);
        }
    }

    public int size() {

        return recipeMap.size();
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (CustomWrappedStack key : recipeMap.keySet()) {

            Collection<List<CustomWrappedStack>> recipeMappings = recipeMap.get(key);

            for (List<CustomWrappedStack> recipeList : recipeMappings) {
                stringBuilder.append(String.format("Recipe Output: %s, Recipe Input: %s\n", key.toString(), recipeList.toString()));
            }
        }

        return stringBuilder.toString();
    }

    public Multimap<CustomWrappedStack, List<CustomWrappedStack>> getRecipeMappings() {

        return recipeMap;
    }

    public List<CustomWrappedStack> getDiscoveredStacks() {

        return discoveredStacks;
    }

    public List<CustomWrappedStack> getRecipelessStacks() {

        return recipelessStacks;
    }

    public List<CustomWrappedStack> getWildCardStacks() {

        return wildCardStacks;
    }
}

package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.util.ItemUtil;
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
        boolean found = false;

        LogHelper.debug("Recipe Output: " + recipeOutput.toString());
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
            
            if (wildCardList.contains(wrappedInputStack)) {
                Iterator<CustomWrappedStack> wildIter = wildCardList.iterator();
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
                            if (OreStack.compareStacks((OreStack) wrappedInputStack.getWrappedStack(), (OreStack) collatedStacks.get(i).getWrappedStack())) {
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
        
        // TODO Once we have a collated set of inputs for the given output, check to see if we have it registered already and if not add it to the recipeMap
        for (CustomWrappedStack collatedStack : collatedStacks) {
            LogHelper.debug("Collated Recipe Input: " + collatedStack);
        }
    }
    
    // TODO Temporary for testing, remove this later
    static {
        recipeRegistry = new RecipeRegistry();
        
        Multimap<ItemStack, List<ItemStack>> potionRecipes = RecipesPotions.getPotionRecipes();
        
        Set<ItemStack> keySet = potionRecipes.keySet();
        Iterator<ItemStack> keySetIter = keySet.iterator();
        ItemStack key = null;
        while (keySetIter.hasNext()) {
            key = keySetIter.next();
            
            for (List<ItemStack> potionInputs : potionRecipes.get(key)) {
                recipeRegistry.addRecipe(new CustomWrappedStack(key), potionInputs);
            }
        }
    }
}

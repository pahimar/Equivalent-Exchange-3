package com.pahimar.ee3.core.util;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.pahimar.ee3.item.CustomWrappedStack;

import cpw.mods.fml.common.registry.GameRegistry;

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
     * Discovers all instances of ItemStacks with wild card meta values in the vanilla Crafting Manager
     * 
     * @return A list of CustomWrappedStacks that contains all wild card meta ItemStacks in the vanilla Crafting Manager
     */
    public static ArrayList<CustomWrappedStack> discoverWildCards() {
        
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
    @SuppressWarnings({ "rawtypes" })
    public static ArrayList<CustomWrappedStack> getRecipeInputs(IRecipe recipe) {

        ArrayList<CustomWrappedStack> recipeInputs = new ArrayList<CustomWrappedStack>();
        ItemStack itemStack = null;
        OreStack oreStack = null;

        if (recipe instanceof ShapedRecipes) {

            ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;

            for (int i = 0; i < shapedRecipe.recipeItems.length; i++) {
                if (shapedRecipe.recipeItems[i] instanceof ItemStack) {

                    itemStack = shapedRecipe.recipeItems[i];
                    itemStack.stackSize = 1;

                    recipeInputs.add(new CustomWrappedStack(itemStack));
                }
            }
        }
        else if (recipe instanceof ShapelessRecipes) {

            ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;

            for (Object object : shapelessRecipe.recipeItems) {
                if (object instanceof ItemStack) {

                    itemStack = (ItemStack) object;
                    itemStack.stackSize = 1;

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
                    ArrayList shapedOreRecipeList = (ArrayList<?>) shapedOreRecipe.getInput()[i];

                    if (!shapedOreRecipeList.isEmpty()) {

                        oreStack = new OreStack((ItemStack) shapedOreRecipeList.get(0));
                        oreStack.stackSize = 1;

                        recipeInputs.add(new CustomWrappedStack(oreStack));
                    }
                }
                /*
                 * Else it is possibly an ItemStack
                 */
                else if (shapedOreRecipe.getInput()[i] instanceof ItemStack) {

                    itemStack = (ItemStack) shapedOreRecipe.getInput()[i];
                    itemStack.stackSize = 1;

                    recipeInputs.add(new CustomWrappedStack(itemStack));
                }
            }
        }
        else if (recipe instanceof ShapelessOreRecipe) {

            ShapelessOreRecipe shapelessOreRecipe = (ShapelessOreRecipe) recipe;

            for (Object object : shapelessOreRecipe.getInput()) {
                if (object instanceof ArrayList) {
                    ArrayList shapelessOreRecipeList = (ArrayList<?>) object;

                    if (!shapelessOreRecipeList.isEmpty()) {

                        oreStack = new OreStack((ItemStack) shapelessOreRecipeList.get(0));
                        oreStack.stackSize = 1;

                        recipeInputs.add(new CustomWrappedStack(oreStack));
                    }
                }
                else if (object instanceof ItemStack) {
                    itemStack = (ItemStack) object;
                    itemStack.stackSize = 1;

                    recipeInputs.add(new CustomWrappedStack(itemStack));
                }
            }
        }

        return recipeInputs;
    }
    
    public static ArrayList<CustomWrappedStack> getCollatedRecipeInputs(IRecipe recipe) {
        
        return ItemUtil.collateStacks(getRecipeInputs(recipe));
    }
    
    public static ArrayList<IRecipe> getReverseRecipes(CustomWrappedStack customWrappedStack) {
        
        if (customWrappedStack.getWrappedStack() instanceof ItemStack) {
            return getReverseRecipes((ItemStack) customWrappedStack.getWrappedStack());
        }

        return new ArrayList<IRecipe>();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<IRecipe> getReverseRecipes(ItemStack itemStack) {
        
        ArrayList<IRecipe> reverseRecipeList = new ArrayList<IRecipe>();
        
        if (itemStack != null) {
            ArrayList<IRecipe> craftingManagerRecipeList = new ArrayList<IRecipe>(CraftingManager.getInstance().getRecipeList());    
    
            for (IRecipe recipe : craftingManagerRecipeList) {
                if (recipe.getRecipeOutput() != null) {
                    if (ItemUtil.compare(itemStack, recipe.getRecipeOutput())) {
                        reverseRecipeList.add(recipe);
                    }
                }
            }
        }
        
        return reverseRecipeList;
    }

    public static void addRecipe(ItemStack output, Object... input) {

        GameRegistry.addShapelessRecipe(output, input);
    }

    public static void addRecipe(ItemStack output, ItemStack transmutationStone, Object... input) {

        Object[] inputs = new Object[input.length + 1];
        System.arraycopy(input, 0, inputs, 0, input.length);
        inputs[input.length] = transmutationStone;

        addRecipe(output, inputs);
    }

    public static void addRecipe(Block output, Object... input) {

        addRecipe(new ItemStack(output), input);
    }

    public static void addRecipe(Block output, int count, Object... input) {

        addRecipe(new ItemStack(output, count), input);
    }

    public static void addRecipe(Item output, Object... input) {

        addRecipe(new ItemStack(output), input);
    }

    public static void addRecipe(Item output, int count, Object... input) {

        addRecipe(new ItemStack(output, count), input);
    }

    public static Object[] getMetaCycle(Object input, int n) {

        ArrayList<ItemStack> list = new ArrayList<ItemStack>();

        ItemStack stack;

        for (int i = 0; i < n; i++) {
            stack = GeneralHelper.convertObjectToItemStack(input);
            stack.setItemDamage(i);
            list.add(stack);
        }

        return list.toArray();
    }

    public static Object[] getMetaCycle(Object input, int n, int... excludedMeta) {

        ArrayList<ItemStack> list = new ArrayList<ItemStack>();

        ItemStack stack;
        int i = 0;
        while (i < n) {
            for (int j : excludedMeta) {
                if (i == j) {
                    ++i;
                }
            }

            if (!(i < n)) {
                break;
            }

            stack = GeneralHelper.convertObjectToItemStack(input);
            stack.setItemDamage(i);
            list.add(stack);
            ++i;
        }

        return list.toArray();
    }

    /*
     * Pass this a Block, Item or ItemStack and the maximum number of indexes,
     * EXCLUDING zero
     */
    protected static void addMetaCycleRecipe(Object input, ItemStack stone, int n) {

        int outputI;

        /*
         * Makes a single item cycle through its meta values when it's crafted
         * with a PStone
         */
        for (int i = 0; i < n; i++) {
            outputI = i == n - 1 ? 0 : i + 1;

            if (input instanceof Block) {
                GameRegistry.addShapelessRecipe(new ItemStack((Block) input, 1, outputI), stone, new ItemStack((Block) input, 1, i));
            }
            else if (input instanceof Item) {
                GameRegistry.addShapelessRecipe(new ItemStack((Item) input, 1, outputI), stone, new ItemStack((Item) input, 1, i));
            }
            else if (input instanceof ItemStack) {
                GameRegistry.addShapelessRecipe(new ItemStack(((ItemStack) input).itemID, 1, outputI), stone, new ItemStack(((ItemStack) input).itemID, 1, i));
            }
        }
    }

    protected static void addMetaCycleRecipe(Object input, ItemStack stone, int n, int... excludedMeta) {

        int i = 0;
        int outputI = 1;
        while (i < n && outputI != 0) {
            outputI = i == n - 1 ? 0 : i + 1;

            for (int j : excludedMeta) {
                if (outputI == j) {
                    outputI = (outputI + 1) % 16;
                }
            }

            if (input instanceof Block) {
                GameRegistry.addShapelessRecipe(new ItemStack((Block) input, 1, outputI), stone, new ItemStack((Block) input, 1, i));
            }
            else if (input instanceof Item) {
                GameRegistry.addShapelessRecipe(new ItemStack((Item) input, 1, outputI), stone, new ItemStack((Item) input, 1, i));
            }
            else if (input instanceof ItemStack) {
                GameRegistry.addShapelessRecipe(new ItemStack(((ItemStack) input).itemID, 1, outputI), stone, new ItemStack(((ItemStack) input).itemID, 1, i));
            }

            i = outputI;
        }
    }

    public static void addSmeltingRecipe(ItemStack input, ItemStack stone, ItemStack fuel) {

        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(input);

        if (input == null || input.getItem() == null || result == null)
            return;

        Object[] list = new Object[9];
        list[0] = stone;
        list[1] = fuel;

        for (int i = 2; i < 9; i++) {
            list[i] = new ItemStack(input.getItem(), 1, input.getItemDamage());
        }

        if (result.stackSize * 7 <= result.getItem().getItemStackLimit()) {
            GameRegistry.addShapelessRecipe(new ItemStack(result.getItem(), result.stackSize * 7, result.getItemDamage()), list);
        }
        else {
            GameRegistry.addShapelessRecipe(new ItemStack(result.getItem(), result.getItem().getItemStackLimit(), result.getItemDamage()), list);
        }
    }

}

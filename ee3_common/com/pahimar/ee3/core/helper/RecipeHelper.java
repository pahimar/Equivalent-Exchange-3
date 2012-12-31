package com.pahimar.ee3.core.helper;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * RecipeHelper
 * 
 * Helper methods for adding recipes
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class RecipeHelper {

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
                if (i == j)
                    ++i;
            }

            if (!(i < n))
                break;

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
            outputI = (i == n - 1 ? 0 : i + 1);

            if (input instanceof Block)
                GameRegistry.addShapelessRecipe(new ItemStack((Block) input, 1, outputI), stone, new ItemStack((Block) input, 1, i));
            else if (input instanceof Item)
                GameRegistry.addShapelessRecipe(new ItemStack((Item) input, 1, outputI), stone, new ItemStack((Item) input, 1, i));
            else if (input instanceof ItemStack)
                GameRegistry.addShapelessRecipe(new ItemStack(((ItemStack) input).itemID, 1, outputI), stone, new ItemStack(((ItemStack) input).itemID, 1, i));
        }
    }

    protected static void addMetaCycleRecipe(Object input, ItemStack stone, int n, int... excludedMeta) {

        int i = 0;
        int outputI = 1;
        while (i < n && outputI != 0) {
            outputI = (i == n - 1 ? 0 : i + 1);

            for (int j : excludedMeta) {
                if (outputI == j)
                    outputI = (outputI + 1) % 16;
            }

            if (input instanceof Block)
                GameRegistry.addShapelessRecipe(new ItemStack((Block) input, 1, outputI), stone, new ItemStack((Block) input, 1, i));
            else if (input instanceof Item)
                GameRegistry.addShapelessRecipe(new ItemStack((Item) input, 1, outputI), stone, new ItemStack((Item) input, 1, i));
            else if (input instanceof ItemStack)
                GameRegistry.addShapelessRecipe(new ItemStack(((ItemStack) input).itemID, 1, outputI), stone, new ItemStack(((ItemStack) input).itemID, 1, i));

            i = outputI;
        }
    }

    public static void addSmeltingRecipe(ItemStack input, ItemStack stone, ItemStack fuel) {

        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(input);

        if ((input == null) || (input.getItem() == null) || (result == null))
            return;

        Object[] list = new Object[9];
        list[0] = stone;
        list[1] = fuel;

        for (int i = 2; i < 9; i++) {
            list[i] = new ItemStack(input.getItem(), 1, input.getItemDamage());
        }

        if (result.stackSize * 7 <= result.getItem().getItemStackLimit()) {
            GameRegistry.addShapelessRecipe(new ItemStack(result.getItem(), (result.stackSize * 7), result.getItemDamage()), list);
        }
        else {
            GameRegistry.addShapelessRecipe(new ItemStack(result.getItem(), result.getItem().getItemStackLimit(), result.getItemDamage()), list);
        }
    }

}

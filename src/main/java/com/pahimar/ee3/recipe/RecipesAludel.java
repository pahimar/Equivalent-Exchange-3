package com.pahimar.ee3.recipe;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.helper.LogHelper;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.item.crafting.RecipeAludel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class RecipesAludel
{
    private static RecipesAludel aludelRegistry = null;

    private List<RecipeAludel> aludelRecipes;

    private RecipesAludel()
    {
        aludelRecipes = new ArrayList<RecipeAludel>();
    }

    public static RecipesAludel getInstance()
    {
        if (aludelRegistry == null)
        {
            aludelRegistry = new RecipesAludel();
            aludelRegistry.init();
        }

        return aludelRegistry;
    }

    private void init()
    {
        // Alchemical Coal
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 0), new ItemStack(Item.coal, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 32, 1));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 0), new ItemStack(Item.coal, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 1, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 4, 0), new ItemStack(Item.coal, 4, 0), new ItemStack(ModItems.alchemicalDust.itemID, 1, 3));

        // Mobius Fuel
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 1), new ItemStack(ModItems.alchemicalFuel.itemID, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 7, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 1), new ItemStack(ModItems.alchemicalFuel.itemID, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 2, 3));

        // Aeternalis Fuel
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 2), new ItemStack(ModItems.alchemicalFuel.itemID, 1, 1), new ItemStack(ModItems.alchemicalDust.itemID, 56, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 2), new ItemStack(ModItems.alchemicalFuel.itemID, 1, 1), new ItemStack(ModItems.alchemicalDust.itemID, 14, 3));

        // Infused Cloth
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedCloth.blockID, 1, 0), new ItemStack(Block.cloth.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 1, 0));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedCloth.blockID, 1, 1), new ItemStack(Block.cloth.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 1, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedCloth.blockID, 1, 2), new ItemStack(Block.cloth.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 1, 2));

        // Infused Wood
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedWood.blockID, 1, 0), new ItemStack(Block.wood.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 4, 0));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedWood.blockID, 1, 1), new ItemStack(Block.wood.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 4, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedWood.blockID, 1, 2), new ItemStack(Block.wood.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 4, 2));

        // Infused Planks
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedPlanks.blockID, 1, 0), new ItemStack(Block.planks.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 1, 0));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedPlanks.blockID, 1, 1), new ItemStack(Block.planks.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 1, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedPlanks.blockID, 1, 2), new ItemStack(Block.planks.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 1, 2));
    }


    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        // TODO Finish
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        // TODO Finish
        return null;
    }

    public AludelRecipeInputPair getRecipeInputs(ItemStack itemStack)
    {
        // TODO Finish
        return null;
    }

    public List<RecipeAludel> getRecipeMap()
    {
        return aludelRecipes;
    }

    public void debugDumpMap()
    {
        for (RecipeAludel recipeAludel : aludelRecipes)
        {
            LogHelper.debug(String.format("Output: %s, Input Stacks: %s", recipeAludel.getRecipeOutput(), recipeAludel.getRecipeInputs()));
        }
    }
}

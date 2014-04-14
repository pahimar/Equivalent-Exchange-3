package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.helper.LogHelper;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.item.crafting.RecipeAludel;

import cpw.mods.ironchest.IronChest;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
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
        // Ash + Verdant = Azure
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalDust.itemID, 1, 2), new ItemStack(ModItems.alchemicalDust.itemID, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 32, 1));

        // Ash + Azure = Minium
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalDust.itemID, 1, 3), new ItemStack(ModItems.alchemicalDust.itemID, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 4, 2));

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
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedCloth.blockID, 1, 0), new ItemStack(Block.cloth.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 1, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedCloth.blockID, 1, 1), new ItemStack(Block.cloth.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 1, 2));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedCloth.blockID, 1, 2), new ItemStack(Block.cloth.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 1, 3));

        // Infused Wood
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedWood.blockID, 1, 0), new OreStack("logWood"), new ItemStack(ModItems.alchemicalDust.itemID, 4, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedWood.blockID, 1, 1), new OreStack("logWood"), new ItemStack(ModItems.alchemicalDust.itemID, 4, 2));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedWood.blockID, 1, 2), new OreStack("logWood"), new ItemStack(ModItems.alchemicalDust.itemID, 4, 3));

        // Infused Planks
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedPlanks.blockID, 1, 0), new OreStack("plankWood"), new ItemStack(ModItems.alchemicalDust.itemID, 1, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedPlanks.blockID, 1, 1), new OreStack("plankWood"), new ItemStack(ModItems.alchemicalDust.itemID, 1, 2));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.infusedPlanks.blockID, 1, 2), new OreStack("plankWood"), new ItemStack(ModItems.alchemicalDust.itemID, 1, 3));

        // Alchemical Chest
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 0), new ItemStack(Block.chest.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 8, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 0), new ItemStack(Block.chestTrapped.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 8, 1));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 1), new ItemStack(Block.chest.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 8, 2));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 1), new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 8, 2));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 2), new ItemStack(Block.chest.blockID, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.alchemicalDust.itemID, 8, 3));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 2), new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 8, 3));
        aludelRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 2), new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 1), new ItemStack(ModItems.alchemicalDust.itemID, 8, 3));

        // Minium Stone
        aludelRegistry.addRecipe(new ItemStack(ModItems.miniumStone), new ItemStack(ModItems.inertStone), new ItemStack(ModItems.alchemicalDust.itemID, 8, 3));
    }

    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        addRecipe(new RecipeAludel(recipeOutput, recipeInputStack, recipeInputDust));
    }

    public void addRecipe(ItemStack recipeOutput, OreStack recipeInputStack, ItemStack recipeInputDust)
    {
        addRecipe(new RecipeAludel(recipeOutput, recipeInputStack, recipeInputDust));
    }

    public void addRecipe(RecipeAludel recipeAludel)
    {
        if (!aludelRecipes.contains(recipeAludel))
        {
            aludelRecipes.add(recipeAludel);
        }
        else
        {
            LogHelper.debug(String.format("Attempted to add RecipeAludel '%s' but already exists in the recipe list", recipeAludel));
        }
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        for (RecipeAludel recipeAludel : aludelRecipes)
        {
            if (recipeAludel.matches(recipeInputStack, recipeInputDust))
            {
                return recipeAludel.getRecipeOutput();
            }
        }

        return null;
    }

    public RecipeAludel getRecipe(ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        for (RecipeAludel recipeAludel : aludelRecipes)
        {
            if (recipeAludel.matches(recipeInputStack, recipeInputDust))
            {
                return recipeAludel;
            }
        }

        return null;
    }

    public List<RecipeAludel> getRecipes()
    {
        return aludelRecipes;
    }

    public void debugDumpMap()
    {
        for (RecipeAludel recipeAludel : aludelRecipes)
        {
            LogHelper.debug(String.format("Output: %s, Input Stack: %s, Dust Stack: %s", recipeAludel.getRecipeOutput(), recipeAludel.getRecipeInputs()[0], recipeAludel.getRecipeInputs()[1]));
        }
    }
    public static boolean retroaddRecipe(RecipeAludel recipe){
    	if(aludelRegistry == null){
    		LogHelper.severe("You cannot retroactively add aludel recipies before the aludel recipe registry is initiated!");
    		return false;
    	} else{
    	aludelRegistry.addRecipe(recipe);
    	return true;
    	}
    }
}

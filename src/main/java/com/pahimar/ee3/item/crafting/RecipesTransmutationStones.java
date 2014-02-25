package com.pahimar.ee3.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.helper.CraftingHelper;
import com.pahimar.ee3.item.ModItems;

public class RecipesTransmutationStones
{
    private static final String TRANSMUTATION_STONE = "transmutationStone";

    public static void init()
    {
        registerTransmutationStones();
        registerTransmutationRecipes();
    }

    private static void registerTransmutationStones()
    {
        OreDictionary.registerOre(TRANSMUTATION_STONE, new ItemStack(ModItems.miniumStone, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre(TRANSMUTATION_STONE, new ItemStack(ModItems.philStone, 1, OreDictionary.WILDCARD_VALUE));
    }

    private static void registerTransmutationRecipes()
    {
        /* 4 Cobble <-> 1 Flint */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.flint), TRANSMUTATION_STONE, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.cobblestone, 4), TRANSMUTATION_STONE, Items.flint);

        /* 4 Dirt <-> 1 Gravel */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.gravel), TRANSMUTATION_STONE, Blocks.dirt, Blocks.dirt, Blocks.dirt, Blocks.dirt);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.dirt, 4), TRANSMUTATION_STONE, Blocks.gravel);

        /* 4 Sand <-> 1 Sandstone */
        // Vanilla Recipes exist to make SandStone from 4 Sand
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.sand, 4), TRANSMUTATION_STONE, new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE));

        /* 2 Sticks -> Wood Plank */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.planks), TRANSMUTATION_STONE, "stickWood", "stickWood");
        // Vanilla recipe exists to make sticks from planks

        /* 4 Wood Planks -> Wood Block */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.log), TRANSMUTATION_STONE, "plankWood", "plankWood", "plankWood", "plankWood");
        // Vanilla recipes exist to make planks from any wood log

        /* 4 Gravel/Sandstone/Flint -> 1 Clay Ball, 1 Clay Ball -> 4 Gravel */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.clay_ball), TRANSMUTATION_STONE, Blocks.gravel, Blocks.gravel, Blocks.gravel, Blocks.gravel);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.clay_ball), TRANSMUTATION_STONE, new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE));
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.clay_ball), TRANSMUTATION_STONE, Items.flint, Items.flint, Items.flint, Items.flint);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.gravel, 4), TRANSMUTATION_STONE, Items.clay_ball);

        /* 2 Wood Log <-> 1 Obsidian */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.obsidian), TRANSMUTATION_STONE, "logWood", "logWood");
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.log, 2), TRANSMUTATION_STONE, Blocks.obsidian);

        /* 4 Clay Ball <-> 1 Clay Block */
        // Vanilla recipe exists to make clay blocks from clay balls
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.clay_ball, 4), TRANSMUTATION_STONE, Blocks.clay);

        /* 4 Obsidian/Clay Block -> 1 Iron Ingot, Iron Ingot -> Clay Block */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.iron_ingot), TRANSMUTATION_STONE, Blocks.obsidian, Blocks.obsidian, Blocks.obsidian, Blocks.obsidian);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.iron_ingot), TRANSMUTATION_STONE, Blocks.clay, Blocks.clay, Blocks.clay, Blocks.clay);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.clay, 4), TRANSMUTATION_STONE, Items.iron_ingot);

        /* 8 Iron Ingot <-> 1 Gold Ingot */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.gold_ingot), TRANSMUTATION_STONE, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.iron_ingot, 8), TRANSMUTATION_STONE, Items.gold_ingot);

        /* 4 Gold Ingot <-> 1 Diamond */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.diamond), TRANSMUTATION_STONE, Items.gold_ingot, Items.gold_ingot, Items.gold_ingot, Items.gold_ingot);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.gold_ingot, 4), TRANSMUTATION_STONE, Items.diamond);

        /* 8 Iron Block <-> 1 Gold Block */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.gold_block), TRANSMUTATION_STONE, Blocks.iron_block, Blocks.iron_block, Blocks.iron_block, Blocks.iron_block, Blocks.iron_block, Blocks.iron_block, Blocks.iron_block, Blocks.iron_block);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.iron_block, 8), TRANSMUTATION_STONE, Blocks.gold_block);

        /* 4 Gold Block <-> 1 Diamond Block */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.diamond_block), TRANSMUTATION_STONE, Blocks.gold_block, Blocks.gold_block, Blocks.gold_block, Blocks.gold_block);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Blocks.gold_block, 4), TRANSMUTATION_STONE, Blocks.diamond_block);

        /* 1 Ender Pearl <-> 4 Iron Ingot */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.ender_pearl), TRANSMUTATION_STONE, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Items.iron_ingot, 4), TRANSMUTATION_STONE, Items.ender_pearl);
    }
}

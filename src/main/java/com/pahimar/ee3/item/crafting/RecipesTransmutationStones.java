package com.pahimar.ee3.item.crafting;

import com.pahimar.ee3.helper.CraftingHelper;
import com.pahimar.ee3.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipesTransmutationStones
{
    private static final ItemStack MINIUM_STONE = new ItemStack(ModItems.miniumStone.itemID, 1, OreDictionary.WILDCARD_VALUE);
    private static final ItemStack PHILOSOPHERS_STONE = new ItemStack(ModItems.philStone.itemID, 1, OreDictionary.WILDCARD_VALUE);

    public static void init()
    {
        registerTransmutationRecipes(MINIUM_STONE);
        registerTransmutationRecipes(PHILOSOPHERS_STONE);
    }

    private static void registerTransmutationRecipes(ItemStack transmutationStone)
    {
        /* 4 Cobble <-> 1 Flint */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.flint), transmutationStone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.cobblestone, 4), transmutationStone, Item.flint);

        /* 4 Dirt <-> 1 Gravel */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.gravel), transmutationStone, Block.dirt, Block.dirt, Block.dirt, Block.dirt);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.dirt, 4), transmutationStone, Block.gravel);

        /* 4 Sand <-> 1 Sandstone */
        // Vanilla Recipes exist to make SandStone from 4 Sand
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.sand, 4), transmutationStone, new ItemStack(Block.sandStone, 1, OreDictionary.WILDCARD_VALUE));

        /* 2 Sticks -> Wood Plank */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.planks), transmutationStone, "stickWood", "stickWood");
        // Vanilla recipe exists to make sticks from planks

        /* 4 Wood Planks -> Wood Block */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.wood), transmutationStone, "plankWood", "plankWood", "plankWood", "plankWood");
        // Vanilla recipes exist to make planks from any wood log

        /* 4 Gravel/Sandstone/Flint -> 1 Clay Ball, 1 Clay Ball -> 4 Gravel */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.clay), transmutationStone, Block.gravel, Block.gravel, Block.gravel, Block.gravel);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.clay), transmutationStone, new ItemStack(Block.sandStone, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Block.sandStone, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Block.sandStone, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Block.sandStone, 1, OreDictionary.WILDCARD_VALUE));
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.clay), transmutationStone, Item.flint, Item.flint, Item.flint, Item.flint);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.gravel, 4), transmutationStone, Item.clay);

        /* 2 Wood Log <-> 1 Obsidian */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.obsidian), transmutationStone, "logWood", "logWood");
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.wood, 2), transmutationStone, Block.obsidian);

        /* 4 Clay Ball <-> 1 Clay Block */
        // Vanilla recipe exists to make clay blocks from clay balls
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.clay, 4), transmutationStone, Block.blockClay);

        /* 4 Obsidian/Clay Block -> 1 Iron Ingot, Iron Ingot -> Clay Block */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.ingotIron), transmutationStone, Block.obsidian, Block.obsidian, Block.obsidian, Block.obsidian);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.ingotIron), transmutationStone, Block.blockClay, Block.blockClay, Block.blockClay, Block.blockClay);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.blockClay, 4), transmutationStone, Item.ingotIron);

        /* 8 Iron Ingot <-> 1 Gold Ingot */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.ingotGold), transmutationStone, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.ingotIron, 8), transmutationStone, Item.ingotGold);

        /* 4 Gold Ingot <-> 1 Diamond */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.diamond), transmutationStone, Item.ingotGold, Item.ingotGold, Item.ingotGold, Item.ingotGold);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.ingotGold, 4), transmutationStone, Item.diamond);

        /* 8 Iron Block <-> 1 Gold Block */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.blockGold), transmutationStone, Block.blockIron, Block.blockIron, Block.blockIron, Block.blockIron, Block.blockIron, Block.blockIron, Block.blockIron, Block.blockIron);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.blockIron, 8), transmutationStone, Block.blockGold);

        /* 4 Gold Block <-> 1 Diamond Block */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.blockDiamond), transmutationStone, Block.blockGold, Block.blockGold, Block.blockGold, Block.blockGold);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Block.blockGold, 4), transmutationStone, Block.blockDiamond);

        /* 1 Ender Pearl <-> 4 Iron Ingot */
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.enderPearl), transmutationStone, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron);
        CraftingHelper.addShapelessOreRecipe(new ItemStack(Item.ingotIron, 4), transmutationStone, Item.enderPearl);
    }
}

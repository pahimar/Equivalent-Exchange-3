package com.pahimar.ee3.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import com.pahimar.ee3.core.handlers.EquivalencyHandler;
import com.pahimar.ee3.core.helper.GeneralHelper;
import com.pahimar.ee3.core.helper.RecipeHelper;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

/**
 * Equivalent-Exchange-3
 * 
 * RecipesTransmutationStone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class RecipesTransmutationStone {

    private static ItemStack philStone = new ItemStack(ModItems.philStone, 1, -1);
    private static ItemStack miniumStone = new ItemStack(ModItems.miniumStone, 1, -1);

    public static List<ItemStack> transmutationStones = Arrays.asList(miniumStone, philStone);

    private static ItemStack anyCoal = new ItemStack(Item.coal, 1, -1);
    private static ItemStack anyWood = new ItemStack(Block.wood, 1, -1);
    private static ItemStack anyPlank = new ItemStack(Block.planks, 1, -1);
    private static ItemStack anySandStone = new ItemStack(Block.sandStone, 1, -1);
    private static ItemStack dyeBoneMeal = new ItemStack(Item.dyePowder, 1, 15);

    public static void init() {

        initEquivalencyList();

        for (ItemStack stone : transmutationStones) {
            initTransmutationRecipes(stone);
            initEquivalenceRecipes(stone);
            initReconstructiveRecipes(stone);
            initDestructorRecipes(stone);
            initPortableSmeltingRecipes(stone);
        }

        if (Reference.DEBUG_MODE) {
            EquivalencyHandler.instance().debug();
        }
    }

    public static void initTransmutationRecipes(ItemStack transmutationStone) {

        /* 4 Cobble <-> 1 Flint */
        RecipeHelper.addRecipe(Item.flint, transmutationStone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone);
        RecipeHelper.addRecipe(new ItemStack(Block.cobblestone, 4), transmutationStone, Item.flint);

        /* 4 Dirt <-> 1 Gravel */
        RecipeHelper.addRecipe(Block.gravel, transmutationStone, Block.dirt, Block.dirt, Block.dirt, Block.dirt);
        RecipeHelper.addRecipe(new ItemStack(Block.dirt, 4), transmutationStone, Block.gravel);

        /* 4 Sand <-> 1 Sandstone */
        // Vanilla Recipes exist to make Sandstone from 4 Sand
        RecipeHelper.addRecipe(new ItemStack(Block.sand, 4), transmutationStone, anySandStone);

        /* 2 Stick -> Wood Planks */
        RecipeHelper.addRecipe(Block.planks, transmutationStone, Item.stick, Item.stick);
        // Vanilla recipe exists to make stick from any wood planks

        /* 4 Wood Planks -> Wood Log */
        RecipeHelper.addRecipe(Block.wood, transmutationStone, anyPlank, anyPlank, anyPlank, anyPlank);
        // Vanilla recipes exist to make planks from any wood log

        /* 4 Gravel/Sandstone/Flint -> 1 Clay Ball, 1 Clay Ball -> 4 Gravel */
        RecipeHelper.addRecipe(Item.clay, transmutationStone, Block.gravel, Block.gravel, Block.gravel, Block.gravel);
        RecipeHelper.addRecipe(Item.clay, transmutationStone, anySandStone, anySandStone, anySandStone, anySandStone);
        RecipeHelper.addRecipe(Item.clay, transmutationStone, Item.flint, Item.flint, Item.flint, Item.flint);
        RecipeHelper.addRecipe(new ItemStack(Block.gravel, 4), transmutationStone, Item.clay);

        /* 2 Wood Log/4 Clay Ball -> 1 Obsidian, 1 Obsidian -> 2 Wood Log */
        RecipeHelper.addRecipe(Block.obsidian, transmutationStone, anyWood, anyWood);
        RecipeHelper.addRecipe(Block.obsidian, transmutationStone, Item.clay, Item.clay, Item.clay, Item.clay);
        RecipeHelper.addRecipe(new ItemStack(Block.wood, 2), transmutationStone, Block.obsidian);

        /* 4 Obsidian/Clay Block -> 1 Iron Ingot, 1 Iron Ingot -> 4 Clay Block */
        RecipeHelper.addRecipe(Item.ingotIron, transmutationStone, Block.obsidian, Block.obsidian, Block.obsidian, Block.obsidian);
        RecipeHelper.addRecipe(Item.ingotIron, transmutationStone, Block.blockClay, Block.blockClay, Block.blockClay, Block.blockClay);
        RecipeHelper.addRecipe(new ItemStack(Block.blockClay, 4), transmutationStone, Item.ingotIron);

        /* 8 Iron Ingot <-> 1 Gold Ingot */
        RecipeHelper.addRecipe(Item.ingotGold, transmutationStone, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron);
        RecipeHelper.addRecipe(new ItemStack(Item.ingotIron, 8), transmutationStone, Item.ingotGold);

        /* 4 Gold Ingot <-> 1 Diamond */
        RecipeHelper.addRecipe(Item.diamond, transmutationStone, Item.ingotGold, Item.ingotGold, Item.ingotGold, Item.ingotGold);
        RecipeHelper.addRecipe(new ItemStack(Item.ingotGold, 4), transmutationStone, Item.diamond);

        /* 8 Iron Block <-> 1 Gold Block */
        RecipeHelper.addRecipe(Block.blockGold, transmutationStone, Block.blockSteel, Block.blockSteel, Block.blockSteel, Block.blockSteel, Block.blockSteel, Block.blockSteel, Block.blockSteel, Block.blockSteel);
        RecipeHelper.addRecipe(new ItemStack(Block.blockSteel, 8), transmutationStone, Block.blockGold);

        /* 4 Gold Block <-> 1 Diamond Block */
        RecipeHelper.addRecipe(Block.blockDiamond, transmutationStone, Block.blockGold, Block.blockGold, Block.blockGold, Block.blockGold);
        RecipeHelper.addRecipe(new ItemStack(Block.blockGold, 4), transmutationStone, Block.blockDiamond);

        /* 1 Ender Pearl <-> 4 Iron Ingot */
        RecipeHelper.addRecipe(Item.enderPearl, transmutationStone, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron);
        RecipeHelper.addRecipe(new ItemStack(Item.ingotIron, 4), transmutationStone, Item.enderPearl);
        
        /* 4 Iron Block -> 9 Ender Pearl */
        RecipeHelper.addRecipe(new ItemStack(Item.enderPearl, 9), transmutationStone, Block.blockSteel, Block.blockSteel, Block.blockSteel, Block.blockSteel);
        
        /* 2 Ender Pearl -> 1 Gold Ingot */
        RecipeHelper.addRecipe(Item.ingotGold, transmutationStone, Item.enderPearl, Item.enderPearl);
        
        /* 8 Ender Pearl -> 1 Diamond */
        RecipeHelper.addRecipe(Item.diamond, transmutationStone, Item.enderPearl, Item.enderPearl, Item.enderPearl, Item.enderPearl, Item.enderPearl, Item.enderPearl, Item.enderPearl, Item.enderPearl);
    }

    public static void initEquivalenceRecipes(ItemStack stone) {

        int outputI;

        for (ArrayList<ItemStack> itemStackList : EquivalencyHandler.instance().getAllLists()) {
            ItemStack[] currentList = new ItemStack[itemStackList.size()];
            currentList = itemStackList.toArray(currentList);

            for (int i = 0; i < currentList.length; i++) {
                outputI = i == currentList.length - 1 ? 0 : i + 1;

                RecipeHelper.addRecipe(currentList[outputI], stone, GeneralHelper.convertSingleStackToPluralStacks(currentList[i]));
            }
        }
    }

    public static void initReconstructiveRecipes(ItemStack stone) {

        /* 3 Bone Meal --> 1 Bone */
        RecipeHelper.addRecipe(Item.bone, stone, dyeBoneMeal, dyeBoneMeal, dyeBoneMeal);

        /* 2 Blaze Powder --> 1 Blaze Rod */
        RecipeHelper.addRecipe(Item.blazeRod, stone, Item.blazePowder, Item.blazePowder);
    }

    public static void initDestructorRecipes(ItemStack stone) {

        /* 1 Smooth Stone -> 1 Cobble */
        RecipeHelper.addRecipe(Block.cobblestone, stone, Block.stone);
        
        /* 1 Furnace -> 8 Cobble */
        RecipeHelper.addRecipe(new ItemStack(Block.cobblestone, 8), stone, Block.furnace);

        /* 1 Glass -> 1 Sand */
        RecipeHelper.addRecipe(Block.sand, stone, Block.glass);

        /* 1 Glowstone Block -> 4 Glowstone Dust */
        RecipeHelper.addRecipe(new ItemStack(Item.lightStoneDust, 4), stone, Block.glowStone);

        /* 1 Brick Block -> 4 Brick */
        RecipeHelper.addRecipe(new ItemStack(Item.brick, 4), stone, Block.brick);
        
        /* 1 Flower Pot -> 3 Brick */
        RecipeHelper.addRecipe(new ItemStack(Item.brick, 3), stone, Block.flowerPot);
        
        /* 1 Clay Block -> 4 Clay Ball */
        RecipeHelper.addRecipe(new ItemStack(Item.clay, 4), stone, Block.blockClay);
        
        /* 1 Brick -> 1 Clay Ball */
        RecipeHelper.addRecipe(Item.clay, stone, Item.brick);
        
        /* 1 Chest -> 2 Wood Log */
        RecipeHelper.addRecipe(new ItemStack(Block.wood, 2), stone, Block.chest);
        
        /* 1 Fence Gate -> 1 Wood Log */
        RecipeHelper.addRecipe(Block.wood, stone, Block.fenceGate);
        
        /* 1 Wooden Door -> 6 Wood Planks */
        RecipeHelper.addRecipe(new ItemStack(Block.planks, 6), stone, Block.doorWood);
        
        /* 1 Crafting Table -> 4 Wood Planks */
        RecipeHelper.addRecipe(new ItemStack(Block.planks, 4), stone, Block.workbench);
        
        /* 1 Fence -> 3 Stick */
        RecipeHelper.addRecipe(new ItemStack(Item.stick, 3), stone, Block.fence);
        
        /* 1 Cauldron -> 7 Iron Ingot */
        RecipeHelper.addRecipe(new ItemStack(Item.ingotIron, 7), stone, Block.cauldron);
        
        /* 1 Iron Door -> 6 Iron Ingot */
        RecipeHelper.addRecipe(new ItemStack(Item.ingotIron, 6), stone, Block.doorIron);
        
        /* 1 Minecart -> 5 Iron Ingot */
        RecipeHelper.addRecipe(new ItemStack(Item.ingotIron, 5), stone, Item.minecart);
        
        /* 1 Bucket -> 3 Iron Ingot */
        RecipeHelper.addRecipe(new ItemStack(Item.ingotIron, 3), stone, Item.bucket);
        
        /* 1 Bread -> 3 Wheat */
        RecipeHelper.addRecipe(new ItemStack(Item.wheat, 3), stone, Item.bread);
        
        /* 1 Nether Brick Fence -> 1 Nether Brick Block */
        RecipeHelper.addRecipe(Block.netherFence, stone, Block.netherBrick);
        /**
         * Yeah, I said Nether Brick BLOCK there so
         * it doesn't get confused with the Nether
         * Brick counterpart of the regular Brick
         * introduced in the Minecraft 1.5 update.
         */
    }

    public static void initPortableSmeltingRecipes(ItemStack stone) {

        Map furnaceMap = FurnaceRecipes.smelting().getSmeltingList();
        Map furnaceMetaMap = ObfuscationReflectionHelper.getPrivateValue(FurnaceRecipes.class, FurnaceRecipes.smelting(), "metaSmeltingList");

        Iterator iterFurnaceKeyMap = furnaceMap.keySet().iterator();
        Iterator iterFurnaceMetaKeyMap = furnaceMetaMap.keySet().iterator();

        Integer furnaceMapKey;
        List furnaceMetaMapKey;

        ItemStack unSmeltedStack;

        while (iterFurnaceKeyMap.hasNext()) {
            furnaceMapKey = (Integer) iterFurnaceKeyMap.next();
            unSmeltedStack = new ItemStack(furnaceMapKey, 1, 0);

            RecipeHelper.addSmeltingRecipe(unSmeltedStack, stone, anyCoal);
        }

        while (iterFurnaceMetaKeyMap.hasNext()) {
            furnaceMetaMapKey = (List) iterFurnaceMetaKeyMap.next();
            unSmeltedStack = new ItemStack((Integer) furnaceMetaMapKey.get(0), 1, (Integer) furnaceMetaMapKey.get(1));

            RecipeHelper.addSmeltingRecipe(unSmeltedStack, stone, anyCoal);
        }
    }

    protected static void initEquivalencyList() {

        EquivalencyHandler.instance().addObjects(Block.sand, Block.dirt, Block.cobblestone, Block.grass);
        EquivalencyHandler.instance().addObjects(Block.plantYellow, Block.plantRed);
        EquivalencyHandler.instance().addObjects(Block.mushroomRed, Block.mushroomBrown);
        EquivalencyHandler.instance().addObjects(Item.pumpkinSeeds, Item.melonSeeds);
        EquivalencyHandler.instance().addObjects(Block.pumpkin, Block.melon);
        EquivalencyHandler.instance().addObjects(Block.pumpkinStem, Block.melonStem);
        EquivalencyHandler.instance().addObjects(Block.stairsWoodSpruce, Block.stairsWoodBirch, Block.stairsWoodJungle);
        EquivalencyHandler.instance().addObjects(new ItemStack(Item.paper, 3), new ItemStack(Item.reed, 3));
        EquivalencyHandler.instance().addObjects(new ItemStack(Item.flint, 2), new ItemStack(Block.gravel, 2), new ItemStack(Block.sandStone, 2, 0), new ItemStack(Block.sandStone, 2, 1), new ItemStack(Block.sandStone, 2, 2));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Block.planks, 4));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Block.wood, 4));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Block.woodSingleSlab, 4));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Block.woodDoubleSlab, 4));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Block.sapling, 4));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Block.leaves, 4));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Block.tallGrass, 3));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Block.cloth, 16));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Block.stoneBrick, 4));
        EquivalencyHandler.instance().addObjects(RecipeHelper.getMetaCycle(Item.dyePowder, 16, 3, 4, 15));
    }

}

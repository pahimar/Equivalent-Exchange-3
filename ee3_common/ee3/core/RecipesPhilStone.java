package ee3.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ee3.core.helper.Helper;
import ee3.item.ItemPhilosopherStone;
import ee3.item.ItemMiniumStone;
import ee3.item.ModItems;
import ee3.lib.Reference;

import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

import static net.minecraft.src.Item.*;
import static net.minecraft.src.Block.*;
import static ee3.lib.TransmuteEquivalencyList.*;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class RecipesPhilStone {

	private static ItemStack philStone = new ItemStack(ModItems.philStone, 1, -1);
	private static ItemStack miniumStone = new ItemStack(ModItems.miniumStone, 1, -1);
	private static ItemStack anyCoal = new ItemStack(coal, 1, -1);	
	private static ItemStack anyWood = new ItemStack(wood, 1, -1);
	private static ItemStack anyPlank = new ItemStack(planks, 1, -1);
	private static ItemStack anySandStone = new ItemStack(sandStone, 1, -1);
	private static ItemStack dyeBoneMeal = new ItemStack(dyePowder, 1, 15);
	
	private static List<ItemStack> transmutationStones = Arrays.asList(miniumStone, philStone);
	
	public static void initRecipes() {
		initEquivalencyList();
		
		for (ItemStack transmutationStone: transmutationStones) {
			initTransmutationRecipes(transmutationStone);
			initEquivalenceRecipes(transmutationStone);
			initReconstructiveRecipes(transmutationStone);
			initDestructorRecipes(transmutationStone);
			initPortableSmeltingRecipes(transmutationStone);
		}
		
		if (Reference.DEBUG) {
			debugPrintEquivalencyList();
		}
	}
	
	public static void initTransmutationRecipes(ItemStack transmutationStone) {
		/* 4 Cobble <-> 1 Flint */
		addRecipe(flint, transmutationStone, cobblestone, cobblestone, cobblestone, cobblestone);
		addRecipe(new ItemStack(cobblestone, 4), transmutationStone, flint);
		
		/* 4 Dirt <-> 1 Gravel */
		addRecipe(gravel, transmutationStone, dirt, dirt, dirt, dirt);
		addRecipe(new ItemStack(dirt, 4), transmutationStone, gravel);
		
		/* 4 Sand <-> 1 Sandstone */
		// Vanilla Recipes exist to make SandStone from 4 Sand
		addRecipe(new ItemStack(sand, 4), transmutationStone, anySandStone);
		
		/* 2 Sticks -> Wood Plank */
		addRecipe(planks, transmutationStone, stick, stick);
		// Vanilla recipe exists to make sticks from planks
		
		/* 4 Wood Planks -> Wood Block */
		addRecipe(wood, transmutationStone, anyPlank, anyPlank, anyPlank, anyPlank);
		// Vanilla recipes exist to make planks from any wood log
		
		/* 4 Gravel/Sandstone/Flint -> 1 Clay Ball, 1 Clay Ball -> 4 Gravel */
		addRecipe(clay, transmutationStone, gravel, gravel, gravel, gravel);
		addRecipe(clay, transmutationStone, anySandStone, anySandStone, anySandStone, anySandStone);
		addRecipe(clay, transmutationStone, flint, flint, flint, flint);
		addRecipe(new ItemStack(gravel, 4), transmutationStone, clay);
		
		/* 2 Wood Log <-> 1 Obsidian */
		addRecipe(obsidian, transmutationStone, anyWood, anyWood);
		addRecipe(new ItemStack(wood, 2), transmutationStone, obsidian);
		
		/* 4 Clay Ball <-> 1 Clay Block */
		// Vanilla recipe exists to make clay blocks from clay balls
		addRecipe(new ItemStack(clay, 4), transmutationStone, blockClay);
		
		/* 4 Obsidian/Clay Block -> 1 Iron Ingot, Iron Ingot -> Clay Block */
		addRecipe(ingotIron, transmutationStone, obsidian, obsidian, obsidian, obsidian);
		addRecipe(ingotIron, transmutationStone, blockClay, blockClay, blockClay, blockClay);
		addRecipe(new ItemStack(blockClay, 4), transmutationStone, ingotIron);
		
		/* 8 Iron Ingot <-> 1 Gold Ingot */
		addRecipe(ingotGold, transmutationStone, ingotIron, ingotIron, ingotIron, ingotIron, ingotIron, ingotIron, ingotIron, ingotIron);
		addRecipe(new ItemStack(ingotIron, 8), transmutationStone, ingotGold);
		
		/* 4 Gold Ingot <-> 1 Diamond */
		addRecipe(diamond, transmutationStone, ingotGold, ingotGold, ingotGold, ingotGold);
		addRecipe(new ItemStack(ingotGold, 4), transmutationStone, diamond);
		
		/* 8 Iron Block <-> 1 Gold Block */
		addRecipe(blockGold, transmutationStone, blockSteel, blockSteel, blockSteel, blockSteel, blockSteel, blockSteel, blockSteel, blockSteel);
		addRecipe(new ItemStack(blockSteel, 8), transmutationStone, blockGold);
		
		/* 4 Gold Block <-> 1 Diamond Block */
		addRecipe(blockDiamond, transmutationStone, blockGold, blockGold, blockGold, blockGold);
		addRecipe(new ItemStack(blockGold, 4), transmutationStone, blockDiamond);
		
		/* 1 Ender Pearl <-> 4 Iron Ingot */
		addRecipe(enderPearl, transmutationStone, ingotIron, ingotIron, ingotIron, ingotIron);
		addRecipe(new ItemStack(ingotIron, 4), transmutationStone, enderPearl);
	}
	
	public static void initEquivalencyList() {
		addObjectsToEquivalencyLists(sand, dirt, cobblestone, grass);
		addObjectsToEquivalencyLists(plantYellow, plantRed);
		addObjectsToEquivalencyLists(mushroomRed, mushroomBrown);
		addObjectsToEquivalencyLists(pumpkinSeeds, melonSeeds);
		addObjectsToEquivalencyLists(pumpkin, Block.melon);
		addObjectsToEquivalencyLists(new ItemStack(paper, 3), new ItemStack(Item.reed, 3));
		addObjectsToEquivalencyLists(new ItemStack(flint, 2), new ItemStack(gravel, 2), new ItemStack(sandStone, 2, 0), new ItemStack(sandStone, 2, 1), new ItemStack(sandStone, 2, 2));
		addObjectsToEquivalencyLists(getMetaCycle(planks, 4));
		addObjectsToEquivalencyLists(getMetaCycle(wood, 4));
		addObjectsToEquivalencyLists(getMetaCycle(sapling, 4));
		addObjectsToEquivalencyLists(getMetaCycle(leaves, 4));
		addObjectsToEquivalencyLists(getMetaCycle(tallGrass, 3));
		addObjectsToEquivalencyLists(getMetaCycle(cloth, 16));
		addObjectsToEquivalencyLists(getMetaCycle(stoneBrick, 4));
		addObjectsToEquivalencyLists(getMetaCycle(dyePowder, 16, 3, 4, 15));
	}
	
	public static void initEquivalenceRecipes(ItemStack transmutationStone) {
		int outputI;
		
		for (ArrayList<ItemStack> itemStackList : equivalencyLists) {
			ItemStack[] currentList = new ItemStack[itemStackList.size()];
			currentList = itemStackList.toArray(currentList);
			
			for(int i = 0; i < currentList.length; i++) {
				outputI = (i == currentList.length - 1 ? 0 : i + 1);

				addRecipe(currentList[outputI], transmutationStone, Helper.convertSingleStackToPluralStacks(currentList[i]));
			}
		}
		System.out.println();
	}
	
	public static void initReconstructiveRecipes(ItemStack stone) {
		/* 3 Bone Meal --> 1 Bone */
		addRecipe(bone, stone, dyeBoneMeal, dyeBoneMeal, dyeBoneMeal);
		
		/* 2 Blaze Powder --> 1 Blaze Rod */
		addRecipe(blazeRod, stone, blazePowder, blazePowder);
	}
	
	public static void initDestructorRecipes(ItemStack transmutationStone) {
		/* Smooth Stone -> Cobble Stone */
		addRecipe(cobblestone, transmutationStone, stone);
		
		/* Glass -> Sand */
		addRecipe(sand, transmutationStone, glass);
		
		/* Glowstone Block -> 4 Glowstone Dust */
		addRecipe(new ItemStack(lightStoneDust, 4), transmutationStone, glowStone);
		
		/* Brick Block -> 4 Bricks */
		addRecipe(new ItemStack(Item.brick, 4), transmutationStone, Block.brick);
	}
	
	public static void initPortableSmeltingRecipes(ItemStack transmutationStone) {	
		Map furnaceMap = FurnaceRecipes.smelting().getSmeltingList();
		Map furnaceMetaMap = ModLoader.getPrivateValue(FurnaceRecipes.class, FurnaceRecipes.smelting(), "metaSmeltingList");
		
		Iterator iterFurnaceKeyMap = furnaceMap.keySet().iterator();
		Iterator iterFurnaceMetaKeyMap = furnaceMetaMap.keySet().iterator();
		
		Integer furnaceMapKey;
		List furnaceMetaMapKey;
		
		ItemStack unSmeltedStack;
		
		while (iterFurnaceKeyMap.hasNext()) {
			furnaceMapKey = (Integer) iterFurnaceKeyMap.next();
			unSmeltedStack = new ItemStack(furnaceMapKey, 1, 0);

			addSmeltingRecipe(unSmeltedStack, transmutationStone);
		}
		
		while (iterFurnaceMetaKeyMap.hasNext()) {
			furnaceMetaMapKey = (List)iterFurnaceMetaKeyMap.next();
			unSmeltedStack = new ItemStack((Integer)furnaceMetaMapKey.get(0), 1, (Integer)furnaceMetaMapKey.get(1));
			
			addSmeltingRecipe(unSmeltedStack, transmutationStone);
		}
	}
	
	protected static void addRecipe(ItemStack result, Object ... input) {
		ModLoader.addShapelessRecipe(result, input);
	}
	
	protected static void addRecipe(ItemStack result, ItemStack transmutationStone, Object ... input) {
		Object[] inputs = new Object[input.length + 1];
		inputs[0] = transmutationStone;
		for (int i = 1; i < inputs.length; ++i) {
			inputs[i] = input[i-1];
		}
		
		addRecipe(result, inputs);
	}

	protected static void addRecipe(Block result, Object ... input) {
		addRecipe(new ItemStack(result), input);
	}
	
	protected static void addRecipe(Block result, int count, Object ... input) {
		addRecipe(new ItemStack(result, count), input);
	}
	
	protected static void addRecipe(Item result, Object ... input) {
		addRecipe(new ItemStack(result), input);
	}
	
	protected static void addRecipe(Item result, int count, Object ... input) {
		addRecipe(new ItemStack(result, count), input);
	}
	
	protected static Object[] getMetaCycle(Object input, int n) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		
		ItemStack stack;
		
		for (int i = 0; i < n; i++) {
			stack = Helper.convertObjectToItemStack(input);
			stack.setItemDamage(i);
			list.add(stack);
		}
		
		return list.toArray();
	}
	
	protected static Object[] getMetaCycle(Object input, int n, int ... excludedMeta) {
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
			
			stack = Helper.convertObjectToItemStack(input);
			stack.setItemDamage(i);
			list.add(stack);
			++i;
		}
		
		return list.toArray();
	}
	
	/* Pass this a Block, Item or ItemStack and the maximum number of indexes, EXCLUDING zero */
	protected static void addMetaCycleRecipe(Object input, int n) {
		int outputI;
		
		/* Makes a single item cycle through its meta values when it's crafted with a PStone */
		for(int i = 0; i < n; i++) {
			outputI = (i == n - 1 ? 0 : i + 1);
			
			if(input instanceof Block)
				ModLoader.addShapelessRecipe(new ItemStack((Block)input, 1, outputI), philStone, new ItemStack((Block)input, 1, i));
			else if (input instanceof Item)
				ModLoader.addShapelessRecipe(new ItemStack((Item)input, 1, outputI), philStone, new ItemStack((Item)input, 1, i));
			else if (input instanceof ItemStack)		
				ModLoader.addShapelessRecipe(new ItemStack(((ItemStack)input).itemID, 1, outputI), philStone, new ItemStack(((ItemStack)input).itemID, 1, i));
		}
	}
	
	protected static void addMetaCycleRecipe(Object input, int n, int ... excludedMeta) {
		int i = 0;
		int outputI = 1;
		while (i < n && outputI != 0) {
			outputI = (i == n - 1 ? 0 : i + 1);
			
			for (int j : excludedMeta) {
				if (outputI == j)
					outputI = (outputI + 1) % 16;
			}
			
			if(input instanceof Block)
				ModLoader.addShapelessRecipe(new ItemStack((Block)input, 1, outputI), philStone, new ItemStack((Block)input, 1, i));
			else if (input instanceof Item)
				ModLoader.addShapelessRecipe(new ItemStack((Item)input, 1, outputI), philStone, new ItemStack((Item)input, 1, i));
			else if (input instanceof ItemStack)		
				ModLoader.addShapelessRecipe(new ItemStack(((ItemStack)input).itemID, 1, outputI), philStone, new ItemStack(((ItemStack)input).itemID, 1, i));
			
			i = outputI;
		}
	}
	
	/* Final method, actually adds the portable smelting recipe */
	protected static void addSmeltingRecipe(ItemStack input, ItemStack transmutationStone) {
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(input); 
		
		if(result == null)
			return;
		Object[] list = new Object[9];
		list[0] = transmutationStone;
		list[1] = anyCoal;
		
		for(int i = 2; i < 9; i++)
			list[i] = new ItemStack(input.getItem(), 1, input.getItemDamage());
		
		ModLoader.addShapelessRecipe(new ItemStack(result.getItem(), 7, result.getItemDamage()), list);
	}
}

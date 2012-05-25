package ee3.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import ee3.item.ItemPhilosopherStone;
import ee3.item.ModItems;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ShapedRecipes;
import net.minecraft.src.ShapelessRecipes;
import net.minecraft.src.mod_EE3;

import static net.minecraft.src.Item.*;
import static net.minecraft.src.Block.*;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class RecipesPhilStone {

	private static ItemStack philStone = new ItemStack(ModItems.philStone, 1, -1);
	private static ItemStack anyCoal = new ItemStack(coal, 1, -1);	
	private static ItemStack anyWood = new ItemStack(wood, 1, -1);
	private static ItemStack anyPlank = new ItemStack(planks, 1, -1);
	private static ItemStack anySandStone = new ItemStack(sandStone, 1, -1);
	private static ItemStack dyeBoneMeal = new ItemStack(dyePowder, 1, 15);
	
	
	
	public static void initRecipes() {
		//determineBaseMaterials();
		initTransmutationRecipes();
		initEquivalenceRecipes();
		initReconstructiveRecipes();
		initDestructorRecipes();
		initPortableSmeltingRecipes();
	}
	
	public static void determineBaseMaterials() {
		CraftingManager instance = CraftingManager.getInstance();
		List recipeList = instance.getRecipeList();

		IRecipe recipe;
		ShapedRecipes shapedRecipe;
		ShapelessRecipes shapelessRecipe;
		
		ItemStack[] shapedInputs;
		List<ItemStack> shapelessInputs;
		
		ItemStack recipeOutput = null;
		Iterator<IRecipe> recipeIter = recipeList.iterator();
		
		ItemStack[] recipeInputs = null;
		Vector<Integer> inputs = new Vector<Integer>();
		Vector<Integer> outputs = new Vector<Integer>();
		
		try {			
			while (recipeIter.hasNext()) {
				recipe = recipeIter.next();
				
				if (recipe instanceof ShapedRecipes) {
					shapedRecipe = (ShapedRecipes) recipe;
					//shapedInputs = mod_EE3.proxy.getPrivateValue(ShapedRecipes.class, shapedRecipe, "recipeItems");
					recipeInputs = mod_EE3.proxy.getPrivateValue(ShapedRecipes.class, shapedRecipe, "recipeItems");
					recipeOutput = mod_EE3.proxy.getPrivateValue(ShapedRecipes.class, shapedRecipe, "recipeOutput");
					System.out.println("Shaped Recipe");
				}
				else if (recipe instanceof ShapelessRecipes) {
					shapelessRecipe = (ShapelessRecipes) recipe;
					shapelessInputs = mod_EE3.proxy.getPrivateValue(ShapelessRecipes.class, shapelessRecipe, "recipeItems");
					recipeInputs = shapelessInputs.toArray(new ItemStack[0]);
					recipeOutput = mod_EE3.proxy.getPrivateValue(ShapelessRecipes.class, shapelessRecipe, "recipeOutput");
					System.out.println("Shapeless Recipe");
				}
				System.out.println("Output: " + recipeOutput.toString());
				outputs.add(recipeOutput.getItem().shiftedIndex);
				for (ItemStack itemStack : recipeInputs) {
					if (itemStack != null) {
						System.out.println("Input: " + itemStack.toString());
						if (!inputs.contains(new Integer(itemStack.getItem().shiftedIndex)))
							inputs.add(new Integer(itemStack.getItem().shiftedIndex));
					}
				}
				System.out.println();
			}
			
			//for (int i = 0; i < outputs.size(); i++) {
			//	System.out.println("i: " + i + ", item: " + outputs.get(i));
			//}
			inputs.removeAll(outputs);
			for (int i = 0; i < inputs.size(); i++) {
				System.out.println(i + "," + inputs.get(i) + "," + Item.itemsList[inputs.get(i)].getItemName());
			}
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void initTransmutationRecipes() {
		/* 4 Cobble <-> 1 Flint */
		addRecipe(flint, philStone, cobblestone, cobblestone, cobblestone, cobblestone);
		addRecipe(cobblestone, 4, philStone, flint);
		
		/* 4 Dirt <-> 1 Gravel */
		addRecipe(gravel, philStone, dirt, dirt, dirt, dirt);
		addRecipe(dirt, 4, philStone, gravel);
		
		/* 4 Sand <-> 1 Sandstone */
		// Vanilla Recipes exist to make SandStone from 4 Sand
		addRecipe(sand, 4, philStone, anySandStone);
		
		/* 2 Sticks -> Wood Plank */
		addRecipe(planks, philStone, stick, stick);
		// Vanilla recipe exists to make sticks from planks
		
		/* 4 Wood Planks -> Wood Block */
		addRecipe(wood, philStone, anyPlank, anyPlank, anyPlank, anyPlank);
		// Vanilla recipes exist to make planks from any wood log
		
		/* 4 Gravel/Sandstone/Flint -> 1 Clay Ball, 1 Clay Ball -> 4 Gravel */
		addRecipe(clay, philStone, gravel, gravel, gravel, gravel);
		addRecipe(clay, philStone, anySandStone, anySandStone, anySandStone, anySandStone);
		addRecipe(clay, philStone, flint, flint, flint, flint);
		addRecipe(gravel, 4, philStone, clay);
		
		/* 2 Wood Log <-> 1 Obsidian */
		addRecipe(obsidian, philStone, anyWood, anyWood);
		addRecipe(wood, 2, philStone, obsidian);
		
		/* 4 Clay Ball <-> 1 Clay Block */
		// Vanilla recipe exists to make clay blocks from clay balls
		addRecipe(clay, 4, philStone, blockClay);
		
		/* 4 Obsidian/Clay Block -> 1 Iron Ingot, Iron Ingot -> Clay Block */
		addRecipe(ingotIron, philStone, obsidian, obsidian, obsidian, obsidian);
		addRecipe(ingotIron, philStone, blockClay, blockClay, blockClay, blockClay);
		addRecipe(blockClay, 4, philStone, ingotIron);
		
		/* 8 Iron Ingot <-> 1 Gold Ingot */
		addRecipe(ingotGold, philStone, ingotIron, ingotIron, ingotIron, ingotIron, ingotIron, ingotIron, ingotIron, ingotIron);
		addRecipe(ingotIron, 8, philStone, ingotGold);
		
		/* 4 Gold Ingot <-> 1 Diamond */
		addRecipe(diamond, philStone, ingotGold, ingotGold, ingotGold, ingotGold);
		addRecipe(ingotGold, 4, philStone, diamond);
		
		/* 8 Iron Block <-> 1 Gold Block */
		addRecipe(blockGold, philStone, blockSteel, blockSteel, blockSteel, blockSteel, blockSteel, blockSteel, blockSteel, blockSteel);
		addRecipe(blockSteel, 8, philStone, blockGold);
		
		/* 4 Gold Block <-> 1 Diamond Block */
		addRecipe(blockDiamond, philStone, blockGold, blockGold, blockGold, blockGold);
		addRecipe(blockGold, 4, philStone, blockDiamond);
		
		/* 1 Ender Pearl <-> 4 Iron Ingot */
		addRecipe(enderPearl, philStone, ingotIron, ingotIron, ingotIron, ingotIron);
		addRecipe(ingotIron, 4, philStone, enderPearl);
	}
	
	public static void initEquivalenceRecipes() {
		/* Wood Plank Cycle */
		addMetaCycleRecipe(planks, 4);
		
		/* Wood Log Cycle */
		addMetaCycleRecipe(wood, 4);
		
		/* Sapling Cycle */
		addMetaCycleRecipe(sapling, 4);
		
		/* Leaf Cycle */
		addMetaCycleRecipe(leaves, 4);
		
		/* Tall Grass Cycle */
		addMetaCycleRecipe(tallGrass, 3);
		
		/* Wool Cycle */
		addMetaCycleRecipe(cloth, 16);
		
		/* Stone Brick Cycle */
		addMetaCycleRecipe(stoneBrick, 4);
		
		/* Dye Equivalence Cycle */
		addMetaCycleRecipe(dyePowder, 16, 3, 4, 15);
		
		/* Dirt -> Cobble -> Sand -> Dirt */
		addRecipe(dirt, philStone, cobblestone);
		addRecipe(cobblestone, philStone, sand);
		addRecipe(sand, philStone, dirt);
		
		/* 2 Gravel -> 2 Flint -> 2 Sandstone (Cycles) -> 2 Gravel*/
		addRecipe(flint, 2, philStone, gravel, gravel);
		addRecipe(new ItemStack(sandStone, 2, 0), philStone, flint, flint);
		addRecipe(new ItemStack(sandStone, 2, 1), philStone, new ItemStack(sandStone, 2, 0), new ItemStack(sandStone, 2, 0));
		addRecipe(new ItemStack(sandStone, 2, 2), philStone, new ItemStack(sandStone, 2, 1), new ItemStack(sandStone, 2, 1));
		addRecipe(gravel, 2, philStone, new ItemStack(sandStone, 2, 2), new ItemStack(sandStone, 2, 2));	
		
		/* Flower Equivalence Recipes */
		addRecipe(plantYellow, philStone, plantRed);
		addRecipe(plantRed, philStone, plantYellow);
		// RP2 flower recipe goes here, it SHOULD make them cycle instead of two-way if RP2 is present
		
		/* Mushroom Equivalence Recipes */
		addRecipe(mushroomRed, philStone, mushroomBrown);
		addRecipe(mushroomBrown, philStone, mushroomRed);
		
		/*           Books/                              */
		/* Reeds <-> Paper <-> Sugar Equivalence Recipes */
		addRecipe(paper, 3, philStone, book);
		addRecipe(Item.reed, 3, philStone, paper, paper, paper);
		addRecipe(Item.reed, philStone, sugar);
		
		/* Melon <-> Pumpkin Equivalence Recipes */
		addRecipe(pumpkinSeeds, philStone, melonSeeds);
		addRecipe(melonSeeds, philStone, pumpkinSeeds);
		addRecipe(pumpkin, philStone, Block.melon);
		addRecipe(Block.melon, philStone, pumpkin);
	}
	
	public static void initReconstructiveRecipes() {
		/* 3 Bone Meal --> 1 Bone */
		addRecipe(bone, philStone, dyeBoneMeal, dyeBoneMeal, dyeBoneMeal);
		
		/* 2 Blaze Powder --> 1 Blaze Rod */
		addRecipe(blazeRod, philStone, blazePowder, blazePowder);
	}
	
	public static void initDestructorRecipes() {
		/* Smooth Stone -> Cobble Stone */
		addRecipe(cobblestone, philStone, stone);
		
		/* Glass -> Sand */
		addRecipe(sand, philStone, glass);
		
		/* Glowstone Block -> 4 Glowstone Dust */
		addRecipe(lightStoneDust, 4, philStone, glowStone);
		
		/* Brick Block -> 4 Bricks */
		addRecipe(Item.brick, 4, philStone, Block.brick);
	}
	
	public static void initPortableSmeltingRecipes() {
		/* Smelt cobblestone */
		addSmeltingRecipe(Block.cobblestone);

		/* Smelt any wood */
		addSmeltingRecipe(anyWood);

		/* Smelt iron ore */
		addSmeltingRecipe(oreIron);
		
		/* Smelt gold ore */
		addSmeltingRecipe(oreGold);
		
		/* Smelt sand */
		addSmeltingRecipe(sand);
		
		/* Cook chicken */
		addSmeltingRecipe(chickenRaw);
		
		/* Cook pork */
		addSmeltingRecipe(porkRaw);
		
		/* Cook beef */
		addSmeltingRecipe(beefRaw);
		
		/* Cook fish */
		addSmeltingRecipe(fishRaw);		
	}
	
	
	
	protected static void addRecipe(ItemStack result, Object ... input) {
		ModLoader.addShapelessRecipe(result, input);
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
	
	/* No meta, defaults to zero */
	protected static void addSmeltingRecipe(Object input) {
		addSmeltingRecipe(input, 0);
	}
	
	/* Includes meta, passes either Block or Item, with meta, to final method as an ItemStack */
	protected static void addSmeltingRecipe(Object input, int i) {
		if(input instanceof Item)
			addSmeltingRecipe(new ItemStack((Item)input, 1, i));
		else if (input instanceof Block)
			addSmeltingRecipe(new ItemStack((Block)input, 1, i));
	}
	
	/* Final method, actually adds the portable smelting recipe */
	protected static void addSmeltingRecipe(ItemStack input) {
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(input); 
		
		if(result == null)
			return;
		Object[] list = new Object[9];
		list[0] = philStone;
		list[1] = anyCoal;
		
		for(int i = 2; i < 9; i++)
			list[i] = new ItemStack(input.getItem(), 1, input.getItemDamage());
		
		ModLoader.addShapelessRecipe(new ItemStack(result.getItem(), 7, result.getItemDamage()), list);
	}
}

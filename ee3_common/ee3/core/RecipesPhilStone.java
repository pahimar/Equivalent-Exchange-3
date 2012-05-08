package ee3.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

public class RecipesPhilStone {

	public static ItemStack philStone = new ItemStack(ModItems.philStone, 1, -1);
	
	private static ItemStack anyCoal = new ItemStack(Item.coal, 1, -1);
	
	private static ItemStack anyWood = new ItemStack(Block.wood, 1, -1);
	
	private static ItemStack planks(int i) { return new ItemStack(Block.planks, 1, i); }
	private static ItemStack wood(int i) { return new ItemStack(Block.wood, 1, i); }
	
	private static ItemStack sandStone(int i) { return sandStone(1, i); }
	private static ItemStack sandStone(int i, int j) { return new ItemStack(Block.sandStone, i, j); }
	
	private static ItemStack stoneBrick(int i) { return stoneBrick(1, i); }
	private static ItemStack stoneBrick(int i, int j) { return new ItemStack(Block.stoneBrick, i, j); }
	
	private static ItemStack boneMeal = new ItemStack(Item.dyePowder, 1, 15);
	
	public static void initRecipes() {
		//determineBaseMaterials();
		initTransmutationRecipes();
		initEquivalencyRecipes();
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
		List shapelessInputs;
		
		Iterator<IRecipe> recipeIter = recipeList.iterator();

		try {			
			while (recipeIter.hasNext()) {
				recipe = recipeIter.next();
				
				if (recipe instanceof ShapedRecipes) {
					shapedRecipe = (ShapedRecipes) recipe;
					shapedInputs = mod_EE3.proxy.getPrivateValue(ShapedRecipes.class, shapedRecipe, "recipeItems");
					
					System.out.println("Shaped");
					System.out.println(shapedInputs.toString());
				}
				else if (recipe instanceof ShapelessRecipes) {
					shapelessRecipe = (ShapelessRecipes) recipe;
					shapelessInputs = mod_EE3.proxy.getPrivateValue(ShapelessRecipes.class, shapelessRecipe, "recipeItems");
					
					System.out.println("Shapeless");
					System.out.println(shapelessInputs.toString());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public static void addOneWayRecipe(Object input, Object output) {
		addOneWayRecipe(input, 1, output, 1);
	}
	
	public static void addOneWayRecipe(Object input, int n, Object output) {
		addOneWayRecipe(input, n, output, 1);
	}
	
	public static void addOneWayRecipe(Object input, int numOfInputs, Object output, int outputStackSize) {
		ItemStack inputStack, outputStack = null;
		
		if (input instanceof Item)
			inputStack = new ItemStack((Item)input);
		else if (input instanceof Block)
			inputStack = new ItemStack((Block)input);
		else if (input instanceof ItemStack)
			inputStack = (ItemStack) input;
		else
			return;
		
		if (output instanceof Item)
			outputStack = new ItemStack((Item)output);
		else if (output instanceof Block)
			outputStack = new ItemStack((Block)output);
		else if (output instanceof ItemStack)
			outputStack = (ItemStack) output;
		else
			return;
		
		outputStack.stackSize = outputStackSize;
		
		Object[] list = new Object[numOfInputs + 1];
		list[0] = philStone;
		for(int i = 1; i < numOfInputs + 1; i++) {
			list[i] = inputStack;
		}
		
		ModLoader.addShapelessRecipe(outputStack, list);
	}
	
	/* Adds a recipe AND its exact reversal */
	public static void addTwoWayRecipe(Object input, Object output) {
		addOneWayRecipe(input, output);
		addOneWayRecipe(output, input);
	}
	
	public static void addTwoWayRecipe(Object input, int numOfInputs, Object output, int outputStackSize) {
		addOneWayRecipe(input, numOfInputs, output, outputStackSize);
		addOneWayRecipe(output, outputStackSize, input, numOfInputs);
	}
	
	/* Pass this a Block, Item or ItemStack and the maximum number of indexes, EXCLUDING zero */
	public static void addMetaCycleRecipe(Object input, int n) {
		int outputI;
		
		/* Makes a single item cycle through its meta values when it's crafted with a PStone */
		for(int i = 0; i < n; i++) {
			outputI = (i == n - 1 ? 0 : i + 1);
			
			if(input instanceof Block) {
				ModLoader.addShapelessRecipe(new ItemStack((Block)input, 1, outputI), new Object[] {
					philStone, new ItemStack((Block)input, 1, i)
				});
			}
			else if (input instanceof Item) {
				ModLoader.addShapelessRecipe(new ItemStack((Item)input, 1, outputI), new Object[] {
					philStone, new ItemStack((Item)input, 1, i)
				});
			}
			else if (input instanceof ItemStack) {				
				ModLoader.addShapelessRecipe(new ItemStack(((ItemStack)input).itemID, 1, outputI), new Object[] {
					philStone, new ItemStack(((ItemStack)input).itemID, 1, i)
				});
			}
		}
	}
	
	/* Just use Item/Block and (optionally) meta-data for best results, when adding smelt-recipes.
	* Notice that this actually pulls results from the Furnace Recipes list, you could theoretically use this to pull
	* all possible results from a list and support their smelts automatically. */
	
	/* No meta, defaults to zero */
	public static void addSmeltingRecipe(Object input) {
		addSmeltingRecipe(input, 0);
	}
	
	/* Includes meta, passes either Block or Item, with meta, to final method as an ItemStack */
	public static void addSmeltingRecipe(Object input, int i) {
		if(input instanceof Item)
			addSmeltingRecipe(new ItemStack((Item)input, 1, i));
		else if (input instanceof Block)
			addSmeltingRecipe(new ItemStack((Block)input, 1, i));
		else
			return;		
	}
	
	/* Final method, actually adds the portable smelting recipe */
	public static void addSmeltingRecipe(ItemStack input) {
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
	
	
	
	public static void initTransmutationRecipes() {		
		/* Initialize constructive/destructive recipes */
		
		/* 4 Cobble <-> 1 Flint */
		addTwoWayRecipe(Block.cobblestone, 4, Item.flint, 1);
		
		/* 4 Dirt/Sand -> 1 Gravel, 1 Gravel -> 4 Dirt */
		addTwoWayRecipe(Block.dirt, 4, Block.gravel, 1);
		addOneWayRecipe(Block.sand, 4, Block.gravel, 1);
		
		/* 2 Sticks -> Wood Plank */
		addOneWayRecipe(Item.stick, 2, planks(0));
		
		/* 4 Wood Planks -> Wood Block */
		for(int i = 0; i <= 3; i++)
			addOneWayRecipe(planks(i), 1, wood(i));
		
		/* 4 Gravel/Sandstone/Flint -> 1 Clay Ball, 1 Clay Ball -> 4 Gravel */
		addTwoWayRecipe(Block.gravel, 4, Item.clay, 1);
		addOneWayRecipe(Block.sandStone, 4, Item.clay, 1);
		addOneWayRecipe(Item.flint, 4, Item.clay, 1);
		
		/* 2 Wood Log <-> 1 Obsidian */
		addTwoWayRecipe(anyWood, 2, Block.obsidian, 1);
		
		/* 4 Obsidian/Clay Block -> 1 Iron Ingot, Iron Ingot -> Clay Block */
		addTwoWayRecipe(Block.blockClay, 4, Item.ingotIron, 1);
		addOneWayRecipe(Block.obsidian, 4, Item.ingotIron, 1);
		
		/* 8 Iron Ingot <-> 1 Gold Ingot */
		addTwoWayRecipe(Item.ingotIron, 8, Item.ingotGold, 1);
		
		/* 4 Gold Ingot <-> 1 Diamond */
		addTwoWayRecipe(Item.ingotGold, 4, Item.diamond, 1);
		
		/* 8 Iron Block <-> 1 Gold Block */
		addTwoWayRecipe(Block.blockSteel, 8, Block.blockGold, 1);
		
		/* 4 Gold Block <-> 1 Diamond Block */
		addTwoWayRecipe(Block.blockGold, 4, Block.blockDiamond, 1);
		
		/* 1 Ender Pearl <-> 4 Iron Ingot */
		addTwoWayRecipe(Item.ingotIron, 4, Item.enderPearl, 1);
	}
	
	public static void initEquivalencyRecipes() {
		/* Initialize meta-cycling recipes and other cycles first */
		
		/* Wood Plank Cycle */
		addMetaCycleRecipe(Block.planks, 4);
		
		/* Wood Log Cycle */
		addMetaCycleRecipe(Block.wood, 4);
		
		/* Sapling Cycle */
		addMetaCycleRecipe(Block.sapling, 4);
		
		/* Leaf Cycle */
		addMetaCycleRecipe(Block.leaves, 4);
		
		/* Tallgrass Cycle */
		addMetaCycleRecipe(Block.tallGrass, 3);
		
		/* Wool Cycle */
		addMetaCycleRecipe(Block.cloth, 16);
		
		/* Dirt -> Cobble -> Sand -> Dirt */
		addOneWayRecipe(Block.dirt, Block.cobblestone);
		addOneWayRecipe(Block.cobblestone, Block.sand);
		addOneWayRecipe(Block.sand, Block.dirt);
		
		/* 2 Gravel -> 2 Flint -> 2 Sandstone (Cycles) -> 2 Gravel*/
		addOneWayRecipe(Block.gravel, 2, Item.flint, 2);
		addOneWayRecipe(Item.flint, 2, sandStone(2, 0), 2);
		addOneWayRecipe(sandStone(0), 2, sandStone(1), 2);
		addOneWayRecipe(sandStone(1), 2, sandStone(2), 2);
		addOneWayRecipe(sandStone(2), 2, Block.gravel, 2);	
		
		/* Flower Equivalence Recipes */
		addTwoWayRecipe(Block.plantYellow, Block.plantRed);
		// RP2 flower recipe goes here, it SHOULD make them cycle instead of two-way if RP2 is present
		
		/* Mushroom Equivalence Recipes */
		addTwoWayRecipe(Block.mushroomBrown, Block.mushroomRed);
		
		/*           Books/                              */
		/* Reeds <-> Paper <-> Sugar Equivalence Recipes */
		addOneWayRecipe(Item.book, 1, Item.paper, 3);
		addOneWayRecipe(Item.paper, 3, Item.reed, 3);
		addOneWayRecipe(Item.sugar, Item.reed);
		
		/* Melon <-> Pumpkin Equivalence Recipes */
		addTwoWayRecipe(Item.pumpkinSeeds, Item.melonSeeds);
		addTwoWayRecipe(Block.pumpkin, Block.melon);		
	}
	
	public static void initReconstructiveRecipes() {
		/* 3 Bone Meal --> 1 Bone */
		addOneWayRecipe(boneMeal, 3, new ItemStack(Item.bone, 1));
		
		/* 2 Blaze Powder --> 1 Blaze Rod */
		addOneWayRecipe(Item.blazePowder, 2, new ItemStack(Item.blazeRod, 1));
	}
	
	public static void initDestructorRecipes() {
		/* Clay Block -> 4 Clay Balls */
		addOneWayRecipe(Block.blockClay, 1, new ItemStack(Item.clay, 4));
		
		/* Smooth Stone -> Cobble Stone */
		addOneWayRecipe(Block.stone, Block.cobblestone);
		
		/* Glass -> Sand */
		addOneWayRecipe(Block.glass, Block.sand);
		
		/* Glowstone Block -> 4 Glowstone Dust */
		addOneWayRecipe(Block.glowStone, 1, new ItemStack(Item.lightStoneDust, 4));
		
		/* Brick Block -> 4 Bricks */
		addOneWayRecipe(Block.brick, 1, new ItemStack(Item.brick, 4));
	}
	
	public static void initPortableSmeltingRecipes() {
		/* Smelt cobblestone */
		addSmeltingRecipe(Block.cobblestone);

		/* Smelt any wood */
		addSmeltingRecipe(anyWood);

		/* Smelt iron ore */
		addSmeltingRecipe(Block.oreIron);
		
		/* Smelt gold ore */
		addSmeltingRecipe(Block.oreGold);
		
		/* Smelt sand */
		addSmeltingRecipe(Block.sand);
		
		/* Cook chicken */
		addSmeltingRecipe(Item.chickenRaw);
		
		/* Cook pork */
		addSmeltingRecipe(Item.porkRaw);
		
		/* Cook beef */
		addSmeltingRecipe(Item.beefRaw);
		
		/* Cook fish */
		addSmeltingRecipe(Item.fishRaw);		
	}
}

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
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ShapedRecipes;
import net.minecraft.src.ShapelessRecipes;

public class RecipesPhilStone {

	public static ItemStack philStone = new ItemStack(ModItems.philStone, 1, -1);
	
	private static ItemStack anyCoal = new ItemStack(Item.coal, 1, -1);
	
	private static ItemStack oakWood = new ItemStack(Block.wood, 1, 0);
	private static ItemStack birchWood = new ItemStack(Block.wood, 1, 1);
	private static ItemStack pineWood = new ItemStack(Block.wood, 1, 2);
	private static ItemStack jungleWood = new ItemStack(Block.wood, 1, 3);
	private static ItemStack anyWood = new ItemStack(Block.wood, 1, -1);
	private static ItemStack planks(int i) { return new ItemStack(Block.planks, 1, i); }
	private static ItemStack wood(int i) { return new ItemStack(Block.wood, 1, i); }
	
	private static ItemStack boneMeal = new ItemStack(Item.dyePowder, 1, 15);
	
	public static void initRecipes() {
		determineBaseMaterials();
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
		
		Iterator<IRecipe> recipeIter = recipeList.iterator();

		while (recipeIter.hasNext()) {
			recipe = recipeIter.next();
			
			if (recipe instanceof ShapedRecipes) {
				System.out.println("Shaped");
			}
			else if (recipe instanceof ShapelessRecipes) {
				System.out.println("Shapeless");
			}
		}
	}
	
	/* Just a speed-loader for a linear recipes requiring the PStone */
	/* Give it an input Item/Block/ItemStack, the number inputs required, and an output ItemStack */
	public static void addOneWayRecipe(Object input, int n, ItemStack output) {
		if(!(input instanceof Item || input instanceof Block || input instanceof ItemStack))
			return;
		Object[] list = new Object[n];
		list[0] = philStone;
		for(int i = 1; i <= n; i++)
			list[i] = input;
		ModLoader.addShapelessRecipe(output, list);
	}
	
	/* Adds a recipe AND its exact reversal */
	public static void addTwoWayRecipe(Object input, int n, ItemStack output) {
		/* Adds the one-way recipe */
		addOneWayRecipe(input, n, output);
		
		/* Reverses the recipe by making a list of the output */
		Object[] list = new Object[output.stackSize];
		for(int i = 0; i < output.stackSize; i++)
			list[i] = new ItemStack(output.itemID, 1, output.getItemDamage());
		
		/* Checks the type of the input so it can make a proper ItemStack */
		if(input instanceof Item)
			ModLoader.addShapelessRecipe(new ItemStack((Item)input, n), list);
		else if(input instanceof Block)
			ModLoader.addShapelessRecipe(new ItemStack((Block)input, n), list);
		else if(input instanceof ItemStack) {
			ItemStack ist = new ItemStack(((ItemStack)input).itemID, n, ((ItemStack)input).getItemDamage());
			ModLoader.addShapelessRecipe(ist, list);
		}
	}
	
	/* Pass this a Block, Item or ItemStack and the maximum (desired) meta, including zero */
	public static void addMetaCycleRecipe(Object input, int n) {
		/* Makes a single item cycle through its meta values when it's crafted with a PStone */
		for(int i = 0; i <= n; i++) {
			if(input instanceof Block)
				ModLoader.addShapelessRecipe(new ItemStack((Block)input, 1, (i == n - 1 ? 0 : i + 1)), new Object[] {
					new ItemStack((Block)input, 1, i)
				});
			else if (input instanceof Item)
				ModLoader.addShapelessRecipe(new ItemStack((Item)input, 1, (i == n - 1 ? 0 : i + 1)), new Object[] {
					new ItemStack((Item)input, 1, i)
				});
			else if (input instanceof ItemStack) {				
				ModLoader.addShapelessRecipe(new ItemStack(((ItemStack)input).itemID, 1, (i == n - 1 ? 0 : i + 1)), new Object[] {
					new ItemStack(((ItemStack)input).itemID, 1, i)
				});
			}
				
		}
	}
	
	
	
	public static void initTransmutationRecipes() {
		/* Initialize meta-cycling recipes and other cycles first */
		
		/* Wood Plank Cycle */
		addMetaCycleRecipe(Block.planks, 3);
		
		/* Wood Log Cycle */
		addMetaCycleRecipe(Block.wood, 3);
		
		/* Sapling Cycle */
		addMetaCycleRecipe(Block.sapling, 3);
		
		/* Leaf Cycle */
		addMetaCycleRecipe(Block.leaves, 3);
		
		/* Wool Cycle */
		addMetaCycleRecipe(Block.cloth, 15);
		
		/* Dirt -> Cobble -> Sand -> Dirt */
		addOneWayRecipe(Block.dirt, 1, new ItemStack(Block.cobblestone, 1));
		addOneWayRecipe(Block.cobblestone, 1, new ItemStack(Block.sand, 1));
		addOneWayRecipe(Block.sand, 1, new ItemStack(Block.dirt, 1));
		
		/* 2 Gravel -> 2 Flint -> 2 Sandstone -> 2 Gravel*/
		addOneWayRecipe(Block.gravel, 2, new ItemStack(Item.flint, 2));
		addOneWayRecipe(Item.flint, 2, new ItemStack(Block.sandStone, 2));
		addOneWayRecipe(Block.sandStone, 2, new ItemStack(Block.gravel, 2));		
		
		/* Initialize constructive/destructive recipes */
		
		/* 4 Cobble <-> 1 Flint */
		addTwoWayRecipe(Block.cobblestone, 4, new ItemStack(Item.flint, 1));
		
		/* 4 Dirt/Sand -> 1 Gravel, 1 Gravel -> 4 Dirt */
		addTwoWayRecipe(Block.dirt, 4, new ItemStack(Block.gravel, 1));
		addOneWayRecipe(Block.sand, 4, new ItemStack(Block.gravel, 1));
		
		/* 2 Sticks -> Wood Plank */
		addOneWayRecipe(Item.stick, 2, planks(0));
		
		/* 4 Wood Planks -> Wood Block */
		for(int i = 0; i <= 3; i++)
			addOneWayRecipe(planks(i), 1, wood(i));
		
		/* 4 Gravel/Sandstone/Flint -> 1 Clay Ball, 1 Clay Ball -> 4 Gravel */
		addTwoWayRecipe(Block.gravel, 4, new ItemStack(Item.clay, 1));
		addOneWayRecipe(Block.sandStone, 4, new ItemStack(Item.clay, 1));
		addOneWayRecipe(Item.flint, 4, new ItemStack(Item.clay, 1));
		
		/* 2 Wood Log <-> 1 Obsidian */
		addTwoWayRecipe(anyWood, 2, new ItemStack(Block.obsidian, 1));
		
		/* 4 Obsidian/Clay Block -> 1 Iron Ingot, Iron Ingot -> Clay Block */
		addTwoWayRecipe(Block.blockClay, 4, new ItemStack(Item.ingotIron, 1));
		addOneWayRecipe(Block.obsidian, 4, new ItemStack(Item.ingotIron, 1));
		
		/* 8 Iron Ingot <-> 1 Gold Ingot */
		addTwoWayRecipe(Item.ingotIron, 8, new ItemStack(Item.ingotGold, 1));
		
		/* 4 Gold Ingot <-> 1 Diamond */
		addTwoWayRecipe(Item.ingotGold, 4, new ItemStack(Item.diamond, 1));
		
		/* 8 Iron Block <-> 1 Gold Block */
		addTwoWayRecipe(Block.blockSteel, 8, new ItemStack(Block.blockGold, 1));
		
		/* 4 Gold Block <-> 1 Diamond Block */
		addTwoWayRecipe(Block.blockGold, 4, new ItemStack(Block.blockDiamond, 1));
		
		/* 1 Ender Pearl <-> 4 Iron Ingot */
		addTwoWayRecipe(Item.ingotIron, 4, new ItemStack(Item.enderPearl, 1));
	}
	
	public static void initEquivalencyRecipes() {
		/* Dirt <-> Cobble <-> Sand Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Block.cobblestone, 1), new Object[] {
			philStone, Block.dirt
		});		
		ModLoader.addShapelessRecipe(new ItemStack(Block.sand, 1), new Object[] {
			philStone, Block.cobblestone
		});		
		ModLoader.addShapelessRecipe(new ItemStack(Block.dirt, 1), new Object[] {
			philStone, Block.sand
		});
		
		/* Sapling Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Block.sapling, 1, 1), new Object[] {
			philStone, new ItemStack(Block.sapling, 1, 0)
		});		
		ModLoader.addShapelessRecipe(new ItemStack(Block.sapling, 1, 2), new Object[] {
			philStone, new ItemStack(Block.sapling, 1, 1)
		});		
		ModLoader.addShapelessRecipe(new ItemStack(Block.sapling, 1, 3), new Object[] {
			philStone, new ItemStack(Block.sapling, 1, 2)
		});		
		ModLoader.addShapelessRecipe(new ItemStack(Block.sapling, 1, 0), new Object[] {
			philStone, new ItemStack(Block.sapling, 1, 3)
		});
		
		/* Leaf Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Block.leaves, 1, 1), new Object[] {
			philStone, new ItemStack(Block.leaves, 1, 0)
		});		
		ModLoader.addShapelessRecipe(new ItemStack(Block.leaves, 1, 2), new Object[] {
			philStone, new ItemStack(Block.leaves, 1, 1)
		});		
		ModLoader.addShapelessRecipe(new ItemStack(Block.leaves, 1, 3), new Object[] {
			philStone, new ItemStack(Block.leaves, 1, 2)
		});		
		ModLoader.addShapelessRecipe(new ItemStack(Block.leaves, 1, 0), new Object[] {
			philStone, new ItemStack(Block.leaves, 1, 3)
		});
		
		/* Wood Log Equivalence Recipes */
		ModLoader.addShapelessRecipe(birchWood, new Object[] {
			philStone, oakWood
		});
		ModLoader.addShapelessRecipe(pineWood, new Object[] {
			philStone, birchWood
		});
		ModLoader.addShapelessRecipe(jungleWood, new Object[] {
			philStone, pineWood
		});
		ModLoader.addShapelessRecipe(oakWood, new Object[] {
			philStone, jungleWood
		});
		
		/* Wood Plank Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Block.planks, 1, 1), new Object[] {
			philStone, new ItemStack(Block.planks, 1, 0)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.planks, 1, 2), new Object[] {
			philStone, new ItemStack(Block.planks, 1, 1)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.planks, 1, 3), new Object[] {
			philStone, new ItemStack(Block.planks, 1, 2)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.planks, 1, 0), new Object[] {
			philStone, new ItemStack(Block.planks, 1, 3)
		});
		
		/* Flower Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Block.plantRed, 1), new Object[] {
			philStone, Block.plantYellow
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.plantYellow, 1), new Object[] {
			philStone, Block.plantRed
		});
		// RP2 flower recipe goes here
		
		/* Mushroom Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Block.mushroomBrown, 1), new Object[] {
			philStone, Block.mushroomRed
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.mushroomRed, 1), new Object[] {
			philStone, Block.mushroomBrown
		});
		
		/* Gravel <-> Sandstone <-> Fancy Sandstone I <-> Fancy Sandstone II Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Block.sandStone, 1, 0), new Object[] {
			philStone, new ItemStack(Block.gravel, 1)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.sandStone, 1, 1), new Object[] {
			philStone, new ItemStack(Block.sandStone, 1, 0)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.sandStone, 1, 2), new Object[] {
			philStone, new ItemStack(Block.sandStone, 1, 1)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.gravel, 1), new Object[] {
			philStone, new ItemStack(Block.sandStone, 1, 2)
		});
		
		/* Reeds <-> Paper <-> Sugar Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Item.reed, 3), new Object[] {
			philStone, Item.paper, Item.paper, Item.paper 
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.reed, 1), new Object[] {
			philStone, Item.sugar
		});
		
		/* Melon <-> Pumpkin Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Item.pumpkinSeeds, 1), new Object[] {
			philStone, Item.melonSeeds
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.melonSeeds, 1), new Object[] {
			philStone, Item.pumpkinSeeds
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.pumpkin, 1), new Object[] {
			philStone, Block.melon
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.melon, 1), new Object[] {
			philStone, Block.pumpkin
		});
		
		/* Tall Grass Equivalence Recipes */
		ModLoader.addShapelessRecipe(new ItemStack(Block.tallGrass, 1, 1), new Object[] {
			philStone, new ItemStack(Block.tallGrass, 1, 0)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.tallGrass, 1, 2), new Object[] {
			philStone, new ItemStack(Block.tallGrass, 1, 1)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.tallGrass, 1, 0), new Object[] {
			philStone, new ItemStack(Block.tallGrass, 1, 2)
		});
	}
	
	public static void initReconstructiveRecipes() {
		/* 3 Bone Meal <-> 1 Bone */
		ModLoader.addShapelessRecipe(new ItemStack(Item.bone, 1), new Object[] {
			philStone, boneMeal, boneMeal, boneMeal
		});
		
		/* 2 Blaze Powder <-> 1 Blaze Rod */
		ModLoader.addShapelessRecipe(new ItemStack(Item.blazeRod, 1), new Object[] {
			philStone, Item.blazePowder, Item.blazePowder
		});
	}
	
	public static void initDestructorRecipes() {
		/* Clay Block -> 4 Clay Balls */
		ModLoader.addShapelessRecipe(new ItemStack(Item.clay, 4), new Object[] {
			philStone, Block.blockClay
		});
		
		/* Smooth Stone -> Cobble Stone */
		ModLoader.addShapelessRecipe(new ItemStack(Block.cobblestone, 1), new Object[] {
			philStone, Block.stone
		});
		
		/* Glass -> Sand */
		ModLoader.addShapelessRecipe(new ItemStack(Block.sand, 1), new Object[] {
			philStone, Block.glass
		});
		
		/* Glowstone Block -> 4 Glowstone Dust */
		ModLoader.addShapelessRecipe(new ItemStack(Item.lightStoneDust, 4), new Object[] {
			philStone, Block.glowStone
		});
		
		/* Brick Block -> 4 Bricks */
		ModLoader.addShapelessRecipe(new ItemStack(Item.brick, 4), new Object[] {
			philStone, Block.brick
		});		
	}
	
	public static void initPortableSmeltingRecipes() {
		/* 7 Cobble + 1 Coal|Charcoal = 7 Stone */
		ModLoader.addShapelessRecipe(new ItemStack(Block.stone, 7), new Object[] {
			philStone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, anyCoal
		});
		
		/* 7 Wood Logs + 1 Coal|Charcoal = 7 Charcoal */
		ModLoader.addShapelessRecipe(new ItemStack(Item.coal, 7, 1), new Object[] {
			philStone, anyCoal, anyWood, anyWood, anyWood, anyWood, anyWood, anyWood, anyWood, 
		});
		
		/* 7 Iron Ore + 1 Coal|Charcoal = 7 Iron Ingot */
		ModLoader.addShapelessRecipe(new ItemStack(Item.ingotIron, 7), new Object[] {
			philStone, anyCoal, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron
		});
		
		/* 7 Gold Ore + 1 Coal|Charcoal = 7 Gold Ingot */
		ModLoader.addShapelessRecipe(new ItemStack(Item.ingotGold, 7), new Object[] {
			philStone, anyCoal, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold
		});
		
		/* 7 Sand + 1 Coal|Charcoal = 7 Glass */
		ModLoader.addShapelessRecipe(new ItemStack(Block.glass, 7), new Object[] {
			philStone, anyCoal, Block.sand, Block.sand, Block.sand, Block.sand, Block.sand, Block.sand, Block.sand
		});
		
		/* 7 Raw Chicken + 1 Coal|Charcoal = 7 Cooked Chicken */
		ModLoader.addShapelessRecipe(new ItemStack(Item.chickenCooked, 7), new Object[] {
			philStone, anyCoal, Item.chickenRaw, Item.chickenRaw, Item.chickenRaw, Item.chickenRaw, Item.chickenRaw, Item.chickenRaw, Item.chickenRaw
		});
		
		/* 7 Raw Pork + 1 Coal|Charcoal = 7 Cooked Pork */
		ModLoader.addShapelessRecipe(new ItemStack(Item.porkCooked, 7), new Object[] {
			philStone, anyCoal, Item.porkRaw, Item.porkRaw, Item.porkRaw, Item.porkRaw, Item.porkRaw, Item.porkRaw, Item.porkRaw
		});
		
		/* 7 Raw Beef + 1 Coal|Charcoal = 7 Cooked Pork */
		ModLoader.addShapelessRecipe(new ItemStack(Item.beefCooked, 7), new Object[] {
			philStone, anyCoal, Item.beefRaw, Item.beefRaw, Item.beefRaw, Item.beefRaw, Item.beefRaw, Item.beefRaw, Item.beefRaw
		});
	}
}

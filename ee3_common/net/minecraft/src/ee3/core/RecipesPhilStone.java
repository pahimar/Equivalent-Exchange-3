package net.minecraft.src.ee3.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ShapedRecipes;
import net.minecraft.src.ShapelessRecipes;
import net.minecraft.src.ee3.item.ModItems;
import net.minecraft.src.ee3.item.ItemPhilosopherStone;

public class RecipesPhilStone {

	public static ItemStack philStone = new ItemStack(ModItems.philStone, 1, -1);
	
	private static ItemStack coal = new ItemStack(Item.coal, 1, 0);
	private static ItemStack charcoal = new ItemStack(Item.coal, 1, 1);
	
	private static ItemStack oakWood = new ItemStack(Block.wood, 1, 0);
	private static ItemStack birchWood = new ItemStack(Block.wood, 1, 1);
	private static ItemStack pineWood = new ItemStack(Block.wood, 1, 2);
	private static ItemStack jungleWood = new ItemStack(Block.wood, 1, 3);
	
	public static void initRecipes() {
		determineBaseMaterials();
		//initEquivalencyRecipes();
		//initDestructorRecipes();
		//initPortableSmeltingRecipes();
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
			
			if (recipe instanceof ShapedRecipes)
				System.out.println("Shaped");
			else if (recipe instanceof ShapelessRecipes)
				System.out.println("Shapeless");
		}
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
		ModLoader.addShapelessRecipe(new ItemStack(Block.wood, 1, 1), new Object[] {
			philStone, new ItemStack(Block.wood, 1, 0)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.wood, 1, 2), new Object[] {
			philStone, new ItemStack(Block.wood, 1, 1)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.wood, 1, 3), new Object[] {
			philStone, new ItemStack(Block.wood, 1, 2)
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.wood, 1, 0), new Object[] {
			philStone, new ItemStack(Block.wood, 1, 3)
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
	
	public static void initDestructorRecipes() {
		/* Clay Block -> 4 Clay Balls */
		//ModLoader.addShapelessRecipe(new ItemStack(Item.clay, 4), new Object[] {
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
			philStone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, coal
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.stone, 7), new Object[] {
			philStone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, charcoal
		});
		
		/* 7 Wood Logs + 1 Coal|Charcoal = 7 Charcoal */
		ModLoader.addShapelessRecipe(new ItemStack(Item.coal, 7, 1), new Object[] {
			philStone, coal, oakWood, oakWood, oakWood, oakWood, oakWood, oakWood, oakWood
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.coal, 7, 1), new Object[] {
			philStone, charcoal, oakWood, oakWood, oakWood, oakWood, oakWood, oakWood, oakWood
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.coal, 7, 1), new Object[] {
			philStone, coal, birchWood, birchWood, birchWood, birchWood, birchWood, birchWood, birchWood
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.coal, 7, 1), new Object[] {
			philStone, charcoal, birchWood, birchWood, birchWood, birchWood, birchWood, birchWood, birchWood
		});		
		ModLoader.addShapelessRecipe(new ItemStack(Item.coal, 7, 1), new Object[] {
			philStone, coal, pineWood, pineWood, pineWood, pineWood, pineWood, pineWood, pineWood
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.coal, 7, 1), new Object[] {
			philStone, charcoal, pineWood, pineWood, pineWood, pineWood, pineWood, pineWood, pineWood
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.coal, 7, 1), new Object[] {
			philStone, coal, jungleWood, jungleWood, jungleWood, jungleWood, jungleWood, jungleWood, jungleWood
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.coal, 7, 1), new Object[] {
			philStone, charcoal, jungleWood, jungleWood, jungleWood, jungleWood, jungleWood, jungleWood, jungleWood
		});
		
		/* 7 Iron Ore + 1 Coal|Charcoal = 7 Iron Ingot */
		ModLoader.addShapelessRecipe(new ItemStack(Item.ingotIron, 7), new Object[] {
			philStone, coal, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.ingotIron, 7), new Object[] {
			philStone, charcoal, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron, Block.oreIron
		});
		
		/* 7 Gold Ore + 1 Coal|Charcoal = 7 Gold Ingot */
		ModLoader.addShapelessRecipe(new ItemStack(Item.ingotGold, 7), new Object[] {
			philStone, coal, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold
		});
		ModLoader.addShapelessRecipe(new ItemStack(Item.ingotGold, 7), new Object[] {
			philStone, charcoal, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold, Block.oreGold
		});
		
		/* 7 Sand + 1 Coal|Charcoal = 7 Glass */
		ModLoader.addShapelessRecipe(new ItemStack(Block.glass, 7), new Object[] {
			philStone, coal, Block.sand, Block.sand, Block.sand, Block.sand, Block.sand, Block.sand, Block.sand
		});
		ModLoader.addShapelessRecipe(new ItemStack(Block.glass, 7), new Object[] {
			philStone, charcoal, Block.sand, Block.sand, Block.sand, Block.sand, Block.sand, Block.sand, Block.sand
		});
	}
}

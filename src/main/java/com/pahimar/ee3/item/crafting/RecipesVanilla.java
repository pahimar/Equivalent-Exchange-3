package com.pahimar.ee3.item.crafting;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.helper.CraftingHelper;
import com.pahimar.ee3.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesVanilla
{
    public static void init()
    {
        initBlockRecipes();
        initItemRecipes();
    }

    private static void initBlockRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalFuel, 1, 0), new Object[]{"iii", "iii", "iii", 'i', new ItemStack(ModItems.alchemicalFuel, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalFuel, 1, 1), new Object[]{"iii", "iii", "iii", 'i', new ItemStack(ModItems.alchemicalFuel, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalFuel, 1, 2), new Object[]{"iii", "iii", "iii", 'i', new ItemStack(ModItems.alchemicalFuel, 1, 2)});

        GameRegistry.addRecipe(new ItemStack(ModBlocks.chalk), new Object[]{"bcb", "cbc", "bcb", 'b', new ItemStack(Item.dyePowder.itemID, 1, 15), 'c', new ItemStack(Item.clay)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.chalk), new Object[]{"cc", "cc", 'c', new ItemStack(ModItems.chalk)});

        GameRegistry.addRecipe(new ItemStack(ModBlocks.glassBell), new Object[]{"iii", "i i", "i i", 'i', Block.glass});

        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.infusedPlanks, 4, 0), new ItemStack(ModBlocks.infusedWood, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.infusedPlanks, 4, 1), new ItemStack(ModBlocks.infusedWood, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.infusedPlanks, 4, 2), new ItemStack(ModBlocks.infusedWood, 1, 2));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 0), new Object[]{"ppp", "p p", "ppp", 'p', new ItemStack(ModBlocks.infusedPlanks, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 1), new Object[]{"ppp", "p p", "ppp", 'p', new ItemStack(ModBlocks.infusedPlanks, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest.blockID, 1, 2), new Object[]{"ppp", "p p", "ppp", 'p', new ItemStack(ModBlocks.infusedPlanks, 1, 2)});

        CraftingHelper.addShapedOreRecipe(new ItemStack(ModBlocks.calcinator), new Object[]{"i i", "sis", "s s", 'i', Item.ingotIron, 's', "stone"});
        CraftingHelper.addShapedOreRecipe(new ItemStack(ModBlocks.aludelBase), new Object[]{"iii", "sis", "iii", 'i', Item.ingotIron, 's', "stone"});
        CraftingHelper.addShapedOreRecipe(new ItemStack(ModBlocks.researchStation), new Object[]{"ipi", " s ", "sss", 'p', "plankWood", 'i', Item.ingotIron, 's', "stone"});
    }

    private static void initItemRecipes()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 0), new ItemStack(ModBlocks.alchemicalFuel, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 1), new ItemStack(ModBlocks.alchemicalFuel, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 2), new ItemStack(ModBlocks.alchemicalFuel, 1, 2));

        GameRegistry.addRecipe(new ItemStack(ModItems.inertStone), new Object[]{"sis", "igi", "sis", 's', Block.stone, 'i', Item.ingotIron, 'g', Item.ingotGold});

        // TODO Proper recipes for the different bag sizes
        // TODO Also, decide if we want different sizes, and appropriate recipes
        GameRegistry.addRecipe(new ItemStack(ModItems.alchemicalBag), new Object[]{"ccc", "c c", "ccc", 'c', new ItemStack(ModBlocks.infusedCloth, 1, 2)});

        CraftingHelper.addShapedOreRecipe(new ItemStack(ModItems.diviningRod), new Object[]{" s ", " s ", "s s", 's', "stickWood"});
    }
}

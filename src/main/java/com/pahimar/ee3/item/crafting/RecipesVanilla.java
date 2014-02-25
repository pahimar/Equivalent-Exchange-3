package com.pahimar.ee3.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.helper.CraftingHelper;
import com.pahimar.ee3.item.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;

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

        GameRegistry.addRecipe(new ItemStack(ModBlocks.chalk), new Object[]{"bcb", "cbc", "bcb", 'b', new ItemStack(Items.dye, 1, 15), 'c', new ItemStack(Items.clay_ball)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.chalk), new Object[]{"cc", "cc", 'c', new ItemStack(ModItems.chalk)});

        GameRegistry.addRecipe(new ItemStack(ModBlocks.glassBell), new Object[]{"iii", "i i", "i i", 'i', Blocks.glass});

        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.infusedPlanks, 4, 0), new ItemStack(ModBlocks.infusedWood, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.infusedPlanks, 4, 1), new ItemStack(ModBlocks.infusedWood, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.infusedPlanks, 4, 2), new ItemStack(ModBlocks.infusedWood, 1, 2));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 0), new Object[]{"ppp", "p p", "ppp", 'p', new ItemStack(ModBlocks.infusedPlanks, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 1), new Object[]{"ppp", "p p", "ppp", 'p', new ItemStack(ModBlocks.infusedPlanks, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalChest, 1, 2), new Object[]{"ppp", "p p", "ppp", 'p', new ItemStack(ModBlocks.infusedPlanks, 1, 2)});

        CraftingHelper.addShapedOreRecipe(new ItemStack(ModBlocks.calcinator), new Object[]{"i i", "sis", "s s", 'i', Items.iron_ingot, 's', "stone"});
        CraftingHelper.addShapedOreRecipe(new ItemStack(ModBlocks.aludelBase), new Object[]{"iii", "sis", "iii", 'i', Items.iron_ingot, 's', "stone"});
        CraftingHelper.addShapedOreRecipe(new ItemStack(ModBlocks.researchStation), new Object[]{"ipi", " s ", "sss", 'p', "plankWood", 'i', Items.iron_ingot, 's', "stone"});
    }

    private static void initItemRecipes()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 0), new ItemStack(ModBlocks.alchemicalFuel, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 1), new ItemStack(ModBlocks.alchemicalFuel, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 2), new ItemStack(ModBlocks.alchemicalFuel, 1, 2));

        GameRegistry.addRecipe(new ItemStack(ModItems.inertStone), new Object[]{"sis", "igi", "sis", 's', Blocks.stone, 'i', Items.iron_ingot, 'g', Items.gold_ingot});

        GameRegistry.addRecipe(new ItemStack(ModItems.alchemicalBag, 1, 0), new Object[]{"ccc", "cdc", "ccc", 'c', new ItemStack(ModBlocks.infusedCloth, 1, 0), 'd', new ItemStack(ModItems.alchemicalDust, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ModItems.alchemicalBag, 1, 1), new Object[]{"ccc", "cdc", "ccc", 'c', new ItemStack(ModBlocks.infusedCloth, 1, 1), 'd', new ItemStack(ModItems.alchemicalDust, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(ModItems.alchemicalBag, 1, 2), new Object[]{"ccc", "cdc", "ccc", 'c', new ItemStack(ModBlocks.infusedCloth, 1, 2), 'd', new ItemStack(ModItems.alchemicalDust, 1, 3)});

        CraftingHelper.addShapedOreRecipe(new ItemStack(ModItems.diviningRod), new Object[]{" s ", " s ", "s s", 's', "stickWood"});
    }
}

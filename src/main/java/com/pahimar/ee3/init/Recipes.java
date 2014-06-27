package com.pahimar.ee3.init;

import com.pahimar.ee3.util.CraftingHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes
{
    public static void init()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 0), "fff", "fff", "fff", 'f', new ItemStack(ModItems.alchemicalFuel, 1, 0));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 1), "fff", "fff", "fff", 'f', new ItemStack(ModItems.alchemicalFuel, 1, 1));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 2), "fff", "fff", "fff", 'f', new ItemStack(ModItems.alchemicalFuel, 1, 2));

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 0), new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 1), new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 2), new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 2));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.chalkBlock), "bcb", "cbc", "bcb", 'b', new ItemStack(Items.dye, 1, 15), 'c', new ItemStack(Items.clay_ball));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.chalkBlock), "cc", "cc", 'c', new ItemStack(ModItems.chalk));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.glassBell), "ggg", "g g", "g g", 'g', Blocks.glass);
        CraftingHelper.addShapedOreRecipe(new ItemStack(ModBlocks.calcinator), "i i", "sis", "s s", 'i', "ingotIron", 's', Blocks.stone);
        CraftingHelper.addShapedOreRecipe(new ItemStack(ModBlocks.aludel), "iii", "sis", "iii", 'i', "ingotIron", 's', Blocks.stone);
        CraftingHelper.addShapedOreRecipe(new ItemStack(ModBlocks.researchStation), "ipi", " s ", "sss", 'i', "ingotIron", 's', Blocks.stone, 'p', "slabWood");

        CraftingHelper.addShapedOreRecipe(new ItemStack(ModItems.stoneInert), "sis", "igi", "sis", 's', Blocks.stone, 'i', "ingotIron", 'g', "ingotGold");

        CraftingHelper.addShapedOreRecipe(new ItemStack(ModItems.diviningRod), " s ", " s ", "s s", 's', "stickWood");
    }
}

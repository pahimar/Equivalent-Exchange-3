package com.pahimar.ee.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {

    public static void init() {
        initModRecipes();
        initAludelRecipes();
    }

    private static void initModRecipes() {

        // Alchemical Fuel (Item) ---> Alchemical Fuel (Block)
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.ALCHEMICAL_FUEL, 1, 0), "fff", "fff", "fff", 'f', new ItemStack(ModItems.ALCHEMICAL_FUEL, 1, 0));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.ALCHEMICAL_FUEL, 1, 1), "fff", "fff", "fff", 'f', new ItemStack(ModItems.ALCHEMICAL_FUEL, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.ALCHEMICAL_FUEL, 1, 2), "fff", "fff", "fff", 'f', new ItemStack(ModItems.ALCHEMICAL_FUEL, 1, 2));

        // Alchemical Fuel (Block) ---> Alchemical Fuel (Item)
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ALCHEMICAL_FUEL, 9, 0), new ItemStack(ModBlocks.ALCHEMICAL_FUEL, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ALCHEMICAL_FUEL, 9, 1), new ItemStack(ModBlocks.ALCHEMICAL_FUEL, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ALCHEMICAL_FUEL, 9, 2), new ItemStack(ModBlocks.ALCHEMICAL_FUEL, 1, 2));
    }

    private static void initAludelRecipes() {
    }
}

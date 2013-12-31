package com.pahimar.ee3.item.crafting;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipesEquivalentExchange
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
        GameRegistry.addRecipe(new ItemStack(ModBlocks.aludelBase), new Object[]{"iii", "sis", "iii", 'i', Item.ingotIron, 's', Block.stone});
    }

    private static void initItemRecipes()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 0), new ItemStack(ModBlocks.alchemicalFuel, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 1), new ItemStack(ModBlocks.alchemicalFuel, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 2), new ItemStack(ModBlocks.alchemicalFuel, 1, 2));

        GameRegistry.addRecipe(new ItemStack(ModItems.inertStone), new Object[]{"sis", "igi", "sis", 's', Block.stone, 'i', Item.ingotIron, 'g', Item.ingotGold});
        GameRegistry.addRecipe(new ItemStack(ModItems.miniumStone), new Object[]{"sss", "sis", "sss", 's', ModItems.miniumShard, 'i', ModItems.inertStone});
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.diviningRod), new Object[]{" s ", " s ", "s s", 's', Item.stick}));
    }
}

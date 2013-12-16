package com.pahimar.ee3.core.handler.addon;

import com.pahimar.ee3.api.RecipeMapping;
import com.pahimar.ee3.imc.InterModCommsOperations;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Equivalent-Exchange-3
 * <p/>
 * AddonVanilla
 *
 * @author pahimar
 */
public class AddonRecipes
{

    public static void init()
    {
        // TODO Work on more recipe mappings
        sendAddRecipe(new OreStack("dustBronze"), new OreStack("dustTin"), new OreStack("dustCopper", 3));
        sendAddRecipe(new OreStack("dustGold"), Item.ingotGold);
        sendAddRecipe(new OreStack("dustDiamond"), Item.diamond);
        sendAddRecipe(new OreStack("dustObsidian"), Block.obsidian);
        sendAddRecipe(new OreStack("dustClay"), new ItemStack(Item.coal, 1, 0));
        sendAddRecipe(new OreStack("dustCoal"), Item.clay);
    }

    private static void sendAddRecipe(Object outputObject, Object ... inputObjects)
    {
        List<?> inputObjectsList = Arrays.asList(inputObjects);
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.RECIPE_ADD, new RecipeMapping(outputObject, inputObjectsList).toJson());
    }
}

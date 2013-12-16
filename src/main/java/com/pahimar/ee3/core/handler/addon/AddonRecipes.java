package com.pahimar.ee3.core.handler.addon;

import com.pahimar.ee3.api.RecipeMapping;
import com.pahimar.ee3.imc.InterModCommsOperations;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.common.event.FMLInterModComms;

import java.util.Arrays;
import java.util.List;

/**
 * Equivalent-Exchange-3
 * <p/>
 * AddonVanilla
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AddonRecipes
{

    public static void init()
    {

        // TODO Once the API is more solid, add examples here for proper IMC calls
        // FMLInterModComms.sendMessage(Reference.MOD_ID, InterModComms.ADD_RECIPE, "");
        sendAddRecipe(new OreStack("dustBronze"), Arrays.asList(new OreStack("dustTin"), new OreStack("dustCopper"), new OreStack("dustCopper"), new OreStack("dustCopper")));
        sendAddRecipe(new OreStack("dustGold"), Arrays.asList(new OreStack("ingotGold")));
    }

    private static void sendAddRecipe(Object outputObject, List<?> inputObjects)
    {
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.RECIPE_ADD, new RecipeMapping(outputObject, inputObjects).toJson());
    }
}

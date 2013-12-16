package com.pahimar.ee3.addon;

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

        sendAddRecipe(new OreStack("dustGold"), Item.ingotGold);
        sendAddRecipe(new OreStack("dustDiamond"), Item.diamond);
        sendAddRecipe(new OreStack("dustObsidian"), Block.obsidian);
        sendAddRecipe(new OreStack("dustClay"), new ItemStack(Item.coal, 1, 0));
        sendAddRecipe(new OreStack("dustCoal"), Item.clay);

        /**
         * Bronze
         */
        sendAddRecipe(new OreStack("dustBronze"), new OreStack("dustTin"), new OreStack("dustCopper", 3));
        sendAddRecipe(new OreStack("ingotBronze"), new OreStack("dustBronze"));
        sendAddRecipe(new OreStack("blockBronze"), new OreStack("ingotBronze", 9));

        /**
         * Clay
         */

        /**
         * Coal
         */

        /**
         * Copper
         */
        sendAddRecipe(new OreStack("dustCopper"), new OreStack("oreCopper"));
        sendAddRecipe(new OreStack("dustTinyCopper", 9), new OreStack("dustCopper"));
        sendAddRecipe(new OreStack("crushedCopper"), new OreStack("dustCopper"));
        sendAddRecipe(new OreStack("crushedPurifiedCopper"), new OreStack("crushedCopper"));
        sendAddRecipe(new OreStack("ingotCopper"), new OreStack("dustCopper"));
        sendAddRecipe(new OreStack("plateCopper"), new OreStack("ingotCopper"));
        sendAddRecipe(new OreStack("blockCopper"), new OreStack("ingotCopper", 9));
        sendAddRecipe(new OreStack("plateDenseCopper"), new OreStack("ingotCopper", 9));

        /**
         * Diamond
         */

        /**
         * Gold
         */

        /**
         * Iron
         */

        /**
         * Lapis
         */

        /**
         * Lead
         */

        /**
         * Lithium
         */

        /**
         * Obsidian
         */

        /**
         * Silver
         */

        /**
         * Sulfur
         */

        /**
         * Tin
         */
        sendAddRecipe(new OreStack("ingotTin"), new OreStack("dustTin"));

        /**
         * Uranium
         */


    }

    private static void sendAddRecipe(Object outputObject, Object ... inputObjects)
    {
        List<?> inputObjectsList = Arrays.asList(inputObjects);
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.RECIPE_ADD, new RecipeMapping(outputObject, inputObjectsList).toJson());
    }
}

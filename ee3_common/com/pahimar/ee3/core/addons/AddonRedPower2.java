package com.pahimar.ee3.core.addons;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.core.util.RecipeHelper;
import com.pahimar.ee3.recipe.RecipesTransmutationStone;

import cpw.mods.fml.common.Loader;

/**
 * Equivalent-Exchange-3
 * 
 * AddonRedPower2
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class AddonRedPower2 {

    public static Block rp2Stone = null;

    public static void initWorld() {

        if (Loader.isModLoaded("RedPowerWorld")) {
            try {
                rp2Stone = (Block) Class.forName("com.eloraam.redpower.RedPowerWorld").getField("blockStone").get(null);

                for (ItemStack stone : RecipesTransmutationStone.transmutationStones) {
                    // Extremely temporary recipe
                    RecipeHelper.addRecipe(new ItemStack(rp2Stone.blockID, 1, 3), stone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone, Block.cobblestone);
                }

                LogHelper.log(Level.INFO, "Loaded RP2 World addon");
            }
            catch (Exception e) {
                LogHelper.log(Level.SEVERE, "Could not load RP2 World addon");
                e.printStackTrace(System.err);
            }
        }
    }

}

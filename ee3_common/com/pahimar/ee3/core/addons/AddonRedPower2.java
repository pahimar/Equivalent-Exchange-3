package com.pahimar.ee3.core.addons;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.core.helper.RecipeHelper;
import com.pahimar.ee3.recipe.RecipesTransmutationStone;

import cpw.mods.fml.common.Loader;

public class AddonRedPower2 {

    public static Block rp2Stone = null;

    public static void initWorld() {

        if (Loader.isModLoaded("RedPowerMechanical")) {
            try {
                rp2Stone = (Block) Class.forName("RedPowerWorld").getField("blockStone").get(null);

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

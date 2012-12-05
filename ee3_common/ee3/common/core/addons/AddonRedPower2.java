package ee3.common.core.addons;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

import ee3.common.core.helper.LogHelper;
import ee3.common.core.helper.RecipeHelper;
import ee3.common.recipe.RecipesTransmutationStone;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class AddonRedPower2 {

    public static Block rp2Stone = null;

    public static void initWorld() {

        if (Loader.isModLoaded("RedPowerWorld")) {
            try {
                rp2Stone = (Block) Class.forName("RedPowerWorld").getField("blockStone").get(null);
                
                for (ItemStack stone: RecipesTransmutationStone.transmutationStones) {
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

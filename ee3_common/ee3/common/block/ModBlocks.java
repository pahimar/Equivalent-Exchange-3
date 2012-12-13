package ee3.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.EquivalentExchange3;
import ee3.common.lib.BlockIds;
import ee3.common.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * ModBlocks
 * 
 * Class containing all the EE3 custom blocks
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModBlocks {
	
    /* Mod block instances */
	public static Block calcinator;
	public static Block redWaterStill;
	public static Block redWaterFlowing;

	public static void init() {

		calcinator = new BlockCalcinator(BlockIds.CALCINATOR);
		redWaterStill = new BlockRedWaterStill(BlockIds.RED_WATER_STILL);
		redWaterFlowing = new BlockRedWaterFlowing(BlockIds.RED_WATER_STILL - 1);
		
		GameRegistry.registerBlock(calcinator);
		GameRegistry.registerBlock(redWaterStill);
		GameRegistry.registerBlock(redWaterFlowing);
		
		initBlockRecipes();
		
	}
	
	private static void initBlockRecipes() {
	    
	    // Calcinator Recipe
		/* Temporarily disabled for pre-release 1, as it is not completely functional
	    GameRegistry.addRecipe(new ItemStack(calcinator), 
	            new Object[] {"i i","iii","sfs", 
	        Character.valueOf('i'), Item.ingotIron, 
	        Character.valueOf('s'), Block.stone,
	        Character.valueOf('f'), Item.flintAndSteel
	        });
	    */
	}

}

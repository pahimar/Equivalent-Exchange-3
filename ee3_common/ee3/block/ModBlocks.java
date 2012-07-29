package ee3.block;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import ee3.lib.BlockIds;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ModBlocks {
	
	public static BlockRedWaterStill redWaterStill;
	public static BlockRedWaterFlowing redWaterFlowing;
	
	public static void init() {

		redWaterStill = new BlockRedWaterStill(BlockIds.RED_WATER_STILL, Material.water);
		redWaterFlowing = new BlockRedWaterFlowing(BlockIds.RED_WATER_STILL - 1, Material.water);
		
		ModLoader.addName(redWaterStill, "Red Water (Still)");
		ModLoader.addName(redWaterFlowing, "Red Water (Flowing)");
		
		ModLoader.registerBlock(redWaterStill);
		ModLoader.registerBlock(redWaterFlowing);
		
		ModLoader.addShapelessRecipe(new ItemStack(redWaterStill), Block.cobblestone);
		ModLoader.addShapelessRecipe(new ItemStack(redWaterFlowing), Block.cobblestone, Block.cobblestone);
	}
	
}

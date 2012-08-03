package ee3.block;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemMetadata;
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
	public static BlockCorruptedLog corruptedLog;
	public static BlockCorruptedSapling corruptedSapling;
	
	public static void init() {

		redWaterStill = new BlockRedWaterStill(BlockIds.RED_WATER_STILL, Material.water);
		redWaterFlowing = new BlockRedWaterFlowing(BlockIds.RED_WATER_STILL - 1, Material.water);
		corruptedLog = new BlockCorruptedLog(250);
		corruptedSapling = new BlockCorruptedSapling(251);
		
		redWaterStill.setBlockName("redWaterStill");
		redWaterFlowing.setBlockName("redWaterFlowing");
		corruptedLog.setBlockName("corruptedLog");
		corruptedSapling.setBlockName("corruptedSapling");
		
		ModLoader.addName(redWaterStill, "Red Water (Still)");
		ModLoader.addName(redWaterFlowing, "Red Water (Flowing)");
		ModLoader.addName(corruptedLog, "Corrupted Wood");
		ModLoader.addName(corruptedSapling, "Corrupted Sapling");
		
		ModLoader.registerBlock(redWaterStill);
		ModLoader.registerBlock(redWaterFlowing);
		ModLoader.registerBlock(corruptedLog);
		ModLoader.registerBlock(corruptedSapling);
		
		Item.itemsList[corruptedLog.blockID] = (new ItemMetadata(corruptedLog.blockID - 256, corruptedLog)).setItemName("log");
	}
	
}

package ee3.core.helper;

import java.util.ArrayList;
import java.util.logging.Level;

import ee3.addons.BuildCraftAddon;
import ee3.addons.ForestryAddon;
import ee3.addons.IndustrialCraftAddon;
import ee3.addons.RedPowerAddon;
import ee3.block.ModBlocks;
import ee3.lib.Reference;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BaseMod;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class Helper {

	public static ItemStack convertObjectToItemStack(Object obj) {
		if (obj instanceof Item) { return new ItemStack((Item)obj);	}
		else if (obj instanceof Block) { return new ItemStack((Block)obj); }
		else if (obj instanceof ItemStack){	return (ItemStack)obj; }
		else { return null;	}
	}

	public static Object[] convertSingleStackToPluralStacks(ItemStack stack) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		ItemStack currentStack;

		for (int i = 0; i < stack.stackSize; i++) {
			currentStack = new ItemStack(stack.itemID, 1, stack.getItemDamage());
			list.add(currentStack);
		}
		
		return list.toArray();
	}
	
	public static String getLogMessage(String logMessage) {
		return Reference.LOGGER_PREFIX + logMessage;
	}
	
	public static void initAddons() {
		ModLoader.getLogger().finer(getLogMessage("Checking for addons needing initializing"));
		boolean addonsFound = false;
		
		for (BaseMod mod : ModLoader.getLoadedMods()) {
			if (mod.toString().contains("mod_BuildCraftEnergy")) {
				BuildCraftAddon.init();
				addonsFound = true;
			}
			else if (mod.toString().contains("mod_RedPowerCore")) {
				RedPowerAddon.init();
				addonsFound = true;
			}
			else if (mod.toString().contains("mod_RedPowerWorld")) {
				RedPowerAddon.init();
				addonsFound = true;
			}
			else if (mod.toString().contains("mod_Forestry")) {
				ForestryAddon.init();
				addonsFound = true;
			}
			else if (mod.toString().contains("mod_IC2")) {
				IndustrialCraftAddon.init();
				addonsFound = true;
			}
		}
		if (addonsFound)
			ModLoader.getLogger().finer(getLogMessage("Done initializing addons"));
		else
			ModLoader.getLogger().finer(getLogMessage("No addons for loaded mods found"));
	}
	
	public static boolean handleRedWaterDetection(EntityLiving entity) {
        return (isBlockInBB(entity.worldObj, entity.boundingBox, ModBlocks.redWaterFlowing) 
        		|| isBlockInBB(entity.worldObj, entity.boundingBox, ModBlocks.redWaterStill));
    }
	
	public static boolean isBlockInBB(World world, AxisAlignedBB par1AxisAlignedBB, Block block) {
        int minX = MathHelper.floor_double(par1AxisAlignedBB.minX - Reference.BLOCK_RED_WATER_RANGE_BASE * Reference.BLOCK_RED_WATER_RANGE_MODIFIER * 1.0D);
        int maxX = MathHelper.floor_double(par1AxisAlignedBB.maxX + Reference.BLOCK_RED_WATER_RANGE_BASE * Reference.BLOCK_RED_WATER_RANGE_MODIFIER * 1.0D);
        int minY = MathHelper.floor_double(par1AxisAlignedBB.minY - Reference.BLOCK_RED_WATER_RANGE_BASE * Reference.BLOCK_RED_WATER_RANGE_MODIFIER * 1.0D);
        int maxY = MathHelper.floor_double(par1AxisAlignedBB.maxY + Reference.BLOCK_RED_WATER_RANGE_BASE * Reference.BLOCK_RED_WATER_RANGE_MODIFIER * 1.0D);
        int minZ = MathHelper.floor_double(par1AxisAlignedBB.minZ - Reference.BLOCK_RED_WATER_RANGE_BASE * Reference.BLOCK_RED_WATER_RANGE_MODIFIER * 1.0D);
        int maxZ = MathHelper.floor_double(par1AxisAlignedBB.maxZ + Reference.BLOCK_RED_WATER_RANGE_BASE * Reference.BLOCK_RED_WATER_RANGE_MODIFIER * 1.0D);
        
        for (int i = minX; i < maxX; ++i) {
            for (int j = minY; j < maxY; ++j) {
                for (int k = minZ; k < maxZ; ++k) {
                    Block currentBlock = Block.blocksList[world.getBlockId(i, j, k)];
                    if (currentBlock != null && currentBlock.blockID == block.blockID) {
                    	return true;
                    }
                }
            }
        }
        return false;
    }
}

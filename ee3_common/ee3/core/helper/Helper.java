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
import net.minecraft.src.EntityItem;
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
	
	public static boolean handleRedWaterMovement(EntityLiving entity) {
        return (isBlockInBB(entity.worldObj, entity.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), ModBlocks.redWaterFlowing) || isBlockInBB(entity.worldObj, entity.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), ModBlocks.redWaterStill));
    }
	
	public static boolean handleWaterMovement(EntityItem entity) {
		boolean isInWater = isBlockInBB(entity.worldObj, entity.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Block.waterStill);
        if(isInWater){
        	entity.worldObj.setBlock((int)entity.posX - 1, (int)entity.posY, (int)entity.posZ, ModBlocks.redWaterFlowing.blockID);
        }
		return isInWater;
    }
	
	public static boolean isBlockInBB(World world, AxisAlignedBB par1AxisAlignedBB, Block block) {
        int var3 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int var4 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
        int var5 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int var6 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
        int var7 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int var8 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

        for (int var9 = var3; var9 < var4; ++var9) {
            for (int var10 = var5; var10 < var6; ++var10) {
                for (int var11 = var7; var11 < var8; ++var11) {
                    Block var12 = Block.blocksList[world.getBlockId(var9, var10, var11)];
                    if (var12 != null && var12.blockID == block.blockID) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

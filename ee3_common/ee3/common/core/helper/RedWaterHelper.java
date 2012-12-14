package ee3.common.core.helper;

import ee3.common.block.ModBlocks;
import ee3.common.lib.ConfigurationSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * RedWaterHelper
 * 
 * Helper methods for Red Water related effects
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class RedWaterHelper {

    public static boolean handleRedWaterDetection(EntityLiving entity) {
        return (isBlockInBB(entity.worldObj, entity.boundingBox, ModBlocks.redWaterFlowing) || isBlockInBB(entity.worldObj, entity.boundingBox, ModBlocks.redWaterStill));
    }
    
    public static boolean isBlockInBB(World world, AxisAlignedBB par1AxisAlignedBB, Block block) {
        int minX = MathHelper.floor_double(par1AxisAlignedBB.minX - ConfigurationSettings.RED_WATER_RANGE_BASE * ConfigurationSettings.RED_WATER_RANGE_MODIFIER * 1.0D);
        int maxX = MathHelper.floor_double(par1AxisAlignedBB.maxX + ConfigurationSettings.RED_WATER_RANGE_BASE * ConfigurationSettings.RED_WATER_RANGE_MODIFIER * 1.0D);
        int minY = MathHelper.floor_double(par1AxisAlignedBB.minY - ConfigurationSettings.RED_WATER_RANGE_BASE * ConfigurationSettings.RED_WATER_RANGE_MODIFIER * 1.0D);
        int maxY = MathHelper.floor_double(par1AxisAlignedBB.maxY + ConfigurationSettings.RED_WATER_RANGE_BASE * ConfigurationSettings.RED_WATER_RANGE_MODIFIER * 1.0D);
        int minZ = MathHelper.floor_double(par1AxisAlignedBB.minZ - ConfigurationSettings.RED_WATER_RANGE_BASE * ConfigurationSettings.RED_WATER_RANGE_MODIFIER * 1.0D);
        int maxZ = MathHelper.floor_double(par1AxisAlignedBB.maxZ + ConfigurationSettings.RED_WATER_RANGE_BASE * ConfigurationSettings.RED_WATER_RANGE_MODIFIER * 1.0D);
        
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

package ee3.common.core.helper;

import ee3.common.block.ModBlocks;
import ee3.common.lib.Reference;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class RedWaterHelper {

    public static boolean handleRedWaterDetection(EntityLiving entity) {
        return (isBlockInBB(entity.worldObj, entity.boundingBox, ModBlocks.redWaterFlowing) || isBlockInBB(entity.worldObj, entity.boundingBox, ModBlocks.redWaterStill));
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

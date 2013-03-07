package com.pahimar.ee3.block;

import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * BlockEE
 * 
 * Parent block class for Equivalent Exchange blocks
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public abstract class BlockEE extends BlockContainer {

    public BlockEE(int id, Material material) {

        super(id, material);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister iconRegister) {
        
        this.field_94336_cN = iconRegister.func_94245_a(Reference.MOD_ID.toLowerCase() + ":"+ this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }
    
    /**
     * Sets the direction of the block when placed
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack) {
        
        int direction = 0;
        int facing = MathHelper.floor_double((double)(entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
        if (facing == 0) {
            direction = ForgeDirection.NORTH.ordinal();
        }
        else if (facing == 1) {
            direction = ForgeDirection.EAST.ordinal();
        }
        else if (facing == 2) {
            direction = ForgeDirection.SOUTH.ordinal();
        }
        else if (facing == 3) {
            direction = ForgeDirection.WEST.ordinal();
        }

        world.setBlockMetadataWithNotify(x, y, z, direction, 3);
    }
}

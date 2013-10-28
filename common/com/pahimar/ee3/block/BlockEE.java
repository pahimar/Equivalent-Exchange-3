package com.pahimar.ee3.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.tileentity.TileEE;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * BlockEE
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public abstract class BlockEE extends BlockContainer {

    public BlockEE(int id, Material material) {

        super(id, material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(String.format("%s:%s", Reference.MOD_ID.toLowerCase(), getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {

        return unlocalizedName.substring(unlocalizedName.indexOf(":") + 1);
    }

    /**
     * Sets the direction of the block when placed
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {

        int direction = 0;
        int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

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

        if (itemStack.hasDisplayName()) {
            ((TileEE) world.getBlockTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
        }

        ((TileEE) world.getBlockTileEntity(x, y, z)).setOrientation(direction);
    }
}

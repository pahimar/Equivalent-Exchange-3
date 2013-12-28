package com.pahimar.ee3.block;

import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;

// TODO Finish
public class BlockAeternalisFuel extends BlockEE
{
    @SideOnly(Side.CLIENT)
    private Icon blockTop, blockSide;

    public BlockAeternalisFuel(int id)
    {
        super(id);
        setHardness(5.0F);
        setResistance(10.0F);
        setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.AETERNALIS_FUEL_NAME);
        Block.setBurnProperties(id, 100, 5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockTop = iconRegister.registerIcon(String.format("%s_top", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
        blockSide = iconRegister.registerIcon(String.format("%s_side", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metaData)
    {
        if (ForgeDirection.getOrientation(side) == ForgeDirection.UP || ForgeDirection.getOrientation(side) == ForgeDirection.DOWN)
        {
            return blockTop;
        }
        else
        {
            return blockSide;
        }
    }
}

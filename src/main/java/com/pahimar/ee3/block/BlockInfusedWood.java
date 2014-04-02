package com.pahimar.ee3.block;

import com.pahimar.ee3.reference.Colors;
import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class BlockInfusedWood extends BlockEE
{
    @SideOnly(Side.CLIENT)
    private IIcon logEnd, logSide;

    public BlockInfusedWood()
    {
        super(Material.wood);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.INFUSED_WOOD);
        this.setStepSound(soundTypeWood);
    }

    @Override
    public int damageDropped(int metaData)
    {
        return metaData & 3;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int meta = 0; meta < 3; meta++)
        {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        logEnd = iconRegister.registerIcon("log_oak_top");
        logSide = iconRegister.registerIcon("log_oak");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData)
    {
        if (ForgeDirection.getOrientation(side) == ForgeDirection.UP || ForgeDirection.getOrientation(side) == ForgeDirection.DOWN)
        {
            if (metaData >> 2 == 0)
            {
                return logEnd;
            }
            else
            {
                return logSide;
            }
        }
        else if (ForgeDirection.getOrientation(side) == ForgeDirection.NORTH || ForgeDirection.getOrientation(side) == ForgeDirection.SOUTH)
        {
            if (metaData >> 2 == 2)
            {
                return logEnd;
            }
            else
            {
                return logSide;
            }
        }
        else
        {
            if (metaData >> 2 == 1)
            {
                return logEnd;
            }
            else
            {
                return logSide;
            }
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metaData)
    {
        int logType = metaData & 3;
        byte rotation = 0;

        switch (side)
        {
            case 0:
            case 1:
                rotation = 0;
                break;
            case 2:
            case 3:
                rotation = 8;
                break;
            case 4:
            case 5:
                rotation = 4;
        }

        int newMetaData = logType | rotation;
        world.setBlockMetadataWithNotify(x, y, z, newMetaData, 3);
        return newMetaData;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        int metaData = blockAccess.getBlockMetadata(x, y, z) & 3;

        if (metaData == 0)
        {
            return Integer.parseInt(Colors.INFUSED_WOOD_VERDANT, 16);
        }
        else if (metaData == 1)
        {
            return Integer.parseInt(Colors.INFUSED_WOOD_AZURE, 16);
        }
        else if (metaData == 2)
        {
            return Integer.parseInt(Colors.INFUSED_WOOD_MINIUM, 16);
        }
        else
        {
            return Integer.parseInt(Colors.PURE_WHITE, 16);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int metaData)
    {
        int adjustedMetaData = metaData & 3;
        if (adjustedMetaData == 0)
        {
            return Integer.parseInt(Colors.INFUSED_WOOD_VERDANT, 16);
        }
        else if (adjustedMetaData == 1)
        {
            return Integer.parseInt(Colors.INFUSED_WOOD_AZURE, 16);
        }
        else if (adjustedMetaData == 2)
        {
            return Integer.parseInt(Colors.INFUSED_WOOD_MINIUM, 16);
        }
        else
        {
            return Integer.parseInt(Colors.PURE_WHITE, 16);
        }
    }
}

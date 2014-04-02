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
import net.minecraft.world.IBlockAccess;

import java.util.List;

public class BlockInfusedCloth extends BlockEE
{
    public BlockInfusedCloth()
    {
        super(Material.cloth);
        this.setHardness(0.8f);
        this.setBlockName(Names.Blocks.INFUSED_CLOTH);
        this.setStepSound(soundTypeCloth);
    }

    @Override
    public int damageDropped(int metaData)
    {
        return metaData;
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
        blockIcon = iconRegister.registerIcon("wool_colored_white");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        int metaData = blockAccess.getBlockMetadata(x, y, z);

        if (metaData == 0)
        {
            return Integer.parseInt(Colors.INFUSED_CLOTH_VERDANT, 16);
        }
        else if (metaData == 1)
        {
            return Integer.parseInt(Colors.INFUSED_CLOTH_AZURE, 16);
        }
        else if (metaData == 2)
        {
            return Integer.parseInt(Colors.INFUSED_CLOTH_MINIUM, 16);
        }
        else
        {
            return Integer.parseInt(Colors.PURE_WHITE, 16);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int metaData)
    {
        if (metaData == 0)
        {
            return Integer.parseInt(Colors.INFUSED_CLOTH_VERDANT, 16);
        }
        else if (metaData == 1)
        {
            return Integer.parseInt(Colors.INFUSED_CLOTH_AZURE, 16);
        }
        else if (metaData == 2)
        {
            return Integer.parseInt(Colors.INFUSED_CLOTH_MINIUM, 16);
        }
        else
        {
            return Integer.parseInt(Colors.PURE_WHITE, 16);
        }
    }
}

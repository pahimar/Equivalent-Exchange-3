package com.pahimar.ee3.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInfusedCloth extends BlockEE
{
    public BlockInfusedCloth()
    {
        super(Material.cloth);
        this.setBlockName(Strings.INFUSED_CLOTH_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setStepSound(soundTypeCloth);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("wool_colored_white");
    }

    @Override
    public int damageDropped(int metaData)
    {
        return metaData;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        int metaData = blockAccess.getBlockMetadata(x, y, z);

        if (metaData == 0)
        {
            return Integer.parseInt(Colours.INFUSED_CLOTH_VERDANT, 16);
        }
        else if (metaData == 1)
        {
            return Integer.parseInt(Colours.INFUSED_CLOTH_AZURE, 16);
        }
        else if (metaData == 2)
        {
            return Integer.parseInt(Colours.INFUSED_CLOTH_MINIUM, 16);
        }
        else
        {
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int metaData)
    {
        if (metaData == 0)
        {
            return Integer.parseInt(Colours.INFUSED_CLOTH_VERDANT, 16);
        }
        else if (metaData == 1)
        {
            return Integer.parseInt(Colours.INFUSED_CLOTH_AZURE, 16);
        }
        else if (metaData == 2)
        {
            return Integer.parseInt(Colours.INFUSED_CLOTH_MINIUM, 16);
        }
        else
        {
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < 3; ++meta)
        {
            list.add(new ItemStack(id, 1, meta));
        }
    }
}

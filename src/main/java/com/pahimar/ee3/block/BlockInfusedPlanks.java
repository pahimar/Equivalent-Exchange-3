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

public class BlockInfusedPlanks extends BlockEE
{
    public BlockInfusedPlanks()
    {
        super(Material.wood);
        this.setBlockName(Strings.INFUSED_PLANKS_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setStepSound(soundTypeWood);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("planks_oak");
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        int metaData = blockAccess.getBlockMetadata(x, y, z);

        if (metaData == 0)
        {
            return Integer.parseInt(Colours.INFUSED_PLANKS_VERDANT, 16);
        }
        else if (metaData == 1)
        {
            return Integer.parseInt(Colours.INFUSED_PLANKS_AZURE, 16);
        }
        else if (metaData == 2)
        {
            return Integer.parseInt(Colours.INFUSED_PLANKS_MINIUM, 16);
        }
        else
        {
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        }
    }

    @Override
    public int damageDropped(int metaData)
    {
        return metaData;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int metaData)
    {
        if (metaData == 0)
        {
            return Integer.parseInt(Colours.INFUSED_PLANKS_VERDANT, 16);
        }
        else if (metaData == 1)
        {
            return Integer.parseInt(Colours.INFUSED_PLANKS_AZURE, 16);
        }
        else if (metaData == 2)
        {
            return Integer.parseInt(Colours.INFUSED_PLANKS_MINIUM, 16);
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

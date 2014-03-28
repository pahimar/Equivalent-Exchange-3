package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

import java.util.List;

public class BlockInfusedPlanks extends BlockEE
{
    public BlockInfusedPlanks(int id)
    {
        super(id, Material.wood);
        this.setUnlocalizedName(Strings.INFUSED_PLANKS_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setStepSound(soundWoodFootstep);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
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

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < 3; ++meta)
        {
            list.add(new ItemStack(id, 1, meta));
        }
    }
}

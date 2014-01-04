package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.ItemIds;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

import java.util.List;

public class BlockInfusedCloth extends BlockEE
{
    public BlockInfusedCloth(int id)
    {
        super(id, Material.cloth);
        this.setUnlocalizedName(Strings.INFUSED_CLOTH_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setStepSound(soundClothFootstep);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("wool_colored_white");
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        ItemStack dustStack = new ItemStack(ItemIds.ALCHEMICAL_DUST, 1, MathHelper.clamp_int(blockAccess.getBlockMetadata(x, y, z), 0, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1));
        return dustStack.getItem().getColorFromItemStack(dustStack, 0);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int metaData)
    {
        ItemStack dustStack = new ItemStack(ItemIds.ALCHEMICAL_DUST, 1, MathHelper.clamp_int(metaData, 0, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1));
        return dustStack.getItem().getColorFromItemStack(dustStack, metaData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length; ++meta)
        {
            list.add(new ItemStack(id, 1, meta));
        }
    }
}

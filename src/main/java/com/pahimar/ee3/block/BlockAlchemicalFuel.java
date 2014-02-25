package com.pahimar.ee3.block;

import java.util.List;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAlchemicalFuel extends BlockEE
{
    @SideOnly(Side.CLIENT)
    private IIcon[] blockTop, blockSide;

    public BlockAlchemicalFuel()
    {
        super();
        this.setBlockName(Strings.ALCHEMICAL_FUEL_BLOCK_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Block block, CreativeTabs creativeTabs, List list)
    {
        for (int meta = 0; meta < Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length; meta++)
        {
            list.add(new ItemStack(block, 1, meta));
        }
    }

    @Override
    public int damageDropped(int metaData)
    {
        return metaData;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockTop = new IIcon[Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length];
        this.blockSide = new IIcon[Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length];

        for (int i = 0; i < Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length; i++)
        {
            blockTop[i] = iconRegister.registerIcon(String.format("%s.%s_top", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES[i]));
            blockSide[i] = iconRegister.registerIcon(String.format("%s.%s_side", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES[i]));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData)
    {
        metaData = MathHelper.clamp_int(metaData, 0, Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length - 1);

        if (ForgeDirection.getOrientation(side) == ForgeDirection.UP || ForgeDirection.getOrientation(side) == ForgeDirection.DOWN)
        {
            return blockTop[metaData];
        }
        else
        {
            return blockSide[metaData];
        }
    }
}

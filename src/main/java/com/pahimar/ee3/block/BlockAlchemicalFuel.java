package com.pahimar.ee3.block;

import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

public class BlockAlchemicalFuel extends BlockEE
{
    @SideOnly(Side.CLIENT)
    private IIcon[] blockTop, blockSide;

    public BlockAlchemicalFuel()
    {
        super();
        this.setBlockName(Names.Blocks.ALCHEMICAL_FUEL);
        this.setHardness(5.0f);
        this.setResistance(10.0f);
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par2)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int meta = 0; meta < Names.Items.ALCHEMICAL_FUEL_SUBTYPES.length; meta++)
        {
            list.add(new ItemStack(item, 1, meta));
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
        this.blockTop = new IIcon[Names.Items.ALCHEMICAL_FUEL_SUBTYPES.length];
        this.blockSide = new IIcon[Names.Items.ALCHEMICAL_FUEL_SUBTYPES.length];

        for (int i = 0; i < Names.Items.ALCHEMICAL_FUEL_SUBTYPES.length; i++)
        {
            blockTop[i] = iconRegister.registerIcon(String.format("%s.%s_top", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), Names.Items.ALCHEMICAL_FUEL_SUBTYPES[i]));
            blockSide[i] = iconRegister.registerIcon(String.format("%s.%s_side", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), Names.Items.ALCHEMICAL_FUEL_SUBTYPES[i]));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData)
    {
        metaData = MathHelper.clamp_int(metaData, 0, Names.Items.ALCHEMICAL_FUEL_SUBTYPES.length - 1);

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

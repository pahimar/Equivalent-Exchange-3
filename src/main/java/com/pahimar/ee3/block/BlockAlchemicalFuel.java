package com.pahimar.ee3.block;

import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeDirection;

import java.util.List;

public class BlockAlchemicalFuel extends BlockEE
{
    @SideOnly(Side.CLIENT)
    private Icon[] blockTop, blockSide;

    public BlockAlchemicalFuel(int id)
    {
        super(id);
        setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_FUEL_BLOCK_NAME);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs creativeTabs, List list)
    {
        for (int meta = 0; meta < Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length; meta++)
        {
            list.add(new ItemStack(id, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockTop = new Icon[Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length];
        this.blockSide = new Icon[Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length];

        for (int i = 0; i < Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length; i++)
        {
            blockTop[i] = iconRegister.registerIcon(String.format("%s.%s_top", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES[i]));
            blockSide[i] = iconRegister.registerIcon(String.format("%s.%s_side", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES[i]));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metaData)
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

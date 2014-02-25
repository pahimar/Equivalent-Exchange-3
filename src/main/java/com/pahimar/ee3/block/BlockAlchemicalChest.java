package com.pahimar.ee3.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAlchemicalChestLarge;
import com.pahimar.ee3.tileentity.TileAlchemicalChestMedium;
import com.pahimar.ee3.tileentity.TileAlchemicalChestSmall;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * <p/>
 * BlockAlchemicalChest
 *
 * @author pahimar
 */
public class BlockAlchemicalChest extends BlockEE implements ITileEntityProvider
{
    public BlockAlchemicalChest()
    {
        super(Material.wood);
        this.setHardness(2.5F);
        this.setBlockName(Strings.ALCHEMICAL_CHEST_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return null;
    }

    @Override
    public TileEntity createTileEntity(World world, int metaData)
    {
        if (metaData == 0)
        {
            return new TileAlchemicalChestSmall();
        }
        else if (metaData == 1)
        {
            return new TileAlchemicalChestMedium();
        }
        else if (metaData == 2)
        {
            return new TileAlchemicalChestLarge();
        }

        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Block block, CreativeTabs creativeTabs, List list)
    {
        for (int meta = 0; meta < 3; meta++)
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
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {

        return RenderIds.alchemicalChestRender;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking() || world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN))
        {
            return true;
        }
        else
        {
            if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileAlchemicalChest)
            {
                player.openGui(EquivalentExchange3.instance, GuiIds.ALCHEMICAL_CHEST, world, x, y, z);
            }

            return true;
        }
    }
}

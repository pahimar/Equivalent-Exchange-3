package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileEE;
import com.pahimar.ee3.tileentity.TileGlassBell;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlassBell extends BlockEE implements ITileEntityProvider
{
    public BlockGlassBell()
    {
        super(Material.glass);
        this.setBlockName(Strings.GLASS_BELL_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setHardness(1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2)
    {
        return new TileGlassBell();
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

        return RenderIds.glassBell;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block id, int meta)
    {
        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                if (world.getTileEntity(x, y, z) instanceof TileGlassBell)
                {
                    if (world.getTileEntity(x, y - 1, z) instanceof TileAludel)
                    {
                        player.openGui(EquivalentExchange3.instance, GuiIds.ALUDEL, world, x, y - 1, z);
                    }
                    else
                    {
                        player.openGui(EquivalentExchange3.instance, GuiIds.GLASS_BELL, world, x, y, z);
                    }
                }
            }

            return true;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        if (itemStack.hasDisplayName())
        {
            ((TileEE) world.getTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
        }

        if (world.getTileEntity(x, y - 1, z) != null && world.getTileEntity(x, y - 1, z) instanceof TileAludel)
        {
            ((TileEE) world.getTileEntity(x, y, z)).setOrientation(ForgeDirection.UP);
        }
        else
        {
            ((TileEE) world.getTileEntity(x, y, z)).setOrientation(world.getBlockMetadata(x, y, z));
        }

        world.setBlockMetadataWithNotify(x, y, z, 0, 3);
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData)
    {
        return sideHit;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
    {
        if (world.getTileEntity(x, y, z) instanceof TileGlassBell)
        {
            TileGlassBell tileGlassBell = (TileGlassBell) world.getTileEntity(x, y, z);

            switch (tileGlassBell.getOrientation())
            {
                case DOWN:
                {
                    this.setBlockBounds(0.125F, 0.33F, 0.125F, 0.875F, 1.0F, 0.875F);
                    break;
                }
                case UP:
                {
                    this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.66F, 0.875F);
                    break;
                }
                case NORTH:
                {
                    this.setBlockBounds(0.125F, 0.125F, 0.33F, 0.875F, 0.875F, 1.0F);
                    break;
                }
                case SOUTH:
                {
                    this.setBlockBounds(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 0.66F);
                    break;
                }
                case EAST:
                {
                    this.setBlockBounds(0.0F, 0.125F, 0.125F, 0.66F, 0.875F, 0.875F);
                    break;
                }
                case WEST:
                {
                    this.setBlockBounds(0.33F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);
                    break;
                }
                case UNKNOWN:
                {
                    break;
                }
            }
        }

        return super.collisionRayTrace(world, x, y, z, startVec, endVec);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        ItemStack itemStack;

        if (world.getTileEntity(x, y, z) instanceof TileGlassBell)
        {
            TileGlassBell tileGlassBell = (TileGlassBell) world.getTileEntity(x, y, z);

            if (world.getTileEntity(x, y - 1, z) instanceof TileAludel)
            {
                TileAludel tileAludel = (TileAludel) world.getTileEntity(x, y - 1, z);

                itemStack = tileAludel.getStackInSlot(TileAludel.INPUT_INVENTORY_INDEX);

                if (itemStack != null && itemStack.getItem() != null)
                {
                    //return itemStack.getItem().
                	return Block.getBlockFromItem(itemStack.getItem()).getLightValue();
                }
            }

            itemStack = tileGlassBell.getStackInSlot(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX);

            if (itemStack != null && itemStack.getItem() != null)
            {
                //return Block.lightValue[itemStack.itemID];
            	return Block.getBlockFromItem(itemStack.getItem()).getLightValue();
            }
        }

        return 0;
    }
}

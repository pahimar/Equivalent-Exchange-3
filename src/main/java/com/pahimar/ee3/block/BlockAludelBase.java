package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileGlassBell;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * Equivalent-Exchange-3
 * <p/>
 * BlockAludel
 *
 * @author pahimar
 */
public class BlockAludelBase extends BlockEE implements ITileEntityProvider
{
    public BlockAludelBase(int id)
    {
        super(id, Material.anvil);
        this.setUnlocalizedName(Strings.ALUDEL_NAME);
        this.setBlockBounds(0.10F, 0.0F, 0.10F, 0.90F, 1.0F, 0.90F);
        this.setHardness(5F);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAludel();
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

        return RenderIds.aludelRender;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta)
    {
        if (world.getBlockTileEntity(x, y + 1, z) instanceof TileGlassBell)
        {
            world.markBlockForUpdate(x, y + 1, z);
            world.updateAllLightTypes(x, y + 1, z);
        }

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
                if (world.getBlockTileEntity(x, y, z) instanceof TileAludel && world.getBlockTileEntity(x, y + 1, z) instanceof TileGlassBell)
                {
                    player.openGui(EquivalentExchange3.instance, GuiIds.ALUDEL, world, x, y, z);
                }
            }

            return true;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);

        if (world.getBlockTileEntity(x, y + 1, z) instanceof TileGlassBell)
        {
            TileGlassBell tileGlassBell = (TileGlassBell) world.getBlockTileEntity(x, y + 1, z);

            tileGlassBell.setOrientation(ForgeDirection.UP);

            if (world.getBlockTileEntity(x, y, z) instanceof TileAludel)
            {
                TileAludel tileAludel = (TileAludel) world.getBlockTileEntity(x, y, z);

                ItemStack itemStackGlassBell = tileGlassBell.getStackInSlot(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX);

                tileGlassBell.setInventorySlotContents(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX, null);

                tileAludel.setInventorySlotContents(TileAludel.INPUT_INVENTORY_INDEX, itemStackGlassBell);
            }
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        // TODO Vary light levels depending on if we are burning or not
        return 0;
    }
}

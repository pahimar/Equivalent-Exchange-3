package com.pahimar.ee3.array;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TransmutationAlchemyArray extends AlchemyArray implements IInventory
{
    private EnergyValue energyValue;

    public TransmutationAlchemyArray()
    {
        super(Textures.AlchemyArray.TRANSMUTATION_ALCHEMY_ARRAY, Names.AlchemyArrays.TRANSMUTATION_ALCHEMY_ARRAY);
    }

    @Override
    public void onArrayActivated(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            if (world.getTileEntity(arrayX, arrayY, arrayZ) instanceof TileEntityAlchemyArray)
            {
                TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(arrayX, arrayY, arrayZ);
                if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.UP && tileEntityAlchemyArray.getSize() == 2 && areBlocksValidForTransmutationTablet(world, arrayX, arrayY, arrayZ))
                {
                    entityPlayer.openGui(EquivalentExchange3.instance, GUIs.TRANSMUTATION_TABLET.ordinal(), world, arrayX, arrayY, arrayZ);
                }
            }
        }
    }

    private boolean areBlocksValidForTransmutationTablet(World world, int arrayX, int arrayY, int arrayZ)
    {
        boolean areBlocksValid = true;

        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (world.getBlock(arrayX + i, arrayY - 1, arrayZ + j) != ModBlocks.ashInfusedStone)
                {
                    areBlocksValid = false;
                }
            }
        }

        return areBlocksValid;
    }

    @Override
    public int getSizeInventory()
    {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_)
    {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
    {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
    {

    }

    @Override
    public String getInventoryName()
    {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 0;
    }

    @Override
    public void markDirty()
    {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return false;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return false;
    }
}

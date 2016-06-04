package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageTileEntityDummy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

public class TileEntityDummyArray extends TileEntityEE
{
    private int trueXCoord, trueYCoord, trueZCoord;
    private int ticksSinceSync;

    public TileEntityDummyArray()
    {
        super();
    }

    public int getTrueXCoord()
    {
        return trueXCoord;
    }

    public int getTrueYCoord()
    {
        return trueYCoord;
    }

    public int getTrueZCoord()
    {
        return trueZCoord;
    }

    public void setTrueCoords(int trueXCoord, int trueYCoord, int trueZCoord)
    {
        this.trueXCoord = trueXCoord;
        this.trueYCoord = trueYCoord;
        this.trueZCoord = trueZCoord;
    }

    public TileEntityAlchemyArray getAssociatedTileEntity()
    {
        if (this.worldObj.getTileEntity(trueXCoord, trueYCoord, trueZCoord) instanceof TileEntityAlchemyArray)
        {
            return (TileEntityAlchemyArray) this.worldObj.getTileEntity(trueXCoord, trueYCoord, trueZCoord);
        }

        return null;
    }

    public int getLightLevel()
    {
        TileEntityAlchemyArray tileEntityAlchemyArray = getAssociatedTileEntity();

        if (tileEntityAlchemyArray != null)
        {
            return tileEntityAlchemyArray.getLightLevel();
        }

        return 0;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (++ticksSinceSync % 10 == 0)
        {
            if (!worldObj.isRemote && !(worldObj.getTileEntity(trueXCoord, trueYCoord, trueZCoord) instanceof TileEntityAlchemyArray))
            {
                this.invalidate();
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return Network.INSTANCE.getPacketFrom(new MessageTileEntityDummy(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        this.trueXCoord = nbtTagCompound.getInteger("trueXCoord");
        this.trueYCoord = nbtTagCompound.getInteger("trueYCoord");
        this.trueZCoord = nbtTagCompound.getInteger("trueZCoord");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("trueXCoord", trueXCoord);
        nbtTagCompound.setInteger("trueYCoord", trueYCoord);
        nbtTagCompound.setInteger("trueZCoord", trueZCoord);
    }
}

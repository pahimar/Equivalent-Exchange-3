package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.init.AlchemyArrays;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityAlchemyArray;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAlchemyArray extends TileEntityEE
{
    private AlchemyArray alchemyArray;
    private ForgeDirection rotation;
    private int size;
    private int ticksSinceSync;

    public TileEntityAlchemyArray()
    {
        super();
        rotation = ForgeDirection.UNKNOWN;
        size = 0;
        alchemyArray = AlchemyArrays.basicAlchemyArray;
    }

    public AlchemyArray getAlchemyArray()
    {
        return alchemyArray;
    }

    public void setAlchemyArray(AlchemyArray alchemyArray)
    {
        setAlchemyArray(alchemyArray, 1);
    }

    public void setAlchemyArray(AlchemyArray alchemyArray, int size)
    {
        this.alchemyArray = alchemyArray;
        this.size = size;
    }

    public ForgeDirection getRotation()
    {
        return rotation;
    }

    public void setRotation(ForgeDirection rotation)
    {
        this.rotation = rotation;
    }

    public void setRotation(int rotation, int facing)
    {
        if (this.orientation == ForgeDirection.UP)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.NORTH;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.EAST;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.SOUTH;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.WEST;
            }

        }
        else if (this.orientation == ForgeDirection.DOWN)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.NORTH;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.EAST;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.SOUTH;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.WEST;
            }
        }
        else if (this.orientation == ForgeDirection.NORTH)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.UP;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.EAST;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.DOWN;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.WEST;
            }
        }
        else if (this.orientation == ForgeDirection.SOUTH)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.DOWN;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.EAST;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.UP;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.WEST;
            }
        }
        else if (this.orientation == ForgeDirection.EAST)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.NORTH;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.UP;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.SOUTH;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.DOWN;
            }
        }
        else if (this.orientation == ForgeDirection.WEST)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.NORTH;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.DOWN;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.SOUTH;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.UP;
            }
        }
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        if (this.orientation == ForgeDirection.UP || this.orientation == ForgeDirection.DOWN)
        {
            return AxisAlignedBB.getBoundingBox(xCoord - size, yCoord - 1, zCoord - size, xCoord + size, yCoord + 1, zCoord + size);
        }
        else if (this.orientation == ForgeDirection.NORTH || this.orientation == ForgeDirection.SOUTH)
        {
            return AxisAlignedBB.getBoundingBox(xCoord - size, yCoord - size, zCoord - 1, xCoord + size, yCoord + size, zCoord + 1);
        }
        else if (this.orientation == ForgeDirection.EAST || this.orientation == ForgeDirection.WEST)
        {
            return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - size, zCoord - size, xCoord + 1, yCoord + size, zCoord + size);
        }

        return super.getRenderBoundingBox();
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!worldObj.isRemote)
        {
            if (++ticksSinceSync % 100 == 0)
            {
                if (!areDummyBlocksValid())
                {
                    this.invalidate();
                    worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                }
            }
        }
    }

    public void onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            alchemyArray.onArrayActivated(world, x, y, z, entityPlayer, sideHit, hitX, hitY, hitZ);
        }
    }


    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityAlchemyArray(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        rotation = ForgeDirection.getOrientation(nbtTagCompound.getInteger("rotation"));
        size = nbtTagCompound.getInteger("size");
        NBTTagCompound alchemyArrayTagCompound = nbtTagCompound.getCompoundTag("alchemyArray");
        alchemyArray = AlchemyArray.readArrayFromNBT(alchemyArrayTagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("rotation", rotation.ordinal());
        nbtTagCompound.setInteger("size", size);
        NBTTagCompound alchemyArrayTagCompound = new NBTTagCompound();
        alchemyArray.writeToNBT(alchemyArrayTagCompound);
        nbtTagCompound.setTag("alchemyArray", alchemyArrayTagCompound);
    }

    private boolean areDummyBlocksValid()
    {
        boolean validDummyBlocks = true;
        int coordOffset = this.size / 2;

        if (this.orientation == ForgeDirection.UP || this.orientation == ForgeDirection.DOWN)
        {
            for (int i = this.xCoord - coordOffset; i <= this.xCoord + coordOffset; i++)
            {
                for (int j = this.zCoord - coordOffset; j <= this.zCoord + coordOffset; j++)
                {
                    if ((i != this.xCoord || j != this.zCoord) && !isValidDummyBlock(i, this.yCoord, j))
                    {
                        validDummyBlocks = false;
                    }
                }
            }
        }
        else if (this.orientation == ForgeDirection.NORTH || this.orientation == ForgeDirection.SOUTH)
        {
            for (int i = this.xCoord - coordOffset; i <= this.xCoord + coordOffset; i++)
            {
                for (int j = this.yCoord - coordOffset; j <= this.yCoord + coordOffset; j++)
                {
                    if ((i != this.xCoord || j != this.yCoord) && !isValidDummyBlock(i, j, this.zCoord))
                    {
                        validDummyBlocks = false;
                    }
                }
            }
        }
        else if (this.orientation == ForgeDirection.EAST || this.orientation == ForgeDirection.WEST)
        {
            for (int i = this.yCoord - coordOffset; i <= this.yCoord + coordOffset; i++)
            {
                for (int j = this.zCoord - coordOffset; j <= this.zCoord + coordOffset; j++)
                {
                    if ((i != this.yCoord || j != this.zCoord) && !isValidDummyBlock(this.xCoord, i, j))
                    {
                        validDummyBlocks = false;
                    }
                }
            }
        }

        return validDummyBlocks;
    }

    private boolean isValidDummyBlock(int x, int y, int z)
    {
        if (!this.worldObj.isRemote)
        {
            if (this.worldObj.getTileEntity(x, y, z) instanceof TileEntityDummyArray)
            {
                TileEntityDummyArray tileEntityDummyArray = (TileEntityDummyArray) this.worldObj.getTileEntity(x, y, z);

                return tileEntityDummyArray.getOrientation() == this.orientation &&
                        tileEntityDummyArray.getTrueXCoord() == this.xCoord &&
                        tileEntityDummyArray.getTrueYCoord() == this.yCoord &&
                        tileEntityDummyArray.getTrueZCoord() == this.zCoord;
            }
        }

        return false;
    }
}

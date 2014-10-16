package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.api.Glyph;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityAlchemyArray;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAlchemyArray extends TileEntityEE
{
    private AlchemyArray alchemyArray;
    private ForgeDirection rotation;

    public TileEntityAlchemyArray()
    {
        super();
        alchemyArray = new AlchemyArray();
        rotation = ForgeDirection.UNKNOWN;
    }

    public AlchemyArray getAlchemyArray()
    {
        return alchemyArray;
    }

    public boolean addGlyphToAlchemyArray(Glyph glyph)
    {
        return alchemyArray.addGlyph(glyph);
    }

    public boolean addGlyphToAlchemyArray(Glyph glyph, int size)
    {
        return addGlyphToAlchemyArray(new Glyph(glyph, size));
    }

    public ForgeDirection getRotation()
    {
        return rotation;
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

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        // TODO: Make this glyph size and orientation sensitive
        return AxisAlignedBB.getBoundingBox(xCoord - alchemyArray.getLargestGlyphSize(), yCoord - alchemyArray.getLargestGlyphSize(), zCoord - alchemyArray.getLargestGlyphSize(), xCoord + alchemyArray.getLargestGlyphSize(), yCoord + alchemyArray.getLargestGlyphSize(), zCoord + alchemyArray.getLargestGlyphSize());
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

        NBTTagCompound alchemyArrayTagCompound = nbtTagCompound.getCompoundTag("alchemyArray");
        alchemyArray = AlchemyArray.readAlchemyArrayFromNBT(alchemyArrayTagCompound);

        rotation = ForgeDirection.getOrientation(nbtTagCompound.getInteger("rotation"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        NBTTagCompound alchemyArrayTagCompound = new NBTTagCompound();
        alchemyArray.writeToNBT(alchemyArrayTagCompound);

        nbtTagCompound.setInteger("rotation", rotation.ordinal());

        nbtTagCompound.setTag("alchemyArray", alchemyArrayTagCompound);
    }

    @Override
    public void updateEntity()
    {
//        LogHelper.info(String.format("x: %s, y: %s, z: %s", this.xCoord, this.yCoord, this.zCoord));
    }
}

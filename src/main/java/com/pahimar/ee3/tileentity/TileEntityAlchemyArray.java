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

public class TileEntityAlchemyArray extends TileEntityEE
{
    private AlchemyArray alchemyArray;
    private int rotation;

    public TileEntityAlchemyArray()
    {
        super();
        alchemyArray = new AlchemyArray();
        rotation = 0;
    }

    public AlchemyArray getAlchemyArray()
    {
        return alchemyArray;
    }

    public void addGlyphToAlchemyArray(Glyph glyph, int size)
    {
        alchemyArray.addGlyph(new Glyph(glyph, size));
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        this.rotation = rotation;

        if (this.rotation < 0)
        {
            this.rotation = 0;
        }
        else
        {
            this.rotation = this.rotation % 4;
        }
    }

    public void rotate()
    {
        rotate(true);
    }

    /**
     * @param rotateClockwise true if we should rotate clockwise, false if we rotate counter clockwise
     */
    public void rotate(boolean rotateClockwise)
    {
        if (rotateClockwise)
        {
            this.rotation = (rotation + 1) % 4;
        }
        else
        {
            this.rotation -= 1;

            if (this.rotation < 0)
            {
                this.rotation = 3;
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

        rotation = nbtTagCompound.getInteger("rotation");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        NBTTagCompound alchemyArrayTagCompound = new NBTTagCompound();
        alchemyArray.writeToNBT(alchemyArrayTagCompound);

        nbtTagCompound.setInteger("rotation", rotation);

        nbtTagCompound.setTag("alchemyArray", alchemyArrayTagCompound);
    }
}

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

    public TileEntityAlchemyArray()
    {
        super();
        alchemyArray = new AlchemyArray();
    }

    public AlchemyArray getAlchemyArray()
    {
        return alchemyArray;
    }

    public void addGlyphToAlchemyArray(Glyph glyph)
    {
        alchemyArray.addGlyph(glyph);
    }

    public void addGlyphToAlchemyArray(Glyph glyph, int size)
    {
        alchemyArray.addGlyph(new Glyph(glyph, size));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return AxisAlignedBB.getBoundingBox(xCoord - alchemyArray.getLargestGlyphSize(), yCoord, zCoord - alchemyArray.getLargestGlyphSize(), xCoord + alchemyArray.getLargestGlyphSize(), yCoord, zCoord + alchemyArray.getLargestGlyphSize());
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
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        NBTTagCompound alchemyArrayTagCompound = new NBTTagCompound();
        alchemyArray.writeToNBT(alchemyArrayTagCompound);

        nbtTagCompound.setTag("alchemyArray", alchemyArrayTagCompound);
    }
}

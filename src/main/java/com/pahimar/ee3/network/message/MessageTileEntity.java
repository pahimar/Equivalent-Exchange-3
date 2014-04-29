package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityEE;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageTileEntity implements IMessage
{
    public int x, y, z;
    public byte orientation, state;
    public String customName, owner;

    public MessageTileEntity()
    {
    }

    public MessageTileEntity(TileEntityEE tileEntityEE)
    {
        this.x = tileEntityEE.xCoord;
        this.y = tileEntityEE.yCoord;
        this.z = tileEntityEE.zCoord;
        this.orientation = (byte) tileEntityEE.getOrientation().ordinal();
        this.state = (byte) tileEntityEE.getState();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.state = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
    }
}

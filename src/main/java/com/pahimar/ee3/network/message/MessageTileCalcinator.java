package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityCalcinator;
import com.pahimar.ee3.tileentity.TileEntityEE;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class MessageTileCalcinator implements IMessage, IMessageHandler<MessageTileCalcinator, IMessage>
{
    public int x, y, z;
    public byte orientation, state;
    public String customName;
    public UUID ownerUUID;
    public byte leftStackSize, leftStackMeta, rightStackSize, rightStackMeta;

    public MessageTileCalcinator()
    {
    }

    public MessageTileCalcinator(TileEntityCalcinator tileEntityCalcinator)
    {
        this.x = tileEntityCalcinator.xCoord;
        this.y = tileEntityCalcinator.yCoord;
        this.z = tileEntityCalcinator.zCoord;
        this.orientation = (byte) tileEntityCalcinator.getOrientation().ordinal();
        this.state = (byte) tileEntityCalcinator.getState();
        this.customName = tileEntityCalcinator.getCustomName();
        this.ownerUUID = tileEntityCalcinator.getOwnerUUID();
        this.leftStackSize = tileEntityCalcinator.leftStackSize;
        this.leftStackMeta = tileEntityCalcinator.leftStackMeta;
        this.rightStackSize = tileEntityCalcinator.rightStackSize;
        this.rightStackMeta = tileEntityCalcinator.rightStackMeta;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        this.ownerUUID = new UUID(buf.readLong(), buf.readLong());
        this.leftStackSize = buf.readByte();
        this.leftStackMeta = buf.readByte();
        this.rightStackSize = buf.readByte();
        this.rightStackMeta = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        buf.writeLong(ownerUUID.getMostSignificantBits());
        buf.writeLong(ownerUUID.getLeastSignificantBits());
        buf.writeByte(leftStackSize);
        buf.writeByte(leftStackMeta);
        buf.writeByte(rightStackSize);
        buf.writeByte(rightStackMeta);
    }

    @Override
    public IMessage onMessage(MessageTileCalcinator message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityCalcinator)
        {
            ((TileEntityEE) tileEntity).setOrientation(message.orientation);
            ((TileEntityEE) tileEntity).setState(message.state);
            ((TileEntityEE) tileEntity).setCustomName(message.customName);
            ((TileEntityEE) tileEntity).setOwnerUUID(message.ownerUUID);
            ((TileEntityCalcinator) tileEntity).leftStackSize = message.leftStackSize;
            ((TileEntityCalcinator) tileEntity).leftStackMeta = message.leftStackMeta;
            ((TileEntityCalcinator) tileEntity).rightStackSize = message.rightStackSize;
            ((TileEntityCalcinator) tileEntity).rightStackMeta = message.rightStackMeta;
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityCalcinator - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, ownerUUID:%s, leftStackSize: %s, leftStackMeta: %s, rightStackSize: %s, rightStackMeta: %s", x, y, z, orientation, state, customName, ownerUUID, leftStackSize, leftStackMeta, rightStackSize, rightStackMeta);
    }
}

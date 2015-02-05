package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityGlassBell;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class MessageTileEntityGlassBell implements IMessage, IMessageHandler<MessageTileEntityGlassBell, IMessage>
{
    public int x, y, z;
    public byte orientation;
    public byte state;
    public String customName;
    public UUID ownerUUID;
    public ItemStack outputItemStack;

    public MessageTileEntityGlassBell()
    {
    }

    public MessageTileEntityGlassBell(TileEntityGlassBell tileEntityGlassBell, ItemStack outputItemStack)
    {
        this.x = tileEntityGlassBell.xCoord;
        this.y = tileEntityGlassBell.yCoord;
        this.z = tileEntityGlassBell.zCoord;
        this.orientation = (byte) tileEntityGlassBell.getOrientation().ordinal();
        this.state = (byte) tileEntityGlassBell.getState();
        this.customName = tileEntityGlassBell.getCustomName();
        this.ownerUUID = tileEntityGlassBell.getOwnerUUID();
        this.outputItemStack = outputItemStack;
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
        if (buf.readBoolean())
        {
            this.ownerUUID = new UUID(buf.readLong(), buf.readLong());
        }
        else
        {
            this.ownerUUID = null;
        }
        outputItemStack = ByteBufUtils.readItemStack(buf);
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
        if (ownerUUID != null)
        {
            buf.writeBoolean(true);
            buf.writeLong(ownerUUID.getMostSignificantBits());
            buf.writeLong(ownerUUID.getLeastSignificantBits());
        }
        else
        {
            buf.writeBoolean(false);
        }
        ByteBufUtils.writeItemStack(buf, outputItemStack);
    }

    @Override
    public IMessage onMessage(MessageTileEntityGlassBell message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityGlassBell)
        {
            ((TileEntityGlassBell) tileEntity).setOrientation(message.orientation);
            ((TileEntityGlassBell) tileEntity).setState(message.state);
            ((TileEntityGlassBell) tileEntity).setCustomName(message.customName);
            ((TileEntityGlassBell) tileEntity).setOwnerUUID(message.ownerUUID);
            ((TileEntityGlassBell) tileEntity).outputItemStack = message.outputItemStack;

            //NAME UPDATE
            FMLClientHandler.instance().getClient().theWorld.func_147451_t(message.x, message.y, message.z);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityGlassBell - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, ownerUUID:%s, outputItemStack: %s", x, y, z, orientation, state, customName, ownerUUID, outputItemStack.toString());
    }
}

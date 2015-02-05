package com.pahimar.ee3.network.message;

import com.pahimar.ee3.reference.Colors;
import com.pahimar.ee3.tileentity.TileEntityAludel;
import com.pahimar.ee3.util.ColorHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class MessageTileEntityAludel implements IMessage, IMessageHandler<MessageTileEntityAludel, IMessage>
{
    public int x, y, z;
    public byte orientation;
    public byte state;
    public String customName;
    public UUID ownerUUID;
    public int itemId, metaData, stackSize, itemColor;

    public MessageTileEntityAludel()
    {
    }

    public MessageTileEntityAludel(TileEntityAludel tileEntityAludel, ItemStack outputItemStack)
    {
        this.x = tileEntityAludel.xCoord;
        this.y = tileEntityAludel.yCoord;
        this.z = tileEntityAludel.zCoord;
        this.orientation = (byte) tileEntityAludel.getOrientation().ordinal();
        this.state = (byte) tileEntityAludel.getState();
        this.customName = tileEntityAludel.getCustomName();
        this.ownerUUID = tileEntityAludel.getOwnerUUID();

        if (outputItemStack != null)
        {
            this.itemId = Item.getIdFromItem(outputItemStack.getItem());
            this.metaData = outputItemStack.getItemDamage();
            this.stackSize = outputItemStack.stackSize;
            this.itemColor = ColorHelper.getColor(outputItemStack);
        }
        else
        {
            this.itemId = -1;
            this.metaData = 0;
            this.stackSize = 0;
            this.itemColor = 0;
        }
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
        this.itemId = buf.readInt();
        this.metaData = buf.readInt();
        this.stackSize = buf.readInt();
        this.itemColor = buf.readInt();
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
        buf.writeInt(itemId);
        buf.writeInt(metaData);
        buf.writeInt(stackSize);
        buf.writeInt(itemColor);
    }

    @Override
    public IMessage onMessage(MessageTileEntityAludel message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityAludel)
        {
            ((TileEntityAludel) tileEntity).setOrientation(message.orientation);
            ((TileEntityAludel) tileEntity).setState(message.state);
            ((TileEntityAludel) tileEntity).setCustomName(message.customName);
            ((TileEntityAludel) tileEntity).setOwnerUUID(message.ownerUUID);

            ItemStack outputItemStack = null;

            if (message.itemId != -1)
            {
                outputItemStack = new ItemStack(Item.getItemById(message.itemId), message.stackSize, message.metaData);
                if (message.itemColor != Integer.parseInt(Colors.PURE_WHITE, 16))
                {
                    ColorHelper.setColor(outputItemStack, itemColor);
                }
            }

            ((TileEntityAludel) tileEntity).outputItemStack = outputItemStack;

            //NAME UPDATE
            FMLClientHandler.instance().getClient().theWorld.func_147451_t(message.x, message.y, message.z);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityAludel - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, ownerUUID:%s, itemId: %s, metaData: %s, stackSize: %s, itemColor: %s", x, y, z, orientation, state, customName, ownerUUID, itemId, metaData, stackSize, itemColor);
    }
}

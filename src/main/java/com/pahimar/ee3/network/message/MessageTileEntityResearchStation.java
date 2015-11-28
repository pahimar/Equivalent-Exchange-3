package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityResearchStation;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityResearchStation implements IMessage, IMessageHandler<MessageTileEntityResearchStation, IMessage>
{
    public int x, y, z;
    public ItemStack alchenomiconItemStack;

    public MessageTileEntityResearchStation()
    {
    }

    public MessageTileEntityResearchStation(TileEntityResearchStation tileEntityResearchStation)
    {
        this.x = tileEntityResearchStation.xCoord;
        this.y = tileEntityResearchStation.yCoord;
        this.z = tileEntityResearchStation.zCoord;
        this.alchenomiconItemStack = tileEntityResearchStation.getStackInSlot(TileEntityResearchStation.ALCHENOMICON_SLOT_INVENTORY_INDEX);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        alchenomiconItemStack = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        ByteBufUtils.writeItemStack(buf, alchenomiconItemStack);
    }

    @Override
    public IMessage onMessage(MessageTileEntityResearchStation message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityResearchStation)
        {
            ((TileEntityResearchStation) tileEntity).setInventorySlotContents(TileEntityResearchStation.ALCHENOMICON_SLOT_INVENTORY_INDEX, message.alchenomiconItemStack);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityResearchStation - x:%s, y:%s, z:%s, tome:%s", x, y, z, alchenomiconItemStack.toString());
    }
}

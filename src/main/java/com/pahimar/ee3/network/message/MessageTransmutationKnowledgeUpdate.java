package com.pahimar.ee3.network.message;

import com.pahimar.ee3.inventory.ContainerTransmutationTablet;
import com.pahimar.ee3.knowledge.TransmutationKnowledge;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.CompressionHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

import java.util.Collection;

public class MessageTransmutationKnowledgeUpdate implements IMessage, IMessageHandler<MessageTransmutationKnowledgeUpdate, IMessage>
{
    public int xCoord, yCoord, zCoord;
    public TransmutationKnowledge transmutationKnowledge;

    public MessageTransmutationKnowledgeUpdate()
    {

    }

    public MessageTransmutationKnowledgeUpdate(TileEntityTransmutationTablet tileEntityTransmutationTablet, Collection<ItemStack> knownTransmutationsCollection)
    {
        if (tileEntityTransmutationTablet != null)
        {
            this.xCoord = tileEntityTransmutationTablet.xCoord;
            this.yCoord = tileEntityTransmutationTablet.yCoord;
            this.zCoord = tileEntityTransmutationTablet.zCoord;
        }
        else
        {
            this.xCoord = 0;
            this.yCoord = Integer.MIN_VALUE;
            this.zCoord = 0;
        }

        if (knownTransmutationsCollection != null)
        {
            this.transmutationKnowledge = new TransmutationKnowledge(knownTransmutationsCollection);
        }
        else
        {
            this.transmutationKnowledge = new TransmutationKnowledge();
        }
    }

    public MessageTransmutationKnowledgeUpdate(int xCoord, int yCoord, int zCoord, Collection<ItemStack> knownTransmutationsCollection)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;

        if (knownTransmutationsCollection != null)
        {
            this.transmutationKnowledge = new TransmutationKnowledge(knownTransmutationsCollection);
        }
        else
        {
            this.transmutationKnowledge = new TransmutationKnowledge();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.xCoord = buf.readInt();
        this.yCoord = buf.readInt();
        this.zCoord = buf.readInt();

        byte[] compressedString = null;
        int readableBytes = buf.readInt();

        if (readableBytes > 0)
        {
            compressedString = buf.readBytes(readableBytes).array();
        }

        if (compressedString != null)
        {
            String uncompressedString = CompressionHelper.decompressStringFromByteArray(compressedString);
            this.transmutationKnowledge = TransmutationKnowledge.createFromJson(uncompressedString);
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(xCoord);
        buf.writeInt(yCoord);
        buf.writeInt(zCoord);

        byte[] compressedString = null;

        if (transmutationKnowledge != null)
        {
            compressedString = CompressionHelper.compressStringToByteArray(transmutationKnowledge.toJson());
        }

        if (compressedString != null)
        {
            buf.writeInt(compressedString.length);
            buf.writeBytes(compressedString);
        }
        else
        {
            buf.writeInt(0);
        }
    }

    @Override
    public IMessage onMessage(MessageTransmutationKnowledgeUpdate message, MessageContext ctx)
    {
        if (message.yCoord != Integer.MIN_VALUE)
        {
            if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiContainer)
            {
                GuiContainer guiContainer = (GuiContainer) FMLClientHandler.instance().getClient().currentScreen;

                if (guiContainer.inventorySlots instanceof ContainerTransmutationTablet)
                {
                    TileEntityTransmutationTablet tileEntityTransmutationTablet = ((ContainerTransmutationTablet) guiContainer.inventorySlots).tileEntityTransmutationTablet;

                    if (tileEntityTransmutationTablet.xCoord == message.xCoord && tileEntityTransmutationTablet.yCoord == message.yCoord && tileEntityTransmutationTablet.zCoord == message.zCoord)
                    {
                        ((ContainerTransmutationTablet) guiContainer.inventorySlots).handleTransmutationKnowledgeUpdate(message.transmutationKnowledge);
                    }
                }
            }
        }

        return null;
    }
}

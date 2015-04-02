package com.pahimar.ee3.network.message;

import com.pahimar.ee3.inventory.ContainerTransmutationTablet;
import com.pahimar.ee3.knowledge.TransmutationKnowledge;
import com.pahimar.ee3.util.CompressionHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Collection;

public class MessageTransmutationKnowledgeUpdate implements IMessage, IMessageHandler<MessageTransmutationKnowledgeUpdate, IMessage>
{
    public TransmutationKnowledge transmutationKnowledge;

    public MessageTransmutationKnowledgeUpdate()
    {
    }

    public MessageTransmutationKnowledgeUpdate(Collection<ItemStack> knownTransmutationsCollection)
    {
        this.transmutationKnowledge = new TransmutationKnowledge(knownTransmutationsCollection);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
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
        EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

        if (entityPlayer != null && entityPlayer.openContainer instanceof ContainerTransmutationTablet)
        {
            ((ContainerTransmutationTablet) entityPlayer.openContainer).handleTransmutationKnowledgeUpdate(message.transmutationKnowledge);
        }

        return null;
    }
}

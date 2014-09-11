package com.pahimar.ee3.network.message;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MessageSyncEnergyValues implements IMessage, IMessageHandler<MessageSyncEnergyValues, IMessage>
{
    public NBTTagCompound energyValueRegistryNBT;

    public MessageSyncEnergyValues()
    {
    }

    public MessageSyncEnergyValues(EnergyValueRegistry energyValueRegistry)
    {
        energyValueRegistryNBT = new NBTTagCompound();
        energyValueRegistry.writeToNBT(energyValueRegistryNBT);
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        byte[] compressedNBT = null;
        int readableBytes = buf.readInt();

        if (readableBytes > 0)
        {
            compressedNBT = buf.readBytes(readableBytes).array();
        }

        if (compressedNBT != null)
        {
            try
            {
                this.energyValueRegistryNBT = CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedNBT));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf)
    {
        byte[] compressedNBT = null;

        try
        {
            compressedNBT = CompressedStreamTools.compress(energyValueRegistryNBT);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (compressedNBT != null)
        {
            buf.writeInt(compressedNBT.length);
            buf.writeBytes(compressedNBT);
        }
        else
        {
            buf.writeInt(0);
        }
    }

    /**
     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
     * is needed.
     *
     * @param message The message
     * @param ctx
     * @return an optional return message
     */
    @Override
    public IMessage onMessage(MessageSyncEnergyValues message, MessageContext ctx)
    {
        if (message.energyValueRegistryNBT != null)
        {
            EnergyValueRegistry.getInstance().readFromNBT(message.energyValueRegistryNBT);
            LogHelper.info("Client successfully received EnergyValues from server");
        }
        else
        {
            LogHelper.info("Client failed to receive EnergyValues from server - falling back to local EnergyValues");
        }

        return null;
    }
}

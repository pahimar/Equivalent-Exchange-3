package com.pahimar.ee3.network.message;

import com.google.gson.stream.JsonReader;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.EnergyValueStackMapping;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.CompressionHelper;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.TreeMap;

public class MessageSyncEnergyValues implements IMessage, IMessageHandler<MessageSyncEnergyValues, IMessage>
{
    public String jsonEnergyValueRegistry;

    public MessageSyncEnergyValues()
    {
    }

    public MessageSyncEnergyValues(EnergyValueRegistry energyValueRegistry)
    {
        this.jsonEnergyValueRegistry = energyValueRegistry.toJson();
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        byte[] compressedBytes = null;
        int readableBytes = buf.readInt();

        if (readableBytes > 0)
        {
            compressedBytes = buf.readBytes(readableBytes).array();
        }

        if (compressedBytes != null)
        {
            this.jsonEnergyValueRegistry = CompressionHelper.decompressStringFromByteArray(compressedBytes);
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
        byte[] compressedBytes = null;

        if (jsonEnergyValueRegistry != null)
        {
            compressedBytes = CompressionHelper.compressStringToByteArray(jsonEnergyValueRegistry);
        }

        if (compressedBytes != null)
        {
            buf.writeInt(compressedBytes.length);
            buf.writeBytes(compressedBytes);
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
        if (message.jsonEnergyValueRegistry != null)
        {
            Map<WrappedStack, EnergyValue> energyValueStackMap = new TreeMap<WrappedStack, EnergyValue>();

            try
            {
                JsonReader jsonReader = new JsonReader(new StringReader(message.jsonEnergyValueRegistry));
                jsonReader.beginArray();
                while (jsonReader.hasNext())
                {
                    EnergyValueStackMapping energyValueStackMapping = EnergyValueStackMapping.jsonSerializer.fromJson(jsonReader, EnergyValueStackMapping.class);
                    if (energyValueStackMapping != null)
                    {
                        energyValueStackMap.put(energyValueStackMapping.wrappedStack, energyValueStackMapping.energyValue);
                    }
                }
                jsonReader.endArray();
                jsonReader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            EnergyValueRegistry.getInstance().loadFromMap(energyValueStackMap);
            LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Client successfully received EnergyValues from server");
        }
        else
        {
            LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Client failed to receive EnergyValues from server - falling back to local EnergyValues");
        }

        return null;
    }
}

package com.pahimar.ee3.network.message;

import com.google.gson.JsonParseException;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.CompressionHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.util.Map;

public class MessageSyncEnergyValues implements IMessage, IMessageHandler<MessageSyncEnergyValues, IMessage> {

    public String jsonString;

    public MessageSyncEnergyValues() {
        this.jsonString = SerializationHelper.GSON.toJson(EnergyValueRegistry.INSTANCE.getEnergyValues(), SerializationHelper.ENERGY_VALUE_MAP_TYPE);
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {

        byte[] compressedString;
        int compressedStringLength = buf.readInt();

        if (compressedStringLength > 0) {
            compressedString = buf.readBytes(compressedStringLength).array();

            if (compressedString != null) {
                this.jsonString = CompressionHelper.decompress(compressedString);
            }
        }
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf) {

        byte[] compressedString = null;

        if (jsonString != null) {

            compressedString = CompressionHelper.compress(jsonString);

            if (compressedString != null) {
                buf.writeInt(compressedString.length);
                buf.writeBytes(compressedString);
            }
            else {
                buf.writeInt(0);
            }
        }
        else {
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
    public IMessage onMessage(MessageSyncEnergyValues message, MessageContext ctx) {

        if (message.jsonString != null) {

            Map<WrappedStack, EnergyValue> valueMap = null;

            try {
                valueMap = SerializationHelper.GSON.fromJson(message.jsonString, SerializationHelper.ENERGY_VALUE_MAP_TYPE);
            }
            catch (JsonParseException e) {
                // TODO Log an error message here
            }

            if (valueMap != null) {
                EnergyValueRegistry.INSTANCE.load(valueMap);
                LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Client successfully received {} energy values from server", valueMap.size());
            }
            else {
                // TODO Log a message here
            }
        }
        else {
            LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Client failed to receive energy values from server - falling back to local values");
        }

        return null;
    }
}

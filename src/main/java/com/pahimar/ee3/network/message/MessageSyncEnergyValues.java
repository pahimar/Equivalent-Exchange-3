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

    public Map<WrappedStack, EnergyValue> valueMap;

    public MessageSyncEnergyValues() {
        this.valueMap = EnergyValueRegistry.INSTANCE.getEnergyValues();
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {

        int compressedJsonLength = buf.readInt();

        if (compressedJsonLength != 0) {
            byte[] compressedValueMap = buf.readBytes(compressedJsonLength).array();

            if (compressedValueMap != null) {

                String jsonString = CompressionHelper.decompress(compressedValueMap);

                try {
                    valueMap = SerializationHelper.GSON.fromJson(jsonString, SerializationHelper.ENERGY_VALUE_MAP_TYPE);
                }
                catch (JsonParseException e) {
                    LogHelper.warn("Failed to read energy value map from server");
                    valueMap = null;
                }
            }
            else {
                valueMap = null;
            }
        }
        else {
            valueMap = null;
        }
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf) {

        if (valueMap != null) {

            byte[] compressedValueMap = CompressionHelper.compress(SerializationHelper.GSON.toJson(valueMap, SerializationHelper.ENERGY_VALUE_MAP_TYPE));

            if (compressedValueMap != null) {
                buf.writeInt(compressedValueMap.length);
                buf.writeBytes(compressedValueMap);
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

        if (message.valueMap != null) {
            EnergyValueRegistry.INSTANCE.load(message.valueMap);
            LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Client successfully received {} energy values from server", message.valueMap.size());
        }
        else {
            LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Client failed to receive energy values from server - falling back to local values");
        }

        return null;
    }
}

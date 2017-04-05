package com.pahimar.ee.network.message;

import com.google.gson.JsonParseException;
import com.pahimar.ee.api.exchange.EnergyValue;
import com.pahimar.ee.exchange.EnergyValueRegistry;
import com.pahimar.ee.exchange.WrappedStack;
import com.pahimar.ee.util.CompressionUtils;
import com.pahimar.ee.util.LogHelper;
import com.pahimar.ee.util.SerializationHelper;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Map;

public class MessageSyncEnergyValues implements IMessage {

    public Map<WrappedStack, EnergyValue> valueMap;

    public MessageSyncEnergyValues() {
        this.valueMap = EnergyValueRegistry.INSTANCE.getEnergyValues();
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        int compressedJsonLength = buf.readInt();

        if (compressedJsonLength != 0) {
            byte[] compressedValueMap = buf.readBytes(compressedJsonLength).array();

            if (compressedValueMap != null) {

                String jsonString = CompressionUtils.decompress(compressedValueMap);

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

    @Override
    public void toBytes(ByteBuf buf) {

        if (valueMap != null) {

            byte[] compressedValueMap = CompressionUtils.compress(SerializationHelper.GSON.toJson(valueMap, SerializationHelper.ENERGY_VALUE_MAP_TYPE));

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

    public static class MessageHandler implements IMessageHandler<MessageSyncEnergyValues, IMessage> {

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
}

package com.pahimar.ee3.network.message;

import com.google.gson.JsonParseException;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.CompressionUtils;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Map;
import java.util.TreeMap;

public class MessageSetEnergyValue implements IMessage {

    public Map<WrappedStack, EnergyValue> energyValueMap;

    public MessageSetEnergyValue() {
    }

    public MessageSetEnergyValue(WrappedStack wrappedStack, EnergyValue energyValue) {

        this.energyValueMap = new TreeMap<>();
        if (wrappedStack != null && wrappedStack.getObject() != null && energyValue != null) {
            this.energyValueMap.put(wrappedStack, energyValue);
        }
    }

    public MessageSetEnergyValue(Map<WrappedStack, EnergyValue> energyValueMap) {
        this.energyValueMap = energyValueMap;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        int compressedJsonLength = buf.readInt();

        if (compressedJsonLength != 0) {
            byte[] compressedValueMap = buf.readBytes(compressedJsonLength).array();

            if (compressedValueMap != null) {

                String jsonString = CompressionUtils.decompress(compressedValueMap);

                try {
                    energyValueMap = SerializationHelper.GSON.fromJson(jsonString, SerializationHelper.ENERGY_VALUE_MAP_TYPE);
                }
                catch (JsonParseException e) {
                    LogHelper.warn("Failed to read energy value map from server");
                    energyValueMap = null;
                }
            }
            else {
                energyValueMap = null;
            }
        }
        else {
            energyValueMap = null;
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {

        if (energyValueMap != null) {

            byte[] compressedValueMap = CompressionUtils.compress(SerializationHelper.GSON.toJson(energyValueMap, SerializationHelper.ENERGY_VALUE_MAP_TYPE));

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

    public static class MessageHandler implements IMessageHandler<MessageSetEnergyValue, IMessage> {

        @Override
        public IMessage onMessage(MessageSetEnergyValue message, MessageContext ctx) {

            if (message.energyValueMap != null) {
                for (WrappedStack wrappedStack : message.energyValueMap.keySet()) {

                    EnergyValue energyValue = message.energyValueMap.get(wrappedStack);
                    EnergyValueRegistryProxy.setEnergyValue(wrappedStack, energyValue);
                    EnergyValueRegistry.INSTANCE.setShouldSave(false);
                    LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Client successfully received new energy value '{}' for object '{}'", energyValue, wrappedStack);
                }
            }

            return null;
        }
    }
}

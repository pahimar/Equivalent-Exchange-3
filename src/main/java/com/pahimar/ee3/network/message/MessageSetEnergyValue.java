package com.pahimar.ee3.network.message;

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

public class MessageSetEnergyValue implements IMessage, IMessageHandler<MessageSetEnergyValue, IMessage>
{
    public EnergyValueStackMapping energyValueStackMapping;

    public MessageSetEnergyValue()
    {
    }

    public MessageSetEnergyValue(WrappedStack wrappedStack, EnergyValue energyValue)
    {
        this.energyValueStackMapping = new EnergyValueStackMapping(wrappedStack, energyValue);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        byte[] compressedEnergyValueStackMapping = null;
        int energyValueStackMappingByteCount = buf.readInt();

        if (energyValueStackMappingByteCount > 0)
        {
            compressedEnergyValueStackMapping = buf.readBytes(energyValueStackMappingByteCount).array();
        }

        if (compressedEnergyValueStackMapping != null)
        {
            String decompressedEnergyValueStackMapping = CompressionHelper.decompressStringFromByteArray(compressedEnergyValueStackMapping);
            this.energyValueStackMapping = EnergyValueStackMapping.createFromJson(decompressedEnergyValueStackMapping);
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        byte[] compressedBytes = null;
        String jsonEnergyValueStackMapping = this.energyValueStackMapping.toJson();


        if (jsonEnergyValueStackMapping != null)
        {
            compressedBytes = CompressionHelper.compressStringToByteArray(jsonEnergyValueStackMapping);
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

    @Override
    public IMessage onMessage(MessageSetEnergyValue message, MessageContext ctx)
    {
        if (message.energyValueStackMapping != null && message.energyValueStackMapping.wrappedStack != null && message.energyValueStackMapping.energyValue != null)
        {
            EnergyValueRegistry.getInstance().setEnergyValue(message.energyValueStackMapping.wrappedStack, message.energyValueStackMapping.energyValue);
            LogHelper.info(String.format("Client successfully received new EnergyValue '%s' for object '%s'", message.energyValueStackMapping.wrappedStack, message.energyValueStackMapping.energyValue));
        }
        else
        {
            LogHelper.info("Client failed to receive new EnergyValue from server");
        }

        return null;
    }
}

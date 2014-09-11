package com.pahimar.ee3.network.message;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MessageSetEnergyValue implements IMessage, IMessageHandler<MessageSetEnergyValue, IMessage>
{
    public WrappedStack wrappedStack;
    public EnergyValue energyValue;

    public MessageSetEnergyValue()
    {
    }

    public MessageSetEnergyValue(WrappedStack wrappedStack, EnergyValue energyValue)
    {
        this.wrappedStack = wrappedStack;
        this.energyValue = energyValue;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        byte[] compressedWrappedStack = null;
        int wrappedStackByteCount = buf.readInt();

        if (wrappedStackByteCount > 0)
        {
            compressedWrappedStack = buf.readBytes(wrappedStackByteCount).array();
        }

        byte[] compressedEnergyValue = null;
        int energyValueByteCount = buf.readInt();

        if (energyValueByteCount > 0)
        {
            compressedEnergyValue = buf.readBytes(energyValueByteCount).array();
        }

        if (compressedWrappedStack != null && compressedEnergyValue != null)
        {
            try
            {
                this.wrappedStack = WrappedStack.fromNBTTagCompound(CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedWrappedStack)));
                this.energyValue = EnergyValue.loadEnergyValueFromNBT(CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedWrappedStack)));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        byte[] compressedWrappedStack = null;
        byte[] compressedEnergyValue = null;

        try
        {
            compressedWrappedStack = CompressedStreamTools.compress(WrappedStack.toNBTTagCompound(wrappedStack));
            compressedEnergyValue = CompressedStreamTools.compress(EnergyValue.writeEnergyValueToNBT(energyValue));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (compressedWrappedStack != null && compressedEnergyValue != null)
        {
            buf.writeInt(compressedWrappedStack.length);
            buf.writeBytes(compressedWrappedStack);

            buf.writeInt(compressedEnergyValue.length);
            buf.writeBytes(compressedEnergyValue);
        }
        else
        {
            buf.writeInt(0);
        }
    }

    @Override
    public IMessage onMessage(MessageSetEnergyValue message, MessageContext ctx)
    {
        if (message.wrappedStack != null && message.energyValue != null)
        {
            EnergyValueRegistry.getInstance().setEnergyValue(message.wrappedStack, message.energyValue);
            LogHelper.info(String.format("Client successfully received new EnergyValue '%s' for object '%s'", message.energyValue, message.wrappedStack));
        }
        else
        {
            LogHelper.info("Client failed to receive new EnergyValue from server");
        }

        return null;
    }
}

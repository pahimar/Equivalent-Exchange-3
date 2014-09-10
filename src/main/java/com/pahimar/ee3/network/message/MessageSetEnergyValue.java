package com.pahimar.ee3.network.message;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.WrappedStack;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import java.io.IOException;

public class MessageSetEnergyValue implements IMessage, IMessageHandler<MessageSetEnergyValue, IMessage>
{
    public NBTTagCompound energyValueMappingNBT;

    public MessageSetEnergyValue()
    {
    }

    public MessageSetEnergyValue(WrappedStack wrappedStack, EnergyValue energyValue)
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        byte[] compressedNBT = null;

        try
        {
            compressedNBT = CompressedStreamTools.compress(energyValueMappingNBT);
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

    @Override
    public IMessage onMessage(MessageSetEnergyValue message, MessageContext ctx)
    {
        return null;
    }
}

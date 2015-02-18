package com.pahimar.ee3.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageGuiElementClicked implements IMessage, IMessageHandler<MessageGuiElementClicked, IMessage>
{
    public int guiId;
    public String elementName;
    public int buttonPressed;

    public MessageGuiElementClicked()
    {

    }

    public MessageGuiElementClicked(int guiId, String elementName, int buttonPressed)
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(guiId);
        buf.writeInt(elementName.length());
    }

    @Override
    public IMessage onMessage(MessageGuiElementClicked message, MessageContext ctx)
    {
        return null;
    }
}

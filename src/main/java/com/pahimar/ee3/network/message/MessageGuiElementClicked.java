package com.pahimar.ee3.network.message;

import com.pahimar.ee3.inventory.element.IElementButtonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageGuiElementClicked implements IMessage, IMessageHandler<MessageGuiElementClicked, IMessage>
{
    public String elementName;
    public int buttonPressed;

    public MessageGuiElementClicked()
    {

    }

    public MessageGuiElementClicked(String elementName, int buttonPressed)
    {
        this.elementName = elementName;
        this.buttonPressed = buttonPressed;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        int elementNameLength = buf.readInt();
        this.elementName = new String(buf.readBytes(elementNameLength).array());
        this.buttonPressed = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(elementName.length());
        buf.writeBytes(elementName.getBytes());
        buf.writeInt(buttonPressed);
    }

    @Override
    public IMessage onMessage(MessageGuiElementClicked message, MessageContext ctx)
    {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

        if (entityPlayer != null)
        {
            if (entityPlayer.openContainer instanceof IElementButtonHandler)
            {
                ((IElementButtonHandler) entityPlayer.openContainer).handleElementButtonClick(message.elementName, message.buttonPressed);
            }
        }

        return null;
    }
}

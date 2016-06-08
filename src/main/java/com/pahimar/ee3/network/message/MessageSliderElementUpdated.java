package com.pahimar.ee3.network.message;

import com.pahimar.ee3.inventory.element.IElementSliderHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSliderElementUpdated implements IMessage {

    public String elementName;
    public int elementValue;

    public MessageSliderElementUpdated() {
    }

    public MessageSliderElementUpdated(String elementName, int elementValue) {
        this.elementName = elementName;
        this.elementValue = elementValue;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int elementNameLength = buf.readInt();
        this.elementName = new String(buf.readBytes(elementNameLength).array());
        this.elementValue = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {

        if (elementName != null) {
            buf.writeInt(elementName.length());
            buf.writeBytes(elementName.getBytes());
        }
        else {
            buf.writeInt(0);
        }
        buf.writeInt(elementValue);
    }

    public static class MessageHandler implements IMessageHandler<MessageSliderElementUpdated, IMessage> {

        @Override
        public IMessage onMessage(MessageSliderElementUpdated message, MessageContext ctx) {

            EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

            if (entityPlayer != null && entityPlayer.openContainer instanceof IElementSliderHandler) {
                ((IElementSliderHandler) entityPlayer.openContainer).handleElementSliderUpdate(message.elementName, message.elementValue);
            }

            return null;
        }
    }
}

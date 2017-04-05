package com.pahimar.ee.network.message;

import com.pahimar.ee.inventory.element.IElementTextFieldHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageGuiElementTextFieldUpdate implements IMessage {

    public String elementName, elementText;

    public MessageGuiElementTextFieldUpdate() {
    }

    public MessageGuiElementTextFieldUpdate(String elementName, String elementText) {
        this.elementName = elementName;
        this.elementText = elementText;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int elementNameLength = buf.readInt();
        this.elementName = new String(buf.readBytes(elementNameLength).array());
        int elementTextLength = buf.readInt();
        this.elementText = new String(buf.readBytes(elementTextLength).array());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(elementName.length());
        buf.writeBytes(elementName.getBytes());
        buf.writeInt(elementText.length());
        buf.writeBytes(elementText.getBytes());
    }

    public static class MessageHandler implements IMessageHandler<MessageGuiElementTextFieldUpdate, IMessage> {

        @Override
        public IMessage onMessage(MessageGuiElementTextFieldUpdate message, MessageContext ctx) {

            EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

            if (entityPlayer != null && entityPlayer.openContainer instanceof IElementTextFieldHandler) {
                ((IElementTextFieldHandler) entityPlayer.openContainer).handleElementTextFieldUpdate(message.elementName, message.elementText);
            }

            return null;
        }
    }
}

package com.pahimar.ee.network.message;

import com.pahimar.ee.inventory.element.IElementButtonHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageGuiElementClicked implements IMessage {

    public String elementName;
    public int buttonPressed;

    public MessageGuiElementClicked() {
    }

    public MessageGuiElementClicked(String elementName, int buttonPressed) {
        this.elementName = elementName;
        this.buttonPressed = buttonPressed;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        int elementNameLength = buf.readInt();
        this.elementName = new String(buf.readBytes(elementNameLength).array());
        this.buttonPressed = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(elementName.length());
        buf.writeBytes(elementName.getBytes());
        buf.writeInt(buttonPressed);
    }

    public static class MessageHandler implements IMessageHandler<MessageGuiElementClicked, IMessage> {

        @Override
        public IMessage onMessage(MessageGuiElementClicked message, MessageContext ctx) {

            EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

            if (entityPlayer != null && entityPlayer.openContainer instanceof IElementButtonHandler) {
                ((IElementButtonHandler) entityPlayer.openContainer).handleElementButtonClick(message.elementName, message.buttonPressed);
            }

            return null;
        }
    }
}

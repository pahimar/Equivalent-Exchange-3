package com.pahimar.ee3.network.message;

import com.pahimar.ee3.inventory.ContainerEE;
import com.pahimar.repackage.cofh.lib.gui.element.ElementTextField;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageGuiElementTextFieldUpdate implements IMessage, IMessageHandler<MessageGuiElementTextFieldUpdate, IMessage>
{
    public String elementName;
    public String elementText;

    public MessageGuiElementTextFieldUpdate()
    {

    }

    public MessageGuiElementTextFieldUpdate(ElementTextField elementTextField)
    {
        this(elementTextField.getName(), elementTextField.getText());
    }

    public MessageGuiElementTextFieldUpdate(String elementName, String elementText)
    {
        this.elementName = elementName;
        this.elementText = elementText;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        int elementNameLength = buf.readInt();
        this.elementName = new String(buf.readBytes(elementNameLength).array());
        int elementTextLength = buf.readInt();
        this.elementText = new String(buf.readBytes(elementTextLength).array());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(elementName.length());
        buf.writeBytes(elementName.getBytes());
        buf.writeInt(elementText.length());
        buf.writeBytes(elementText.getBytes());
    }

    @Override
    public IMessage onMessage(MessageGuiElementTextFieldUpdate message, MessageContext ctx)
    {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

        if (entityPlayer != null)
        {
            if (entityPlayer.openContainer instanceof ContainerEE)
            {
                ((ContainerEE) entityPlayer.openContainer).handleElementTextFieldUpdate(message.elementName, message.elementText);
            }
        }

        return null;
    }
}

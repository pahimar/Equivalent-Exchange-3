package com.pahimar.ee3.network.message;

import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.reference.Key;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageKeyPressed implements IMessage, IMessageHandler<MessageKeyPressed, IMessage>
{
    private byte keyPressed;

    public MessageKeyPressed()
    {
    }

    public MessageKeyPressed(Key key)
    {
        if (key == Key.CHARGE)
        {
            this.keyPressed = (byte) Key.CHARGE.ordinal();
        }
        else if (key == Key.EXTRA)
        {
            this.keyPressed = (byte) Key.EXTRA.ordinal();
        }
        else if (key == Key.RELEASE)
        {
            this.keyPressed = (byte) Key.RELEASE.ordinal();
        }
        else if (key == Key.TOGGLE)
        {
            this.keyPressed = (byte) Key.TOGGLE.ordinal();
        }
        else
        {
            this.keyPressed = (byte) Key.UNKNOWN.ordinal();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.keyPressed = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeByte(keyPressed);
    }

    @Override
    public IMessage onMessage(MessageKeyPressed message, MessageContext ctx)
    {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

        if (entityPlayer != null && entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() instanceof IKeyBound)
        {
            if (message.keyPressed == Key.CHARGE.ordinal())
            {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.CHARGE);
            }
            else if (message.keyPressed == Key.EXTRA.ordinal())
            {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.EXTRA);
            }
            else if (message.keyPressed == Key.RELEASE.ordinal())
            {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.RELEASE);
            }
            else if (message.keyPressed == Key.TOGGLE.ordinal())
            {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.TOGGLE);
            }
        }

        return null;
    }
}

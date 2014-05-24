package com.pahimar.ee3.network.message;

import com.pahimar.ee3.reference.Key;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

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
        /**
         * Yes I know that the entityPlayer is not correct. It appears that MessageContext never gets a nethandler set for it so
         * there isn't an easy way to grab the player that sent the packet until that's resolved.
         */

//        if (entityPlayer != null && entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() instanceof IKeyBound)
//        {
//            if (keyPressed == Key.CHARGE.ordinal())
//            {
//                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.CHARGE);
//            }
//            else if (keyPressed == Key.EXTRA.ordinal())
//            {
//                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.EXTRA);
//            }
//            else if (keyPressed == Key.RELEASE.ordinal())
//            {
//                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.RELEASE);
//            }
//            else if (keyPressed == Key.TOGGLE.ordinal())
//            {
//                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.TOGGLE);
//            }
//        }

        return null;
    }
}

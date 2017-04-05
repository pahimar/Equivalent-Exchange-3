package com.pahimar.ee.network.message;

import com.pahimar.ee.reference.Key;
import com.pahimar.ee.util.IKeyBound;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyPressed implements IMessage {

    private Key keyPressed;

    public MessageKeyPressed() {
    }

    public MessageKeyPressed(Key keyPressed) {

        if (keyPressed != null) {
            this.keyPressed = keyPressed;
        }
        else {
            this.keyPressed = Key.UNKNOWN;
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.keyPressed = Key.getKey(buf.readByte());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte((byte) keyPressed.ordinal());
    }

    public static class MessageHandler implements IMessageHandler<MessageKeyPressed, IMessage> {

        @Override
        public IMessage onMessage(MessageKeyPressed message, MessageContext ctx) {

            EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

            if (entityPlayer != null && entityPlayer.getHeldItemMainhand() != null && entityPlayer.getHeldItemMainhand().getItem() instanceof IKeyBound) {
                ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), message.keyPressed);
            }

            return null;
        }
    }
}

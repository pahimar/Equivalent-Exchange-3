package com.pahimar.ee.network.message;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.settings.ChalkSettings;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageChalkSettings implements IMessage {

    public ChalkSettings chalkSettings;

    public MessageChalkSettings() {
    }

    public MessageChalkSettings(ChalkSettings chalkSettings) {
        this.chalkSettings = chalkSettings;
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.chalkSettings = new ChalkSettings(buf.readInt(), buf.readInt(), buf.readInt());
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param byteBuf
     */
    @Override
    public void toBytes(ByteBuf byteBuf) {

        byteBuf.writeInt(chalkSettings.getIndex());
        byteBuf.writeInt(chalkSettings.getSize());
        byteBuf.writeInt(chalkSettings.getRotation());
    }

    public static class MessageHandler implements IMessageHandler<MessageChalkSettings, IMessage> {

        @Override
        public IMessage onMessage(MessageChalkSettings message, MessageContext ctx) {
            EquivalentExchange.proxy.getClientProxy().chalkSettings = message.chalkSettings;
            return null;
        }
    }
}
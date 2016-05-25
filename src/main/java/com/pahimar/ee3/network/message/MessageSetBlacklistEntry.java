package com.pahimar.ee3.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageSetBlacklistEntry implements IMessage, IMessageHandler<MessageSetBlacklistEntry, IMessage> {

    public MessageSetBlacklistEntry() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public IMessage onMessage(MessageSetBlacklistEntry message, MessageContext ctx) {

        return null;
    }
}

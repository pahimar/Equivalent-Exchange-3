package com.pahimar.ee3.network.message;

import com.google.gson.JsonParseException;
import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.blacklist.BlacklistRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.CompressionHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import static com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy.Blacklist;

public class MessageSetBlacklistEntry implements IMessage, IMessageHandler<MessageSetBlacklistEntry, IMessage> {

    public Blacklist blacklist;
    public boolean isBlacklistAction;
    public WrappedStack wrappedStack;

    public MessageSetBlacklistEntry() {
    }

    public MessageSetBlacklistEntry(Object object, Blacklist blacklist, boolean isBlacklistAction) {

        if (WrappedStack.canBeWrapped(object)) {
            this.wrappedStack = WrappedStack.wrap(object, 1);
        }
        else {
            wrappedStack = null;
        }
        this.blacklist = blacklist;
        this.isBlacklistAction = isBlacklistAction;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        int blacklistOrdinal = buf.readInt();

        if (blacklistOrdinal >= 0) {

            if (blacklistOrdinal == 0) {
                blacklist = Blacklist.KNOWLEDGE;
            }
            else if (blacklistOrdinal == 1) {
                blacklist = Blacklist.EXCHANGE;
            }
            else {
                blacklist = null;
            }

            if (blacklist != null) {

                isBlacklistAction = buf.readBoolean();
                int compressedJsonLength = buf.readInt();

                if (compressedJsonLength != 0) {

                    byte[] compressedWrappedStack = buf.readBytes(compressedJsonLength).array();

                    if (compressedWrappedStack != null) {

                        String jsonWrappedStack = CompressionHelper.decompress(compressedWrappedStack);

                        try {
                            wrappedStack = SerializationHelper.GSON.fromJson(jsonWrappedStack, WrappedStack.class);
                        }
                        catch (JsonParseException e) {
                            LogHelper.warn("Failed to receive {} blacklist data from server", blacklist);
                            wrappedStack = null;
                        }
                    }
                    else {
                        wrappedStack = null;
                    }
                }
                else {
                    wrappedStack = null;
                }
            }
        }
        else {
            blacklist = null;
            isBlacklistAction = false;
            wrappedStack = null;
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {

        if (blacklist != null) {

            buf.writeBoolean(isBlacklistAction);

            if (wrappedStack != null && wrappedStack.getWrappedObject() != null) {

                byte[] compressedWrappedStack = CompressionHelper.compress(SerializationHelper.GSON.toJson(wrappedStack));
                buf.writeInt(compressedWrappedStack.length);
                buf.writeBytes(compressedWrappedStack);
            }
            else {
                buf.writeInt(0);
            }
        }
        else {
            buf.writeInt(-1);
        }
    }

    @Override
    public IMessage onMessage(MessageSetBlacklistEntry message, MessageContext ctx) {

        if (message.blacklist != null && message.wrappedStack != null) {

            if (message.isBlacklistAction) {
                BlacklistRegistryProxy.addToBlacklist(message.wrappedStack, message.blacklist);
                BlacklistRegistry.INSTANCE.setShouldSave(false);
            }
            else {
                BlacklistRegistryProxy.removeFromBlacklist(message.wrappedStack, message.blacklist);
                BlacklistRegistry.INSTANCE.setShouldSave(false);
            }
        }

        return null;
    }
}

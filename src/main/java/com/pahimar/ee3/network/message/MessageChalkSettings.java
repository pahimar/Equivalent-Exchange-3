package com.pahimar.ee3.network.message;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.settings.ChalkSettings;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageChalkSettings implements IMessage, IMessageHandler<MessageChalkSettings, IMessage>
{
    private int index, size, rotation;

    public MessageChalkSettings()
    {

    }

    public MessageChalkSettings(ChalkSettings chalkSettings)
    {
        this.index = chalkSettings.getIndex();
        this.size = chalkSettings.getSize();
        this.rotation = chalkSettings.getRotation();
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.index = buf.readInt();
        this.size = buf.readInt();
        this.rotation = buf.readInt();
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.index);
        buf.writeInt(this.size);
        buf.writeInt(this.rotation);
    }

    /**
     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
     * is needed.
     *
     * @param message The message
     * @param ctx
     * @return an optional return message
     */
    @Override
    public IMessage onMessage(MessageChalkSettings message, MessageContext ctx)
    {
        EquivalentExchange3.proxy.getClientProxy().chalkSettings = new ChalkSettings(message.index, message.size, message.rotation);

        LogHelper.info(String.format("index: %s, size: %s, rotation: %s", EquivalentExchange3.proxy.getClientProxy().chalkSettings.getIndex(), EquivalentExchange3.proxy.getClientProxy().chalkSettings.getSize(), EquivalentExchange3.proxy.getClientProxy().chalkSettings.getRotation()));

        return null;
    }
}

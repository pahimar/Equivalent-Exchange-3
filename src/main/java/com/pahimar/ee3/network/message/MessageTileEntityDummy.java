package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityDummy;
import com.pahimar.ee3.tileentity.TileEntityEE;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityDummy implements IMessage, IMessageHandler<MessageTileEntityDummy, IMessage>
{
    public int x, y, z;
    public byte orientation;
    public byte state;
    public String customName, owner;
    public int trueXCoord, trueYCoord, trueZCoord;

    public MessageTileEntityDummy()
    {

    }

    public MessageTileEntityDummy(TileEntityDummy tileEntityDummy)
    {
        this.x = tileEntityDummy.xCoord;
        this.y = tileEntityDummy.yCoord;
        this.z = tileEntityDummy.zCoord;
        this.orientation = (byte) tileEntityDummy.getOrientation().ordinal();
        this.state = (byte) tileEntityDummy.getState();
        this.customName = tileEntityDummy.getCustomName();
        this.owner = tileEntityDummy.getOwner();
        this.trueXCoord = tileEntityDummy.getTrueXCoord();
        this.trueYCoord = tileEntityDummy.getTrueYCoord();
        this.trueZCoord = tileEntityDummy.getTrueZCoord();
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        int ownerLength = buf.readInt();
        this.owner = new String(buf.readBytes(ownerLength).array());
        this.trueXCoord = buf.readInt();
        this.trueYCoord = buf.readInt();
        this.trueZCoord = buf.readInt();
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        buf.writeInt(owner.length());
        buf.writeBytes(owner.getBytes());
        buf.writeInt(trueXCoord);
        buf.writeInt(trueYCoord);
        buf.writeInt(trueZCoord);
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
    public IMessage onMessage(MessageTileEntityDummy message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityEE)
        {
            ((TileEntityEE) tileEntity).setOrientation(message.orientation);
            ((TileEntityEE) tileEntity).setState(message.state);
            ((TileEntityEE) tileEntity).setCustomName(message.customName);
            ((TileEntityEE) tileEntity).setOwner(message.owner);

            if (tileEntity instanceof TileEntityDummy)
            {
                ((TileEntityDummy) tileEntity).setTrueCoords(message.trueXCoord, message.trueYCoord, message.trueZCoord);
            }
        }

        return null;
    }
}

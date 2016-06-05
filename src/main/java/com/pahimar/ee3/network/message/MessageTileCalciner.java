package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityCalciner;
import com.pahimar.ee3.tileentity.TileEntityEE;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

// FIXME Broken and not finished porting
public class MessageTileCalciner implements IMessage {

    public int x, y, z;
    public BlockPos blockPos;
    public byte orientation, state;
    public String customName;
    public UUID ownerUUID;
    public byte leftStackSize, leftStackMeta, rightStackSize, rightStackMeta;

    public MessageTileCalciner() {
    }

    public MessageTileCalciner(TileEntityCalciner tileEntityCalciner) {

        this.x = tileEntityCalciner.xCoord;
        this.y = tileEntityCalciner.yCoord;
        this.z = tileEntityCalciner.zCoord;
        this.orientation = (byte) tileEntityCalciner.getOrientation().ordinal();
        this.state = (byte) tileEntityCalciner.getState();
        this.customName = tileEntityCalciner.getCustomName();
        this.ownerUUID = tileEntityCalciner.getOwnerUUID();
        this.leftStackSize = tileEntityCalciner.leftStackSize;
        this.leftStackMeta = tileEntityCalciner.leftStackMeta;
        this.rightStackSize = tileEntityCalciner.rightStackSize;
        this.rightStackMeta = tileEntityCalciner.rightStackMeta;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        if (buf.readBoolean()) {
            this.ownerUUID = new UUID(buf.readLong(), buf.readLong());
        }
        else {
            this.ownerUUID = null;
        }
        this.leftStackSize = buf.readByte();
        this.leftStackMeta = buf.readByte();
        this.rightStackSize = buf.readByte();
        this.rightStackMeta = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        if (ownerUUID != null) {
            buf.writeBoolean(true);
            buf.writeLong(ownerUUID.getMostSignificantBits());
            buf.writeLong(ownerUUID.getLeastSignificantBits());
        }
        else {
            buf.writeBoolean(false);
        }
        buf.writeByte(leftStackSize);
        buf.writeByte(leftStackMeta);
        buf.writeByte(rightStackSize);
        buf.writeByte(rightStackMeta);
    }

    public static class MessageHandler implements IMessageHandler<MessageTileCalciner, IMessage> {

        @Override
        public IMessage onMessage(MessageTileCalciner message, MessageContext ctx) {

            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

            if (tileEntity instanceof TileEntityCalciner) {
                ((TileEntityEE) tileEntity).setOrientation(message.orientation);
                ((TileEntityEE) tileEntity).setState(message.state);
                ((TileEntityEE) tileEntity).setCustomName(message.customName);
                ((TileEntityEE) tileEntity).setOwnerUUID(message.ownerUUID);
                ((TileEntityCalciner) tileEntity).leftStackSize = message.leftStackSize;
                ((TileEntityCalciner) tileEntity).leftStackMeta = message.leftStackMeta;
                ((TileEntityCalciner) tileEntity).rightStackSize = message.rightStackSize;
                ((TileEntityCalciner) tileEntity).rightStackMeta = message.rightStackMeta;
            }

            return null;
        }
    }
}

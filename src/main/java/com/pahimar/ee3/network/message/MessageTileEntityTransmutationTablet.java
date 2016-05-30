package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MessageTileEntityTransmutationTablet implements IMessage, IMessageHandler<MessageTileEntityTransmutationTablet, IMessage> {

    public NBTTagCompound tileEntityTransmutationTabletNBT;

    public MessageTileEntityTransmutationTablet() {
    }

    public MessageTileEntityTransmutationTablet(TileEntityTransmutationTablet tileEntityTransmutationTablet) {

        tileEntityTransmutationTabletNBT = new NBTTagCompound();
        tileEntityTransmutationTablet.writeToNBT(tileEntityTransmutationTabletNBT);
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        byte[] compressedNBT = null;
        int readableBytes = buf.readInt();

        if (readableBytes > 0) {
            compressedNBT = buf.readBytes(readableBytes).array();
        }

        if (compressedNBT != null) {

            try {
                this.tileEntityTransmutationTabletNBT = CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedNBT));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {

        byte[] compressedNBT = null;

        try {
            if (tileEntityTransmutationTabletNBT != null) {
                compressedNBT = CompressedStreamTools.compress(tileEntityTransmutationTabletNBT);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (compressedNBT != null) {
            buf.writeInt(compressedNBT.length);
            buf.writeBytes(compressedNBT);
        }
        else {
            buf.writeInt(0);
        }
    }

    @Override
    public IMessage onMessage(MessageTileEntityTransmutationTablet message, MessageContext ctx) {

        if (message.tileEntityTransmutationTabletNBT != null) {

            TileEntityTransmutationTablet tileEntityTransmutationTablet = new TileEntityTransmutationTablet();
            tileEntityTransmutationTablet.readFromNBT(message.tileEntityTransmutationTabletNBT);

            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntityTransmutationTablet.xCoord, tileEntityTransmutationTablet.yCoord, tileEntityTransmutationTablet.zCoord);

            if (tileEntity instanceof TileEntityTransmutationTablet) {
                tileEntity.readFromNBT(message.tileEntityTransmutationTabletNBT);
                //NAME UPDATE
                FMLClientHandler.instance().getClient().theWorld.func_147451_t(tileEntityTransmutationTablet.xCoord, tileEntityTransmutationTablet.yCoord, tileEntityTransmutationTablet.zCoord);
            }
        }

        return null;
    }
}

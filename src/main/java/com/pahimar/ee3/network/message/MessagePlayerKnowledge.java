package com.pahimar.ee3.network.message;

import com.google.gson.JsonSyntaxException;
import com.pahimar.ee3.knowledge.PlayerKnowledge;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.CompressionUtils;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.Collection;

public class MessagePlayerKnowledge implements IMessage, IMessageHandler<MessagePlayerKnowledge, IMessage> {

    public int xCoord, yCoord, zCoord;
    public PlayerKnowledge playerKnowledge;

    public MessagePlayerKnowledge(){
    }

    public MessagePlayerKnowledge(TileEntityTransmutationTablet transmutationTablet, Collection<ItemStack> knownItemStacks) {

        if (transmutationTablet != null) {
            this.xCoord = transmutationTablet.xCoord;
            this.yCoord = transmutationTablet.yCoord;
            this.zCoord = transmutationTablet.zCoord;
        }
        else {
            this.xCoord = 0;
            this.yCoord = Integer.MIN_VALUE;
            this.zCoord = 0;
        }

        if (knownItemStacks != null) {
            this.playerKnowledge = new PlayerKnowledge(knownItemStacks);
        }
        else {
            this.playerKnowledge = new PlayerKnowledge();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        this.xCoord = buf.readInt();
        this.yCoord = buf.readInt();
        this.zCoord = buf.readInt();

        byte[] compressedJson = null;
        int readableBytes = buf.readInt();

        if (readableBytes > 0) {
            compressedJson = buf.readBytes(readableBytes).array();
        }

        if (compressedJson != null) {
            try {
                this.playerKnowledge = SerializationHelper.GSON.fromJson(CompressionUtils.decompress(compressedJson), PlayerKnowledge.class);
            }
            catch (JsonSyntaxException e) {
                this.playerKnowledge = new PlayerKnowledge();
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(xCoord);
        buf.writeInt(yCoord);
        buf.writeInt(zCoord);

        byte[] compressedJson = null;

        if (playerKnowledge != null) {
            compressedJson = CompressionUtils.compress(SerializationHelper.GSON.toJson(playerKnowledge));
        }

        if (compressedJson != null) {
            buf.writeInt(compressedJson.length);
            buf.writeBytes(compressedJson);
        }
        else {
            buf.writeInt(0);
        }
    }

    @Override
    public IMessage onMessage(MessagePlayerKnowledge message, MessageContext ctx) {

        if (message.yCoord != Integer.MIN_VALUE) {

            TileEntity tileEntity = FMLClientHandler.instance().getWorldClient().getTileEntity(message.xCoord, message.yCoord, message.zCoord);

            if (tileEntity instanceof TileEntityTransmutationTablet) {
                ((TileEntityTransmutationTablet) tileEntity).handlePlayerKnowledgeUpdate(message.playerKnowledge);
            }
        }

        return null;
    }
}

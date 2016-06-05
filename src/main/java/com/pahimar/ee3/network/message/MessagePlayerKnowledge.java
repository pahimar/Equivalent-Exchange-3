package com.pahimar.ee3.network.message;

import com.google.gson.JsonSyntaxException;
import com.pahimar.ee3.knowledge.PlayerKnowledge;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.CompressionUtils;
import com.pahimar.ee3.util.SerializationHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Collection;

public class MessagePlayerKnowledge implements IMessage {

    public BlockPos blockPos;
    public PlayerKnowledge playerKnowledge;

    public MessagePlayerKnowledge(){
    }

    public MessagePlayerKnowledge(TileEntityTransmutationTablet transmutationTablet, Collection<ItemStack> knownItemStacks) {

        if (transmutationTablet != null) {
            this.blockPos = transmutationTablet.getPos();
        }
        else {
            this.blockPos = new BlockPos(0, Integer.MIN_VALUE, 0);
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

        blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());

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

        if (blockPos != null) {
            buf.writeInt(blockPos.getX());
            buf.writeInt(blockPos.getY());
            buf.writeInt(blockPos.getZ());
        }
        else {
            buf.writeInt(0);
            buf.writeInt(Integer.MIN_VALUE);
            buf.writeInt(0);
        }

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

    public static class MessageHandler implements IMessageHandler<MessagePlayerKnowledge, IMessage> {

        @Override
        public IMessage onMessage(MessagePlayerKnowledge message, MessageContext ctx) {

            if (message.blockPos.getY() != Integer.MIN_VALUE) {

                TileEntity tileEntity = FMLClientHandler.instance().getWorldClient().getTileEntity(message.blockPos);

                if (tileEntity instanceof TileEntityTransmutationTablet) {
                    ((TileEntityTransmutationTablet) tileEntity).handlePlayerKnowledgeUpdate(message.playerKnowledge);
                }
            }

            return null;
        }
    }
}

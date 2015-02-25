package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
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

public class MessageTileEntityAlchemyArray implements IMessage, IMessageHandler<MessageTileEntityAlchemyArray, IMessage>
{
    public NBTTagCompound tileEntityAlchemyArrayNBT;

    public MessageTileEntityAlchemyArray()
    {
    }

    public MessageTileEntityAlchemyArray(TileEntityAlchemyArray tileEntityAlchemyArray)
    {
        tileEntityAlchemyArrayNBT = new NBTTagCompound();
        tileEntityAlchemyArray.writeToNBT(tileEntityAlchemyArrayNBT);
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        byte[] compressedNBT = null;
        int readableBytes = buf.readInt();

        if (readableBytes > 0)
        {
            compressedNBT = buf.readBytes(readableBytes).array();
        }

        if (compressedNBT != null)
        {
            try
            {
                this.tileEntityAlchemyArrayNBT = CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedNBT));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf)
    {
        byte[] compressedNBT = null;

        try
        {
            if (tileEntityAlchemyArrayNBT != null)
            {
                compressedNBT = CompressedStreamTools.compress(tileEntityAlchemyArrayNBT);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (compressedNBT != null)
        {
            buf.writeInt(compressedNBT.length);
            buf.writeBytes(compressedNBT);
        }
        else
        {
            buf.writeInt(0);
        }
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
    public IMessage onMessage(MessageTileEntityAlchemyArray message, MessageContext ctx)
    {
        if (message.tileEntityAlchemyArrayNBT != null)
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = new TileEntityAlchemyArray();
            tileEntityAlchemyArray.readFromNBT(message.tileEntityAlchemyArrayNBT);

            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntityAlchemyArray.xCoord, tileEntityAlchemyArray.yCoord, tileEntityAlchemyArray.zCoord);

            if (tileEntity instanceof TileEntityAlchemyArray)
            {
                tileEntity.readFromNBT(message.tileEntityAlchemyArrayNBT);
            }
        }

        return null;
    }
}
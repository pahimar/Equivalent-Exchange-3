package com.pahimar.ee3.network;

import com.pahimar.ee3.network.message.MessageTileEntity;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.tileentity.TileEntityEE;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.tileentity.TileEntity;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init()
    {
        INSTANCE.registerMessage(TileEntityUpdateHandler.class, MessageTileEntity.class, 0, Side.CLIENT);
    }

    public class TileEntityUpdateHandler implements IMessageHandler<MessageTileEntity, IMessage>
    {
        @Override
        public IMessage onMessage(MessageTileEntity message, MessageContext ctx)
        {
            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

            if (tileEntity instanceof TileEntityEE)
            {
                ((TileEntityEE) tileEntity).setOrientation(message.orientation);
                ((TileEntityEE) tileEntity).setState(message.state);
            }

            return null;
        }
    }
}

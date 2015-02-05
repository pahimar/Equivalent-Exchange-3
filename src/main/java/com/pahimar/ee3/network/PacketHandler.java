package com.pahimar.ee3.network;

import com.pahimar.ee3.network.message.*;
import com.pahimar.ee3.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.LOWERCASE_MOD_ID);

    public static void init()
    {
        INSTANCE.registerMessage(MessageTileEntityEE.class, MessageTileEntityEE.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileCalcinator.class, MessageTileCalcinator.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityAludel.class, MessageTileEntityAludel.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityGlassBell.class, MessageTileEntityGlassBell.class, 3, Side.CLIENT);
        INSTANCE.registerMessage(MessageKeyPressed.class, MessageKeyPressed.class, 4, Side.SERVER);
        INSTANCE.registerMessage(MessageSoundEvent.class, MessageSoundEvent.class, 5, Side.CLIENT);
        INSTANCE.registerMessage(MessageSyncEnergyValues.class, MessageSyncEnergyValues.class, 6, Side.CLIENT);
        INSTANCE.registerMessage(MessageSetEnergyValue.class, MessageSetEnergyValue.class, 7, Side.CLIENT);
    }
}

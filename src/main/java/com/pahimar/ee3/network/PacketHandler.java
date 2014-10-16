package com.pahimar.ee3.network;

import com.pahimar.ee3.network.message.*;
import com.pahimar.ee3.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

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
        INSTANCE.registerMessage(MessageTileEntityAlchemyArray.class, MessageTileEntityAlchemyArray.class, 8, Side.CLIENT);
        INSTANCE.registerMessage(MessageChalkSettings.class, MessageChalkSettings.class, 9, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityDummy.class, MessageTileEntityDummy.class, 10, Side.CLIENT);
    }
}

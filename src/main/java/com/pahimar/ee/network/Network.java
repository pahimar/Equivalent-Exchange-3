package com.pahimar.ee.network;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.network.message.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class Network {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(EquivalentExchange.MOD_ID);

    public static void init() {

        INSTANCE.registerMessage(MessageKeyPressed.MessageHandler.class, MessageKeyPressed.class, 4, Side.SERVER);
        INSTANCE.registerMessage(MessageSyncEnergyValues.MessageHandler.class, MessageSyncEnergyValues.class, 6, Side.CLIENT);
        INSTANCE.registerMessage(MessageSetEnergyValue.MessageHandler.class, MessageSetEnergyValue.class, 7, Side.CLIENT);
        INSTANCE.registerMessage(MessageGuiElementClicked.MessageHandler.class, MessageGuiElementClicked.class, 8, Side.SERVER);
        INSTANCE.registerMessage(MessageGuiElementTextFieldUpdate.MessageHandler.class, MessageGuiElementTextFieldUpdate.class, 9, Side.SERVER);
        INSTANCE.registerMessage(MessageChalkSettings.MessageHandler.class, MessageChalkSettings.class, 10, Side.CLIENT);
        INSTANCE.registerMessage(MessageSingleParticleEvent.MessageHandler.class, MessageSingleParticleEvent.class, 14, Side.CLIENT);
        INSTANCE.registerMessage(MessageSliderElementUpdated.MessageHandler.class, MessageSliderElementUpdated.class, 15, Side.SERVER);
        INSTANCE.registerMessage(MessagePlayerKnowledge.MessageHandler.class, MessagePlayerKnowledge.class, 16, Side.CLIENT);
        INSTANCE.registerMessage(MessageSyncBlacklist.MessageHandler.class, MessageSyncBlacklist.class, 18, Side.CLIENT);
        INSTANCE.registerMessage(MessageSetBlacklistEntry.MessageHandler.class, MessageSetBlacklistEntry.class, 19, Side.CLIENT);
    }
}

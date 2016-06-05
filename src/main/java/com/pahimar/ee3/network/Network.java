package com.pahimar.ee3.network;

import com.pahimar.ee3.network.message.*;
import com.pahimar.ee3.reference.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class Network {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.LOWERCASE_MOD_ID);

    public static void init() {
        INSTANCE.registerMessage(MessageTileEntityEE.class, MessageTileEntityEE.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileCalciner.MessageHandler.class, MessageTileCalciner.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityAludel.class, MessageTileEntityAludel.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityGlassBell.class, MessageTileEntityGlassBell.class, 3, Side.CLIENT);
        INSTANCE.registerMessage(MessageKeyPressed.MessageHandler.class, MessageKeyPressed.class, 4, Side.SERVER);
        INSTANCE.registerMessage(MessageSoundEvent.MessageHandler.class, MessageSoundEvent.class, 5, Side.CLIENT);
        INSTANCE.registerMessage(MessageSyncEnergyValues.MessageHandler.class, MessageSyncEnergyValues.class, 6, Side.CLIENT);
        INSTANCE.registerMessage(MessageSetEnergyValue.MessageHandler.class, MessageSetEnergyValue.class, 7, Side.CLIENT);
        INSTANCE.registerMessage(MessageGuiElementClicked.MessageHandler.class, MessageGuiElementClicked.class, 8, Side.SERVER);
        INSTANCE.registerMessage(MessageGuiElementTextFieldUpdate.MessageHandler.class, MessageGuiElementTextFieldUpdate.class, 9, Side.SERVER);
        INSTANCE.registerMessage(MessageChalkSettings.MessageHandler.class, MessageChalkSettings.class, 10, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityDummy.class, MessageTileEntityDummy.class, 11, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityAlchemyArray.class, MessageTileEntityAlchemyArray.class, 12, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityTransmutationTablet.class, MessageTileEntityTransmutationTablet.class, 13, Side.CLIENT);
        INSTANCE.registerMessage(MessageSingleParticleEvent.MessageHandler.class, MessageSingleParticleEvent.class, 14, Side.CLIENT);
        INSTANCE.registerMessage(MessageSliderElementUpdated.MessageHandler.class, MessageSliderElementUpdated.class, 15, Side.SERVER);
        INSTANCE.registerMessage(MessagePlayerKnowledge.MessageHandler.class, MessagePlayerKnowledge.class, 16, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityResearchStation.class, MessageTileEntityResearchStation.class, 17, Side.CLIENT);
        INSTANCE.registerMessage(MessageSyncBlacklist.MessageHandler.class, MessageSyncBlacklist.class, 18, Side.CLIENT);
        INSTANCE.registerMessage(MessageSetBlacklistEntry.MessageHandler.class, MessageSetBlacklistEntry.class, 19, Side.CLIENT);
    }
}

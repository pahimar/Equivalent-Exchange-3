package com.pahimar.ee3.core.handler;

import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.lib.InterModComms;

import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

public class InterModCommsHandler {

    // TODO Revisit logging levels and the use of String.format for logging messages
    // TODO Add more IMC functions (has-emc, get-emc) with return messages
    
    public static void processIMCMessages(IMCEvent event) {

        for (IMCMessage imcMessage : event.getMessages()) {

            if (imcMessage.getMessageType() == String.class) {

                if (imcMessage.key.equalsIgnoreCase(InterModComms.ADD_RECIPE)) {
                    processAddRecipeMessage(imcMessage);
                }
                else if (imcMessage.key.equalsIgnoreCase(InterModComms.ADD_BLACKLIST_ENTRY)) {
                    processAddBlackListMessage(imcMessage);
                }
                else if (imcMessage.key.equalsIgnoreCase(InterModComms.REMOVE_BLACKLIST_ENTRY)) {
                    processRemoveBlackListMessage(imcMessage);
                }
                else if (imcMessage.key.equalsIgnoreCase(InterModComms.ASSIGN_EMC_VALUE_PRE)) {
                    processPreAssignEmcValueMessage(imcMessage);
                }
                else if (imcMessage.key.equalsIgnoreCase(InterModComms.ASSIGN_EMC_VALUE_POST)) {
                    processPostAssignEmcValueMessage(imcMessage);
                }
            }
            else {
                LogHelper.severe("[IMC] Mod '" + imcMessage.getSender() + "' sent a message with key '" + imcMessage.key + "' with an invalid argument type (received " + imcMessage.getMessageType().getSimpleName() + ", expected String)");
            }
        }
    }

    private static void processAddRecipeMessage(IMCMessage imcMessage) {

        // TODO 
    }

    private static void processAddBlackListMessage(IMCMessage imcMessage) {

        // TODO
    }

    private static void processRemoveBlackListMessage(IMCMessage imcMessage) {

        // TODO
    }

    private static void processPreAssignEmcValueMessage(IMCMessage imcMessage) {

        // TODO
    }
    
    private static void processPostAssignEmcValueMessage(IMCMessage imcMessage) {

        // TODO
    }
}

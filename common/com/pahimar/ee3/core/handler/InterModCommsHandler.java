package com.pahimar.ee3.core.handler;

import com.pahimar.ee3.lib.InterModComms;

import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

public class InterModCommsHandler {

    // TODO Logging
    
    public static void processIMCMessages(IMCEvent event) {
        
        for (IMCMessage imcMessage : event.getMessages()) {

            String requestedOperation = imcMessage.key;

            if (requestedOperation.equalsIgnoreCase(InterModComms.RECIPE_ADD)) {
                processAddRecipeMessage(imcMessage);
            }
            else if (requestedOperation.equalsIgnoreCase(InterModComms.BLACKLIST_ADD_ENTRY)) {
                processAddBlackListMessage(imcMessage);
            }
            else if (requestedOperation.equalsIgnoreCase(InterModComms.BLACKLIST_REMOVE_ENTRY)) {
                processRemoveBlackListMessage(imcMessage);
            }
            else if (requestedOperation.equalsIgnoreCase(InterModComms.EMC_ASSIGN_VALUE_PRE)) {
                processPreAssignEmcValueMessage(imcMessage);
            }
            else if (requestedOperation.equalsIgnoreCase(InterModComms.EMC_ASSIGN_VALUE_POST)) {
                processPostAssignEmcValueMessage(imcMessage);
            }
            else if (requestedOperation.equalsIgnoreCase(InterModComms.EMC_HAS_VALUE)) {
                processHasEmcValueMessage(imcMessage);
            }
            else if (requestedOperation.equalsIgnoreCase(InterModComms.EMC_GET_VALUE)) {
                processGetEmcValueMessage(imcMessage);
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
    
    private static void processHasEmcValueMessage(IMCMessage imcMessage) {

        // TODO
    }
    
    private static void processGetEmcValueMessage(IMCMessage imcMessage) {
        
        // TODO
    }
}

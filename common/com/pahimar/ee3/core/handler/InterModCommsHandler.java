package com.pahimar.ee3.core.handler;

import net.minecraft.item.ItemStack;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.api.StackValueMapping;
import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.emc.EmcValuesIMC;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.lib.InterModComms;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

public class InterModCommsHandler {

    // TODO Logging
    // TODO Tick handler to grab runtime messages
    
    public static void processIMCMessages(IMCEvent event) {
        
        for (IMCMessage imcMessage : event.getMessages()) {
            processIMCMessage(imcMessage);
        }
    }
    
    public static void processIMCMessage(IMCMessage imcMessage) {
        
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

        if (imcMessage.getMessageType() == String.class) {
            
            StackValueMapping stackValueMapping = StackValueMapping.createFromJson(imcMessage.getStringValue());
            
            if (stackValueMapping != null) {
                
                CustomWrappedStack customWrappedStack = stackValueMapping.customWrappedStack;
                EmcValue emcValue = stackValueMapping.emcValue;
                
                EmcValuesIMC.addPreAssignedValued(customWrappedStack, emcValue);
            }
            else {
                // TODO Log that the message payloads json was invalid
            }
        }
        else {
            // TODO Log that the message payload is of an invalid type
        }
    }
    
    private static void processPostAssignEmcValueMessage(IMCMessage imcMessage) {

        if (imcMessage.getMessageType() == String.class) {
            
            StackValueMapping stackValueMapping = StackValueMapping.createFromJson(imcMessage.getStringValue());
            
            if (stackValueMapping != null) {
                
                CustomWrappedStack customWrappedStack = stackValueMapping.customWrappedStack;
                EmcValue emcValue = stackValueMapping.emcValue;
                
                EmcValuesIMC.addPostAssignedValued(customWrappedStack, emcValue);
            }
            else {
                // TODO Log that the message payloads json was invalid
            }
        }
        else {
            // TODO Log that the message payload is of an invalid type
        }
    }
    
    private static void processHasEmcValueMessage(IMCMessage imcMessage) {

        if (imcMessage.getMessageType() == String.class) {
            
            // TODO What if it is an encoded ItemStack | OreStack | EnergyStack | FluidStack
            CustomWrappedStack customWrappedStack = CustomWrappedStack.createFromJson(imcMessage.getStringValue());
            
            if (customWrappedStack != null) {
                
            }
            else {
                // TODO Log that the message payloads json was invalid
            }
        }
        else if (imcMessage.getMessageType() == ItemStack.class) {
            
            /*
             * Reply back to the mod that queried for the existance of an EmcValue for the given ItemStack
             */
            FMLInterModComms.sendRuntimeMessage(EquivalentExchange3.instance, imcMessage.getSender(), InterModComms.EMC_RETURN_HAS_VALUE, String.valueOf(EmcRegistry.hasEmcValue(imcMessage.getItemStackValue())));
        }
        else {
            // TODO Log that the message payload is of an invalid type
        }
    }
    
    private static void processGetEmcValueMessage(IMCMessage imcMessage) {
        
        if (imcMessage.getMessageType() == String.class) {
            
            // TODO What if it is an encoded ItemStack | OreStack | EnergyStack | FluidStack
            CustomWrappedStack customWrappedStack = CustomWrappedStack.createFromJson(imcMessage.getStringValue());
            
            if (customWrappedStack != null) {
                
            }
            else {
                // TODO Log that the message payloads json was invalid
            }
        }
        else if (imcMessage.getMessageType() == ItemStack.class) {
            
            /*
             * Reply back to the mod that queried for the existance of an EmcValue for the given ItemStack
             */
            EmcValue emcValue = EmcRegistry.getEmcValue(imcMessage.getItemStackValue());
            
            if (emcValue != null) {
                FMLInterModComms.sendRuntimeMessage(EquivalentExchange3.instance, imcMessage.getSender(), InterModComms.EMC_RETURN_GET_VALUE, emcValue.toJson());
            }
            else {
                FMLInterModComms.sendRuntimeMessage(EquivalentExchange3.instance, imcMessage.getSender(), InterModComms.EMC_RETURN_GET_VALUE, "null");
            }
        }
        else {
            // TODO Log that the message payload is of an invalid type
        }
    }
}

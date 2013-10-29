package com.pahimar.ee3.core.handler;

import java.util.List;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;

import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.core.helper.nbt.GeneralNBTHelper;
import com.pahimar.ee3.emc.EmcBlackList;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.emc.EmcValuesIMC;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.crafting.RecipesIMC;
import com.pahimar.ee3.lib.InterModComms;

import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

public class InterModCommsHandler {

    // TODO Revisit logging levels and the use of String.format for logging messages
    
    public static void processIMCMessages(IMCEvent event) {

        for (IMCMessage imcMessage : event.getMessages()) {

            if (imcMessage.getMessageType() == NBTTagCompound.class) {

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
                LogHelper.severe("[IMC] Mod '" + imcMessage.getSender() + "' sent a message with key '" + imcMessage.key + "' with an invalid argument type (received " + imcMessage.getMessageType().getSimpleName() + ", expected NBTTagCompound)");
            }
        }
    }

    private static void processAddRecipeMessage(IMCMessage imcMessage) {

        NBTTagCompound encodedRecipe = imcMessage.getNBTValue();

        Map<CustomWrappedStack, List<CustomWrappedStack>> decodedRecipe = GeneralNBTHelper.decodeRecipeFromNBT(encodedRecipe);

        if (!decodedRecipe.isEmpty()) {
            for (CustomWrappedStack key : decodedRecipe.keySet()) {
                RecipesIMC.addRecipe(key, decodedRecipe.get(key));
                LogHelper.info("[IMC] Mod '" + imcMessage.getSender() + "' added recipe with output '" + key.toString() + "' and inputs '" + decodedRecipe.get(key) + "'");
            }
        }
        else {
            LogHelper.severe("[IMC] Mod '" + imcMessage.getSender() + "' attempting to add a NBT encoded recipe to the recipe registry, but the encoded recipe is invalid");
        }
    }

    private static void processAddBlackListMessage(IMCMessage imcMessage) {

        NBTTagCompound encodedStack = imcMessage.getNBTValue();

        CustomWrappedStack decodedStack = GeneralNBTHelper.decodeStackFromNBT(encodedStack);

        if (decodedStack != null) {
            if (EmcBlackList.getInstance().add(decodedStack)) {
                LogHelper.info("[IMC] Mod '" + imcMessage.getSender() + "' added object '" + decodedStack.toString() + "' to the EMC blacklist");
            }
            else {
                LogHelper.severe("[IMC] Mod '" + imcMessage.getSender() + "' attempted to add an object to the EMC blacklist that already existed");
            }
        }
    }

    private static void processRemoveBlackListMessage(IMCMessage imcMessage) {

        NBTTagCompound encodedStack = imcMessage.getNBTValue();

        CustomWrappedStack decodedStack = GeneralNBTHelper.decodeStackFromNBT(encodedStack);

        if (decodedStack != null) {
            if (EmcBlackList.getInstance().remove(decodedStack)) {
                LogHelper.info("[IMC] Mod '" + imcMessage.getSender() + "' removed object '" + decodedStack.toString() + "' from the EMC blacklist");
            }
            else {
                LogHelper.severe("[IMC] Mod '" + imcMessage.getSender() + "' attempted to remove an object to the EMC blacklist that was not present");
            }
        }
    }

    private static void processPreAssignEmcValueMessage(IMCMessage imcMessage) {

        // FIXME Not all methods of passing mappings work, and logging not 100% yet
        
        NBTTagCompound encodedEmcValueMapping = imcMessage.getNBTValue();
        
        Map<CustomWrappedStack, EmcValue> emcValueMapping = GeneralNBTHelper.decodeEmcValueMappings(encodedEmcValueMapping);
        
        if (emcValueMapping != null && emcValueMapping.size() > 0) {
            for (CustomWrappedStack stack : emcValueMapping.keySet()) {
                EmcValue emcValue = emcValueMapping.get(stack);
                
                if (stack.getWrappedStack() != null && emcValue != null && emcValue.getValue() > 0) {
                    EmcValuesIMC.addPreAssignedValued(stack, emcValue);
                    LogHelper.fine(String.format("[IMC] Mod '%s' added a pre auto assignment EmcValue of %s (%s) for object '%s'",imcMessage.getSender(), emcValue.getValue(), emcValue.toString(), stack));
                }
                else {
                    LogHelper.severe(String.format("[IMC] Mod '%s' failed in attempting to add a pre auto assignment EmcValue of %s (%s) for object '%s'",imcMessage.getSender(), emcValue.getValue(), emcValue.toString(), stack));
                }
            }
        }
        else {
            LogHelper.severe(String.format("[IMC] Mod failed in attempting to add a pre auto assignment: %s", encodedEmcValueMapping));
        }
    }
    
    private static void processPostAssignEmcValueMessage(IMCMessage imcMessage) {

NBTTagCompound encodedEmcValueMapping = imcMessage.getNBTValue();
        
        Map<CustomWrappedStack, EmcValue> emcValueMapping = GeneralNBTHelper.decodeEmcValueMappings(encodedEmcValueMapping);
        
        if (emcValueMapping != null && emcValueMapping.size() > 0) {
            for (CustomWrappedStack stack : emcValueMapping.keySet()) {
                EmcValue emcValue = emcValueMapping.get(stack);
                
                if (stack.getWrappedStack() != null && emcValue != null && emcValue.getValue() > 0) {
                    EmcValuesIMC.addPostAssignedValued(stack, emcValue);
                    LogHelper.fine(String.format("[IMC] Mod '%s' added a post auto assignment EmcValue of %s (%s) for object '%s'",imcMessage.getSender(), emcValue.getValue(), emcValue.toString(), stack));
                }
                else {
                    LogHelper.severe(String.format("[IMC] Mod '%s' failed in attempting to add a post auto assignment EmcValue of %s (%s) for object '%s'",imcMessage.getSender(), emcValue.getValue(), emcValue.toString(), stack));
                }
            }
        }
    }
}

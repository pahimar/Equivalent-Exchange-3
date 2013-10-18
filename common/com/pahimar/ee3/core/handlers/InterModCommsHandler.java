package com.pahimar.ee3.core.handlers;

import java.util.List;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;

import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.emc.EmcBlackList;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.crafting.RecipesIMC;
import com.pahimar.ee3.lib.InterModComms;
import com.pahimar.ee3.nbt.NBTHelper;

import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

public class InterModCommsHandler {

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
                else if (imcMessage.key.equalsIgnoreCase(InterModComms.SET_EMC_VALUE)) {
                    processSetEmcValueMessage(imcMessage);
                }
            }
            else {
                LogHelper.severe("[IMC] Mod '" + imcMessage.getSender() + "' sent a message with key '" + imcMessage.key + "' with an invalid argument type (received " + imcMessage.getMessageType().getSimpleName() + ", expected NBTTagCompound)");
            }
        }
    }

    private static void processAddRecipeMessage(IMCMessage imcMessage) {

        NBTTagCompound encodedRecipe = imcMessage.getNBTValue();

        Map<CustomWrappedStack, List<CustomWrappedStack>> decodedRecipe = NBTHelper.decodeRecipeFromNBT(encodedRecipe);

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

        CustomWrappedStack decodedStack = NBTHelper.decodeStackFromNBT(encodedStack);

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

        CustomWrappedStack decodedStack = NBTHelper.decodeStackFromNBT(encodedStack);

        if (decodedStack != null) {
            if (EmcBlackList.getInstance().remove(decodedStack)) {
                LogHelper.info("[IMC] Mod '" + imcMessage.getSender() + "' removed object '" + decodedStack.toString() + "' from the EMC blacklist");
            }
            else {
                LogHelper.severe("[IMC] Mod '" + imcMessage.getSender() + "' attempted to remove an object to the EMC blacklist that was not present");
            }
        }
    }

    private static void processSetEmcValueMessage(IMCMessage imcMessage) {

        // TODO Set an EMC Value via IMC
    }
}

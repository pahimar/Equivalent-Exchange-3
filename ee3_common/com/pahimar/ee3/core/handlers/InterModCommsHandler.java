package com.pahimar.ee3.core.handlers;

import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.crafting.RecipeRegistry;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.nbt.NBTHelper;

import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

public class InterModCommsHandler {

    public static void processIMCMessages(IMCEvent event) {
        
        for (IMCMessage imcMessage : event.getMessages()) {
            
            if (imcMessage.key.equalsIgnoreCase(Strings.IMC_ADD_RECIPE_KEY)) {
                processAddRecipeMessage(imcMessage);
            }
            else if (imcMessage.key.equalsIgnoreCase(Strings.IMC_ADD_BLACKLIST_ENTRY)) {
                // TODO Handle EMC blacklist IMC messages
            }
            else if (imcMessage.key.equalsIgnoreCase(Strings.IMC_SET_EMC_VALUE)) {
                processSetEMCValueMessage(imcMessage);
            }
        }
    }
    
    private static void processAddRecipeMessage(IMCMessage imcMessage) {
        
        if (imcMessage.getMessageType() == String.class) {
            // TODO Verify if the string we received is a valid encoded string
        }
        else if (imcMessage.getMessageType() == ItemStack.class) {
            LogHelper.severe("Mod: '" + imcMessage.getSender() + "' attempting to add a recipe to the recipe registry via InterModCommunications with an invalid argument: ItemStack (expected String or NBTTagCompound)");
        }
        else if (imcMessage.getMessageType() == NBTTagCompound.class) {
            NBTTagCompound encodedRecipe = imcMessage.getNBTValue();
            
            Map<CustomWrappedStack, List<CustomWrappedStack>> decodedRecipe = NBTHelper.decodeRecipeFromNBT(encodedRecipe);
            
            if (!decodedRecipe.isEmpty()) {
                for (CustomWrappedStack key : decodedRecipe.keySet()) {
                    RecipeRegistry.getInstance().addRecipe(key, decodedRecipe.get(key));
                    LogHelper.info("Mod: '" + imcMessage.getSender() + "' added recipe with output '" + key.toString() + "' and inputs '" + decodedRecipe.get(key) + "'");
                }
            }
            else {
                LogHelper.severe("Mod: '" + imcMessage.getSender() + "' attempting to add a NBT encoded recipe to the recipe registry via InterModCommunications, but the encoded recipe is invalid");
            }
        }
    }
    
    private static void processSetEMCValueMessage(IMCMessage imcMessage) {
        
        if (imcMessage.getMessageType() == String.class) {
            // TODO Verify if the string we received is a valid encoded string
        }
        else if (imcMessage.getMessageType() == ItemStack.class) {
            LogHelper.severe("Mod: '" + imcMessage.getSender() + "' attempting to set an EMC value via InterModCommunications with an invalid argument: ItemStack (expected String or NBTTagCompound)");
        }
        else if (imcMessage.getMessageType() == NBTTagCompound.class) {
            // TODO Handle setting an EMC value with the provided NBTTagCompound
        }
    }
}

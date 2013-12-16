package com.pahimar.ee3.imc;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.api.RecipeMapping;
import com.pahimar.ee3.api.StackValueMapping;
import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.emc.EmcValuesIMC;
import com.pahimar.ee3.item.WrappedStack;
import com.pahimar.ee3.item.crafting.RecipesIMC;
import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import net.minecraft.item.ItemStack;

import java.util.EnumSet;
import java.util.List;

public class InterModCommsHandler implements ITickHandler, IScheduledTickHandler
{

    private static Gson gson = new Gson();

    // TODO Logging

    public static void processIMCMessages(IMCEvent event)
    {

        for (IMCMessage imcMessage : event.getMessages())
        {
            processIMCMessage(imcMessage);
        }
    }

    public static void processIMCMessage(IMCMessage imcMessage)
    {

        String requestedOperation = imcMessage.key;

        if (requestedOperation.equalsIgnoreCase(InterModCommsOperations.RECIPE_ADD))
        {
            processAddRecipeMessage(imcMessage);
        }
        else if (requestedOperation.equalsIgnoreCase(InterModCommsOperations.EMC_ASSIGN_VALUE_PRE))
        {
            processPreAssignEmcValueMessage(imcMessage);
        }
        else if (requestedOperation.equalsIgnoreCase(InterModCommsOperations.EMC_ASSIGN_VALUE_POST))
        {
            processPostAssignEmcValueMessage(imcMessage);
        }
        else if (requestedOperation.equalsIgnoreCase(InterModCommsOperations.EMC_HAS_VALUE))
        {
            processHasEmcValueMessage(imcMessage);
        }
        else if (requestedOperation.equalsIgnoreCase(InterModCommsOperations.EMC_GET_VALUE))
        {
            processGetEmcValueMessage(imcMessage);
        }
    }

    private static void processAddRecipeMessage(IMCMessage imcMessage)
    {

        if (imcMessage.getMessageType() == String.class)
        {
            RecipeMapping recipeMapping = RecipeMapping.createFromJson(imcMessage.getStringValue());

            if (recipeMapping != null)
            {

                WrappedStack outputWrappedStack = recipeMapping.outputWrappedStack;
                List<WrappedStack> inputWrappedStacks = recipeMapping.inputWrappedStacks;

                RecipesIMC.addRecipe(outputWrappedStack, inputWrappedStacks);
            }
            else
            {
                // TODO Log that the message payloads json was invalid
            }
        }
        else
        {
            // TODO Log that the message payload is of an invalid type
        }
    }

    private static void processPreAssignEmcValueMessage(IMCMessage imcMessage)
    {

        if (imcMessage.getMessageType() == String.class)
        {

            StackValueMapping stackValueMapping = StackValueMapping.createFromJson(imcMessage.getStringValue());

            if (stackValueMapping != null)
            {

                WrappedStack wrappedStack = stackValueMapping.wrappedStack;
                EmcValue emcValue = stackValueMapping.emcValue;

                EmcValuesIMC.addPreAssignedValued(wrappedStack, emcValue);
            }
            else
            {
                // TODO Log that the message payloads json was invalid
            }
        }
        else
        {
            // TODO Log that the message payload is of an invalid type
        }
    }

    private static void processPostAssignEmcValueMessage(IMCMessage imcMessage)
    {

        if (imcMessage.getMessageType() == String.class)
        {

            StackValueMapping stackValueMapping = StackValueMapping.createFromJson(imcMessage.getStringValue());

            if (stackValueMapping != null)
            {

                WrappedStack wrappedStack = stackValueMapping.wrappedStack;
                EmcValue emcValue = stackValueMapping.emcValue;

                EmcValuesIMC.addPostAssignedValued(wrappedStack, emcValue);
            }
            else
            {
                // TODO Log that the message payloads json was invalid
            }
        }
        else
        {
            // TODO Log that the message payload is of an invalid type
        }
    }

    private static void processHasEmcValueMessage(IMCMessage imcMessage)
    {

        if (imcMessage.getMessageType() == String.class)
        {

            WrappedStack wrappedStack = WrappedStack.createFromJson(imcMessage.getStringValue());

            if (wrappedStack != null)
            {
                FMLInterModComms.sendRuntimeMessage(
                        EquivalentExchange3.instance,
                        imcMessage.getSender(),
                        InterModCommsOperations.EMC_RETURN_HAS_VALUE,
                        String.format("%s==%s", imcMessage.getStringValue(), String.valueOf(EmcRegistry.hasEmcValue(wrappedStack))));
            }
            else
            {
                // TODO Log that the message payloads json was invalid
            }
        }
        else if (imcMessage.getMessageType() == ItemStack.class)
        {

            ItemStack itemStack = imcMessage.getItemStackValue();

            if (itemStack != null)
            {
                FMLInterModComms.sendRuntimeMessage(
                        EquivalentExchange3.instance,
                        imcMessage.getSender(),
                        InterModCommsOperations.EMC_RETURN_HAS_VALUE,
                        String.format("%s==%s", gson.toJson(itemStack), String.valueOf(EmcRegistry.hasEmcValue(itemStack))));
            }
            else
            {
                // TODO Logging
            }
        }
        else
        {
            // TODO Log that the message payload is of an invalid type
        }
    }

    private static void processGetEmcValueMessage(IMCMessage imcMessage)
    {

        if (imcMessage.getMessageType() == String.class)
        {

            WrappedStack wrappedStack = WrappedStack.createFromJson(imcMessage.getStringValue());

            if (wrappedStack != null)
            {
                FMLInterModComms.sendRuntimeMessage(
                        EquivalentExchange3.instance,
                        imcMessage.getSender(),
                        InterModCommsOperations.EMC_RETURN_GET_VALUE,
                        String.format("%s==%s", imcMessage.getStringValue(), EmcRegistry.getEmcValue(wrappedStack).toJson()));
            }
            else
            {
                // TODO Log that the message payloads json was invalid
            }
        }
        else if (imcMessage.getMessageType() == ItemStack.class)
        {
            
            /*
             * Reply back to the mod that queried for the existance of an EmcValue for the given ItemStack
             */
            ItemStack itemStack = imcMessage.getItemStackValue();

            FMLInterModComms.sendRuntimeMessage(
                    EquivalentExchange3.instance,
                    imcMessage.getSender(),
                    InterModCommsOperations.EMC_RETURN_GET_VALUE,
                    String.format("%s==%s", gson.toJson(itemStack), EmcRegistry.getEmcValue(itemStack).toJson()));

        }
        else
        {
            // TODO Log that the message payload is of an invalid type
        }
    }

    /**
     * Runtime fetching and processing of IMC messages
     */
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {

        for (TickType tickType : type)
        {

            if (tickType == TickType.SERVER)
            {

                ImmutableList<IMCMessage> runtimeIMCMessages = FMLInterModComms.fetchRuntimeMessages(EquivalentExchange3.instance);

                for (IMCMessage imcMessage : runtimeIMCMessages)
                {
                    InterModCommsHandler.processIMCMessage(imcMessage);
                }
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {

        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel()
    {

        return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }

    @Override
    public int nextTickSpacing()
    {

        return 10;
    }
}

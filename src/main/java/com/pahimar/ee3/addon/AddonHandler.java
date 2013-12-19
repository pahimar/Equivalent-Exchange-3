package com.pahimar.ee3.addon;

import com.pahimar.ee3.api.RecipeMapping;
import com.pahimar.ee3.api.StackValueMapping;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.imc.InterModCommsOperations;
import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.common.event.FMLInterModComms;

import java.util.Arrays;
import java.util.List;

/**
 * Equivalent-Exchange-3
 * <p/>
 * AddonHandler
 *
 * @author pahimar
 */
public class AddonHandler
{

    public static void init()
    {
        AddonIndustrialCraft2.init();
    }

    public static void sendAddRecipe(Object outputObject, Object... inputObjects)
    {
        List<?> inputObjectsList = Arrays.asList(inputObjects);
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.RECIPE_ADD, new RecipeMapping(outputObject, inputObjectsList).toJson());
    }

    public static void sendPreValueAssignment(Object object, EmcValue emcValue)
    {
        sendPreValueAssignment(new StackValueMapping(object, emcValue));
    }

    public static void sendPreValueAssignment(StackValueMapping stackValueMapping)
    {
        if (stackValueMapping != null)
        {
            FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.EMC_ASSIGN_VALUE_PRE, stackValueMapping.toJson());
        }
    }

    public static void sendPostValueAssignment(Object object, EmcValue emcValue)
    {
        sendPostValueAssignment(new StackValueMapping(object, emcValue));
    }

    public static void sendPostValueAssignment(StackValueMapping stackValueMapping)
    {
        if (stackValueMapping != null)
        {
            FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.EMC_ASSIGN_VALUE_POST, stackValueMapping.toJson());
        }
    }
}

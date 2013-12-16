package com.pahimar.ee3.addon;

import com.pahimar.ee3.api.StackValueMapping;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.imc.InterModCommsOperations;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.common.event.FMLInterModComms;

public class AddonEmcValues
{

    // TODO Clean up these EmcValues and give them some proper breakdowns
    private static final EmcValue COPPER_EMC_VALUE = new EmcValue(72);
    private static final EmcValue TIN_EMC_VALUE = new EmcValue(256);

    public static void init()
    {

        /**
         * Pre-Value Assignment Mapping
         */

        /**
         * Copper
         */
        sendPreValueAssignment(new OreStack("oreCopper"), COPPER_EMC_VALUE);

        /**
         * Tin
         */
        sendPreValueAssignment(new OreStack("dustTin"), TIN_EMC_VALUE);

        /**
         * Post-Value Assignment Mapping
         */
    }

    private static void sendPreValueAssignment(Object object, EmcValue emcValue)
    {
        sendPreValueAssignment(new StackValueMapping(object, emcValue));
    }

    private static void sendPreValueAssignment(StackValueMapping stackValueMapping)
    {
        if (stackValueMapping != null)
        {
            FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.EMC_ASSIGN_VALUE_PRE, stackValueMapping.toJson());
        }
    }

    private static void sendPostValueAssignment(Object object, EmcValue emcValue)
    {
        sendPostValueAssignment(new StackValueMapping(object, emcValue));
    }

    @SuppressWarnings("unused")
    private static void sendPostValueAssignment(StackValueMapping stackValueMapping)
    {
        if (stackValueMapping != null)
        {
            FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.EMC_ASSIGN_VALUE_POST, stackValueMapping.toJson());
        }
    }
}

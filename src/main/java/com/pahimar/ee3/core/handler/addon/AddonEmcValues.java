package com.pahimar.ee3.core.handler.addon;

import com.pahimar.ee3.api.StackValueMapping;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.imc.InterModCommsOperations;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.common.event.FMLInterModComms;

public class AddonEmcValues
{

    // TODO Clean up these EmcValues and give them some proper breakdowns
    private static final EmcValue RUBBER_EMC_VALUE = new EmcValue(24);
    private static final EmcValue COPPER_EMC_VALUE = new EmcValue(85);
    private static final EmcValue TIN_EMC_VALUE = new EmcValue(256);
    private static final EmcValue IRON_EMC_VALUE = new EmcValue(256);
    private static final EmcValue SILVER_EMC_VALUE = new EmcValue(512);
    private static final EmcValue APATITE_EMC_VALUE = new EmcValue(192);
    private static final EmcValue GOLD_EMC_VALUE = new EmcValue(2048);
    private static final EmcValue ALUMINIUM_EMC_VALUE = new EmcValue(256);
    private static final EmcValue LEAD_EMC_VALUE = new EmcValue(1024);
    private static final EmcValue BRONZE_EMC_VALUE = new EmcValue(256);
    private static final EmcValue URANIUM_EMC_VALUE = new EmcValue(4096);
    private static final EmcValue CERTUS_QUARTZ_EMC_VALUE = new EmcValue(256);
    private static final EmcValue STEEL_EMC_VALUE = new EmcValue(256);
    private static final EmcValue DIAMOND_EMC_VALUE = new EmcValue(8192);
    private static final EmcValue COAL_EMC_VALUE = new EmcValue(32);
    private static final EmcValue CLAY_EMC_VALUE = new EmcValue(64);
    private static final EmcValue OBSIDIAN_EMC_VALUE = new EmcValue(64);
    private static final EmcValue LAPIS_EMC_VALUE = new EmcValue(864);
    private static final EmcValue LITHIUM_EMC_VALUE = new EmcValue(256);
    private static final EmcValue SULFUR_EMC_VALUE = new EmcValue(256);

    public static void init()
    {

        /**
         * Pre-Value Assignment Mapping
         */
        sendPreValueAssignment(new OreStack("dustCopper"), COPPER_EMC_VALUE);
        sendPreValueAssignment(new OreStack("dustTin"), TIN_EMC_VALUE);

        // Gems
        sendPreValueAssignment(new StackValueMapping(new OreStack("gemApatite"), APATITE_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("gemDiamond"), DIAMOND_EMC_VALUE));

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

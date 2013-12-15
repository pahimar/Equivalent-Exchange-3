package com.pahimar.ee3.core.handler.addon;

import com.pahimar.ee3.api.StackValueMapping;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.imc.InterModCommsOperations;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.common.event.FMLInterModComms;

import java.awt.peer.LabelPeer;

public class AddonEmcValues {

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

    public static void init() {

        /**
         * Pre-Value Assignment Mapping
         */

        // Items
        sendPreValueAssignment(new StackValueMapping(new OreStack("itemRubber"), RUBBER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crystalCertusQuartz"), CERTUS_QUARTZ_EMC_VALUE));

        // Ores
        sendPreValueAssignment(new StackValueMapping(new OreStack("oreCopper"), COPPER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("oreTin"), TIN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("oreSilver"), SILVER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("oreLead"), LEAD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("oreUranium"), URANIUM_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("oreApatite"), APATITE_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("oreAluminium"), ALUMINIUM_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("oreCertusQuartz"), CERTUS_QUARTZ_EMC_VALUE));

        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedCopper"), COPPER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedTin"), TIN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedIron"), IRON_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedSilver"), SILVER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedLead"), LEAD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedGold"), GOLD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedUranium"), URANIUM_EMC_VALUE));

        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedPurifiedCopper"), COPPER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedPurifiedTin"), TIN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedPurifiedIron"), IRON_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedPurifiedSilver"), SILVER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedPurifiedLead"), LEAD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedPurifiedGold"), GOLD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("crushedPurifiedUranium"), URANIUM_EMC_VALUE));

        // Ingots
        sendPreValueAssignment(new StackValueMapping(new OreStack("ingotCopper"), COPPER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("ingotTin"), TIN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("ingotRefinedIron"), IRON_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("ingotBronze"), BRONZE_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("ingotAluminium"), ALUMINIUM_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("ingotSteel"), STEEL_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("ingotSilver"), SILVER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("ingotLead"), LEAD_EMC_VALUE));

        // Dusts
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustCoal"), COAL_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustClay"), CLAY_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustObsidian"), OBSIDIAN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustCopper"), COPPER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustTin"), TIN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustIron"), IRON_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustCertusQuartz"), CERTUS_QUARTZ_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustBronze"), BRONZE_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustSilver"), SILVER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustLapis"), LAPIS_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustLead"), LEAD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustGold"), GOLD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustDiamond"), DIAMOND_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustLithium"), LITHIUM_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustSulfur"), SULFUR_EMC_VALUE));

        sendPreValueAssignment(new StackValueMapping(new OreStack("dustTinyCopper"), COPPER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustTinyTin"), TIN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustTinyIron"), IRON_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustTinySilver"), SILVER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustTinyLead"), LEAD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustTinyGold"), GOLD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustTinyLithium"), LITHIUM_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("dustTinySulfur"), SULFUR_EMC_VALUE));

        sendPreValueAssignment(new StackValueMapping(new OreStack("plateDenseObsidian"), OBSIDIAN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateDenseCopper"), COPPER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateDenseTin"), TIN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateDenseIron"), IRON_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateDenseRefinedIron"), IRON_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateDenseBronze"), BRONZE_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateDenseLapis"), LAPIS_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateDenseLead"), LEAD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateDenseGold"), GOLD_EMC_VALUE));

        sendPreValueAssignment(new StackValueMapping(new OreStack("plateObsidian"), OBSIDIAN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateCopper"), COPPER_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateTin"), TIN_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateIron"), IRON_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateRefinedIron"), IRON_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateBronze"), BRONZE_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateLapis"), LAPIS_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateLead"), LEAD_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("plateGold"), GOLD_EMC_VALUE));

        // Gems
        sendPreValueAssignment(new StackValueMapping(new OreStack("gemApatite"), APATITE_EMC_VALUE));
        sendPreValueAssignment(new StackValueMapping(new OreStack("gemDiamond"), DIAMOND_EMC_VALUE));

        /**
         * Post-Value Assignment Mapping
         */
    }

    private static void sendPreValueAssignment(StackValueMapping stackValueMapping) {
        if (stackValueMapping != null) {
            FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.EMC_ASSIGN_VALUE_PRE, stackValueMapping.toJson());
        }
    }

    @SuppressWarnings("unused")
    private static void sendPostValueAssignment(StackValueMapping stackValueMapping) {
        if (stackValueMapping != null) {
            FMLInterModComms.sendMessage(Reference.MOD_ID, InterModCommsOperations.EMC_ASSIGN_VALUE_POST, stackValueMapping.toJson());
        }
    }
}

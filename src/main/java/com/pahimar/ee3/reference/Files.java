package com.pahimar.ee3.reference;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.knowledge.PlayerKnowledgeRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class Files {

    public static File globalDataDirectory;
    public static File playerDataDirectory;

    private static final String ENERGY_VALUES_JSON_FILENAME = "energy-values.json";
    private static final String PRE_CALCULATION_ENERGY_VALUES_FILENAME = "pre-calculation-energy-values.json";
    private static final String POST_CALCULATION_ENERGY_VALUES_FILENAME = "post-calculation-energy-values.json";

    public static final String TEMPLATE_JSON_FILENAME = "template-player-knowledge.json";
    public static final String ABILITIES_JSON_FILENAME = "abilities.json";

    public static File abilitiesDataDirectory;
    public static File abilitiesDataFile;

    public static void init(FMLPreInitializationEvent event) {

        globalDataDirectory = new File(event.getModConfigurationDirectory().getParentFile(), "data" + File.separator + Reference.LOWERCASE_MOD_ID);

        EnergyValueRegistry.energyValuesDirectory = new File(globalDataDirectory, "energy-values");
        EnergyValueRegistry.energyValuesDirectory.mkdirs();
        EnergyValueRegistry.energyValuesFile = new File(EnergyValueRegistry.energyValuesDirectory, ENERGY_VALUES_JSON_FILENAME);
        EnergyValueRegistry.preCalculationValuesFile = new File(EnergyValueRegistry.energyValuesDirectory, PRE_CALCULATION_ENERGY_VALUES_FILENAME);
        EnergyValueRegistry.postCalculationValuesFile = new File(EnergyValueRegistry.energyValuesDirectory, POST_CALCULATION_ENERGY_VALUES_FILENAME);

        File templatePlayerKnowledgeDirectory = new File(globalDataDirectory, "knowledge" + File.separator + "transmutation");
        templatePlayerKnowledgeDirectory.mkdirs();
        PlayerKnowledgeRegistry.templatePlayerKnowledgeFile = new File(templatePlayerKnowledgeDirectory, TEMPLATE_JSON_FILENAME);

        abilitiesDataDirectory = new File(globalDataDirectory, "abilities");
        abilitiesDataDirectory.mkdirs();
        abilitiesDataFile = new File(abilitiesDataDirectory, ABILITIES_JSON_FILENAME);
    }

    /**
     * Updates the references to the instance specific EE3 data directories, creating them if they don't already exist
     */
    public static void updateFileReferences() {

        playerDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "playerdata" + File.separator + Reference.LOWERCASE_MOD_ID);
        playerDataDirectory.mkdirs();
    }
}

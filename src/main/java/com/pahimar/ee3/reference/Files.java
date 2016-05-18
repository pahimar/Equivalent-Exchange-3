package com.pahimar.ee3.reference;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class Files {

    public static File dataDirectory;

    private static final String ENERGY_VALUES_JSON = "energy-values.json";
    private static final String PRE_CALCULATION_ENERGY_VALUES = "pre-calculation-energy-values.json";
    private static final String POST_CALCULATION_ENERGY_VALUES = "post-calculation-energy-values.json";

    public static final String TEMPLATE_JSON_FILE = "template.json";
    public static final String ABILITIES_JSON_FILE = "abilities.json";

    public static File abilitiesDataDirectory;
    public static File knowledgeDataDirectory;

    public static File abilitiesDataFile;
    public static File knowledgeDataFile;

    public static void init(FMLPreInitializationEvent event) {

        dataDirectory = new File(event.getModConfigurationDirectory().getParentFile(), "data" + File.separator + Reference.LOWERCASE_MOD_ID);

        EnergyValueRegistry.energyValuesDirectory = new File(dataDirectory, "energy-values");
        EnergyValueRegistry.energyValuesDirectory.mkdirs();
        EnergyValueRegistry.energyValuesFile = new File(EnergyValueRegistry.energyValuesDirectory, ENERGY_VALUES_JSON);
        EnergyValueRegistry.preCalculationValuesFile = new File(EnergyValueRegistry.energyValuesDirectory, PRE_CALCULATION_ENERGY_VALUES);
        EnergyValueRegistry.postCalculationValuesFile = new File(EnergyValueRegistry.energyValuesDirectory, POST_CALCULATION_ENERGY_VALUES);

        abilitiesDataDirectory = new File(dataDirectory, "abilities");
        abilitiesDataDirectory.mkdirs();
        abilitiesDataFile = new File(abilitiesDataDirectory, ABILITIES_JSON_FILE);

        knowledgeDataDirectory = new File(dataDirectory, "knowledge");
        knowledgeDataDirectory.mkdirs();
        knowledgeDataFile = new File(knowledgeDataDirectory, TEMPLATE_JSON_FILE);
    }
}

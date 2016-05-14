package com.pahimar.ee3.reference;

import com.pahimar.ee3.exchange.NewEnergyValueRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class Files {

    public static final String ENERGY_VALUES_JSON = "energy-values.json";
    public static final String PRE_CALCULATION_ENERGY_VALUES = "pre-calculation-energy-values.json";
    public static final String POST_CALCULATION_ENERGY_VALUES = "post-calculation-energy-values.json";

    public static final String TEMPLATE_JSON_FILE = "template.json";
    public static final String ABILITIES_JSON_FILE = "abilities.json";

    public static File dataDirectory;

    public static File abilitiesDataDirectory;
    public static File knowledgeDataDirectory;

    public static File abilitiesDataFile;
    public static File knowledgeDataFile;

    public static void init(FMLPreInitializationEvent event) {

        dataDirectory = new File(event.getModConfigurationDirectory().getParentFile(), "data" + File.separator + Reference.LOWERCASE_MOD_ID);

        NewEnergyValueRegistry.energyValuesDirectory = new File(dataDirectory, "energy-values");
        NewEnergyValueRegistry.energyValuesDirectory.mkdirs();
        NewEnergyValueRegistry.energyValuesFile = new File(NewEnergyValueRegistry.energyValuesDirectory, ENERGY_VALUES_JSON);
        NewEnergyValueRegistry.preCalculationValuesFile = new File(NewEnergyValueRegistry.energyValuesDirectory, PRE_CALCULATION_ENERGY_VALUES);
        NewEnergyValueRegistry.postCalculationValuesFile = new File(NewEnergyValueRegistry.energyValuesDirectory, POST_CALCULATION_ENERGY_VALUES);

        abilitiesDataDirectory = new File(dataDirectory, "abilities");
        abilitiesDataDirectory.mkdirs();
        abilitiesDataFile = new File(abilitiesDataDirectory, ABILITIES_JSON_FILE);

        knowledgeDataDirectory = new File(dataDirectory, "knowledge");
        knowledgeDataDirectory.mkdirs();
        knowledgeDataFile = new File(knowledgeDataDirectory, TEMPLATE_JSON_FILE);
    }

    public static class Global
    {
        public static File dataDirectory;

        public static File preCalcluationEnergyValueFile;
        public static File postCalcluationEnergyValueFile;

        public static File abilityFile;

        public static File templateTransmutationKnowledgeFile;

        public static void init(FMLPreInitializationEvent event)
        {
            dataDirectory = new File(event.getModConfigurationDirectory().getParentFile(), "data" + File.separator + Reference.LOWERCASE_MOD_ID);
            dataDirectory.mkdirs();

            File energyValueDataDirectory = new File(dataDirectory, "energy-values");
            energyValueDataDirectory.mkdirs();
            preCalcluationEnergyValueFile = new File(energyValueDataDirectory, PRE_CALCULATION_ENERGY_VALUES);
            postCalcluationEnergyValueFile = new File(energyValueDataDirectory, POST_CALCULATION_ENERGY_VALUES);

            File abilityDataDirectory = new File(dataDirectory, "abilities");
            abilityDataDirectory.mkdirs();
            abilityFile = new File(abilityDataDirectory, ABILITIES_JSON_FILE);

            File knowledgeDataDirectory = new File(dataDirectory, "knowledge");
            knowledgeDataDirectory.mkdirs();
            templateTransmutationKnowledgeFile = new File(knowledgeDataDirectory, TEMPLATE_JSON_FILE);
        }
    }
}

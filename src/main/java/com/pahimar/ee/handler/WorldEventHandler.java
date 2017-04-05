package com.pahimar.ee.handler;

import com.pahimar.ee.exchange.EnergyValueRegistry;
import com.pahimar.ee.knowledge.PlayerKnowledgeRegistry;
import com.pahimar.ee.recipe.AludelRecipeManager;
import com.pahimar.ee.recipe.RecipeRegistry;
import com.pahimar.ee.util.LogHelper;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

public class WorldEventHandler {

    public static boolean hasInitilialized = false;

    @SubscribeEvent
    public void onWorldLoadEvent(WorldEvent.Load event) {

        if (!hasInitilialized && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {

            RecipeRegistry.INSTANCE.registerVanillaRecipes();
            AludelRecipeManager.registerRecipes();

            long startTime = System.nanoTime();
            if (ConfigurationHandler.Settings.regenerateEnergyValuesWhen.equalsIgnoreCase("As Needed")) {
                EnergyValueRegistry.INSTANCE.load();
            }
            else {
                EnergyValueRegistry.INSTANCE.compute();
            }
            LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Energy value system initialized {} values after {} ms", EnergyValueRegistry.INSTANCE.getEnergyValues().size(), (System.nanoTime() - startTime) / 100000);
            hasInitilialized = true;

            PlayerKnowledgeRegistry.INSTANCE.load();
        }
    }
}

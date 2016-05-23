package com.pahimar.ee3.handler;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.knowledge.PlayerKnowledgeRegistry;
import com.pahimar.ee3.recipe.AludelRecipeManager;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.event.world.WorldEvent;

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

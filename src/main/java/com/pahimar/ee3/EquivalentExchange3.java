package com.pahimar.ee3;

import com.pahimar.ee3.array.AlchemyArrayRegistry;
import com.pahimar.ee3.blacklist.BlacklistRegistry;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.knowledge.PlayerKnowledgeRegistry;
import com.pahimar.ee3.proxy.IProxy;
import com.pahimar.ee3.recipe.AludelRecipeManager;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.util.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = EquivalentExchange3.MOD_ID,
        name = "Equivalent Exchange 3",
        certificateFingerprint = EquivalentExchange3.FINGERPRINT,
        version = "@MOD_VERSION@",
        dependencies = "required-after:Forge@[12.18.1,)",
        guiFactory = "com.pahimar.ee3.client.gui.GuiFactory",
        updateJSON = "http://ee3.pahimar.com/update/versions.json")
public class EquivalentExchange3 {

    public static final String MOD_ID = "ee3";
    static final String FINGERPRINT = "@FINGERPRINT@";

    @Mod.Instance(EquivalentExchange3.MOD_ID)
    public static EquivalentExchange3 instance;

    @SidedProxy(clientSide = "com.pahimar.ee3.proxy.ClientProxy", serverSide = "com.pahimar.ee3.proxy.ServerProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event) {

        if (FINGERPRINT.equals("@FINGERPRINT@")) {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        }
        else {
            LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        proxy.onServerStarting(event);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.onPreInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.onInit(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.onPostInit(event);
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        proxy.onServerStopping(event);
    }

    public EnergyValueRegistry getEnergyValueRegistry() {
        return EnergyValueRegistry.INSTANCE;
    }

    public RecipeRegistry getRecipeRegistry() {
        return RecipeRegistry.INSTANCE;
    }

    public AludelRecipeManager getAludelRecipeManager() {
        return AludelRecipeManager.getInstance();
    }

    public BlacklistRegistry getBlacklistRegistry() {
        return BlacklistRegistry.INSTANCE;
    }

    public AlchemyArrayRegistry getAlchemyArrayRegistry() {
        return AlchemyArrayRegistry.INSTANCE;
    }

    public PlayerKnowledgeRegistry getPlayerKnowledgeRegistry() {
        return PlayerKnowledgeRegistry.INSTANCE;
    }
}

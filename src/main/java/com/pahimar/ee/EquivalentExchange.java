package com.pahimar.ee;

import com.pahimar.ee.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = EquivalentExchange.MOD_ID,
        name = "Equivalent Exchange",
        certificateFingerprint = EquivalentExchange.FINGERPRINT,
        version = "@MOD_VERSION@",
        updateJSON = "https://ee.pahimar.com/update/versions.json")
public class EquivalentExchange {

    /**
     * TODO Finish Javadoc
     */
    static final String FINGERPRINT = "@FINGERPRINT@";

    /**
     * TODO Finish Javadoc
     */
    public static final String MOD_ID = "ee";

    /**
     * TODO Finish Javadoc
     */
    public static final Logger LOGGER = LogManager.getLogger(EquivalentExchange.MOD_ID);

    @Mod.Instance
    public static EquivalentExchange instance;

    @SidedProxy(clientSide = "com.pahimar.ee.proxy.ClientProxy", serverSide = "com.pahimar.ee.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        proxy.onServerStarting(event);
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        proxy.onPreInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.onInit(event);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.onPostInit(event);
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        proxy.onServerStopping(event);
    }
}

package com.pahimar.ee3.client.gui;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.handler.ConfigurationHandler;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig
{
    public ModGuiConfig(GuiScreen guiScreen)
    {
        super(guiScreen, new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), EquivalentExchange3.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }
}

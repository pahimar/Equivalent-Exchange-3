package com.pahimar.ee3.handler;

import java.util.EnumSet;

import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.config.Configuration;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.configuration.GeneralConfiguration;
import com.pahimar.ee3.helper.VersionHelper;
import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

/**
 * Equivalent-Exchange-3
 * <p/>
 * VersionCheckTickHandler
 *
 * @author pahimar
 */
public class VersionCheckTickHandler
{

    private static boolean initialized = false;

    @SubscribeEvent
    public void tickEnd(TickEvent.ClientTickEvent event)
    {

        if (ConfigurationSettings.DISPLAY_VERSION_RESULT)
        {
            if (!initialized)
            {

                        if (FMLClientHandler.instance().getClient().currentScreen == null)
                        {
                            if (VersionHelper.getResult() != VersionHelper.UNINITIALIZED || VersionHelper.getResult() != VersionHelper.FINAL_ERROR)
                            {

                                initialized = true;

                                if (VersionHelper.getResult() == VersionHelper.OUTDATED)
                                {
                                    FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(VersionHelper.getResultMessageForClient()));
                                    GeneralConfiguration.set(Configuration.CATEGORY_GENERAL, ConfigurationSettings.DISPLAY_VERSION_RESULT_CONFIGNAME, "false");
                                }
                            }
                        }

            }
        }
    }
}

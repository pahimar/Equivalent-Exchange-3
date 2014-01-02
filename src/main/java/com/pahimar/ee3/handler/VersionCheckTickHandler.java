package com.pahimar.ee3.handler;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.configuration.GeneralConfiguration;
import com.pahimar.ee3.helper.VersionHelper;
import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraftforge.common.Configuration;

import java.util.EnumSet;

/**
 * Equivalent-Exchange-3
 * <p/>
 * VersionCheckTickHandler
 *
 * @author pahimar
 */
public class VersionCheckTickHandler implements ITickHandler
{

    private static boolean initialized = false;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {

        if (ConfigurationSettings.DISPLAY_VERSION_RESULT)
        {
            if (!initialized)
            {
                for (TickType tickType : type)
                {
                    if (tickType == TickType.CLIENT)
                    {
                        if (FMLClientHandler.instance().getClient().currentScreen == null)
                        {
                            if (VersionHelper.getResult() != VersionHelper.UNINITIALIZED || VersionHelper.getResult() != VersionHelper.FINAL_ERROR)
                            {

                                initialized = true;

                                if (VersionHelper.getResult() == VersionHelper.OUTDATED)
                                {
                                    FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(VersionHelper.getResultMessageForClient());
                                    GeneralConfiguration.set(Configuration.CATEGORY_GENERAL, ConfigurationSettings.DISPLAY_VERSION_RESULT_CONFIGNAME, "false");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {

        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel()
    {

        return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }
}

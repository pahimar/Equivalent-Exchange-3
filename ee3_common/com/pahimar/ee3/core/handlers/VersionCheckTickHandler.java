package com.pahimar.ee3.core.handlers;

import java.util.EnumSet;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.core.helper.VersionHelper;
import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * VersionCheckTickHandler
 * 
 * Class for notifying the player on their client when they get in game the
 * outcome of the remote version check
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class VersionCheckTickHandler implements ITickHandler {

    private static boolean initialized = false;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        if (ConfigurationSettings.ENABLE_VERSION_CHECK) {
            if (!initialized) {
                for (TickType tickType : type) {
                    if (tickType == TickType.CLIENT) {
                        if (FMLClientHandler.instance().getClient().currentScreen == null) {
                            if ((VersionHelper.getResult() != VersionHelper.UNINITIALIZED) || (VersionHelper.getResult() != VersionHelper.FINAL_ERROR)) {
                                
                                initialized = true;
                                
                                if (VersionHelper.getResult() == VersionHelper.OUTDATED) {
                                    FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(VersionHelper.getResultMessageForClient());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {

        return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }

}

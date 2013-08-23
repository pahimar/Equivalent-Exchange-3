package com.pahimar.ee3.command;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * Equivalent-Exchange-3
 * 
 * CommandHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CommandHandler {

    public static void initCommands(FMLServerStartingEvent event) {

        event.registerServerCommand(new CommandEE());
    }
}

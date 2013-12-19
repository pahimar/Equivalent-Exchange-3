package com.pahimar.ee3.command;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CommandHandler
 *
 * @author pahimar
 */
public class CommandHandler
{

    public static void initCommands(FMLServerStartingEvent event)
    {

        event.registerServerCommand(new CommandEE());
    }
}

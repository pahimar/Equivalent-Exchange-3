package com.pahimar.ee3.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.core.util.LocalizationUtil;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Commands;
import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * CommandParticles
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CommandParticles {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        String subCommand;

        if (args.length > 0) {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals(Commands.COMMAND_ON)) {
                processOnCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals(Commands.COMMAND_OFF)) {
                processOffCommand(commandSender);
            }
            else
                throw new WrongUsageException(Commands.COMMAND_PARTICLES_USAGE, new Object[0]);
        }
        else
            throw new WrongUsageException(Commands.COMMAND_PARTICLES_USAGE, new Object[0]);
    }

    private static void processOnCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_PARTICLE_FX = true;
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_PARTICLE_FX_CONFIGNAME, Strings.TRUE);
        commandSender.sendChatToPlayer(Colours.TEXT_COLOUR_PREFIX_GRAY + LocalizationUtil.getLocalizedString(Commands.COMMAND_PARTICLES_TURNED_ON));
    }

    private static void processOffCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_PARTICLE_FX = false;
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_PARTICLE_FX_CONFIGNAME, Strings.FALSE);
        commandSender.sendChatToPlayer(Colours.TEXT_COLOUR_PREFIX_GRAY + LocalizationUtil.getLocalizedString(Commands.COMMAND_PARTICLES_TURNED_OFF));
    }
}

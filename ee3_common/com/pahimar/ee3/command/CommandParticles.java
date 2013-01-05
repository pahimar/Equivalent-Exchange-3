package com.pahimar.ee3.command;

import static com.pahimar.ee3.core.helper.LocalizationHelper.getLocalizedString;
import static com.pahimar.ee3.lib.Strings.*;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.configuration.ConfigurationSettings;

public class CommandParticles {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        String subCommand;

        if (args.length > 0) {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals(getLocalizedString(COMMAND_ON))) {
                processOnCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals(getLocalizedString(COMMAND_OFF))) {
                processOffCommand(commandSender);
            }
            else {
                throw new WrongUsageException(getLocalizedString(COMMAND_PARTICLES_USAGE), new Object[0]);
            }
        }
        else {
            throw new WrongUsageException(getLocalizedString(COMMAND_PARTICLES_USAGE), new Object[0]);
        }
    }

    private static void processOnCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_PARTICLE_FX = true;
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_PARTICLE_FX_CONFIGNAME, TRUE);
        commandSender.sendChatToPlayer(getLocalizedString(COMMAND_PARTICLES_TURNED_ON));
    }

    private static void processOffCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_PARTICLE_FX = false;
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_PARTICLE_FX_CONFIGNAME, FALSE);
        commandSender.sendChatToPlayer(getLocalizedString(COMMAND_PARTICLES_TURNED_OFF));
    }
}

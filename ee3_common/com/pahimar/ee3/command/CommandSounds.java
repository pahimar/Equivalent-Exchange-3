package com.pahimar.ee3.command;

import static com.pahimar.ee3.core.helper.LocalizationHelper.getLocalizedString;
import static com.pahimar.ee3.lib.Strings.*;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.configuration.ConfigurationSettings;

public class CommandSounds {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        String subCommand;

        if (args.length > 0) {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals(getLocalizedString(COMMAND_ALL))) {
                processAllCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals(getLocalizedString(COMMAND_SELF))) {
                processSelfCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals(getLocalizedString(COMMAND_OFF))) {
                processOffCommand(commandSender);
            }
            else {
                throw new WrongUsageException(getLocalizedString(COMMAND_SOUNDS_USAGE), new Object[0]);
            }
        }
        else {
            throw new WrongUsageException(getLocalizedString(COMMAND_SOUNDS_USAGE), new Object[0]);
        }
    }

    private static void processAllCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_SOUNDS = ALL;
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, ALL);
        commandSender.sendChatToPlayer(getLocalizedString(COMMAND_SOUNDS_SET_TO_ALL));
    }

    private static void processSelfCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_SOUNDS = SELF;
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, SELF);
        commandSender.sendChatToPlayer(getLocalizedString(COMMAND_SOUNDS_SET_TO_SELF));
    }

    private static void processOffCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_SOUNDS = OFF;
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, OFF);
        commandSender.sendChatToPlayer(getLocalizedString(COMMAND_SOUNDS_TURNED_OFF));
    }
}

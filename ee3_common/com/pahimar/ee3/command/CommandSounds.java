package com.pahimar.ee3.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.configuration.ConfigurationSettings;

public class CommandSounds {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        String subCommand;

        if (args.length > 0) {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals("all")) {
                processAllCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals("self")) {
                processSelfCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals("off")) {
                processOffCommand(commandSender);
            }
            else {
                throw new WrongUsageException("commands.ee3.sounds.usage", new Object[0]);
            }
        }
        else {
            throw new WrongUsageException("commands.ee3.sounds.usage", new Object[0]);
        }
    }

    private static void processAllCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_SOUNDS = "all";
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, "all");
        commandSender.sendChatToPlayer("commands.ee3.sounds.all");
    }
    
    private static void processSelfCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_SOUNDS = "self";
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, "self");
        commandSender.sendChatToPlayer("commands.ee3.sounds.self");
    }

    private static void processOffCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_SOUNDS = "off";
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, "false");
        commandSender.sendChatToPlayer("commands.ee3.sounds.off");
    }
}

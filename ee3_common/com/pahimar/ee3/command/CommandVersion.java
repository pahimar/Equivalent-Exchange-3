package com.pahimar.ee3.command;

import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.core.helper.LocalizationHelper;
import com.pahimar.ee3.core.helper.VersionHelper;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;


public class CommandVersion {
    
    public static void processCommand(ICommandSender commandSender, String[] args) {
        
        String subCommand;

        if (args.length > 0) {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals(Commands.COMMAND_VERSION)) {
                processVersionCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals(Commands.COMMAND_CHANGELOG)) {
                processChangelogCommand(commandSender);
            }
            else {
                throw new WrongUsageException(Commands.COMMAND_VERSION_USAGE, new Object[0]);
            }
        }
        else {
            throw new WrongUsageException(Commands.COMMAND_VERSION_USAGE, new Object[0]);
        }
    }
    
    private static void processVersionCommand(ICommandSender commandSender) {

        commandSender.sendChatToPlayer(VersionHelper.getResultMessageForClient());
    }
    
    private static void processChangelogCommand(ICommandSender commandSender) {
        
        
    }

}

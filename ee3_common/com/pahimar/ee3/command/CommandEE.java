package com.pahimar.ee3.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.configuration.ConfigurationSettings;

public class CommandEE extends CommandBase {

    public String getCommandName() {

        return "ee3";
    }

    public static List getListOfStringsMatchingLastWord(String[] par0ArrayOfStr, String... par1ArrayOfStr) {

        // TODO
        return null;
    }

    public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {

        // TODO
        return null;
    }

    public void processCommand(ICommandSender commandSender, String[] args) {

        String commandName;

        if (args.length > 0) {
            commandName = args[0];
            System.arraycopy(args, 1, args, 0, args.length - 1);

            if (commandName.toLowerCase().equals("overlay")) {
                CommandOverlay.processCommand(commandSender, args);
            }
            else if (commandName.toLowerCase().equals("particles")) {
                CommandParticles.processCommand(commandSender, args);
            }
        }
        else {
            throw new WrongUsageException("commands.ee3.usage", new Object[0]);
        }
    }

    
}

package com.pahimar.ee3.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class CommandEE extends CommandBase {

    public String getCommandName() {

        return "ee3";
    }

    public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {

        switch (args.length) {
            case 1: {
                return getListOfStringsMatchingLastWord(args, new String[] { "overlay", "particles" });
            }
            case 2: {
                if (args[0].equalsIgnoreCase("overlay")) {
                    return getListOfStringsMatchingLastWord(args, new String[] { "on", "off", "position", "scale", "opacity" });
                }
                else if (args[0].equalsIgnoreCase("particles")) {
                    return getListOfStringsMatchingLastWord(args, new String[] { "on", "off" });
                }
            }
            case 3: {
                if (args[0].equalsIgnoreCase("overlay")) {
                    if (args[1].equalsIgnoreCase("position")) {
                        return getListOfStringsMatchingLastWord(args, new String[] { "top", "bottom" });
                    }
                }
            }
            case 4: {
                if (args[0].equalsIgnoreCase("overlay")) {
                    if (args[1].equalsIgnoreCase("position")) {
                        if (args[2].equalsIgnoreCase("top") || args[2].equalsIgnoreCase("bottom")) {
                            return getListOfStringsMatchingLastWord(args, new String[] { "left", "right" });
                        }
                    }
                }
            }
            default: {
                return null;
            }
        }
    }

    public void processCommand(ICommandSender commandSender, String[] args) {

        if (args.length > 0) {
            String commandName = args[0];
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

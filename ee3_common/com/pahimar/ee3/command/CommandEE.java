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

            // TODO Find a nice neat way to implement Commands like we did Packets
            if (commandName.toLowerCase().equals("overlay")) {
                processOverlayCommand(commandSender, args);
            }
        }
        else {
            throw new WrongUsageException("commands.ee3.usage", new Object[0]);
        }
    }

    public void processOverlayCommand(ICommandSender commandSender, String[] args) {

        String subCommand;

        if (args.length > 0) {
            subCommand = args[0];

            switch (subCommand) {
                case "on": {
                    ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION = true;
                    ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME, "true");
                    commandSender.sendChatToPlayer("commands.ee3.overlay.turned_on");
                    break;
                }
                case "off": {
                    ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION = false;
                    ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME, "false");
                    commandSender.sendChatToPlayer("commands.ee3.overlay.turned_off");
                    break;
                }
                case "move": {

                    String xPosition, yPosition;

                    if (args.length >= 3) {
                        xPosition = args[1];
                        yPosition = args[2];

                        if ((xPosition.toLowerCase().equals("top")) && (yPosition.toLowerCase().equals("left"))) {
                            ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 0;
                            ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "0");
                            commandSender.sendChatToPlayer("commands.ee3.overlay.move.top_left");
                        }
                        else if ((xPosition.toLowerCase().equals("top")) && (yPosition.toLowerCase().equals("right"))) {
                            ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 1;
                            ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "1");
                            commandSender.sendChatToPlayer("commands.ee3.overlay.move.top_right");
                        }
                        else if ((xPosition.toLowerCase().equals("bottom")) && (yPosition.toLowerCase().equals("left"))) {
                            ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 2;
                            ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "2");
                            commandSender.sendChatToPlayer("commands.ee3.overlay.move.bottom_left");
                        }
                        else if ((xPosition.toLowerCase().equals("bottom")) && (yPosition.toLowerCase().equals("right"))) {
                            ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 3;
                            ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "3");
                            commandSender.sendChatToPlayer("commands.ee3.overlay.move.bottom_right");
                        }
                        else {
                            throw new WrongUsageException("commands.ee3.overlay.move.usage", new Object[0]);
                        }
                    }
                    else {
                        new WrongUsageException("commands.ee3.overlay.move.usage", new Object[0]);
                    }
                    break;
                }
                default: {
                    throw new WrongUsageException("commands.ee3.overlay.usage", new Object[0]);
                }
            }
        }
        else {
            throw new WrongUsageException("commands.ee3.overlay.usage", new Object[0]);
        }
    }
}

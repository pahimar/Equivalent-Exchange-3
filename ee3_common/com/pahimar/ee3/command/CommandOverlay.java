package com.pahimar.ee3.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.configuration.ConfigurationSettings;

public class CommandOverlay {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        String subCommand;

        if (args.length > 0) {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals("on")) {
                ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION = true;
                ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME, "true");
                commandSender.sendChatToPlayer("commands.ee3.overlay.turned_on");
            }
            else if (subCommand.toLowerCase().equals("off")) {
                ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION = false;
                ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME, "false");
                commandSender.sendChatToPlayer("commands.ee3.overlay.turned_off");
            }
            else if (subCommand.toLowerCase().equals("opacity")) {
                if (args.length >= 2) {
                    try {
                        float opacity = Float.parseFloat(args[1]);
                        
                        if ((opacity < 0F) || (opacity > 1F)) {
                            throw new WrongUsageException("commands.ee3.overlay.opacity.usage", new Object[0]);
                        }
                        else {
                            ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY = opacity;
                            ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY_CONFIGNAME, args[1]);
                            commandSender.sendChatToPlayer("commands.ee3.overlay.opacity.updated");
                        }
                    }
                    catch (Exception e) {
                        throw new WrongUsageException("commands.ee3.overlay.opacity.usage", new Object[0]);
                    }
                }
                else {
                    throw new WrongUsageException("commands.ee3.overlay.opacity.usage", new Object[0]);
                }
            }
            else if (subCommand.toLowerCase().equals("scale")) {
                if (args.length >= 2) {
                    try {
                        float scale = Float.parseFloat(args[1]);
                        
                        if (scale <= 0F) {
                            throw new WrongUsageException("commands.ee3.overlay.scale.usage", new Object[0]);
                        }
                        else {
                            ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE = scale;
                            ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE_CONFIGNAME, args[1]);
                            commandSender.sendChatToPlayer("commands.ee3.overlay.scale.updated");
                        }
                    }
                    catch (Exception e) {
                        throw new WrongUsageException("commands.ee3.overlay.opacity.usage", new Object[0]);
                    }
                }
                else {
                    throw new WrongUsageException("commands.ee3.overlay.opacity.usage", new Object[0]);
                }
            }
            else if (subCommand.toLowerCase().equals("position")) {

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
            }
            else {
                throw new WrongUsageException("commands.ee3.overlay.usage", new Object[0]);
            }
        }
        else {
            throw new WrongUsageException("commands.ee3.overlay.usage", new Object[0]);
        }
    }
}

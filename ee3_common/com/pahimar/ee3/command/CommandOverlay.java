package com.pahimar.ee3.command;

import static com.pahimar.ee3.core.helper.LocalizationHelper.getLocalizedString;
import static com.pahimar.ee3.lib.Strings.*;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.configuration.ConfigurationSettings;

public class CommandOverlay {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        if (args.length > 0) {
            String subCommand = args[0];

            if (subCommand.equalsIgnoreCase(getLocalizedString(COMMAND_ON))) {
                processOnCommand(commandSender);
            }
            else if (subCommand.equalsIgnoreCase(getLocalizedString(COMMAND_OFF))) {
                processOffCommand(commandSender);
            }
            else if (subCommand.equalsIgnoreCase(getLocalizedString(COMMAND_OPACITY))) {
                processOpacityCommand(commandSender, args);
            }
            else if (subCommand.equalsIgnoreCase(getLocalizedString(COMMAND_SCALE))) {
                processScaleCommand(commandSender, args);
            }
            else if (subCommand.equalsIgnoreCase(getLocalizedString(COMMAND_POSITION))) {
                processPositionCommand(commandSender, args);
            }
            else {
                throw new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_USAGE), new Object[0]);
            }
        }
        else {
            throw new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_USAGE), new Object[0]);
        }
    }

    private static void processOnCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION = true;
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME, TRUE);
        commandSender.sendChatToPlayer(getLocalizedString(COMMAND_OVERLAY_TURNED_ON));
    }

    private static void processOffCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION = false;
        ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME, FALSE);
        commandSender.sendChatToPlayer(getLocalizedString(COMMAND_OVERLAY_TURNED_OFF));
    }

    private static void processScaleCommand(ICommandSender commandSender, String[] args) {

        if (args.length >= 2) {
            try {
                float scale = Float.parseFloat(args[1]);

                if (scale <= 0F) {
                    throw new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_SCALE_USAGE), new Object[0]);
                }
                else {
                    ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE = scale;
                    ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE_CONFIGNAME, args[1]);
                    commandSender.sendChatToPlayer(getLocalizedString(COMMAND_OVERLAY_SCALE_UPDATED));
                }
            }
            catch (Exception e) {
                throw new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_SCALE_USAGE), new Object[0]);
            }
        }
        else {
            throw new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_SCALE_USAGE), new Object[0]);
        }
    }

    private static void processOpacityCommand(ICommandSender commandSender, String[] args) {

        if (args.length >= 2) {
            try {
                float opacity = Float.parseFloat(args[1]);

                if ((opacity < 0F) || (opacity > 1F)) {
                    throw new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_OPACITY_USAGE), new Object[0]);
                }
                else {
                    ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY = opacity;
                    ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY_CONFIGNAME, args[1]);
                    commandSender.sendChatToPlayer(getLocalizedString(COMMAND_OVERLAY_OPACITY_UPDATED));
                }
            }
            catch (Exception e) {
                throw new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_OPACITY_USAGE), new Object[0]);
            }
        }
        else {
            throw new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_OPACITY_USAGE), new Object[0]);
        }
    }

    private static void processPositionCommand(ICommandSender commandSender, String[] args) {

        String yPosition, xPosition;

        if (args.length >= 3) {
            yPosition = args[1];
            xPosition = args[2];

            if ((yPosition.equalsIgnoreCase(getLocalizedString(COMMAND_TOP))) && (xPosition.equalsIgnoreCase(getLocalizedString(COMMAND_LEFT)))) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 0;
                ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "0");
                commandSender.sendChatToPlayer(getLocalizedString(COMMAND_OVERLAY_POSITION_TOP_LEFT));
            }
            else if ((yPosition.equalsIgnoreCase(getLocalizedString(COMMAND_TOP))) && (xPosition.equalsIgnoreCase(getLocalizedString(COMMAND_RIGHT)))) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 1;
                ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "1");
                commandSender.sendChatToPlayer(getLocalizedString(COMMAND_OVERLAY_POSITION_TOP_RIGHT));
            }
            else if ((yPosition.equalsIgnoreCase(getLocalizedString(COMMAND_BOTTOM))) && (xPosition.equalsIgnoreCase(getLocalizedString(COMMAND_LEFT)))) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 2;
                ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "2");
                commandSender.sendChatToPlayer(getLocalizedString(COMMAND_OVERLAY_POSITION_BOTTOM_LEFT));
            }
            else if ((yPosition.equalsIgnoreCase(getLocalizedString(COMMAND_BOTTOM))) && (xPosition.equalsIgnoreCase(getLocalizedString(COMMAND_RIGHT)))) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 3;
                ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "3");
                commandSender.sendChatToPlayer(getLocalizedString(COMMAND_OVERLAY_POSITION_BOTTOM_RIGHT));
            }
            else {
                throw new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_POSITION_USAGE), new Object[0]);
            }
        }
        else {
            new WrongUsageException(getLocalizedString(COMMAND_OVERLAY_POSITION_USAGE), new Object[0]);
        }
    }
}

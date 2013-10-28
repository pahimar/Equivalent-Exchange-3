package com.pahimar.ee3.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatMessageComponent;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.configuration.GeneralConfiguration;
import com.pahimar.ee3.lib.Commands;
import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * CommandOverlay
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CommandOverlay {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        if (args.length > 0) {
            String subCommand = args[0];

            if (subCommand.equalsIgnoreCase(Commands.COMMAND_ON)) {
                processOnCommand(commandSender);
            }
            else if (subCommand.equalsIgnoreCase(Commands.COMMAND_OFF)) {
                processOffCommand(commandSender);
            }
            else if (subCommand.equalsIgnoreCase(Commands.COMMAND_OPACITY)) {
                processOpacityCommand(commandSender, args);
            }
            else if (subCommand.equalsIgnoreCase(Commands.COMMAND_SCALE)) {
                processScaleCommand(commandSender, args);
            }
            else if (subCommand.equalsIgnoreCase(Commands.COMMAND_POSITION)) {
                processPositionCommand(commandSender, args);
            }
            else
                throw new WrongUsageException(Commands.COMMAND_OVERLAY_USAGE, new Object[0]);
        }
        else
            throw new WrongUsageException(Commands.COMMAND_OVERLAY_USAGE, new Object[0]);
    }

    private static void processOnCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION = true;
        GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME, Strings.TRUE);
        commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_OVERLAY_TURNED_ON));
    }

    private static void processOffCommand(ICommandSender commandSender) {

        ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION = false;
        GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME, Strings.FALSE);
        commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_OVERLAY_TURNED_OFF));
    }

    private static void processScaleCommand(ICommandSender commandSender, String[] args) {

        if (args.length > 2 && args.length < 4) {
            try {
                float scale = Float.parseFloat(args[1]);

                if (scale <= 0F) {
                    throw new WrongUsageException(Commands.COMMAND_OVERLAY_SCALE_USAGE_ADDITIONAL_TEXT, new Object[] { Commands.COMMAND_OVERLAY_SCALE_USAGE });
                }
                else {
                    ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE = scale;
                    GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE_CONFIGNAME, args[1]);
                    commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_OVERLAY_SCALE_UPDATED));
                }
            }
            catch (Exception e) {
                throw new WrongUsageException(Commands.COMMAND_OVERLAY_SCALE_USAGE_ADDITIONAL_TEXT, new Object[] { Commands.COMMAND_OVERLAY_SCALE_USAGE });
            }
        }
        else {
            throw new WrongUsageException(Commands.COMMAND_OVERLAY_SCALE_USAGE_ADDITIONAL_TEXT, new Object[] { Commands.COMMAND_OVERLAY_SCALE_USAGE });
        }
    }

    private static void processOpacityCommand(ICommandSender commandSender, String[] args) {

        if (args.length > 2 && args.length < 4) {
            try {
                float opacity = Float.parseFloat(args[1]);

                if (opacity < 0F || opacity > 1F) {
                    throw new WrongUsageException(Commands.COMMAND_OVERLAY_OPACITY_USAGE_ADDITIONAL_TEXT, new Object[] { Commands.COMMAND_OVERLAY_OPACITY_USAGE });
                }
                else {
                    ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY = opacity;
                    GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY_CONFIGNAME, args[1]);
                    commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_OVERLAY_OPACITY_UPDATED));
                }
            }
            catch (Exception e) {
                throw new WrongUsageException(Commands.COMMAND_OVERLAY_OPACITY_USAGE_ADDITIONAL_TEXT, new Object[] { Commands.COMMAND_OVERLAY_OPACITY_USAGE });
            }
        }
        else
            throw new WrongUsageException(Commands.COMMAND_OVERLAY_OPACITY_USAGE_ADDITIONAL_TEXT, new Object[] { Commands.COMMAND_OVERLAY_OPACITY_USAGE });
    }

    private static void processPositionCommand(ICommandSender commandSender, String[] args) {

        String yPosition, xPosition;

        if (args.length > 2 && args.length < 5) {
            yPosition = args[1];
            xPosition = args[2];

            if (yPosition.equalsIgnoreCase(Commands.COMMAND_TOP) && xPosition.equalsIgnoreCase(Commands.COMMAND_LEFT)) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 0;
                GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "0");
                commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_OVERLAY_POSITION_TOP_LEFT));
            }
            else if (yPosition.equalsIgnoreCase(Commands.COMMAND_TOP) && xPosition.equalsIgnoreCase(Commands.COMMAND_RIGHT)) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 1;
                GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "1");
                commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_OVERLAY_POSITION_TOP_RIGHT));
            }
            else if (yPosition.equalsIgnoreCase(Commands.COMMAND_BOTTOM) && xPosition.equalsIgnoreCase(Commands.COMMAND_LEFT)) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 2;
                GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "2");
                commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_OVERLAY_POSITION_BOTTOM_LEFT));
            }
            else if (yPosition.equalsIgnoreCase(Commands.COMMAND_BOTTOM) && xPosition.equalsIgnoreCase(Commands.COMMAND_RIGHT)) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = 3;
                GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, "3");
                commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_OVERLAY_POSITION_BOTTOM_RIGHT));
            }
            else
                throw new WrongUsageException(Commands.COMMAND_OVERLAY_POSITION_USAGE, new Object[0]);
        }
        else
            throw new WrongUsageException(Commands.COMMAND_OVERLAY_POSITION_USAGE, new Object[0]);
    }
}

package com.pahimar.ee3.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.IChatComponent;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.configuration.GeneralConfiguration;
import com.pahimar.ee3.lib.Commands;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CommandSounds
 *
 * @author pahimar
 */
public class CommandSounds
{

    public static void processCommand(ICommandSender commandSender, String[] args)
    {

        String subCommand;

        if (args.length > 0)
        {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals(Commands.COMMAND_ALL))
            {
                processAllCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals(Commands.COMMAND_SELF))
            {
                processSelfCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals(Commands.COMMAND_OFF))
            {
                processOffCommand(commandSender);
            }
            else
            {
                throw new WrongUsageException(Commands.COMMAND_SOUNDS_USAGE);
            }
        }
        else
        {
            throw new WrongUsageException(Commands.COMMAND_SOUNDS_USAGE);
        }
    }

    private static void processAllCommand(ICommandSender commandSender)
    {

        ConfigurationSettings.ENABLE_SOUNDS = Commands.ALL;
        GeneralConfiguration.set(GeneralConfiguration.CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, Commands.ALL);
        commandSender.addChatMessage(IChatComponent.Serializer.func_150699_a(Commands.COMMAND_SOUNDS_SET_TO_ALL));
    }

    private static void processSelfCommand(ICommandSender commandSender)
    {

        ConfigurationSettings.ENABLE_SOUNDS = Commands.SELF;
        GeneralConfiguration.set(GeneralConfiguration.CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, Commands.SELF);
        commandSender.addChatMessage(IChatComponent.Serializer.func_150699_a(Commands.COMMAND_SOUNDS_SET_TO_SELF));
    }

    private static void processOffCommand(ICommandSender commandSender)
    {

        ConfigurationSettings.ENABLE_SOUNDS = Commands.OFF;
        GeneralConfiguration.set(GeneralConfiguration.CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, Commands.OFF);
        commandSender.addChatMessage(IChatComponent.Serializer.func_150699_a(Commands.COMMAND_SOUNDS_TURNED_OFF));
    }
}

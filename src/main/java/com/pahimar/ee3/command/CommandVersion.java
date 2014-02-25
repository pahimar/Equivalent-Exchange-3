package com.pahimar.ee3.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.IChatComponent;

import com.pahimar.ee3.helper.VersionHelper;
import com.pahimar.ee3.lib.Commands;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CommandVersion
 *
 * @author pahimar
 */
public class CommandVersion
{

    public static void processCommand(ICommandSender commandSender, String[] args)
    {

        String subCommand;

        if (args.length > 0)
        {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals(Commands.COMMAND_VERSION))
            {
                processVersionCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals(Commands.COMMAND_CHANGELOG))
            {
                processChangelogCommand(commandSender);
            }
            else
            {
                throw new WrongUsageException(Commands.COMMAND_VERSION_USAGE);
            }
        }
        else
        {
            throw new WrongUsageException(Commands.COMMAND_VERSION_USAGE);
        }
    }

    private static void processVersionCommand(ICommandSender commandSender)
    {

    	commandSender.addChatMessage(IChatComponent.Serializer.func_150699_a(VersionHelper.getResultMessage()));
    }

    private static void processChangelogCommand(ICommandSender commandSender)
    {

    }
}

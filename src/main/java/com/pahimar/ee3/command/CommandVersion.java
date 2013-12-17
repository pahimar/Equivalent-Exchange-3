package com.pahimar.ee3.command;

import com.pahimar.ee3.helper.VersionHelper;
import com.pahimar.ee3.lib.Commands;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatMessageComponent;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CommandVersion
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
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
                throw new WrongUsageException(Commands.COMMAND_VERSION_USAGE, new Object[0]);
            }
        }
        else
        {
            throw new WrongUsageException(Commands.COMMAND_VERSION_USAGE, new Object[0]);
        }
    }

    private static void processVersionCommand(ICommandSender commandSender)
    {

        commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(VersionHelper.getResultMessage()));
    }

    private static void processChangelogCommand(ICommandSender commandSender)
    {

    }

}

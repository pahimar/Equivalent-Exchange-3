package com.pahimar.ee3.command;

import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.List;

public class CommandEE extends CommandBase
{
    CommandBase[] modCommands = {new CommandPlayerLearnEverything(), new CommandPlayerLearnItem(), new CommandTemplateLearnEverything(), new CommandTemplateLearnItem()};

    @Override
    public String getCommandName()
    {
        return Names.Commands.BASE_COMMAND;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return "TODO";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length >= 1)
        {
            for (CommandBase command : modCommands)
            {
                if (command.getCommandName().equalsIgnoreCase(args[0]) && command.canCommandSenderUseCommand(commandSender))
                {
                    command.processCommand(commandSender, args);
                }
            }
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, "set-energy-value", "set-item-learnable", "set-item-recoverable", "set-item-unlearnable", "set-item-unrecoverable", "sync-energy-values", "player-learn-everything", "player-learn-item", "template-learn-everything", "template-learn-item");
        }
        else if (args.length >= 2)
        {
            for (CommandBase command : modCommands)
            {
                if (command.getCommandName().equalsIgnoreCase(args[0]))
                {
                    return command.addTabCompletionOptions(commandSender, args);
                }
            }
        }

        return null;
    }
}

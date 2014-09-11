package com.pahimar.ee3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import java.util.List;

public class CommandEE extends CommandBase
{
    private static final CommandEE[] COMMANDS = {new CommandSetValue(), new CommandSyncValues()};

    @Override
    public String getCommandName()
    {
        return "ee3";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender)
    {
        return null;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length > 0)
        {
            for (CommandEE command : COMMANDS)
            {
                if (args[0].equalsIgnoreCase(command.getCommandName()))
                {
                    command.processCommand(commandSender, args);
                    return;
                }
            }

            throw new WrongUsageException("command.ee3.usage");
        }
        else
        {
            throw new WrongUsageException("command.ee3.usage");
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length > 0)
        {
            if (args.length == 1)
            {
                String[] commands = new String[COMMANDS.length];

                for (int i = 0; i < COMMANDS.length; i++)
                {
                    commands[i] = COMMANDS[i].getCommandName();
                }

                return getListOfStringsMatchingLastWord(args, commands);
            }
            else if (args.length == 2)
            {
                for (CommandEE command : COMMANDS)
                {
                    if (args[0].equalsIgnoreCase(command.getCommandName()))
                    {
                        return command.addTabCompletionOptions(commandSender, args);
                    }
                }

                return null;
            }
        }

        return null;
    }

    @Override
    public int compareTo(Object obj)
    {
        if (obj instanceof ICommand)
        {
            return this.compareTo((ICommand) obj);
        }
        else
        {
            return 0;
        }
    }
}

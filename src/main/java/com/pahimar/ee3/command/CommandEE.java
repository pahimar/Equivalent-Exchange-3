package com.pahimar.ee3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import java.util.List;

public class CommandEE extends CommandBase
{
    private static final CommandSetEnergyValue COMMAND_SET_ENERGY_VALUE = new CommandSetEnergyValue();
    private static final CommandSyncEnergyValues COMMAND_SYNC_ENERGY_VALUES = new CommandSyncEnergyValues();

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
            if (args[0].equalsIgnoreCase(COMMAND_SET_ENERGY_VALUE.getCommandName()))
            {
                COMMAND_SET_ENERGY_VALUE.processCommand(commandSender, args);
            }
            else if (args[0].equalsIgnoreCase(COMMAND_SYNC_ENERGY_VALUES.getCommandName()))
            {
                COMMAND_SYNC_ENERGY_VALUES.processCommand(commandSender, args);
            }
            else
            {
                throw new WrongUsageException("command.ee3.usage");
            }
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
                return getListOfStringsMatchingLastWord(args, COMMAND_SET_ENERGY_VALUE.getCommandName(), COMMAND_SYNC_ENERGY_VALUES.getCommandName());
            }
            else if (args.length == 2)
            {
                if (args[0].equalsIgnoreCase(COMMAND_SET_ENERGY_VALUE.getCommandName()))
                {
                    return COMMAND_SET_ENERGY_VALUE.addTabCompletionOptions(commandSender, args);
                }
                else if (args[0].equalsIgnoreCase(COMMAND_SYNC_ENERGY_VALUES.getCommandName()))
                {
                    return COMMAND_SYNC_ENERGY_VALUES.addTabCompletionOptions(commandSender, args);
                }
                else
                {
                    return null;
                }
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

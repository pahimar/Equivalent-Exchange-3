package com.pahimar.ee3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import java.util.List;

public class CommandEE extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "ee3";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender)
    {
        return null;
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] args)
    {

    }

    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        switch (args.length)
        {
            case 1:
            {
                return getListOfStringsMatchingLastWord(args, "set-energy-value", "sync-energy-values");
            }
            default:
            {
                return null;
            }
        }
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

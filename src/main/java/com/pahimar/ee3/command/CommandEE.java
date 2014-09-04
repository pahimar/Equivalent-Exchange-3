package com.pahimar.ee3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

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

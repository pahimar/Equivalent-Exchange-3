package com.pahimar.ee3.command;

import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.List;

public class CommandPlayerLearnItem extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.PLAYER_LEARN_ITEM;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return "TODO";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        // TODO
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        // TODO List currently logged in players as options
        return null;
    }
}

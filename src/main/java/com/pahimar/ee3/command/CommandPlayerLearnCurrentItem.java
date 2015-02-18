package com.pahimar.ee3.command;

import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandPlayerLearnCurrentItem extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.PLAYER_LEARN_CURRENT_ITEM;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.PLAYER_LEARN_CURRENT_ITEM_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {

    }
}

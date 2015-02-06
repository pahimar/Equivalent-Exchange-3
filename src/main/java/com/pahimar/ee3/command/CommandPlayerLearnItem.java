package com.pahimar.ee3.command;

import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;

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
        return Messages.Commands.PLAYER_LEARN_ITEM_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        // TODO
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            return getListOfStringsMatchingLastWord(args, FMLCommonHandler.instance().getMinecraftServerInstance().getAllUsernames());
        }
        else if (args.length == 3)
        {
            return getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys());
        }

        return null;
    }
}

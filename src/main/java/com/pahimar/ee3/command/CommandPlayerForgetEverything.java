package com.pahimar.ee3.command;

import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class CommandPlayerForgetEverything extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.PLAYER_FORGET_EVERYTHING;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.PLAYER_FORGET_EVERYTHING_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length >= 2)
        {
            EntityPlayer entityPlayer = getPlayer(commandSender, args[1]);

            if (entityPlayer != null)
            {
                TransmutationKnowledgeRegistry.getInstance().makePlayerForgetEverything(entityPlayer);
                func_152373_a(commandSender, this, Messages.Commands.PLAYER_FORGET_EVERYTHING_SUCCESS, new Object[]{commandSender.getCommandSenderName(), entityPlayer.getCommandSenderName()});
            }
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            return getListOfStringsMatchingLastWord(args, FMLCommonHandler.instance().getMinecraftServerInstance().getAllUsernames());
        }

        return null;
    }
}

package com.pahimar.ee3.command;

import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandTemplateLearnEverything extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.TEMPLATE_LEARN_EVERYTHING;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.TEMPLATE_LEARN_EVERYTHING_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        TransmutationKnowledgeRegistry.getInstance().teachTemplateAll();
        func_152373_a(commandSender, this, Messages.Commands.TEMPLATE_LEARN_EVERYTHING_SUCCESS, new Object[]{commandSender.getCommandSenderName()});
    }
}

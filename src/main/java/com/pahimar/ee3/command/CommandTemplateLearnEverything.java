package com.pahimar.ee3.command;

import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
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
        return "TODO";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        TransmutationKnowledgeRegistry.getInstance().teachTemplateAll();
        // TODO Notify admins of the change to the template
    }
}

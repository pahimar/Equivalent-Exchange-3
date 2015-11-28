package com.pahimar.ee3.command;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandAdmin extends CommandEE
{

    @Override
    public String getCommandName()
    {
        return Names.Commands.ADMIN_PANEL;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.ADMIN_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        EntityPlayer entityPlayer = (EntityPlayer) commandSender;

        entityPlayer.openGui(EquivalentExchange3.instance, GUIs.ADMIN_PANEL.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
    }
}

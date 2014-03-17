package com.pahimar.ee3.command;

import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketOpenGuiScreen;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.command.ICommandSender;

public class CommandEmcAssignment
{
    public static void processCommand(ICommandSender commandSender, String[] args)
    {
        PacketDispatcher.sendPacketToPlayer(PacketTypeHandler.populatePacket(new PacketOpenGuiScreen((byte)GuiIds.EMC_ASSIGNMENT)), (Player)commandSender);
    }
}

package com.pahimar.ee3.command;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommandSyncEnergyValues extends CommandBase
{
    private static Map<UUID, Long> requesterMap = new HashMap<UUID, Long>();

    @Override
    public String getCommandName()
    {
        return Names.Commands.SYNC_ENERGY_VALUES;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 4;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.SYNC_ENERGY_VALUES_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        boolean shouldSync = true;
        float coolDown = 0f;
        UUID commandSenderUUID = ((EntityPlayer) commandSender).getUniqueID();

        if (requesterMap.containsKey(commandSenderUUID))
        {
            long timeDifference = System.currentTimeMillis() - requesterMap.get(commandSenderUUID).longValue();
            if (timeDifference >= (Settings.General.syncThreshold * 1000))
            {
                requesterMap.remove(commandSenderUUID);
            }
            else
            {
                coolDown = (Settings.General.syncThreshold * 1000) - timeDifference;
                shouldSync = false;
            }
        }
        else
        {
            requesterMap.put(commandSenderUUID, System.currentTimeMillis());
        }

        if (shouldSync)
        {
            LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Syncing energy values with player '{}' at their request", commandSender.getCommandSenderName());
            PacketHandler.INSTANCE.sendTo(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()), (EntityPlayerMP) commandSender);
            commandSender.addChatMessage(new ChatComponentTranslation(Messages.Commands.SYNC_ENERGY_VALUES_SUCCESS));
        }
        else
        {
            throw new WrongUsageException(Messages.Commands.SYNC_ENERGY_VALUES_DENIED, new Object[]{coolDown / 1000f});
        }
    }
}

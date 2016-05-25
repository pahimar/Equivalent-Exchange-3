package com.pahimar.ee3.command;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.handler.ConfigurationHandler;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommandRegenEnergyValues extends CommandBase {

    private static Map<UUID, Long> requesterMap = new HashMap<>();

    @Override
    public String getCommandName() {
        return Names.Commands.REGEN_ENERGY_VALUES;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return Messages.Commands.SYNC_ENERGY_VALUES_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) {

        boolean shouldRegen = true;
        float coolDown = 0f;
        UUID commandSenderUUID = ((EntityPlayer) commandSender).getUniqueID();

        if (requesterMap.containsKey(commandSenderUUID)) {

            // TODO Switch to nanoTime from currentTimeMillis
            long timeDifference = System.currentTimeMillis() - requesterMap.get(commandSenderUUID).longValue();

            if (timeDifference >= (ConfigurationHandler.Settings.serverSyncThreshold * 1000)) {
                requesterMap.remove(commandSenderUUID);
            }
            else {
                coolDown = (ConfigurationHandler.Settings.serverSyncThreshold * 1000) - timeDifference;
                shouldRegen = false;
            }
        }
        else {
            requesterMap.put(commandSenderUUID, System.currentTimeMillis());
        }

        if (shouldRegen) {
            LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Regenerating energy values at {}'s request", commandSender.getCommandSenderName());
            EnergyValueRegistry.INSTANCE.compute();
            PacketHandler.INSTANCE.sendToAll(new MessageSyncEnergyValues());
            commandSender.addChatMessage(new ChatComponentTranslation(Messages.Commands.REGEN_ENERGY_VALUES_SUCCESS));
        }
        else {
            throw new WrongUsageException(Messages.Commands.REGEN_ENERGY_VALUES_DENIED, new Object[]{coolDown / 1000f});
        }
    }
}

package com.pahimar.ee3.command;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.handler.ConfigurationHandler;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommandSyncEnergyValues extends CommandBase {

    private static Map<UUID, Long> requesterMap = new HashMap<>();

    @Override
    public String getCommandName() {
        return Names.Commands.SYNC_ENERGY_VALUES;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return Messages.Commands.SYNC_ENERGY_VALUES_USAGE;
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender commandSender, String[] args) throws CommandException {

        boolean shouldSync = true;
        float coolDown = 0f;
        UUID commandSenderUUID = ((EntityPlayer) commandSender).getUniqueID();

        if (requesterMap.containsKey(commandSenderUUID)) {

            // TODO Switch to nanoTime from currentTimeMillis
            long timeDifference = (System.nanoTime() - requesterMap.get(commandSenderUUID).longValue()) / 100000;

            if (timeDifference >= (ConfigurationHandler.Settings.serverSyncThreshold * 1000)) {
                requesterMap.remove(commandSenderUUID);
            }
            else {
                coolDown = (ConfigurationHandler.Settings.serverSyncThreshold * 1000) - timeDifference;
                shouldSync = false;
            }
        }
        else {
            requesterMap.put(commandSenderUUID, System.nanoTime() / 100000);
        }

        if (shouldSync) {
            LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Syncing energy values with all players at {}'s request", commandSender.getName());
            Network.INSTANCE.sendToAll(new MessageSyncEnergyValues());
            commandSender.addChatMessage(new TextComponentTranslation(Messages.Commands.SYNC_ENERGY_VALUES_SUCCESS));
        }
        else {
            throw new WrongUsageException(Messages.Commands.SYNC_ENERGY_VALUES_DENIED, coolDown / 1000f);
        }
    }
}

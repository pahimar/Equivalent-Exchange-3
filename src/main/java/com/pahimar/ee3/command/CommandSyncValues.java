package com.pahimar.ee3.command;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.PlayerHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommandSyncValues extends CommandBase
{
    private static final long SYNC_THRESHOLD = 5000;
    private static Map<UUID, Long> requesterMap = new HashMap<UUID, Long>();

    @Override
    public String getCommandName()
    {
        return "ee3-sync-values";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender commandSender)
    {
        return true;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return "command.ee3.sync-values.usage";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("self"))
            {
                boolean shouldSync = true;
                float coolDown = 0f;

                if (!PlayerHelper.isPlayerOp(((EntityPlayer) commandSender)))
                {
                    UUID commandSenderUUID = ((EntityPlayer) commandSender).getUniqueID();

                    if (requesterMap.containsKey(commandSenderUUID))
                    {
                        long timeDifference = System.currentTimeMillis() - requesterMap.get(commandSenderUUID).longValue();
                        if (timeDifference >= SYNC_THRESHOLD)
                        {
                            requesterMap.remove(commandSenderUUID);
                        }
                        else
                        {
                            coolDown = SYNC_THRESHOLD - timeDifference;
                            shouldSync = false;
                        }
                    }
                    else
                    {
                        requesterMap.put(commandSenderUUID, System.currentTimeMillis());
                    }
                }

                if (shouldSync)
                {
                    LogHelper.info(String.format("Syncing EnergyValues with player '%s' at their request", commandSender.getCommandSenderName()));
                    PacketHandler.INSTANCE.sendTo(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()), (EntityPlayerMP) commandSender);
                    commandSender.addChatMessage(new ChatComponentTranslation("command.ee3.sync-values.self.success"));
                }
                else
                {
                    throw new WrongUsageException("command.ee3.sync-values.self.denied", new Object[]{coolDown / 1000f});
                }
            }
            else if (args[0].equalsIgnoreCase("all"))
            {
                if (PlayerHelper.isPlayerOp((EntityPlayer) commandSender))
                {
                    LogHelper.info(String.format("Syncing EnergyValues with all players at %s's request", commandSender.getCommandSenderName()));
                    PacketHandler.INSTANCE.sendToAll(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()));
                    func_152373_a(commandSender, this, "command.ee3.sync-values.all.success", new Object[]{commandSender.getCommandSenderName()});
                }
                else
                {
                    throw new WrongUsageException("command.ee3.sync-values.all.denied");
                }
            }
            else
            {
                throw new WrongUsageException("command.ee3.sync-values.usage");
            }
        }
        else
        {
            throw new WrongUsageException("command.ee3.sync-values.usage");
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, "self", "all");
        }

        return null;
    }
}

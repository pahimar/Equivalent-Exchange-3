package com.pahimar.ee3.command;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class CommandSetValue extends CommandEE
{
    @Override
    public String getCommandName()
    {
        return "set-value";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length < 3)
        {
            throw new WrongUsageException("command.ee3.set-value.usage");
        }
        else
        {
            Item item = getItemByText(commandSender, args[1]);
            double energyValue = 0;
            int metaData = 0;

            if (args.length >= 3)
            {
                energyValue = parseDoubleWithMin(commandSender, args[2], 0);
            }
            else if (args.length >= 4)
            {
                metaData = parseInt(commandSender, args[3]);
            }

            ItemStack itemStack = new ItemStack(item, 1, metaData);

            if (args.length >= 5)
            {
                String stringNBTData = func_147178_a(commandSender, args, 4).getUnformattedText();

                try
                {
                    NBTBase nbtBase = JsonToNBT.func_150315_a(stringNBTData);

                    if (!(nbtBase instanceof NBTTagCompound))
                    {
                        func_152373_a(commandSender, this, "command.ee3.set-value.tagError", new Object[]{"Not a valid tag"});
                        return;
                    }

                    itemStack.setTagCompound((NBTTagCompound) nbtBase);
                }
                catch (Exception exception)
                {
                    func_152373_a(commandSender, this, "command.ee3.set-value.tagError", new Object[]{exception.getMessage()});
                    return;
                }
            }

            WrappedStack wrappedStack = new WrappedStack(itemStack);
            EnergyValue newEnergyValue = new EnergyValue(energyValue);

            if (wrappedStack != null && newEnergyValue != null && Float.compare(newEnergyValue.getEnergyValue(), 0) > 0)
            {
                LogHelper.info(String.format("%s set the EnergyValue of %s to %s", commandSender.getCommandSenderName(), wrappedStack, newEnergyValue));
                EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);
                PacketHandler.INSTANCE.sendToAll(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance())); //TODO Get MessageSetEnergyValue working so we are only setting new values and not unchanged ones
                func_152373_a(commandSender, this, "command.ee3.set-value.success", new Object[]{commandSender.getCommandSenderName(), wrappedStack.toString(), newEnergyValue.toString()});
            }
            else
            {
                throw new WrongUsageException("command.ee3.set-value.usage");
            }
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        return args.length == 2 ? getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys()) : null;
    }
}

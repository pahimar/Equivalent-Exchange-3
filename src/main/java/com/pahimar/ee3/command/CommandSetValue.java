package com.pahimar.ee3.command;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSetEnergyValue;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;
import java.util.Map;

public class CommandSetValue extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "ee3-set-value";
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return "command.ee3.set-value.usage";
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
                if (args[0].equalsIgnoreCase("pre"))
                {
                    // TODO Mark that the server needs to regen values on next startup
                    Map<WrappedStack, EnergyValue> preAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.PRE_ASSIGNED_ENERGY_VALUES);
                    preAssignedValues.put(wrappedStack, newEnergyValue);
                    SerializationHelper.writeEnergyValueStackMapToJsonFile(Files.PRE_ASSIGNED_ENERGY_VALUES, preAssignedValues);
                }
                else if (args[0].equalsIgnoreCase("post"))
                {
                    EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);
                    Map<WrappedStack, EnergyValue> postAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.POST_ASSIGNED_ENERGY_VALUES);
                    postAssignedValues.put(wrappedStack, newEnergyValue);
                    SerializationHelper.writeEnergyValueStackMapToJsonFile(Files.POST_ASSIGNED_ENERGY_VALUES, postAssignedValues);
                    PacketHandler.INSTANCE.sendToAll(new MessageSetEnergyValue(wrappedStack, newEnergyValue));
                }

                // Notify admins and log the value change
                func_152373_a(commandSender, this, "command.ee3.set-value.success", new Object[]{commandSender.getCommandSenderName(), args[0], wrappedStack.toString(), newEnergyValue.toString()});
                LogHelper.info(String.format("%s set the EnergyValue of %s to %s", commandSender.getCommandSenderName(), wrappedStack, newEnergyValue));
            }
            else
            {
                throw new WrongUsageException("command.ee3.set-value.usage");
            }
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, "pre", "post");
        }
        else if (args.length == 2)
        {
            return getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys());
        }

        return null;
    }
}

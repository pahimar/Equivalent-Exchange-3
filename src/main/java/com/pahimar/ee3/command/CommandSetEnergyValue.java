package com.pahimar.ee3.command;

import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSetEnergyValue;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class CommandSetEnergyValue extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.SET_ENERGY_VALUE;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.SET_ENERGY_VALUE_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length < 4)
        {
            throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
        }
        else
        {
            Item item = getItemByText(commandSender, args[2]);
            float energyValue = 0;
            int metaData = 0;

            if (args.length >= 4)
            {
                energyValue = (float) parseDoubleWithMin(commandSender, args[3], 0);
            }
            else if (args.length >= 5)
            {
                metaData = parseInt(commandSender, args[4]);
            }

            ItemStack itemStack = new ItemStack(item, 1, metaData);

            if (args.length >= 6)
            {
                String stringNBTData = func_147178_a(commandSender, args, 5).getUnformattedText();

                try
                {
                    NBTBase nbtBase = JsonToNBT.func_150315_a(stringNBTData);

                    if (!(nbtBase instanceof NBTTagCompound))
                    {
                        func_152373_a(commandSender, this, Messages.Commands.INVALID_NBT_TAG_ERROR, new Object[]{"Not a valid tag"});
                        return;
                    }

                    itemStack.setTagCompound((NBTTagCompound) nbtBase);
                }
                catch (Exception exception)
                {
                    func_152373_a(commandSender, this, Messages.Commands.INVALID_NBT_TAG_ERROR, new Object[]{exception.getMessage()});
                    return;
                }
            }

            WrappedStack wrappedStack = WrappedStack.wrap(itemStack);
            EnergyValue newEnergyValue = new EnergyValue(energyValue);

            if (wrappedStack != null && newEnergyValue != null)
            {
                if (Float.compare(newEnergyValue.getValue(), 0) > 0) {

                    if (args[1].equalsIgnoreCase("pre")) {
                        EnergyValueRegistryProxy.setEnergyValue(wrappedStack, newEnergyValue, EnergyValueRegistryProxy.Phase.PRE_CALCULATION);
                    }
                    else if (args[1].equalsIgnoreCase("post")) {
                        EnergyValueRegistryProxy.setEnergyValue(wrappedStack, newEnergyValue);
                        PacketHandler.INSTANCE.sendToAll(new MessageSetEnergyValue(wrappedStack, newEnergyValue));
                    }
                    else {
                        throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
                    }

                    EnergyValueRegistry.INSTANCE.save();
                    // Notify admins and log the value change
                    func_152373_a(commandSender, this, Messages.Commands.SET_ENERGY_VALUE_SUCCESS, new Object[]{commandSender.getCommandSenderName(), args[1], itemStack.func_151000_E(), newEnergyValue.getChatComponent()});
                }
                else if (Float.compare(newEnergyValue.getValue(), 0) == 0) {

                    BlacklistRegistryProxy.setAsNotLearnable(wrappedStack);
                    BlacklistRegistryProxy.setAsNotExchangeable(wrappedStack);
                    // TODO Remove energy value from EnergyValueRegistry
                    // TODO Sync change with client
                    func_152373_a(commandSender, this, "%s set %s as not learnable and not exchangeable", new Object[]{commandSender.getCommandSenderName(), itemStack.func_151000_E()});
                }
            }
            else
            {
                throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
            }
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            return getListOfStringsMatchingLastWord(args, "pre", "post");
        }
        else if (args.length == 3)
        {
            return getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys());
        }

        return null;
    }
}

package com.pahimar.ee3.command;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.filesystem.FileSystem;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSetEnergyValue;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import javax.naming.OperationNotSupportedException;
import java.io.File;
import java.util.List;
import java.util.Map;

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

            try
            {
                if (wrappedStack != null && newEnergyValue != null && Float.compare(newEnergyValue.getValue(), 0) > 0)
                {
                    if (args[1].equalsIgnoreCase("pre"))
                    {
                        EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);

                        Map<WrappedStack, EnergyValue> preAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.PRE_CALCULATION_ENERGY_VALUES);
                        preAssignedValues.put(wrappedStack, newEnergyValue);
                        SerializationHelper.writeEnergyValueStackMapToJsonFile(Files.PRE_CALCULATION_ENERGY_VALUES, preAssignedValues);
                        EnergyValueRegistry.getInstance().setShouldRegenNextRestart(true);
                    }
                    else if (args[1].equalsIgnoreCase("global-pre"))
                    {
                        EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);

                        File preCalcluationEnergyValueFile = FileSystem.getGlobal().getPreCalcluationEnergyValueFile();
                        Map<WrappedStack, EnergyValue> preAssignedValues = null;
                        preAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(preCalcluationEnergyValueFile);
                        preAssignedValues.put(wrappedStack, newEnergyValue);
                        SerializationHelper.writeEnergyValueStackMapToJsonFile(preCalcluationEnergyValueFile, preAssignedValues);
                        EnergyValueRegistry.getInstance().setShouldRegenNextRestart(true);
                    }
                    else if (args[1].equalsIgnoreCase("post"))
                    {
                        EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);

                        Map<WrappedStack, EnergyValue> postAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.POST_CALCULATION_ENERGY_VALUES);
                        postAssignedValues.put(wrappedStack, newEnergyValue);
                        SerializationHelper.writeEnergyValueStackMapToJsonFile(Files.POST_CALCULATION_ENERGY_VALUES, postAssignedValues);

                        PacketHandler.INSTANCE.sendToAll(new MessageSetEnergyValue(wrappedStack, newEnergyValue));
                    }
                    else if (args[1].equalsIgnoreCase("global-post"))
                    {
                        EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);

                        File postCalcluationEnergyValueFile = FileSystem.getGlobal().getPostCalcluationEnergyValueFile();
                        Map<WrappedStack, EnergyValue> postAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(postCalcluationEnergyValueFile);
                        postAssignedValues.put(wrappedStack, newEnergyValue);
                        SerializationHelper.writeEnergyValueStackMapToJsonFile(postCalcluationEnergyValueFile, postAssignedValues);

                        PacketHandler.INSTANCE.sendToAll(new MessageSetEnergyValue(wrappedStack, newEnergyValue));
                    }
                    else
                    {
                        throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
                    }

                    // Notify admins and log the value change
                    func_152373_a(commandSender, this, Messages.Commands.SET_ENERGY_VALUE_SUCCESS, new Object[]{commandSender.getCommandSenderName(), args[1], itemStack.func_151000_E(), newEnergyValue.getChatComponent()});
                }
                else
                {
                    throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
                }
            } catch (OperationNotSupportedException e)
            {
                // TODO Inform the player that the files are not available.
                e.printStackTrace();
            }
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            return getListOfStringsMatchingLastWord(args, "pre", "global-pre", "post", "global-post");
        }
        else if (args.length == 3)
        {
            return getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys());
        }

        return null;
    }
}

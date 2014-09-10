package com.pahimar.ee3.command;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class CommandSetEnergyValue extends CommandEE
{
    @Override
    public String getCommandName()
    {
        return "set-energy-value";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        LogHelper.info(args.length);

        if (args.length < 3)
        {
            throw new WrongUsageException("command.ee3.set-energy-value.usage");
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
                        func_152373_a(commandSender, this, "command.ee3.set-energy-value.tagError", new Object[]{"Not a valid tag"});
                        return;
                    }

                    itemStack.setTagCompound((NBTTagCompound) nbtBase);
                }
                catch (Exception exception)
                {
                    func_152373_a(commandSender, this, "command.ee3.set-energy-value.tagError", new Object[]{exception.getMessage()});
                    return;
                }
            }

            EnergyValueRegistry.getInstance().setEnergyValue(new WrappedStack(itemStack), new EnergyValue(energyValue));
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        return args.length == 2 ? getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys()) : null;
    }
}

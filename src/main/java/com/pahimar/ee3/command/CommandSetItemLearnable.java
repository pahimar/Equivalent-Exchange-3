package com.pahimar.ee3.command;

import com.pahimar.ee3.api.AbilityRegistryProxy;
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

public class CommandSetItemLearnable extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.SET_ITEM_LEARNABLE;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.SET_ITEM_LEARNABLE_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length < 2)
        {
            throw new WrongUsageException(Messages.Commands.SET_ITEM_LEARNABLE_USAGE);
        }
        else
        {
            Item item = getItemByText(commandSender, args[1]);
            int metaData = 0;

            if (args.length >= 3)
            {
                metaData = parseInt(commandSender, args[2]);
            }

            ItemStack itemStack = new ItemStack(item, 1, metaData);

            if (args.length >= 4)
            {
                String stringNBTData = func_147178_a(commandSender, args, 3).getUnformattedText();

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

            AbilityRegistryProxy.setAsLearnable(itemStack);
            func_152373_a(commandSender, this, Messages.Commands.SET_ITEM_LEARNABLE_SUCCESS, new Object[]{commandSender.getCommandSenderName(), itemStack.func_151000_E()});
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            return getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys());
        }

        return null;
    }
}

package com.pahimar.ee3.command;

import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class CommandPlayerLearnItem extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.PLAYER_LEARN_ITEM;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.PLAYER_LEARN_ITEM_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length < 3)
        {
            throw new WrongUsageException(Messages.Commands.PLAYER_LEARN_ITEM_USAGE);
        }
        else
        {
            EntityPlayer entityPlayer = getPlayer(commandSender, args[1]);

            if (entityPlayer != null)
            {
                Item item = getItemByText(commandSender, args[2]);
                int metaData = 0;

                if (args.length >= 4)
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

                TransmutationKnowledgeRegistry.getInstance().teachPlayer(entityPlayer, itemStack);
                func_152373_a(commandSender, this, Messages.Commands.PLAYER_LEARN_ITEM_SUCCESS, new Object[]{commandSender.getCommandSenderName(), entityPlayer.getCommandSenderName(), itemStack.func_151000_E()});
            }
            else
            {
                throw new WrongUsageException(Messages.Commands.PLAYER_NOT_FOUND_ERROR);
            }
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            return getListOfStringsMatchingLastWord(args, FMLCommonHandler.instance().getMinecraftServerInstance().getAllUsernames());
        }
        else if (args.length == 3)
        {
            return getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys());
        }

        return null;
    }
}

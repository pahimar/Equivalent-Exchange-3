package com.pahimar.ee3.command;

import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageSetBlacklistEntry;
import com.pahimar.ee3.network.message.MessageSetEnergyValue;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

public class CommandSetEnergyValue extends CommandBase {

    @Override
    public String getCommandName() {
        return Names.Commands.SET_ENERGY_VALUE;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return Messages.Commands.SET_ENERGY_VALUE_USAGE;
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender commandSender, String[] args) throws CommandException {

        if (args.length < 4) {
            throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
        }
        else {

            Item item = getItemByText(commandSender, args[2]);
            float energyValue = args.length >= 4 ? (float) parseDouble(args[3], 0) : 0;
            int metaData = args.length >= 5 ? parseInt(args[4]) : 0;

            ItemStack itemStack = new ItemStack(item, 1, metaData);

            if (args.length >= 6) {

                String stringNBTData = getChatComponentFromNthArg(commandSender, args, 5).getUnformattedText();

                try {
                    itemStack.setTagCompound(JsonToNBT.getTagFromJson(stringNBTData));
                }
                catch (Exception exception) {
                    notifyCommandListener(commandSender, this, Messages.Commands.INVALID_NBT_TAG_ERROR, exception.getMessage());
                    return;
                }
            }

            WrappedStack wrappedStack = WrappedStack.build(itemStack);
            EnergyValue newEnergyValue = new EnergyValue(energyValue);

            if (wrappedStack != null) {

                // TODO Check to see if the request runs before telling everyone it did
                if (Float.compare(newEnergyValue.getValue(), 0) > 0) {
                    if (args[1].equalsIgnoreCase("pre")) {
                        EnergyValueRegistryProxy.setEnergyValue(wrappedStack, newEnergyValue, EnergyValueRegistryProxy.Phase.PRE_CALCULATION);
                    }
                    else if (args[1].equalsIgnoreCase("post")) {
                        EnergyValueRegistryProxy.setEnergyValue(wrappedStack, newEnergyValue);
                        Network.INSTANCE.sendToAll(new MessageSetEnergyValue(wrappedStack, newEnergyValue));
                    }
                    else {
                        throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
                    }

                    EnergyValueRegistry.INSTANCE.save();
                    notifyCommandListener(commandSender, this, Messages.Commands.SET_ENERGY_VALUE_SUCCESS, commandSender.getName(), args[1], itemStack.getTextComponent(), newEnergyValue.getTextComponent());
                }
                else if (Float.compare(newEnergyValue.getValue(), 0) == 0) {

                    BlacklistRegistryProxy.setAsNotLearnable(wrappedStack);
                    BlacklistRegistryProxy.setAsNotExchangeable(wrappedStack);
                    // TODO Remove energy value from EnergyValueRegistry
                    Network.INSTANCE.sendToAll(new MessageSetBlacklistEntry(itemStack, BlacklistRegistryProxy.Blacklist.KNOWLEDGE));
                    Network.INSTANCE.sendToAll(new MessageSetBlacklistEntry(itemStack, BlacklistRegistryProxy.Blacklist.EXCHANGE));
                    notifyCommandListener(commandSender, this, "%s set %s as not learnable and not exchangeable", commandSender.getName(), itemStack.getTextComponent());
                }
            }
            else
            {
                throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
            }
        }
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer minecraftServer, ICommandSender commandSender, String[] args, @Nullable BlockPos pos) {

        if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, "pre", "post");
        }
        else if (args.length == 3) {
            return getListOfStringsMatchingLastWord(args, Item.REGISTRY.getKeys());
        }

        return null;
    }
}

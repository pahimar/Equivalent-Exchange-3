package com.pahimar.ee3.command;

import com.pahimar.ee3.api.knowledge.PlayerKnowledgeRegistryProxy;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;
import java.util.List;

public class CommandPlayerForgetItem extends CommandBase {

    @Override
    public String getName() {
        return Names.Commands.PLAYER_FORGET_ITEM;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender commandSender) {
        return Messages.Commands.PLAYER_FORGET_ITEM_USAGE;
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender commandSender, String[] args) throws CommandException {

        if (args.length < 3) {
            throw new WrongUsageException(Messages.Commands.PLAYER_FORGET_ITEM_USAGE);
        }
        else {

            EntityPlayer entityPlayer = getPlayer(minecraftServer, commandSender, args[1]);

            Item item = getItemByText(commandSender, args[2]);
            int metaData = args.length >= 4 ? parseInt(args[3]) : 0;

            ItemStack itemStack = new ItemStack(item, 1, metaData);

            if (args.length >= 5) {
                String stringNBTData = getChatComponentFromNthArg(commandSender, args, 4).getUnformattedText();

                try {
                    itemStack.setTagCompound(JsonToNBT.getTagFromJson(stringNBTData));
                }
                catch (Exception exception) {
                    notifyCommandListener(commandSender, this, Messages.Commands.INVALID_NBT_TAG_ERROR, exception.getMessage());
                    return;
                }
            }

            // TODO Check to see if the request runs before telling everyone it did
            PlayerKnowledgeRegistryProxy.makePlayerForget(entityPlayer, itemStack);
            notifyCommandListener(commandSender, this, Messages.Commands.PLAYER_FORGET_ITEM_SUCCESS, commandSender.getName(), entityPlayer.getName(), itemStack.getTextComponent());
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer minecraftServer, ICommandSender commandSender, String[] args, @Nullable BlockPos pos) {

        if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, FMLCommonHandler.instance().getMinecraftServerInstance().getOnlinePlayerNames());
        }
        else if (args.length == 3) {
            return getListOfStringsMatchingLastWord(args, Item.REGISTRY.getKeys());
        }

        return null;
    }
}

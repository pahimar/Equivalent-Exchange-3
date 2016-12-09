package com.pahimar.ee3.command;

import com.pahimar.ee3.api.knowledge.PlayerKnowledgeRegistryProxy;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;
import java.util.List;

public class CommandPlayerForgetEverything extends CommandBase {

    @Override
    public String getName() {
        return Names.Commands.PLAYER_FORGET_EVERYTHING;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender commandSender) {
        return Messages.Commands.PLAYER_FORGET_EVERYTHING_USAGE;
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender commandSender, String[] args) throws CommandException {

        if (args.length < 2) {
            throw new WrongUsageException(Messages.Commands.PLAYER_FORGET_EVERYTHING_USAGE);
        }
        else {

            EntityPlayer entityPlayer = getPlayer(minecraftServer, commandSender, args[1]);

            // TODO Check to see if the request runs before telling everyone it did
            PlayerKnowledgeRegistryProxy.makePlayerForgetAll(entityPlayer);
            notifyCommandListener(commandSender, this, Messages.Commands.PLAYER_FORGET_EVERYTHING_SUCCESS, commandSender.getName(), entityPlayer.getName());
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer minecraftServer, ICommandSender commandSender, String[] args, @Nullable BlockPos blockPos) {

        if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, FMLCommonHandler.instance().getMinecraftServerInstance().getOnlinePlayerNames());
        }

        return null;
    }
}

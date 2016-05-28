package com.pahimar.ee3.command;

import com.google.common.base.Joiner;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import java.util.ArrayList;
import java.util.List;

public class CommandEE extends CommandBase {

    private static List<CommandBase> modCommands = new ArrayList<>();
    private static List<String> commands = new ArrayList<>();

    @Override
    public String getCommandName() {
        return Names.Commands.BASE_COMMAND;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return Messages.Commands.BASE_COMMAND_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) {

        boolean found = false;

        if (args.length >= 1) {

            for (CommandBase command : modCommands) {

                if (command.getCommandName().equalsIgnoreCase(args[0]) && command.canCommandSenderUseCommand(commandSender)) {
                    found = true;
                    command.processCommand(commandSender, args);
                }
            }
        }

        if (!found) {
            throw new WrongUsageException("Invalid command. Usage: /ee3 " + Joiner.on(" ").join(commands));
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {

        if (args.length == 1) {
            return getListOfStringsFromIterableMatchingLastWord(args, commands);
        }
        else if (args.length >= 2) {
            for (CommandBase command : modCommands) {
                if (command.getCommandName().equalsIgnoreCase(args[0])) {
                    return command.addTabCompletionOptions(commandSender, args);
                }
            }
        }

        return null;
    }

    static {
        modCommands.add(new CommandSetEnergyValue());
        modCommands.add(new CommandSetEnergyValueCurrentItem());
        modCommands.add(new CommandPlayerLearnItem());
        modCommands.add(new CommandPlayerLearnCurrentItem());
        modCommands.add(new CommandPlayerForgetEverything());
        modCommands.add(new CommandPlayerForgetItem());
        modCommands.add(new CommandPlayerForgetCurrentItem());
        modCommands.add(new CommandSetItemLearnable());
        modCommands.add(new CommandSetCurrentItemLearnable());
        modCommands.add(new CommandSetItemNotLearnable());
        modCommands.add(new CommandSetCurrentItemNotLearnable());
        modCommands.add(new CommandSetItemRecoverable());
        modCommands.add(new CommandSetCurrentItemRecoverable());
        modCommands.add(new CommandSetItemNotRecoverable());
        modCommands.add(new CommandSetCurrentItemNotRecoverable());
        modCommands.add(new CommandSyncEnergyValues());
        modCommands.add(new CommandRegenEnergyValues());
        modCommands.add(new CommandRunTest());

        for (CommandBase commandBase : modCommands) {
            commands.add(commandBase.getCommandName());
        }
    }
}

package com.pahimar.ee3.command;

import com.pahimar.ee3.lib.Commands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import java.util.List;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CommandEE
 *
 * @author pahimar
 */
public class CommandEE extends CommandBase
{

    @Override
    public String getCommandName()
    {

        return Commands.COMMAND_EE3;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender commandSender)
    {

        return true;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {

        switch (args.length)
        {
            case 1:
            {
                return getListOfStringsMatchingLastWord(args, Commands.COMMAND_OVERLAY, Commands.COMMAND_PARTICLES, Commands.COMMAND_SOUNDS, Commands.COMMAND_VERSION, Commands.COMMAND_EMC);
            }
            case 2:
            {
                if (args[0].equalsIgnoreCase(Commands.COMMAND_OVERLAY))
                {
                    return getListOfStringsMatchingLastWord(args, Commands.COMMAND_ON, Commands.COMMAND_OFF, Commands.COMMAND_POSITION, Commands.COMMAND_SCALE, Commands.COMMAND_OPACITY);
                }
                else if (args[0].equalsIgnoreCase(Commands.COMMAND_PARTICLES))
                {
                    return getListOfStringsMatchingLastWord(args, Commands.COMMAND_ON, Commands.COMMAND_OFF);
                }
                else if (args[0].equalsIgnoreCase(Commands.COMMAND_SOUNDS))
                {
                    return getListOfStringsMatchingLastWord(args, Commands.COMMAND_ALL, Commands.COMMAND_SELF, Commands.COMMAND_OFF);
                }
                else if (args[0].equalsIgnoreCase(Commands.COMMAND_VERSION))
                {
                    return getListOfStringsMatchingLastWord(args, Commands.COMMAND_CHANGELOG);
                }
            }
            case 3:
            {
                if (args[0].equalsIgnoreCase(Commands.COMMAND_OVERLAY))
                {
                    if (args[1].equalsIgnoreCase(Commands.COMMAND_POSITION))
                    {
                        return getListOfStringsMatchingLastWord(args, Commands.COMMAND_TOP, Commands.COMMAND_BOTTOM);
                    }
                }
            }
            case 4:
            {
                if (args[0].equalsIgnoreCase(Commands.COMMAND_OVERLAY))
                {
                    if (args[1].equalsIgnoreCase(Commands.COMMAND_POSITION))
                    {
                        if (args[2].equalsIgnoreCase(Commands.COMMAND_TOP) || args[2].equalsIgnoreCase(Commands.COMMAND_BOTTOM))
                        {
                            return getListOfStringsMatchingLastWord(args, Commands.COMMAND_LEFT, Commands.COMMAND_RIGHT);
                        }
                    }
                }
            }
            default:
            {
                return null;
            }
        }
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {

        if (args.length > 0)
        {
            String commandName = args[0];
            System.arraycopy(args, 1, args, 0, args.length - 1);

            if (commandName.equalsIgnoreCase(Commands.COMMAND_OVERLAY))
            {
                CommandOverlay.processCommand(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.COMMAND_PARTICLES))
            {
                CommandParticles.processCommand(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.COMMAND_SOUNDS))
            {
                CommandSounds.processCommand(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.COMMAND_VERSION))
            {
                CommandVersion.processCommand(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.COMMAND_EMC))
            {
                CommandEmcAssignment.processCommand(commandSender, args);
            }
            else
            {
                throw new WrongUsageException(Commands.COMMAND_EE3_USAGE);
            }
        }
        else
        {
            throw new WrongUsageException(Commands.COMMAND_EE3_USAGE);
        }
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int compareTo(Object obj)
    {
        if (obj instanceof ICommand)
        {
            return this.compareTo((ICommand) obj);
        }
        else
        {
            return 0;
        }
    }
}

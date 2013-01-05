package com.pahimar.ee3.command;

import static com.pahimar.ee3.core.helper.LocalizationHelper.getLocalizedString;
import static com.pahimar.ee3.lib.Strings.*;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class CommandEE extends CommandBase {

    public String getCommandName() {

        return COMMAND_EE3;
    }

    public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {

        switch (args.length) {
            case 1: {
                return getListOfStringsMatchingLastWord(args, new String[] {
                        getLocalizedString(COMMAND_OVERLAY),
                        getLocalizedString(COMMAND_PARTICLES),
                        getLocalizedString(COMMAND_SOUNDS) });
            }
            case 2: {
                if (args[0].equalsIgnoreCase(getLocalizedString(COMMAND_OVERLAY))) {
                    return getListOfStringsMatchingLastWord(args, new String[] {
                            getLocalizedString(COMMAND_ON),
                            getLocalizedString(COMMAND_OFF),
                            getLocalizedString(COMMAND_POSITION),
                            getLocalizedString(COMMAND_SCALE),
                            getLocalizedString(COMMAND_OPACITY) });
                }
                else if (args[0].equalsIgnoreCase(getLocalizedString(COMMAND_PARTICLES))) {
                    return getListOfStringsMatchingLastWord(args, new String[] {
                            getLocalizedString(COMMAND_ON),
                            getLocalizedString(COMMAND_OFF) });
                }
                else if (args[0].equalsIgnoreCase(getLocalizedString(COMMAND_SOUNDS))) {
                    return getListOfStringsMatchingLastWord(args, new String[] {
                            getLocalizedString(COMMAND_ALL),
                            getLocalizedString(COMMAND_SELF),
                            getLocalizedString(COMMAND_OFF) });
                }
            }
            case 3: {
                if (args[0].equalsIgnoreCase(getLocalizedString(COMMAND_OVERLAY))) {
                    if (args[1].equalsIgnoreCase(getLocalizedString(COMMAND_POSITION))) {
                        return getListOfStringsMatchingLastWord(args, new String[] {
                                getLocalizedString(COMMAND_TOP),
                                getLocalizedString(COMMAND_BOTTOM) });
                    }
                }
            }
            case 4: {
                if (args[0].equalsIgnoreCase(getLocalizedString(COMMAND_OVERLAY))) {
                    if (args[1].equalsIgnoreCase(getLocalizedString(COMMAND_POSITION))) {
                        if (args[2].equalsIgnoreCase(getLocalizedString(COMMAND_TOP)) || args[2].equalsIgnoreCase(getLocalizedString(COMMAND_BOTTOM))) {
                            return getListOfStringsMatchingLastWord(args, new String[] {
                                    getLocalizedString(COMMAND_LEFT),
                                    getLocalizedString(COMMAND_RIGHT) });
                        }
                    }
                }
            }
            default: {
                return null;
            }
        }
    }

    public void processCommand(ICommandSender commandSender, String[] args) {

        if (args.length > 0) {
            String commandName = args[0];
            System.arraycopy(args, 1, args, 0, args.length - 1);

            if (commandName.equalsIgnoreCase(getLocalizedString(COMMAND_OVERLAY))) {
                CommandOverlay.processCommand(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(getLocalizedString(COMMAND_PARTICLES))) {
                CommandParticles.processCommand(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(getLocalizedString(COMMAND_SOUNDS))) {
                CommandSounds.processCommand(commandSender, args);
            }
        }
        else {
            throw new WrongUsageException(getLocalizedString(COMMAND_EE3_USAGE), new Object[0]);
        }
    }
}

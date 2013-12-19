package com.pahimar.ee3.command;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.configuration.GeneralConfiguration;
import com.pahimar.ee3.lib.Commands;
import com.pahimar.ee3.lib.Strings;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatMessageComponent;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CommandParticles
 *
 * @author pahimar
 */
public class CommandParticles
{

    public static void processCommand(ICommandSender commandSender, String[] args)
    {

        String subCommand;

        if (args.length > 0)
        {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals(Commands.COMMAND_ON))
            {
                processOnCommand(commandSender);
            }
            else if (subCommand.toLowerCase().equals(Commands.COMMAND_OFF))
            {
                processOffCommand(commandSender);
            }
            else
            {
                throw new WrongUsageException(Commands.COMMAND_PARTICLES_USAGE, new Object[0]);
            }
        }
        else
        {
            throw new WrongUsageException(Commands.COMMAND_PARTICLES_USAGE, new Object[0]);
        }
    }

    private static void processOnCommand(ICommandSender commandSender)
    {

        ConfigurationSettings.ENABLE_PARTICLE_FX = true;
        GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_PARTICLE_FX_CONFIGNAME, Strings.TRUE);
        commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_PARTICLES_TURNED_ON));
    }

    private static void processOffCommand(ICommandSender commandSender)
    {

        ConfigurationSettings.ENABLE_PARTICLE_FX = false;
        GeneralConfiguration.set(GeneralConfiguration.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_PARTICLE_FX_CONFIGNAME, Strings.FALSE);
        commandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(Commands.COMMAND_PARTICLES_TURNED_OFF));
    }
}

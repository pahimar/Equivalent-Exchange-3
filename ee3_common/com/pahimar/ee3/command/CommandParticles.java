package com.pahimar.ee3.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.configuration.ConfigurationSettings;

public class CommandParticles {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        String subCommand;

        if (args.length > 0) {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals("on")) {
                ConfigurationSettings.ENABLE_PARTICLE_FX = true;
                ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_PARTICLE_FX_CONFIGNAME, "true");
                commandSender.sendChatToPlayer("commands.ee3.particles.turned_on");
            }
            else if (subCommand.toLowerCase().equals("off")) {
                ConfigurationSettings.ENABLE_PARTICLE_FX = false;
                ConfigurationHandler.set(ConfigurationHandler.CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_PARTICLE_FX_CONFIGNAME, "false");
                commandSender.sendChatToPlayer("commands.ee3.particles.turned_off");
            }
            else {
                throw new WrongUsageException("commands.ee3.particles.usage", new Object[0]);
            }
        }
        else {
            throw new WrongUsageException("commands.ee3.particles.usage", new Object[0]);
        }
    }
}

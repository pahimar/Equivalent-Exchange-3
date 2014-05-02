package com.pahimar.ee3.configuration;

import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.util.LogHelper;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ClientConfiguration
{
    public static final String CATEGORY_KEYBIND = "keybindings";
    private static Configuration configuration;

    protected static void init(File configFile)
    {
        configuration = new Configuration(configFile);

        try
        {
            configuration.load();

            /* KeyBindings */
            configuration.addCustomCategoryComment(CATEGORY_KEYBIND, "Keybindings for Equivalent Exchange 3. See http://www.minecraftwiki.net/wiki/Key_codes for mapping of key codes to keyboard keys");
        }
        catch (Exception e)
        {
            LogHelper.error(Reference.MOD_NAME + " has had a problem loading its general configuration");
        }
        finally
        {
            configuration.save();
        }
    }
}

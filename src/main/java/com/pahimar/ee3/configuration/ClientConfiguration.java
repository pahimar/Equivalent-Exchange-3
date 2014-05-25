package com.pahimar.ee3.configuration;

import com.pahimar.ee3.client.settings.Keybindings;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.config.Configuration;
import org.lwjgl.input.Keyboard;

import java.io.File;

public class ClientConfiguration
{
    private static final String CATEGORY_KEYBIND = "keybindings";
    private static Configuration configuration;

    protected static void init(File configFile)
    {
        configuration = new Configuration(configFile);

        try
        {
            configuration.load();

            /* KeyBindings */
            configuration.addCustomCategoryComment(CATEGORY_KEYBIND, "Keybindings for Equivalent Exchange 3. See http://www.minecraftwiki.net/wiki/Key_codes for mapping of key codes to keyboard keys");

            Keybindings.charge = new KeyBinding(Names.Keys.CHARGE, configuration.get(CATEGORY_KEYBIND, Names.Keys.CHARGE, Keyboard.KEY_V).getInt(Keyboard.KEY_V), Names.Keys.CATEGORY);
            Keybindings.extra = new KeyBinding(Names.Keys.EXTRA, configuration.get(CATEGORY_KEYBIND, Names.Keys.EXTRA, Keyboard.KEY_C).getInt(Keyboard.KEY_C), Names.Keys.CATEGORY);
            Keybindings.release = new KeyBinding(Names.Keys.RELEASE, configuration.get(CATEGORY_KEYBIND, Names.Keys.RELEASE, Keyboard.KEY_R).getInt(Keyboard.KEY_R), Names.Keys.CATEGORY);
            Keybindings.toggle = new KeyBinding(Names.Keys.TOGGLE, configuration.get(CATEGORY_KEYBIND, Names.Keys.TOGGLE, Keyboard.KEY_G).getInt(Keyboard.KEY_G), Names.Keys.CATEGORY);
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

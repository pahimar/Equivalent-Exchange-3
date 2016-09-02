package com.pahimar.ee3.client.settings;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keybindings {

    private static final String CATEGORY = "key.categories.ee3";

    public static final KeyBinding CHARGE = new KeyBinding("key.charge", Keyboard.KEY_V, CATEGORY);
    public static final KeyBinding EXTRA = new KeyBinding("key.extra", Keyboard.KEY_C, CATEGORY);
    public static final KeyBinding RELEASE = new KeyBinding("key.release", Keyboard.KEY_R, CATEGORY);
    public static final KeyBinding TOGGLE = new KeyBinding("key.toggle", Keyboard.KEY_G, CATEGORY);
}

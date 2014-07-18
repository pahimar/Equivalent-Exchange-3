package com.pahimar.ee3.client.settings;

import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keybindings
{
    public static KeyBinding charge = new KeyBinding(Names.Keys.CHARGE, Keyboard.KEY_V, Names.Keys.CATEGORY);
    public static KeyBinding extra = new KeyBinding(Names.Keys.EXTRA, Keyboard.KEY_C, Names.Keys.CATEGORY);
    public static KeyBinding release = new KeyBinding(Names.Keys.RELEASE, Keyboard.KEY_R, Names.Keys.CATEGORY);
    public static KeyBinding toggle = new KeyBinding(Names.Keys.TOGGLE, Keyboard.KEY_G, Names.Keys.CATEGORY);
}

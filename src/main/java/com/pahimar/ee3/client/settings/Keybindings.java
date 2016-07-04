package com.pahimar.ee3.client.settings;

import com.pahimar.ee3.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keybindings {

    public static final KeyBinding CHARGE = new KeyBinding(Names.Keys.CHARGE, Keyboard.KEY_V, Names.Keys.CATEGORY);
    public static final KeyBinding EXTRA = new KeyBinding(Names.Keys.EXTRA, Keyboard.KEY_C, Names.Keys.CATEGORY);
    public static final KeyBinding RELEASE = new KeyBinding(Names.Keys.RELEASE, Keyboard.KEY_R, Names.Keys.CATEGORY);
    public static final KeyBinding TOGGLE = new KeyBinding(Names.Keys.TOGGLE, Keyboard.KEY_G, Names.Keys.CATEGORY);
}

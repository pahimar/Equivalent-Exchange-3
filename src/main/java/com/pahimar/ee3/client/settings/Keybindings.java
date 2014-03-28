package com.pahimar.ee3.client.settings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(Side.CLIENT)
public class Keybindings
{
    public KeyBinding keyBindCharge = new KeyBinding("key.charge", 47, "key.categories.ee3");
    public KeyBinding keyBindExtra = new KeyBinding("key.extra", 46, "key.categories.ee3");
    public KeyBinding keyBindRelease = new KeyBinding("key.release", 19, "key.categories.ee3");
    public KeyBinding keyBindToggle = new KeyBinding("key.toggle", 34, "key.categories.ee3");
}

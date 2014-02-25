package com.pahimar.ee3.client.helper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;

import java.util.ArrayList;

/**
 * Equivalent-Exchange-3
 * <p/>
 * KeyBindingHelper
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class KeyBindingHelper
{
    private static ArrayList<KeyBinding> keyBindingsList;
    private static ArrayList<Boolean> isRepeatingList;

    public static void addKeyBinding(String name, int value, String category)
    {
        if (keyBindingsList == null)
        {
            keyBindingsList = new ArrayList<KeyBinding>();
        }

        keyBindingsList.add(new KeyBinding(name, value, category));
    }

    public static void addIsRepeating(boolean value)
    {
        if (isRepeatingList == null)
        {
            isRepeatingList = new ArrayList<Boolean>();
        }

        isRepeatingList.add(value);
    }

    public static KeyBinding[] gatherKeyBindings()
    {
        return keyBindingsList.toArray(new KeyBinding[keyBindingsList.size()]);
    }

    public static boolean[] gatherIsRepeating()
    {
        boolean[] isRepeating = new boolean[isRepeatingList.size()];

        for (int x = 0; x < isRepeating.length; x++)
        {
            isRepeating[x] = isRepeatingList.get(x);
        }

        return isRepeating;
    }
}

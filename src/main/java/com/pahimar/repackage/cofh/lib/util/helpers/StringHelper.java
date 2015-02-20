package com.pahimar.repackage.cofh.lib.util.helpers;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Contains various helper functions to assist with String manipulation.
 *
 * @author King Lemming
 */
public final class StringHelper
{

    private StringHelper()
    {

    }

    /* KEY HELPERS */
    public static boolean isAltKeyDown()
    {

        return Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU);
    }

    public static boolean isControlKeyDown()
    {

        return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
    }

    public static boolean isShiftKeyDown()
    {

        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    /* FORMAT HELPERS */
    public static int getSplitStringHeight(FontRenderer fontRenderer, String input, int width)
    {

        @SuppressWarnings("rawtypes") List stringRows = fontRenderer.listFormattedStringToWidth(input, width);
        return stringRows.size() * fontRenderer.FONT_HEIGHT;
    }

    public static String camelCase(String input)
    {

        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }

    public static String titleCase(String input)
    {

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String localize(String key)
    {

        return StatCollector.translateToLocal(key);
    }

    public static String getFluidName(FluidStack stack)
    {

        Fluid fluid = stack.getFluid();

        String name = "" + END;
        if (fluid.getRarity() == EnumRarity.uncommon)
        {
            name += YELLOW;
        }
        else if (fluid.getRarity() == EnumRarity.rare)
        {
            name += BRIGHT_BLUE;
        }
        else if (fluid.getRarity() == EnumRarity.epic)
        {
            name += PINK;
        }
        name += fluid.getLocalizedName(stack) + END;

        return name;
    }

    public static String getFluidName(FluidStack stack, String defaultName)
    {

        if (stack == null)
        {
            return defaultName;
        }
        return getFluidName(stack);
    }

    public static String getItemName(ItemStack stack)
    {

        String name = "" + END;
        if (stack.getRarity() == EnumRarity.uncommon)
        {
            name += YELLOW;
        }
        else if (stack.getRarity() == EnumRarity.rare)
        {
            name += BRIGHT_BLUE;
        }
        else if (stack.getRarity() == EnumRarity.epic)
        {
            name += PINK;
        }
        name += stack.getDisplayName() + END;

        return name;
    }

    public static String getScaledNumber(long number)
    {

        if (number >= 1000000000)
        {
            return number / 1000000000 + "." + (number % 1000000000 / 10000000) + "G";
        }
        else if (number >= 1000000)
        {
            return number / 1000000 + "." + (number % 1000000 / 10000) + "M";
        }
        else if (number >= 1000)
        {
            return number / 1000 + "." + (number % 1000 / 10) + "k";
        }
        else
        {
            return String.valueOf(number);
        }
    }

    @Deprecated
    public static String getScaledNumber(long number, int minDigits)
    {

        return getScaledNumber(number);
    }

    /* ITEM TEXT HELPERS */
    public static String getActivationText(String key)
    {

        return BRIGHT_BLUE + localize(key) + END;
    }

    public static String getDeactivationText(String key)
    {

        return YELLOW + localize(key) + END;
    }

    public static String getInfoText(String key)
    {

        return BRIGHT_GREEN + localize(key) + END;
    }

    public static String getFlavorText(String key)
    {

        return LIGHT_GRAY + ITALIC + localize(key) + END;
    }

    public static String getRarity(int level)
    {

        switch (level)
        {
            case 2:
                return StringHelper.YELLOW;
            case 3:
                return StringHelper.BRIGHT_BLUE;
            default:
                return StringHelper.LIGHT_GRAY;
        }
    }

    public static String shiftForDetails()
    {

        return LIGHT_GRAY + localize("info.cofh.hold") + " " + YELLOW + ITALIC + localize("info.cofh.shift") + " " + END + LIGHT_GRAY + localize("info.cofh.forDetails") + END;
    }

    /* TUTORIAL TAB HELPERS */
    public static String tutorialTabAugment()
    {

        return localize("info.cofh.tutorial.tabAugment");
    }

    public static String tutorialTabConfiguration()
    {

        return localize("info.cofh.tutorial.tabConfiguration.0");
    }

    public static String tutorialTabOperation()
    {

        return localize("info.cofh.tutorial.tabConfiguration.1");
    }

    public static String tutorialTabRedstone()
    {

        return localize("info.cofh.tutorial.tabRedstone");
    }

    public static String tutorialTabSecurity()
    {

        return localize("info.cofh.tutorial.tabSecurity");
    }

    public static String tutorialTabFluxRequired()
    {

        return localize("info.cofh.tutorial.fluxRequired");
    }

    /**
     * When formatting a string, always apply color before font modification.
     */
    public static final String BLACK = (char) 167 + "0";
    public static final String BLUE = (char) 167 + "1";
    public static final String GREEN = (char) 167 + "2";
    public static final String TEAL = (char) 167 + "3";
    public static final String RED = (char) 167 + "4";
    public static final String PURPLE = (char) 167 + "5";
    public static final String ORANGE = (char) 167 + "6";
    public static final String LIGHT_GRAY = (char) 167 + "7";
    public static final String GRAY = (char) 167 + "8";
    public static final String LIGHT_BLUE = (char) 167 + "9";
    public static final String BRIGHT_GREEN = (char) 167 + "a";
    public static final String BRIGHT_BLUE = (char) 167 + "b";
    public static final String LIGHT_RED = (char) 167 + "c";
    public static final String PINK = (char) 167 + "d";
    public static final String YELLOW = (char) 167 + "e";
    public static final String WHITE = (char) 167 + "f";

    public static final String OBFUSCATED = (char) 167 + "k";
    public static final String BOLD = (char) 167 + "l";
    public static final String STRIKETHROUGH = (char) 167 + "m";
    public static final String UNDERLINE = (char) 167 + "n";
    public static final String ITALIC = (char) 167 + "o";
    public static final String END = (char) 167 + "r";

    public static final String[] ROMAN_NUMERAL = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static boolean displayShiftForDetail = true;
    public static boolean displayStackCount = false;

}

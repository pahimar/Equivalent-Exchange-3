package com.pahimar.ee3.util;

import net.minecraft.item.EnumDyeColor;

import java.awt.*;

public class ColorUtils {

    public static float[] getRGB(EnumDyeColor dyeColor) {
        return getRGB(dyeColor.getMapColor().colorValue);
    }

    public static float[] getRGB(int intColor) {
        return new Color(intColor).getRGBColorComponents(null);
    }

    public static int blend(int color1, int color2) {
        return blend(color1, 1, color2, 1);

    }

    public static int blend(int color1, int weight1, int color2, int weight2) {
        return blend(new Color(color1), weight1, new Color(color2), weight2).getRGB();
    }

    private static Color blend (Color color1, int weight1, Color color2, int weight2) {

        if (color1 == null) {
            color1 = Color.WHITE;
        }

        if (color2 == null) {
            color2 = Color.WHITE;
        }

        if (weight1 <= 0) {
            weight1 = 1;
        }

        if (weight2 <= 0) {
            weight2 = 1;
        }

        double totalWeight = weight1 + weight2;
        double adjWeight1 = weight1 / totalWeight;
        double adjWeight2 = weight2 / totalWeight;

        double r = adjWeight1 * color1.getRed() + adjWeight2 * color2.getRed();
        double g = adjWeight1 * color1.getGreen() + adjWeight2 * color2.getGreen();
        double b = adjWeight1 * color1.getBlue() + adjWeight2 * color2.getBlue();

        return new Color((int) r, (int) g, (int) b, 0);
    }
}
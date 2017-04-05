package com.pahimar.ee.client.util;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class RenderUtils {

    public static void bindTexture(ResourceLocation texture) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }

    public static int getCenteredTextOffset(FontRenderer fontRenderer, String string, int width) {
        return (width - fontRenderer.getStringWidth(string)) / 2;
    }

    private static double getPulseValue() {
        return (Math.sin(System.nanoTime() / 100f) + 1) / 2;
    }
}

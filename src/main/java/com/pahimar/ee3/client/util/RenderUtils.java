package com.pahimar.ee3.client.util;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderUtils
{
    private static int pulse = 0;
    private static boolean doInc = true;

    public static void bindTexture(ResourceLocation texture) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }

    public static int getCenteredTextOffset(FontRenderer fontRenderer, String string, int width) {

        return (width - fontRenderer.getStringWidth(string)) / 2;
    }

    public static void renderItemIntoGUI(FontRenderer fontRenderer, ItemStack itemStack, int x, int y, float opacity, float scale, int zLevel)
    {
        IIcon icon = itemStack.getIconIndex();
        GL11.glDisable(GL11.GL_LIGHTING);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationItemsTexture);
        int overlayColour = itemStack.getItem().getColorFromItemStack(itemStack, 0);
        float red = (overlayColour >> 16 & 255) / 255.0F;
        float green = (overlayColour >> 8 & 255) / 255.0F;
        float blue = (overlayColour & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, opacity);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + 16 * scale, zLevel, icon.getMinU(), icon.getMaxV());
        tessellator.addVertexWithUV(x + 16 * scale, y + 16 * scale, zLevel, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(x + 16 * scale, y, zLevel, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(x, y, zLevel, icon.getMinU(), icon.getMinV());
        tessellator.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public static void renderQuad(ResourceLocation texture)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1, 1, 1, 1);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-0.5D, 0.5D, 0F, 0, 1);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0F, 1, 1);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0F, 1, 0);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0F, 0, 0);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    public static void renderPulsingQuad(ResourceLocation texture, float maxTransparency)
    {
        float pulseTransparency = getPulseValue() * maxTransparency / 3000f;
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1, 1, 1, pulseTransparency);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(1, 1, 1, pulseTransparency);
        tessellator.addVertexWithUV(-0.5D, 0.5D, 0F, 0, 1);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0F, 1, 1);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0F, 1, 0);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0F, 0, 0);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    private static int getPulseValue()
    {
        if (doInc)
        {
            pulse += 50;
        }
        else
        {
            pulse -= 50;
        }
        if (pulse == 3000)
        {
            doInc = false;
        }
        if (pulse == 0)
        {
            doInc = true;
        }
        return pulse;
    }
}

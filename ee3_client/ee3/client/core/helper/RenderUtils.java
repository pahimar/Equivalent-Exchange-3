package ee3.client.core.helper;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.FontRenderer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.Tessellator;

public class RenderUtils {

    public static void renderItemIntoGUI(FontRenderer fontRenderer, RenderEngine renderEngine, ItemStack stack, int x, int y) {

        int itemID = stack.itemID;
        int meta = stack.getItemDamage();
        int iconIndex = stack.getIconIndex();

        GL11.glDisable(GL11.GL_LIGHTING);

        renderEngine.bindTexture(renderEngine.getTexture(stack.getItem().getTextureFile()));

        int overlayColour = Item.itemsList[itemID].getColorFromItemStack(stack, 0);
        float var17 = (float) (overlayColour >> 16 & 255) / 255.0F;
        float var16 = (float) (overlayColour >> 8 & 255) / 255.0F;
        float var12 = (float) (overlayColour & 255) / 255.0F;

        GL11.glColor4f(var17, var16, var12, 1.0F);

        drawTexturedQuad(x, y, iconIndex % 16 * 16*4, iconIndex / 16 * 16*4, 16*4, 16*4, -90);
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public static void drawTexturedQuad(int x, int y, int u, int v, int width, int height, double zLevel) {

        float var7 = 0.00390625F/4;
        float var8 = 0.00390625F/4;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV(x + 0, y + height, zLevel, (u + 0) * var7, (v + height) * var8);
        var9.addVertexWithUV(x + width, y + height, zLevel, (u + width) * var7, (v + height) * var8);
        var9.addVertexWithUV(x + width, y + 0, zLevel, (u + width) * var7, (v + 0) * var8);
        var9.addVertexWithUV(x + 0, y + 0, zLevel, (u + 0) * var7, (v + 0) * var8);
        var9.draw();
    }

}

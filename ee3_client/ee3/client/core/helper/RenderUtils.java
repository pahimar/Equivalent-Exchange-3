package ee3.client.core.helper;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Block;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.Tessellator;

public class RenderUtils {

    private static int rotationAngle = 0;
    
    public static void renderRotatingBlockIntoGUI(FontRenderer fontRenderer, RenderEngine renderEngine, ItemStack stack, int x, int y, float zLevel, float scale) {
        
        RenderBlocks renderBlocks = new RenderBlocks();
        
        Block block = Block.blocksList[stack.itemID];
        renderEngine.bindTexture(renderEngine.getTexture(block.getTextureFile()));
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(x - 2), (float)(y + 3), -3.0F + zLevel);
        GL11.glScalef(10.0F, 10.0F, 10.0F);
        GL11.glTranslatef(1.0F, 0.5F, 1.0F);
        GL11.glScalef(1.0F * scale, 1.0F * scale, -1.0F);
        GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(0F + 1 * rotationAngle, 0.0F, 1.0F, 0.0F);
        rotationAngle = (rotationAngle + 1) % 360;
        
        int var10 = Item.itemsList[stack.itemID].getColorFromItemStack(stack, 0);
        float var16 = (float)(var10 >> 16 & 255) / 255.0F;
        float var12 = (float)(var10 >> 8 & 255) / 255.0F;
        float var13 = (float)(var10 & 255) / 255.0F;

        GL11.glColor4f(var16, var12, var13, 1.0F);

        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        renderBlocks.useInventoryTint = true;
        renderBlocks.renderBlockAsItem(block, stack.getItemDamage(), 1.0F);
        renderBlocks.useInventoryTint = true;
        GL11.glPopMatrix();
    }
    
    public static void renderItemIntoGUI(FontRenderer fontRenderer, RenderEngine renderEngine, ItemStack stack, int x, int y, float opacity, float scale) {

        int itemID = stack.itemID;
        int meta = stack.getItemDamage();
        int iconIndex = stack.getIconIndex();

        GL11.glDisable(GL11.GL_LIGHTING);

        renderEngine.bindTexture(renderEngine.getTexture(stack.getItem().getTextureFile()));

        int overlayColour = Item.itemsList[itemID].getColorFromItemStack(stack, 0);
        float var17 = (float) (overlayColour >> 16 & 255) / 255.0F;
        float var16 = (float) (overlayColour >> 8 & 255) / 255.0F;
        float var12 = (float) (overlayColour & 255) / 255.0F;

        GL11.glColor4f(var17, var16, var12, opacity);

        drawTexturedQuad(x, y, iconIndex % 16 * 16, iconIndex / 16 * 16, 16, 16, -90, scale);
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public static void drawTexturedQuad(int x, int y, int u, int v, int width, int height, double zLevel, float scale) {

        u = (int) (u * scale);
        v = (int) (v * scale);
        width = (int) (width * scale);
        height = (int) (height * scale);
        float var7 = 0.00390625F/scale;
        float var8 = 0.00390625F/scale;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV(x + 0, y + height, zLevel, (u + 0) * var7, (v + height) * var8);
        var9.addVertexWithUV(x + width, y + height, zLevel, (u + width) * var7, (v + height) * var8);
        var9.addVertexWithUV(x + width, y + 0, zLevel, (u + width) * var7, (v + 0) * var8);
        var9.addVertexWithUV(x + 0, y + 0, zLevel, (u + 0) * var7, (v + 0) * var8);
        var9.draw();
    }

}

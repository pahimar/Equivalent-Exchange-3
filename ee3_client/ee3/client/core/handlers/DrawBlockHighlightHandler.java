package ee3.client.core.handlers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import ee3.common.item.ItemPhilosopherStone;
import ee3.common.lib.Reference;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.Tessellator;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;

public class DrawBlockHighlightHandler {

    @ForgeSubscribe
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {

        if (event.currentItem != null) {
            if (event.currentItem.getItem() instanceof ItemPhilosopherStone) {
                if (event.target.typeOfHit == EnumMovingObjectType.TILE) {
                    drawPhilosopherStoneOverlay(event);
                }
            }
        }
    }

    public void drawPhilosopherStoneOverlay(DrawBlockHighlightEvent event) {

        double x = event.target.blockX + 0.5F;
        double y = event.target.blockY + 0.5F;
        double z = event.target.blockZ + 0.5F;
        double iPX = event.player.prevPosX + (event.player.posX - event.player.prevPosX) * event.partialTicks;
        double iPY = event.player.prevPosY + (event.player.posY - event.player.prevPosY) * event.partialTicks;
        double iPZ = event.player.prevPosZ + (event.player.posZ - event.player.prevPosZ) * event.partialTicks;
        int texture = event.context.renderEngine.getTexture(Reference.SPRITE_SHEET_LOCATION + "noise.png");

        int xScale = 1;
        int yScale = 1;
        int zScale = 1;
        int chargeLevel = ((ItemPhilosopherStone)event.currentItem.getItem()).getCharge(event.currentItem);
        
        if (chargeLevel < 1) {
            chargeLevel = 1;
        }

        if ((event.target.sideHit == 0) || (event.target.sideHit == 1)) {
            xScale = chargeLevel;
            zScale = chargeLevel;
        }
        else if ((event.target.sideHit == 2) || (event.target.sideHit == 3)) {
            xScale = chargeLevel;
            yScale = chargeLevel;
        }
        else if ((event.target.sideHit == 4) || (event.target.sideHit == 5)) {
            yScale = chargeLevel;
            zScale = chargeLevel;
        }

        float xShift = 0.1F;
        float yShift = 0.1F;
        float zShift = 0.1F;

        if (event.target.sideHit == 0) {
            xShift = 0;
            yShift = -yShift;
            zShift = 0;
        }
        else if (event.target.sideHit == 1) {
            xShift = 0;
            zShift = 0;
        }
        else if (event.target.sideHit == 2) {
            xShift = 0;
            yShift = 0;
            if (chargeLevel > 1) {
                zShift = -zShift - 1;
            }
            else {
                zShift = -zShift;
            }
        }
        else if (event.target.sideHit == 3) {
            xShift = 0;
            yShift = 0;
        }
        else if (event.target.sideHit == 4) {
            xShift = -xShift;
            yShift = 0;
            zShift = 0;
        }
        else if (event.target.sideHit == 5) {
            yShift = 0;
            zShift = 0;
        }

        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_CULL_FACE);

        for (int i = 0; i < 6; i++) {
            ForgeDirection forgeDir = ForgeDirection.getOrientation(i);
            GL11.glPushMatrix();
            GL11.glTranslated(-iPX + x + xShift, -iPY + y + yShift, -iPZ + z + zShift);
            GL11.glScalef(1F * xScale, 1F * yScale, 1F * zScale);
            GL11.glRotatef(90, forgeDir.offsetX, forgeDir.offsetY, forgeDir.offsetZ);
            GL11.glTranslated(0, 0, 0.5f);
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
            renderSlidingQuad(texture, 0.75F);
            GL11.glPopMatrix();
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(true);
    }

    public static void renderSlidingQuad(int texture, float transparency) {

        float slide = (System.currentTimeMillis() % 3000) / 3000f;
        double pulse = (double) 1 + slide;

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        Tessellator tessellator = Tessellator.instance;

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1, 1, 1, transparency);

        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(1, 1, 1, transparency);

        tessellator.addVertexWithUV(-0.5D, 0.5D, 0F, 0, pulse);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0F, 0, pulse);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0F, 1, pulse);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0F, 1, pulse);

        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

}

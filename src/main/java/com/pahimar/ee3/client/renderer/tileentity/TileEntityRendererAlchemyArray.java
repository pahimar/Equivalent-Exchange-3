package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.api.Glyph;
import com.pahimar.ee3.client.util.RenderUtils;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityRendererAlchemyArray extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity instanceof TileEntityAlchemyArray)
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);

            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glPushMatrix();

            for (Glyph glyph : tileEntityAlchemyArray.getAlchemyArray().getGlyphs())
            {
                GL11.glDepthMask(false);
                GL11.glPushMatrix();
                GL11.glTranslated(x + 0.5d, y + 0.5d, z + 0.5d);
                GL11.glScalef(1f, 1f, 1f);
                // TODO: Finish making this work in this much more intelligent way than the way we did in renderSymbol
//            GL11.glRotatef(rotationAngle, sideHit.offsetX, sideHit.offsetY, sideHit.offsetZ);
//            GL11.glRotatef(facingCorrectionAngle, sideHit.offsetX, sideHit.offsetY, sideHit.offsetZ);
//            GL11.glRotatef(90, xRotate, yRotate, zRotate);
//            GL11.glTranslated(0, 0, 0.5f * zCorrection);
                GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
                RenderUtils.renderQuad(glyph.getTexture());
                GL11.glPopMatrix();
                GL11.glDepthMask(true);
            }

            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
    }

    private void renderSymbol(Glyph glyph, double x, double y, double z, ForgeDirection orientation)
    {
        // TODO handle facing variants of glyphs
        Tessellator tessellator = Tessellator.instance;

        GL11.glPushMatrix();

        tessellator.startDrawingQuads();

        if (orientation == ForgeDirection.DOWN)
        {
            GL11.glTranslatef(0.5f - (glyph.getSize() / 2f), 0f, 0.5f - (glyph.getSize() / 2f));
            tessellator.addVertexWithUV(x + glyph.getSize(), y + 0.999d, z + glyph.getSize(), 0, 0);
            tessellator.addVertexWithUV(x + glyph.getSize(), y + 0.999d, z, 0, 1);
            tessellator.addVertexWithUV(x, y + 0.999d, z, 1, 1);
            tessellator.addVertexWithUV(x, y + 0.999d, z + glyph.getSize(), 1, 0);
        }
        else if (orientation == ForgeDirection.UP)
        {
            GL11.glTranslatef(0.5f - (glyph.getSize() / 2f), 0f, 0.5f - (glyph.getSize() / 2f));
            tessellator.addVertexWithUV(x + glyph.getSize(), y + 0.001d, z + glyph.getSize(), 0, 0);
            tessellator.addVertexWithUV(x + glyph.getSize(), y + 0.001d, z, 0, 1);
            tessellator.addVertexWithUV(x, y + 0.001d, z, 1, 1);
            tessellator.addVertexWithUV(x, y + 0.001d, z + glyph.getSize(), 1, 0);
        }
        else if (orientation == ForgeDirection.NORTH)
        {
            GL11.glTranslatef(0.5f - (glyph.getSize() / 2f), 0.5f - (glyph.getSize() / 2f), 0f);
            tessellator.addVertexWithUV(x, y + glyph.getSize(), z + 0.999d, 1, 0);
            tessellator.addVertexWithUV(x + glyph.getSize(), y + glyph.getSize(), z + 0.999d, 0, 0);
            tessellator.addVertexWithUV(x + glyph.getSize(), y, z + 0.999d, 0, 1);
            tessellator.addVertexWithUV(x, y, z + 0.999d, 1, 1);
        }
        else if (orientation == ForgeDirection.SOUTH)
        {
            GL11.glTranslatef(0.5f - (glyph.getSize() / 2f), 0.5f - (glyph.getSize() / 2f), 0f);
            tessellator.addVertexWithUV(x, y + glyph.getSize(), z + 0.001d, 1, 0);
            tessellator.addVertexWithUV(x + glyph.getSize(), y + glyph.getSize(), z + 0.001d, 0, 0);
            tessellator.addVertexWithUV(x + glyph.getSize(), y, z + 0.001d, 0, 1);
            tessellator.addVertexWithUV(x, y, z + 0.001d, 1, 1);
        }
        else if (orientation == ForgeDirection.WEST)
        {
            GL11.glTranslatef(0f, 0.5f - (glyph.getSize() / 2f), 0.5f - (glyph.getSize() / 2f));
            tessellator.addVertexWithUV(x + 0.999d, y + glyph.getSize(), z, 0, 0);
            tessellator.addVertexWithUV(x + 0.999d, y + glyph.getSize(), z + glyph.getSize(), 0, 1);
            tessellator.addVertexWithUV(x + 0.999d, y, z + glyph.getSize(), 1, 1);
            tessellator.addVertexWithUV(x + 0.999d, y, z, 1, 0);
        }
        else if (orientation == ForgeDirection.EAST)
        {
            GL11.glTranslatef(0f, 0.5f - (glyph.getSize() / 2f), 0.5f - (glyph.getSize() / 2f));
            tessellator.addVertexWithUV(x + 0.001d, y + glyph.getSize(), z, 0, 0);
            tessellator.addVertexWithUV(x + 0.001d, y + glyph.getSize(), z + glyph.getSize(), 0, 1);
            tessellator.addVertexWithUV(x + 0.001d, y, z + glyph.getSize(), 1, 1);
            tessellator.addVertexWithUV(x + 0.001d, y, z, 1, 0);
        }

        tessellator.draw();
        GL11.glPopMatrix();
    }
}

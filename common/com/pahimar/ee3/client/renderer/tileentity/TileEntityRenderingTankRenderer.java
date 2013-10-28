package com.pahimar.ee3.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.client.model.ModelRenderingTank;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileRenderingTank;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityRenderingTankRenderer extends TileEntitySpecialRenderer {

    private ModelRenderingTank modelRenderingTank = new ModelRenderingTank();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

        if (tileEntity instanceof TileRenderingTank) {

            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);

            GL11.glPushMatrix();

            // Scale, Translate, Rotate
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x + 0.0F, (float) y + 0.0F, (float) z + 1.0F);
            //GL11.glRotatef(90F, 0F, 1F, 0F);

            // Bind texture
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_RENDERING_TANK);

            // Render
            modelRenderingTank.render();

            GL11.glPopMatrix();

            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_LIGHTING);
        }
    }

}

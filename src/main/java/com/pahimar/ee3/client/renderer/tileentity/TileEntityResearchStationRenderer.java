package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.client.renderer.model.ModelResearchStation;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityResearchStation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityResearchStationRenderer extends TileEntitySpecialRenderer
{
    private final ModelResearchStation modelResearchStation = new ModelResearchStation();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity instanceof TileEntityResearchStation)
        {
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);

            // Scale, Translate, Rotate
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x + 0.0F, (float) y + 0.0F, (float) z + 1.0F);

            // Bind texture
            this.bindTexture(Textures.MODEL_RESEARCH_STATION);

            // Render
            modelResearchStation.render();

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
}

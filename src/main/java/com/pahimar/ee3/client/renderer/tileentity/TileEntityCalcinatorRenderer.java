package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.client.model.ModelCalcinator;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileCalcinator;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * Equivalent-Exchange-3
 * <p/>
 * TileEntityCalcinatorRenderer
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class TileEntityCalcinatorRenderer extends TileEntitySpecialRenderer
{

    private ModelCalcinator modelCalcinator = new ModelCalcinator();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {

        if (tileEntity instanceof TileCalcinator)
        {
            TileCalcinator tileCalcinator = (TileCalcinator) tileEntity;

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);

            // Scale, Translate, Rotate
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 1.2F);
            GL11.glRotatef(45F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);

            // Bind texture
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_CALCINATOR);

            // Render
            modelCalcinator.renderPart("Calcinator");

            if (tileCalcinator.getStackInSlot(TileCalcinator.OUTPUT_LEFT_INVENTORY_INDEX) != null || tileCalcinator.getStackInSlot(TileCalcinator.OUTPUT_RIGHT_INVENTORY_INDEX) != null)
            {
                modelCalcinator.renderPart("Dust");
            }

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
}

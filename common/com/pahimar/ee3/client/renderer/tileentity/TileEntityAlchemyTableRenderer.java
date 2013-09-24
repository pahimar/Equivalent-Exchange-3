/**
 * 
 */
package com.pahimar.ee3.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.client.model.ModelAlchemyTable;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileAlchemyTable;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * TileEntityAlchemyTableRenderer
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class TileEntityAlchemyTableRenderer extends TileEntitySpecialRenderer {

    private ModelAlchemyTable modelAlchemyTable = new ModelAlchemyTable();

    /*
     * (non-Javadoc)
     * @see net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer#
     * renderTileEntityAt(net.minecraft.tileentity.TileEntity, double, double,
     * double, float)
     */
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

        if (tileEntity instanceof TileAlchemyTable) {
            // TODO Use the TileAlchemyTable to determine what circle to project on the server of the table
            TileAlchemyTable tileAlchemyTable = (TileAlchemyTable) tileEntity;

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);

            // Scale, Translate, Rotate
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x + 0.0F, (float) y + 0.0F, (float) z + 1.0F);

            // Bind texture
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_ALCHEMY_TABLE);

            // Render
            modelAlchemyTable.render();

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

}

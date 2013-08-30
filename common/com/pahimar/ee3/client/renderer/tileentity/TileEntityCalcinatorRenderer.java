package com.pahimar.ee3.client.renderer.tileentity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.client.model.ModelCalcinator;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileCalcinator;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * TileEntityCalcinatorRenderer
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class TileEntityCalcinatorRenderer extends TileEntitySpecialRenderer {

	private ModelCalcinator modelCalcinator = new ModelCalcinator();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

		if (tileEntity instanceof TileCalcinator) {
			TileCalcinator tileCalcinator = (TileCalcinator) tileEntity;

			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);

			// Scale, Translate, Rotate
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 1.2F);
			GL11.glRotatef(45F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);

			// Bind texture
			FMLClientHandler.instance().getClient().renderEngine.func_110577_a(Textures.MODEL_CALCINATOR);

			// Render
			modelCalcinator.renderPart("Calcinator");
			GL11.glPushMatrix();
			double translationvalue = (((Math.cos(System.currentTimeMillis()/1000D)%360)))*0.0125;
			GL11.glRotatef(12F, 0F, 1F, 0F);
			GL11.glTranslated(translationvalue/0.25, 0, translationvalue);
			GL11.glRotatef(-12F, 0F, 1F, 0F);
			modelCalcinator.renderPart("Pan");
			GL11.glPopMatrix();
			this.renderChain(translationvalue);
			if (tileCalcinator.getStackInSlot(TileCalcinator.OUTPUT_INVENTORY_INDEX) != null) {
				modelCalcinator.renderPart("Dust");
			}

			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();
		}
	}
	private void renderChain(double translationvalue){
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glBegin(GL11.GL_TRIANGLES);{
			//face1 quad1
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.347500 + translationvalue/0.25, 0.613300, 0.294500 + translationvalue);
			GL11.glTexCoord2d(0.468800, 1-0.125000);
			GL11.glVertex3d(0.387700 + translationvalue/0.25, 0.653500, 0.294500 + translationvalue);
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.356900, 0.685500, 0.397000);
			//face2 quad1
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.356900, 0.685500, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.296900);
			GL11.glVertex3d(0.316600, 0.645300, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.347500 + translationvalue/0.25, 0.613300, 0.294500 + translationvalue);
			//face3 quad2
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.613300 + translationvalue/0.25, 0.652500, 0.294500 + translationvalue);
			GL11.glTexCoord2d(0.468800, 1-0.125000);
			GL11.glVertex3d(0.653500 + translationvalue/0.25, 0.612300, 0.294500 + translationvalue);
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.685500, 0.643100, 0.397000);
			//face4 quad2
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.685500, 0.643100, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.296900);
			GL11.glVertex3d(0.645300, 0.683400, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.613300 + translationvalue/0.25, 0.652500, 0.294500 + translationvalue);
			//face5 quad3
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.386700 + translationvalue/0.25, 0.347500, 0.294500 + translationvalue);
			GL11.glTexCoord2d(0.468800, 1-0.125000);
			GL11.glVertex3d(0.346500 + translationvalue/0.25, 0.387700, 0.294500 + translationvalue);
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.314500, 0.356900, 0.397000);
			//face6 quad3
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.314500, 0.356900, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.296900);
			GL11.glVertex3d(0.354700, 0.316600, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.386700 + translationvalue/0.25, 0.347500, 0.294500 + translationvalue);
			//face7 quad4
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.652500 + translationvalue/0.25, 0.386700, 0.294500 + translationvalue);
			GL11.glTexCoord2d(0.468800, 1-0.125000);
			GL11.glVertex3d(0.612300 + translationvalue/0.25, 0.346500, 0.294500 + translationvalue);
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.643100, 0.314500, 0.397000);
			//face8 quad4
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.643100, 0.314500, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.296900);
			GL11.glVertex3d(0.683400, 0.354700, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.652500 + translationvalue/0.25, 0.386700, 0.294500 + translationvalue);
		}
		GL11.glEnd();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
}

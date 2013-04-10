package com.pahimar.ee3.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.ICustomModel;
import net.minecraftforge.client.model.obj.WavefrontObject;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.lib.Models;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileAludel;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * ModelAludel
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class ModelAludel extends ModelBase implements ICustomModel {

    private WavefrontObject modelAludelOBJ;

    public ModelAludel() {

        modelAludelOBJ = new WavefrontObject(Models.ALUDEL);
    }

    public void render() {

        modelAludelOBJ.renderAll();
    }

    public void render(TileAludel aludel, double x, double y, double z) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        
        // Scale, Translate, Rotate
        scaleTranslateRotate(x, y, z, aludel.getOrientation());
        
        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_ALUDEL);
        
        // Render
        this.render();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    private void scaleTranslateRotate(double x, double y, double z, ForgeDirection orientation) {

        if (orientation == ForgeDirection.NORTH) {
            GL11.glTranslated(x + 1, y, z);
            GL11.glRotatef(180F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if (orientation == ForgeDirection.EAST) {
            GL11.glTranslated(x + 1, y, z + 1);
            GL11.glRotatef(90F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if (orientation == ForgeDirection.SOUTH) {
            GL11.glTranslated(x, y, z + 1);
            GL11.glRotatef(0F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if (orientation == ForgeDirection.WEST) {
            GL11.glTranslated(x, y, z);
            GL11.glRotatef(-90F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
    }
}

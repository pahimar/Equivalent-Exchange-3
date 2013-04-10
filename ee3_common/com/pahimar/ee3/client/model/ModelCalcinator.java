package com.pahimar.ee3.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.ICustomModel;
import net.minecraftforge.client.model.obj.WavefrontObject;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.lib.Models;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileCalcinator;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ModelCalcinator
 * 
 * Model for the Calcinator
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class ModelCalcinator extends ModelBase implements ICustomModel {

    private WavefrontObject modelCalcinatorOBJ;

    public ModelCalcinator() {

        modelCalcinatorOBJ = new WavefrontObject(Models.CALCINATOR);
    }
    
    public void render() {
        
        modelCalcinatorOBJ.renderAll();
    }

    public void render(TileCalcinator calcinator, double x, double y, double z) {

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
        this.render();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}

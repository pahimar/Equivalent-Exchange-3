package com.pahimar.ee3.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.WavefrontObject;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.lib.Models;
import com.pahimar.ee3.lib.Reference;
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
public class ModelCalcinator extends ModelBase {

    private float scale;

    private WavefrontObject modelCalcinatorOBJ;

    public ModelCalcinator() {

        scale = 1F;
        modelCalcinatorOBJ = new WavefrontObject(Models.CALCINATOR);
    }

    public ModelCalcinator(float scale) {

        this.scale = scale;
        modelCalcinatorOBJ = new WavefrontObject(Models.CALCINATOR);
    }

    public void render(Tessellator tessellator, float scale) {

        if (modelCalcinatorOBJ.groupObjects.size() != 0) {
            for (GroupObject group : modelCalcinatorOBJ.groupObjects) {
                if (group.name.equalsIgnoreCase("calcinator")) {
                    group.render(tessellator, Reference.MODEL_TEXTURE_OFFSET, scale);
                }
            }
        }
    }

    public void render(TileCalcinator calcinator, double x, double y, double z) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 1.2F);
        GL11.glRotatef(45F, 0F, 1F, 0F);
        GL11.glRotatef(-90F, 1F, 0F, 0F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_CALCINATOR);
        this.render(Tessellator.instance, scale);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}

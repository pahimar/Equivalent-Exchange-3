package com.pahimar.ee3.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import com.obj.Face;
import com.obj.Group;
import com.obj.WavefrontObject;
import com.pahimar.ee3.lib.Models;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Sprites;
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

        this.scale = 1F;
        this.modelCalcinatorOBJ = new WavefrontObject(this.getClass().getResource("/" + Models.CALCINATOR).getFile());
    }

    public ModelCalcinator(float scale) {

        this.scale = scale;
        this.modelCalcinatorOBJ = new WavefrontObject(this.getClass().getResource("/" + Models.CALCINATOR).getFile());
    }

    public void render(Tessellator tessellator, float scale) {

        if (modelCalcinatorOBJ.getGroups().size() != 0) {
            for (Group group : modelCalcinatorOBJ.getGroups()) {
                if (group.getName().equalsIgnoreCase("calcinator")) {
                    if (group.getFaces().size() != 0) {
                        for (Face face : group.getFaces()) {
                            tessellator.startDrawing(GL11.GL_TRIANGLES);
    
                            float sumU = 0F;
                            float sumV = 0F;
    
                            for (int i = 0; i < face.getTextures().length; ++i) {
                                sumU += face.getTextures()[i].getU();
                                sumV += face.getTextures()[i].getV();
                            }
    
                            for (int i = 0; i < 3; ++i) {
                                float offsetU = Reference.MODEL_TEXTURE_OFFSET;
                                float offsetV = Reference.MODEL_TEXTURE_OFFSET;
                                if (face.getTextures()[i].getU() > (sumU / face.getTextures().length)) {
                                    offsetU = -offsetU;
                                }
                                if (face.getTextures()[i].getV() > (sumV / face.getTextures().length)) {
                                    offsetV = -offsetV;
                                }
                                tessellator.addVertexWithUV(face.getVertices()[i].getX() * scale, face.getVertices()[i].getY() * scale, face.getVertices()[i].getZ() * scale, face.getTextures()[i].getU() + offsetU, face.getTextures()[i].getV() + offsetV);
                            }
    
                            tessellator.draw();
                        }
                    }
                }
            }
        }
    }
    
    public void render(TileCalcinator calcinator, double x, double y, double z) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.0F, (float)z + 1.2F);
        GL11.glRotatef(45F, 0F, 1F, 0F);
        GL11.glRotatef(-90F, 1F, 0F, 0F);
        FMLClientHandler.instance().getClient().renderEngine.func_98187_b(Sprites.MODEL_CALCINATOR);
        this.render(Tessellator.instance, this.scale);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}

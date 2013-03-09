package com.pahimar.ee3.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

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

    private static final int TEXTURE_HEIGHT = 128;
    private static final int TEXTURE_WIDTH = 128;

    private ModelRenderer firePotBottom, firePotLeft, firePotRight, firePotFront, firePotBack;
    private ModelRenderer legFrontLeft, legFrontRight, legBackLeft, legBackRight;
    private ModelRenderer armFrontLeft, armFrontRight, armBackLeft, armBackRight;
    private ModelRenderer bowlBottom, bowlLeft, bowlRight, bowlFront, bowlBack;
    private ModelRenderer firePotEmbers, bowlEmbers;

    private float scale;

    public ModelCalcinator(float scale) {

        this.scale = scale;
        textureHeight = TEXTURE_HEIGHT;
        textureWidth = TEXTURE_WIDTH;

        firePotLeft = new ModelRenderer(this, 0, 36);
        firePotLeft.addBox(-0.5F, -1.5F, -4F, 1, 3, 8, scale);
        firePotLeft.setRotationPoint(3.5F, 1F, 0F);
        firePotRight = new ModelRenderer(this, 0, 36);
        firePotRight.addBox(-0.5F, -1.5F, -4F, 1, 3, 8, scale);
        firePotRight.setRotationPoint(-3.5F, 1F, 0F);
        firePotBack = new ModelRenderer(this, 0, 36);
        firePotBack.addBox(-3F, -1.5F, -0.5F, 6, 3, 1, scale);
        firePotBack.setRotationPoint(0F, 1F, -3.5F);
        firePotFront = new ModelRenderer(this, 0, 36);
        firePotFront.addBox(-3F, -1.5F, -0.5F, 6, 3, 1, scale);
        firePotFront.setRotationPoint(0F, 1F, 3.5F);
        firePotBottom = new ModelRenderer(this, 0, 36);
        firePotBottom.addBox(-3F, -0.5F, -3F, 6, 1, 6, scale);
        firePotBottom.setRotationPoint(8, 2, 8);

        firePotBottom.addChild(firePotBack);
        firePotBottom.addChild(firePotFront);
        firePotBottom.addChild(firePotLeft);
        firePotBottom.addChild(firePotRight);

        legFrontLeft = new ModelRenderer(this, 0, 0);
        legFrontLeft.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        legFrontLeft.setRotationPoint(-9F, 6, 0);
        legFrontRight = new ModelRenderer(this, 0, 0);
        legFrontRight.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        legFrontRight.setRotationPoint(9F, 6, 0);
        legBackLeft = new ModelRenderer(this, 0, 0);
        legBackLeft.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        legBackLeft.setRotationPoint(0, 6, -9F);
        legBackRight = new ModelRenderer(this, 0, 0);
        legBackRight.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        legBackRight.setRotationPoint(0, 6, 9F);

        armFrontLeft = new ModelRenderer(this, 0, 10);
        armFrontLeft.addBox(-2F, -0.5F, -1F, 4, 1, 2, scale);
        armFrontLeft.setRotationPoint(6, 1, 0);
        armFrontRight = new ModelRenderer(this, 0, 10);
        armFrontRight.addBox(-2F, -0.5F, -1F, 4, 1, 2, scale);
        armFrontRight.setRotationPoint(-6, 1, 0);
        armBackLeft = new ModelRenderer(this, 12, 10);
        armBackLeft.addBox(-1F, -0.5F, -2.0F, 2, 1, 4, scale);
        armBackLeft.setRotationPoint(0, 1, 6);
        armBackRight = new ModelRenderer(this, 12, 10);
        armBackRight.addBox(-1F, -0.5F, -2.0F, 2, 1, 4, scale);
        armBackRight.setRotationPoint(0, 1, -6);

        firePotBottom.addChild(legFrontLeft);
        firePotBottom.addChild(legFrontRight);
        firePotBottom.addChild(legBackLeft);
        firePotBottom.addChild(legBackRight);
        firePotBottom.addChild(armFrontLeft);
        firePotBottom.addChild(armFrontRight);
        firePotBottom.addChild(armBackLeft);
        firePotBottom.addChild(armBackRight);
        firePotBottom.rotateAngleY = (float) (Math.PI / 4F);

        bowlBack = new ModelRenderer(this, 0, 36);
        bowlBack.addBox(-8F, -3.5F, -0.5F, 16, 7, 1, scale);
        bowlBack.setRotationPoint(0F, 3.6F, 7.5F);
        bowlFront = new ModelRenderer(this, 0, 36);
        bowlFront.addBox(-8F, -3.5F, -0.5F, 16, 7, 1, scale);
        bowlFront.setRotationPoint(0, 3.6F, -7.5F);
        bowlLeft = new ModelRenderer(this, 0, 44);
        bowlLeft.addBox(-0.5F, -3.5F, -7F, 1, 7, 14, scale);
        bowlLeft.setRotationPoint(7.5F, 3.6F, 0);
        bowlRight = new ModelRenderer(this, 0, 44);
        bowlRight.addBox(-0.5F, -3.5F, -7F, 1, 7, 14, scale);
        bowlRight.setRotationPoint(-7.5F, 3.6F, 0);

        bowlBottom = new ModelRenderer(this, 0, 19);
        bowlBottom.addBox(-8F, -1F, -8F, 16, 1, 16, scale);
        bowlBottom.setRotationPoint(8, 9, 8);
        bowlBottom.addChild(bowlBack);
        bowlBottom.addChild(bowlFront);
        bowlBottom.addChild(bowlLeft);
        bowlBottom.addChild(bowlRight);

        bowlEmbers = new ModelRenderer(this, 0, 65);
        bowlEmbers.addBox(-7F, -0.5F, -7F, 14, 1, 14, scale);
        bowlEmbers.setRotationPoint(8, 9, 8);
        bowlEmbers.mirror = true;

        firePotEmbers = new ModelRenderer(this, 0, 65);
        firePotEmbers.addBox(-3F, -0.5F, -3F, 6, 1, 6, scale);
        firePotEmbers.setRotationPoint(8, 3, 8);
        firePotEmbers.rotateAngleY = (float) (Math.PI / 4F);

    }

    public void render(float scale) {

        firePotBottom.render(scale);
        bowlBottom.render(scale);
        firePotEmbers.render(scale);
        bowlEmbers.render(scale);
    }

    public void render(TileCalcinator calcinator, double x, double y, double z) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslated(x, y, z);
        FMLClientHandler.instance().getClient().renderEngine.func_98187_b(Sprites.MODEL_CALCINATOR);
        this.render(scale);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}

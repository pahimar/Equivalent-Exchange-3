package com.pahimar.ee3.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.tileentity.TileCalcinator;

/**
 * ModelCalcinator
 * 
 * Model for the Calcinator
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
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
        this.textureHeight = TEXTURE_HEIGHT;
        this.textureWidth = TEXTURE_WIDTH;

        this.firePotLeft = new ModelRenderer(this, 0, 36);
        this.firePotLeft.addBox(-0.5F, -1.5F, -4F, 1, 3, 8, scale);
        this.firePotLeft.setRotationPoint(3.5F, 1F, 0F);
        this.firePotRight = new ModelRenderer(this, 0, 36);
        this.firePotRight.addBox(-0.5F, -1.5F, -4F, 1, 3, 8, scale);
        this.firePotRight.setRotationPoint(-3.5F, 1F, 0F);
        this.firePotBack = new ModelRenderer(this, 0, 36);
        this.firePotBack.addBox(-3F, -1.5F, -0.5F, 6, 3, 1, scale);
        this.firePotBack.setRotationPoint(0F, 1F, -3.5F);
        this.firePotFront = new ModelRenderer(this, 0, 36);
        this.firePotFront.addBox(-3F, -1.5F, -0.5F, 6, 3, 1, scale);
        this.firePotFront.setRotationPoint(0F, 1F, 3.5F);
        this.firePotBottom = new ModelRenderer(this, 0, 36);
        this.firePotBottom.addBox(-3F, -0.5F, -3F, 6, 1, 6, scale);
        this.firePotBottom.setRotationPoint(8, 2, 8);

        this.firePotBottom.addChild(this.firePotBack);
        this.firePotBottom.addChild(this.firePotFront);
        this.firePotBottom.addChild(this.firePotLeft);
        this.firePotBottom.addChild(this.firePotRight);

        this.legFrontLeft = new ModelRenderer(this, 0, 0);
        this.legFrontLeft.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        this.legFrontLeft.setRotationPoint(-9F, 6, 0);
        this.legFrontRight = new ModelRenderer(this, 0, 0);
        this.legFrontRight.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        this.legFrontRight.setRotationPoint(9F, 6, 0);
        this.legBackLeft = new ModelRenderer(this, 0, 0);
        this.legBackLeft.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        this.legBackLeft.setRotationPoint(0, 6, -9F);
        this.legBackRight = new ModelRenderer(this, 0, 0);
        this.legBackRight.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        this.legBackRight.setRotationPoint(0, 6, 9F);

        this.armFrontLeft = new ModelRenderer(this, 0, 10);
        this.armFrontLeft.addBox(-2F, -0.5F, -1F, 4, 1, 2, scale);
        this.armFrontLeft.setRotationPoint(6, 1, 0);
        this.armFrontRight = new ModelRenderer(this, 0, 10);
        this.armFrontRight.addBox(-2F, -0.5F, -1F, 4, 1, 2, scale);
        this.armFrontRight.setRotationPoint(-6, 1, 0);
        this.armBackLeft = new ModelRenderer(this, 12, 10);
        this.armBackLeft.addBox(-1F, -0.5F, -2.0F, 2, 1, 4, scale);
        this.armBackLeft.setRotationPoint(0, 1, 6);
        this.armBackRight = new ModelRenderer(this, 12, 10);
        this.armBackRight.addBox(-1F, -0.5F, -2.0F, 2, 1, 4, scale);
        this.armBackRight.setRotationPoint(0, 1, -6);

        this.firePotBottom.addChild(this.legFrontLeft);
        this.firePotBottom.addChild(this.legFrontRight);
        this.firePotBottom.addChild(this.legBackLeft);
        this.firePotBottom.addChild(this.legBackRight);
        this.firePotBottom.addChild(this.armFrontLeft);
        this.firePotBottom.addChild(this.armFrontRight);
        this.firePotBottom.addChild(this.armBackLeft);
        this.firePotBottom.addChild(this.armBackRight);
        this.firePotBottom.rotateAngleY = (float) (Math.PI / 4F);

        this.bowlBack = new ModelRenderer(this, 0, 36);
        this.bowlBack.addBox(-8F, -3.5F, -0.5F, 16, 7, 1, scale);
        this.bowlBack.setRotationPoint(0F, 3.6F, 7.5F);
        this.bowlFront = new ModelRenderer(this, 0, 36);
        this.bowlFront.addBox(-8F, -3.5F, -0.5F, 16, 7, 1, scale);
        this.bowlFront.setRotationPoint(0, 3.6F, -7.5F);
        this.bowlLeft = new ModelRenderer(this, 0, 44);
        this.bowlLeft.addBox(-0.5F, -3.5F, -7F, 1, 7, 14, scale);
        this.bowlLeft.setRotationPoint(7.5F, 3.6F, 0);
        this.bowlRight = new ModelRenderer(this, 0, 44);
        this.bowlRight.addBox(-0.5F, -3.5F, -7F, 1, 7, 14, scale);
        this.bowlRight.setRotationPoint(-7.5F, 3.6F, 0);

        this.bowlBottom = new ModelRenderer(this, 0, 19);
        this.bowlBottom.addBox(-8F, -1F, -8F, 16, 1, 16, scale);
        this.bowlBottom.setRotationPoint(8, 9, 8);
        this.bowlBottom.addChild(this.bowlBack);
        this.bowlBottom.addChild(this.bowlFront);
        this.bowlBottom.addChild(this.bowlLeft);
        this.bowlBottom.addChild(this.bowlRight);

        this.bowlEmbers = new ModelRenderer(this, 0, 65);
        this.bowlEmbers.addBox(-7F, -0.5F, -7F, 14, 1, 14, scale);
        this.bowlEmbers.setRotationPoint(8, 9, 8);
        this.bowlEmbers.mirror = true;

        this.firePotEmbers = new ModelRenderer(this, 0, 65);
        this.firePotEmbers.addBox(-3F, -0.5F, -3F, 6, 1, 6, scale);
        this.firePotEmbers.setRotationPoint(8, 3, 8);
        this.firePotEmbers.rotateAngleY = (float) (Math.PI / 4F);

    }

    public void render(TileCalcinator calcinator, double x, double y, double z) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslated(x, y, z);
        ForgeHooksClient.bindTexture(Sprites.CALCINATOR_MODEL_TEXTURE, 0);

        firePotBottom.render(scale);
        bowlBottom.render(scale);
        firePotEmbers.render(scale);
        bowlEmbers.render(scale);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    public void render(float scale) {

        firePotBottom.render(scale);
        bowlBottom.render(scale);
        firePotEmbers.render(scale);
        bowlEmbers.render(scale);
    }

}

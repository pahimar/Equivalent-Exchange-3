package com.pahimar.ee3.client.model;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileCalcinator;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.client.ForgeHooksClient;


public class ModelAludel extends ModelBase {
    
    private static final int TEXTURE_HEIGHT = 128;
    private static final int TEXTURE_WIDTH = 128;
    
    private float scale;
    
    private ModelRenderer northStand, southStand, eastStand, westStand;
    private ModelRenderer bottomBulb, topBulb;
    private ModelRenderer chute;
    
    public ModelAludel(float scale) {

        this.scale = scale;
        this.textureHeight = TEXTURE_HEIGHT;
        this.textureWidth = TEXTURE_WIDTH;
        
        this.northStand = new ModelRenderer(this, 0, 36);
        this.northStand.addBox(-2F, -1F, -1F, 4, 2, 2);
        this.northStand.setRotationPoint(4F, 1F, 8F);
        this.southStand = new ModelRenderer(this, 0, 36);
        this.southStand.addBox(-2F, -1F, -1F, 4, 2, 2);
        this.southStand.setRotationPoint(12F, 1F, 8F);
        this.eastStand = new ModelRenderer(this, 0, 36);
        this.eastStand.addBox(-1F, -1F, -2F, 2, 2, 4);
        this.eastStand.setRotationPoint(8F, 1F, 4F);
        this.westStand = new ModelRenderer(this, 0, 36);
        this.westStand.addBox(-1F, -1F, -2F, 2, 2, 4);
        this.westStand.setRotationPoint(8F, 1F, 12F);
        
        this.northStand.addChild(this.southStand);
        this.northStand.addChild(this.eastStand);
        this.northStand.addChild(this.westStand);
        this.northStand.rotateAngleY = (float) (Math.PI / 4F);
        
        this.bottomBulb = new ModelRenderer(this, 0, 36);
        this.bottomBulb.addBox(-4F, -3F, -4F, 8, 6, 8);
        this.bottomBulb.setRotationPoint(8F, 4.5F, 8F);
        
        this.topBulb = new ModelRenderer(this, 0, 36);
        this.topBulb.addBox(-3F, -3F, -3F, 6, 6, 6);
        this.topBulb.setRotationPoint(8F, 13F, 8F);
        
        this.chute = new ModelRenderer(this, 0, 36);
        this.chute.addBox(-2F, -1.5F, -2F, 4, 3, 4);
        this.chute.setRotationPoint(8F, 9F, 8F);
        
    }
    
    public void render(float scale) {
        
        northStand.render(scale);
        /*
        bottomBulb.render(scale);
        chute.render(scale);
        topBulb.render(scale);
        */
    }
    
    public void render(TileAludel aludel, double x, double y, double z) {
        
        GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glTranslated(x, y, z);
            ForgeHooksClient.bindTexture(Sprites.MODEL_ALUDEL, 0);
            this.render(scale);
            GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}

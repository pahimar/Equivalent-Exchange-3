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
    
    private ModelRenderer bottomPlate;
    private ModelRenderer bottomBulb, topBulb;
    private ModelRenderer chute;
    
    public ModelAludel(float scale) {

        this.scale = scale;
        this.textureHeight = TEXTURE_HEIGHT;
        this.textureWidth = TEXTURE_WIDTH;
        
        this.bottomPlate = new ModelRenderer(this, 0, 36);
        this.bottomPlate.addBox(-8F, -0.5F, -8F, 16, 1, 16, scale);
        this.bottomPlate.setRotationPoint(8F, 0.5F, 8F);
        
        this.bottomBulb = new ModelRenderer(this, 0, 36);
        this.bottomBulb.addBox(-3F, -3F, -3F, 6, 6, 6);
        this.bottomBulb.setRotationPoint(8F, 4.5F, 8F);
        
        this.topBulb = new ModelRenderer(this, 0, 36);
        this.topBulb.addBox(-3F, -3F, -3F, 6, 6, 6);
        this.topBulb.setRotationPoint(8F, 13F, 8F);
        
        this.chute = new ModelRenderer(this, 0, 36);
        this.chute.addBox(-2F, -0.5F, -2F, 4, 1, 4);
        this.chute.setRotationPoint(8F, 9.5F, 8F);
        
    }
    
    public void render(TileAludel aludel, double x, double y, double z) {
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslated(x, y, z);
        ForgeHooksClient.bindTexture(Sprites.MODEL_ALUDEL, 0);

        //bottomPlate.render(scale);
        bottomBulb.render(scale);
        topBulb.render(scale);
        chute.render(scale);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
    public void render(float scale) {
        
    }
}

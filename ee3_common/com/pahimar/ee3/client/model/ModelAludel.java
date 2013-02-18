package com.pahimar.ee3.client.model;

import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileCalcinator;

import net.minecraft.client.model.ModelBase;


public class ModelAludel extends ModelBase {
    
    private static final int TEXTURE_HEIGHT = 128;
    private static final int TEXTURE_WIDTH = 128;
    
    private float scale;
    
    public ModelAludel(float scale) {

        this.scale = scale;
        this.textureHeight = TEXTURE_HEIGHT;
        this.textureWidth = TEXTURE_WIDTH;
    }
    
    public void render(TileAludel aludel, double x, double y, double z) {
        
    }
    
    public void render(float scale) {
        
    }
}

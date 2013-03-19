package net.minecraftforge.client.model.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextureCoordinate {

    /**
     * 
     */
    public float u, v, w;
    
    /**
     * 
     */
    public TextureCoordinate() {

    }
    
    /**
     * @param u
     * @param v
     */
    public TextureCoordinate(float u, float v) {
        
        this(u, v, 0F);
    }

    /**
     * @param u
     * @param v
     * @param w
     */
    public TextureCoordinate(float u, float v, float w) {

        this.u = u;
        this.v = v;
        this.w = w;
    }
}

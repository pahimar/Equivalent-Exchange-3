package net.minecraftforge.client.model.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Vertex {

    /**
     * 
     */
    public float x, y, z;

    /**
     * 
     */
    public Vertex() {

    }
    
    /**
     * @param x
     * @param y
     */
    public Vertex(float x, float y) {
        
        this(x, y, 0F);
    }

    /**
     * @param x
     * @param y
     * @param z
     */
    public Vertex(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }
}

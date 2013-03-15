package net.minecraftforge.client.model.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Vertex {

    public float x, y, z;

    public Vertex() {

    }

    public Vertex(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex(Vertex vertex) {

        x = vertex.x;
        y = vertex.y;
        z = vertex.z;
    }

    @Override
    public String toString() {

        return "x: " + x + ", y: " + y + ", z: " + z;
    }
}

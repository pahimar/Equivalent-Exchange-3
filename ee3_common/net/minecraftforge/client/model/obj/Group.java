package net.minecraftforge.client.model.obj;

import java.util.ArrayList;

import net.minecraft.client.renderer.Tessellator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Group {

    public String name;
    public ArrayList<Face> faces = new ArrayList<Face>();
    public ArrayList<Integer> indices = new ArrayList<Integer>();
    public ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    public ArrayList<Vertex> vertexNormals = new ArrayList<Vertex>();
    public ArrayList<TextureCoordinate> textureCoordinates = new ArrayList<TextureCoordinate>();
    public int indexCount;
    
    public Group() {
        
        indexCount = 0;
        name = "";
    }
    
    public Group(String name) {

        indexCount = 0;
        this.name = name;
    }

    public void render(Tessellator tessellator, float scale) {

        for (Face face : faces) {
            face.render(tessellator, 0F, scale);
        }
    }

    public void render(Tessellator tessellator, float textureOffset, float scale) {

        for (Face face : faces) {
            face.render(tessellator, textureOffset, scale);
        }
    }
}

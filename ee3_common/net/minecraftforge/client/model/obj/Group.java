package net.minecraftforge.client.model.obj;

import java.util.ArrayList;

import net.minecraft.client.renderer.Tessellator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Group {

    private String name;
    private Vertex min = null;
    private ArrayList<Face> faces = new ArrayList<Face>();

    public ArrayList<Integer> indices = new ArrayList<Integer>();
    public ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    public ArrayList<Vertex> vertexNormals = new ArrayList<Vertex>();
    public ArrayList<TextureCoordinate> textureCoordinates = new ArrayList<TextureCoordinate>();
    public int indexCount;

    public Group(String name) {

        indexCount = 0;
        this.name = name;
    }

    public void addFace(Face face) {

        faces.add(face);
    }

    public void pack() {

        float minX = 0;
        float minY = 0;
        float minZ = 0;
        Face currentFace = null;
        Vertex currentVertex = null;
        for (int i = 0; i < faces.size(); i++) {
            currentFace = faces.get(i);
            for (int j = 0; j < currentFace.vertices.length; j++) {
                currentVertex = currentFace.vertices[j];
                if (Math.abs(currentVertex.x) > minX) {
                    minX = Math.abs(currentVertex.x);
                }
                if (Math.abs(currentVertex.y) > minY) {
                    minY = Math.abs(currentVertex.y);
                }
                if (Math.abs(currentVertex.z) > minZ) {
                    minZ = Math.abs(currentVertex.z);
                }
            }
        }

        min = new Vertex(minX, minY, minZ);
    }

    public String getName() {

        return name;
    }

    public ArrayList<Face> getFaces() {

        return faces;
    }

    public Vertex getMin() {

        return min;
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

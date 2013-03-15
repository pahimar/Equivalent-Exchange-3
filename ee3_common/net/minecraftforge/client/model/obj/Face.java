package net.minecraftforge.client.model.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Face {

    public static int GL_TRIANGLES = 1;
    public static int GL_QUADS = 2;

    public int[] vertIndices;
    public int[] normIndices;
    public int[] texIndices;
    private Vertex[] vertices;
    private Vertex[] normals;
    private TextureCoordinate[] textures;

    private int type;

    public int[] getIndices() {

        return vertIndices;
    }

    public Vertex[] getVertices() {

        return vertices;
    }

    public void setIndices(int[] indices) {

        vertIndices = indices;
    }

    public void setVertices(Vertex[] vertices) {

        this.vertices = vertices;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public Vertex[] getNormals() {

        return normals;
    }

    public void setNormals(Vertex[] normals) {

        this.normals = normals;
    }

    public TextureCoordinate[] getTextures() {

        return textures;
    }

    public void setTextures(TextureCoordinate[] textures) {

        this.textures = textures;
    }
}

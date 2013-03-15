package net.minecraftforge.client.model.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Face {

    public int[] vertexIndices;
    public int[] vertexNormalIndices;
    public int[] textureCoordinateIndices;

    private Vertex[] vertices;
    private Vertex[] vertexNormals;
    private TextureCoordinate[] textureCoordinates;

    private int glDrawingMode;

    public int[] getVertexIndices() {

        return vertexIndices;
    }

    public Vertex[] getVertices() {

        return vertices;
    }

    public void setVertexIndices(int[] vertexIndices) {

        this.vertexIndices = vertexIndices;
    }

    public void setVertices(Vertex[] vertices) {

        this.vertices = vertices;
    }

    public int getDrawingMode() {

        return glDrawingMode;
    }

    public void setDrawingMode(int glDrawingMode) {

        this.glDrawingMode = glDrawingMode;
    }

    public Vertex[] getVertexNormals() {

        return vertexNormals;
    }

    public void setVertexNormals(Vertex[] vertexNormals) {

        this.vertexNormals = vertexNormals;
    }

    public TextureCoordinate[] getTextureCoordinates() {

        return textureCoordinates;
    }

    public void setTextureCoordinates(TextureCoordinate[] textureCoordinates) {

        this.textureCoordinates = textureCoordinates;
    }
}

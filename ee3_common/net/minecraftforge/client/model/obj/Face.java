package net.minecraftforge.client.model.obj;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Face {

    public Vertex[] vertices;
    public Vertex[] vertexNormals;
    public TextureCoordinate[] textureCoordinates;

    public boolean invertNormal = false;
    public int glDrawingMode;

    public Vec3 getFaceNormal() {

        Vec3 v1 = Vec3.createVectorHelper(vertices[1].x - vertices[0].x, vertices[1].y - vertices[0].y, vertices[1].z - vertices[0].z);
        Vec3 v2 = Vec3.createVectorHelper(vertices[2].x - vertices[0].x, vertices[2].y - vertices[0].y, vertices[2].z - vertices[0].z);

        return v1.crossProduct(v2).normalize();
    }

    public void render(Tessellator tessellator, float scale) {

        this.render(tessellator, 0F, scale);
    }

    public void render(Tessellator tessellator, float textureOffset, float scale) {

        tessellator.startDrawing(glDrawingMode);

        tessellator.setNormal((float) getFaceNormal().xCoord, (float) getFaceNormal().yCoord, (float) getFaceNormal().zCoord);

        float averageU = 0F;
        float averageV = 0F;

        for (int i = 0; i < textureCoordinates.length; ++i) {
            averageU += textureCoordinates[i].u;
            averageV += textureCoordinates[i].v;
        }

        averageU = averageU / textureCoordinates.length;
        averageV = averageV / textureCoordinates.length;

        float offsetU, offsetV;

        for (int i = 0; i < vertices.length; ++i) {

            offsetU = textureOffset;
            offsetV = textureOffset;

            if (textureCoordinates[i].u > averageU) {
                offsetU = -offsetU;
            }
            if (textureCoordinates[i].v > averageV) {
                offsetV = -offsetV;
            }

            tessellator.addVertexWithUV(vertices[i].x * scale, vertices[i].y * scale, vertices[i].z * scale, textureCoordinates[i].u + offsetU, textureCoordinates[i].v + offsetV);
        }

        tessellator.draw();
    }
}

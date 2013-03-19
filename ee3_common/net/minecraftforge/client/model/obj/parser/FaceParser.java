package net.minecraftforge.client.model.obj.parser;

import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.Group;
import net.minecraftforge.client.model.obj.TextureCoordinate;
import net.minecraftforge.client.model.obj.Vertex;
import net.minecraftforge.client.model.obj.WavefrontObject;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FaceParser extends LineParser {

    private Face face;
    public int[] vindices;
    public int[] nindices;
    public int[] tindices;
    private Vertex[] vertices;
    private Vertex[] normals;
    private TextureCoordinate[] textures;
    private WavefrontObject object = null;

    public FaceParser(WavefrontObject object) {

        this.object = object;
    }

    @Override
    public void parse() {

        face = new Face();
        switch (words.length) {
            case 4:
                parseTriangles();
                break;
            case 5:
                parseQuad();
                break;
            default:
                //throw new RuntimeException("Could not identify face around '" + object.getFaces()+1 );
                break;
        }

    }

    private void parseTriangles() {

        face.glDrawingMode = GL11.GL_TRIANGLES;
        parseLine(3);
    }

    private void parseLine(int vertexCount) {

        String[] rawFaces = null;
        int currentValue;

        vindices = new int[vertexCount];
        nindices = new int[vertexCount];
        tindices = new int[vertexCount];
        vertices = new Vertex[vertexCount];
        normals = new Vertex[vertexCount];
        textures = new TextureCoordinate[vertexCount];

        for (int i = 1; i <= vertexCount; i++) {
            rawFaces = words[i].split("/");

            // v
            currentValue = Integer.parseInt(rawFaces[0]);
            vindices[i - 1] = currentValue - 1;
            // save vertex
            vertices[i - 1] = object.getVertices().get(currentValue - 1); // -1 because references starts at 1

            if (rawFaces.length == 1) {
                continue;
            }

            // save texcoords
            if (!"".equals(rawFaces[1])) {
                currentValue = Integer.parseInt(rawFaces[1]);
                //System.out.println( currentValue+" at line: " + lineCounter);
                if (currentValue <= object.getTextureCoordinates().size()) // This is to compensate the fact that if no texture is in the obj file, sometimes '1' is put instead of 'blank' (we find coord1/1/coord3 instead of coord1//coord3 or coord1/coord3)
                {
                    tindices[i - 1] = currentValue - 1;
                    textures[i - 1] = object.getTextureCoordinates().get(currentValue - 1); // -1 because references starts at 1
                }
                //System.out.print("indice="+currentValue+" ="+textures[i-1].getU() + ","+textures[i-1].getV());
                //System.out.println(textures[i-1].getU()+"-"+textures[i-1].getV());
            }

            // save normal
            currentValue = Integer.parseInt(rawFaces[2]);

            nindices[i - 1] = currentValue - 1;
            normals[i - 1] = object.getVertexNormals().get(currentValue - 1); // -1 because references starts at 1
        }
        //System.out.println("");
    }

    private void parseQuad() {

        face.glDrawingMode = GL11.GL_QUADS;
        parseLine(4);
    }

    @Override
    public void incoporateResults(WavefrontObject wavefrontObject) {

        //wavefrontObject.getFaces().add(face);
        Group group = wavefrontObject.getCurrentGroup();

        if (group == null) {
            group = new Group("Default created by loader");
            wavefrontObject.getGroups().add(group);
            wavefrontObject.getGroupsDirectAccess().put(group.name, group);
            wavefrontObject.setCurrentGroup(group);
        }

        if (vertices.length == 3) {
            // Add list of vertex/normal/texcoord to current group
            // Each object keeps a list of its own data, apart from the global list
            group.vertices.add(vertices[0]);
            group.vertices.add(vertices[1]);
            group.vertices.add(vertices[2]);
            group.vertexNormals.add(normals[0]);
            group.vertexNormals.add(normals[1]);
            group.vertexNormals.add(normals[2]);
            group.textureCoordinates.add(textures[0]);
            group.textureCoordinates.add(textures[1]);
            group.textureCoordinates.add(textures[2]);
            group.indices.add(group.indexCount++);
            group.indices.add(group.indexCount++);
            group.indices.add(group.indexCount++); // create index list for current object

            face.vertexNormals = normals;
            face.vertices = vertices;
            face.textureCoordinates = textures;

            wavefrontObject.getCurrentGroup().faces.add(face);
        }
        else {
            // Add list of vertex/normal/texcoord to current group
            // Each object keeps a list of its own data, apart from the global list
            group.vertices.add(vertices[0]);
            group.vertices.add(vertices[1]);
            group.vertices.add(vertices[2]);
            group.vertices.add(vertices[3]);
            group.vertexNormals.add(normals[0]);
            group.vertexNormals.add(normals[1]);
            group.vertexNormals.add(normals[2]);
            group.vertexNormals.add(normals[3]);
            group.textureCoordinates.add(textures[0]);
            group.textureCoordinates.add(textures[1]);
            group.textureCoordinates.add(textures[2]);
            group.textureCoordinates.add(textures[3]);
            group.indices.add(group.indexCount++);
            group.indices.add(group.indexCount++);
            group.indices.add(group.indexCount++);
            group.indices.add(group.indexCount++); // create index list for current object

            face.vertexNormals = normals;
            face.vertices = vertices;
            face.textureCoordinates = textures;

            wavefrontObject.getCurrentGroup().faces.add(face);
        }
    }

    static int faceC = 0;
}

package net.minecraftforge.client.model.obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.model.obj.parser.LineParser;
import net.minecraftforge.client.model.obj.parser.ObjLineParserFactory;

@SideOnly(Side.CLIENT)
public class WavefrontObject {

    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Vertex> normals = new ArrayList<Vertex>();
    private ArrayList<TextureCoordinate> textureCoordinates = new ArrayList<TextureCoordinate>();
    private ArrayList<Group> groups = new ArrayList<Group>();
    private Hashtable<String, Group> groupsDirectAccess = new Hashtable<String, Group>();
    public String fileName;

    private ObjLineParserFactory parserFactory;

    private Group currentGroup;

    private String contextfolder = "";

    public double radius = 0;

    public float xScale;
    public float yScale;
    public float zScale;

    public Vertex translate;
    public Vertex rotate;

    public WavefrontObject(String fileName) {

        this(fileName, 1f, 1f, 1f, new Vertex(), new Vertex());
    }

    public WavefrontObject(String fileName, float xScale, float yScale, float zScale) {

        this(fileName, xScale, yScale, zScale, new Vertex(), new Vertex());
    }

    public WavefrontObject(String fileName, float scale) {

        this(fileName, scale, scale, scale, new Vertex(), new Vertex());
    }

    public WavefrontObject(String fileName, float scale, Vertex translation, Vertex rotation) {

        this(fileName, scale, scale, scale, translation, rotation);
    }

    public WavefrontObject(String fileName, float xScale, float yScale, float zScale, Vertex translation, Vertex rotation) {

        try {
            this.fileName = fileName;

            translate = translation;
            rotate = rotation;

            this.xScale = xScale;
            this.yScale = yScale;
            this.zScale = zScale;

            int lastSlashIndex = fileName.lastIndexOf('/');
            if (lastSlashIndex != -1) {
                contextfolder = fileName.substring(0, lastSlashIndex + 1);
            }

            lastSlashIndex = fileName.lastIndexOf('\\');
            if (lastSlashIndex != -1) {
                contextfolder = fileName.substring(0, lastSlashIndex + 1);
            }

            parse(fileName);

            calculateRadius();
        }
        catch (Exception e) {
            System.out.println("Error, could not load obj:" + fileName);
        }
    }

    private void calculateRadius() {

        double currentNorm = 0;
        for (Vertex vertex : vertices) {
            currentNorm = vertex.norm();
            if (currentNorm > radius) {
                radius = currentNorm;
            }
        }

    }

    public String getContextfolder() {

        return contextfolder;
    }

    public void parse(String fileName) {
        
        BufferedReader reader = null;
        InputStream inputStream = null;
        
        parserFactory = new ObjLineParserFactory(this);
        
        try {
            inputStream = this.getClass().getResource(fileName).openStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                parseLine(currentLine);
            }
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                inputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseLine(String currentLine) {

        if (currentLine.length() > 0) {
            LineParser parser = parserFactory.getLineParser(currentLine);
            parser.parse();
            parser.incoporateResults(this);
        }
    }

    public void setTextureCoordinates(ArrayList<TextureCoordinate> textures) {

        this.textureCoordinates = textures;
    }

    public ArrayList<TextureCoordinate> getTextureCoordinates() {

        return textureCoordinates;
    }

    public void setVertices(ArrayList<Vertex> vertices) {

        this.vertices = vertices;
    }

    public ArrayList<Vertex> getVertices() {

        return vertices;
    }

    public void setNormals(ArrayList<Vertex> normals) {

        this.normals = normals;
    }

    public ArrayList<Vertex> getNormals() {

        return normals;
    }

    public ArrayList<Group> getGroups() {

        return groups;
    }

    public Hashtable<String, Group> getGroupsDirectAccess() {

        return groupsDirectAccess;
    }

    public Group getCurrentGroup() {

        return currentGroup;
    }

    public void setCurrentGroup(Group currentGroup) {

        this.currentGroup = currentGroup;
    }

    public String getBoudariesText() {

        float minX = 0;
        float maxX = 0;
        float minY = 0;
        float maxY = 0;
        float minZ = 0;
        float maxZ = 0;

        Vertex currentVertex = null;
        for (int i = 0; i < getVertices().size(); i++) {
            currentVertex = getVertices().get(i);
            if (currentVertex.getX() > maxX) {
                maxX = currentVertex.getX();
            }
            if (currentVertex.getX() < minX) {
                minX = currentVertex.getX();
            }

            if (currentVertex.getY() > maxY) {
                maxY = currentVertex.getY();
            }
            if (currentVertex.getY() < minY) {
                minY = currentVertex.getY();
            }

            if (currentVertex.getZ() > maxZ) {
                maxZ = currentVertex.getZ();
            }
            if (currentVertex.getZ() < minZ) {
                minZ = currentVertex.getZ();
            }

        }

        return "maxX=" + maxX + " minX=" + minX + " maxY=" + maxY + " minY=" + minY + " maxZ=" + maxZ + " minZ=" + minZ;
    }

    public void printBoudariesText() {

        System.out.println(getBoudariesText());

    }
}

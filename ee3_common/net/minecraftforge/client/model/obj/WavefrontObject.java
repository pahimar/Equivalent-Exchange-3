package net.minecraftforge.client.model.obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;

import com.pahimar.ee3.core.helper.LogHelper;

import net.minecraftforge.client.model.obj.parser.LineParser;
import net.minecraftforge.client.model.obj.parser.ObjLineParserFactory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WavefrontObject {

    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Vertex> vertexNormals = new ArrayList<Vertex>();
    private ArrayList<TextureCoordinate> textureCoordinates = new ArrayList<TextureCoordinate>();
    private ArrayList<Group> groups = new ArrayList<Group>();
    private Hashtable<String, Group> groupsDirectAccess = new Hashtable<String, Group>();
    public String fileName;

    private ObjLineParserFactory parserFactory;

    private Group currentGroup;

    public WavefrontObject(String fileName) {

        this.fileName = fileName;
        parseObjModel(fileName);
    }

    public void parseObjModel(String fileName) {

        parseObjModel(this.getClass().getResource(fileName));
    }
    
    public void parseObjModel(URL fileURL) {

        BufferedReader reader = null;
        InputStream inputStream = null;

        parserFactory = new ObjLineParserFactory(this);

        try {
            inputStream = fileURL.openStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                //parseLine(currentLine);

                currentLine = currentLine.replaceAll("\\s+", " ").trim();

                if (currentLine.startsWith("#") || currentLine.length() == 0) {
                    continue;
                }
                else if (currentLine.startsWith("v ")) {
                    LogHelper.log(Level.INFO, "Vertex");    
                }
                else if (currentLine.startsWith("vn ")) {
                    LogHelper.log(Level.INFO, "Vertex Normal");
                }
                else if (currentLine.startsWith("vt ")) {
                    LogHelper.log(Level.INFO, "Texture Coordinate");
                }
                else if (currentLine.startsWith("f ")) {
                    LogHelper.log(Level.INFO, "Face");
                }
                else if (currentLine.startsWith("g ")) {
                    LogHelper.log(Level.INFO, "Group");
                }
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

    public void setTextureCoordinates(ArrayList<TextureCoordinate> textureCoordinates) {

        this.textureCoordinates = textureCoordinates;
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

    public void setVertexNormals(ArrayList<Vertex> vertexNormals) {

        this.vertexNormals = vertexNormals;
    }

    public ArrayList<Vertex> getVertexNormals() {

        return vertexNormals;
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
}

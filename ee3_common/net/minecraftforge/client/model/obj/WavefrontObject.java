package net.minecraftforge.client.model.obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.client.model.obj.parser.LineParser;
import net.minecraftforge.client.model.obj.parser.ObjLineParserFactory;

import com.pahimar.ee3.core.helper.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WavefrontObject {

    private static final String REGEX_FACE_VERTEX_TEXTURECOORD_VERTEXNORMAL = "f(?: ((?:\\d*)(?:/\\d*)(?:/\\d*)))+";
    private static final String REGEX_FACE_VERTEX_TEXTURECOORD = "f(?: ((?:\\d*)(?:/\\d*)))+";
    private static final String REGEX_FACE_VERTEX_VERTEXNORMAL = "f(?: ((?:\\d*)(?://\\d*)))+";
    private static final String REGEX_FACE_VERTEX = "f(?: ((?:\\d*)))+";
    
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
                    Vertex vertex = parseVertex(currentLine);
                    if (vertex != null) {
                        vertices.add(vertex);
                    }
                }
                else if (currentLine.startsWith("vn ")) {
                    Vertex vertex = parseVertexNormal(currentLine);
                    if (vertex != null) {
                        vertexNormals.add(vertex);
                    }
                }
                else if (currentLine.startsWith("vt ")) {
                    TextureCoordinate textureCoordinate = parseTextureCoordinate(currentLine);
                    if (textureCoordinate != null) {
                        textureCoordinates.add(textureCoordinate);
                    }
                }
                else if (currentLine.startsWith("f ")) {
                    Face face = parseFace(currentLine);
                    if (face != null) {
                        if (currentGroup == null) {
                            currentGroup = new Group("Default");
                        }

                        currentGroup.faces.add(face);
                    }
                }
                else if (currentLine.startsWith("g ")) {
                    
                }
            }
            
            groups.add(currentGroup);
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
        
        int faceSum = 0;
        for (Group group : groups) {
            faceSum += group.faces.size();
        }
        
        LogHelper.log(Level.INFO, "filename: " + fileName);
        LogHelper.log(Level.INFO, "vertex count: " + vertices.size());
        LogHelper.log(Level.INFO, "vertex normal count: " + vertexNormals.size());
        LogHelper.log(Level.INFO, "texture coordinate count: " + textureCoordinates.size());
        LogHelper.log(Level.INFO, "face count: " + faceSum);
        LogHelper.log(Level.INFO, "group count: " + groups.size());
    }
    
    private Vertex parseVertex(String line) {
        Vertex vertex = null;
        
        line = line.substring(line.indexOf(" ") + 1);
        String[] tokens = line.split(" ");
        
        try {
            if (tokens.length == 3) {
                return new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        return vertex;
    }
    
    private Vertex parseVertexNormal(String line) {
        
        return parseVertex(line);
    }
    
    private TextureCoordinate parseTextureCoordinate(String line) {
        TextureCoordinate textureCoordinate = null;
        
        line = line.substring(line.indexOf(" ") + 1);
        String[] tokens = line.split(" ");
        
        try {
            if (tokens.length == 2) {
                return new TextureCoordinate(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]));
            }
            else if (tokens.length == 3) {
                return new TextureCoordinate(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        return textureCoordinate;
    }
    
    private Face parseFace(String line) {
        Face face = null;
        
        if (validateFaceLine(line)) {
            face = new Face();
            
            String trimmedLine = line.substring(line.indexOf(" ") + 1);
            String[] tokens = trimmedLine.split(" ");
            String[] subTokens = null;
            
            if (tokens.length == 3) {
                face.glDrawingMode = GL11.GL_TRIANGLES;
            }
            else if (tokens.length == 4) {
                face.glDrawingMode = GL11.GL_QUADS;
            }
            else {
                
            }
            
            face.vertices = new Vertex[tokens.length];
            face.textureCoordinates = new TextureCoordinate[tokens.length];
            face.vertexNormals = new Vertex[tokens.length];
    
            // f v1/vt1/vn1 v2/vt2/vn2 v3/vt3/vn3 ...
            if (line.matches(REGEX_FACE_VERTEX_TEXTURECOORD_VERTEXNORMAL)) {
                for (int i = 0; i < tokens.length; ++i) {
                    subTokens = tokens[i].split("/");
                    
                    face.vertices[i] = vertices.get(Integer.parseInt(subTokens[0]) - 1);
                    face.textureCoordinates[i] = textureCoordinates.get(Integer.parseInt(subTokens[1]) - 1);
                    face.vertexNormals[i] = vertexNormals.get(Integer.parseInt(subTokens[2]) - 1);
                }
    
            }
            // f v1/vt1 v2/vt2 v3/vt3 ...
            else if (line.matches(REGEX_FACE_VERTEX_TEXTURECOORD)) {
                // @TODO Finish
            }
            // f v1//vn1 v2//vn2 v3//vn3 ...
            else if (line.matches(REGEX_FACE_VERTEX_VERTEXNORMAL)) {
             // @TODO Finish
            }
            // f v1 v2 v3 ...
            else if (line.matches(REGEX_FACE_VERTEX)) {
             // @TODO Finish
            }
            else {
                throw new IllegalArgumentException();
            }
        }
                
        return face;
    }
    
    private static boolean validateFaceLine(String faceLine) {
        
        return (faceLine.matches(REGEX_FACE_VERTEX_TEXTURECOORD_VERTEXNORMAL) ||
                faceLine.matches(REGEX_FACE_VERTEX_TEXTURECOORD) ||
                faceLine.matches(REGEX_FACE_VERTEX_VERTEXNORMAL) ||
                faceLine.matches(REGEX_FACE_VERTEX));
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

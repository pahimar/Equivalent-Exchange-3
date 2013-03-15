package net.minecraftforge.client.model.obj.parser;

import net.minecraftforge.client.model.obj.Vertex;
import net.minecraftforge.client.model.obj.WavefrontObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NormalParser extends LineParser {

    Vertex vertex = null;

    public NormalParser() {

        // TODO Auto-generated constructor stub
    }

    @Override
    public void parse() {

        vertex = new Vertex();

        try {
            vertex.setX(Float.parseFloat(words[1]));
            vertex.setY(Float.parseFloat(words[2]));
            vertex.setZ(Float.parseFloat(words[3]));
        }
        catch (Exception e) {
            throw new RuntimeException("NormalParser Error");
        }

    }

    @Override
    public void incoporateResults(WavefrontObject wavefrontObject) {

        wavefrontObject.getNormals().add(vertex);

    }

}

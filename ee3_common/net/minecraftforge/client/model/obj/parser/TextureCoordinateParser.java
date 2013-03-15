package net.minecraftforge.client.model.obj.parser;

import net.minecraftforge.client.model.obj.TextureCoordinate;
import net.minecraftforge.client.model.obj.WavefrontObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextureCoordinateParser extends LineParser {

    private TextureCoordinate coordinate = null;

    public TextureCoordinateParser() {

    }

    @Override
    public void parse() {

        coordinate = new TextureCoordinate();
        try {
            if (words.length >= 2) {
                coordinate.u = (Float.parseFloat(words[1]));
            }

            if (words.length >= 3) {
                coordinate.v = (1 - Float.parseFloat(words[2])); // OBJ origin is at upper left, OpenGL origin is	 at lower left.
            }

            if (words.length >= 4) {
                coordinate.w = (Float.parseFloat(words[3]));
            }

        }
        catch (Exception e) {
            throw new RuntimeException("TextureParser Error");
        }
    }

    @Override
    public void incoporateResults(WavefrontObject wavefrontObject) {

        wavefrontObject.getTextureCoordinates().add(coordinate);
    }
}

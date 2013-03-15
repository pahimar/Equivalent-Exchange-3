package net.minecraftforge.client.model.obj.parser;

import net.minecraftforge.client.model.obj.WavefrontObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ObjLineParserFactory extends LineParserFactory {

    public ObjLineParserFactory(WavefrontObject object) {

        this.object = object;
        parsers.put("v", new VertexParser());
        parsers.put("vn", new NormalParser());
        parsers.put("vt", new TextureCoordinateParser());
        parsers.put("f", new FaceParser(object));
        parsers.put("g", new GroupParser());
    }

}

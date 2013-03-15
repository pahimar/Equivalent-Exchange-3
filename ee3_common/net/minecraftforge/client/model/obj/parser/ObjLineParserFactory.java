package net.minecraftforge.client.model.obj.parser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.obj.WavefrontObject;

@SideOnly(Side.CLIENT)
public class ObjLineParserFactory extends LineParserFactory {

    public ObjLineParserFactory(WavefrontObject object) {

        this.object = object;
        parsers.put("v", new VertexParser());
        parsers.put("vn", new NormalParser());
        parsers.put("vp", new FreeFormParser());
        parsers.put("vt", new TextureCooParser());
        parsers.put("f", new FaceParser(object));
        parsers.put("#", new CommentParser());
        parsers.put("g", new GroupParser());
    }

}

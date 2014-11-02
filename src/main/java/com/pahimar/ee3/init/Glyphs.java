package com.pahimar.ee3.init;

import com.pahimar.ee3.api.Glyph;
import com.pahimar.ee3.array.GlyphTextureRegistry;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;

public class Glyphs
{
    private static final String SYMBOL_TEXTURE_LOCATION = "textures/glyphs/";

    public static final Glyph BASE_CIRCLE = new Glyph(Textures.Glyph.BASE_CIRCLE, Names.Glyphs.BASE_CIRCLE);
    public static final Glyph DOT = new Glyph(Textures.Glyph.DOT, Names.Glyphs.DOT);
    public static final Glyph LINE = new Glyph(Textures.Glyph.LINE, Names.Glyphs.LINE);
    public static final Glyph CIRCLE = new Glyph(Textures.Glyph.CIRCLE, Names.Glyphs.CIRCLE);
    public static final Glyph TRIANGLE = new Glyph(Textures.Glyph.TRIANGLE, Names.Glyphs.TRIANGLE);
    public static final Glyph SQUARE = new Glyph(Textures.Glyph.SQUARE, Names.Glyphs.SQUARE);
    public static final Glyph DIAMOND = new Glyph(Textures.Glyph.DIAMOND, Names.Glyphs.DIAMOND);
    public static final Glyph PENTAGON = new Glyph(Textures.Glyph.PENTAGON, Names.Glyphs.PENTAGON);
    public static final Glyph HEXAGON = new Glyph(Textures.Glyph.HEXAGON, Names.Glyphs.HEXAGON);
    public static final Glyph HEPTAGON = new Glyph(Textures.Glyph.HEPTAGON, Names.Glyphs.HEPTAGON);
    public static final Glyph OCTAGON = new Glyph(Textures.Glyph.OCTAGON, Names.Glyphs.OCTAGON);

    public static void init()
    {
        GlyphTextureRegistry.getInstance().registerGlyph(BASE_CIRCLE);
        GlyphTextureRegistry.getInstance().registerGlyph(DOT);
        GlyphTextureRegistry.getInstance().registerGlyph(LINE);
        GlyphTextureRegistry.getInstance().registerGlyph(CIRCLE);
        GlyphTextureRegistry.getInstance().registerGlyph(TRIANGLE);
        GlyphTextureRegistry.getInstance().registerGlyph(SQUARE);
        GlyphTextureRegistry.getInstance().registerGlyph(DIAMOND);
        GlyphTextureRegistry.getInstance().registerGlyph(PENTAGON);
        GlyphTextureRegistry.getInstance().registerGlyph(HEXAGON);
        GlyphTextureRegistry.getInstance().registerGlyph(HEPTAGON);
        GlyphTextureRegistry.getInstance().registerGlyph(OCTAGON);
    }
}

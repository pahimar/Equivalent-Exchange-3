package com.pahimar.ee3.init;

import com.pahimar.ee3.api.Glyph;
import com.pahimar.ee3.api.GlyphRegistryProxy;

public class Glyphs
{
    private static final String SYMBOL_TEXTURE_LOCATION = "textures/array/";

    public static final Glyph BASE_CIRCLE = new Glyph(SYMBOL_TEXTURE_LOCATION + "transBaseCircle.png", "");
    public static final Glyph DOT = new Glyph(SYMBOL_TEXTURE_LOCATION + "transDot.png", "");
    public static final Glyph LINE = new Glyph(SYMBOL_TEXTURE_LOCATION + "transLine.png", "");
    public static final Glyph CIRCLE = new Glyph(SYMBOL_TEXTURE_LOCATION + "transCircle.png", "");
    public static final Glyph TRIANGLE = new Glyph(SYMBOL_TEXTURE_LOCATION + "transTriangle.png", "");
    public static final Glyph INVERTED_TRIANGLE = new Glyph(SYMBOL_TEXTURE_LOCATION + "transInvertedTriangle.png", "");
    public static final Glyph SQUARE = new Glyph(SYMBOL_TEXTURE_LOCATION + "transSquare.png", "");
    public static final Glyph DIAMOND = new Glyph(SYMBOL_TEXTURE_LOCATION + "transDiamond.png", "");
    public static final Glyph PENTAGON = new Glyph(SYMBOL_TEXTURE_LOCATION + "transPentagon.png", "");
    public static final Glyph HEXAGON = new Glyph(SYMBOL_TEXTURE_LOCATION + "transHexagon.png", "");
    public static final Glyph HEPTGON = new Glyph(SYMBOL_TEXTURE_LOCATION + "transHeptagon.png", "");
    public static final Glyph OCTAGON = new Glyph(SYMBOL_TEXTURE_LOCATION + "transOctagon.png", "");

    public static void init()
    {
        GlyphRegistryProxy.addGlyph(BASE_CIRCLE);
        GlyphRegistryProxy.addGlyph(DOT);
        GlyphRegistryProxy.addGlyph(LINE);
        GlyphRegistryProxy.addGlyph(CIRCLE);
        GlyphRegistryProxy.addGlyph(TRIANGLE);
        GlyphRegistryProxy.addGlyph(INVERTED_TRIANGLE);
        GlyphRegistryProxy.addGlyph(SQUARE);
        GlyphRegistryProxy.addGlyph(DIAMOND);
        GlyphRegistryProxy.addGlyph(PENTAGON);
        GlyphRegistryProxy.addGlyph(HEXAGON);
        GlyphRegistryProxy.addGlyph(HEPTGON);
        GlyphRegistryProxy.addGlyph(OCTAGON);
    }
}

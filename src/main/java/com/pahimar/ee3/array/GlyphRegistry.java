package com.pahimar.ee3.array;

import com.google.common.collect.ImmutableSet;
import com.pahimar.ee3.api.Glyph;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class GlyphRegistry
{
    private static GlyphRegistry glyphRegistry = null;
    private SortedSet<Glyph> glyphSortedSet;

    private GlyphRegistry()
    {
    }

    public static GlyphRegistry getInstance()
    {
        if (glyphRegistry == null)
        {
            glyphRegistry = new GlyphRegistry();
            glyphRegistry.init();
        }

        return glyphRegistry;
    }

    private void init()
    {
        glyphSortedSet = new TreeSet<Glyph>();
    }

    public void addGlyph(Glyph glyph)
    {
        glyphSortedSet.add(new Glyph(glyph, 1));
    }

    public Set<Glyph> getGlyphs()
    {
        return ImmutableSet.copyOf(glyphSortedSet);
    }
}

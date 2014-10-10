package com.pahimar.ee3.array;

import com.google.common.collect.ImmutableMap;
import com.pahimar.ee3.api.Glyph;
import net.minecraft.util.ResourceLocation;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class GlyphTextureRegistry
{
    private static GlyphTextureRegistry glyphTextureRegistry = null;
    private SortedMap<ResourceLocation, String> glyphTextureSortedMap;

    private GlyphTextureRegistry()
    {
    }

    public static GlyphTextureRegistry getInstance()
    {
        if (glyphTextureRegistry == null)
        {
            glyphTextureRegistry = new GlyphTextureRegistry();
            glyphTextureRegistry.init();
        }

        return glyphTextureRegistry;
    }

    private void init()
    {
        glyphTextureSortedMap = new TreeMap<ResourceLocation, String>(comparator);
    }

    public void addGlyph(Glyph glyph)
    {
        if (glyph.getTexture() != null)
        {
            glyphTextureSortedMap.put(glyph.getTexture(), glyph.getUnLocalizedName());
        }
    }

    public void addGlyph(ResourceLocation glyphTexture, String unLocalizedName)
    {
        if (glyphTexture != null)
        {
            glyphTextureSortedMap.put(glyphTexture, unLocalizedName);
        }
    }

    public ResourceLocation getResourceLocation(int index)
    {
        if (index >= glyphTextureSortedMap.size() || index < 0)
        {
            return null;
        }

        ResourceLocation[] glyphTextures = glyphTextureSortedMap.keySet().toArray(new ResourceLocation[]{});

        return glyphTextures[index];
    }

    public Map<ResourceLocation, String> getGlyphs()
    {
        return ImmutableMap.copyOf(glyphTextureSortedMap);
    }

    private static Comparator<ResourceLocation> comparator = new Comparator<ResourceLocation>()
    {
        @Override
        public int compare(ResourceLocation resourceLocation1, ResourceLocation resourceLocation2)
        {
            if (resourceLocation1.getResourceDomain().equalsIgnoreCase(resourceLocation2.getResourceDomain()))
            {
                return resourceLocation1.getResourcePath().compareToIgnoreCase(resourceLocation2.getResourcePath());
            }
            else
            {
                return resourceLocation1.getResourceDomain().compareToIgnoreCase(resourceLocation2.getResourceDomain());
            }
        }
    };
}

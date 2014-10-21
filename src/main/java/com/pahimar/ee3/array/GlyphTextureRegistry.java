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
    private SortedMap<ResourceLocation, String> registeredGlyphTextures;

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
        registeredGlyphTextures = new TreeMap<ResourceLocation, String>(comparator);
    }

    public void registerGlyph(Glyph glyph)
    {
        if (glyph.getTexture() != null)
        {
            registeredGlyphTextures.put(glyph.getTexture(), glyph.getUnLocalizedName());
        }
    }

    public void registerGlyph(ResourceLocation glyphTexture, String unLocalizedName)
    {
        if (glyphTexture != null)
        {
            registeredGlyphTextures.put(glyphTexture, unLocalizedName);
        }
    }

    public ResourceLocation getRegisteredGlyphAt(int index)
    {
        if (index >= registeredGlyphTextures.size() || index < 0)
        {
            return null;
        }

        ResourceLocation[] registeredGlyphTextures = this.registeredGlyphTextures.keySet().toArray(new ResourceLocation[]{});

        return registeredGlyphTextures[index];
    }

    public Map<ResourceLocation, String> getRegisteredGlyphTextures()
    {
        return ImmutableMap.copyOf(registeredGlyphTextures);
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

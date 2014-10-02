package com.pahimar.ee3.array;

import com.pahimar.ee3.util.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Glyph
{
    private final ResourceLocation texture;
    private final int size;

    public Glyph(String texture)
    {
        this(texture, 1);
    }

    public Glyph(String texture, int size)
    {
        this(ResourceLocationHelper.getResourceLocation(texture), size);
    }

    public Glyph(ResourceLocation texture, int size)
    {
        this.texture = texture;
        this.size = size;
    }

    public ResourceLocation getTexture()
    {
        return texture;
    }

    public int getSize()
    {
        return size;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Glyph)
        {
            return this.texture.equals(((Glyph) object).getTexture()) && this.size == ((Glyph) object).size;
        }

        return false;
    }
}

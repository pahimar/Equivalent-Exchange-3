package com.pahimar.ee3.alchemy;

import com.pahimar.ee3.util.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Symbol
{
    private final ResourceLocation texture;
    private final int size;

    public Symbol(String texture)
    {
        this(texture, 1);
    }

    public Symbol(String texture, int size)
    {
        this(ResourceLocationHelper.getResourceLocation(texture), size);
    }

    public Symbol(ResourceLocation texture, int size)
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
        if (object instanceof Symbol)
        {
            return this.texture.equals(((Symbol) object).getTexture()) && this.size == ((Symbol) object).size;
        }

        return false;
    }
}

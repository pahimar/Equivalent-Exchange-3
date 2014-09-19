package com.pahimar.ee3.alchemy;

import com.pahimar.ee3.util.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Symbol
{
    private final ResourceLocation texture;
    private final float size;

    public Symbol(String texture, float size)
    {
        this(ResourceLocationHelper.getResourceLocation(texture), size);
    }

    public Symbol(ResourceLocation texture, float size)
    {
        this.texture = texture;
        this.size = size;
    }

    public ResourceLocation getTexture()
    {
        return texture;
    }

    public float getSize()
    {
        return size;
    }
}

package com.pahimar.ee3.api;

import com.pahimar.ee3.util.ResourceLocationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class Glyph implements Comparable<Glyph>
{
    private ResourceLocation texture;
    private String unLocalizedName;
    private int size;

    private Glyph()
    {

    }

    public Glyph(String texture, String unLocalizedName)
    {
        this(texture, unLocalizedName, 1);
    }

    public Glyph(String texture, String unLocalizedName, int size)
    {
        this(ResourceLocationHelper.getResourceLocation(texture), unLocalizedName, size);
    }

    public Glyph(ResourceLocation texture, String unLocalizedName, int size)
    {
        this.texture = texture;
        this.unLocalizedName = unLocalizedName;
        this.size = size;
    }

    public Glyph(Glyph glyph, int size)
    {
        this.texture = glyph.texture;
        this.unLocalizedName = glyph.unLocalizedName;
        this.size = size;
    }

    public ResourceLocation getTexture()
    {
        return texture;
    }

    public String getUnLocalizedName()
    {
        return unLocalizedName;
    }

    public int getSize()
    {
        return size;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey("textureDomain") && nbtTagCompound.hasKey("texturePath") && nbtTagCompound.hasKey("unLocalizedName") && nbtTagCompound.hasKey("size"))
        {
            this.texture = new ResourceLocation(nbtTagCompound.getString("textureDomain"), nbtTagCompound.getString("texturePath"));
            this.unLocalizedName = nbtTagCompound.getString("unLocalizedName");
            this.size = nbtTagCompound.getInteger("size");
        }
        else
        {
            this.texture = new ResourceLocation("");
            this.unLocalizedName = "";
            this.size = 0;
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setString("textureDomain", texture.getResourceDomain());
        nbtTagCompound.setString("texturePath", texture.getResourcePath());
        nbtTagCompound.setString("unLocalizedName", unLocalizedName);
        nbtTagCompound.setInteger("size", size);
    }

    public static Glyph readGlyphFromNBT(NBTTagCompound nbtTagCompound)
    {
        Glyph glyph = new Glyph();
        glyph.readFromNBT(nbtTagCompound);
        return glyph;
    }

    @Override
    public String toString()
    {
        return String.format("texture: %s, unLocalizedName: %s, size: %s", texture.getResourcePath() + ":" + texture.getResourcePath(), unLocalizedName, size);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Glyph)
        {
            return this.compareTo((Glyph) object) == 0;
        }

        return false;
    }

    @Override
    public int compareTo(Glyph glyph)
    {
        if (this.texture.getResourceDomain().equalsIgnoreCase(glyph.getTexture().getResourceDomain()))
        {
            if (this.texture.getResourcePath().equalsIgnoreCase(glyph.getTexture().getResourcePath()))
            {
                return this.size - glyph.size;
            }
            else
            {
                return this.texture.getResourcePath().compareToIgnoreCase(glyph.getTexture().getResourcePath());
            }
        }
        else
        {
            return this.texture.getResourceDomain().compareToIgnoreCase(glyph.getTexture().getResourceDomain());
        }
    }
}

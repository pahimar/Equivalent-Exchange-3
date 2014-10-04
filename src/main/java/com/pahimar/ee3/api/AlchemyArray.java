package com.pahimar.ee3.api;

import com.google.common.collect.ImmutableSortedSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class AlchemyArray
{
    private SortedSet<Glyph> glyphs;
    private int largestGlyphSize;

    public AlchemyArray()
    {
        glyphs = new TreeSet<Glyph>();
        largestGlyphSize = 0;
    }

    public AlchemyArray(Collection<Glyph> glyphs)
    {
        this.glyphs = new TreeSet<Glyph>(glyphs);
        largestGlyphSize = 0;

        for (Glyph glyph : glyphs)
        {
            if (glyph.getSize() > largestGlyphSize)
            {
                largestGlyphSize = glyph.getSize();
            }
        }
    }

    public void addGlyph(Glyph glyph)
    {
        if (glyph.getSize() > largestGlyphSize)
        {
            largestGlyphSize = glyph.getSize();
        }

        glyphs.add(glyph);
    }

    public void addGlyph(Glyph glyph, int size)
    {
        if (size > largestGlyphSize)
        {
            largestGlyphSize = size;
        }

        glyphs.add(new Glyph(glyph, size));
    }

    public Set<Glyph> getGlyphs()
    {
        return ImmutableSortedSet.copyOf(glyphs);
    }

    public int getLargestGlyphSize()
    {
        return largestGlyphSize;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey("glyphs"))
        {
            // Read in the ItemStacks in the inventory from NBT
            if (nbtTagCompound.hasKey("glyphs"))
            {
                NBTTagList tagList = nbtTagCompound.getTagList("glyphs", 10);
                glyphs = new TreeSet<Glyph>();
                largestGlyphSize = 0;
                for (int i = 0; i < tagList.tagCount(); ++i)
                {
                    NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                    Glyph glyph = Glyph.readGlyphFromNBT(tagCompound);
                    glyphs.add(glyph);
                    if (glyph.getSize() > largestGlyphSize)
                    {
                        largestGlyphSize = glyph.getSize();
                    }
                }
            }
            else
            {
                glyphs = new TreeSet<Glyph>();
                largestGlyphSize = 0;
            }
        }
        else
        {
            glyphs = new TreeSet<Glyph>();
            largestGlyphSize = 0;
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        NBTTagList tagList = new NBTTagList();
        for (Glyph glyph : glyphs)
        {
            NBTTagCompound tagCompound = new NBTTagCompound();
            glyph.writeToNBT(tagCompound);
            tagList.appendTag(tagCompound);
        }
        nbtTagCompound.setTag("glyphs", tagList);
        nbtTagCompound.setInteger("largestGlyphSize", largestGlyphSize);
    }

    public static AlchemyArray readAlchemyArrayFromNBT(NBTTagCompound nbtTagCompound)
    {
        AlchemyArray alchemyArray = new AlchemyArray();
        alchemyArray.readFromNBT(nbtTagCompound);
        return alchemyArray;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (Glyph glyph : glyphs)
        {
            stringBuilder.append(glyph.toString() + ", ");
        }

        return stringBuilder.toString();
    }
}

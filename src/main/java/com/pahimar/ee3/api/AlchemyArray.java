package com.pahimar.ee3.api;

import com.google.common.collect.ImmutableSortedSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class AlchemyArray implements Comparable<AlchemyArray>
{
    private SortedSet<Glyph> glyphs;

    public AlchemyArray()
    {
        glyphs = new TreeSet<Glyph>();
    }

    public AlchemyArray(Collection<Glyph> glyphs)
    {
        this.glyphs = new TreeSet<Glyph>(glyphs);
    }

    public Set<Glyph> getGlyphs()
    {
        return ImmutableSortedSet.copyOf(glyphs);
    }

    public void onAlchemyArrayActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {

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

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof AlchemyArray)
        {
            return this.compareTo((AlchemyArray) object) == 0;
        }

        return false;
    }

    @Override
    public int compareTo(AlchemyArray alchemyArray)
    {
        if (this.glyphs.size() == alchemyArray.glyphs.size())
        {
            for (Glyph glyph : this.glyphs)
            {
                if (!alchemyArray.glyphs.contains(glyph))
                {
                    return -1;
                }
            }

            return 0;
        }
        else
        {
            return this.glyphs.size() - alchemyArray.glyphs.size();
        }
    }
}

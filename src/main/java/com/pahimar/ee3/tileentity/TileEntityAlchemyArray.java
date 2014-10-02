package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.array.Glyph;
import com.pahimar.ee3.array.Symbols;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileEntityAlchemyArray extends TileEntityEE
{
    private List<Glyph> glyphs;
    private int largestSymbolSize;

    public TileEntityAlchemyArray()
    {
        super();
        glyphs = new ArrayList<Glyph>(Arrays.asList(Symbols.BASE_CIRCLE, Symbols.TRIANGLE));
        this.largestSymbolSize = 1;
    }

    public List<Glyph> getGlyphs()
    {
        return glyphs;
    }

    public int getLargestSymbolSize()
    {
        return largestSymbolSize;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return AxisAlignedBB.getBoundingBox(xCoord - largestSymbolSize, yCoord, zCoord - largestSymbolSize, xCoord + largestSymbolSize, yCoord, zCoord + largestSymbolSize);
    }
}

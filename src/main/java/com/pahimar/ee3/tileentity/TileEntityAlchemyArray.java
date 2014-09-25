package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.alchemy.Symbol;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;

public class TileEntityAlchemyArray extends TileEntityEE
{
    private List<Symbol> symbols;
    private int largestSymbolSize;

    public TileEntityAlchemyArray()
    {
        super();
        symbols = new ArrayList<Symbol>();
        this.largestSymbolSize = 0;
    }

    public List<Symbol> getSymbols()
    {
        return symbols;
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

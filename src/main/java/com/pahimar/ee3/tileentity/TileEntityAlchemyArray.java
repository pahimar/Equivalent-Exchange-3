package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.alchemy.Symbol;
import com.pahimar.ee3.alchemy.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileEntityAlchemyArray extends TileEntityEE
{
    private List<Symbol> alchemySymbols;

    public TileEntityAlchemyArray()
    {
        super();
        alchemySymbols = new ArrayList<Symbol>(Arrays.asList(Symbols.CIRCLE));
    }

    public List<Symbol> getAlchemySymbols()
    {
        return alchemySymbols;
    }
}

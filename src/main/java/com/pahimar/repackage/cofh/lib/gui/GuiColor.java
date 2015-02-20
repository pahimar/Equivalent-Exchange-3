package com.pahimar.repackage.cofh.lib.gui;

public class GuiColor extends Number
{

    private static final long serialVersionUID = 7024827242888861187L;
    private final int _color;

    public GuiColor(int argb)
    {

        _color = argb;
    }

    public GuiColor(int rgba, Void dummy)
    {

        this(rgba >>> 24, rgba >> 16, rgba >> 8, rgba);
    }

    public GuiColor(byte alpha, int argb)
    {

        this(argb >> 16, argb >> 8, argb, alpha);
    }

    public GuiColor(int rgba, byte alpha)
    {

        this(rgba >>> 24, rgba >> 16, rgba >> 8, alpha);
    }

    public GuiColor(int r, int g, int b)
    {

        this(r, g, b, 255);
    }

    public GuiColor(int r, int g, int b, int a)
    {

        _color = (b & 0xFF) | (g & 0xFF) << 8 | (r & 0xFF) << 16 | (a & 0xFF) << 24;
    }

    public int getColor()
    {

        return _color;
    }

    public int getIntR()
    {

        return (_color >> 16) & 0xFF;
    }

    public int getIntG()
    {

        return (_color >> 8) & 0xFF;
    }

    public int getIntB()
    {

        return (_color >> 0) & 0xFF;
    }

    public int getIntA()
    {

        return (_color >> 24) & 0xFF;
    }

    public float getFloatR()
    {

        return getIntR() / 255f;
    }

    public float getFloatG()
    {

        return getIntG() / 255f;
    }

    public float getFloatB()
    {

        return getIntB() / 255f;
    }

    public float getFloatA()
    {

        return getIntA() / 255f;
    }

    @Override
    public int intValue()
    {

        return getColor();
    }

    @Override
    public long longValue()
    {

        return getColor();
    }

    @Override
    public float floatValue()
    {

        return getColor();
    }

    @Override
    public double doubleValue()
    {

        return getColor();
    }

}

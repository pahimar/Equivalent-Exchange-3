package com.pahimar.repackage.cofh.lib.gui;

public class GuiColor {

    private final int _color;

    public GuiColor(int color) {

        _color = color;
    }

    public GuiColor(int r, int g, int b) {

        this(r, g, b, 255);
    }

    public GuiColor(int r, int g, int b, int a) {

        _color = (b & 0xFF) | (g & 0xFF) << 8 | (r & 0xFF) << 16 | (a & 0xFF) << 24;
    }

    public int getColor() {

        return _color;
    }

    public int getIntR() {

        return (_color >> 16) & 0xFF;
    }

    public int getIntG() {

        return (_color >> 8) & 0xFF;
    }

    public int getIntB() {

        return (_color >> 0) & 0xFF;
    }

    public int getIntA() {

        return (_color >> 24) & 0xFF;
    }

    public float getFloatR() {

        return ((_color >> 16) & 0xFF) / 0xFF;
    }

    public float getFloatG() {

        return ((_color >> 8) & 0xFF) / 0xFF;
    }

    public float getFloatB() {

        return ((_color >> 0) & 0xFF) / 0xFF;
    }

    public float getFloatA() {

        return ((_color >> 24) & 0xFF) / 0xFF;
    }

}

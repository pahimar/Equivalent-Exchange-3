package com.pahimar.ee3.client.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.element.ElementButton;
import net.minecraft.util.ResourceLocation;

public class ElementStatefulButton extends ElementButton
{
    private int state;

    public ElementStatefulButton(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int disabledX, int disabledY, int sizeX, int sizeY, int texW, int texH, ResourceLocation texture)
    {
        super(gui, posX, posY, name, sheetX, sheetY, hoverX, hoverY, disabledX, disabledY, sizeX, sizeY, texW, texH, texture);
        this.state = 0;
    }

    public int getState()
    {
        return state;
    }

    public ElementStatefulButton setState(int state)
    {
        this.state = state;
        return this;
    }
}

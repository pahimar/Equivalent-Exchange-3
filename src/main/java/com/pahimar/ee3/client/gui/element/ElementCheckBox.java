package com.pahimar.ee3.client.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.element.ElementButton;
import net.minecraft.util.ResourceLocation;

public class ElementCheckBox extends ElementButton
{
    private boolean isChecked;

    public ElementCheckBox(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int disabledX, int disabledY, int sizeX, int sizeY, int texW, int texH, ResourceLocation texture)
    {
        super(gui, posX, posY, name, sheetX, sheetY, hoverX, hoverY, disabledX, disabledY, sizeX, sizeY, texW, texH, texture);
        this.isChecked = false;

        // TODO Standardize size and textures - should only ever need to know gui, name, and positioning
    }

    public boolean isChecked()
    {
        return isChecked;
    }

    public ElementCheckBox check()
    {
        return setIsChecked(true);
    }

    public ElementCheckBox unCheck()
    {
        return setIsChecked(false);
    }

    public ElementCheckBox setIsChecked(boolean isChecked)
    {
        this.isChecked = isChecked;
        return this;
    }
}

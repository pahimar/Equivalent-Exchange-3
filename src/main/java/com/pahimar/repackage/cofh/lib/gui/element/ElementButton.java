package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.ee3.util.ResourceLocationHelper;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.render.RenderHelper;
import com.pahimar.repackage.cofh.lib.util.helpers.StringHelper;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ElementButton extends ElementBase
{

    int sheetX;
    int sheetY;
    int hoverX;
    int hoverY;
    int disabledX = 0;
    int disabledY = 0;
    boolean tooltipLocalized = false;
    String tooltip;

    public ElementButton(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int sizeX, int sizeY, String texture) {
        this(gui, posX, posY, name, sheetX, sheetY, hoverX, hoverY, sizeX, sizeY, 256, 256, texture);
    }

    public ElementButton(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int sizeX, int sizeY, ResourceLocation texture) {
        this(gui, posX, posY, name, sheetX, sheetY, hoverX, hoverY, sizeX, sizeY, 256, 256, texture);
    }

    public ElementButton(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int sizeX, int sizeY, int texW, int texH, String texture) {
        this(gui, posX, posY, name, sheetX, sheetY, hoverX, hoverY, sizeX, sizeY, texW, texH, ResourceLocationHelper.getResourceLocation(texture));
    }

    public ElementButton(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int sizeX, int sizeY, int texW, int texH, ResourceLocation texture) {
        super(gui, posX, posY);
        setName(name);
        setSize(sizeX, sizeY);
        setTexture(texture, texW, texH);
        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.hoverX = hoverX;
        this.hoverY = hoverY;
    }

    public ElementButton(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int disabledX, int disabledY, int sizeX, int sizeY, int texW, int texH, String texture) {
        this(gui, posX, posY, name, sheetX, sheetY, hoverX, hoverY, disabledX, disabledY, sizeX, sizeY, texW, texH, ResourceLocationHelper.getResourceLocation(texture));
    }

    public ElementButton(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int disabledX, int disabledY, int sizeX, int sizeY, int texW, int texH, ResourceLocation texture) {
        super(gui, posX, posY);
        setName(name);
        setSize(sizeX, sizeY);
        setTexture(texture, texW, texH);
        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.hoverX = hoverX;
        this.hoverY = hoverY;
        this.disabledX = disabledX;
        this.disabledY = disabledY;
    }

    public ElementButton clearToolTip()
    {

        this.tooltip = null;
        return this;
    }

    public ElementButton setToolTip(String tooltip)
    {

        this.tooltip = tooltip;
        return this;
    }

    public ElementButton setToolTipLocalized(boolean localized)
    {

        this.tooltipLocalized = localized;
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks)
    {

        RenderHelper.bindTexture(texture);
        if (isEnabled())
        {
            if (intersectsWith(mouseX, mouseY))
            {

                drawTexturedModalRect(posX, posY, hoverX, hoverY, sizeX, sizeY);
            }
            else
            {
                drawTexturedModalRect(posX, posY, sheetX, sheetY, sizeX, sizeY);
            }
        }
        else
        {
            drawTexturedModalRect(posX, posY, disabledX, disabledY, sizeX, sizeY);
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY)
    {

    }

    @Override
    public void addTooltip(List<String> list)
    {

        if (tooltip != null)
        {
            if (tooltipLocalized)
            {
                list.add(tooltip);
            }
            else
            {
                list.add(StringHelper.localize(tooltip));
            }
        }
    }

    @Override
    public boolean onMousePressed(int x, int y, int mouseButton)
    {

        if (isEnabled())
        {
            gui.handleElementButtonClick(getName(), mouseButton);
            return true;
        }
        return false;
    }

    public void setSheetX(int pos)
    {

        sheetX = pos;
    }

    public void setSheetY(int pos)
    {

        sheetY = pos;
    }

    public void setHoverX(int pos)
    {

        hoverX = pos;
    }

    public void setHoverY(int pos)
    {

        hoverY = pos;
    }

    public ElementButton setDisabledX(int pos)
    {

        disabledX = pos;
        return this;
    }

    public ElementButton setDisabledY(int pos)
    {

        disabledY = pos;
        return this;
    }

    public void setActive()
    {

        setEnabled(true);
    }

    public void setDisabled()
    {

        setEnabled(false);
    }

}

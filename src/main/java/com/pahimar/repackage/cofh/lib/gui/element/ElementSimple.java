package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.render.RenderHelper;

/**
 * Basic element which can render an arbitrary texture.
 *
 * @author King Lemming
 */
public class ElementSimple extends ElementBase
{

    int texU = 0;
    int texV = 0;

    public ElementSimple(GuiBase gui, int posX, int posY)
    {

        super(gui, posX, posY);
    }

    public ElementSimple setTextureOffsets(int u, int v)
    {

        texU = u;
        texV = v;
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks)
    {

        RenderHelper.bindTexture(texture);
        drawTexturedModalRect(posX, posY, texU, texV, sizeX, sizeY);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY)
    {

        return;
    }

}

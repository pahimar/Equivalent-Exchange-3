package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.render.RenderHelper;

public class ElementDualScaled extends ElementBase {

    public int quantity;
    public int mode;
    public boolean background = true;

    public ElementDualScaled(GuiBase gui, int posX, int posY) {

        super(gui, posX, posY);
    }

    public ElementDualScaled setBackground(boolean background) {

        this.background = background;
        return this;
    }

    public ElementDualScaled setMode(int mode) {

        this.mode = mode;
        return this;
    }

    public ElementDualScaled setQuantity(int quantity) {

        this.quantity = quantity;
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks) {

        RenderHelper.bindTexture(texture);

        if (background) {
            drawTexturedModalRect(posX, posY, 0, 0, sizeX, sizeY);
        }
        switch (mode) {
            case 0:
                // vertical bottom -> top
                drawTexturedModalRect(posX, posY + sizeY - quantity, sizeX, sizeY - quantity, sizeX, quantity);
                return;
            case 1:
                // horizontal left -> right
                drawTexturedModalRect(posX, posY, sizeX, 0, quantity, sizeY);
                return;
            case 2:
                // horizontal right -> left
                drawTexturedModalRect(posX + sizeX - quantity, posY, sizeX + sizeX - quantity, 0, quantity, sizeY);
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {

    }

}

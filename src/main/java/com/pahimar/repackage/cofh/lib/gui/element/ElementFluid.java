package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import net.minecraftforge.fluids.FluidStack;

public class ElementFluid extends ElementBase {

    public FluidStack fluid;

    public ElementFluid(GuiBase gui, int posX, int posY) {

        super(gui, posX, posY);
    }

    public ElementFluid setFluid(FluidStack fluid) {

        this.fluid = fluid;
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks) {

        gui.drawFluid(posX, posY, fluid, sizeX, sizeY);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {

    }

}

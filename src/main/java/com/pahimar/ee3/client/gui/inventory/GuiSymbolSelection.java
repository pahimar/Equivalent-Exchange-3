package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerSymbolSelection;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiSymbolSelection extends GuiContainer
{
    public GuiSymbolSelection()
    {
        super(new ContainerSymbolSelection());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {

    }

    @Override
    public void drawDefaultBackground()
    {
        // NOOP
    }
}

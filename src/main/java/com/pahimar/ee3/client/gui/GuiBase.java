package com.pahimar.ee3.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

@SideOnly(Side.CLIENT)
public abstract class GuiBase extends GuiContainer {

    public GuiBase(Container container) {
        super(container);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int x, int y, float partialTick) {
        super.drawScreen(x, y, partialTick);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int x, int y) {

    }

}

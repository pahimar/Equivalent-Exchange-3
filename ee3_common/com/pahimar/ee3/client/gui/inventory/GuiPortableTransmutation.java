package com.pahimar.ee3.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.lib.Sprites;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiPortableTransmutation extends GuiContainer {

    public GuiPortableTransmutation(Container par1Container) {

        super(par1Container);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

        // TODO Auto-generated method stub
        int var4 = this.mc.renderEngine.getTexture(Sprites.GUI_SHEET_LOCATION + "portable_transmutation.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }

}

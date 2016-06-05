package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerCalcinator;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiCalcinator extends GuiContainer
{
    private TileEntityCalciner tileEntityCalciner;

    public GuiCalcinator(InventoryPlayer inventoryPlayer, TileEntityCalciner tileEntityCalciner)
    {
        super(new ContainerCalcinator(inventoryPlayer, tileEntityCalciner));
        ySize = 176;
        this.tileEntityCalciner = tileEntityCalciner;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        // NOOP
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.CALCINATOR);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int scaleAdjustment;

        if (this.tileEntityCalciner.getState() == 1)
        {
            scaleAdjustment = this.tileEntityCalciner.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(xStart + 46, yStart + 22 + 23 - scaleAdjustment, 176, 12 - scaleAdjustment, 14, scaleAdjustment + 2);
        }

        scaleAdjustment = this.tileEntityCalciner.getCookProgressScaled(24);
        this.drawTexturedModalRect(xStart + 75, yStart + 30, 176, 14, scaleAdjustment + 1, 16);
    }
}

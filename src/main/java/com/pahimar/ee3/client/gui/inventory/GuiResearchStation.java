package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerResearchStation;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityResearchStation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiResearchStation extends GuiContainer
{
    private TileEntityResearchStation tileEntityResearchStation;

    public GuiResearchStation(InventoryPlayer inventoryPlayer, TileEntityResearchStation tileEntityResearchStation)
    {
        super(new ContainerResearchStation(inventoryPlayer, tileEntityResearchStation));
        xSize = 256;
        ySize = 234;
        this.tileEntityResearchStation = tileEntityResearchStation;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.RESEARCH_STATION);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        if (this.tileEntityResearchStation.isItemKnown)
        {
            this.mc.getTextureManager().bindTexture(Textures.Gui.RESEARCH_STATION_GYLPH_1);
            this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        }
        else
        {
            int arrowScaleAdjustment = this.tileEntityResearchStation.getLearnProgressScaled(42);
            this.drawTexturedModalRect(xStart + 107, yStart + 87, 0, 236, arrowScaleAdjustment, 16);

            int scaleAdjustment = this.tileEntityResearchStation.getLearnProgressScaled(80);
            this.mc.getTextureManager().bindTexture(Textures.Gui.RESEARCH_STATION_GYLPH_1);
            this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, (ySize * scaleAdjustment) / 100);
        }
    }
}

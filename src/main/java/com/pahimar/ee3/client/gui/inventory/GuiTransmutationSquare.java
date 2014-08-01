package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerTransmutationSquare;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityTransmutationSquare;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiTransmutationSquare extends GuiContainer
{
    private TileEntityTransmutationSquare tileEntityTransmutationSquare;

    public GuiTransmutationSquare(InventoryPlayer inventoryPlayer, TileEntityTransmutationSquare tileEntityTransmutationSquare)
    {
        super(new ContainerTransmutationSquare(inventoryPlayer, tileEntityTransmutationSquare));
        this.tileEntityTransmutationSquare = tileEntityTransmutationSquare;
        xSize = 246;
        ySize = 223;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = StatCollector.translateToLocal(Names.Containers.TRANSMUTATION_SQUARE);
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.TRANSMUTATION_SQUARE);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

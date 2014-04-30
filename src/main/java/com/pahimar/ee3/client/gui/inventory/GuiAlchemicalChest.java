package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerAlchemicalChest;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityAlchemicalChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiAlchemicalChest extends GuiContainer
{
    private TileEntityAlchemicalChest tileEntityAlchemicalChest;

    public GuiAlchemicalChest(InventoryPlayer inventoryPlayer, TileEntityAlchemicalChest alchemicalChest)
    {
        super(new ContainerAlchemicalChest(inventoryPlayer, alchemicalChest));
        tileEntityAlchemicalChest = alchemicalChest;

        if (this.tileEntityAlchemicalChest.getState() == 0)
        {
            xSize = 230;
            ySize = 186;
        }
        else if (this.tileEntityAlchemicalChest.getState() == 1)
        {
            xSize = 230;
            ySize = 240;
        }
        else if (this.tileEntityAlchemicalChest.getState() == 2)
        {
            xSize = 248;
            ySize = 256;
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        if (tileEntityAlchemicalChest.getState() == 0 || tileEntityAlchemicalChest.getState() == 1)
        {
            fontRendererObj.drawString(StatCollector.translateToLocal(tileEntityAlchemicalChest.getInventoryName()), 8, 6, 4210752);
            fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 35, ySize - 95 + 2, 4210752);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        if (tileEntityAlchemicalChest.getState() == 0)
        {
            this.mc.getTextureManager().bindTexture(Textures.GUI_ALCHEMICAL_CHEST_SMALL);
        }
        else if (tileEntityAlchemicalChest.getState() == 1)
        {
            this.mc.getTextureManager().bindTexture(Textures.GUI_ALCHEMICAL_CHEST_MEDIUM);
        }
        else if (tileEntityAlchemicalChest.getState() == 2)
        {
            this.mc.getTextureManager().bindTexture(Textures.GUI_ALCHEMICAL_CHEST_LARGE);
        }

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

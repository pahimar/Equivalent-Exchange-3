package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerGlassBell;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityGlassBell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiGlassBell extends GuiContainer
{
    private TileEntityGlassBell tileEntityGlassBell;

    public GuiGlassBell(InventoryPlayer inventoryPlayer, TileEntityGlassBell tileEntityGlassBell)
    {
        super(new ContainerGlassBell(inventoryPlayer, tileEntityGlassBell));
        this.tileEntityGlassBell = tileEntityGlassBell;
        xSize = 176;
        ySize = 140;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = StatCollector.translateToLocal(tileEntityGlassBell.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_GLASS_BELL);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

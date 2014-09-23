package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerGlassBell;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityGlassBell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
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
        ySize = 161;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        // NOOP
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.GLASS_BELL);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        GL11.glDisable(GL11.GL_BLEND);
    }
}

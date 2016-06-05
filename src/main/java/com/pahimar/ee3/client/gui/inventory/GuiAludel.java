package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerAludel;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAludel extends GuiContainer
{
    private TileEntityAludel tileEntityAludel;

    public GuiAludel(InventoryPlayer inventoryPlayer, TileEntityAludel tileEntityAludel)
    {
        super(new ContainerAludel(inventoryPlayer, tileEntityAludel));
        this.tileEntityAludel = tileEntityAludel;
        xSize = 176;
        ySize = 187;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        // NOOP
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.ALUDEL);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int scaleAdjustment;

        if (this.tileEntityAludel.getState() == 1)
        {
            scaleAdjustment = this.tileEntityAludel.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(xStart + 45, yStart + 36 + 34 - scaleAdjustment, 176, 12 - scaleAdjustment, 14, scaleAdjustment + 2);
        }

        scaleAdjustment = this.tileEntityAludel.getCookProgressScaled(24);
        this.drawTexturedModalRect(xStart + 80, yStart + 40, 176, 14, scaleAdjustment + 1, 16);
    }
}

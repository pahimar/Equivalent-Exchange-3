package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.inventory.ContainerAludel;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileAludel;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * GuiAludel
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class GuiAludel extends GuiContainer {

    private TileAludel tileAludel;

    public GuiAludel(InventoryPlayer inventoryPlayer, TileAludel tileAludel) {

        super(new ContainerAludel(inventoryPlayer, tileAludel));
        this.tileAludel = tileAludel;
        xSize = 176;
        ySize = 187;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        String containerName = tileAludel.isInvNameLocalized() ? tileAludel.getInvName() : StatCollector.translateToLocal(tileAludel.getInvName());
        fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 8, ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_ALUDEL);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

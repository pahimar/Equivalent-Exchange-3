package com.pahimar.ee3.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.inventory.ContainerAludel;
import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAludel;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiAludel extends GuiContainer {
    
    private TileAludel tileAludel;

    public GuiAludel(InventoryPlayer inventoryPlayer, TileAludel tileAludel) {

        super(new ContainerAludel(inventoryPlayer, tileAludel));
        this.tileAludel = tileAludel;
        this.xSize = 176;
        this.ySize = 187;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        
        this.fontRenderer.drawString(LanguageRegistry.instance().getStringLocalization(Strings.GUI_ALUDEL_NAME), 73, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

        int backgroundTexture = this.mc.renderEngine.getTexture(Sprites.GUI_ALUDEL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(backgroundTexture);
        int xStart = (this.width - this.xSize) / 2;
        int yStart = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, this.xSize, this.ySize);
    }

}

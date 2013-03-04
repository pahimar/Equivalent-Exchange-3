package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.inventory.ContainerCalcinator;
import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileCalcinator;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * GuiCalcinator
 * 
 * Calcinator Gui class
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class GuiCalcinator extends GuiContainer {

    private TileCalcinator calcinator;

    public GuiCalcinator(InventoryPlayer player, TileCalcinator calcinator) {

        super(new ContainerCalcinator(player, calcinator));
        this.ySize = 176;
        this.calcinator = calcinator;
    }

    protected void drawGuiContainerForegroundLayer(int x, int y) {

        this.fontRenderer.drawString(LanguageRegistry.instance().getStringLocalization(Strings.GUI_CALCINATOR_NAME), 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //this.mc.renderEngine.bindTexture(Sprites.GUI_CALCINATOR);
        this.mc.renderEngine.func_98187_b(Sprites.GUI_CALCINATOR);
        int xStart = (this.width - this.xSize) / 2;
        int yStart = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, this.xSize, this.ySize);
    }

}

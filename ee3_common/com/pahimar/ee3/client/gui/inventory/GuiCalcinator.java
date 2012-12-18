package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.inventory.ContainerCalcinator;
import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileCalcinator;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * GuiCalcinator
 * 
 * Calcinator Gui class
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class GuiCalcinator extends GuiContainer {

    private TileCalcinator calcinator;
    
    public GuiCalcinator(InventoryPlayer player, TileCalcinator calcinator) {
        super(new ContainerCalcinator(player, calcinator));
        this.ySize = 176;
        this.calcinator = calcinator;
    }
    
    protected void drawGuiContainerForegroundLayer()
    {
        this.fontRenderer.drawString(LanguageRegistry.instance().getStringLocalization(Strings.GUI_CALCINATOR_NAME), 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int var4 = this.mc.renderEngine.getTexture(Sprites.GUI_SHEET_LOCATION + Sprites.CALCINATOR_MODEL_TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;

        /*
         * This bit shows the "fire" effect in the GUI
        if (this.furnaceInventory.isBurning())
        {
            var7 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
        }

         * This bit shows the progress bar in the GUI
        var7 = this.furnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
        */
    }
    
}

package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.inventory.ContainerAlchemicalChest;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * GuiAlchemicalChest
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class GuiAlchemicalChest extends GuiContainer {

    private TileAlchemicalChest tileAlchemicalChest;

    public GuiAlchemicalChest(InventoryPlayer inventoryPlayer, TileAlchemicalChest alchemicalChest) {

        super(new ContainerAlchemicalChest(inventoryPlayer, alchemicalChest));
        tileAlchemicalChest = alchemicalChest;
        xSize = 248;
        ySize = 186;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        fontRenderer.drawString(tileAlchemicalChest.isInvNameLocalized() ? tileAlchemicalChest.getInvName() : StatCollector.translateToLocal(tileAlchemicalChest.getInvName()), 8, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 44, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.GUI_ALCHEMICAL_STORAGE);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

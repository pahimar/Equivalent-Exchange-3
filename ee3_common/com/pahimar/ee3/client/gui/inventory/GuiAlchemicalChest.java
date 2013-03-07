package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.inventory.ContainerAlchemicalChest;
import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAlchemicalChest extends GuiContainer {

    private IInventory tileAlchemicalChest;
    
    public GuiAlchemicalChest(InventoryPlayer inventoryPlayer, IInventory tileAlchemicalChest) {

        super(new ContainerAlchemicalChest(inventoryPlayer, tileAlchemicalChest));
        this.tileAlchemicalChest = tileAlchemicalChest;
        this.xSize = 248;
        this.ySize = 186;
    }

    protected void drawGuiContainerForegroundLayer(int x, int y) {

        this.fontRenderer.drawString(this.tileAlchemicalChest.func_94042_c() ? this.tileAlchemicalChest.getInvName() : StatCollector.translateToLocal(this.tileAlchemicalChest.getInvName()), 8, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 44, this.ySize - 96 + 2, 4210752);
    }
    
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //this.mc.renderEngine.bindTexture(Sprites.GUI_ALCHEMICAL_STORAGE);
        this.mc.renderEngine.func_98187_b(Sprites.GUI_ALCHEMICAL_STORAGE);
        int xStart = (this.width - this.xSize) / 2;
        int yStart = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, this.xSize, this.ySize);
    }

}

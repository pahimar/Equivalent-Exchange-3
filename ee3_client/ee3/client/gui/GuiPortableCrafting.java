package ee3.client.gui;

import org.lwjgl.opengl.GL11;

import ee3.container.ContainerPortableCrafting;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;

public class GuiPortableCrafting extends GuiContainer {
	
	public GuiPortableCrafting(InventoryPlayer inventoryPlayer) {
        super(new ContainerPortableCrafting(inventoryPlayer, inventoryPlayer.player.worldObj, (int)inventoryPlayer.player.posX, (int)inventoryPlayer.player.posY, (int)inventoryPlayer.player.posZ));
    }
	
    public void onGuiClosed() {
        super.onGuiClosed();
    }
    
    protected void drawGuiContainerForegroundLayer() {
        fontRenderer.drawString("Crafting", 28, 6, 0x404040);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
    }
    
    protected void drawGuiContainerBackgroundLayer(float f, int l, int m) {
        int i = mc.renderEngine.getTexture("/gui/crafting.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
    }

}

package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerAlchemicalTome;
import com.pahimar.ee3.inventory.InventoryAlchemicalTome;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAlchemicalTome extends GuiContainer
{
    private InventoryAlchemicalTome inventoryAlchemicalTome;

    public GuiAlchemicalTome(InventoryAlchemicalTome inventoryAlchemicalTome)
    {
        super(new ContainerAlchemicalTome(inventoryAlchemicalTome));
        this.inventoryAlchemicalTome = inventoryAlchemicalTome;
        ySize = 209;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = StatCollector.translateToLocal(inventoryAlchemicalTome.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.ALCHEMICAL_TOME);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

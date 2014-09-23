package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerAugmentationTable;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityAugmentationTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAugmentationTable extends GuiContainer
{
    private TileEntityAugmentationTable tileEntityAugmentationTable;

    public GuiAugmentationTable(InventoryPlayer inventoryPlayer, TileEntityAugmentationTable tileEntityAugmentationTable)
    {
        super(new ContainerAugmentationTable(inventoryPlayer, tileEntityAugmentationTable));
        this.tileEntityAugmentationTable = tileEntityAugmentationTable;
        xSize = 188;
        ySize = 199;
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

        this.mc.getTextureManager().bindTexture(Textures.Gui.AUGMENTATION_TABLE);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

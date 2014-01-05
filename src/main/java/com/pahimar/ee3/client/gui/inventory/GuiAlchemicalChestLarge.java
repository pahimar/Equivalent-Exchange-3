package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerAlchemicalChestLarge;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Equivalent-Exchange-3
 * <p/>
 * GuiAlchemicalChestLarge
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class GuiAlchemicalChestLarge extends GuiContainer
{
    public GuiAlchemicalChestLarge(InventoryPlayer inventoryPlayer, TileAlchemicalChest alchemicalChest)
    {
        super(new ContainerAlchemicalChestLarge(inventoryPlayer, alchemicalChest));
        xSize = 248;
        ySize = 256;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_ALCHEMICAL_STORAGE_LARGE);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

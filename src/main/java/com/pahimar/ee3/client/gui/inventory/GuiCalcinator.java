package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerCalcinator;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileCalcinator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Equivalent-Exchange-3
 * <p/>
 * GuiCalcinator
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class GuiCalcinator extends GuiContainer
{
    private TileCalcinator tileCalcinator;

    public GuiCalcinator(InventoryPlayer player, TileCalcinator tileCalcinator)
    {
        super(new ContainerCalcinator(player, tileCalcinator));
        ySize = 176;
        this.tileCalcinator = tileCalcinator;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = tileCalcinator.isInvNameLocalized() ? tileCalcinator.getInvName() : StatCollector.translateToLocal(tileCalcinator.getInvName());
        fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.GUI_CALCINATOR);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int scaleAdjustment;

        if (this.tileCalcinator.isBurning())
        {
            scaleAdjustment = this.tileCalcinator.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(xStart + 57, yStart + 36 + 23 - scaleAdjustment, 176, 12 - scaleAdjustment, 14, scaleAdjustment + 2);
        }

        scaleAdjustment = this.tileCalcinator.getCookProgressScaled(24);
        this.drawTexturedModalRect(xStart + 83, yStart + 34, 176, 14, scaleAdjustment + 1, 16);
    }
}

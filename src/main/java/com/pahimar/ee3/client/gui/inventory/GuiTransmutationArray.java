package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerTransmutationArray;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTransmutationArray extends GuiBase
{
    private TileEntityAlchemyArray tileEntityAlchemyArray;

    public GuiTransmutationArray(InventoryPlayer inventoryPlayer, TileEntityAlchemyArray tileEntityAlchemyArray)
    {
        super(new ContainerTransmutationArray(inventoryPlayer, tileEntityAlchemyArray));
        this.tileEntityAlchemyArray = tileEntityAlchemyArray;
        xSize = 256;
        ySize = 256;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        this.drawTitle = false;
        this.drawInventory = false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        super.drawGuiContainerForegroundLayer(x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y)
    {
        mouseX = x - guiLeft;
        mouseY = y - guiTop;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (tileEntityAlchemyArray.getSize() == 1)
        {
            bindTexture(Textures.Gui.TRANSMUTATION_ARRAY_1);
        }
        else if (tileEntityAlchemyArray.getSize() == 2)
        {
            bindTexture(Textures.Gui.TRANSMUTATION_ARRAY_3);
        }
        else if (tileEntityAlchemyArray.getSize() == 3)
        {
            bindTexture(Textures.Gui.TRANSMUTATION_ARRAY_5);
        }
        drawSizedTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize, 256f, 256f);
        GL11.glPushMatrix();
        GL11.glTranslatef(guiLeft, guiTop, 0.0F);
        drawElements(partialTicks, false);
        drawTabs(partialTicks, false);
        GL11.glPopMatrix();
    }
}

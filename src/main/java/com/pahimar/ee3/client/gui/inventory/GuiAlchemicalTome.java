package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerAlchemicalTome;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.element.ElementButton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAlchemicalTome extends GuiBase
{
    private static final int LEFT_CLICK = 0;

    private ElementButton prevPageButton;
    private ElementButton nextPageButton;

    public GuiAlchemicalTome(EntityPlayer entityPlayer)
    {
        super(new ContainerAlchemicalTome(entityPlayer.inventory), Textures.Gui.ALCHEMICAL_TOME);
        LogHelper.info(this.inventorySlots instanceof ContainerAlchemicalTome);
        ContainerAlchemicalTome containerAlchemicalTome = (ContainerAlchemicalTome) this.inventorySlots;
        LogHelper.info(containerAlchemicalTome.getInventorySize());
        xSize = 256;
        ySize = 226;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        prevPageButton = new ElementButton(this, 15, 177, "Prev", 0, 0, 20, 0, 40, 0, 20, 10, 60, 10, "textures/gui/elements/arrowLeft.png");
        nextPageButton = new ElementButton(this, 223, 177, "Next", 0, 0, 22, 0, 44, 0, 22, 10, 66, 10, "textures/gui/elements/arrowRight.png");

        addElement(prevPageButton);
        addElement(nextPageButton);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        // NOOP
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y)
    {
        mouseX = x - guiLeft;
        mouseY = y - guiTop;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        bindTexture(texture);
        drawSizedTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize, 256f, 256f);
        GL11.glPushMatrix();
        GL11.glTranslatef(guiLeft, guiTop, 0.0F);
        drawElements(partialTicks, false);
        drawTabs(partialTicks, false);
        GL11.glPopMatrix();
    }

    @Override
    protected void updateElementInformation()
    {
        if (((ContainerAlchemicalTome) this.inventorySlots).getInventorySize() < 80)
        {
            prevPageButton.setDisabled();
            nextPageButton.setDisabled();
        }
    }

    @Override
    protected void handleMouseClick(Slot slot, int p_146984_2_, int p_146984_3_, int p_146984_4_)
    {
        // NOOP
    }

    @Override
    public void handleElementButtonClick(String buttonName, int mouseButton)
    {
        LogHelper.info(String.format("%s %s", buttonName, mouseButton));
    }
}

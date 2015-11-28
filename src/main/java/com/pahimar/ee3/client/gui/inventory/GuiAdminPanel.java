package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerAdminPanel;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageGuiElementClicked;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.GuiColor;
import com.pahimar.repackage.cofh.lib.gui.element.ElementButton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAdminPanel extends GuiBase
{

    private ElementButton learnableButton;
    private ElementButton recoverableButton;

    public GuiAdminPanel(InventoryPlayer inventoryPlayer)
    {
        super(new ContainerAdminPanel(inventoryPlayer), Textures.Gui.ADMIN_PANEL);
        xSize = 175;
        ySize = 176;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        learnableButton = new ElementButton(this, 65, 22, "learnable", 0, 0, 0, 20, 0, 40, 100, 20, 100, 60, Textures.Gui.Elements.BUTTON);
        recoverableButton = new ElementButton(this, 65, 48, "recoverable", 0, 0, 0, 20, 0, 40, 100, 20, 100, 60, Textures.Gui.Elements.BUTTON);
        addElement(learnableButton);
        addElement(recoverableButton);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        super.drawGuiContainerForegroundLayer(x, y);

        if (learnableButton.intersectsWith(mouseX, mouseY))
        {
            fontRendererObj.drawSplitString("Not Learnable", 81, 28, 100, new GuiColor(255, 255, 255).getColor());
        }
        else
        {
            fontRendererObj.drawSplitString("Learnable", 90, 28, 100, new GuiColor(255, 255, 255).getColor());
        }

        fontRendererObj.drawSplitString("Recoverable", 85, 54, 100, new GuiColor(255, 255, 255).getColor());
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

    }

    @Override
    public void handleElementButtonClick(String buttonName, int mouseButton)
    {
        PacketHandler.INSTANCE.sendToServer(new MessageGuiElementClicked(buttonName, mouseButton));
    }
}

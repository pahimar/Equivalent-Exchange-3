package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.client.gui.element.ElementSearchField;
import com.pahimar.ee3.inventory.ContainerAlchenomicon;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageGuiElementClicked;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.GuiColor;
import com.pahimar.repackage.cofh.lib.gui.element.ElementButton;
import com.pahimar.repackage.cofh.lib.gui.element.ElementTextField;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAlchenomicon extends GuiBase
{
    private ElementButton prevPageButton;
    private ElementButton nextPageButton;
    private ElementTextField searchTextField;

    public GuiAlchenomicon(InventoryPlayer inventoryPlayer, ItemStack itemStack)
    {
        super(new ContainerAlchenomicon(inventoryPlayer.player, itemStack), Textures.Gui.ALCHENOMICON);
        xSize = 256;
        ySize = 226;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        this.drawTitle = false;
        this.drawInventory = false;

        prevPageButton = new ElementButton(this, 15, 177, "prev", 0, 0, 20, 0, 40, 0, 20, 10, 60, 10, Textures.Gui.Elements.ARROW_LEFT);
        nextPageButton = new ElementButton(this, 223, 177, "next", 0, 0, 22, 0, 44, 0, 22, 10, 66, 10, Textures.Gui.Elements.ARROW_RIGHT);
        searchTextField = new ElementSearchField(this, 64, 205, "searchField", 128, 20);
        searchTextField.setPadding(6, 3, 0, 3);
        searchTextField.borderColor = new GuiColor(160, 160, 160).getColor();
        searchTextField.backgroundColor = new GuiColor(0, 0, 0).getColor();

        addElement(prevPageButton);
        addElement(nextPageButton);
        addElement(searchTextField);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        super.drawGuiContainerForegroundLayer(x, y);
        int pageOffset = ((ContainerAlchenomicon) this.inventorySlots).getPageOffset();

        if (this.inventorySlots.getSlot(0).getHasStack())
        {
            fontRendererObj.drawString((pageOffset * 2 + 1) + "", 64, 178, new GuiColor(50, 50, 50).getColor());
        }
        else
        {
            if (((ContainerAlchenomicon) this.inventorySlots).getKnownTransmutationsCount() == 0)
            {
                fontRendererObj.drawSplitString(StatCollector.translateToLocal(Messages.Gui.NO_KNOWN_TRANSMUTATIONS), 142, 20, 100, new GuiColor(50, 50, 50).getColor());
            }
        }

        if (this.inventorySlots.getSlot(40).getHasStack())
        {
            fontRendererObj.drawString((pageOffset * 2 + 2) + "", 186, 178, new GuiColor(50, 50, 50).getColor());
        }
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
        if (((ContainerAlchenomicon) this.inventorySlots).getPageOffset() == 0)
        {
            prevPageButton.setDisabled();
        }
        else if (!prevPageButton.isEnabled())
        {
            prevPageButton.setEnabled(true);
        }

        if (((ContainerAlchenomicon) this.inventorySlots).getPageOffset() == ((ContainerAlchenomicon) this.inventorySlots).getMaxPageOffset())
        {
            nextPageButton.setDisabled();
        }
        else if (!nextPageButton.isEnabled())
        {
            nextPageButton.setEnabled(true);
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
        PacketHandler.INSTANCE.sendToServer(new MessageGuiElementClicked(buttonName, mouseButton));
    }
}

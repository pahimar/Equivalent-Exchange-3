package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerTransmutationTablet;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageGuiElementTextFieldUpdate;
import com.pahimar.ee3.reference.Colors;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.GuiColor;
import com.pahimar.repackage.cofh.lib.gui.element.ElementSlider;
import com.pahimar.repackage.cofh.lib.gui.element.ElementTextField;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiTransmutationTablet extends GuiBase
{
    private TileEntityTransmutationTablet tileEntityTransmutationTablet;

    private ElementTextField searchTextField;
    private ElementSlider slider;

    public GuiTransmutationTablet(InventoryPlayer inventoryPlayer, TileEntityTransmutationTablet tileEntityTransmutationTablet)
    {
        super(new ContainerTransmutationTablet(inventoryPlayer, tileEntityTransmutationTablet), Textures.Gui.TRANSMUTATION_TABLET);
        this.tileEntityTransmutationTablet = tileEntityTransmutationTablet;
        xSize = 256;
        ySize = 256;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        this.drawTitle = false;
        this.drawInventory = false;

        searchTextField = new ElementTextField(this, 173, 145, "searchField", 78, 10)
        {
            @Override
            protected void onCharacterEntered(boolean success)
            {
                if (success)
                {
                    PacketHandler.INSTANCE.sendToServer(new MessageGuiElementTextFieldUpdate(this));
                }
            }
        };
        searchTextField.backgroundColor = new GuiColor(0, 0, 0, 0).getColor();
        searchTextField.borderColor = new GuiColor(0, 0, 0, 0).getColor();
        searchTextField.setFocused(true);

        slider = new ElementSlider(this, 239, 163, 12, 74, 50, 0)
        {
            @Override
            protected void dragSlider(int x, int y)
            {
                if (y > _value)
                {
                    setValue(_value + 1);
                }
                else
                {
                    setValue(_value - 1);
                }
            }

            @Override
            public int getSliderY()
            {
                return _value;
            }
        };
        slider.backgroundColor = new GuiColor(0, 0, 0, 0).getColor();
        slider.borderColor = new GuiColor(0, 0, 0, 0).getColor();
        slider.setSliderSize(12, 15);

        addElement(searchTextField);
        addElement(slider);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        super.drawGuiContainerForegroundLayer(x, y);
        fontRendererObj.drawString("Energy Value:", 8, 140, Integer.parseInt(Colors.PURE_WHITE, 16));

        // TODO Nicer text formatting of the value
        fontRendererObj.drawString(String.format("%s", tileEntityTransmutationTablet.getEnergyValue().getEnergyValue()), 8, 150, Integer.parseInt(Colors.PURE_WHITE, 16));
    }
}

package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.client.gui.element.ElementSearchField;
import com.pahimar.ee3.inventory.ContainerTransmutationTablet;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSliderElementUpdated;
import com.pahimar.ee3.reference.Colors;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.GuiColor;
import com.pahimar.repackage.cofh.lib.gui.element.ElementButton;
import com.pahimar.repackage.cofh.lib.gui.element.ElementSlider;
import com.pahimar.repackage.cofh.lib.gui.element.ElementTextField;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;

import java.text.DecimalFormat;

@SideOnly(Side.CLIENT)
public class GuiTransmutationTablet extends GuiBase
{
    private TileEntityTransmutationTablet tileEntityTransmutationTablet;

    private ElementTextField searchTextField;
    private ElementButton sortOrderButton;
    private ElementSlider slider;

    private static DecimalFormat energyValueDecimalFormat = new DecimalFormat("###,###,###,###,###.###");

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

        searchTextField = new ElementSearchField(this, 173, 18, "searchField", 78, 10);
        searchTextField.backgroundColor = new GuiColor(0, 0, 0, 0).getColor();
        searchTextField.borderColor = new GuiColor(0, 0, 0, 0).getColor();

        sortOrderButton = new ElementButton(this, 151, 36, "sortOrder", 0, 0, 18, 0, 36, 0, 18, 18, 54, 18, Textures.Gui.Elements.BUTTON_SORT_ORDER);

        slider = new ElementSlider(this, 239, 36, 12, 201, 187, 0)
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
            public void onStopDragging()
            {
                PacketHandler.INSTANCE.sendToServer(new MessageSliderElementUpdated(this));
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

        addElement(sortOrderButton);
        addElement(searchTextField);
        addElement(slider);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        super.drawGuiContainerForegroundLayer(x, y);
        fontRendererObj.drawString("Energy Value:", 8, 140, Integer.parseInt(Colors.PURE_WHITE, 16)); // TODO Localize
        fontRendererObj.drawString(String.format("%s", energyValueDecimalFormat.format(tileEntityTransmutationTablet.getStoredEnergyValue().getEnergyValue())), 8, 150, Integer.parseInt(Colors.PURE_WHITE, 16));
    }
}

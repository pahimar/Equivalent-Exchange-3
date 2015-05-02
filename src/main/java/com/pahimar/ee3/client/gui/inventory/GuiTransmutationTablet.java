package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.client.gui.element.ElementSearchField;
import com.pahimar.ee3.client.gui.element.ElementStatefulButton;
import com.pahimar.ee3.inventory.ContainerTransmutationTablet;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageGuiElementClicked;
import com.pahimar.ee3.network.message.MessageSliderElementUpdated;
import com.pahimar.ee3.reference.Colors;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.GuiColor;
import com.pahimar.repackage.cofh.lib.gui.element.ElementSlider;
import com.pahimar.repackage.cofh.lib.gui.element.ElementTextField;
import com.pahimar.repackage.cofh.lib.render.RenderHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import java.text.DecimalFormat;

@SideOnly(Side.CLIENT)
public class GuiTransmutationTablet extends GuiBase
{
    private TileEntityTransmutationTablet tileEntityTransmutationTablet;

    private ElementTextField searchTextField;
    private ElementStatefulButton sortOrderButton;
    private ElementSlider slider;
    protected int tickCount;

    private static DecimalFormat energyValueDecimalFormat = new DecimalFormat("###,###,###,###,###.###");

    private static final int LEFT_MOUSEBUTTON = 0;
    private static final int RIGHT_MOUSEBUTTON = 1;

    private static final int SORT_BY_DISPLAY_NAME = 0;
    private static final int SORT_BY_ENERGY_VALUE = 1;
    private static final int SORT_BY_ID = 2;

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

        sortOrderButton = new ElementStatefulButton(this, 151, 36, "sortOrder", 0, 0, 18, 0, 36, 0, 18, 18, 54, 18, Textures.Gui.Elements.BUTTON_SORT_ORDER)
        {
            @Override
            public void drawBackground(int mouseX, int mouseY, float gameTicks)
            {

                RenderHelper.bindTexture(texture);
                if (isEnabled())
                {
                    if (getState() == SORT_BY_DISPLAY_NAME)
                    {
                        drawTexturedModalRect(posX, posY, 36, 0, sizeX, sizeY);
                    }
                    else if (getState() == SORT_BY_ENERGY_VALUE)
                    {
                        drawTexturedModalRect(posX, posY, 0, 0, sizeX, sizeY);
                    }
                    else if (getState() == SORT_BY_ID)
                    {
                        drawTexturedModalRect(posX, posY, 18, 0, sizeX, sizeY);
                    }
                }
            }
        };
        setTooltipByState();

        slider = new ElementSlider(this, "scrollBar", 239, 36, 12, 201, 187, 0)
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
            public boolean onMouseWheel(int mouseX, int mouseY, int movement)
            {
                PacketHandler.INSTANCE.sendToServer(new MessageSliderElementUpdated(this));
                LogHelper.info(tickCount);
                return super.onMouseWheel(mouseX, mouseY, movement);
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
        fontRendererObj.drawString(String.format("%s:", StatCollector.translateToLocal(Messages.ENERGY_VALUE)), 10, 142, Integer.parseInt(Colors.PURE_WHITE, 16));
        fontRendererObj.drawString(String.format("%s", energyValueDecimalFormat.format(tileEntityTransmutationTablet.getAvailableEnergyValue().getValue())), 10, 152, Integer.parseInt(Colors.PURE_WHITE, 16));
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        tickCount++;
    }

    @Override
    public void handleElementButtonClick(String buttonName, int mouseButton)
    {
        if (buttonName.equals("sortOrder"))
        {
            PacketHandler.INSTANCE.sendToServer(new MessageGuiElementClicked(buttonName, mouseButton));

            if (mouseButton == LEFT_MOUSEBUTTON)
            {
                if (sortOrderButton.getState() == SORT_BY_DISPLAY_NAME)
                {
                    sortOrderButton.setState(SORT_BY_ENERGY_VALUE);
                }
                else if (sortOrderButton.getState() == SORT_BY_ENERGY_VALUE)
                {
                    sortOrderButton.setState(SORT_BY_ID);
                }
                else if (sortOrderButton.getState() == SORT_BY_ID)
                {
                    sortOrderButton.setState(SORT_BY_DISPLAY_NAME);
                }
            }
            else if (mouseButton == RIGHT_MOUSEBUTTON)
            {
                if (sortOrderButton.getState() == SORT_BY_DISPLAY_NAME)
                {
                    sortOrderButton.setState(SORT_BY_ID);
                }
                else if (sortOrderButton.getState() == SORT_BY_ENERGY_VALUE)
                {
                    sortOrderButton.setState(SORT_BY_DISPLAY_NAME);
                }
                else if (sortOrderButton.getState() == SORT_BY_ID)
                {
                    sortOrderButton.setState(SORT_BY_ENERGY_VALUE);
                }
            }

            setTooltipByState();
        }
    }

    private void setTooltipByState()
    {
        sortOrderButton.clearToolTip();
        if (sortOrderButton.getState() == SORT_BY_DISPLAY_NAME)
        {
            sortOrderButton.setToolTip(Messages.Tooltips.SORT_BY_DISPLAY_NAME);
        }
        else if (sortOrderButton.getState() == SORT_BY_ENERGY_VALUE)
        {
            sortOrderButton.setToolTip(Messages.Tooltips.SORT_BY_ENERGY_VALUE);
        }
        else if (sortOrderButton.getState() == SORT_BY_ID)
        {
            sortOrderButton.setToolTip(Messages.Tooltips.SORT_BY_ID);
        }
    }
}

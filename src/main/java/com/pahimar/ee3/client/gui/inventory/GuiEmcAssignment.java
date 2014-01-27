package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.handler.ValueFilesHandler;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;

public class GuiEmcAssignment extends GuiScreen
{
    private static ArrayList<ItemStack> itemStackList;

    private GuiItemList itemList;
    private GuiTextField filterField;
    private GuiTextField valueField;
    private GuiButton buttonFilterType;
    private boolean showOnlyNoValue = false;

    private ArrayList<ItemStack> filteredItemStackList;
    private ItemStack selectedItemStack;
    private EmcValue selectedItemStackValue;
    private int selected = -1;

    public GuiEmcAssignment()
    {
        if (itemStackList == null)
        {
            init();
        }
        filteredItemStackList = new ArrayList<ItemStack>(itemStackList);
    }

    public static void init()
    {
        itemStackList = new ArrayList<ItemStack>();
        for (Item item : Item.itemsList)
        {
            if (item != null)
            {
                ArrayList<ItemStack> damages = new ArrayList<ItemStack>();
                item.getSubItems(item.itemID, null, damages);
                for (ItemStack itemStack : damages)
                {
                    itemStackList.add(itemStack);
                }
            }
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        itemList = new GuiItemList(this, 150);
        filterField = new GuiTextField(fontRenderer, 10, getHeight() - 30, 150, 20);
        filterField.setFocused(true);

        valueField = new GuiTextField(fontRenderer, getWidth()/2 + 25, 140, 150, 20);

        this.buttonList.add(new GuiButton(0, getWidth() / 2, getHeight() - 30, I18n.getStringParams("gui.done")));
        this.buttonList.add(new GuiButton(1, getWidth() / 2, getHeight() - 60, StatCollector.translateToLocal(Strings.SET)));
        this.buttonList.add(buttonFilterType = new GuiButton(2, 10, 10, 150, 20, StatCollector.translateToLocal(Strings.SHOW_ALL)));
    }

    @Override
    public void drawScreen(int x, int y, float par3)
    {
        this.itemList.drawScreen(x, y, par3);
        filterField.drawTextBox();

        if (selectedItemStack != null)
        {
            valueField.drawTextBox();

            boolean hasValue = selectedItemStackValue != null;

            this.getFontRenderer().drawString(selectedItemStack.getDisplayName(), getWidth()/2 + 100 - (this.getFontRenderer().getStringWidth(selectedItemStack.getDisplayName())/2), 30, 0xFFFFFF);

            String itemStackInfo = StatCollector.translateToLocalFormatted(Strings.ITEM_INFORMATION, selectedItemStack.getUnlocalizedName(), selectedItemStack.getItemDamage());
            this.getFontRenderer().drawString(itemStackInfo, getWidth()/2 + 100 - (this.getFontRenderer().getStringWidth(itemStackInfo)/2), 60, 0xFFFFFF);


            String valueString = hasValue? StatCollector.translateToLocal(Strings.HAS_EMC_VALUE): StatCollector.translateToLocal(Strings.HAS_NO_EMC_VALUE);
            this.getFontRenderer().drawString(valueString, getWidth()/2 + + 100 - (this.getFontRenderer().getStringWidth(valueString)/2), 90, hasValue? 0x00FF00: 0xFF0000);

            if (hasValue)
            {
                this.getFontRenderer().drawString(String.valueOf(selectedItemStackValue.getValue()), getWidth()/2 + 100 - (this.getFontRenderer().getStringWidth(String.valueOf(selectedItemStackValue.getValue()))/2), 120, 0xFFFFFF);
            }
        }

        super.drawScreen(x, y, par3);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.enabled)
        {
            switch (button.id)
            {
                case 0:
                    this.mc.displayGuiScreen(null);
                    this.mc.setIngameFocus();
                    break;
                case 1:
                    GameRegistry.UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor(selectedItemStack.getItem());
                    String modid = identifier != null? identifier.modId: "minecraft";

                    if (!valueField.getText().isEmpty() && (selectedItemStackValue == null || selectedItemStackValue.getValue() != Float.parseFloat(valueField.getText())))
                    {
                        ValueFilesHandler.addFileValue(modid, selectedItemStack, new EmcValue(Float.parseFloat(valueField.getText())));
                    }
                    break;
                case 2:
                    showOnlyNoValue = !showOnlyNoValue;
                    buttonFilterType.displayString = showOnlyNoValue? StatCollector.translateToLocal(Strings.SHOW_ONLY_NO_VALUE): StatCollector.translateToLocal(Strings.SHOW_ALL);
                    onFilterChanged();
                    break;
            }
        }
    }

    @Override
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1)
        {
            this.mc.displayGuiScreen(null);
            this.mc.setIngameFocus();
        }

        if (filterField.isFocused())
        {
            filterField.textboxKeyTyped(par1, par2);
            onFilterChanged();
        }
        if (Character.isDigit(par1) || par2 == 14 || par2 == 203 || par2 == 205 || (par1 == 46 && !valueField.getText().contains(".")))
        {
            valueField.textboxKeyTyped(par1, par2);
        }
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        filterField.mouseClicked(par1, par2, par3);
        valueField.mouseClicked(par1, par2, par3);
    }

    public void onFilterChanged()
    {
        filteredItemStackList.clear();
        for (ItemStack itemStack : itemStackList)
        {
            if (filterField.getText().isEmpty() || itemStack.getDisplayName().toLowerCase().contains(filterField.getText().toLowerCase()))
            {
                if (!showOnlyNoValue || !EmcRegistry.getInstance().hasEmcValue(itemStack))
                {
                    filteredItemStackList.add(itemStack);
                }
            }
        }
    }

    public ArrayList<ItemStack> getItemStackList()
    {
        return filteredItemStackList;
    }

    public Minecraft getMinecraftInstance()
    {
        return mc;
    }

    public FontRenderer getFontRenderer()
    {
        return fontRenderer;
    }

    public void selectItemIndex(int selected)
    {
        this.selected = selected;

        if (selected >= 0 && selected < getItemStackList().size())
        {
            this.selectedItemStack = getItemStackList().get(selected);
            this.selectedItemStackValue = EmcRegistry.getInstance().getEmcValue(selectedItemStack);
            this.valueField.setText(selectedItemStackValue != null? String.valueOf(selectedItemStackValue.getValue()): "");
        }
        else
        {
            this.selectedItemStack = null;
            this.selectedItemStackValue = null;
            this.valueField.setText("");
        }
    }

    public boolean itemIndexSelected(int var1)
    {
        return var1 == selected;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void drawBackground()
    {
        drawBackground(0);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}

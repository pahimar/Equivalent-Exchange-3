package com.pahimar.ee3.client.gui.element;

import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageGuiElementTextFieldUpdate;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.element.ElementTextField;

public class ElementSearchField extends ElementTextField
{
    public ElementSearchField(GuiBase gui, int posX, int posY, String name, int width, int height)
    {
        super(gui, posX, posY, name, width, height);
    }

    @Override
    protected void onCharacterEntered(boolean success)
    {
        if (success)
        {
            PacketHandler.INSTANCE.sendToServer(new MessageGuiElementTextFieldUpdate(this));
        }
    }

    @Override
    protected boolean onEnter()
    {
        if (isFocused())
        {
            this.setFocused(false);
        }
        return super.onEnter();
    }

    @Override
    public boolean onMousePressed(int mouseX, int mouseY, int mouseButton)
    {
        super.onMousePressed(mouseX, mouseY, mouseButton);
        if (mouseButton == 1)
        {
            this.setText("");
            PacketHandler.INSTANCE.sendToServer(new MessageGuiElementTextFieldUpdate(this));
        }

        return true;
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY)
    {
        super.onMouseReleased(mouseX, mouseY);
        if (this.intersectsWith(mouseX, mouseY))
        {
            this.setFocused(true);
        }
    }
}

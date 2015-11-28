package com.pahimar.ee3.inventory;

import com.pahimar.ee3.inventory.element.IElementButtonHandler;
import com.pahimar.ee3.inventory.element.IElementTextFieldHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAdminPanel extends ContainerEE implements IElementButtonHandler, IElementTextFieldHandler
{

    public ContainerAdminPanel(InventoryPlayer inventoryPlayer)
    {

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        // TODO Check if the player is Admin
        return true;
    }

    @Override
    public void handleElementButtonClick(String elementName, int mouseButton)
    {

    }

    @Override
    public void handleElementTextFieldUpdate(String elementName, String updatedText)
    {

    }
}

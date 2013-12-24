package com.pahimar.ee3.inventory;

import com.pahimar.ee3.helper.ItemStackNBTHelper;
import com.pahimar.ee3.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ContainerAlchemicalBag
 *
 * @author pahimar
 */
public class ContainerAlchemicalBag extends Container
{

    private final int BAG_INVENTORY_ROWS = 4;
    private final int BAG_INVENTORY_COLUMNS = 13;

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;
    
    public InventoryAlchemicalBag inv;

    public ContainerAlchemicalBag(InventoryPlayer inventoryPlayer, InventoryAlchemicalBag inventoryAlchemicalBag)
    {
        this.inv = inventoryAlchemicalBag;
        
        // Add the Alchemical Bag slots to the container
        for (int chestRowIndex = 0; chestRowIndex < BAG_INVENTORY_ROWS; ++chestRowIndex)
        {
            for (int chestColumnIndex = 0; chestColumnIndex < BAG_INVENTORY_COLUMNS; ++chestColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryAlchemicalBag, chestColumnIndex + chestRowIndex * 13, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18));
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * PLAYER_INVENTORY_COLUMNS + PLAYER_INVENTORY_COLUMNS, 44 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
        	if (actionBarSlotIndex == inventoryAlchemicalBag.getSlot())
        	    // Nessessary to prevent player from placing the alchemical bag inside itself.
        	    this.addSlotToContainer(new SlotReadonly(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 162));
        	else
                this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 162));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {

        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {

        super.onContainerClosed(player);

        if (!player.worldObj.isRemote)
        {
            InventoryPlayer invPlayer = player.inventory;
            for (ItemStack itemStack : invPlayer.mainInventory)
            {
                if (itemStack != null)
                {
                    if (ItemStackNBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN))
                    {
                        ItemStackNBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
                    }
                }
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {

        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();

            if (slotIndex < BAG_INVENTORY_ROWS * BAG_INVENTORY_COLUMNS)
            {
                if (!this.mergeItemStack(itemStack, BAG_INVENTORY_ROWS * BAG_INVENTORY_COLUMNS, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemStack, 0, BAG_INVENTORY_ROWS * BAG_INVENTORY_COLUMNS, false))
            {
                return null;
            }

            if (itemStack.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return newItemStack;
    }
}

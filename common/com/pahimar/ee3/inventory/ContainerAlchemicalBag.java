package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.nbt.NBTHelper;

/**
 * Equivalent-Exchange-3
 * 
 * ContainerAlchemicalBag
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ContainerAlchemicalBag extends Container {

    private final int BAG_INVENTORY_ROWS = 4;
    private final int BAG_INVENTORY_COLUMNS = 13;

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerAlchemicalBag(InventoryPlayer inventoryPlayer) {

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * PLAYER_INVENTORY_COLUMNS + PLAYER_INVENTORY_COLUMNS, 44 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 162));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {

        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {

        super.onContainerClosed(player);

        if (!player.worldObj.isRemote) {
            InventoryPlayer invPlayer = player.inventory;
            for (ItemStack itemStack : invPlayer.mainInventory) {
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
                        NBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
                    }
                }
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {

        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();

            if (slotIndex < BAG_INVENTORY_ROWS * BAG_INVENTORY_COLUMNS) {
                if (!this.mergeItemStack(itemStack, BAG_INVENTORY_ROWS * BAG_INVENTORY_COLUMNS, inventorySlots.size(), false))
                    return null;
            }
            else if (!this.mergeItemStack(itemStack, 0, BAG_INVENTORY_ROWS * BAG_INVENTORY_COLUMNS, false))
                return null;

            if (itemStack.stackSize == 0) {
                slot.putStack((ItemStack) null);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return newItemStack;
    }
}

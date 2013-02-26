package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.tileentity.TileAlchemicalChest;

public class ContainerAlchemicalChest extends Container {

    private TileAlchemicalChest tileAlchemicalChest;

    private int numChestRows = 4;
    private int numChestColumns = 13;

    private int numPlayerRows = 3;
    private int numPlayerColumns = 9;

    public ContainerAlchemicalChest(InventoryPlayer inventoryPlayer, TileAlchemicalChest tileAlchemicalChest) {

        this.tileAlchemicalChest = tileAlchemicalChest;

        tileAlchemicalChest.openChest();

        // Add the Alchemical Chest slots to the container
        for (int chestRowIndex = 0; chestRowIndex < numChestRows; ++chestRowIndex) {
            for (int chestColumnIndex = 0; chestColumnIndex < numChestColumns; ++chestColumnIndex) {
                this.addSlotToContainer(new Slot(tileAlchemicalChest, chestColumnIndex + chestRowIndex * 9 + 9, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18));
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < numPlayerRows; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < numPlayerColumns; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 44 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < numPlayerColumns; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 162));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {

        return true;
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    public void onCraftGuiClosed(EntityPlayer entityPlayer) {

        super.onCraftGuiClosed(entityPlayer);
        this.tileAlchemicalChest.closeChest();
    }

    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {

        ItemStack newItemStack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();

            if (slotIndex < (numChestRows * numChestColumns)) {
                if (!this.mergeItemStack(itemStack, (numChestRows * numChestColumns), this.inventorySlots.size(), true)) {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemStack, 0, (numChestRows * numChestColumns), false)) {
                return null;
            }

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

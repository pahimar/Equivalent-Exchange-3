package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import com.pahimar.ee3.tileentity.TileAlchemicalChest;

public class ContainerAlchemicalChest extends Container {

    private TileAlchemicalChest tileAlchemicalChest;

    public ContainerAlchemicalChest(InventoryPlayer inventoryPlayer, TileAlchemicalChest tileAlchemicalChest) {

        this.tileAlchemicalChest = tileAlchemicalChest;

        // Add the Alchemical Chest slots to the container
        for (int chestRowIndex = 0; chestRowIndex < 4; ++chestRowIndex) {
            for (int chestColumnIndex = 0; chestColumnIndex < 13; ++chestColumnIndex) {
                this.addSlotToContainer(new Slot(tileAlchemicalChest, chestColumnIndex + chestRowIndex * 9 + 9, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18));
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 44 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 162));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {

        return true;
    }
}

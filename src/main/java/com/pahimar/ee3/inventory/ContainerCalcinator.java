package com.pahimar.ee3.inventory;

import com.pahimar.ee3.tileentity.TileEntityCalcinator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerCalcinator extends ContainerEE
{
    private TileEntityCalcinator tileEntityCalcinator;
    private int lastCookTime;               // How much longer the Calcinator will burn
    private int lastBurnTime;               // The fuel value for the currently burning fuel
    private int lastItemCookTime;           // How long the current item has been "cooking"

    public ContainerCalcinator(InventoryPlayer inventoryPlayer, TileEntityCalcinator tileEntityCalcinator)
    {
        this.tileEntityCalcinator = tileEntityCalcinator;

        // Add the fuel slot to the container
        this.addSlotToContainer(new Slot(tileEntityCalcinator, TileEntityCalcinator.FUEL_INVENTORY_INDEX, 45, 55));

        // Add the input slot to the container
        this.addSlotToContainer(new Slot(tileEntityCalcinator, TileEntityCalcinator.INPUT_INVENTORY_INDEX, 45, 10));

        // Add the output results slot to the container
        this.addSlotToContainer(new SlotCalcinator(tileEntityCalcinator, TileEntityCalcinator.OUTPUT_LEFT_INVENTORY_INDEX, 105, 29));
        this.addSlotToContainer(new SlotCalcinator(tileEntityCalcinator, TileEntityCalcinator.OUTPUT_RIGHT_INVENTORY_INDEX, 125, 29));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 94 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 152));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityCalcinator.deviceCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityCalcinator.fuelBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityCalcinator.itemCookTime);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters)
        {
            ICrafting icrafting = (ICrafting) crafter;

            if (this.lastCookTime != this.tileEntityCalcinator.deviceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityCalcinator.deviceCookTime);
            }

            if (this.lastBurnTime != this.tileEntityCalcinator.fuelBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityCalcinator.fuelBurnTime);
            }

            if (this.lastItemCookTime != this.tileEntityCalcinator.itemCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntityCalcinator.itemCookTime);
            }
        }

        this.lastCookTime = this.tileEntityCalcinator.deviceCookTime;
        this.lastBurnTime = this.tileEntityCalcinator.fuelBurnTime;
        this.lastItemCookTime = this.tileEntityCalcinator.itemCookTime;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {

            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            /**
             * If we are shift-clicking an item out of the Aludel's container,
             * attempt to put it in the first available slot in the player's
             * inventory
             */
            if (slotIndex < TileEntityCalcinator.INVENTORY_SIZE)
            {
                if (!this.mergeItemStack(slotItemStack, TileEntityCalcinator.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                /**
                 * If the stack being shift-clicked into the Aludel's container
                 * is a fuel, first try to put it in the fuel slot. If it cannot
                 * be merged into the fuel slot, try to put it in the input
                 * slot.
                 */
                if (TileEntityFurnace.isItemFuel(slotItemStack))
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityCalcinator.FUEL_INVENTORY_INDEX, TileEntityCalcinator.OUTPUT_LEFT_INVENTORY_INDEX, false))
                    {
                        return null;
                    }
                }

                /**
                 * Finally, attempt to put stack into the input slot
                 */
                else if (!this.mergeItemStack(slotItemStack, TileEntityCalcinator.INPUT_INVENTORY_INDEX, TileEntityCalcinator.OUTPUT_LEFT_INVENTORY_INDEX, false))
                {
                    return null;
                }
            }

            if (slotItemStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.tileEntityCalcinator.deviceCookTime = updatedValue;
        }

        if (valueType == 1)
        {
            this.tileEntityCalcinator.fuelBurnTime = updatedValue;
        }

        if (valueType == 2)
        {
            this.tileEntityCalcinator.itemCookTime = updatedValue;
        }
    }

    private class SlotCalcinator extends Slot
    {
        public SlotCalcinator(IInventory inventory, int slotIndex, int x, int y)
        {
            super(inventory, slotIndex, x, y);
        }

        @Override
        public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
        {
            super.onPickupFromSlot(entityPlayer, itemStack);
            FMLCommonHandler.instance().firePlayerCraftingEvent(entityPlayer, itemStack, inventory);
        }

        @Override
        public boolean isItemValid(ItemStack itemStack)
        {
            return false;
        }
    }
}

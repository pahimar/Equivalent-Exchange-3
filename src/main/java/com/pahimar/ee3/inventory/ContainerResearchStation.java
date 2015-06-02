package com.pahimar.ee3.inventory;

import com.pahimar.ee3.item.ItemAlchemicalTome;
import com.pahimar.ee3.knowledge.AbilityRegistry;
import com.pahimar.ee3.tileentity.TileEntityResearchStation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerResearchStation extends ContainerEE
{
    private TileEntityResearchStation tileEntityResearchStation;
    private int lastItemLearnTime;
    private boolean isItemStackKnown;

    public ContainerResearchStation(InventoryPlayer inventoryPlayer, TileEntityResearchStation tileEntityResearchStation)
    {
        this.tileEntityResearchStation = tileEntityResearchStation;

        this.addSlotToContainer(new Slot(tileEntityResearchStation, TileEntityResearchStation.ITEM_SLOT_INVENTORY_INDEX, 79, 84)
        {
            @Override
            public int getSlotStackLimit()
            {
                return 1;
            }

            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                return AbilityRegistry.getInstance().isLearnable(itemStack);
            }
        });

        this.addSlotToContainer(new Slot(tileEntityResearchStation, TileEntityResearchStation.TOME_SLOT_INVENTORY_INDEX, 161, 84)
        {
            @Override
            public int getSlotStackLimit()
            {
                return 1;
            }

            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                return itemStack.getItem() instanceof ItemAlchemicalTome;
            }
        });

        // Add the entityPlayer's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 50 + inventoryColumnIndex * 18, 152 + inventoryRowIndex * 18));
            }
        }

        // Add the entityPlayer's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 50 + actionBarSlotIndex * 18, 210));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityResearchStation.itemLearnTime);
        if (this.tileEntityResearchStation.isItemKnown)
        {
            iCrafting.sendProgressBarUpdate(this, 1, 1);
        }
        else
        {
            iCrafting.sendProgressBarUpdate(this, 1, 0);
        }
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters)
        {
            ICrafting iCrafting = (ICrafting) crafter;

            if (this.lastItemLearnTime != this.tileEntityResearchStation.itemLearnTime)
            {
                iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityResearchStation.itemLearnTime);
            }

            if (this.isItemStackKnown != this.tileEntityResearchStation.isItemKnown)
            {
                if (this.tileEntityResearchStation.isItemKnown)
                {
                    iCrafting.sendProgressBarUpdate(this, 1, 1);
                }
                else
                {
                    iCrafting.sendProgressBarUpdate(this, 1, 0);
                }
            }
        }

        this.lastItemLearnTime = this.tileEntityResearchStation.itemLearnTime;
        this.isItemStackKnown = this.tileEntityResearchStation.isItemKnown;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.tileEntityResearchStation.itemLearnTime = updatedValue;
        }
        else if (valueType == 1)
        {
            if (updatedValue == 1)
            {
                this.tileEntityResearchStation.isItemKnown = true;
            }
            else
            {
                this.tileEntityResearchStation.isItemKnown = false;
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.tileEntityResearchStation.isUseableByPlayer(entityPlayer);
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
             * If we are shift-clicking an item out of the Research Table's container,
             * attempt to put it in the first available slot in the entityPlayer's
             * inventory
             */
            if (slotIndex < TileEntityResearchStation.INVENTORY_SIZE)
            {
                if (!this.mergeItemStack(slotItemStack, TileEntityResearchStation.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                /**
                 * If the stack being shift-clicked into the Research Table's container
                 * is a fuel, first try to put it in the fuel slot. If it cannot
                 * be merged into the fuel slot, try to put it in the input
                 * slot.
                 */
                if (slotItemStack.getItem() instanceof ItemAlchemicalTome)
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityResearchStation.TOME_SLOT_INVENTORY_INDEX, TileEntityResearchStation.INVENTORY_SIZE, false))
                    {
                        return null;
                    }
                }
                else
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityResearchStation.ITEM_SLOT_INVENTORY_INDEX, TileEntityResearchStation.TOME_SLOT_INVENTORY_INDEX, false))
                    {
                        return null;
                    }
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
}

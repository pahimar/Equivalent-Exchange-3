package com.pahimar.ee3.inventory;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.item.ItemAlchemicalTome;
import com.pahimar.ee3.item.ItemMiniumStone;
import com.pahimar.ee3.item.ItemPhilosophersStone;
import com.pahimar.ee3.knowledge.AbilityRegistry;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTransmutationTablet extends ContainerEE implements ITextFieldElementHandler
{
    private TileEntityTransmutationTablet tileEntityTransmutationTablet;
    private EnergyValue energyValue;
    private String searchTerm;

    public ContainerTransmutationTablet(InventoryPlayer inventoryPlayer, TileEntityTransmutationTablet tileEntityTransmutationTablet)
    {
        this.tileEntityTransmutationTablet = tileEntityTransmutationTablet;

        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_1, 62, 24));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_2, 35, 35));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_3, 26, 61));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_4, 35, 87));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_5, 62, 99));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_6, 89, 87));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_7, 98, 61));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_8, 89, 35));
        this.addSlotToContainer(new Slot(tileEntityTransmutationTablet, TileEntityTransmutationTablet.STONE_INDEX, 62, 61)
        {
            @Override
            public int getSlotStackLimit()
            {
                return 1;
            }

            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                return itemStack.getItem() instanceof ItemMiniumStone || itemStack.getItem() instanceof ItemPhilosophersStone;
            }
        });
        this.addSlotToContainer(new Slot(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ALCHEMICAL_TOME_INDEX, 152, 15)
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

            @Override
            public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
            {
                super.onPickupFromSlot(entityPlayer, itemStack);

                if (!((TileEntityTransmutationTablet) this.inventory).getWorldObj().isRemote)
                {
                    LogHelper.info("onPickup: " + ItemHelper.toString(itemStack));
                    this.inventory.setInventorySlotContents(15, null);
                }
            }

            @Override
            public void putStack(ItemStack itemStack)
            {
                super.putStack(itemStack);

                if (!((TileEntityTransmutationTablet) this.inventory).getWorldObj().isRemote)
                {
                    LogHelper.info("putStack: " + ItemHelper.toString(itemStack));
                    this.inventory.setInventorySlotContents(15, itemStack);
                }
            }
        });

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                this.addSlotToContainer(new Slot(tileEntityTransmutationTablet, 10 + (j + i * 3), 175 + j * 20, 39 + i * 20)
                {
                    @Override
                    public boolean canTakeStack(EntityPlayer entityPlayer)
                    {
                        return super.canTakeStack(entityPlayer);
                    }

                    @Override
                    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
                    {
                        super.onPickupFromSlot(entityPlayer, itemStack);
                    }

                    @Override
                    @SideOnly(Side.CLIENT)
                    public boolean func_111238_b()
                    {
                        return this.getHasStack();
                    }
                });
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 164 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 222));
        }
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        this.energyValue = this.tileEntityTransmutationTablet.getStoredEnergyValue();
    }

    @Override
    public void handleElementTextFieldUpdate(String buttonName, String updatedText)
    {
        if (buttonName.equalsIgnoreCase("searchField"))
        {
            this.searchTerm = updatedText;
            updateInventory();
        }
    }

    private void updateInventory()
    {

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
             * If we are shift-clicking an item out of the Transmutation Tablet's container,
             * attempt to put it in the first available slot in the entityPlayer's
             * inventory
             */
            if (slotIndex < TileEntityTransmutationTablet.INVENTORY_SIZE)
            {
                if (!this.mergeItemStack(slotItemStack, TileEntityTransmutationTablet.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                if (slotItemStack.getItem() instanceof ItemAlchemicalTome)
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityTransmutationTablet.ALCHEMICAL_TOME_INDEX, TileEntityTransmutationTablet.INVENTORY_SIZE, false))
                    {
                        return null;
                    }
                }
                else if (slotItemStack.getItem() instanceof ItemMiniumStone || slotItemStack.getItem() instanceof ItemPhilosophersStone)
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityTransmutationTablet.STONE_INDEX, TileEntityTransmutationTablet.INVENTORY_SIZE, false))
                    {
                        return null;
                    }
                }
                else
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityTransmutationTablet.ITEM_INPUT_1, TileEntityTransmutationTablet.INVENTORY_SIZE, false))
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

    private class SlotTabletOutput extends Slot
    {
        public SlotTabletOutput(IInventory iInventory, int slotIndex, int x, int y)
        {
            super(iInventory, slotIndex, x, y);
        }

        @Override
        public boolean isItemValid(ItemStack itemStack)
        {
            return false;
        }
    }

    private class SlotTabletInput extends Slot
    {
        public SlotTabletInput(IInventory iInventory, int slotIndex, int x, int y)
        {
            super(iInventory, slotIndex, x, y);
        }

        @Override
        public boolean isItemValid(ItemStack itemStack)
        {
            return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && AbilityRegistry.getInstance().isRecoverable(itemStack);
        }
    }
}

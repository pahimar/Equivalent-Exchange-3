package com.pahimar.ee3.inventory;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.item.ItemAlchemicalTome;
import com.pahimar.ee3.item.ItemMiniumStone;
import com.pahimar.ee3.item.ItemPhilosophersStone;
import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.TreeSet;

public class ContainerTransmutationTablet extends ContainerEE implements ITextFieldElementHandler
{
    private final InventoryTransmutationTablet inventoryTransmutationTablet;
    private TileEntityTransmutationTablet tileEntityTransmutationTablet;
    private EnergyValue energyValue;
    private String searchTerm;

    public ContainerTransmutationTablet(InventoryPlayer inventoryPlayer, TileEntityTransmutationTablet tileEntityTransmutationTablet)
    {
        this.tileEntityTransmutationTablet = tileEntityTransmutationTablet;
        TreeSet<ItemStack> knownTransmutations = new TreeSet<ItemStack>(ItemHelper.displayNameComparator);
        knownTransmutations.addAll(TransmutationKnowledgeRegistry.getInstance().getPlayersKnownTransmutations(inventoryPlayer.player.getUniqueID()));
        inventoryTransmutationTablet = new InventoryTransmutationTablet(knownTransmutations);

        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_1, 120, 27));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_2, 93, 38));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_3, 84, 64));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_4, 93, 90));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_5, 120, 102));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_6, 147, 90));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_7, 156, 64));
        this.addSlotToContainer(new SlotTabletInput(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_8, 147, 38));
        this.addSlotToContainer(new Slot(tileEntityTransmutationTablet, TileEntityTransmutationTablet.STONE_INDEX, 120, 64)
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
        this.addSlotToContainer(new Slot(tileEntityTransmutationTablet, TileEntityTransmutationTablet.ALCHEMICAL_TOME_INDEX, 152, 142)
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

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 163 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 221));
        }
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        this.energyValue = this.tileEntityTransmutationTablet.getEnergyValue();
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
}

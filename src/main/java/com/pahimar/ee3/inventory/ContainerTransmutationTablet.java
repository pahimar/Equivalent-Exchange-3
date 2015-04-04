package com.pahimar.ee3.inventory;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.inventory.element.IElementButtonHandler;
import com.pahimar.ee3.inventory.element.IElementSliderHandler;
import com.pahimar.ee3.inventory.element.IElementTextFieldHandler;
import com.pahimar.ee3.item.ItemAlchemicalTome;
import com.pahimar.ee3.item.ItemMiniumStone;
import com.pahimar.ee3.item.ItemPhilosophersStone;
import com.pahimar.ee3.knowledge.AbilityRegistry;
import com.pahimar.ee3.knowledge.TransmutationKnowledge;
import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTransmutationKnowledgeUpdate;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.FilterUtils;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.*;

public class ContainerTransmutationTablet extends ContainerEE implements IElementTextFieldHandler, IElementSliderHandler, IElementButtonHandler
{
    private InventoryTransmutationTablet inventoryTransmutationTablet;
    public final TileEntityTransmutationTablet tileEntityTransmutationTablet;
    private float energyValue;
    private String searchTerm;
    private int sortOrder;

    public ContainerTransmutationTablet(InventoryPlayer inventoryPlayer, TileEntityTransmutationTablet tileEntityTransmutationTablet)
    {
        this.tileEntityTransmutationTablet = tileEntityTransmutationTablet;

        TreeSet<ItemStack> knownTransmutations = new TreeSet<ItemStack>(ItemHelper.displayNameComparator);
        if (tileEntityTransmutationTablet.getStackInSlot(TileEntityTransmutationTablet.ALCHEMICAL_TOME_INDEX) != null)
        {
            ItemStack itemStack = tileEntityTransmutationTablet.getStackInSlot(TileEntityTransmutationTablet.ALCHEMICAL_TOME_INDEX);
            if (itemStack.getItem() instanceof ItemAlchemicalTome && ItemHelper.hasOwnerUUID(itemStack))
            {
                knownTransmutations.addAll(TransmutationKnowledgeRegistry.getInstance().getPlayersKnownTransmutations(ItemHelper.getOwnerUUID(itemStack)));
            }
        }
        inventoryTransmutationTablet = new InventoryTransmutationTablet(knownTransmutations);

        this.sortOrder = 0;
        this.energyValue = tileEntityTransmutationTablet.getStoredEnergyValue().getEnergyValue();

        this.addSlotToContainer(new SlotTabletInput(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_1, 62, 24));
        this.addSlotToContainer(new SlotTabletInput(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_2, 35, 35));
        this.addSlotToContainer(new SlotTabletInput(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_3, 26, 61));
        this.addSlotToContainer(new SlotTabletInput(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_4, 35, 87));
        this.addSlotToContainer(new SlotTabletInput(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_5, 62, 99));
        this.addSlotToContainer(new SlotTabletInput(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_6, 89, 87));
        this.addSlotToContainer(new SlotTabletInput(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_7, 98, 61));
        this.addSlotToContainer(new SlotTabletInput(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_8, 89, 35));
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
        this.addSlotToContainer(new SlotAlchemicalTome(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ALCHEMICAL_TOME_INDEX, 152, 15));

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                this.addSlotToContainer(new SlotTabletOutput(this, inventoryTransmutationTablet, i * 3 + j, 175 + j * 20, 38 + i * 20));
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

        this.updateInventory();
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters)
        {
            ICrafting iCrafting = (ICrafting) crafter;

            if (this.energyValue != this.tileEntityTransmutationTablet.getStoredEnergyValue().getEnergyValue())
            {
                this.energyValue = this.tileEntityTransmutationTablet.getStoredEnergyValue().getEnergyValue();
                this.updateInventory();
                int energyValueAsInt = Float.floatToRawIntBits(this.tileEntityTransmutationTablet.getStoredEnergyValue().getEnergyValue());
                iCrafting.sendProgressBarUpdate(this, 0, energyValueAsInt & 0xffff);
                iCrafting.sendProgressBarUpdate(this, 1, energyValueAsInt >>> 16);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            int energyValueAsInt = Float.floatToRawIntBits(energyValue);
            energyValueAsInt = (energyValueAsInt & 0xffff0000) | updatedValue;
            energyValue = Float.intBitsToFloat(energyValueAsInt);
        }
        else if (valueType == 1)
        {
            int energyValueAsInt = Float.floatToRawIntBits(energyValue);
            energyValueAsInt = (energyValueAsInt & 0xffff) | (updatedValue << 16);
            energyValue = Float.intBitsToFloat(energyValueAsInt);
        }
        else if (valueType == 2)
        {
            sortOrder = updatedValue;
        }

        if (valueType == 0 || valueType == 1 || valueType == 2)
        {
            updateInventory();
        }
    }

    @Override
    public void handleElementTextFieldUpdate(String elementName, String updatedText)
    {
        if (elementName.equalsIgnoreCase("searchField"))
        {
            this.searchTerm = updatedText;
            updateInventory();
        }
    }

    @Override
    public void handleElementSliderUpdate(String elementName, int elementValue)
    {
        LogHelper.info(elementValue);
    }

    public void handleTransmutationKnowledgeUpdate(TransmutationKnowledge transmutationKnowledge)
    {
        if (transmutationKnowledge != null)
        {
            this.inventoryTransmutationTablet = new InventoryTransmutationTablet(transmutationKnowledge.getKnownTransmutations());
            this.updateInventory();
        }
    }

    private void updateInventory()
    {
        ItemStack[] newInventory = new ItemStack[30];

        Set<ItemStack> filteredSet = FilterUtils.filterByNameContains(this.inventoryTransmutationTablet.getKnownTransmutations(), searchTerm);
        List<ItemStack> filteredList = new ArrayList(FilterUtils.filterByEnergyValue(filteredSet, energyValue));

        if (sortOrder == 0)
        {
            Collections.sort(filteredList, ItemHelper.displayNameComparator);
        }
        else if (sortOrder == 1)
        {
            Collections.sort(filteredList, ItemHelper.energyValueComparator);
        }
        else if (sortOrder == 2)
        {
            Collections.sort(filteredList, ItemHelper.idComparator);
        }

        if (filteredList.size() <= 30)
        {
            newInventory = filteredList.toArray(newInventory);
        }
        else
        {
            newInventory = filteredList.subList(0, 30).toArray(newInventory);
        }

        for (int i = 0; i < 30; i++)
        {
            this.getSlot(i + 10).putStack(newInventory[i]);
        }
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

    @Override
    public void handleElementButtonClick(String elementName, int mouseButton)
    {
        if (elementName.equals("sortOrder"))
        {
            if (mouseButton == 0)
            {
                if (sortOrder == 0)
                {
                    sortOrder = 1;
                }
                else if (sortOrder == 1)
                {
                    sortOrder = 2;
                }
                else if (sortOrder == 2)
                {
                    sortOrder = 0;
                }
            }
            else if (mouseButton == 1)
            {
                if (sortOrder == 0)
                {
                    sortOrder = 2;
                }
                else if (sortOrder == 1)
                {
                    sortOrder = 0;
                }
                else if (sortOrder == 2)
                {
                    sortOrder = 1;
                }
            }
        }
    }

    private class SlotAlchemicalTome extends Slot
    {
        private ContainerTransmutationTablet containerTransmutationTablet;
        private TileEntityTransmutationTablet tileEntityTransmutationTablet;

        public SlotAlchemicalTome(ContainerTransmutationTablet containerTransmutationTablet, IInventory iInventory, int slotIndex, int x, int y)
        {
            super(iInventory, slotIndex, x, y);
            this.containerTransmutationTablet = containerTransmutationTablet;
            this.tileEntityTransmutationTablet = containerTransmutationTablet.tileEntityTransmutationTablet;
        }

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

            this.containerTransmutationTablet.inventoryTransmutationTablet = new InventoryTransmutationTablet();
            this.containerTransmutationTablet.updateInventory();

            if (!this.tileEntityTransmutationTablet.getWorldObj().isRemote && itemStack != null && itemStack.getItem() instanceof ItemAlchemicalTome && ItemHelper.hasOwnerUUID(itemStack))
            {
                PacketHandler.INSTANCE.sendToAllAround(new MessageTransmutationKnowledgeUpdate(this.containerTransmutationTablet.tileEntityTransmutationTablet, null), new NetworkRegistry.TargetPoint(this.tileEntityTransmutationTablet.getWorldObj().provider.dimensionId, (double) this.tileEntityTransmutationTablet.xCoord, (double) this.tileEntityTransmutationTablet.yCoord, (double) this.tileEntityTransmutationTablet.zCoord, 5d));
            }
        }

        @Override
        public void putStack(ItemStack itemStack)
        {
            super.putStack(itemStack);

            if (!this.tileEntityTransmutationTablet.getWorldObj().isRemote && itemStack != null && itemStack.getItem() instanceof ItemAlchemicalTome && ItemHelper.hasOwnerUUID(itemStack))
            {
                Set<ItemStack> knownTransmutations = TransmutationKnowledgeRegistry.getInstance().getPlayersKnownTransmutations(ItemHelper.getOwnerUUID(itemStack));
                this.containerTransmutationTablet.inventoryTransmutationTablet = new InventoryTransmutationTablet(knownTransmutations);
                this.containerTransmutationTablet.updateInventory();
                PacketHandler.INSTANCE.sendToAllAround(new MessageTransmutationKnowledgeUpdate(this.containerTransmutationTablet.tileEntityTransmutationTablet, knownTransmutations), new NetworkRegistry.TargetPoint(this.tileEntityTransmutationTablet.getWorldObj().provider.dimensionId, (double) this.tileEntityTransmutationTablet.xCoord, (double) this.tileEntityTransmutationTablet.yCoord, (double) this.tileEntityTransmutationTablet.zCoord, 5d));
            }
        }
    }

    private class SlotTabletOutput extends Slot
    {
        private ContainerTransmutationTablet containerTransmutationTablet;

        public SlotTabletOutput(ContainerTransmutationTablet containerTransmutationTablet, IInventory iInventory, int slotIndex, int x, int y)
        {
            super(iInventory, slotIndex, x, y);
            this.containerTransmutationTablet = containerTransmutationTablet;
        }

        @Override
        public boolean isItemValid(ItemStack itemStack)
        {
            return false;
        }

        @Override
        public boolean canTakeStack(EntityPlayer entityPlayer)
        {
            return false;
        }

        @Override
        public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
        {
            super.onPickupFromSlot(entityPlayer, itemStack);
            this.containerTransmutationTablet.inventoryTransmutationTablet.setInventorySlotContents(this.getSlotIndex(), new ItemStack(itemStack.getItem(), 1, itemStack.getItemDamage()));
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean func_111238_b()
        {
            return this.getHasStack();
        }
    }

    private class SlotTabletInput extends Slot
    {
        private ContainerTransmutationTablet containerTransmutationTablet;

        public SlotTabletInput(ContainerTransmutationTablet containerTransmutationTablet, IInventory iInventory, int slotIndex, int x, int y)
        {
            super(iInventory, slotIndex, x, y);
            this.containerTransmutationTablet = containerTransmutationTablet;
        }

        @Override
        public boolean isItemValid(ItemStack itemStack)
        {
            return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && AbilityRegistry.getInstance().isRecoverable(itemStack);
        }

        @Override
        public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
        {
            super.onPickupFromSlot(entityPlayer, itemStack);
            this.containerTransmutationTablet.tileEntityTransmutationTablet.updateEnergyValueFromInventory();
            this.containerTransmutationTablet.updateInventory();
        }

        @Override
        public void putStack(ItemStack itemStack)
        {
            super.putStack(itemStack);
            this.containerTransmutationTablet.tileEntityTransmutationTablet.updateEnergyValueFromInventory();
            this.containerTransmutationTablet.updateInventory();
        }
    }
}

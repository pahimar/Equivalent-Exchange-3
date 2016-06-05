package com.pahimar.ee3.inventory;

import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.knowledge.PlayerKnowledgeRegistryProxy;
import com.pahimar.ee3.inventory.element.IElementButtonHandler;
import com.pahimar.ee3.inventory.element.IElementSliderHandler;
import com.pahimar.ee3.inventory.element.IElementTextFieldHandler;
import com.pahimar.ee3.item.ItemAlchenomicon;
import com.pahimar.ee3.item.ItemMiniumStone;
import com.pahimar.ee3.item.ItemPhilosophersStone;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessagePlayerKnowledge;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.FilterUtils;
import com.pahimar.ee3.util.ItemStackUtils;
import com.pahimar.repackage.cofh.lib.util.helpers.MathHelper;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.*;

/**
 * FIXME Continue integrating PR#881
 */
public class ContainerTransmutationTablet extends ContainerEE implements IElementTextFieldHandler, IElementSliderHandler, IElementButtonHandler {

    private InventoryTransmutationTablet inventoryTransmutationTablet;
    private final TileEntityTransmutationTablet transmutationTablet;
    private final World world;
    private EnergyValue energyValue;
    private String searchTerm;
    private int sortOption, sortOrder, scrollBarPosition;

    public ContainerTransmutationTablet(InventoryPlayer inventoryPlayer, TileEntityTransmutationTablet transmutationTablet) {

        this.transmutationTablet = transmutationTablet;
        this.world = transmutationTablet.getWorldObj();

        handleTomeSync(transmutationTablet.getStackInSlot(TileEntityTransmutationTablet.ALCHENOMICON_INDEX));

        this.sortOption = 0;
        this.scrollBarPosition = 0;
        this.energyValue = transmutationTablet.getAvailableEnergy();

        this.addSlotToContainer(new SlotTabletInput(this, transmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_1, 62, 24));
        this.addSlotToContainer(new SlotTabletInput(this, transmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_2, 35, 35));
        this.addSlotToContainer(new SlotTabletInput(this, transmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_3, 26, 61));
        this.addSlotToContainer(new SlotTabletInput(this, transmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_4, 35, 87));
        this.addSlotToContainer(new SlotTabletInput(this, transmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_5, 62, 99));
        this.addSlotToContainer(new SlotTabletInput(this, transmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_6, 89, 87));
        this.addSlotToContainer(new SlotTabletInput(this, transmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_7, 98, 61));
        this.addSlotToContainer(new SlotTabletInput(this, transmutationTablet, TileEntityTransmutationTablet.ITEM_INPUT_8, 89, 35));
        this.addSlotToContainer(new Slot(transmutationTablet, TileEntityTransmutationTablet.STONE_INDEX, 62, 61) {
            @Override
            public int getSlotStackLimit() {
                return 1;
            }

            @Override
            public boolean isItemValid(ItemStack itemStack) {
                return itemStack.getItem() instanceof ItemMiniumStone || itemStack.getItem() instanceof ItemPhilosophersStone;
            }
        });
        this.addSlotToContainer(new SlotAlchenomicon(transmutationTablet, TileEntityTransmutationTablet.ALCHENOMICON_INDEX, 152, 15));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                this.addSlotToContainer(new SlotTabletOutput(this, inventoryTransmutationTablet, i * 3 + j, 175 + j * 20, 38 + i * 20));
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 164 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 222));
        }

        updateInventory();
    }

    public EnergyValue getEnergyValue() {
        return energyValue;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return transmutationTablet != null && transmutationTablet.isUseableByPlayer(entityPlayer);
    }

    @Override
    public void detectAndSendChanges() {

        super.detectAndSendChanges();

        EnergyValue tileEnergyValue = transmutationTablet.getAvailableEnergy();
        for (Object crafter : this.crafters) {

            ICrafting iCrafting = (ICrafting) crafter;

            if (energyValue.compareTo(tileEnergyValue) != 0) {
                energyValue = tileEnergyValue;

                int energyValueAsInt = Float.floatToRawIntBits(tileEnergyValue.getValue());
                iCrafting.sendProgressBarUpdate(this, 0, energyValueAsInt & 0xffff);
                iCrafting.sendProgressBarUpdate(this, 1, energyValueAsInt >>> 16);
            }
        }

        this.updateInventory();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue) {

        if (valueType == 0) {
            int energyValueAsInt = Float.floatToRawIntBits(energyValue.getValue());
            energyValueAsInt = (energyValueAsInt & 0xffff0000) | updatedValue;
            energyValue = new EnergyValue(Float.intBitsToFloat(energyValueAsInt));
        }
        else if (valueType == 1) {
            int energyValueAsInt = Float.floatToRawIntBits(energyValue.getValue());
            energyValueAsInt = (energyValueAsInt & 0xffff) | (updatedValue << 16);
            energyValue = new EnergyValue(Float.intBitsToFloat(energyValueAsInt));
        }
        else if (valueType == 2) {
            sortOption = updatedValue;
        }
        else if (valueType == 3) {
            scrollBarPosition = updatedValue;
        }
        else if (valueType == 4) {
            sortOrder = updatedValue;
        }

        if (valueType >= 0 && valueType <= 4) {
            updateInventory();
        }
    }

    private void sendKnowledgeToClient(Collection<ItemStack> knownItemStacks) {

        Network.INSTANCE.sendToAllAround(
                new MessagePlayerKnowledge(transmutationTablet, knownItemStacks),
                new NetworkRegistry.TargetPoint(world.provider.dimensionId, transmutationTablet.xCoord, transmutationTablet.yCoord, transmutationTablet.zCoord, 5d)
        );
    }

    private void handleTomeSync(ItemStack itemStack) {

        if (itemStack != null && itemStack.getItem() instanceof ItemAlchenomicon && ItemStackUtils.getOwnerName(itemStack) != null) {

            if (!world.isRemote) {

                Set<ItemStack> knownItemStacks = PlayerKnowledgeRegistryProxy.getKnownItemStacks(ItemStackUtils.getOwnerName(itemStack));
                inventoryTransmutationTablet = new InventoryTransmutationTablet(knownItemStacks);
                sendKnowledgeToClient(knownItemStacks);
            }
            else {
                this.inventoryTransmutationTablet = new InventoryTransmutationTablet(transmutationTablet.getPlayerKnowledge());
            }
        }
        else {

            this.inventoryTransmutationTablet = new InventoryTransmutationTablet();
            if (!world.isRemote) {
                sendKnowledgeToClient(null);
            }
        }
    }

    @Override
    public void handleElementTextFieldUpdate(String elementName, String updatedText) {

        if (elementName.equalsIgnoreCase("searchField")) {
            searchTerm = updatedText;
            updateInventory();
        }
    }

    @Override
    public void handleElementSliderUpdate(String elementName, int elementValue) {

        if (elementName.equals("scrollBar")) {
            scrollBarPosition = elementValue;
            updateInventory();
        }
    }

    private void updateInventory() {
        
        ItemStack[] newInventory = new ItemStack[30];

        Set<ItemStack> filteredSet = FilterUtils.filterByDisplayName(inventoryTransmutationTablet.getKnownTransmutations(), searchTerm, FilterUtils.NameFilterType.CONTAINS);
        List<ItemStack> filteredList = new ArrayList<>(FilterUtils.filterByEnergyValue(filteredSet, energyValue));

        if (sortOption == 0 && sortOrder == 0) {
            Collections.sort(filteredList, Comparators.DISPLAY_NAME_COMPARATOR);
        }
        else if (sortOption == 0 && sortOrder == 1) {
            Collections.sort(filteredList, Comparators.DISPLAY_NAME_COMPARATOR.reversed());
        }
        else if (sortOption == 1 && sortOrder == 0) {
            Collections.sort(filteredList, Comparators.ENERGY_VALUE_ITEM_STACK_COMPARATOR);
        }
        else if (sortOption == 1 && sortOrder == 1) {
            Collections.sort(filteredList, Comparators.ENERGY_VALUE_ITEM_STACK_COMPARATOR.reversed());
        }
        else if (sortOption == 2 && sortOrder == 0) {
            Collections.sort(filteredList, Comparators.ID_COMPARATOR);
        }
        else if (sortOption == 2 && sortOrder == 1) {
            Collections.sort(filteredList, Comparators.ID_COMPARATOR.reversed());
        }

        if (filteredList.size() <= 30) {
            this.scrollBarPosition = 0;
        }

        int adjustedStartIndex = (int) ((scrollBarPosition / 187f) * filteredList.size());
        adjustedStartIndex -= adjustedStartIndex % 3; // Paginate by 3 elements.

        int startIndex = Math.max(0, Math.min(adjustedStartIndex, filteredList.size() - 30));
        int endIndex = Math.min(adjustedStartIndex + 30, filteredList.size());

        filteredList.subList(startIndex, endIndex).toArray(newInventory);

        for (int i = 0; i < 30; i++) {
            this.getSlot(i + 10).putStack(newInventory[i]);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {

        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {

            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            /**
             * If we are shift-clicking an item out of the Transmutation Tablet's container,
             * attempt to put it in the first available slot in the entityPlayer's
             * inventory
             */
            if (slotIndex < TileEntityTransmutationTablet.INVENTORY_SIZE) {

                if (!this.mergeItemStack(slotItemStack, TileEntityTransmutationTablet.INVENTORY_SIZE, inventorySlots.size(), false)) {
                    return null;
                }
            }
            else if (slotIndex >= TileEntityTransmutationTablet.INVENTORY_SIZE && slotIndex < 40) {

                if (!this.mergeTransmutedItemStack(entityPlayer, slot, slotItemStack, 40, inventorySlots.size(), false)) {
                    return null;
                }
            }
            else {

                if (slotItemStack.getItem() instanceof ItemAlchenomicon) {

                    if (!this.mergeItemStack(slotItemStack, TileEntityTransmutationTablet.ALCHENOMICON_INDEX, TileEntityTransmutationTablet.INVENTORY_SIZE, false)) {
                        return null;
                    }
                }
                else if (slotItemStack.getItem() instanceof ItemMiniumStone || slotItemStack.getItem() instanceof ItemPhilosophersStone) {

                    if (!this.mergeItemStack(slotItemStack, TileEntityTransmutationTablet.STONE_INDEX, TileEntityTransmutationTablet.INVENTORY_SIZE, false)) {
                        return null;
                    }
                }
                else {

                    if (!this.mergeItemStack(slotItemStack, TileEntityTransmutationTablet.ITEM_INPUT_1, TileEntityTransmutationTablet.INVENTORY_SIZE, false)) {
                        return null;
                    }
                }
            }

            if (slotItemStack.stackSize == 0) {
                slot.putStack(null);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }

    private boolean mergeTransmutedItemStack(EntityPlayer entityPlayer, Slot transmutationOutputSlot, ItemStack itemStack, int slotMin, int slotMax, boolean ascending) {

        // Calculate how many items can be transmuted
        int numCanTransmute = MathHelper.floor(this.transmutationTablet.getAvailableEnergy().getValue() / EnergyValueRegistryProxy.getEnergyValue(itemStack).getValue());
        int numTransmuted = 0;

        ItemStack itemStack1 = itemStack.copy();
        itemStack1.stackSize = Math.min(numCanTransmute, itemStack1.getMaxStackSize());

        if (numCanTransmute <= 0) {
            return false;
        }

        int currentSlotIndex = ascending ? slotMax - 1 : slotMin;

        Slot slot;
        ItemStack stackInSlot;

        while (itemStack1.stackSize > 0 && (!ascending && currentSlotIndex < slotMax || ascending && currentSlotIndex >= slotMin)) {

            slot = (Slot) this.inventorySlots.get(currentSlotIndex);
            stackInSlot = slot.getStack();

            if (stackInSlot == null) {

                stackInSlot = itemStack1.copy();
                stackInSlot.stackSize = itemStack1.stackSize;
                slot.putStack(stackInSlot);
                numTransmuted = itemStack1.stackSize;
                itemStack1.stackSize = 0;
                slot.onSlotChanged();
            }
            else if (slot.isItemValid(itemStack1) && ItemStackUtils.equalsIgnoreStackSize(itemStack1, stackInSlot)) {

                int slotStackSizeLimit = Math.min(stackInSlot.getMaxStackSize(), slot.getSlotStackLimit());
                int combinedStackSize = stackInSlot.stackSize + itemStack1.stackSize;

                if (combinedStackSize <= slotStackSizeLimit) {

                    stackInSlot.stackSize = combinedStackSize;
                    numTransmuted = itemStack1.stackSize;
                    itemStack1.stackSize = 0;
                    slot.onSlotChanged();
                }
                else if (stackInSlot.stackSize < slotStackSizeLimit) {

                    itemStack1.stackSize = slotStackSizeLimit - stackInSlot.stackSize;
                    stackInSlot.stackSize = slotStackSizeLimit;
                    numTransmuted = itemStack1.stackSize;
                    itemStack1.stackSize = 0;
                    slot.onSlotChanged();
                }
            }

            currentSlotIndex += ascending ? -1 : 1;
        }

        ((SlotTabletOutput) transmutationOutputSlot).onShiftPickupFromSlot(entityPlayer, ItemStackUtils.clone(itemStack, numTransmuted));

        return false;
    }

    @Override
    public void handleElementButtonClick(String elementName, int mouseButton) {

        if (elementName.equals("sortOption")) {
            if (mouseButton == 0) {
                sortOption = (sortOption + 1) % 3;
            }
            else if (mouseButton == 1) {
                sortOption = (sortOption + (3 - 1)) % 3;
            }
        }
        else if (elementName.equals("sortOrder")) {
            sortOrder = 1 - sortOrder; // Toggle between 0 and 1
        }

        for (Object crafter : this.crafters) {

            ICrafting iCrafting = (ICrafting) crafter;
            iCrafting.sendProgressBarUpdate(this, 2, sortOption);
            iCrafting.sendProgressBarUpdate(this, 4, sortOrder);
        }
    }

    @Override
    public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {

    	if (button == 0 && flag == 6) {
    		return null;
    	}

    	return super.slotClick(slot, button, flag, player);
    }

    private class SlotAlchenomicon extends Slot {

        public SlotAlchenomicon(IInventory iInventory, int slotIndex, int x, int y) {
            super(iInventory, slotIndex, x, y);
        }

        @Override
        public int getSlotStackLimit() {
            return 1;
        }

        @Override
        public boolean isItemValid(ItemStack itemStack) {
            return itemStack.getItem() instanceof ItemAlchenomicon;
        }

        @Override
        public void onSlotChanged() {

            super.onSlotChanged();
            handleTomeSync(getStack());
            updateInventory();
        }
    }

    private class SlotTabletOutput extends Slot {

        private ContainerTransmutationTablet containerTransmutationTablet;

        public SlotTabletOutput(ContainerTransmutationTablet containerTransmutationTablet, IInventory iInventory, int slotIndex, int x, int y) {

            super(iInventory, slotIndex, x, y);
            this.containerTransmutationTablet = containerTransmutationTablet;
        }

        @Override
        public boolean isItemValid(ItemStack itemStack) {
            return false;
        }

        @Override
        public boolean canTakeStack(EntityPlayer entityPlayer) {
            return this.getHasStack();
        }

        @Override
        public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {

            super.onPickupFromSlot(entityPlayer, itemStack);

            if (getHasStack()) {
                ItemStack unitItemStack = ItemStackUtils.clone(itemStack, 1);
                transmutationTablet.consumeInventoryForEnergyValue(unitItemStack);
            }

            updateInventory();
        }

        public void onShiftPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {

            super.onPickupFromSlot(entityPlayer, itemStack);

            if (getHasStack()) {
                transmutationTablet.consumeInventoryForEnergyValue(itemStack);
            }

            updateInventory();
        }

        @Override
        public void onSlotChanged() {

            super.onSlotChanged();
            transmutationTablet.updateEnergyValueFromInventory();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean func_111238_b() {
            return this.getHasStack();
        }
    }

    private class SlotTabletInput extends Slot {

        private ContainerTransmutationTablet containerTransmutationTablet;

        public SlotTabletInput(ContainerTransmutationTablet containerTransmutationTablet, IInventory iInventory, int slotIndex, int x, int y) {

            super(iInventory, slotIndex, x, y);
            this.containerTransmutationTablet = containerTransmutationTablet;
        }

        @Override
        public boolean isItemValid(ItemStack itemStack) {
            return EnergyValueRegistryProxy.hasEnergyValue(itemStack) && BlacklistRegistryProxy.isExchangeable(itemStack);
        }

        @Override
        public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {

            super.onPickupFromSlot(entityPlayer, itemStack);
            this.containerTransmutationTablet.transmutationTablet.updateEnergyValueFromInventory();
            this.containerTransmutationTablet.updateInventory();
        }

        @Override
        public void putStack(ItemStack itemStack) {

            super.putStack(itemStack);
            this.containerTransmutationTablet.transmutationTablet.updateEnergyValueFromInventory();
            this.containerTransmutationTablet.updateInventory();
        }
    }
}

package com.pahimar.ee3.inventory;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.knowledge.AbilityRegistryProxy;
import com.pahimar.ee3.api.knowledge.PlayerKnowledgeRegistryProxy;
import com.pahimar.ee3.inventory.element.IElementButtonHandler;
import com.pahimar.ee3.inventory.element.IElementSliderHandler;
import com.pahimar.ee3.inventory.element.IElementTextFieldHandler;
import com.pahimar.ee3.item.ItemAlchenomicon;
import com.pahimar.ee3.item.ItemMiniumStone;
import com.pahimar.ee3.item.ItemPhilosophersStone;
import com.pahimar.ee3.knowledge.PlayerKnowledge;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessagePlayerKnowledge;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.FilterUtils;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.repackage.cofh.lib.util.helpers.MathHelper;
import cpw.mods.fml.common.FMLCommonHandler;
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
    private int sortOption;
    private int sortOrder;
    private int scrollBarPosition;

    public ContainerTransmutationTablet(InventoryPlayer inventoryPlayer, TileEntityTransmutationTablet tileEntityTransmutationTablet)
    {
        this.tileEntityTransmutationTablet = tileEntityTransmutationTablet;

        TreeSet<ItemStack> knownTransmutations = new TreeSet<ItemStack>(Comparators.DISPLAY_NAME_COMPARATOR);
        if (tileEntityTransmutationTablet.getStackInSlot(TileEntityTransmutationTablet.ALCHENOMICON_INDEX) != null)
        {
            ItemStack itemStack = tileEntityTransmutationTablet.getStackInSlot(TileEntityTransmutationTablet.ALCHENOMICON_INDEX);
            if (itemStack.getItem() instanceof ItemAlchenomicon && ItemHelper.hasOwnerName(itemStack))
            {
                knownTransmutations.addAll(PlayerKnowledgeRegistryProxy.getKnownItemStacks(ItemHelper.getOwnerName(itemStack)));
            }
        }
        inventoryTransmutationTablet = new InventoryTransmutationTablet(knownTransmutations);

        this.sortOption = 0;
        this.scrollBarPosition = 0;
        this.energyValue = tileEntityTransmutationTablet.getAvailableEnergyValue().getValue();

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
        this.addSlotToContainer(new SlotAlchenomicon(this, tileEntityTransmutationTablet, TileEntityTransmutationTablet.ALCHENOMICON_INDEX, 152, 15));

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
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.tileEntityTransmutationTablet != null && this.tileEntityTransmutationTablet.isStructureValid();
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters)
        {
            ICrafting iCrafting = (ICrafting) crafter;

            if (this.energyValue != this.tileEntityTransmutationTablet.getAvailableEnergyValue().getValue())
            {
                this.energyValue = this.tileEntityTransmutationTablet.getAvailableEnergyValue().getValue();
                this.updateInventory();
                int energyValueAsInt = Float.floatToRawIntBits(this.tileEntityTransmutationTablet.getAvailableEnergyValue().getValue());
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
            sortOption = updatedValue;
        }
        else if (valueType == 3)
        {
            scrollBarPosition = updatedValue;
        }
        else if (valueType == 4)
        {
            sortOrder = updatedValue;
        }

        if (valueType >= 0 && valueType <= 4)
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
        if (elementName.equals("scrollBar"))
        {
            this.scrollBarPosition = elementValue;
            updateInventory();
        }
    }

    public void handlePlayerKnowledgeUpdate(PlayerKnowledge playerKnowledge) {

        if (playerKnowledge != null) {
            this.inventoryTransmutationTablet = new InventoryTransmutationTablet(playerKnowledge.getKnownItemStacks());
            this.updateInventory();
        }
    }

    private void updateInventory() {
        
        ItemStack[] newInventory = new ItemStack[30];

        Set<ItemStack> filteredSet = FilterUtils.filterByDisplayName(this.inventoryTransmutationTablet.getKnownTransmutations(), searchTerm, FilterUtils.NameFilterType.CONTAINS);
        List<ItemStack> filteredList = new ArrayList(FilterUtils.filterByEnergyValue(filteredSet, energyValue));

        int adjustedStartIndex = (int) ((scrollBarPosition / 187f) * filteredList.size());

        if (sortOption == 0) {

            if (sortOrder == 0) {
                Collections.sort(filteredList, Comparators.DISPLAY_NAME_COMPARATOR);
            }
            else {
                Collections.sort(filteredList, Comparators.DISPLAY_NAME_COMPARATOR.reversed());
            }
        }
        else if (sortOption == 1) {

            if (sortOrder == 0) {
                Collections.sort(filteredList, Comparators.ENERGY_VALUE_ITEM_STACK_COMPARATOR);
            }
            else {
                Collections.sort(filteredList, Comparators.ENERGY_VALUE_ITEM_STACK_COMPARATOR.reversed());
            }
        }
        else if (sortOption == 2) {

            if (sortOrder == 0) {
                Collections.sort(filteredList, Comparators.ID_COMPARATOR);
            }
            else {
                Collections.sort(filteredList, Comparators.ID_COMPARATOR.reversed());
            }
        }

        if (filteredList.size() <= 30) {
            newInventory = filteredList.toArray(newInventory);
        }
        else if (adjustedStartIndex + 30 <= filteredList.size()) {
            newInventory = filteredList.subList(adjustedStartIndex, adjustedStartIndex + 30).toArray(newInventory);
        }
        else {
            newInventory = filteredList.subList(filteredList.size() - 30, filteredList.size()).toArray(newInventory);
        }

        for (int i = 0; i < 30; i++) {
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
            else if (slotIndex >= TileEntityTransmutationTablet.INVENTORY_SIZE && slotIndex < 40)
            {
                if (!this.mergeTransmutedItemStack(entityPlayer, slot, slotItemStack, 40, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                if (slotItemStack.getItem() instanceof ItemAlchenomicon)
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityTransmutationTablet.ALCHENOMICON_INDEX, TileEntityTransmutationTablet.INVENTORY_SIZE, false))
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

    protected boolean mergeTransmutedItemStack(EntityPlayer entityPlayer, Slot transmutationOutputSlot, ItemStack itemStack, int slotMin, int slotMax, boolean ascending) {

        // Calculate how many items can be transmuted
        int numCanTransmute = MathHelper.floor(this.tileEntityTransmutationTablet.getAvailableEnergyValue().getValue() / EnergyValueRegistryProxy.getEnergyValue(itemStack).getValue());
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

                stackInSlot = new ItemStack(itemStack1.getItem());
                stackInSlot.stackSize = itemStack1.stackSize;
                slot.putStack(stackInSlot);
                numTransmuted = itemStack1.stackSize;
                itemStack1.stackSize = 0;
                slot.onSlotChanged();
            }
            else if (slot.isItemValid(itemStack1) && ItemHelper.equalsIgnoreStackSize(itemStack1, stackInSlot)) {

                int slotStackSizeLimit = Math.min(stackInSlot.getMaxStackSize(), slot.getSlotStackLimit());
                int combinedStackSize = stackInSlot.stackSize + itemStack1.stackSize;

                if (combinedStackSize <= slotStackSizeLimit) {

                    stackInSlot.stackSize = combinedStackSize;
                    numTransmuted = stackInSlot.stackSize - itemStack1.stackSize;
                    itemStack1.stackSize = 0;
                    slot.onSlotChanged();
                }
                else if (stackInSlot.stackSize < slotStackSizeLimit) {

                    itemStack1.stackSize -= slotStackSizeLimit - stackInSlot.stackSize;
                    stackInSlot.stackSize = slotStackSizeLimit;
                    numTransmuted = stackInSlot.stackSize - itemStack1.stackSize;
                    slot.onSlotChanged();
                }
            }

            currentSlotIndex += ascending ? -1 : 1;
        }

        transmutationOutputSlot.onPickupFromSlot(entityPlayer, new ItemStack(itemStack.getItem(), numTransmuted));

        return false;
    }

    @Override
    public void handleElementButtonClick(String elementName, int mouseButton)
    {
        if (elementName.equals("sortOption"))
        {
            if (mouseButton == 0)
            {
                if (sortOption == 0)
                {
                    sortOption = 1;
                }
                else if (sortOption == 1)
                {
                    sortOption = 2;
                }
                else if (sortOption == 2)
                {
                    sortOption = 0;
                }
            }
            else if (mouseButton == 1)
            {
                if (sortOption == 0)
                {
                    sortOption = 2;
                }
                else if (sortOption == 1)
                {
                    sortOption = 0;
                }
                else if (sortOption == 2)
                {
                    sortOption = 1;
                }
            }
        }
        else if (elementName.equals("sortOrder"))
        {
            if (sortOrder == 0)
            {
                sortOrder = 1;
            }
            else if (sortOrder == 1)
            {
                sortOrder = 0;
            }
        }

        for (Object crafter : this.crafters)
        {
            ICrafting iCrafting = (ICrafting) crafter;
            iCrafting.sendProgressBarUpdate(this, 2, sortOption);
            iCrafting.sendProgressBarUpdate(this, 4, sortOrder);
        }
    }

    @Override
    public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player)
    {
    	if(button==0 && flag==6)
    	{
    		return null;
    	}
    	return super.slotClick(slot, button, flag, player);
    }

    private class SlotAlchenomicon extends Slot
    {
        private ContainerTransmutationTablet containerTransmutationTablet;
        private TileEntityTransmutationTablet tileEntityTransmutationTablet;

        public SlotAlchenomicon(ContainerTransmutationTablet containerTransmutationTablet, IInventory iInventory, int slotIndex, int x, int y)
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
            return itemStack.getItem() instanceof ItemAlchenomicon;
        }

        @Override
        public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
        {
            super.onPickupFromSlot(entityPlayer, itemStack);

            this.containerTransmutationTablet.inventoryTransmutationTablet = new InventoryTransmutationTablet();
            this.containerTransmutationTablet.updateInventory();

            if (!this.tileEntityTransmutationTablet.getWorldObj().isRemote && itemStack != null && itemStack.getItem() instanceof ItemAlchenomicon && ItemHelper.hasOwnerUUID(itemStack))
            {
                PacketHandler.INSTANCE.sendToAllAround(new MessagePlayerKnowledge(this.containerTransmutationTablet.tileEntityTransmutationTablet, null), new NetworkRegistry.TargetPoint(this.tileEntityTransmutationTablet.getWorldObj().provider.dimensionId, (double) this.tileEntityTransmutationTablet.xCoord, (double) this.tileEntityTransmutationTablet.yCoord, (double) this.tileEntityTransmutationTablet.zCoord, 5d));
            }
        }

        @Override
        public void putStack(ItemStack itemStack)
        {
            super.putStack(itemStack);

            if (!this.tileEntityTransmutationTablet.getWorldObj().isRemote && itemStack != null && itemStack.getItem() instanceof ItemAlchenomicon && ItemHelper.hasOwnerName(itemStack))
            {
                Set<ItemStack> knownTransmutations = PlayerKnowledgeRegistryProxy.getKnownItemStacks(ItemHelper.getOwnerName(itemStack));
                this.containerTransmutationTablet.inventoryTransmutationTablet = new InventoryTransmutationTablet(knownTransmutations);
                this.containerTransmutationTablet.updateInventory();
                PacketHandler.INSTANCE.sendToAllAround(new MessagePlayerKnowledge(this.containerTransmutationTablet.tileEntityTransmutationTablet, knownTransmutations), new NetworkRegistry.TargetPoint(this.tileEntityTransmutationTablet.getWorldObj().provider.dimensionId, (double) this.tileEntityTransmutationTablet.xCoord, (double) this.tileEntityTransmutationTablet.yCoord, (double) this.tileEntityTransmutationTablet.zCoord, 5d));
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
            return this.getHasStack();
        }

        @Override
        public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
        {
            super.onPickupFromSlot(entityPlayer, itemStack);

            if (this.getHasStack())
            {
                this.containerTransmutationTablet.tileEntityTransmutationTablet.consumeInventoryForEnergyValue(itemStack);
            }
        }

        @Override
        public void onSlotChanged()
        {
            super.onSlotChanged();

            if (FMLCommonHandler.instance().getEffectiveSide().isServer())
            {
                this.containerTransmutationTablet.tileEntityTransmutationTablet.updateEnergyValueFromInventory();
            }
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
            return EnergyValueRegistryProxy.hasEnergyValue(itemStack) && AbilityRegistryProxy.isRecoverable(itemStack);
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

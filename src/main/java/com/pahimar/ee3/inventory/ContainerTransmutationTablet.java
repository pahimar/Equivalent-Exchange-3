package com.pahimar.ee3.inventory;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
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
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.FilterUtils;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.containers.LinearProgressHandler;
import com.pahimar.ee3.util.containers.ProgressMessage;
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
import net.minecraft.world.World;

import java.util.*;

public class ContainerTransmutationTablet extends ContainerEE implements IElementTextFieldHandler, IElementSliderHandler, IElementButtonHandler
{
    private InventoryTransmutationTablet inventoryTransmutationTablet;
    private final TileEntityTransmutationTablet tileEntityTransmutationTablet;
    private final World world;
    private String searchTerm;
    private int sortOption;
    private int sortOrder;
    private int scrollBarPosition;
    private LinearProgressHandler<ProgressUpdate> progressHandler;

    private float energyValue;
    public float getEnergyValue() { return energyValue; }

    public ContainerTransmutationTablet(InventoryPlayer inventoryPlayer, TileEntityTransmutationTablet tileEntityTransmutationTablet)
    {
        this.tileEntityTransmutationTablet = tileEntityTransmutationTablet;
        this.world = tileEntityTransmutationTablet.getWorldObj();

        if (FMLCommonHandler.instance().getEffectiveSide().isClient())
            this.progressHandler = new LinearProgressHandler<ProgressUpdate>(ProgressUpdate.class) {
                public void handle(ProgressMessage<ProgressUpdate> message) { handleProgressUpdate(message); }
            };

        ItemStack tomeStack = tileEntityTransmutationTablet.getStackInSlot(TileEntityTransmutationTablet.ALCHEMICAL_TOME_INDEX);
        handleTomeSync(tomeStack);

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

        updateInventory();
    }

    private void sendTransmutationsToClient(Set<ItemStack> transmutations) {
        PacketHandler.INSTANCE.sendToAllAround(
                new MessageTransmutationKnowledgeUpdate(
                        this.tileEntityTransmutationTablet,
                        transmutations
                ),
                new NetworkRegistry.TargetPoint(
                        this.world.provider.dimensionId,
                        (double) this.tileEntityTransmutationTablet.xCoord,
                        (double) this.tileEntityTransmutationTablet.yCoord,
                        (double) this.tileEntityTransmutationTablet.zCoord,
                        5d
                )
        );
    }

    private void handleTomeSync(ItemStack tomeStack) {
        if (tomeStack != null && tomeStack.getItem() instanceof ItemAlchemicalTome && ItemHelper.hasOwnerUUID(tomeStack))
        {
            if (!this.world.isRemote)
            {
                Set<ItemStack> knownTransmutations = TransmutationKnowledgeRegistry.getInstance().getPlayersKnownTransmutations(ItemHelper.getOwnerUUID(tomeStack));
                this.inventoryTransmutationTablet = new InventoryTransmutationTablet(knownTransmutations);
                sendTransmutationsToClient(knownTransmutations);
            } else if (this.inventoryTransmutationTablet == null) {
                this.inventoryTransmutationTablet = new InventoryTransmutationTablet(this.tileEntityTransmutationTablet.getTransmutationKnowledge());
            }
        } else {
            this.inventoryTransmutationTablet = new InventoryTransmutationTablet();
            if (!world.isRemote) sendTransmutationsToClient(null);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.tileEntityTransmutationTablet != null && this.tileEntityTransmutationTablet.isStructureValid();
    }

    enum ProgressUpdate {
        EnergyUpdate,
        SortKindUpdate,
        SortDirectionUpdate,
        ScrollBarUpdate;

        public static ProgressMessage<ProgressUpdate> getMessage(ProgressUpdate kind, int data) {
            return new ProgressMessage<ProgressUpdate>(ProgressUpdate.class, kind, data);
        }
        public static ProgressMessage<ProgressUpdate> getMessage(ProgressUpdate kind, float data) {
            return new ProgressMessage<ProgressUpdate>(ProgressUpdate.class, kind, data);
        }
    }

    // Called every tick on the server, seemingly.
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        this.updateInventory();

        float tileEnergyValue = this.tileEntityTransmutationTablet.getAvailableEnergyValue().getValue();
        if (this.energyValue != tileEnergyValue)
        {
            this.energyValue = tileEnergyValue;
            ProgressUpdate.getMessage(ProgressUpdate.EnergyUpdate, tileEnergyValue).send(this, this.crafters);
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        this.progressHandler.handle(valueType, updatedValue);
    }

    @SideOnly(Side.CLIENT)
    public void handleProgressUpdate(ProgressMessage<ProgressUpdate> message) {
        switch (message.getType()) {
            case EnergyUpdate:
                this.energyValue = message.getFloat();
                LogHelper.trace(String.format("Got energy: %f", message.getFloat()));
                break;
            case SortKindUpdate:
                this.sortOption = message.getInt();
                break;
            case SortDirectionUpdate:
                this.sortOrder = message.getInt();
                break;
            case ScrollBarUpdate:
                break;
        }
        LogHelper.trace("calling from handleProgressUpdate!");
        updateInventory();
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
        List<ItemStack> filteredList = new ArrayList<ItemStack>(FilterUtils.filterByEnergyValue(filteredSet, energyValue));

        Comparator<ItemStack> comparator = null;
        switch (sortOption) {
            case 0:
                if (sortOrder == 0) comparator = Comparators.displayNameComparator;
                else comparator = Comparators.reverseDisplayNameComparator;
                break;
            case 1:
                if (sortOrder == 0) comparator = Comparators.energyValueItemStackComparator;
                else comparator = Comparators.reverseEnergyValueComparator;
                break;
            case 2:
                if (sortOrder == 0) comparator = Comparators.idComparator;
                else comparator = Comparators.reverseIdComparator;
                break;
        }
        if (comparator != null) Collections.sort(filteredList, comparator);

        if (filteredList.size() <= 30) {
            this.scrollBarPosition = 0;
        }

        int adjustedStartIndex = (int) ((scrollBarPosition / 187f) * filteredList.size());
        adjustedStartIndex -= adjustedStartIndex % 3; // Paginate by 3 elements.

        int startIndex = Math.max(0, Math.min(adjustedStartIndex, filteredList.size() - 30));
        int endIndex = Math.min(adjustedStartIndex + 30, filteredList.size());

        if (world.isRemote) {
            LogHelper.trace("updateInventory called");
            LogHelper.trace(String.format("known transmutations count: %d", this.inventoryTransmutationTablet.getKnownTransmutations().size()));
            LogHelper.trace(String.format("(from, to): (%d, %d)", startIndex, endIndex));
        }

        filteredList.subList(startIndex, endIndex).toArray(newInventory);

        for (int i = 0; i < 30; i++)
        {
            this.getSlot(i + 10).putStack(newInventory[i]);
            // TODO: Once stable, reinstate (new) incremental/only-where-necessary inventory updates.
//            Slot slot = this.getSlot(i + 10);
//            ItemStack stack = slot.getStack();
//            if (stack != null && newInventory[i] != null && !(stack.isItemEqual(newInventory[i])))
//                slot.putStack(newInventory[i]);
//            else if (newInventory[i] == null)
//                slot.putStack(null);
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
                if (!this.mergeTransmutatedItemStack(entityPlayer, slot, slotItemStack, 40, inventorySlots.size(), false))
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

    protected boolean mergeTransmutatedItemStack(EntityPlayer entityPlayer, Slot transmutationOutputSlot, ItemStack itemStack, int slotMin, int slotMax, boolean ascending)
    {
        if (this.energyValue < EnergyValueRegistryProxy.getEnergyValue(itemStack).getValue())
        {
            return false;
        }

        transmutationOutputSlot.onPickupFromSlot(entityPlayer, itemStack);

        boolean slotFound = false;
        int currentSlotIndex = ascending ? slotMax - 1 : slotMin;

        Slot slot;
        ItemStack stackInSlot;

        if (itemStack.isStackable())
        {
            while (itemStack.stackSize > 0 && (!ascending && currentSlotIndex < slotMax || ascending && currentSlotIndex >= slotMin))
            {
                slot = (Slot) this.inventorySlots.get(currentSlotIndex);
                stackInSlot = slot.getStack();

                if (slot.isItemValid(itemStack) && ItemHelper.equalsIgnoreStackSize(itemStack, stackInSlot))
                {
                    int combinedStackSize = stackInSlot.stackSize + itemStack.stackSize;
                    int slotStackSizeLimit = Math.min(stackInSlot.getMaxStackSize(), slot.getSlotStackLimit());

                    if (combinedStackSize <= slotStackSizeLimit) {
                        itemStack.stackSize = 0;
                        stackInSlot.stackSize = combinedStackSize;
                        slot.onSlotChanged();
                        slotFound = true;
                    }
                    else if (stackInSlot.stackSize < slotStackSizeLimit)
                    {
                        itemStack.stackSize -= slotStackSizeLimit - stackInSlot.stackSize;
                        stackInSlot.stackSize = slotStackSizeLimit;
                        slot.onSlotChanged();
                        slotFound = true;
                    }
                }

                currentSlotIndex += ascending ? -1 : 1;
            }
        }

        if (itemStack.stackSize > 0)
        {
            currentSlotIndex = ascending ? slotMax - 1 : slotMin;

            while (!ascending && currentSlotIndex < slotMax || ascending && currentSlotIndex >= slotMin)
            {
                slot = (Slot) this.inventorySlots.get(currentSlotIndex);
                stackInSlot = slot.getStack();

                if (slot.isItemValid(itemStack) && stackInSlot == null)
                {
                    slot.putStack(ItemHelper.cloneItemStack(itemStack, Math.min(itemStack.stackSize, slot.getSlotStackLimit())));
                    slot.onSlotChanged();

                    if (slot.getStack() != null)
                    {
                        itemStack.stackSize = 0;
                        slotFound = true;
                    }

                    break;
                }

                currentSlotIndex += ascending ? -1 : 1;
            }
        }
        itemStack.stackSize = 1;
        return slotFound;
    }

    @Override
    public void handleElementButtonClick(String elementName, int mouseButton)
    {
        if (elementName.equals("sortOption"))
        {
            switch (mouseButton) {
                case 0:
                    sortOption = (sortOption + 1) % 3; // Increment clockwise 0 -> 3
                    break;
                case 1:
                    sortOption = (sortOption + (3 - 1)) % 3; // Decrement counter-clockwise 3 -> 0
                    break;
            }
        }
        else if (elementName.equals("sortOrder"))
        {
            sortOrder = 1 - sortOrder; // Toggle between 0 and 1
        }

        ProgressUpdate.getMessage(ProgressUpdate.SortKindUpdate, sortOption).send(this, this.crafters);
        ProgressUpdate.getMessage(ProgressUpdate.SortDirectionUpdate, sortOrder).send(this, this.crafters);
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
    
    private class SlotAlchemicalTome extends Slot
    {
        public SlotAlchemicalTome(ContainerTransmutationTablet containerTransmutationTablet, IInventory inventory, int slotIndex, int x, int y)
        {
            super(inventory, slotIndex, x, y);
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
        public void onSlotChanged() {
            super.onSlotChanged();
            handleTomeSync(this.getStack());
            updateInventory();
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
        public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
        {
            super.onPickupFromSlot(entityPlayer, itemStack);

            if (this.getHasStack())
            {
                this.containerTransmutationTablet.tileEntityTransmutationTablet.consumeInventoryForEnergyValue(itemStack);
            }

            this.containerTransmutationTablet.updateInventory();
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

        // Something to do with disabling empty slots (see @{SlotInvisible}).
        @Override
        @SideOnly(Side.CLIENT)
        public boolean func_111238_b()
        {
            return this.getHasStack();
        }

        @Override
        public boolean canTakeStack(EntityPlayer entityPlayer)
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

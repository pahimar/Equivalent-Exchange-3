package com.pahimar.ee3.inventory;

import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class ContainerEE extends Container
{
    private static final Field field_94536_g;
    private static final Field field_94535_f;
    private static final Field field_94537_h;

    protected final int PLAYER_INVENTORY_ROWS = 3;
    protected final int PLAYER_INVENTORY_COLUMNS = 9;

    static
    {
        Class containerClass = Container.class;
        field_94536_g = ReflectionHelper.findField(containerClass, "field_94536_g");
        field_94535_f = ReflectionHelper.findField(containerClass, "field_94535_f");
        field_94537_h = ReflectionHelper.findField(containerClass, "field_94537_h");
    }

    @Override
    protected boolean mergeItemStack(ItemStack itemStack, int slotMin, int slotMax, boolean ascending)
    {
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

                    if (combinedStackSize <= slotStackSizeLimit)
                    {
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
                        itemStack.stackSize -= slot.getStack().stackSize;
                        slotFound = true;
                    }

                    break;
                }

                currentSlotIndex += ascending ? -1 : 1;
            }
        }

        return slotFound;
    }

    private int getDistributeState()
    {
        try {
            return field_94536_g.getInt(this);
        } catch (IllegalAccessException e) {
            LogHelper.error(e);
            return 0;
        }
    }

    private void setDistributeState(int value)
    {
        try {
            field_94536_g.setInt(this, value);
        } catch (IllegalAccessException e) {
            LogHelper.error(e);
        }
    }

    private int getPressedKeyInRange()
    {
        try {
            return field_94535_f.getInt(this);
        } catch (IllegalAccessException e) {
            LogHelper.error(e);
            return 0;
        }
    }

    private void setPressedKeyInRange(int value)
    {
        try {
            field_94535_f.setInt(this, value);
        } catch (IllegalAccessException e) {
            LogHelper.error(e);
        }
    }

    private Set getDistributeSlotSet()
    {
        try {
            return (Set) field_94537_h.get(this);
        } catch (IllegalAccessException e) {
            LogHelper.error(e);
            return new HashSet();
        }
    }

    // A mirror of net.minecraft.inventory.Container.slotClick changed from a recursive call to a loop
    // I hope this is only temporary and will be a forge patch soon

    /* Credit Mr-J:
     *      https://github.com/Mr-J/AdvancedBackpackMod/blob/master/unrelated/slotClick%2BComments%2BRename%2BHelpers.java.txt
     *
     * values for flag:
     * 0 = standard single click
     * 1 = single click + shift modifier
     * 2 = hotbar key is pressed (keys 0-9)
     * 3 = click with the middle button
     * 4 = click outside of the current gui window
     * 5 = button pressed & hold with the cursor holding an itemstack
     * 6 = double left click
     */

    @Override
    public ItemStack slotClick(int slotId, int button, int flags, EntityPlayer player)
    {
        ItemStack result = null;
        ItemStack movedItemStack;
        InventoryPlayer playerInventory = player.inventory;
        int sizeOrID;

        if (flags == 5) // DRAGGED DISTRIBUTION
        {
            int l = this.getDistributeState();
            this.setDistributeState(func_94532_c(button));

            if ((l != 1 || this.getDistributeState() != 2) && l != this.getDistributeState())
            {
                this.func_94533_d();
            } else if (playerInventory.getItemStack() == null)
            {
                this.func_94533_d();
            } else if (this.getDistributeState() == 0)
            {
                this.setPressedKeyInRange(func_94529_b(button));

                if (func_94528_d(this.getPressedKeyInRange()))
                {
                    this.setDistributeState(1);
                    this.getDistributeSlotSet().clear();
                } else
                {
                    this.func_94533_d();
                }
            } else if (this.getDistributeState() == 1)
            {
                Slot slot = (Slot) this.inventorySlots.get(slotId);

                if (slot != null && func_94527_a(slot, playerInventory.getItemStack(), true) &&
                        slot.isItemValid(playerInventory.getItemStack()) &&
                        playerInventory.getItemStack().stackSize > this.getDistributeSlotSet().size() &&
                        this.canDragIntoSlot(slot))
                {
                    this.getDistributeSlotSet().add(slot);
                }
            } else if (this.getDistributeState() == 2)
            {
                if (!this.getDistributeSlotSet().isEmpty())
                {
                    movedItemStack = playerInventory.getItemStack().copy();
                    sizeOrID = playerInventory.getItemStack().stackSize;
                    Iterator iterator = this.getDistributeSlotSet().iterator();

                    while (iterator.hasNext())
                    {
                        Slot slot1 = (Slot) iterator.next();

                        if (slot1 != null && func_94527_a(slot1, playerInventory.getItemStack(), true) &&
                                slot1.isItemValid(playerInventory.getItemStack()) &&
                                playerInventory.getItemStack().stackSize >= this.getDistributeSlotSet().size() &&
                                this.canDragIntoSlot(slot1))
                        {
                            ItemStack itemstack1 = movedItemStack.copy();
                            int j1 = slot1.getHasStack() ? slot1.getStack().stackSize : 0;
                            func_94525_a(this.getDistributeSlotSet(), this.getPressedKeyInRange(), itemstack1, j1);

                            if (itemstack1.stackSize > itemstack1.getMaxStackSize())
                            {
                                itemstack1.stackSize = itemstack1.getMaxStackSize();
                            }

                            if (itemstack1.stackSize > slot1.getSlotStackLimit())
                            {
                                itemstack1.stackSize = slot1.getSlotStackLimit();
                            }

                            sizeOrID -= itemstack1.stackSize - j1;
                            slot1.putStack(itemstack1);
                        }
                    }

                    movedItemStack.stackSize = sizeOrID;

                    if (movedItemStack.stackSize <= 0)
                    {
                        movedItemStack = null;
                    }

                    playerInventory.setItemStack(movedItemStack);
                }

                this.func_94533_d();
            } else
            {
                this.func_94533_d();
            }
        } else if (this.getDistributeState() != 0)
        {
            this.func_94533_d();
        } else // NORMAL SLOTCLICK
        {
            Slot targetSlotCopy;
            int l1;
            ItemStack itemstack5;

            if ((flags == 0 || flags == 1) && (button == 0 || button == 1))
            {
                // Not valid stack
                if (slotId == -999)
                {
                        /*
                         * on leftclick drop the complete itemstack from the inventory
                         * on rightclick drop a single item from the itemstack
                         */
                    if (playerInventory.getItemStack() != null && slotId == -999)
                    {
                        if (button == 0)
                        {
                            player.dropPlayerItemWithRandomChoice(playerInventory.getItemStack(), true);
                            playerInventory.setItemStack((ItemStack) null);
                        }

                        if (button == 1)
                        {
                            player.dropPlayerItemWithRandomChoice(playerInventory.getItemStack().splitStack(1), true);

                            if (playerInventory.getItemStack().stackSize == 0)
                            {
                                playerInventory.setItemStack((ItemStack) null);
                            }
                        }
                    }
                } else if (flags == 1) // SHIFT-CLICK
                {
                    while (true)
                    {

                        if (slotId < 0) // Invalid Slot
                        {
                            return null;
                        }

                        targetSlotCopy = (Slot) this.inventorySlots.get(slotId);

                        //if targetSlotCopy is not null and the stack inside the slot can be moved
                        if (targetSlotCopy != null && targetSlotCopy.canTakeStack(player))
                        {
                            //transfer the picked up stack to targetSlotID in the player inverntory
                            movedItemStack = this.transferStackInSlot(player, slotId);

                            //if the movedItemStack was not transferred completely
                            if (movedItemStack != null)
                            {
                                Item item = movedItemStack.getItem();

                                //set the return value to the rest
                                result = movedItemStack.copy();

                                if (targetSlotCopy.getStack() != null && targetSlotCopy.getStack().getItem() == item)
                                {
                                    // Original recursive bugged MC code
                                    // this.retrySlotClick(targetSlotID, mouseButtonPressed, true, player);
                                    // variables are:
                                    //      * [0]:: targetSlotID -> same as entry
                                    //      * [1]:: mouseButtonPressed -> same as entry
                                    //      * [2]:: true -> const -> 1
                                    //              if (flags == 1) -> same as entry
                                    //      * [3]:: player -> same as entry
                                    //
                                    // Final call: slotClick(slotId, button, 1, player)

                                    /* Execution path summery:
                                     *  [entry]->
                                     *      {{
                                     *          [set only] result -> null
                                     *          [set first] movedItemStack -> null
                                     *          [const] playerInventory -> player.inventory
                                     *          [unused] sizeOrID -> 0
                                     *      }}
                                     *      [if (1 == 5) -> false]
                                     *      [else if (this.getDistributeState() != 0)] -> false]
                                     *      [else -> true] ->
                                     *          {{
                                     *              [set first] targetSlotCopy -> null;
                                     *              [unused] l1 -> 0;
                                     *              [unused] itemstack5 -> null;
                                     *          }}
                                     *          [if ((flags == 0 || flags == 1) && (button == 0 || button == 1)) -> true] ->
                                     *              {{}}
                                     *              [if (slotId == -999) -> false]
                                     *              [else if (flags == 1) -> true]
                                     *                  {{loop body}}
                                     *
                                    */
                                    continue; //retry with the shift-click
                                }
                            }
                        }

                        break; //Keep current route
                    }
                } else //if a click with NO shift modifier is performed
                {
                    if (slotId < 0)
                    {
                        return null;
                    }

                    targetSlotCopy = (Slot) this.inventorySlots.get(slotId);

                    if (targetSlotCopy != null)
                    {
                        movedItemStack = targetSlotCopy.getStack();
                        ItemStack itemstack4 = playerInventory.getItemStack();

                        if (movedItemStack != null)
                        {
                            result = movedItemStack.copy();
                        }

                        if (movedItemStack == null)
                        {
                            if (itemstack4 != null && targetSlotCopy.isItemValid(itemstack4))
                            {
                                l1 = button == 0 ? itemstack4.stackSize : 1;

                                if (l1 > targetSlotCopy.getSlotStackLimit())
                                {
                                    l1 = targetSlotCopy.getSlotStackLimit();
                                }

                                if (itemstack4.stackSize >= l1)
                                {
                                    targetSlotCopy.putStack(itemstack4.splitStack(l1));
                                }

                                if (itemstack4.stackSize == 0)
                                {
                                    playerInventory.setItemStack((ItemStack) null);
                                }
                            }
                        } else if (targetSlotCopy.canTakeStack(player))
                        {
                            if (itemstack4 == null)
                            {
                                l1 = button == 0 ? movedItemStack.stackSize : (movedItemStack.stackSize + 1) / 2;
                                itemstack5 = targetSlotCopy.decrStackSize(l1);
                                playerInventory.setItemStack(itemstack5);

                                if (movedItemStack.stackSize == 0)
                                {
                                    targetSlotCopy.putStack((ItemStack) null);
                                }

                                targetSlotCopy.onPickupFromSlot(player, playerInventory.getItemStack());
                            } else if (targetSlotCopy.isItemValid(itemstack4))
                            {
                                if (movedItemStack.getItem() == itemstack4.getItem() && movedItemStack.getItemDamage() == itemstack4.getItemDamage() && ItemStack.areItemStackTagsEqual(movedItemStack, itemstack4))
                                {
                                    l1 = button == 0 ? itemstack4.stackSize : 1;

                                    if (l1 > targetSlotCopy.getSlotStackLimit() - movedItemStack.stackSize)
                                    {
                                        l1 = targetSlotCopy.getSlotStackLimit() - movedItemStack.stackSize;
                                    }

                                    if (l1 > itemstack4.getMaxStackSize() - movedItemStack.stackSize)
                                    {
                                        l1 = itemstack4.getMaxStackSize() - movedItemStack.stackSize;
                                    }

                                    itemstack4.splitStack(l1);

                                    if (itemstack4.stackSize == 0)
                                    {
                                        playerInventory.setItemStack((ItemStack) null);
                                    }

                                    movedItemStack.stackSize += l1;
                                } else if (itemstack4.stackSize <= targetSlotCopy.getSlotStackLimit())
                                {
                                    targetSlotCopy.putStack(itemstack4);
                                    playerInventory.setItemStack(movedItemStack);
                                }
                            } else if (movedItemStack.getItem() == itemstack4.getItem() && itemstack4.getMaxStackSize() > 1 && (!movedItemStack.getHasSubtypes() || movedItemStack.getItemDamage() == itemstack4.getItemDamage()) && ItemStack.areItemStackTagsEqual(movedItemStack, itemstack4))
                            {
                                l1 = movedItemStack.stackSize;

                                if (l1 > 0 && l1 + itemstack4.stackSize <= itemstack4.getMaxStackSize())
                                {
                                    itemstack4.stackSize += l1;
                                    movedItemStack = targetSlotCopy.decrStackSize(l1);

                                    if (movedItemStack.stackSize == 0)
                                    {
                                        targetSlotCopy.putStack((ItemStack) null);
                                    }

                                    targetSlotCopy.onPickupFromSlot(player, playerInventory.getItemStack());
                                }
                            }
                        }

                        targetSlotCopy.onSlotChanged();
                    }
                }
            }
            // If a hotbar key is pressed (flag == 2)
            else if (flags == 2 && button >= 0 && button < 9)
            {
                targetSlotCopy = (Slot) this.inventorySlots.get(slotId);

                if (targetSlotCopy.canTakeStack(player))
                {
                    movedItemStack = playerInventory.getStackInSlot(button);
                    boolean flag = movedItemStack == null || targetSlotCopy.inventory == playerInventory && targetSlotCopy.isItemValid(movedItemStack);
                    l1 = -1;

                    if (!flag)
                    {
                        l1 = playerInventory.getFirstEmptyStack();
                        flag |= l1 > -1;
                    }

                    if (targetSlotCopy.getHasStack() && flag)
                    {
                        itemstack5 = targetSlotCopy.getStack();
                        playerInventory.setInventorySlotContents(button, itemstack5.copy());

                        if ((targetSlotCopy.inventory != playerInventory || !targetSlotCopy.isItemValid(movedItemStack)) && movedItemStack != null)
                        {
                            if (l1 > -1)
                            {
                                playerInventory.addItemStackToInventory(movedItemStack);
                                targetSlotCopy.decrStackSize(itemstack5.stackSize);
                                targetSlotCopy.putStack((ItemStack) null);
                                targetSlotCopy.onPickupFromSlot(player, itemstack5);
                            }
                        } else
                        {
                            targetSlotCopy.decrStackSize(itemstack5.stackSize);
                            targetSlotCopy.putStack(movedItemStack);
                            targetSlotCopy.onPickupFromSlot(player, itemstack5);
                        }
                    } else if (!targetSlotCopy.getHasStack() && movedItemStack != null && targetSlotCopy.isItemValid(movedItemStack))
                    {
                        playerInventory.setInventorySlotContents(button, (ItemStack) null);
                        targetSlotCopy.putStack(movedItemStack);
                    }
                }
            } else if (flags == 3 && player.capabilities.isCreativeMode && playerInventory.getItemStack() == null && slotId >= 0)
            {
                targetSlotCopy = (Slot) this.inventorySlots.get(slotId);

                if (targetSlotCopy != null && targetSlotCopy.getHasStack())
                {
                    movedItemStack = targetSlotCopy.getStack().copy();
                    movedItemStack.stackSize = movedItemStack.getMaxStackSize();
                    playerInventory.setItemStack(movedItemStack);
                }
            } else if (flags == 4 && playerInventory.getItemStack() == null && slotId >= 0)
            {
                targetSlotCopy = (Slot) this.inventorySlots.get(slotId);

                if (targetSlotCopy != null && targetSlotCopy.getHasStack() && targetSlotCopy.canTakeStack(player))
                {
                    movedItemStack = targetSlotCopy.decrStackSize(button == 0 ? 1 : targetSlotCopy.getStack().stackSize);
                    targetSlotCopy.onPickupFromSlot(player, movedItemStack);
                    player.dropPlayerItemWithRandomChoice(movedItemStack, true);
                }
            } else if (flags == 6 && slotId >= 0)
            {
                targetSlotCopy = (Slot) this.inventorySlots.get(slotId);
                movedItemStack = playerInventory.getItemStack();

                if (movedItemStack != null && (targetSlotCopy == null || !targetSlotCopy.getHasStack() || !targetSlotCopy.canTakeStack(player)))
                {
                    sizeOrID = button == 0 ? 0 : this.inventorySlots.size() - 1;
                    l1 = button == 0 ? 1 : -1;

                    for (int i2 = 0; i2 < 2; ++i2)
                    {
                        for (int j2 = sizeOrID; j2 >= 0 && j2 < this.inventorySlots.size() && movedItemStack.stackSize < movedItemStack.getMaxStackSize(); j2 += l1)
                        {
                            Slot slot3 = (Slot) this.inventorySlots.get(j2);

                            if (slot3.getHasStack() && func_94527_a(slot3, movedItemStack, true) && slot3.canTakeStack(player) && this.func_94530_a(movedItemStack, slot3) && (i2 != 0 || slot3.getStack().stackSize != slot3.getStack().getMaxStackSize()))
                            {
                                int k1 = Math.min(movedItemStack.getMaxStackSize() - movedItemStack.stackSize, slot3.getStack().stackSize);
                                ItemStack itemstack2 = slot3.decrStackSize(k1);
                                movedItemStack.stackSize += k1;

                                if (itemstack2.stackSize <= 0)
                                {
                                    slot3.putStack((ItemStack) null);
                                }

                                slot3.onPickupFromSlot(player, itemstack2);
                            }
                        }
                    }
                }

                this.detectAndSendChanges();
            }
        }

        return result;
    }
}

package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.lib.Strings;
import net.minecraft.block.BlockFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;

import java.util.ArrayList;
import java.util.List;

/**
 * Equivalent-Exchange-3
 * <p/>
 * TileCalcinator
 *
 * @author pahimar
 */
public class TileCalcinator extends TileEE implements IInventory
{
    /**
     * The ItemStacks that hold the items currently being used in the Calcinator
     */
    private ItemStack[] inventory;

    public static final int INVENTORY_SIZE = 4;

    public static final int FUEL_INVENTORY_INDEX = 0;
    public static final int INPUT_INVENTORY_INDEX = 1;
    public static final int OUTPUT_LEFT_INVENTORY_INDEX = 2;
    public static final int OUTPUT_RIGHT_INVENTORY_INDEX = 3;

    public int remainingBurnTime;           // How much longer the Calcinator will burn
    public int currentFuelsFuelValue;       // The fuel value for the currently burning fuel
    public int currentItemCookTime;         // How long the current item has been "cooking"

    public TileCalcinator()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName()
    {
        return this.hasCustomName() ? this.getCustomName() : Strings.CONTAINER_CALCINATOR_NAME;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void openChest()
    {

    }

    @Override
    public void closeChest()
    {

    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return this.hasCustomName();
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        boolean isBurning = this.remainingBurnTime > 0;
        boolean calcinationOccured = false;

        if (this.remainingBurnTime > 0)
        {
            --this.remainingBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.remainingBurnTime == 0 && this.canCalcinate())
            {
                this.currentFuelsFuelValue = this.remainingBurnTime = TileEntityFurnace.getItemBurnTime(this.inventory[FUEL_INVENTORY_INDEX]);

                if (this.remainingBurnTime > 0)
                {
                    calcinationOccured = true;

                    if (this.inventory[INPUT_INVENTORY_INDEX] != null)
                    {
                        --this.inventory[INPUT_INVENTORY_INDEX].stackSize;

                        if (this.inventory[INPUT_INVENTORY_INDEX].stackSize == 0)
                        {
                            this.inventory[INPUT_INVENTORY_INDEX] = this.inventory[INPUT_INVENTORY_INDEX].getItem().getContainerItemStack(inventory[INPUT_INVENTORY_INDEX]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canCalcinate())
            {
                ++this.currentItemCookTime;

                if (this.currentItemCookTime == 200)
                {
                    this.currentItemCookTime = 0;
                    this.calcinateItem();
                    calcinationOccured = true;
                }
            }
            else
            {
                this.currentItemCookTime = 0;
            }

            if (isBurning != this.remainingBurnTime > 0)
            {
                calcinationOccured = true;
                BlockFurnace.updateFurnaceBlockState(this.remainingBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord); // TODO Customize
            }
        }

        if (calcinationOccured)
        {
            this.onInventoryChanged();
        }
    }

    public boolean isBurning()
    {
        return this.remainingBurnTime > 0;
    }

    /**
     * Determines if with the current inventory we can calcinate an item into dusts
     *
     * @return
     */
    private boolean canCalcinate()
    {
        if (inventory[INPUT_INVENTORY_INDEX] == null)
        {
            return false;
        }
        else
        {
            // TODO Calcination Manager integration
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);

            /**
             * If we don't get a calcination result, then return false
             */
            if (itemstack == null)
            {
                return false;
            }

            /**
             * If either slot is empty, return true (we have a valid calcination result
             */
            if (this.inventory[OUTPUT_LEFT_INVENTORY_INDEX] == null || this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX] == null)
            {
                return true;
            }

            if (!this.inventory[OUTPUT_LEFT_INVENTORY_INDEX].isItemEqual(itemstack) && !this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX].isItemEqual(itemstack))
            {
                return false;
            }

            int result = inventory[OUTPUT_LEFT_INVENTORY_INDEX].stackSize + itemstack.stackSize;

            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    public void calcinateItem()
    {
        if (this.canCalcinate())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);

            if (this.inventory[OUTPUT_LEFT_INVENTORY_INDEX] == null)
            {
                this.inventory[OUTPUT_LEFT_INVENTORY_INDEX] = itemstack.copy();
            }
            else if (this.inventory[OUTPUT_LEFT_INVENTORY_INDEX].isItemEqual(itemstack))
            {
                inventory[OUTPUT_LEFT_INVENTORY_INDEX].stackSize += itemstack.stackSize;
            }

            --this.inventory[INPUT_INVENTORY_INDEX].stackSize;

            if (this.inventory[INPUT_INVENTORY_INDEX].stackSize <= 0)
            {
                this.inventory[INPUT_INVENTORY_INDEX] = null;
            }
        }
    }

    private List<ItemStack> getDustForItemStack(ItemStack cookedItemStack)
    {
        List<ItemStack> dustList = new ArrayList<ItemStack>();

        return null;
    }

    @Override
    // TODO This is not an ideal toString
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString());

        stringBuilder.append("TileCalcinator Data - ");
        for (int i = 0; i < inventory.length; i++)
        {
            if (i != 0)
            {
                stringBuilder.append(", ");
            }

            if (inventory[i] != null)
            {
                stringBuilder.append(String.format("inventory[%d]: %s", i, inventory[i].toString()));
            }
            else
            {
                stringBuilder.append(String.format("inventory[%d]: empty", i));
            }
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}

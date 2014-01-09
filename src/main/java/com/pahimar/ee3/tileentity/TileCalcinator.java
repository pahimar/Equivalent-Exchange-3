package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.client.helper.ColourUtils;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.recipe.CalcinationManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;

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

    public int calcinatorCookTime;          // How much longer the Calcinator will cook
    public int fuelBurnTime;                // The fuel value for the currently burning fuel
    public int itemCookTime;                // How long the current item has been "cooking"

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
        if (slotIndex == OUTPUT_LEFT_INVENTORY_INDEX || slotIndex == OUTPUT_RIGHT_INVENTORY_INDEX || slotIndex == FUEL_INVENTORY_INDEX)return false;
        if (true);
    	return true;
    }

    @Override
    public void updateEntity()
    {
        boolean isBurning = this.calcinatorCookTime > 0;
        boolean inventoryChanged = false;

        // If the Calcinator still has burn time, decrement it
        if (this.calcinatorCookTime > 0)
        {
            this.calcinatorCookTime--;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.calcinatorCookTime == 0 && this.canCalcinate())
            {
                // TODO Effect burn speed by fuel quality
                // TODO Notify the client that we are still burning
                this.fuelBurnTime = this.calcinatorCookTime = TileEntityFurnace.getItemBurnTime(this.inventory[FUEL_INVENTORY_INDEX]);

                if (this.calcinatorCookTime > 0)
                {
                    inventoryChanged = true;

                    if (this.inventory[FUEL_INVENTORY_INDEX] != null)
                    {
                        --this.inventory[FUEL_INVENTORY_INDEX].stackSize;

                        if (this.inventory[FUEL_INVENTORY_INDEX].stackSize == 0)
                        {
                            this.inventory[FUEL_INVENTORY_INDEX] = this.inventory[FUEL_INVENTORY_INDEX].getItem().getContainerItemStack(inventory[FUEL_INVENTORY_INDEX]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canCalcinate())
            {
                this.itemCookTime++;

                if (this.itemCookTime == 200)
                {
                    this.itemCookTime = 0;
                    this.calcinateItem();
                    inventoryChanged = true;
                }
            }
            else
            {
                this.itemCookTime = 0;
            }

            if (isBurning != this.calcinatorCookTime > 0)
            {
                inventoryChanged = true;
                // TODO Add in world effects to show that we are making dust
            }
        }

        if (inventoryChanged)
        {
            this.onInventoryChanged();
        }
    }

    public boolean isBurning()
    {
        return this.calcinatorCookTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scale)
    {
        return this.itemCookTime * scale / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale)
    {
        if (this.itemCookTime == 0)
        {
            this.itemCookTime = 200;
        }

        return this.calcinatorCookTime * scale / this.fuelBurnTime;
    }

    /**
     * Determines if with the current inventory we can calcinate an item into dusts
     */
    private boolean canCalcinate()
    {
        if (inventory[INPUT_INVENTORY_INDEX] == null)
        {
            return false;
        }
        else
        {
            ItemStack alchemicalDustStack = CalcinationManager.getCalcinationResult(this.inventory[INPUT_INVENTORY_INDEX]);

            /**
             * If we don't get a calcination result, then return false
             */
            if (alchemicalDustStack == null)
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

            boolean leftEquals = this.inventory[OUTPUT_LEFT_INVENTORY_INDEX].isItemEqual(alchemicalDustStack);
            int leftResult = this.inventory[OUTPUT_LEFT_INVENTORY_INDEX].stackSize + alchemicalDustStack.stackSize;

            boolean rightEquals = this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX].isItemEqual(alchemicalDustStack);
            int rightResult = this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX].stackSize + alchemicalDustStack.stackSize;

            if (!leftEquals && !rightEquals)
            {
                return false;
            }
            else if (leftEquals && !rightEquals)
            {
                return leftResult <= getInventoryStackLimit() && leftResult <= alchemicalDustStack.getMaxStackSize();
            }
            else if (!leftEquals)
            {
                return rightResult <= getInventoryStackLimit() && rightResult <= alchemicalDustStack.getMaxStackSize();
            }
            else
            {
                return leftResult <= getInventoryStackLimit() && leftResult <= alchemicalDustStack.getMaxStackSize() || rightResult <= getInventoryStackLimit() && rightResult <= alchemicalDustStack.getMaxStackSize();
            }
        }
    }

    public void calcinateItem()
    {
        if (this.canCalcinate())
        {
            ItemStack alchemicalDustStack = CalcinationManager.getCalcinationResult(this.inventory[INPUT_INVENTORY_INDEX]);

            if (this.inventory[OUTPUT_LEFT_INVENTORY_INDEX] == null)
            {
                this.inventory[OUTPUT_LEFT_INVENTORY_INDEX] = alchemicalDustStack.copy();
            }
            else if (this.inventory[OUTPUT_LEFT_INVENTORY_INDEX].isItemEqual(alchemicalDustStack))
            {
                inventory[OUTPUT_LEFT_INVENTORY_INDEX].stackSize += alchemicalDustStack.stackSize;
            }
            else if (this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX] == null)
            {
                this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX] = alchemicalDustStack.copy();
            }
            else if (this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX].isItemEqual(alchemicalDustStack))
            {
                inventory[OUTPUT_RIGHT_INVENTORY_INDEX].stackSize += alchemicalDustStack.stackSize;
            }

            this.inventory[INPUT_INVENTORY_INDEX].stackSize--;

            if (this.inventory[INPUT_INVENTORY_INDEX].stackSize <= 0)
            {
                this.inventory[INPUT_INVENTORY_INDEX] = null;
            }
        }
    }

    public int getCombinedOutputSize()
    {
        int result = 0;

        if (this.inventory[OUTPUT_LEFT_INVENTORY_INDEX] != null)
        {
            result += this.inventory[OUTPUT_LEFT_INVENTORY_INDEX].stackSize;
        }

        if (this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX] != null)
        {
            result += this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX].stackSize;
        }

        return result;
    }

    @SideOnly(Side.CLIENT)
    public float[] getBlendedDustColour()
    {
        if (inventory[OUTPUT_LEFT_INVENTORY_INDEX] != null && inventory[OUTPUT_RIGHT_INVENTORY_INDEX] != null)
        {
            int leftStackColour = inventory[OUTPUT_LEFT_INVENTORY_INDEX].getItem().getColorFromItemStack(inventory[OUTPUT_LEFT_INVENTORY_INDEX], 1);
            int rightStackColour = inventory[OUTPUT_RIGHT_INVENTORY_INDEX].getItem().getColorFromItemStack(inventory[OUTPUT_RIGHT_INVENTORY_INDEX], 1);

            int stackSizeStepRange = 8;
            int leftStackSize = inventory[OUTPUT_LEFT_INVENTORY_INDEX].stackSize / stackSizeStepRange;
            int rightStackSize = inventory[OUTPUT_RIGHT_INVENTORY_INDEX].stackSize / stackSizeStepRange;
            float[][] blendedColours = ColourUtils.getFloatBlendedColours(leftStackColour, rightStackColour, 2 * stackSizeStepRange - 1);

            return blendedColours[stackSizeStepRange + (leftStackSize - rightStackSize)];
        }
        else if (inventory[OUTPUT_LEFT_INVENTORY_INDEX] != null)
        {
            return ColourUtils.convertIntColourToFloatArray(inventory[OUTPUT_LEFT_INVENTORY_INDEX].getItem().getColorFromItemStack(inventory[OUTPUT_LEFT_INVENTORY_INDEX], 1));
        }
        else if (inventory[OUTPUT_RIGHT_INVENTORY_INDEX] != null)
        {
            return ColourUtils.convertIntColourToFloatArray(inventory[OUTPUT_RIGHT_INVENTORY_INDEX].getItem().getColorFromItemStack(inventory[OUTPUT_RIGHT_INVENTORY_INDEX], 1));
        }
        else
        {
            return new float[]{1F, 1F, 1F};
        }
    }
}

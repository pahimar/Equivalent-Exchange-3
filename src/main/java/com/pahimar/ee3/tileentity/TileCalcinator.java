package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketTileCalcinator;
import com.pahimar.ee3.recipe.CalcinationManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
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

    public int deviceCookTime;              // How much longer the Calcinator will cook
    public int fuelBurnTime;                // The fuel value for the currently burning fuel
    public int itemCookTime;                // How long the current item has been "cooking"

    public byte leftStackSize, leftStackMeta, rightStackSize, rightStackMeta;

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
        sendDustPileData();
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
    public boolean receiveClientEvent(int eventId, int eventData)
    {
        if (eventId == 1)
        {
            this.state = (byte) eventData;
            this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
            return true;
        }
        else if (eventId == 2)
        {
            this.leftStackSize = (byte) eventData;
            return true;
        }
        else if (eventId == 3)
        {
            this.leftStackMeta = (byte) eventData;
            return true;
        }
        else if (eventId == 4)
        {
            this.rightStackSize = (byte) eventData;
            return true;
        }
        else if (eventId == 5)
        {
            this.rightStackMeta = (byte) eventData;
            return true;
        }
        else
        {
            return super.receiveClientEvent(eventId, eventData);
        }
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

        deviceCookTime = nbtTagCompound.getInteger("deviceCookTime");
        fuelBurnTime = nbtTagCompound.getInteger("fuelBurnTime");
        itemCookTime = nbtTagCompound.getInteger("itemCookTime");
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
        nbtTagCompound.setInteger("deviceCookTime", deviceCookTime);
        nbtTagCompound.setInteger("fuelBurnTime", fuelBurnTime);
        nbtTagCompound.setInteger("itemCookTime", itemCookTime);
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
        boolean isBurning = this.deviceCookTime > 0;
        boolean sendUpdate = false;

        // If the Calcinator still has burn time, decrement it
        if (this.deviceCookTime > 0)
        {
            this.deviceCookTime--;
        }

        if (!this.worldObj.isRemote)
        {
            // Start "cooking" a new item, if we can
            if (this.deviceCookTime == 0 && this.canCalcinate())
            {
                this.fuelBurnTime = this.deviceCookTime = TileEntityFurnace.getItemBurnTime(this.inventory[FUEL_INVENTORY_INDEX]);

                if (this.deviceCookTime > 0)
                {
                    sendUpdate = true;

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

            // Continue "cooking" the same item, if we can
            if (this.deviceCookTime > 0 && this.canCalcinate())
            {
                this.itemCookTime++;

                if (this.itemCookTime == 200)
                {
                    this.itemCookTime = 0;
                    this.calcinateItem();
                    sendUpdate = true;
                }
            }
            else
            {
                this.itemCookTime = 0;
            }

            // If the state has changed, catch that something changed
            if (isBurning != this.deviceCookTime > 0)
            {
                sendUpdate = true;
            }
        }

        if (sendUpdate)
        {
            this.onInventoryChanged();
            this.state = this.deviceCookTime > 0 ? (byte) 1 : (byte) 0;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, this.state);
            sendDustPileData();
            this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scale)
    {
        return this.itemCookTime * scale / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale)
    {
        return this.deviceCookTime * scale / this.fuelBurnTime;
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

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketTypeHandler.populatePacket(new PacketTileCalcinator(xCoord, yCoord, zCoord, orientation, state, customName, (byte) getLeftStackSize(), (byte) getLeftStackMeta(), (byte) getRightStackSize(), (byte) getRightStackMeta()));
    }

    private int getLeftStackSize()
    {
        if (this.inventory[OUTPUT_LEFT_INVENTORY_INDEX] != null)
        {
            return this.inventory[OUTPUT_LEFT_INVENTORY_INDEX].stackSize;
        }

        return 0;
    }

    private int getLeftStackMeta()
    {
        if (this.inventory[OUTPUT_LEFT_INVENTORY_INDEX] != null)
        {
            return this.inventory[OUTPUT_LEFT_INVENTORY_INDEX].getItemDamage();
        }

        return 0;
    }

    private int getRightStackSize()
    {
        if (this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX] != null)
        {
            return this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX].stackSize;
        }

        return 0;
    }

    private int getRightStackMeta()
    {
        if (this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX] != null)
        {
            return this.inventory[OUTPUT_RIGHT_INVENTORY_INDEX].getItemDamage();
        }

        return 0;
    }

    private void sendDustPileData()
    {
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 2, getLeftStackSize());
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 3, getLeftStackMeta());
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 4, getRightStackSize());
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 5, getRightStackMeta());
    }
}

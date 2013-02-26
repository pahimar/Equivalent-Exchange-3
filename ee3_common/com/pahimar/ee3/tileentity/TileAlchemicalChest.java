package com.pahimar.ee3.tileentity;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.lib.Sounds;
import com.pahimar.ee3.lib.Strings;

public class TileAlchemicalChest extends TileEE implements IInventory {

    /** The current angle of the chest lid (between 0 and 1) */
    public float lidAngle;

    /** The angle of the chest lid last tick */
    public float prevLidAngle;

    /** The number of players currently using this chest */
    public int numUsingPlayers;

    /** Server sync counter (once per 20 ticks) */
    private int ticksSinceSync;

    /**
     * The ItemStacks that hold the items currently being used in the Alchemical
     * Chest
     */
    private ItemStack[] inventory;

    public TileAlchemicalChest() {

        inventory = new ItemStack[13 * 4];
    }

    @Override
    public int getSizeInventory() {

        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {

        return this.inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            if (itemStack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            }
            else {
                itemStack = itemStack.splitStack(amount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {

        return "container." + Strings.ALCHEMICAL_CHEST_NAME;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    /**
     * Called when a client event is received with the event number and
     * argument, see World.sendClientEvent
     */
    @Override
    public void receiveClientEvent(int eventID, int numUsingPlayers) {

        if (eventID == 1) {
            this.numUsingPlayers = numUsingPlayers;
        }
    }

    @Override
    public void openChest() {

        ++this.numUsingPlayers;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, ModBlocks.alchemicalChest.blockID, 1, this.numUsingPlayers);
    }

    @Override
    public void closeChest() {

        --this.numUsingPlayers;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, ModBlocks.alchemicalChest.blockID, 1, this.numUsingPlayers);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses,
     * e.g. the mob spawner uses this to count ticks and creates a new spawn
     * inside its implementation.
     */
    @Override
    public void updateEntity() {

        super.updateEntity();

        if (++this.ticksSinceSync % 20 * 4 == 0) {
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, Block.enderChest.blockID, 1, this.numUsingPlayers);
        }

        this.prevLidAngle = this.lidAngle;
        float angleIncrement = 0.1F;
        double adjustedXCoord, adjustedZCoord;

        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F) {
            adjustedXCoord = (double) this.xCoord + 0.5D;
            adjustedZCoord = (double) this.zCoord + 0.5D;
            this.worldObj.playSoundEffect(adjustedXCoord, (double) this.yCoord + 0.5D, adjustedZCoord, Sounds.CHEST_OPEN, 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F) {
            float var8 = this.lidAngle;

            if (this.numUsingPlayers > 0) {
                this.lidAngle += angleIncrement;
            }
            else {
                this.lidAngle -= angleIncrement;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            if (this.lidAngle < 0.5F && var8 >= 0.5F) {
                adjustedXCoord = (double) this.xCoord + 0.5D;
                adjustedZCoord = (double) this.zCoord + 0.5D;
                this.worldObj.playSoundEffect(adjustedXCoord, (double) this.yCoord + 0.5D, adjustedZCoord, Sounds.CHEST_CLOSE, 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < this.inventory.length) {
                this.inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < this.inventory.length; ++currentIndex) {
            if (this.inventory[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                this.inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);
    }
}

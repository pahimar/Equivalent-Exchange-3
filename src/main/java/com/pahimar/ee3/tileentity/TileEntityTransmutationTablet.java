package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.block.BlockAshInfusedStoneSlab;
import com.pahimar.ee3.item.ItemAlchenomicon;
import com.pahimar.ee3.item.ItemMiniumStone;
import com.pahimar.ee3.item.ItemPhilosophersStone;
import com.pahimar.ee3.knowledge.PlayerKnowledge;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityTransmutationTablet;
import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Collections;
import java.util.Set;

public class TileEntityTransmutationTablet extends TileEntityEE implements ISidedInventory {

    public static final int INVENTORY_SIZE = 10;
    public static final int ITEM_INPUT_1 = 0;
    public static final int ITEM_INPUT_2 = 1;
    public static final int ITEM_INPUT_3 = 2;
    public static final int ITEM_INPUT_4 = 3;
    public static final int ITEM_INPUT_5 = 4;
    public static final int ITEM_INPUT_6 = 5;
    public static final int ITEM_INPUT_7 = 6;
    public static final int ITEM_INPUT_8 = 7;
    public static final int STONE_INDEX = 8;
    public static final int ALCHENOMICON_INDEX = 9;

    private EnergyValue storedEnergy, availableEnergy;
    private ForgeDirection rotation;
    private ItemStack[] inventory;
    public PlayerKnowledge playerKnowledge;

    public TileEntityTransmutationTablet() {

        super();
        rotation = ForgeDirection.UNKNOWN;
        availableEnergy = new EnergyValue(0);
        storedEnergy = new EnergyValue(0);
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    public EnergyValue getAvailableEnergy()
    {
        return availableEnergy;
    }

    public EnergyValue getStoredEnergy()
    {
        return storedEnergy;
    }

    public ForgeDirection getRotation() {
        return rotation;
    }

    public void setRotation(ForgeDirection rotation) {
        this.rotation = rotation;
    }

    public Set<ItemStack> getPlayerKnowledge() {

        if (playerKnowledge != null) {
            return playerKnowledge.getKnownItemStacks();
        }
        else {
            return Collections.emptySet();
        }
    }

    public void handlePlayerKnowledgeUpdate(PlayerKnowledge playerKnowledge) {
        this.playerKnowledge = playerKnowledge;
    }

    public void consumeInventoryForEnergyValue(ItemStack outputItemStack) {

        EnergyValue outputEnergyValue = EnergyValueRegistryProxy.getEnergyValueForStack(outputItemStack);

        /**
         *  Algorithm:
         *
         *  1) Check the Stone slot, and attempt to take EMC out of the stone there (til 0)
         *  2) Search the inventory for items that will most make up the difference, decrement them and consume their EMC
         *  3) Repeat 2 until Stored EMC > outputItemStack EMC
         *  4) Profit
         */

        if (this.storedEnergy.compareTo(outputEnergyValue) >= 0) {
            this.storedEnergy = new EnergyValue(this.storedEnergy.getValue() - outputEnergyValue.getValue());
        }
        else {

            while (this.storedEnergy.compareTo(outputEnergyValue) < 0 && this.availableEnergy.compareTo(outputEnergyValue) >= 0) {

                for (int i = 0; i < STONE_INDEX; i++) {

                    ItemStack stackInSlot = getStackInSlot(i);
                    if (stackInSlot != null && EnergyValueRegistryProxy.hasEnergyValue(stackInSlot)) {
                        this.storedEnergy = new EnergyValue(this.storedEnergy.getValue() + EnergyValueRegistryProxy.getEnergyValue(stackInSlot).getValue());
                        decrStackSize(i, 1);
                    }
                }
            }

            if (this.storedEnergy.getValue() >= outputEnergyValue.getValue()) {
                this.storedEnergy = new EnergyValue(this.storedEnergy.getValue() - outputEnergyValue.getValue());
            }
        }

        updateEnergyValueFromInventory();
    }

    public void updateEnergyValueFromInventory() {

        float newEnergyValue = storedEnergy.getValue();
        for (int i = 0; i <= STONE_INDEX; i++) {
            if (inventory[i] != null && EnergyValueRegistryProxy.hasEnergyValue(inventory[i])) {
                newEnergyValue += EnergyValueRegistryProxy.getEnergyValueForStack(inventory[i]).getValue();
            }
        }
        this.availableEnergy = new EnergyValue(newEnergyValue);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord - 1.5d, yCoord - 1, zCoord - 1.5d, xCoord + 1.5d, yCoord + 1, zCoord + 1.5d);
    }

    public boolean isStructureValid() {

        return ((worldObj.getBlock(xCoord - 1, yCoord, zCoord - 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord - 1) == 1) &&
                (worldObj.getBlock(xCoord, yCoord, zCoord - 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1) == 2) &&
                (worldObj.getBlock(xCoord + 1, yCoord, zCoord - 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord - 1) == 3) &&
                (worldObj.getBlock(xCoord - 1, yCoord, zCoord) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord) == 4) &&
                (worldObj.getBlock(xCoord + 1, yCoord, zCoord) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord) == 5) &&
                (worldObj.getBlock(xCoord - 1, yCoord, zCoord + 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord + 1) == 6) &&
                (worldObj.getBlock(xCoord, yCoord, zCoord + 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1) == 7) &&
                (worldObj.getBlock(xCoord + 1, yCoord, zCoord + 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord + 1) == 8));
    }

    @Override
    public void updateEntity() {

        super.updateEntity();
        updateEnergyValueFromInventory();
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityTransmutationTablet(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);
        rotation = ForgeDirection.getOrientation(nbtTagCompound.getInteger("rotation"));

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEMS, 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length) {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }

        NBTTagCompound energyValueTagCompound = nbtTagCompound.getCompoundTag("storedEnergy");
        if (!energyValueTagCompound.hasNoTags()) {
            storedEnergy = EnergyValue.loadEnergyValueFromNBT(energyValueTagCompound);
        }
        else {
            storedEnergy = new EnergyValue(0);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("rotation", rotation.ordinal());

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
            if (inventory[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag(Names.NBT.ITEMS, tagList);

        NBTTagCompound energyValueTagCompound = new NBTTagCompound();
        if (storedEnergy != null) {
            storedEnergy.writeToNBT(energyValueTagCompound);
        }
        nbtTagCompound.setTag("storedEnergy", energyValueTagCompound);
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

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
        if (slotIndex < getSizeInventory())
        {
            inventory[slotIndex] = itemStack;
            if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
            {
                itemStack.stackSize = getInventoryStackLimit();
            }

            updateEnergyValueFromInventory();
        }
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.TRANSMUTATION_TABLET;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && isStructureValid() && entityPlayer.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
    }

    @Override
    public void openInventory()
    {
        // NOOP
    }

    @Override
    public void closeInventory()
    {
        // NOOP
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        if (slotIndex < STONE_INDEX && EnergyValueRegistryProxy.hasEnergyValue(itemStack) && BlacklistRegistryProxy.isExchangeable(itemStack))
        {
            return true;
        }
        else if (slotIndex == STONE_INDEX && (itemStack.getItem() instanceof ItemMiniumStone || itemStack.getItem() instanceof ItemPhilosophersStone))
        {
            return true;
        }
        else if (slotIndex == ALCHENOMICON_INDEX && itemStack.getItem() instanceof ItemAlchenomicon)
        {
            return true;
        }

        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slotIndex)
    {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side)
    {
        return false;
    }
}

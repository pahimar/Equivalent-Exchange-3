package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.item.ItemAlchemicalDust;
import com.pahimar.ee3.item.crafting.RecipeAludel;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityAludel;
import com.pahimar.ee3.recipe.AludelRecipeManager;
import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAludel extends TileEntityEE implements ISidedInventory
{
    public static final int INVENTORY_SIZE = 4;
    public static final int FUEL_INVENTORY_INDEX = 0;
    public static final int INPUT_INVENTORY_INDEX = 1;
    public static final int DUST_INVENTORY_INDEX = 2;
    public static final int OUTPUT_INVENTORY_INDEX = 3;

    public int deviceCookTime;              // How much longer the Aludel will cook
    public int fuelBurnTime;                // The fuel value for the currently burning fuel
    public int itemCookTime;                // How long the current item has been "cooking"

    public ItemStack outputItemStack;

    public boolean hasGlassBell = false;
    /**
     * The ItemStacks that hold the items currently being used in the Aludel
     */
    private ItemStack[] inventory;

    public TileEntityAludel()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == ForgeDirection.DOWN.ordinal() ? new int[]{FUEL_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX} : new int[]{INPUT_INVENTORY_INDEX, DUST_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX};
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side)
    {
        if (worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityGlassBell)
        {
            return isItemValidForSlot(slotIndex, itemStack);
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side)
    {
        return slotIndex == OUTPUT_INVENTORY_INDEX;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEMS, 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }

        deviceCookTime = nbtTagCompound.getInteger("deviceCookTime");
        fuelBurnTime = nbtTagCompound.getInteger("fuelBurnTime");
        itemCookTime = nbtTagCompound.getInteger("itemCookTime");
        hasGlassBell = nbtTagCompound.getBoolean("hasGlassBell");
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
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.ALUDEL;
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
        return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && entityPlayer.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
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
        switch (slotIndex)
        {
            case FUEL_INVENTORY_INDEX:
            {
                return TileEntityFurnace.isItemFuel(itemStack);
            }
            case INPUT_INVENTORY_INDEX:
            {
                return true;
            }
            case DUST_INVENTORY_INDEX:
            {
                return itemStack.getItem() instanceof ItemAlchemicalDust;
            }
            default:
            {
                return false;
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
        nbtTagCompound.setTag(Names.NBT.ITEMS, tagList);
        nbtTagCompound.setInteger("deviceCookTime", deviceCookTime);
        nbtTagCompound.setInteger("fuelBurnTime", fuelBurnTime);
        nbtTagCompound.setInteger("itemCookTime", itemCookTime);
        nbtTagCompound.setBoolean("hasGlassBell", hasGlassBell);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityAludel(this, inventory[OUTPUT_INVENTORY_INDEX]));
    }

    @Override
    public void updateEntity()
    {
        boolean isBurning = this.deviceCookTime > 0;
        boolean sendUpdate = false;

        // If the Aludel still has burn time, decrement it
        if (this.deviceCookTime > 0)
        {
            this.deviceCookTime--;
        }

        if (!this.worldObj.isRemote)
        {
            // Start "cooking" a new item, if we can
            if (this.deviceCookTime == 0 && this.canInfuse())
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
                            this.inventory[FUEL_INVENTORY_INDEX] = this.inventory[FUEL_INVENTORY_INDEX].getItem().getContainerItem(inventory[FUEL_INVENTORY_INDEX]);
                        }
                    }
                }
            }

            // Continue "cooking" the same item, if we can
            if (this.deviceCookTime > 0 && this.canInfuse())
            {
                this.itemCookTime++;

                if (this.itemCookTime == 200)
                {
                    this.itemCookTime = 0;
                    this.infuseItem();
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
            this.markDirty();
            this.state = this.deviceCookTime > 0 ? (byte) 1 : (byte) 0;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.state);
            PacketHandler.INSTANCE.sendToAllAround(new MessageTileEntityAludel(this, inventory[OUTPUT_INVENTORY_INDEX]), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, (double) this.xCoord, (double) this.yCoord, (double) this.zCoord, 128d));
            this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        }
    }

    @Override
    public void markDirty()
    {
        PacketHandler.INSTANCE.sendToAllAround(new MessageTileEntityAludel(this, inventory[OUTPUT_INVENTORY_INDEX]), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, (double) this.xCoord, (double) this.yCoord, (double) this.zCoord, 128d));

        worldObj.func_147451_t(xCoord, yCoord, zCoord);

        if (hasGlassBell)
        {
            worldObj.func_147451_t(xCoord, yCoord + 1, zCoord);
        }
    }

    private boolean canInfuse()
    {
        if (!hasGlassBell || inventory[INPUT_INVENTORY_INDEX] == null || inventory[DUST_INVENTORY_INDEX] == null)
        {
            return false;
        }
        else
        {
            ItemStack infusedItemStack = AludelRecipeManager.getInstance().getResult(inventory[INPUT_INVENTORY_INDEX], inventory[DUST_INVENTORY_INDEX]);

            if (infusedItemStack == null)
            {
                return false;
            }

            if (inventory[OUTPUT_INVENTORY_INDEX] == null)
            {
                return true;
            }
            else
            {
                boolean outputEquals = this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(infusedItemStack);
                int mergedOutputStackSize = this.inventory[OUTPUT_INVENTORY_INDEX].stackSize + infusedItemStack.stackSize;

                if (outputEquals)
                {
                    return mergedOutputStackSize <= getInventoryStackLimit() && mergedOutputStackSize <= infusedItemStack.getMaxStackSize();
                }
            }
        }

        return false;
    }

    public void infuseItem()
    {
        if (this.canInfuse())
        {
            RecipeAludel recipe = AludelRecipeManager.getInstance().getRecipe(inventory[INPUT_INVENTORY_INDEX], inventory[DUST_INVENTORY_INDEX]);

            if (this.inventory[OUTPUT_INVENTORY_INDEX] == null)
            {
                this.inventory[OUTPUT_INVENTORY_INDEX] = recipe.getRecipeOutput().copy();
            }
            else if (this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(recipe.getRecipeOutput()))
            {
                inventory[OUTPUT_INVENTORY_INDEX].stackSize += recipe.getRecipeOutput().stackSize;
            }

            decrStackSize(INPUT_INVENTORY_INDEX, recipe.getRecipeInputs()[0].getStackSize());
            decrStackSize(DUST_INVENTORY_INDEX, recipe.getRecipeInputs()[1].getStackSize());
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
        if (this.fuelBurnTime > 0)
        {
            return this.deviceCookTime * scale / this.fuelBurnTime;
        }
        return 0;
    }
}

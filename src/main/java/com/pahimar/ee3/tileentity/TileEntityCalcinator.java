package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileCalcinator;
import com.pahimar.ee3.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCalcinator extends TileEntityEE implements ISidedInventory
{
    public static final int INVENTORY_SIZE = 4;
    public static final int FUEL_INVENTORY_INDEX = 0;
    public static final int INPUT_INVENTORY_INDEX = 1;
    public static final int OUTPUT_LEFT_INVENTORY_INDEX = 2;
    public static final int OUTPUT_RIGHT_INVENTORY_INDEX = 3;
    private static final int DEFAULT_ITEM_SUCK_COOL_DOWN = 20;
    public int deviceCookTime;              // How much longer the Calcinator will cook
    public int fuelBurnTime;                // The fuel value for the currently burning fuel
    public int itemCookTime;                // How long the current item has been "cooking"

    public byte leftStackSize, leftStackMeta, rightStackSize, rightStackMeta;

    public int itemSuckCoolDown = 0;
    /**
     * The ItemStacks that hold the items currently being used in the Calcinator
     */
    private ItemStack[] inventory;

    public TileEntityCalcinator()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == ForgeDirection.DOWN.ordinal() ? new int[]{FUEL_INVENTORY_INDEX, OUTPUT_LEFT_INVENTORY_INDEX, OUTPUT_RIGHT_INVENTORY_INDEX} : new int[]{INPUT_INVENTORY_INDEX, OUTPUT_LEFT_INVENTORY_INDEX, OUTPUT_RIGHT_INVENTORY_INDEX};
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side)
    {
        return isItemValidForSlot(slotIndex, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side)
    {
        return slotIndex == OUTPUT_LEFT_INVENTORY_INDEX || slotIndex == OUTPUT_RIGHT_INVENTORY_INDEX;
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        // FIXME sendDustPileData();
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
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.CALCINATOR_NAME;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
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
    public boolean isItemValidForSlot(int var1, ItemStack var2)
    {
        return false;
    }

    @Override
    public boolean receiveClientEvent(int eventId, int eventData)
    {
        if (eventId == 1)
        {
            this.state = (byte) eventData;
            // NAME UPDATE
            // this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.func_147451_t(this.xCoord, this.yCoord, this.zCoord);
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
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileCalcinator(this));
    }
}

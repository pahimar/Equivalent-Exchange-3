package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.item.crafting.RecipeAludel;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketTileWithItemUpdate;
import com.pahimar.ee3.recipe.RecipesAludel;
import cpw.mods.fml.common.network.PacketDispatcher;
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
 * TileAludel
 *
 * @author pahimar
 */
public class TileAludel extends TileEE implements IInventory
{
    /**
     * The ItemStacks that hold the items currently being used in the Aludel
     */
    private ItemStack[] inventory;

    public static final int INVENTORY_SIZE = 4;

    public static final int FUEL_INVENTORY_INDEX = 0;
    public static final int INPUT_INVENTORY_INDEX = 1;
    public static final int DUST_INVENTORY_INDEX = 2;
    public static final int OUTPUT_INVENTORY_INDEX = 3;

    public int deviceCookTime;              // How much longer the Aludel will cook
    public int fuelBurnTime;                // The fuel value for the currently burning fuel
    public int itemCookTime;                // How long the current item has been "cooking"

    public ItemStack outputItemStack;

    public TileAludel()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
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
    public String getInvName()
    {
        return this.hasCustomName() ? this.getCustomName() : Strings.CONTAINER_ALUDEL_NAME;
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
        else
        {
            return super.receiveClientEvent(eventId, eventData);
        }
    }

    @Override
    public void openChest()
    {
        // NOOP
    }

    @Override
    public void closeChest()
    {
        // NOOP
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
                            this.inventory[FUEL_INVENTORY_INDEX] = this.inventory[FUEL_INVENTORY_INDEX].getItem().getContainerItemStack(inventory[FUEL_INVENTORY_INDEX]);
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
            this.onInventoryChanged();
            this.state = this.deviceCookTime > 0 ? (byte) 1 : (byte) 0;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, this.state);
            PacketDispatcher.sendPacketToAllAround(this.xCoord, this.yCoord, this.zCoord, 128d, this.worldObj.provider.dimensionId, getDescriptionPacket());
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
        if (this.fuelBurnTime > 0)
        {
            return this.deviceCookTime * scale / this.fuelBurnTime;
        }
        return 0;
    }

    private boolean canInfuse()
    {
        if (inventory[INPUT_INVENTORY_INDEX] == null || inventory[DUST_INVENTORY_INDEX] == null)
        {
            return false;
        }
        else
        {
            ItemStack infusedItemStack = RecipesAludel.getInstance().getResult(inventory[INPUT_INVENTORY_INDEX], inventory[DUST_INVENTORY_INDEX]);

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
            RecipeAludel recipe = RecipesAludel.getInstance().getRecipe(inventory[INPUT_INVENTORY_INDEX], inventory[DUST_INVENTORY_INDEX]);

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

    @Override
    public Packet getDescriptionPacket()
    {
        ItemStack itemStack = this.inventory[OUTPUT_INVENTORY_INDEX];

        if (itemStack != null && itemStack.stackSize > 0)
        {
            return PacketTypeHandler.populatePacket(new PacketTileWithItemUpdate(xCoord, yCoord, zCoord, orientation, state, customName, itemStack.itemID, itemStack.getItemDamage(), itemStack.stackSize, ItemHelper.getColor(itemStack)));
        }
        else
        {
            return PacketTypeHandler.populatePacket(new PacketTileWithItemUpdate(xCoord, yCoord, zCoord, orientation, state, customName, -1, 0, 0, 0));
        }
    }

    @Override
    public void onInventoryChanged()
    {
        PacketDispatcher.sendPacketToAllAround(this.xCoord, this.yCoord, this.zCoord, 128D, this.worldObj.provider.dimensionId, getDescriptionPacket());

        worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);

        if (worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileGlassBell)
        {
            worldObj.updateAllLightTypes(xCoord, yCoord + 1, zCoord);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString());

        stringBuilder.append("TileAludel Data - ");
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

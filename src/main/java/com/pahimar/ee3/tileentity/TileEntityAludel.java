package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.item.ItemAlchemicalDust;
import com.pahimar.ee3.item.crafting.RecipeAludel;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityAludel;
import com.pahimar.ee3.recipe.RecipesAludel;
import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
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
        if (worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityGlassBell) {
            return isItemValidForSlot(slotIndex, itemStack);
        } else {
            return false;
        }
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side)
    {
        return slotIndex == OUTPUT_INVENTORY_INDEX;
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
        if (itemStack != null) {
            if (itemStack.stackSize <= decrementAmount) {
                setInventorySlotContents(slotIndex, null);
            } else {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0) {
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
        if (itemStack != null) {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
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
    public boolean isUseableByPlayer(EntityPlayer var1)
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
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        switch (slotIndex) {
            case FUEL_INVENTORY_INDEX: {
                return TileEntityFurnace.isItemFuel(itemStack);
            }
            case INPUT_INVENTORY_INDEX: {
                return true;
            }
            case DUST_INVENTORY_INDEX: {
                return itemStack.getItem() instanceof ItemAlchemicalDust;
            }
            default: {
                return false;
            }
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityAludel(this, null));
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scale) {
        return this.itemCookTime * scale / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale) {
        if (this.fuelBurnTime > 0) {
            return this.deviceCookTime * scale / this.fuelBurnTime;
        }
        return 0;
    }

    private boolean canInfuse() {
        if (!hasGlassBell || inventory[INPUT_INVENTORY_INDEX] == null || inventory[DUST_INVENTORY_INDEX] == null) {
            return false;
        } else {
            ItemStack infusedItemStack = RecipesAludel.getInstance().getResult(inventory[INPUT_INVENTORY_INDEX], inventory[DUST_INVENTORY_INDEX]);

            if (infusedItemStack == null) {
                return false;
            }

            if (inventory[OUTPUT_INVENTORY_INDEX] == null) {
                return true;
            } else {
                boolean outputEquals = this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(infusedItemStack);
                int mergedOutputStackSize = this.inventory[OUTPUT_INVENTORY_INDEX].stackSize + infusedItemStack.stackSize;

                if (outputEquals) {
                    return mergedOutputStackSize <= getInventoryStackLimit() && mergedOutputStackSize <= infusedItemStack.getMaxStackSize();
                }
            }
        }

        return false;
    }

    public void infuseItem() {
        if (this.canInfuse()) {
            RecipeAludel recipe = RecipesAludel.getInstance().getRecipe(inventory[INPUT_INVENTORY_INDEX], inventory[DUST_INVENTORY_INDEX]);

            if (this.inventory[OUTPUT_INVENTORY_INDEX] == null) {
                this.inventory[OUTPUT_INVENTORY_INDEX] = recipe.getRecipeOutput().copy();
            } else if (this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(recipe.getRecipeOutput())) {
                inventory[OUTPUT_INVENTORY_INDEX].stackSize += recipe.getRecipeOutput().stackSize;
            }

            decrStackSize(INPUT_INVENTORY_INDEX, recipe.getRecipeInputs()[0].getStackSize());
            decrStackSize(DUST_INVENTORY_INDEX, recipe.getRecipeInputs()[1].getStackSize());
        }
    }
}

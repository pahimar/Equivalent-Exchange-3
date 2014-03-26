package com.pahimar.ee3.inventory;

import com.pahimar.ee3.helper.ItemStackNBTHelper;
import com.pahimar.ee3.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ContainerAlchemicalBag
 *
 * @author pahimar
 */
public class ContainerAlchemicalBag extends Container
{
    private final EntityPlayer entityPlayer;
    private final InventoryAlchemicalBag inventoryAlchemicalBag;

    private int bagInventoryRows;
    private int bagInventoryColumns;

    // Small Bag
    public static final int SMALL_BAG_INVENTORY_ROWS = 4;
    public static final int SMALL_BAG_INVENTORY_COLUMNS = 12;

    // Medium Bag
    public static final int MEDIUM_BAG_INVENTORY_ROWS = 7;
    public static final int MEDIUM_BAG_INVENTORY_COLUMNS = 12;

    // Large Bag
    public static final int LARGE_BAG_INVENTORY_ROWS = 9;
    public static final int LARGE_BAG_INVENTORY_COLUMNS = 13;

    // Player Inventory
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerAlchemicalBag(EntityPlayer entityPlayer, InventoryAlchemicalBag inventoryAlchemicalBag)
    {
        this.entityPlayer = entityPlayer;
        this.inventoryAlchemicalBag = inventoryAlchemicalBag;

//        if (alchemicalBag.getItemDamage() == 0)
//        {
//            bagInventoryRows = SMALL_BAG_INVENTORY_ROWS;
//            bagInventoryColumns = SMALL_BAG_INVENTORY_COLUMNS;
//        }
//        else if (alchemicalBag.getItemDamage() == 1)
//        {
//            bagInventoryRows = MEDIUM_BAG_INVENTORY_ROWS;
//            bagInventoryColumns = MEDIUM_BAG_INVENTORY_COLUMNS;
//        }
//        else if (alchemicalBag.getItemDamage() == 2)
//        {
//            bagInventoryRows = LARGE_BAG_INVENTORY_ROWS;
//            bagInventoryColumns = LARGE_BAG_INVENTORY_COLUMNS;
//        }
//
//        // Add the Alchemical Chest slots to the container
//        for (int bagRowIndex = 0; bagRowIndex < bagInventoryRows; ++bagRowIndex)
//        {
//            for (int bagColumnIndex = 0; bagColumnIndex < bagInventoryColumns; ++bagColumnIndex)
//            {
//                if (alchemicalBag.getItemDamage() == 0)
//                {
//                    this.addSlotToContainer(new Slot(inventoryPlayer, bagColumnIndex + bagRowIndex * bagInventoryColumns, 8 + bagColumnIndex * 18, 18 + bagRowIndex * 18));
//                }
//                else if (alchemicalBag.getItemDamage() == 1)
//                {
//                    this.addSlotToContainer(new Slot(inventoryPlayer, bagColumnIndex + bagRowIndex * bagInventoryColumns, 8 + bagColumnIndex * 18, 18 + bagRowIndex * 18));
//                }
//                else if (alchemicalBag.getItemDamage() == 2)
//                {
//                    this.addSlotToContainer(new Slot(inventoryPlayer, bagColumnIndex + bagRowIndex * bagInventoryColumns, 8 + bagColumnIndex * 18, 8 + bagRowIndex * 18));
//                }
//            }
//        }
//
//        // Add the player's inventory slots to the container
//        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
//        {
//            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
//            {
//                if (alchemicalBag.getItemDamage() == 0)
//                {
//                    this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 35 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
//                }
//                else if (alchemicalBag.getItemDamage() == 1)
//                {
//                    this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 35 + inventoryColumnIndex * 18, 158 + inventoryRowIndex * 18));
//                }
//                else if (alchemicalBag.getItemDamage() == 2)
//                {
//                    this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 44 + inventoryColumnIndex * 18, 174 + inventoryRowIndex * 18));
//                }
//            }
//        }
//
//        // Add the player's action bar slots to the container
//        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
//        {
//            if (alchemicalBag.getItemDamage() == 0)
//            {
//                this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 35 + actionBarSlotIndex * 18, 162));
//            }
//            else if (alchemicalBag.getItemDamage() == 1)
//            {
//                this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 35 + actionBarSlotIndex * 18, 216));
//            }
//            else if (alchemicalBag.getItemDamage() == 2)
//            {
//                this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 232));
//            }
//        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer entityPlayer)
    {
        super.onContainerClosed(entityPlayer);

        if (!entityPlayer.worldObj.isRemote)
        {
            // We can probably do this better now considering the InventoryAlchemicalBag has a findParent method
            InventoryPlayer invPlayer = entityPlayer.inventory;
            for (ItemStack itemStack : invPlayer.mainInventory)
            {
                if (itemStack != null)
                {
                    if (ItemStackNBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN))
                    {
                        ItemStackNBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
                    }
                }
            }

            saveInventory(entityPlayer);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        // TODO
        return null;
    }

    public void saveInventory(EntityPlayer entityPlayer)
    {
        inventoryAlchemicalBag.onGuiSaved(entityPlayer);
    }
}

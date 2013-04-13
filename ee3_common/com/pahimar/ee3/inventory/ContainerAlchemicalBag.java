package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.item.ItemAlchemicalBag;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Equivalent-Exchange-3
 *
 * ContainerAlchemicalBag
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ContainerAlchemicalBag extends Container {

    private final int BAG_START_X = 8, BAG_START_Y = 18;
    private final int SHARED_START_X = 20, SHARED_START_Y = 12;

    private final int PLAYER_INV_SHARED_OFFSET_X = 12, PLAYER_INV_SHARED_OFFSET_Y = 86;

    private int startX = BAG_START_X, startY = BAG_START_Y;
    private int sharedOffsetX = 0, sharedOffsetY = 0;

    private int rows = 4, cols = 13;
    private InventoryAlchemicalBag inv;
    private World theWorld;

    public ContainerAlchemicalBag(ItemStack stack, EntityPlayer ep) {

        inv = new InventoryAlchemicalBag(stack);
        theWorld = ep.worldObj;
        boolean shared = ((ItemAlchemicalBag)ModItems.alchemicalBag).isSharing(stack);

        if(shared) {
            startX = SHARED_START_X; startY = SHARED_START_Y;
            IInventory ch = getChestInventory(stack);
            System.out.println("CH: " + ch + ", " + FMLCommonHandler.instance().getEffectiveSide());
            addInventory(ch, startX, startY);
            sharedOffsetX = PLAYER_INV_SHARED_OFFSET_X;
            sharedOffsetY = 76;

        }

        addInventory(inv, startX, startY + sharedOffsetY);
        addPlayerInventory(ep.inventory, startY + sharedOffsetY + PLAYER_INV_SHARED_OFFSET_Y);
    }

    private void addInventory(IInventory in, int x, int y) {
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                addSlotToContainer(new Slot(in, c + r * cols, x + c * 18, y + r * 18));
    }

    private void addPlayerInventory( InventoryPlayer ip, int ystart ) {
        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(ip, inventoryColumnIndex + inventoryRowIndex * 9 + 9, sharedOffsetX + 44 + inventoryColumnIndex * 18, ystart + inventoryRowIndex * 18));
            }
        }
        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(ip, actionBarSlotIndex, 44 + sharedOffsetX + actionBarSlotIndex * 18, 58 + ystart));
        }
    }

    private IInventory getChestInventory(ItemStack stack) {
        if(!NBTHelper.hasTag(stack, Strings.NBT_ITEM_ALCHEMICAL_BAG_CONNECTED_CHEST)) return null;
        NBTTagCompound tag = stack.getTagCompound().getCompoundTag(Strings.NBT_ITEM_ALCHEMICAL_BAG_CONNECTED_CHEST);
        int x = tag.getInteger("x"), y = tag.getInteger("y"), z = tag.getInteger("z");
        TileEntity tile = theWorld.getBlockTileEntity(x, y, z);
        if(tile != null && tile instanceof TileAlchemicalChest)
            return (TileAlchemicalChest)tile;
        return null;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        return null;
    }



    @Override
    public boolean canInteractWith(EntityPlayer var1) {

        return true;
    }

    @Override
    public void onCraftGuiClosed(EntityPlayer player) {

        super.onCraftGuiClosed(player);

        inv.writeBagContents(player.getCurrentEquippedItem());

        if (!player.worldObj.isRemote) {
            InventoryPlayer invPlayer = player.inventory;
            for (ItemStack itemStack : invPlayer.mainInventory) {
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
                        NBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
                    }
                    if(NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_SHARE))
                        NBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_SHARE);
                }
            }
        }
    }
}

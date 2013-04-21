package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.lib.Strings;

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

    public ContainerAlchemicalBag(InventoryPlayer inventoryPlayer) {

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 44 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 162));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {

        return true;
    }
    
    // Code for Shift-Clicking, Should Work! Added by AppleBloomModder
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i)
    {
     ItemStack stack = null;
     Slot slot = (Slot)this.inventorySlots.get(i);
     if(slot != null && slot.getHasStack())
     {
      ItemStack stack1 = slot.getStack();
      stack = stack1.copy();
      
      if(i == 0)
      {
       if(!this.mergeItemStack(stack1, 9, 45, true))
       {
        return null;
       }
       slot.onSlotChange(stack1, stack);
      }else if(i >= 1 && i < 5)
      {
       if(!this.mergeItemStack(stack1, 9, 45, false))  
       {
        return null;
       }
      }else if(i >= 5 && i < 9)
      {
       if(!this.mergeItemStack(stack1, 9, 45, false))
       {
        return null;
       }
      }else if(i >= 9 && i < 36)
      {
       if(!this.mergeItemStack(stack1, 36, 45, false))
       {
        return null;
       }
      }else if(i >= 36 && i < 45)
      {
       if(!this.mergeItemStack(stack1, 9, 36, false))
       {
        return null;
       }
      }else if(!this.mergeItemStack(stack1, 9, 45, false))
      {
       return null;
      }
      
      if(stack1.stackSize == 0)
      {
       slot.putStack((ItemStack)null);
      }else{
       slot.onSlotChanged();
      }
      
      if(stack1.stackSize == stack.stackSize)
      {
       return null;
      }
      slot.onPickupFromSlot(player, stack1);
     }
     return stack;
    }

    @Override
    public void onCraftGuiClosed(EntityPlayer player) {

        super.onCraftGuiClosed(player);

        if (!player.worldObj.isRemote) {
            InventoryPlayer invPlayer = player.inventory;
            for (ItemStack itemStack : invPlayer.mainInventory) {
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
                        NBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
                    }
                }
            }
        }
    }
}

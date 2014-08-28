package com.pahimar.ee3.inventory;

import com.pahimar.ee3.client.gui.inventory.GuiAlchemicalTome;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ContainerAlchemicalTome extends Container
{
    /**
     * the list of items in this container
     */
    public List itemList = new ArrayList();

    public ContainerAlchemicalTome(InventoryPlayer inventoryPlayer)
    {
        for (int x = 0; x < 9; x++)
        {
            for (int y = 0; y < 9; y++)
            {
                this.addSlotToContainer(new Slot(GuiAlchemicalTome.tempInventory, x * 9 + y, 16 + y * 18, 32 + x * 18));
            }
        }

        this.scrollTo(0.0F);
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return true;
    }

    /**
     * Updates the gui slots ItemStack's based on scroll position.
     */
    public void scrollTo(float p_148329_1_)
    {
        int i = this.itemList.size() / 9 - 5 + 1;
        int j = (int) ((double) (p_148329_1_ * (float) i) + 0.5D);

        if (j < 0)
        {
            j = 0;
        }

        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 9; ++l)
            {
                int i1 = l + (k + j) * 9;

                if (i1 >= 0 && i1 < this.itemList.size())
                {
                    GuiAlchemicalTome.tempInventory.setInventorySlotContents(l + k * 9, (ItemStack) this.itemList.get(i1));
                }
                else
                {
                    GuiAlchemicalTome.tempInventory.setInventorySlotContents(l + k * 9, (ItemStack) null);
                }
            }
        }
    }

    public boolean isScrollable()
    {
        return this.itemList.size() > 81;
    }

    protected void retrySlotClick(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_)
    {
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does
     * that.
     */
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        if (p_82846_2_ >= this.inventorySlots.size() - 9 && p_82846_2_ < this.inventorySlots.size())
        {
            Slot slot = (Slot) this.inventorySlots.get(p_82846_2_);

            if (slot != null && slot.getHasStack())
            {
                slot.putStack((ItemStack) null);
            }
        }

        return null;
    }

    public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_)
    {
        return p_94530_2_.yDisplayPosition > 90;
    }

    /**
     * Returns true if the player can "drag-spilt" items into this slot,. returns true by default. Called to
     * check if the slot can be added to a list of Slots to split the held ItemStack across.
     */
    public boolean canDragIntoSlot(Slot p_94531_1_)
    {
        return p_94531_1_.inventory instanceof InventoryPlayer || p_94531_1_.yDisplayPosition > 90 && p_94531_1_.xDisplayPosition <= 162;
    }
}

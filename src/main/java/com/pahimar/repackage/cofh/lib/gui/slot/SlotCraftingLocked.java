package com.pahimar.repackage.cofh.lib.gui.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

/**
 * Crafting result slot where the result cannot be removed.
 *
 * @author King Lemming
 */
public class SlotCraftingLocked extends SlotCrafting
{

    public SlotCraftingLocked(EntityPlayer player, IInventory craftMatrix, IInventory inventory, int index, int x, int y)
    {

        super(player, craftMatrix, inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {

        return false;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player)
    {

        return false;
    }

}

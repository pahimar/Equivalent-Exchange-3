package com.pahimar.repackage.cofh.lib.gui.slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * A slot that can only be used to display an item, not edited. Can optionally not highlight when moused over.
 */
public class SlotViewOnly extends Slot
{

    protected boolean showHighlight;

    public SlotViewOnly(IInventory inventory, int index, int x, int y)
    {

        this(inventory, index, x, y, false);
    }

    public SlotViewOnly(IInventory inventory, int index, int x, int y, boolean highlight)
    {

        super(inventory, index, x, y);
        showHighlight = highlight;
    }

    @Override
    public void putStack(ItemStack stack)
    {

    }

    @Override
    public ItemStack decrStackSize(int i)
    {

        return null;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player)
    {

        return false;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean func_111238_b()
    {

        return showHighlight;
    }

}
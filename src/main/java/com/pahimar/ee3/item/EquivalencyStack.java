package com.pahimar.ee3.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class EquivalencyStack
{
    private final List<Object> equivalentStacks;
    private int stackSize;

    public EquivalencyStack()
    {
        stackSize = -1;
        equivalentStacks = new ArrayList<Object>();
    }

    public EquivalencyStack(Object object)
    {
        if (object instanceof Item)
        {
            object = new ItemStack((Item) object);
        }
        else if (object instanceof Block)
        {
            object = new ItemStack((Block) object);
        }
        else if (object instanceof Fluid)
        {
            object = new FluidStack((Fluid) object, 1000);
        }

        if (object instanceof ItemStack)
        {
            ItemStack copiedItemStack = ((ItemStack) object).copy();
            this.stackSize = copiedItemStack.stackSize;
            copiedItemStack.stackSize = 1;
            equivalentStacks = new ArrayList<Object>();
            equivalentStacks.add(copiedItemStack);
        }
        else if (object instanceof ArrayList)
        {
            equivalentStacks = new ArrayList<Object>();
        }
        else
        {
            stackSize = -1;
            equivalentStacks = new ArrayList<Object>();
        }
    }

    public int getStackSize()
    {
        return this.stackSize;
    }

    public void setStackSize(int stackSize)
    {
        this.stackSize = stackSize;
    }

    public List<Object> getEquivalentStacks()
    {
        return this.equivalentStacks;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (Object object : equivalentStacks)
        {
            stringBuilder.append(object.toString() + " ");
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}

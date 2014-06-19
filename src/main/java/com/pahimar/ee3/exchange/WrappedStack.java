package com.pahimar.ee3.exchange;

import com.pahimar.ee3.reference.Compare;
import com.pahimar.ee3.util.FluidHelper;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WrappedStack implements Comparable<WrappedStack>
{
    private final Object wrappedStack;
    public static Comparator<WrappedStack> comparator = new Comparator<WrappedStack>()
    {

        @Override
        public int compare(WrappedStack wrappedStack1, WrappedStack wrappedStack2)
        {

            if (wrappedStack1.wrappedStack instanceof ItemStack)
            {
                if (wrappedStack2.wrappedStack instanceof ItemStack)
                {
                    return ItemHelper.compare((ItemStack) wrappedStack1.wrappedStack, (ItemStack) wrappedStack2.wrappedStack);
                }
                else
                {
                    return Compare.GREATER_THAN;
                }
            }
            else if (wrappedStack1.wrappedStack instanceof OreStack)
            {
                if (wrappedStack2.wrappedStack instanceof ItemStack)
                {
                    return Compare.LESSER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof OreStack)
                {
                    return OreStack.compare((OreStack) wrappedStack1.wrappedStack, (OreStack) wrappedStack2.wrappedStack);
                }
                else
                {
                    return Compare.GREATER_THAN;
                }
            }
            else if (wrappedStack1.wrappedStack instanceof FluidStack)
            {
                if (wrappedStack2.wrappedStack instanceof ItemStack || wrappedStack2.wrappedStack instanceof OreStack)
                {
                    return Compare.LESSER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof FluidStack)
                {
                    return FluidHelper.compare((FluidStack) wrappedStack1.wrappedStack, (FluidStack) wrappedStack2.wrappedStack);
                }
                else
                {
                    return Compare.GREATER_THAN;
                }
            }
            else if (wrappedStack1.wrappedStack == null)
            {
                if (wrappedStack2.wrappedStack != null)
                {
                    return Compare.LESSER_THAN;
                }
                else
                {
                    return Compare.EQUALS;
                }
            }

            return Compare.EQUALS;
        }
    };
    private int stackSize;

    /**
     *
     */
    public WrappedStack()
    {
        stackSize = -1;
        wrappedStack = null;
    }

    public WrappedStack(Object object)
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
            ItemStack itemStack = ((ItemStack) object).copy();

            stackSize = itemStack.stackSize;
            itemStack.stackSize = 1;
            wrappedStack = itemStack;
        }
        else if (object instanceof OreStack)
        {
            OreStack oreStack = (OreStack) object;

            stackSize = oreStack.stackSize;
            oreStack.stackSize = 1;
            wrappedStack = oreStack;
        }
        else if (object instanceof ArrayList)
        {
            ArrayList<?> objectList = (ArrayList<?>) object;

            OreStack possibleOreStack = OreStack.getOreStackFromList(objectList);

            if (possibleOreStack != null)
            {
                stackSize = possibleOreStack.stackSize;
                possibleOreStack.stackSize = 1;
                wrappedStack = possibleOreStack;
            }
            else
            {
                stackSize = -1;
                wrappedStack = null;
            }
        }
        else if (object instanceof FluidStack)
        {
            FluidStack fluidStack = ((FluidStack) object).copy();

            stackSize = fluidStack.amount;
            fluidStack.amount = 1;
            wrappedStack = fluidStack;
        }
        else if (object instanceof WrappedStack)
        {
            WrappedStack wrappedStackObject = (WrappedStack) object;

            if (wrappedStackObject.getWrappedStack() != null)
            {
                this.stackSize = wrappedStackObject.stackSize;
                this.wrappedStack = wrappedStackObject.wrappedStack;
            }
            else
            {
                stackSize = -1;
                wrappedStack = null;
            }
        }
        else
        {
            stackSize = -1;
            wrappedStack = null;
        }
    }

    public WrappedStack(Object object, int stackSize)
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
            ItemStack itemStack = ((ItemStack) object).copy();

            this.stackSize = stackSize;
            itemStack.stackSize = 1;
            wrappedStack = itemStack;
        }
        else if (object instanceof OreStack)
        {
            OreStack oreStack = (OreStack) object;

            this.stackSize = stackSize;
            oreStack.stackSize = 1;
            wrappedStack = oreStack;
        }
        else if (object instanceof ArrayList)
        {
            ArrayList<?> objectList = (ArrayList<?>) object;

            OreStack possibleOreStack = OreStack.getOreStackFromList(objectList);

            if (possibleOreStack != null)
            {
                this.stackSize = stackSize;
                possibleOreStack.stackSize = 1;
                wrappedStack = possibleOreStack;
            }
            else
            {
                this.stackSize = -1;
                wrappedStack = null;
            }
        }
        else if (object instanceof FluidStack)
        {
            FluidStack fluidStack = (FluidStack) object;

            this.stackSize = stackSize;
            fluidStack.amount = 1;
            wrappedStack = fluidStack;
        }
        else if (object instanceof WrappedStack)
        {
            WrappedStack wrappedStackObject = (WrappedStack) object;

            if (wrappedStackObject.getWrappedStack() != null)
            {
                this.stackSize = stackSize;
                this.wrappedStack = wrappedStackObject.wrappedStack;
            }
            else
            {
                this.stackSize = -1;
                wrappedStack = null;
            }
        }
        else
        {
            this.stackSize = -1;
            wrappedStack = null;
        }
    }

    public static boolean canBeWrapped(Object object)
    {
        if (object instanceof WrappedStack)
        {
            return true;
        }
        else if (object instanceof Item || object instanceof Block || object instanceof ItemStack)
        {
            return true;
        }
        else if (object instanceof OreStack)
        {
            return true;
        }
        else if (object instanceof List)
        {
            if (OreStack.getOreStackFromList((List<?>) object) != null)
            {
                return true;
            }
        }
        else if (object instanceof Fluid || object instanceof FluidStack)
        {
            return true;
        }

        return false;
    }

    public int getStackSize()
    {

        return stackSize;
    }

    public void setStackSize(int stackSize)
    {

        this.stackSize = stackSize;
    }

    public Object getWrappedStack()
    {

        return wrappedStack;
    }

    /*
     * Sort order (class-wise) goes ItemStack, OreStack, EnergyStack,
     * FluidStack, null
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(WrappedStack wrappedStack)
    {
        return comparator.compare(this, wrappedStack);
    }

    /**
     *
     */
    @Override
    public int hashCode()
    {
        int hashCode = 1;
        hashCode = (37 * hashCode) + stackSize;

        if (wrappedStack instanceof ItemStack)
        {
            hashCode = (37 * hashCode) + Item.getIdFromItem(((ItemStack) wrappedStack).getItem());
            hashCode = (37 * hashCode) + ((ItemStack) wrappedStack).getItemDamage();

            if (((ItemStack) wrappedStack).getTagCompound() != null)
            {
                hashCode = (37 * hashCode) + ((ItemStack) wrappedStack).getTagCompound().hashCode();
            }
        }
        else if (wrappedStack instanceof OreStack)
        {
            if (((OreStack) wrappedStack).oreName != null)
            {
                hashCode = (37 * hashCode) + ((OreStack) wrappedStack).oreName.hashCode();
            }
        }
        else if (wrappedStack instanceof FluidStack)
        {
            hashCode = (37 * hashCode) + wrappedStack.hashCode();

            if (((FluidStack) wrappedStack).tag != null)
            {
                hashCode = (37 * hashCode) + ((FluidStack) wrappedStack).tag.hashCode();
            }
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object object)
    {
        return object instanceof WrappedStack && (this.compareTo((WrappedStack) object) == Compare.EQUALS);
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString()
    {
        if (wrappedStack instanceof ItemStack)
        {
            return String.format("%sxitemStack[%s@%s, %s]", stackSize, ((ItemStack) wrappedStack).getUnlocalizedName(), ((ItemStack) wrappedStack).getItemDamage(), Item.getIdFromItem(((ItemStack) wrappedStack).getItem()));
        }
        else if (wrappedStack instanceof OreStack)
        {
            OreStack oreStack = (OreStack) wrappedStack;
            return String.format("%sxoreStack.%s", stackSize, oreStack.oreName);
        }
        else if (wrappedStack instanceof FluidStack)
        {
            FluidStack fluidStack = (FluidStack) wrappedStack;
            return String.format("%sxfluidStack.%s", stackSize, fluidStack.getFluid().getName());
        }
        else
        {
            return "null";
        }
    }
}

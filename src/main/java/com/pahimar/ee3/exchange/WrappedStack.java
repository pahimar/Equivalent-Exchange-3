package com.pahimar.ee3.exchange;

import com.pahimar.ee3.util.FluidHelper;
import com.pahimar.ee3.util.ItemStackUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class WrappedStack implements Comparable<WrappedStack> {

    private final Object wrappedStack;
    private int stackSize;

    public WrappedStack() {

        stackSize = -1;
        wrappedStack = null;
    }

    private WrappedStack(Object object) {

        if (object instanceof Item) {
            object = new ItemStack((Item) object);
        }
        else if (object instanceof Block) {
            object = new ItemStack((Block) object);
        }
        else if (object instanceof Fluid) {
            object = new FluidStack((Fluid) object, 1000);
        }

        if (object instanceof ItemStack) {

            if (((ItemStack) object).getItem() != null) {

                stackSize = ((ItemStack) object).stackSize;
                wrappedStack = ItemStackUtils.clone((ItemStack) object, 1);
            }
            else {

                stackSize = -1;
                wrappedStack = null;
            }
        }
        else if (object instanceof OreStack) {

            OreStack oreStack = (OreStack) object;
            stackSize = oreStack.stackSize;
            oreStack.stackSize = 1;
            wrappedStack = oreStack;
        }
        else if (object instanceof ArrayList) {

            ArrayList<?> objectList = (ArrayList<?>) object;

            OreStack possibleOreStack = OreStack.getOreStackFrom(objectList);

            if (possibleOreStack != null) {

                stackSize = possibleOreStack.stackSize;
                possibleOreStack.stackSize = 1;
                wrappedStack = possibleOreStack;
            }
            else {

                stackSize = -1;
                wrappedStack = null;
            }
        }
        else if (object instanceof FluidStack) {

            FluidStack fluidStack = ((FluidStack) object).copy();
            stackSize = fluidStack.amount;
            fluidStack.amount = 1;
            wrappedStack = fluidStack;
        }
        else if (object instanceof WrappedStack) {

            WrappedStack wrappedStackObject = (WrappedStack) object;

            if (wrappedStackObject.getWrappedObject() != null) {

                this.stackSize = wrappedStackObject.stackSize;
                this.wrappedStack = wrappedStackObject.wrappedStack;
            }
            else {

                stackSize = -1;
                wrappedStack = null;
            }
        }
        else {

            stackSize = -1;
            wrappedStack = null;
        }
    }

    private WrappedStack(Object object, int stackSize) {

        if (object instanceof Item) {

            object = new ItemStack((Item) object);
        }
        else if (object instanceof Block) {

            object = new ItemStack((Block) object);
        }
        else if (object instanceof Fluid) {

            object = new FluidStack((Fluid) object, 1000);
        }

        if (object instanceof ItemStack) {

            this.stackSize = stackSize;
            wrappedStack = ItemStackUtils.clone((ItemStack) object, 1);
        }
        else if (object instanceof OreStack) {

            OreStack oreStack = (OreStack) object;
            this.stackSize = stackSize;
            oreStack.stackSize = 1;
            wrappedStack = oreStack;
        }
        else if (object instanceof ArrayList) {

            ArrayList<?> objectList = (ArrayList<?>) object;

            OreStack possibleOreStack = OreStack.getOreStackFrom(objectList);

            if (possibleOreStack != null) {

                this.stackSize = stackSize;
                possibleOreStack.stackSize = 1;
                wrappedStack = possibleOreStack;
            }
            else {

                this.stackSize = -1;
                wrappedStack = null;
            }
        }
        else if (object instanceof FluidStack) {

            FluidStack fluidStack = (FluidStack) object;
            this.stackSize = stackSize;
            fluidStack.amount = 1;
            wrappedStack = fluidStack;
        }
        else if (object instanceof WrappedStack) {

            WrappedStack wrappedStackObject = (WrappedStack) object;

            if (wrappedStackObject.getWrappedObject() != null) {

                this.stackSize = stackSize;
                this.wrappedStack = wrappedStackObject.wrappedStack;
            }
            else {

                this.stackSize = -1;
                wrappedStack = null;
            }
        }
        else {

            this.stackSize = -1;
            wrappedStack = null;
        }
    }

    public Object getWrappedObject() {
        return wrappedStack;
    }

    public static boolean canBeWrapped(Object object) {

        if (object instanceof WrappedStack) {
            return true;
        }
        else if (object instanceof Item || object instanceof Block) {
            return true;
        }
        else if (object instanceof ItemStack) {

            if (((ItemStack) object).getItem() != null) {
                return true;
            }
        }
        else if (object instanceof OreStack) {
            return true;
        }
        else if (object instanceof List) {

            if (OreStack.getOreStackFrom((List<?>) object) != null) {
                return true;
            }
        }
        else if (object instanceof Fluid || object instanceof FluidStack) {
            return true;
        }

        return false;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public static WrappedStack wrap(Object object) {

        if (canBeWrapped(object)) {
            return new WrappedStack(object);
        }

        return null;
    }

    public static WrappedStack wrap(Object object, int stackSize) {

        if (canBeWrapped(object)) {
            return new WrappedStack(object, stackSize);
        }

        return null;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof WrappedStack && (this.compareTo((WrappedStack) object) == 0);
    }

    /**
     * Sort order (class-wise) goes ItemStack, OreStack, EnergyStack,
     * FluidStack, null
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(WrappedStack wrappedStack) {
        return COMPARATOR.compare(this, wrappedStack);
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {

        if (wrappedStack instanceof ItemStack) {
            ItemStack itemStack = (ItemStack) wrappedStack;
            String unlocalizedName = null;
            try {
                if (itemStack.getItem() != null) {
                    unlocalizedName = Item.itemRegistry.getNameForObject(itemStack.getItem());
                }

                if (unlocalizedName == null) {
                    unlocalizedName = itemStack.getUnlocalizedName();
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                unlocalizedName = "no-name";
            }

            if (itemStack.hasTagCompound()) {
                return String.format("%sxitemStack[%s@%s:%s]", stackSize, unlocalizedName, itemStack.getItemDamage(), itemStack.getTagCompound());
            }
            else {
                return String.format("%sxitemStack[%s@%s]", stackSize, unlocalizedName, itemStack.getItemDamage());
            }
        }
        else if (wrappedStack instanceof OreStack) {
            OreStack oreStack = (OreStack) wrappedStack;
            return String.format("%sxoreStack[%s]", stackSize, oreStack.oreName);
        }
        else if (wrappedStack instanceof FluidStack) {
            FluidStack fluidStack = (FluidStack) wrappedStack;
            return String.format("%sxfluidStack[%s]", stackSize, fluidStack.getFluid().getName());
        }
        else {
            return "null-wrappedstack";
        }
    }

    public static final Comparator<WrappedStack> COMPARATOR = new Comparator<WrappedStack>() {

        @Override
        public int compare(WrappedStack wrappedStack1, WrappedStack wrappedStack2) {

            if (wrappedStack1.wrappedStack instanceof ItemStack) {

                if (wrappedStack2.wrappedStack instanceof ItemStack) {

                    int compareResult = ItemStackUtils.compare((ItemStack) wrappedStack1.wrappedStack, (ItemStack) wrappedStack2.wrappedStack);

                    if (compareResult == 0) {
                        return wrappedStack1.stackSize - wrappedStack2.stackSize;
                    }
                    else {
                        return compareResult;
                    }
                }
                else {
                    return 1;
                }
            }
            else if (wrappedStack1.wrappedStack instanceof OreStack) {

                if (wrappedStack2.wrappedStack instanceof ItemStack) {
                    return -1;
                }
                else if (wrappedStack2.wrappedStack instanceof OreStack) {

                    int compareResult = OreStack.compare((OreStack) wrappedStack1.wrappedStack, (OreStack) wrappedStack2.wrappedStack);

                    if (compareResult == 0) {
                        return wrappedStack1.stackSize - wrappedStack2.stackSize;
                    }
                    else {
                        return compareResult;
                    }
                }
                else {
                    return 1;
                }
            }
            else if (wrappedStack1.wrappedStack instanceof FluidStack) {

                if (wrappedStack2.wrappedStack instanceof ItemStack || wrappedStack2.wrappedStack instanceof OreStack) {
                    return -1;
                }
                else if (wrappedStack2.wrappedStack instanceof FluidStack) {

                    int compareResult = FluidHelper.compare((FluidStack) wrappedStack1.wrappedStack, (FluidStack) wrappedStack2.wrappedStack);

                    if (compareResult == 0) {
                        return wrappedStack1.stackSize - wrappedStack2.stackSize;
                    }
                    else {
                        return compareResult;
                    }
                }
                else {
                    return 1;
                }
            }
            else if (wrappedStack1.wrappedStack == null) {
                if (wrappedStack2.wrappedStack != null) {
                    return -1;
                }
                else {
                    return 0;
                }
            }

            return 0;
        }
    };
}

package com.pahimar.ee3.exchange;

import com.google.gson.*;
import com.pahimar.ee3.serialization.WrappedStackSerializer;
import com.pahimar.ee3.util.FluidHelper;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WrappedStack implements Comparable<WrappedStack>
{
    public static final Gson jsonSerializer = (new GsonBuilder()).setPrettyPrinting()
            .registerTypeAdapter(WrappedStack.class, new WrappedStackSerializer()).create();

    // TODO Convert these constants to an enum(?)
    public static final String itemStackType = "itemstack";
    public static final String oreStackType = "orestack";
    public static final String fluidStackType = "fluidstack";

    private final String objectType;
    private final Object wrappedStack;
    private int stackSize;

    public WrappedStack()
    {
        objectType = null;
        stackSize = -1;
        wrappedStack = null;
    }

    // TODO Way too complex constructor.
    @Deprecated
    @SuppressWarnings("unused")
    private WrappedStack(Object object)
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
            if (((ItemStack) object).getItem() != null)
            {
                ItemStack itemStackObject = (ItemStack) object;
                ItemStack itemStack = new ItemStack(itemStackObject.getItem(), itemStackObject.stackSize, itemStackObject.getItemDamage());
                if (itemStackObject.stackTagCompound != null)
                {
                    itemStack.stackTagCompound = (NBTTagCompound) itemStackObject.stackTagCompound.copy();
                }
                objectType = itemStackType;
                stackSize = itemStack.stackSize;
                itemStack.stackSize = 1;
                wrappedStack = itemStack;
            }
            else
            {
                objectType = null;
                stackSize = -1;
                wrappedStack = null;
            }
        }
        else if (object instanceof OreStack)
        {
            OreStack oreStack = (OreStack) object;
            objectType = oreStackType;
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
                objectType = oreStackType;
                stackSize = possibleOreStack.stackSize;
                possibleOreStack.stackSize = 1;
                wrappedStack = possibleOreStack;
            }
            else
            {
                objectType = null;
                stackSize = -1;
                wrappedStack = null;
            }
        }
        else if (object instanceof FluidStack)
        {
            FluidStack fluidStack = ((FluidStack) object).copy();
            objectType = fluidStackType;
            stackSize = fluidStack.amount;
            fluidStack.amount = 1;
            wrappedStack = fluidStack;
        }
        else if (object instanceof WrappedStack)
        {
            WrappedStack wrappedStackObject = (WrappedStack) object;

            if (wrappedStackObject.getWrappedObject() != null)
            {
                this.objectType = wrappedStackObject.objectType;
                this.stackSize = wrappedStackObject.stackSize;
                this.wrappedStack = wrappedStackObject.wrappedStack;
            }
            else
            {
                objectType = null;
                stackSize = -1;
                wrappedStack = null;
            }
        }
        else
        {
            objectType = null;
            stackSize = -1;
            wrappedStack = null;
        }
    }

    // TODO Way too complex constructor.
    @Deprecated
    @SuppressWarnings("unused")
    private WrappedStack(Object object, int stackSize)
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
            objectType = itemStackType;
            this.stackSize = stackSize;
            itemStack.stackSize = 1;
            wrappedStack = itemStack;
        }
        else if (object instanceof OreStack)
        {
            OreStack oreStack = (OreStack) object;
            objectType = oreStackType;
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
                objectType = oreStackType;
                this.stackSize = stackSize;
                possibleOreStack.stackSize = 1;
                wrappedStack = possibleOreStack;
            }
            else
            {
                objectType = null;
                this.stackSize = -1;
                wrappedStack = null;
            }
        }
        else if (object instanceof FluidStack)
        {
            FluidStack fluidStack = (FluidStack) object;
            objectType = fluidStackType;
            this.stackSize = stackSize;
            fluidStack.amount = 1;
            wrappedStack = fluidStack;
        }
        else if (object instanceof WrappedStack)
        {
            WrappedStack wrappedStackObject = (WrappedStack) object;

            if (wrappedStackObject.getWrappedObject() != null)
            {
                this.objectType = wrappedStackObject.objectType;
                this.stackSize = stackSize;
                this.wrappedStack = wrappedStackObject.wrappedStack;
            }
            else
            {
                objectType = null;
                this.stackSize = -1;
                wrappedStack = null;
            }
        }
        else
        {
            objectType = null;
            this.stackSize = -1;
            wrappedStack = null;
        }
    }

    private WrappedStack(String objectType, Object wrappedStack, int stackSize)
    {
        this.objectType = objectType;
        this.wrappedStack = wrappedStack;
        this.stackSize = stackSize;
    }

    protected WrappedStack clone()
    {
        return this.clone(this.stackSize);
    }

    protected WrappedStack clone(int stackSize)
    {
        if(this.getWrappedObject() == null)
            return new WrappedStack(null, null, -1);

        stackSize = stackSize == -1 ? this.stackSize : stackSize;
        return new WrappedStack(this.objectType, this.wrappedStack, stackSize);
    }

    private static WrappedStack wrapAnonymous(Object object)
    {
        return wrapAnonymous(object, -1);
    }

    private static WrappedStack wrapAnonymous(Object object, int stackSize)
    {
        object = tryTransformStack(object);
        if(object == null)
            return null;

        return wrapAnonymousStack(object, stackSize);
    }

    private static WrappedStack wrapAnonymousStack(Object object, int stackSize)
    {
        if(object instanceof ItemStack)
            return wrapItemStack((ItemStack) object, stackSize);

        if(object instanceof OreStack)
            return wrapOreStack((OreStack) object, stackSize);

        if(object instanceof FluidStack)
            return wrapFluidStack((FluidStack) object, stackSize);

        if (object instanceof ArrayList)
            return wrapArrayList((ArrayList<?>) object, stackSize);

        if(object instanceof WrappedStack)
            return ((WrappedStack) object).clone(stackSize);

        return null;
    }

    private static WrappedStack wrapItemStack(ItemStack stack, int stackSize)
    {
        ItemStack wrappedStack = stack.copy();
        stackSize = stackSize == -1 ? wrappedStack.stackSize : stackSize;
        wrappedStack.stackSize = 1;
        return new WrappedStack(itemStackType, wrappedStack, stackSize);
    }

    private static WrappedStack wrapOreStack(OreStack stack, int stackSize)
    {
        stackSize = stackSize == -1 ? stack.stackSize : stackSize;
        stack.stackSize = 1;
        return new WrappedStack(oreStackType, stack, stackSize);
    }

    private static WrappedStack wrapFluidStack(FluidStack stack, int stackSize)
    {
        stackSize = stackSize == -1 ? stack.amount : stackSize;
        stack.amount = 1;
        return new WrappedStack(fluidStackType, stack, stackSize);
    }

    private static WrappedStack wrapArrayList(ArrayList<?> list, int stackSize)
    {
        OreStack possibleOreStack = OreStack.getOreStackFromList(list);
        if(possibleOreStack == null)
            return null;

        stackSize = stackSize == -1 ? possibleOreStack.stackSize : stackSize;
        possibleOreStack.stackSize = 1;
        return new WrappedStack(oreStackType, possibleOreStack, stackSize);
    }

    private static Object tryTransformStack(Object object)
    {
        if(object == null)
            return null;

        if(isWrappedStackType(object))
            return object;

        Object transformStack = tryWrapItemStack(object);
        if(transformStack != null)
            return transformStack;

        transformStack = tryWrapFluidStack(object);
        if(transformStack != null)
            return transformStack;

        return null;
    }

    private static boolean isWrappedStackType(Object object)
    {
        return object instanceof ItemStack || object instanceof FluidStack ||
                object instanceof OreStack ||object instanceof ArrayList ||
                object instanceof WrappedStack;
    }

    private static ItemStack tryWrapItemStack(Object object)
    {
        if(object instanceof Item)
            return new ItemStack((Item) object);

        if(object instanceof Block)
            return new ItemStack((Block) object);

        return null;
    }

    private static FluidStack tryWrapFluidStack(Object object)
    {
        return object instanceof Fluid
            ? new FluidStack((Fluid) object, 1000)
            : null;
    }

    // Using this method method we're asserting the same think twice,
    //  don't waste these precious cycles.
    public static boolean canBeWrapped(Object object)
    {
        if (object instanceof WrappedStack)
        {
            return true;
        }
        else if (object instanceof Item || object instanceof Block)
        {
            return true;
        }
        else if (object instanceof ItemStack)
        {
            if (((ItemStack) object).getItem() != null)
            {
                return true;
            }
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

    public Object getWrappedObject()
    {
        return wrappedStack;
    }

    public String getObjectType()
    {
        return this.objectType;
    }

    public static WrappedStack wrap(Object object)
    {
        return wrapAnonymous(object);
    }

    public static WrappedStack wrap(Object object, int stackSize)
    {
        return wrapAnonymous(object, stackSize);
    }

    public static WrappedStack createFromJson(String jsonWrappedObject)
    {
        try
        {
            return WrappedStackSerializer.createFromJson(jsonWrappedObject);
        }
        catch (JsonSyntaxException exception)
        {
            exception.printStackTrace();
        }
        catch (JsonParseException exception)
        {
            exception.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public String toJson()
    {
        return WrappedStackSerializer.toJson(this);
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
        return object instanceof WrappedStack && (this.compareTo((WrappedStack) object) == 0);
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
     * @return a string representation of the object.
     */
    @Override
    public String toString()
    {
        if (wrappedStack instanceof ItemStack)
        {
            ItemStack itemStack = (ItemStack) wrappedStack;
            String unlocalizedName = "";
            try
            {
                unlocalizedName = itemStack.getUnlocalizedName();
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                unlocalizedName = "no-name";
            }

            if (itemStack.hasTagCompound())
            {
                return String.format("%sxitemStack[%s@%s:%s]", stackSize, unlocalizedName, itemStack.getItemDamage(), itemStack.getTagCompound());
            }
            else
            {
                return String.format("%sxitemStack[%s@%s]", stackSize, unlocalizedName, itemStack.getItemDamage());
            }
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
            return "null-wrappedstack";
        }
    }

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
                    return 1;
                }
            }
            else if (wrappedStack1.wrappedStack instanceof OreStack)
            {
                if (wrappedStack2.wrappedStack instanceof ItemStack)
                {
                    return -1;
                }
                else if (wrappedStack2.wrappedStack instanceof OreStack)
                {
                    return OreStack.compare((OreStack) wrappedStack1.wrappedStack, (OreStack) wrappedStack2.wrappedStack);
                }
                else
                {
                    return 1;
                }
            }
            else if (wrappedStack1.wrappedStack instanceof FluidStack)
            {
                if (wrappedStack2.wrappedStack instanceof ItemStack || wrappedStack2.wrappedStack instanceof OreStack)
                {
                    return -1;
                }
                else if (wrappedStack2.wrappedStack instanceof FluidStack)
                {
                    return FluidHelper.compare((FluidStack) wrappedStack1.wrappedStack, (FluidStack) wrappedStack2.wrappedStack);
                }
                else
                {
                    return 1;
                }
            }
            else if (wrappedStack1.wrappedStack == null)
            {
                if (wrappedStack2.wrappedStack != null)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }

            return 0;
        }
    };
}

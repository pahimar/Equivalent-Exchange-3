package com.pahimar.ee3.exchange;

import com.google.gson.*;
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

public class WrappedStack implements Comparable<WrappedStack>, JsonDeserializer<WrappedStack>, JsonSerializer<WrappedStack>
{
    public static final Gson jsonSerializer = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(WrappedStack.class, new WrappedStack()).create();

    private final String objectType;
    private final Object wrappedStack;
    private int stackSize;

    public WrappedStack()
    {
        objectType = null;
        stackSize = -1;
        wrappedStack = null;
    }

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
                objectType = "itemstack";
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
            objectType = "orestack";
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
                objectType = "orestack";
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
            objectType = "fluidstack";
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
            objectType = "itemstack";
            this.stackSize = stackSize;
            itemStack.stackSize = 1;
            wrappedStack = itemStack;
        }
        else if (object instanceof OreStack)
        {
            OreStack oreStack = (OreStack) object;
            objectType = "orestack";
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
                objectType = "orestack";
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
            objectType = "fluidstack";
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

    public Object getWrappedObject()
    {
        return wrappedStack;
    }

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

    public static WrappedStack wrap(Object object)
    {
        if (canBeWrapped(object))
        {
            return new WrappedStack(object);
        }

        return null;
    }

    public static WrappedStack wrap(Object object, int stackSize)
    {
        if (canBeWrapped(object))
        {
            return new WrappedStack(object, stackSize);
        }

        return null;
    }

    public static WrappedStack createFromJson(String jsonWrappedObject) throws JsonParseException
    {
        try
        {
            return jsonSerializer.fromJson(jsonWrappedObject, WrappedStack.class);
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
        return jsonSerializer.toJson(this);
    }

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the
     * specified type.
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link com.google.gson.JsonDeserializationContext#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type)} method to create objects
     * for any non-trivial field of the returned object. However, you should never invoke it on the
     * the same type passing {@code jsonElement} since that will cause an infinite loop (Gson will call your
     * call-back method again).
     *
     * @param jsonElement The Json data being deserialized
     * @param typeOfT     The type of the Object to deserialize to
     * @param context
     * @return a deserialized object of the specified type typeOfT which is a subclass of {@code T}
     * @throws com.google.gson.JsonParseException if jsonElement is not in the expected format of {@code typeofT}
     */
    @Override
    public WrappedStack deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (!jsonElement.isJsonPrimitive())
        {
            JsonObject jsonWrappedStack = (JsonObject) jsonElement;

            int stackSize = 1;
            String objectType = null;
            Object stackObject = null;

            if (jsonWrappedStack.get("type") != null)
            {
                objectType = jsonWrappedStack.get("type").getAsString();
            }

            if (jsonWrappedStack.get("stackSize") != null)
            {
                stackSize = jsonWrappedStack.get("stackSize").getAsInt();
            }

            if (jsonWrappedStack.get("objectData") != null && !jsonWrappedStack.get("objectData").isJsonPrimitive())
            {
                if (objectType != null)
                {
                    if (objectType.equalsIgnoreCase("ItemStack"))
                    {
                        JsonItemStack jsonItemStack = JsonItemStack.jsonSerializer.fromJson(jsonWrappedStack.get("objectData"), JsonItemStack.class);
                        ItemStack itemStack = null;
                        Item item = (Item) Item.itemRegistry.getObject(jsonItemStack.itemName);
                        if (stackSize > 0 && item != null)
                        {
                            itemStack = new ItemStack(item, stackSize, jsonItemStack.itemDamage);
                            if (jsonItemStack.itemNBTTagCompound != null)
                            {
                                itemStack.stackTagCompound = jsonItemStack.itemNBTTagCompound;
                            }
                        }
                        stackObject = itemStack;
                    }
                    else if (objectType.equalsIgnoreCase("OreStack"))
                    {
                        OreStack oreStack = jsonSerializer.fromJson(jsonWrappedStack.get("objectData"), OreStack.class);

                        if (stackSize > 0)
                        {
                            oreStack.stackSize = stackSize;
                        }
                        stackObject = oreStack;
                    }
                    else if (objectType.equalsIgnoreCase("FluidStack"))
                    {
                        JsonFluidStack jsonFluidStack = JsonFluidStack.jsonSerializer.fromJson(jsonWrappedStack.get("objectData"), JsonFluidStack.class);
                        FluidStack fluidStack = new FluidStack(jsonFluidStack.fluid, jsonFluidStack.amount, jsonFluidStack.tag);

                        if (stackSize > 0)
                        {
                            fluidStack.amount = stackSize;
                        }
                        stackObject = fluidStack;
                    }
                }
            }

            if (stackObject != null)
            {
                return new WrappedStack(stackObject);
            }
            else
            {
                throw new JsonParseException(String.format("Unable to parse a wrappable stack object from the provided json: %s", jsonElement.toString()));
            }
        }
        else
        {
            throw new JsonParseException(String.format("Unable to parse a wrappable stack object from the provided json: %s", jsonElement.toString()));
        }
    }

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     * <p/>
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link com.google.gson.JsonSerializationContext#serialize(Object, java.lang.reflect.Type)} method to create JsonElements for any
     * non-trivial field of the {@code wrappedStack} object. However, you should never invoke it on the
     * {@code wrappedStack} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param wrappedStack the object that needs to be converted to Json.
     * @param typeOfSrc    the actual type (fully genericized version) of the source object.
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(WrappedStack wrappedStack, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonWrappedStack = new JsonObject();

        Gson gson = new Gson();

        jsonWrappedStack.addProperty("type", wrappedStack.objectType);
        jsonWrappedStack.addProperty("stackSize", wrappedStack.stackSize);

        if (wrappedStack.wrappedStack instanceof ItemStack)
        {
            JsonItemStack jsonItemStack = new JsonItemStack();
            jsonItemStack.itemName = Item.itemRegistry.getNameForObject(((ItemStack) wrappedStack.wrappedStack).getItem());
            jsonItemStack.itemDamage = ((ItemStack) wrappedStack.wrappedStack).getItemDamage();
            if (((ItemStack) wrappedStack.wrappedStack).stackTagCompound != null)
            {
                jsonItemStack.itemNBTTagCompound = ((ItemStack) wrappedStack.wrappedStack).stackTagCompound;
            }
            jsonWrappedStack.add("objectData", JsonItemStack.jsonSerializer.toJsonTree(jsonItemStack, JsonItemStack.class));
        }
        else if (wrappedStack.wrappedStack instanceof OreStack)
        {
            jsonWrappedStack.add("objectData", gson.toJsonTree(wrappedStack.wrappedStack, OreStack.class));
        }
        else if (wrappedStack.wrappedStack instanceof FluidStack)
        {
            JsonFluidStack jsonFluidStack = new JsonFluidStack((FluidStack) wrappedStack.wrappedStack);
            jsonWrappedStack.add("objectData", JsonFluidStack.jsonSerializer.toJsonTree(jsonFluidStack, JsonFluidStack.class));
        }

        return jsonWrappedStack;
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

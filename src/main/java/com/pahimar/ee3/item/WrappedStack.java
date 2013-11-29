package com.pahimar.ee3.item;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.pahimar.ee3.core.helper.FluidHelper;
import com.pahimar.ee3.core.helper.ItemHelper;
import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.lib.Compare;

public class WrappedStack
        implements Comparable<WrappedStack>, JsonDeserializer<WrappedStack> {

    private static final Gson gsonSerializer = (new GsonBuilder()).registerTypeAdapter(WrappedStack.class, new WrappedStack()).create();

    @SuppressWarnings("unused")
    private final String className;

    private int stackSize;
    private final Object wrappedStack;

    /**
     * 
     */
    public WrappedStack() {

        className = null;
        stackSize = -1;
        wrappedStack = null;
    }

    /**
     * 
     * @param object
     */
    public WrappedStack(Object object) {

        if (object instanceof Item) {
            object = new ItemStack((Item) object);
        }
        else if (object instanceof Block) {
            object = new ItemStack((Block) object);
        }
        else if (object instanceof Fluid) {
            object = new FluidStack((Fluid) object, 1);
        }

        if (object instanceof ItemStack) {

            ItemStack itemStack = ((ItemStack) object).copy();

            className = object.getClass().getSimpleName();
            stackSize = itemStack.stackSize;
            itemStack.stackSize = 1;
            wrappedStack = itemStack;
        }
        else if (object instanceof OreStack) {

            OreStack oreStack = (OreStack) object;

            className = object.getClass().getSimpleName();
            stackSize = oreStack.stackSize;
            oreStack.stackSize = 1;
            wrappedStack = oreStack;
        }
        else if (object instanceof ArrayList) {

            ArrayList<?> objectList = (ArrayList<?>) object;

            OreStack possibleOreStack = OreStack.getOreStackFromList(objectList);

            if (possibleOreStack != null) {

                className = possibleOreStack.getClass().getSimpleName();
                stackSize = possibleOreStack.stackSize;
                possibleOreStack.stackSize = 1;
                wrappedStack = possibleOreStack;
            }
            else {

                stackSize = -1;
                className = null;
                wrappedStack = null;
            }
        }
        else if (object instanceof EnergyStack) {

            EnergyStack energyStack = (EnergyStack) object;

            className = object.getClass().getSimpleName();
            stackSize = energyStack.stackSize;
            energyStack.stackSize = 1;
            wrappedStack = energyStack;
        }
        else if (object instanceof FluidStack) {

            FluidStack fluidStack = (FluidStack) object;

            className = object.getClass().getSimpleName();
            stackSize = fluidStack.amount;
            fluidStack.amount = 1;
            wrappedStack = fluidStack;
        }
        else if (object instanceof WrappedStack) {

            WrappedStack wrappedStackObject = (WrappedStack) object;

            className = wrappedStackObject.wrappedStack.getClass().getSimpleName();
            this.stackSize = wrappedStackObject.stackSize;
            this.wrappedStack = wrappedStackObject.wrappedStack;
        }
        else if (object instanceof String) {

            WrappedStack wrappedStack = createFromJson((String) object);

            if (wrappedStack != null) {

                className = object.getClass().getSimpleName();
                stackSize = wrappedStack.stackSize;
                this.wrappedStack = wrappedStack.wrappedStack;
            }
            else {

                className = null;
                stackSize = -1;
                this.wrappedStack = null;
            }
        }
        else {

            className = null;
            stackSize = -1;
            wrappedStack = null;
        }
    }

    /**
     * 
     * @return
     */
    public int getStackSize() {

        return stackSize;
    }

    /**
     * 
     * @param stackSize
     */
    public void setStackSize(int stackSize) {

        this.stackSize = stackSize;
    }

    /**
     * 
     * @return
     */
    public Object getWrappedStack() {

        return wrappedStack;
    }

    /**
     * 
     * @param jsonWrappedObject
     * @return
     * @throws JsonParseException
     */
    public static WrappedStack createFromJson(String jsonWrappedObject) throws JsonParseException {

        try {
            return (WrappedStack) gsonSerializer.fromJson(jsonWrappedObject, WrappedStack.class);
        }
        catch (JsonSyntaxException exception) {
            LogHelper.warning(exception.getMessage());
        }
        catch (JsonParseException exception) {
            LogHelper.warning(exception.getMessage());
        }

        return null;
    }

    /**
     * 
     * @return
     */
    public String toJson() {
        return gsonSerializer.toJson(this);
    }

    /*
     * Sort order (class-wise) goes ItemStack, OreStack, EnergyStack,
     * FluidStack, null
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(WrappedStack wrappedStack) {

        return comparator.compare(this, wrappedStack);
    }

    /**
     * 
     */
    @Override
    public int hashCode() {

        int hashCode = 1;
        hashCode = (37 * hashCode) + stackSize;

        if (wrappedStack instanceof ItemStack) {

            hashCode = (37 * hashCode) + ((ItemStack) wrappedStack).itemID;
            hashCode = (37 * hashCode) + ((ItemStack) wrappedStack).getItemDamage();

            if (((ItemStack) wrappedStack).getTagCompound() != null) {
                hashCode = (37 * hashCode) + ((ItemStack) wrappedStack).getTagCompound().hashCode();
            }
        }
        else if (wrappedStack instanceof OreStack) {

            if (((OreStack) wrappedStack).oreName != null) {
                hashCode = (37 * hashCode) + ((OreStack) wrappedStack).oreName.hashCode();
            }
        }
        else if (wrappedStack instanceof EnergyStack) {

            if (((EnergyStack) wrappedStack).energyName != null) {
                hashCode = (37 * hashCode) + ((EnergyStack) wrappedStack).energyName.hashCode();
            }
        }
        else if (wrappedStack instanceof FluidStack) {

            hashCode = (37 * hashCode) + ((FluidStack) wrappedStack).hashCode();

            if (((FluidStack) wrappedStack).tag != null) {
                hashCode = (37 * hashCode) + ((FluidStack) wrappedStack).tag.hashCode();
            }
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof WrappedStack)) {
            return false;
        }

        return (this.compareTo((WrappedStack) object) == Compare.EQUALS);
    }

    /**
     * 
     * @return a string representation of the object.
     */
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        if (wrappedStack instanceof ItemStack) {
            ItemStack itemStack = (ItemStack) wrappedStack;
            try {
                stringBuilder.append(String.format("%sxitemStack[%s:%s:%s:%s]", stackSize, itemStack.itemID, itemStack.getItemDamage(), itemStack.getUnlocalizedName(), itemStack.getItem().getClass().getCanonicalName()));
            }
            catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        else if (wrappedStack instanceof OreStack) {
            OreStack oreStack = (OreStack) wrappedStack;
            stringBuilder.append(String.format("%sxoreStack.%s", stackSize, oreStack.oreName));
        }
        else if (wrappedStack instanceof EnergyStack) {
            EnergyStack energyStack = (EnergyStack) wrappedStack;
            stringBuilder.append(String.format("%sxenergyStack.%s", stackSize, energyStack.energyName));
        }
        else if (wrappedStack instanceof FluidStack) {
            FluidStack fluidStack = (FluidStack) wrappedStack;
            stringBuilder.append(String.format("%sxfluidStack.%s", stackSize, fluidStack.getFluid().getName()));
        }
        else {
            stringBuilder.append("null");
        }

        return stringBuilder.toString();
    }

    /**
     * 
     * @param object
     * @return
     */
    public static boolean canBeWrapped(Object object) {

        if (object instanceof WrappedStack) {
            return true;
        }
        else if (object instanceof Item || object instanceof Block || object instanceof ItemStack) {
            return true;
        }
        else if (object instanceof OreStack) {
            return true;
        }
        else if (object instanceof List) {
            if (OreStack.getOreStackFromList((List<?>) object) != null) {
                return true;
            }
        }
        else if (object instanceof EnergyStack) {
            return true;
        }
        else if (object instanceof Fluid || object instanceof FluidStack) {
            return true;
        }

        return false;
    }

    /**
     * 
     */
    @Override
    public WrappedStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

        if (!jsonElement.isJsonPrimitive()) {

            JsonObject jsonWrappedStack = (JsonObject) jsonElement;

            int stackSize = -1;
            String className = null;
            Object wrappedStack = null;

            if (jsonWrappedStack.get("className") != null) {
                className = jsonWrappedStack.get("className").getAsString();
            }

            if (jsonWrappedStack.get("stackSize") != null) {
                stackSize = jsonWrappedStack.get("stackSize").getAsInt();
            }

            if (jsonWrappedStack.get("wrappedStack") != null && !jsonWrappedStack.get("wrappedStack").isJsonPrimitive()) {

                if (className.equalsIgnoreCase(Item.class.getSimpleName())) {

                    Item item = gsonSerializer.fromJson(jsonWrappedStack.get("wrappedStack"), Item.class);

                    wrappedStack = item;
                }
                else if (className.equalsIgnoreCase(Block.class.getSimpleName())) {

                    Block block = gsonSerializer.fromJson(jsonWrappedStack.get("wrappedStack"), Block.class);

                    wrappedStack = block;
                }
                else if (className.equalsIgnoreCase(Fluid.class.getSimpleName())) {

                    Fluid fluid = gsonSerializer.fromJson(jsonWrappedStack.get("wrappedStack"), Fluid.class);

                    wrappedStack = fluid;
                }
                else if (className.equalsIgnoreCase(ItemStack.class.getSimpleName())) {

                    ItemStack itemStack = gsonSerializer.fromJson(jsonWrappedStack.get("wrappedStack"), ItemStack.class);

                    if (stackSize > 0) {
                        itemStack.stackSize = stackSize;
                    }
                    wrappedStack = itemStack;
                }
                else if (className.equalsIgnoreCase(OreStack.class.getSimpleName())) {

                    OreStack oreStack = gsonSerializer.fromJson(jsonWrappedStack.get("wrappedStack"), OreStack.class);

                    if (stackSize > 0) {
                        oreStack.stackSize = stackSize;
                    }
                    wrappedStack = oreStack;
                }
                else if (className.equalsIgnoreCase(EnergyStack.class.getSimpleName())) {

                    EnergyStack energyStack = gsonSerializer.fromJson(jsonWrappedStack.get("wrappedStack"), EnergyStack.class);

                    if (stackSize > 0) {
                        energyStack.stackSize = stackSize;
                    }
                    wrappedStack = energyStack;
                }
                else if (className.equalsIgnoreCase(FluidStack.class.getSimpleName())) {

                    FluidStack fluidStack = gsonSerializer.fromJson(jsonWrappedStack.get("wrappedStack"), FluidStack.class);

                    if (stackSize > 0) {
                        fluidStack.amount = stackSize;
                    }
                    wrappedStack = fluidStack;
                }
            }

            if (wrappedStack != null) {
                return new WrappedStack(wrappedStack);
            }
            else {
                throw new JsonParseException(String.format("Unable to parse a wrappable stack object from the provided json: %s", jsonElement.toString()));
            }
        }
        else {
            throw new JsonParseException(String.format("Unable to parse a wrappable stack object from the provided json: %s", jsonElement.toString()));
        }
    }

    public static Comparator<WrappedStack> comparator = new Comparator<WrappedStack>() {

        @Override
        public int compare(WrappedStack wrappedStack1, WrappedStack wrappedStack2) {

            if (wrappedStack1.wrappedStack instanceof ItemStack) {
                if (wrappedStack2.wrappedStack instanceof ItemStack) {
                    return ItemHelper.compare((ItemStack) wrappedStack1.wrappedStack, (ItemStack) wrappedStack2.wrappedStack);
                }
                else if (wrappedStack2.wrappedStack instanceof OreStack) {
                    return Compare.GREATER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof EnergyStack) {
                    return Compare.GREATER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof FluidStack) {
                    return Compare.GREATER_THAN;
                }
                else {
                    return Compare.GREATER_THAN;
                }
            }
            else if (wrappedStack1.wrappedStack instanceof OreStack) {

                if (wrappedStack2.wrappedStack instanceof ItemStack) {
                    return Compare.LESSER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof OreStack) {
                    return OreStack.compare((OreStack) wrappedStack1.wrappedStack, (OreStack) wrappedStack2.wrappedStack);
                }
                else if (wrappedStack2.wrappedStack instanceof EnergyStack) {
                    return Compare.GREATER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof FluidStack) {
                    return Compare.GREATER_THAN;
                }
                else {
                    return Compare.GREATER_THAN;
                }
            }
            else if (wrappedStack1.wrappedStack instanceof EnergyStack) {

                if (wrappedStack2.wrappedStack instanceof ItemStack) {
                    return Compare.LESSER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof OreStack) {
                    return Compare.LESSER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof EnergyStack) {
                    return EnergyStack.compare((EnergyStack) wrappedStack1.wrappedStack, (EnergyStack) wrappedStack2.wrappedStack);
                }
                else if (wrappedStack2.wrappedStack instanceof FluidStack) {
                    return Compare.GREATER_THAN;
                }
                else {
                    return Compare.GREATER_THAN;
                }
            }
            else if (wrappedStack1.wrappedStack instanceof FluidStack) {

                if (wrappedStack2.wrappedStack instanceof ItemStack) {
                    return Compare.LESSER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof OreStack) {
                    return Compare.LESSER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof EnergyStack) {
                    return Compare.LESSER_THAN;
                }
                else if (wrappedStack2.wrappedStack instanceof FluidStack) {
                    return FluidHelper.compare((FluidStack) wrappedStack1.wrappedStack, (FluidStack) wrappedStack2.wrappedStack);
                }
                else {
                    return Compare.GREATER_THAN;
                }
            }
            else if (wrappedStack1.wrappedStack == null) {

                if (wrappedStack2.wrappedStack != null) {
                    return Compare.LESSER_THAN;
                }
                else {
                    return Compare.EQUALS;
                }
            }

            return Compare.EQUALS;
        }

    };
}

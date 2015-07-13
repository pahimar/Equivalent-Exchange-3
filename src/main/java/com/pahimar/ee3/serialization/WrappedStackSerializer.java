package com.pahimar.ee3.serialization;

import com.google.gson.*;
import com.pahimar.ee3.exchange.JsonFluidStack;
import com.pahimar.ee3.exchange.JsonItemStack;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;

public class WrappedStackSerializer implements JsonDeserializer<WrappedStack>, JsonSerializer<WrappedStack>
{
    private static final String memberTypeKey = "type";
    private static final String memberStackSizeKey = "stackSize";
    private static final String memberObjectDataKey = "objectData";

    public static WrappedStack createFromJson(String jsonWrappedObject)
            throws JsonParseException
    {
        return JsonSerialization.jsonSerializer.fromJson(jsonWrappedObject, WrappedStack.class);
    }

    public static String toJson(WrappedStack wrappedStack)
    {
        return JsonSerialization.jsonSerializer.toJson(wrappedStack);
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
    public WrappedStack deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException
    {
        if (jsonElement.isJsonPrimitive())
            throw new JsonParseException(String.format("Unable to parse a wrappable stack object from the provided json: %s", jsonElement.toString()));

        JsonObject jsonWrappedStack = (JsonObject) jsonElement;

        int stackSize = 1;
        String objectType = null;
        Object stackObject = null;

        if (jsonWrappedStack.get(memberTypeKey) != null)
            objectType = jsonWrappedStack.get(memberTypeKey).getAsString();

        if (jsonWrappedStack.get(memberStackSizeKey) != null)
            stackSize = jsonWrappedStack.get(memberStackSizeKey).getAsInt();

        if (jsonWrappedStack.get(memberObjectDataKey) != null && !jsonWrappedStack.get(memberObjectDataKey).isJsonPrimitive())
        {
            if (objectType != null)
            {
                JsonElement objectDataElement = jsonWrappedStack.get(memberObjectDataKey);
                if (objectType.equalsIgnoreCase(WrappedStack.itemStackType))
                    stackObject = readItemStack(objectDataElement, stackSize);
                else if (objectType.equalsIgnoreCase(WrappedStack.oreStackType))
                    stackObject = readOreStack(objectDataElement, stackSize);
                else if (objectType.equalsIgnoreCase(WrappedStack.fluidStackType))
                    stackObject = readFluidStack(objectDataElement, stackSize);
            }
        }

        if (stackObject == null)
            throw new JsonParseException(String.format("Unable to parse a wrappable stack object from the provided json: %s", jsonElement.toString()));

        return WrappedStack.wrap(stackObject);
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

        jsonWrappedStack.addProperty("type", wrappedStack.getObjectType());
        jsonWrappedStack.addProperty("stackSize", wrappedStack.getStackSize());

        Object stackContents = wrappedStack.getWrappedObject();
        if (stackContents instanceof ItemStack)
            writeItemStack((ItemStack) stackContents, jsonWrappedStack);
        else if (stackContents instanceof OreStack)
            writeOreStack((OreStack) stackContents, jsonWrappedStack);
        else if (stackContents instanceof FluidStack)
            writeFluidStack((FluidStack) stackContents, jsonWrappedStack);

        return jsonWrappedStack;
    }

    private static ItemStack readItemStack(JsonElement element, int stackSize)
    {
        JsonItemStack jsonItemStack = JsonSerialization.jsonSerializer.fromJson(element, JsonItemStack.class);
        ItemStack itemStack = null;
        Item item = (Item) Item.itemRegistry.getObject(jsonItemStack.itemName);
        if (stackSize > 0 && item != null)
        {
            itemStack = new ItemStack(item, stackSize, jsonItemStack.itemDamage);
            if (jsonItemStack.itemNBTTagCompound != null)
                itemStack.stackTagCompound = jsonItemStack.itemNBTTagCompound;
        }

        return itemStack;
    }

    private static OreStack readOreStack(JsonElement element, int stackSize)
    {
        OreStack oreStack = JsonSerialization.jsonSerializer.fromJson(element, OreStack.class);

        if (stackSize > 0)
            oreStack.stackSize = stackSize;

        return oreStack;
    }

    private static FluidStack readFluidStack(JsonElement element, int stackSize)
    {
        JsonFluidStack jsonFluidStack = JsonSerialization.jsonSerializer.fromJson(element, JsonFluidStack.class);
        FluidStack fluidStack = new FluidStack(jsonFluidStack.fluid, jsonFluidStack.amount, jsonFluidStack.tag);

        if (stackSize > 0)
            fluidStack.amount = stackSize;

        return fluidStack;
    }

    private static void writeItemStack(ItemStack stack, JsonObject target)
    {
        JsonItemStack jsonItemStack = new JsonItemStack();
        jsonItemStack.itemName = Item.itemRegistry.getNameForObject(stack.getItem());
        jsonItemStack.itemDamage = stack.getItemDamage();
        if (stack.stackTagCompound != null)
            jsonItemStack.itemNBTTagCompound = stack.stackTagCompound;

        target.add("objectData", JsonSerialization.jsonSerializer.toJsonTree(jsonItemStack, JsonItemStack.class));
    }

    private static void writeOreStack(OreStack stack, JsonObject target)
    {
        target.add("objectData", new Gson().toJsonTree(stack, OreStack.class));
    }

    private static void writeFluidStack(FluidStack stack, JsonObject target)
    {
        JsonFluidStack jsonFluidStack = new JsonFluidStack(stack);
        target.add("objectData", JsonSerialization.jsonSerializer.toJsonTree(jsonFluidStack, JsonFluidStack.class));
    }
}

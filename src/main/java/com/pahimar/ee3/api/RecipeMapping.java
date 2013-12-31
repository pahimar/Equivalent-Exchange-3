package com.pahimar.ee3.api;

import com.google.gson.*;
import com.pahimar.ee3.helper.LogHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecipeMapping implements JsonSerializer<RecipeMapping>, JsonDeserializer<RecipeMapping>
{
    private static final Gson gsonSerializer = (new GsonBuilder()).registerTypeAdapter(RecipeMapping.class, new RecipeMapping()).create();

    public final WrappedStack outputWrappedStack;
    public final List<WrappedStack> inputWrappedStacks;

    private RecipeMapping()
    {
        outputWrappedStack = null;
        inputWrappedStacks = null;
    }

    public RecipeMapping(Object outputStack, List<?> inputStacks)
    {
        this.outputWrappedStack = new WrappedStack(outputStack);

        List<WrappedStack> wrappedStacks = new ArrayList<WrappedStack>();
        for (Object object : inputStacks)
        {

            WrappedStack wrappedStack = new WrappedStack(object);

            if (wrappedStack.getWrappedStack() != null)
            {
                wrappedStacks.add(wrappedStack);
            }
        }

        if (inputStacks.size() == wrappedStacks.size())
        {
            this.inputWrappedStacks = wrappedStacks;
        }
        else
        {
            this.inputWrappedStacks = null;
        }
    }

    public static RecipeMapping createFromJson(String jsonRecipeMapping)
    {
        try
        {
            return gsonSerializer.fromJson(jsonRecipeMapping, RecipeMapping.class);
        }
        catch (JsonSyntaxException exception)
        {
            LogHelper.severe(exception.getMessage());
        }
        catch (JsonParseException exception)
        {
            LogHelper.severe(exception.getMessage());
        }

        return null;
    }

    public String toJson()
    {
        return gsonSerializer.toJson(this);
    }

    @Override
    public RecipeMapping deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException
    {

        if (!jsonElement.isJsonPrimitive())
        {

            JsonObject jsonRecipeMapping = (JsonObject) jsonElement;

            WrappedStack outputStack = null;
            List<WrappedStack> inputStacks = new ArrayList<WrappedStack>();

            if (jsonRecipeMapping.get("outputWrappedStack") != null)
            {
                outputStack = new WrappedStack().deserialize(jsonRecipeMapping.get("outputWrappedStack").getAsJsonObject(), type, context);
            }

            if (jsonRecipeMapping.get("inputWrappedStacks") != null)
            {
                JsonArray jsonInputStacks = jsonRecipeMapping.get("inputWrappedStacks").getAsJsonArray();

                for (int i = 0; i < jsonInputStacks.size(); i++)
                {
                    WrappedStack inputStack = new WrappedStack().deserialize(jsonInputStacks.get(i).getAsJsonObject(), type, context);
                    inputStacks.add(inputStack);
                }
            }

            return new RecipeMapping(outputStack, inputStacks);
        }

        return null;
    }

    @Override
    public JsonElement serialize(RecipeMapping recipeMapping, Type type, JsonSerializationContext context)
    {
        JsonObject jsonRecipeMapping = new JsonObject();

        Gson gsonWrappedStack = new Gson();

        JsonArray jsonArray = new JsonArray();
        for (WrappedStack inputStack : recipeMapping.inputWrappedStacks)
        {
            jsonArray.add(gsonWrappedStack.toJsonTree(inputStack, WrappedStack.class));
        }

        jsonRecipeMapping.add("outputWrappedStack", gsonWrappedStack.toJsonTree(recipeMapping.outputWrappedStack, WrappedStack.class));
        jsonRecipeMapping.add("inputWrappedStacks", jsonArray);

        return jsonRecipeMapping;
    }

    @Override
    public String toString()
    {
        return String.format("RecipeMapping[Output: %s, Input: %s]", outputWrappedStack, inputWrappedStacks);
    }
}

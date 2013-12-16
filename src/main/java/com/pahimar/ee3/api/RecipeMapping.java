package com.pahimar.ee3.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pahimar.ee3.item.WrappedStack;

import java.util.ArrayList;
import java.util.List;

public class RecipeMapping
{
    private static Gson gsonSerializer = new Gson();

    public final WrappedStack outputWrappedStack;
    public final List<WrappedStack> inputWrappedStacks;

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
            exception.printStackTrace();
            // TODO Log something regarding the failed parse
        }

        return null;
    }

    public String toJson()
    {
        return gsonSerializer.toJson(this);
    }
}

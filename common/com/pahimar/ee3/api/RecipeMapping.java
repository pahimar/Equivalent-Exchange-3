package com.pahimar.ee3.api;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pahimar.ee3.item.WrappedStack;

public class RecipeMapping
{
    private static Gson gsonSerializer = new Gson();
    
    public final WrappedStack outputWrappedStack;
    public final List<WrappedStack> inputWrappedStacks;
    
    public RecipeMapping(WrappedStack outputWrappedStack, List<WrappedStack> inputWrappedStacks) {
        this.outputWrappedStack = outputWrappedStack;
        this.inputWrappedStacks = inputWrappedStacks;
    }
    
    public static RecipeMapping createFromJson(String jsonRecipeMapping) {
        
        try {
            return (RecipeMapping) gsonSerializer.fromJson(jsonRecipeMapping, RecipeMapping.class);
        }
        catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            // TODO Log something regarding the failed parse
        }

        return null;
    }
    
    public String toJson() {
        return gsonSerializer.toJson(this);
    }
}

package com.pahimar.ee3.emc;

import com.google.common.collect.ImmutableSortedMap;
import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.helper.EmcHelper;
import com.pahimar.ee3.lib.Compare;
import com.pahimar.ee3.recipe.RecipeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class EmcRegistry
{
    private static EmcRegistry emcRegistry = null;

    private ImmutableSortedMap<WrappedStack, EmcValue> stackMappings;
    private ImmutableSortedMap<EmcValue, List<WrappedStack>> valueMappings;

    private EmcRegistry()
    {
    }

    public static EmcRegistry getInstance()
    {
        if (emcRegistry == null)
        {
            emcRegistry = new EmcRegistry();
            emcRegistry.init();
        }

        return emcRegistry;
    }

    private void init()
    {
        HashMap<WrappedStack, EmcValue> stackValueMap = new HashMap<WrappedStack, EmcValue>();
        
        /*
         *  Default values
         */
        Map<WrappedStack, EmcValue> defaultValuesMap = EmcValuesDefault.getDefaultValueMap();
        for (WrappedStack keyStack : defaultValuesMap.keySet())
        {
            EmcValue factoredEmcValue = null;
            WrappedStack factoredKeyStack = null;

            if (keyStack != null && keyStack.getWrappedStack() != null && keyStack.getStackSize() > 0)
            {
                if (defaultValuesMap.get(keyStack) != null && Float.compare(defaultValuesMap.get(keyStack).getValue(), 0f) > Compare.EQUALS)
                {
                    factoredEmcValue = EmcHelper.factorEmcValue(defaultValuesMap.get(keyStack), keyStack.getStackSize());
                    factoredKeyStack = new WrappedStack(keyStack, 1);
                }
            }

            if (factoredEmcValue != null)
            {
                if (stackValueMap.containsKey(factoredKeyStack))
                {
                    if (factoredEmcValue.compareTo(stackValueMap.get(factoredKeyStack)) == Compare.LESSER_THAN)
                    {
                        stackValueMap.put(factoredKeyStack, factoredEmcValue);
                    }
                }
                else
                {
                    stackValueMap.put(factoredKeyStack, factoredEmcValue);
                }
            }
        }
        
        /*
         *  IMC Pre-assigned values
         */
        Map<WrappedStack, EmcValue> preAssignedValuesMap = EmcValuesIMC.getPreAssignedValues();
        for (WrappedStack keyStack : preAssignedValuesMap.keySet())
        {
            EmcValue factoredEmcValue = null;
            WrappedStack factoredKeyStack = null;

            if (keyStack != null && keyStack.getWrappedStack() != null && keyStack.getStackSize() > 0)
            {
                if (preAssignedValuesMap.get(keyStack) != null && Float.compare(preAssignedValuesMap.get(keyStack).getValue(), 0f) > Compare.EQUALS)
                {
                    factoredEmcValue = EmcHelper.factorEmcValue(preAssignedValuesMap.get(keyStack), keyStack.getStackSize());
                    factoredKeyStack = new WrappedStack(keyStack, 1);
                }
            }

            if (factoredEmcValue != null)
            {
                if (stackValueMap.containsKey(factoredKeyStack))
                {
                    if (factoredEmcValue.compareTo(stackValueMap.get(factoredKeyStack)) == Compare.LESSER_THAN)
                    {
                        stackValueMap.put(factoredKeyStack, factoredEmcValue);
                    }
                }
                else
                {
                    stackValueMap.put(factoredKeyStack, factoredEmcValue);
                }
            }
        }
        
        /*
         *  Auto-assignment
         */
        // Initialize the maps for the first pass to happen
        ImmutableSortedMap.Builder<WrappedStack, EmcValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        stackMappings = stackMappingsBuilder.build();
        Map<WrappedStack, EmcValue> computedStackValues = computeStackMappings();

        // Initialize the pass counter
        int passNumber = 0;

        while ((computedStackValues.size() > 0) && (passNumber < 16))
        {
            // Increment the pass counter
            passNumber++;

            // Set the values for getEmcValue calls in the auto-assignment computation
            stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(stackValueMap);
            stackMappings = stackMappingsBuilder.build();

            // Compute stack mappings from existing stack mappings
            computedStackValues = computeStackMappings();
            for (WrappedStack keyStack : computedStackValues.keySet())
            {
                EmcValue factoredEmcValue = null;
                WrappedStack factoredKeyStack = null;

                if (keyStack != null && keyStack.getWrappedStack() != null && keyStack.getStackSize() > 0)
                {
                    if (computedStackValues.get(keyStack) != null && Float.compare(computedStackValues.get(keyStack).getValue(), 0f) > Compare.EQUALS)
                    {
                        factoredEmcValue = EmcHelper.factorEmcValue(computedStackValues.get(keyStack), keyStack.getStackSize());
                        factoredKeyStack = new WrappedStack(keyStack, 1);
                    }
                }

                if (factoredEmcValue != null)
                {
                    if (stackValueMap.containsKey(factoredKeyStack))
                    {
                        if (factoredEmcValue.compareTo(stackValueMap.get(factoredKeyStack)) == Compare.LESSER_THAN)
                        {
                            stackValueMap.put(factoredKeyStack, factoredEmcValue);
                        }
                    }
                    else
                    {
                        stackValueMap.put(factoredKeyStack, factoredEmcValue);
                    }
                }
            }
        }
        
        /*
         *  IMC Post-assigned values
         */
        Map<WrappedStack, EmcValue> postAssignedValuesMap = EmcValuesIMC.getPostAssignedValues();
        for (WrappedStack keyStack : postAssignedValuesMap.keySet())
        {
            EmcValue factoredEmcValue = null;
            WrappedStack factoredKeyStack = null;

            if (keyStack != null && keyStack.getWrappedStack() != null && keyStack.getStackSize() > 0)
            {
                if (postAssignedValuesMap.get(keyStack) != null && Float.compare(postAssignedValuesMap.get(keyStack).getValue(), 0f) > Compare.EQUALS)
                {
                    factoredEmcValue = EmcHelper.factorEmcValue(postAssignedValuesMap.get(keyStack), keyStack.getStackSize());
                    factoredKeyStack = new WrappedStack(keyStack, 1);
                }
            }

            // Post auto assignment values are meant to override all over values, so we just take the value given
            if (factoredEmcValue != null)
            {
                stackValueMap.put(factoredKeyStack, factoredEmcValue);
            }
        }

        /**
         * Finalize the stack to value map
         */
        stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        stackMappings = stackMappingsBuilder.build();
        
        /*
         *  Value map resolution
         */
        SortedMap<EmcValue, List<WrappedStack>> tempValueMappings = new TreeMap<EmcValue, List<WrappedStack>>();

        for (WrappedStack stack : stackMappings.keySet())
        {
            if (stack != null)
            {
                EmcValue value = stackMappings.get(stack);

                if (value != null)
                {
                    if (tempValueMappings.containsKey(value))
                    {
                        if (!(tempValueMappings.get(value).contains(stack)))
                        {
                            tempValueMappings.get(value).add(stack);
                        }
                    }
                    else
                    {
                        tempValueMappings.put(value, new ArrayList<WrappedStack>(Arrays.asList(stack)));
                    }
                }
            }
        }

        valueMappings = ImmutableSortedMap.copyOf(tempValueMappings);
    }

    private Map<WrappedStack, EmcValue> computeStackMappings()
    {
        Map<WrappedStack, EmcValue> computedStackMap = new HashMap<WrappedStack, EmcValue>();

        for (WrappedStack recipeOutput : RecipeRegistry.getInstance().getRecipeMappings().keySet())
        {
            if (!hasEmcValue(recipeOutput.getWrappedStack(), false) && !computedStackMap.containsKey(recipeOutput))
            {
                EmcValue lowestValue = null;

                for (List<WrappedStack> recipeInputs : RecipeRegistry.getInstance().getRecipeMappings().get(recipeOutput))
                {
                    EmcValue computedValue = EmcHelper.computeEmcValueFromList(recipeInputs);
                    computedValue = EmcHelper.factorEmcValue(computedValue, recipeOutput.getStackSize());

                    if (computedValue != null)
                    {
                        if (computedValue.compareTo(lowestValue) < 0)
                        {
                            lowestValue = computedValue;
                        }
                    }
                }

                if ((lowestValue != null) && (lowestValue.getValue() > 0f))
                {
                    computedStackMap.put(new WrappedStack(recipeOutput.getWrappedStack()), lowestValue);
                }
            }
        }

        return computedStackMap;
    }

    public boolean hasEmcValue(Object object, boolean strict)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack stack = new WrappedStack(object);

            if (emcRegistry.stackMappings.containsKey(new WrappedStack(stack.getWrappedStack())))
            {
                return true;
            }
            else
            {
                if (!strict)
                {
                    if (stack.getWrappedStack() instanceof ItemStack)
                    {
                        ItemStack wrappedItemStack = (ItemStack) stack.getWrappedStack();

                        // If its an OreDictionary item, scan its siblings for values
                        if (OreDictionary.getOreID(wrappedItemStack) != -1)
                        {

                            OreStack oreStack = new OreStack(wrappedItemStack);

                            if (emcRegistry.stackMappings.containsKey(new WrappedStack(oreStack)))
                            {
                                return true;
                            }
                            else
                            {
                                for (ItemStack itemStack : OreDictionary.getOres(OreDictionary.getOreID(wrappedItemStack)))
                                {
                                    if (emcRegistry.stackMappings.containsKey(new WrappedStack(itemStack)))
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
                        // Else, scan for if there is a wildcard value for it
                        else
                        {
                            for (WrappedStack valuedStack : emcRegistry.stackMappings.keySet())
                            {

                                if (valuedStack.getWrappedStack() instanceof ItemStack)
                                {
                                    ItemStack valuedItemStack = (ItemStack) valuedStack.getWrappedStack();

                                    if ((valuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || wrappedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) && valuedItemStack.itemID == wrappedItemStack.itemID)
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    else if (stack.getWrappedStack() instanceof OreStack)
                    {
                        OreStack oreStack = (OreStack)stack.getWrappedStack();
                        for (ItemStack oreItemStack : OreDictionary.getOres(oreStack.oreName))
                        {
                            if (emcRegistry.stackMappings.containsKey(new WrappedStack(oreItemStack)))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean hasEmcValue(Object object)
    {
        return hasEmcValue(object, false);
    }

    public EmcValue getEmcValue(Object object, boolean strict)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack stack = new WrappedStack(object);

            if (emcRegistry.stackMappings.containsKey(new WrappedStack(stack.getWrappedStack())))
            {
                return emcRegistry.stackMappings.get(new WrappedStack(stack.getWrappedStack()));
            }
            else
            {
                if (!strict)
                {
                    if (stack.getWrappedStack() instanceof ItemStack)
                    {
                        EmcValue lowestValue = null;
                        ItemStack wrappedItemStack = (ItemStack) stack.getWrappedStack();

                        if (OreDictionary.getOreID(wrappedItemStack) != -1)
                        {
                            OreStack oreStack = new OreStack(wrappedItemStack);

                            if (emcRegistry.stackMappings.containsKey(new WrappedStack(oreStack)))
                            {
                                return emcRegistry.stackMappings.get(new WrappedStack(oreStack));
                            }
                            else
                            {
                                for (ItemStack itemStack : OreDictionary.getOres(OreDictionary.getOreID(wrappedItemStack)))
                                {
                                    if (emcRegistry.stackMappings.containsKey(new WrappedStack(itemStack)))
                                    {
                                        if (lowestValue == null)
                                        {
                                            lowestValue = emcRegistry.stackMappings.get(new WrappedStack(itemStack));
                                        }
                                        else
                                        {
                                            EmcValue itemValue = emcRegistry.stackMappings.get(new WrappedStack(itemStack));

                                            if (itemValue.compareTo(lowestValue) < 0)
                                            {
                                                lowestValue = itemValue;
                                            }
                                        }
                                    }
                                }

                                return lowestValue;
                            }
                        }
                        else
                        {
                            for (WrappedStack valuedStack : emcRegistry.stackMappings.keySet())
                            {

                                if (valuedStack.getWrappedStack() instanceof ItemStack)
                                {
                                    ItemStack valuedItemStack = (ItemStack) valuedStack.getWrappedStack();

                                    if ((valuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || wrappedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) && valuedItemStack.itemID == wrappedItemStack.itemID)
                                    {
                                        EmcValue stackValue = emcRegistry.stackMappings.get(valuedStack);

                                        if (stackValue.compareTo(lowestValue) < 0)
                                        {
                                            lowestValue = stackValue;
                                        }
                                    }
                                }
                            }

                            return lowestValue;
                        }
                    }
                    else if (stack.getWrappedStack() instanceof OreStack)
                    {
                        OreStack oreStack = (OreStack)stack.getWrappedStack();
                        for (ItemStack oreItemStack : OreDictionary.getOres(oreStack.oreName))
                        {
                            if (emcRegistry.stackMappings.containsKey(new WrappedStack(oreItemStack)))
                            {
                                return emcRegistry.stackMappings.get(new WrappedStack(oreItemStack));
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    public EmcValue getEmcValue(Object object)
    {
        return getEmcValue(object, false);
    }

    @SuppressWarnings("unused")
    public List<WrappedStack> getStacksInRange(int start, int finish)
    {
        return getStacksInRange(new EmcValue(start), new EmcValue(finish));
    }

    public List<WrappedStack> getStacksInRange(float start, float finish)
    {
        return getStacksInRange(new EmcValue(start), new EmcValue(finish));
    }

    public List<WrappedStack> getStacksInRange(EmcValue start, EmcValue finish)
    {
        List<WrappedStack> stacksInRange = new ArrayList<WrappedStack>();

        SortedMap<EmcValue, List<WrappedStack>> tailMap = emcRegistry.valueMappings.tailMap(start);
        SortedMap<EmcValue, List<WrappedStack>> headMap = emcRegistry.valueMappings.headMap(finish);

        SortedMap<EmcValue, List<WrappedStack>> smallerMap;
        SortedMap<EmcValue, List<WrappedStack>> biggerMap;

        if (!tailMap.isEmpty() && !headMap.isEmpty())
        {

            if (tailMap.size() <= headMap.size())
            {
                smallerMap = tailMap;
                biggerMap = headMap;
            }
            else
            {
                smallerMap = headMap;
                biggerMap = tailMap;
            }

            for (EmcValue value : smallerMap.keySet())
            {
                if (biggerMap.containsKey(value))
                {
                    stacksInRange.addAll(emcRegistry.valueMappings.get(value));
                }
            }
        }

        return stacksInRange;
    }

    public ImmutableSortedMap<WrappedStack, EmcValue> getStackToEmcValueMap()
    {
        return stackMappings;
    }

    public ImmutableSortedMap<EmcValue, List<WrappedStack>> getEmcValueToStackMap()
    {
        return valueMappings;
    }
}

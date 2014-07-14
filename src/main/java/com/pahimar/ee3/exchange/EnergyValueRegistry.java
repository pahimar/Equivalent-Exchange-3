package com.pahimar.ee3.exchange;

import com.google.common.collect.ImmutableSortedMap;
import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.api.IEnergyValueProvider;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.util.EnergyValueHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class EnergyValueRegistry
{
    private static EnergyValueRegistry energyValueRegistry = null;
    private static Map<WrappedStack, EnergyValue> preAssignedMappings;
    private static Map<WrappedStack, EnergyValue> postAssignedMappings;
    private ImmutableSortedMap<WrappedStack, EnergyValue> stackMappings;
    private ImmutableSortedMap<EnergyValue, List<WrappedStack>> valueMappings;

    private EnergyValueRegistry()
    {
    }

    public static EnergyValueRegistry getInstance()
    {
        if (energyValueRegistry == null)
        {
            energyValueRegistry = new EnergyValueRegistry();
        }

        return energyValueRegistry;
    }

    public static void addPreAssignedEnergyValue(Object object, float energyValue)
    {
        addPreAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public static void addPreAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        if (preAssignedMappings == null)
        {
            preAssignedMappings = new HashMap<WrappedStack, EnergyValue>();
        }

        if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getEnergyValue(), 0f) > 0)
        {
            WrappedStack wrappedStack = new WrappedStack(object);

            if (wrappedStack.getStackSize() > 0)
            {
                WrappedStack factoredWrappedStack = new WrappedStack(wrappedStack, 1);
                EnergyValue factoredEnergyValue = EnergyValueHelper.factorEnergyValue(energyValue, wrappedStack.getStackSize());

                if (preAssignedMappings.containsKey(factoredWrappedStack))
                {
                    if (factoredEnergyValue.compareTo(preAssignedMappings.get(factoredWrappedStack)) < 0)
                    {
                        preAssignedMappings.put(factoredWrappedStack, factoredEnergyValue);
                    }
                }
                else
                {
                    preAssignedMappings.put(factoredWrappedStack, factoredEnergyValue);
                }
            }
        }
    }

    public static void addPostAssignedEnergyValue(Object object, float energyValue)
    {
        if (postAssignedMappings == null)
        {
            postAssignedMappings = new HashMap<WrappedStack, EnergyValue>();
        }

        if (WrappedStack.canBeWrapped(object) && Float.compare(energyValue, 0f) > 0)
        {
            WrappedStack wrappedStack = new WrappedStack(object);

            if (wrappedStack.getStackSize() > 0)
            {
                WrappedStack factoredWrappedStack = new WrappedStack(wrappedStack, 1);
                EnergyValue factoredEnergyValue = new EnergyValue(energyValue * 1f / wrappedStack.getStackSize(), EnergyValue.EnergyType.CORPOREAL);

                postAssignedMappings.put(factoredWrappedStack, factoredEnergyValue);
            }
        }
    }

    public static void addPostAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        if (postAssignedMappings == null)
        {
            postAssignedMappings = new HashMap<WrappedStack, EnergyValue>();
        }

        if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getEnergyValue(), 0f) > 0)
        {
            WrappedStack wrappedStack = new WrappedStack(object);

            if (wrappedStack.getStackSize() > 0)
            {
                WrappedStack factoredWrappedStack = new WrappedStack(wrappedStack, 1);
                EnergyValue factoredEnergyValue = EnergyValueHelper.factorEnergyValue(energyValue, wrappedStack.getStackSize());

                postAssignedMappings.put(factoredWrappedStack, factoredEnergyValue);
            }
        }
    }

    public boolean hasEnergyValue(Object object, boolean strict)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack stack = new WrappedStack(object);

            if (stack.getWrappedStack() instanceof ItemStack && ((ItemStack) stack.getWrappedStack()).getItem() instanceof IEnergyValueProvider)
            {
                EnergyValue energyValue = ((IEnergyValueProvider) ((ItemStack) stack.getWrappedStack()).getItem()).getEnergyValue((ItemStack) stack.getWrappedStack());

                if (energyValue != null && energyValue.getEnergyValue() > 0f)
                {
                    return true;
                }
            }
            else
            {
                if (energyValueRegistry.stackMappings.containsKey(new WrappedStack(stack.getWrappedStack())))
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
                            if (OreDictionary.getOreIDs(wrappedItemStack).length > 0)
                            {

                                OreStack oreStack = new OreStack(wrappedItemStack);

                                if (energyValueRegistry.stackMappings.containsKey(new WrappedStack(oreStack)))
                                {
                                    return true;
                                }
                                else
                                {
                                    for (int oreId : OreDictionary.getOreIDs(wrappedItemStack))
                                    {
                                        for (ItemStack itemStack : OreDictionary.getOres(OreDictionary.getOreName(oreId)))
                                        {
                                            if (energyValueRegistry.stackMappings.containsKey(new WrappedStack(itemStack)))
                                            {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                            // Else, scan for if there is a wildcard value for it
                            else
                            {
                                for (WrappedStack valuedStack : energyValueRegistry.stackMappings.keySet())
                                {
                                    if (valuedStack.getWrappedStack() instanceof ItemStack)
                                    {
                                        ItemStack valuedItemStack = (ItemStack) valuedStack.getWrappedStack();

                                        if (Item.getIdFromItem(valuedItemStack.getItem()) == Item.getIdFromItem(wrappedItemStack.getItem()))
                                        {
                                            if (valuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || wrappedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE)
                                            {
                                                return true;
                                            }
                                            else if (wrappedItemStack.getItem().isDamageable() && wrappedItemStack.isItemDamaged())
                                            {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else if (stack.getWrappedStack() instanceof OreStack)
                        {
                            OreStack oreStack = (OreStack) stack.getWrappedStack();
                            for (ItemStack oreItemStack : OreDictionary.getOres(oreStack.oreName))
                            {
                                if (energyValueRegistry.stackMappings.containsKey(new WrappedStack(oreItemStack)))
                                {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean hasEnergyValue(Object object)
    {
        return hasEnergyValue(object, false);
    }

    public EnergyValue getEnergyValue(Object object)
    {
        return getEnergyValue(object, false);
    }

    public EnergyValue getEnergyValue(Object object, boolean strict)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack stack = new WrappedStack(object);

            if (stack.getWrappedStack() instanceof ItemStack && ((ItemStack) stack.getWrappedStack()).getItem() instanceof IEnergyValueProvider)
            {
                EnergyValue energyValue = ((IEnergyValueProvider) ((ItemStack) stack.getWrappedStack()).getItem()).getEnergyValue((ItemStack) stack.getWrappedStack());

                if (energyValue != null && energyValue.getEnergyValue() > 0f)
                {
                    return energyValue;
                }
            }
            else
            {
                if (energyValueRegistry.stackMappings.containsKey(new WrappedStack(stack.getWrappedStack())))
                {
                    return energyValueRegistry.stackMappings.get(new WrappedStack(stack.getWrappedStack()));
                }
                else
                {
                    if (!strict)
                    {
                        if (stack.getWrappedStack() instanceof ItemStack)
                        {
                            EnergyValue lowestValue = null;
                            ItemStack wrappedItemStack = (ItemStack) stack.getWrappedStack();

                            if (OreDictionary.getOreIDs(wrappedItemStack).length > 0)
                            {
                                OreStack oreStack = new OreStack(wrappedItemStack);

                                if (energyValueRegistry.stackMappings.containsKey(new WrappedStack(oreStack)))
                                {
                                    return energyValueRegistry.stackMappings.get(new WrappedStack(oreStack));
                                }
                                else
                                {
                                    for (int oreId : OreDictionary.getOreIDs(wrappedItemStack))
                                    {
                                        for (ItemStack itemStack : OreDictionary.getOres(OreDictionary.getOreName(oreId)))
                                        {
                                            if (energyValueRegistry.stackMappings.containsKey(new WrappedStack(itemStack)))
                                            {
                                                if (lowestValue == null)
                                                {
                                                    lowestValue = energyValueRegistry.stackMappings.get(new WrappedStack(itemStack));
                                                }
                                                else
                                                {
                                                    EnergyValue itemValue = energyValueRegistry.stackMappings.get(new WrappedStack(itemStack));

                                                    if (itemValue.compareTo(lowestValue) < 0)
                                                    {
                                                        lowestValue = itemValue;
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    return lowestValue;
                                }
                            }
                            else
                            {
                                for (WrappedStack valuedStack : energyValueRegistry.stackMappings.keySet())
                                {
                                    if (valuedStack.getWrappedStack() instanceof ItemStack)
                                    {
                                        ItemStack valuedItemStack = (ItemStack) valuedStack.getWrappedStack();

                                        if (Item.getIdFromItem(valuedItemStack.getItem()) == Item.getIdFromItem(wrappedItemStack.getItem()))
                                        {
                                            if (valuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || wrappedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE)
                                            {
                                                EnergyValue stackValue = energyValueRegistry.stackMappings.get(valuedStack);

                                                if (stackValue.compareTo(lowestValue) < 0)
                                                {
                                                    lowestValue = stackValue;
                                                }
                                            }
                                            else if (wrappedItemStack.getItem().isDamageable() && wrappedItemStack.isItemDamaged())
                                            {
                                                EnergyValue stackValue = new EnergyValue(energyValueRegistry.stackMappings.get(valuedStack).getEnergyValue() * (1 - (wrappedItemStack.getItemDamage() * 1.0F / wrappedItemStack.getMaxDamage())));

                                                if (stackValue.compareTo(lowestValue) < 0)
                                                {
                                                    lowestValue = stackValue;
                                                }
                                            }
                                        }
                                    }
                                }

                                return lowestValue;
                            }
                        }
                        else if (stack.getWrappedStack() instanceof OreStack)
                        {
                            OreStack oreStack = (OreStack) stack.getWrappedStack();
                            for (ItemStack oreItemStack : OreDictionary.getOres(oreStack.oreName))
                            {
                                if (energyValueRegistry.stackMappings.containsKey(new WrappedStack(oreItemStack)))
                                {
                                    return energyValueRegistry.stackMappings.get(new WrappedStack(oreItemStack));
                                }
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    protected final void init()
    {
        HashMap<WrappedStack, EnergyValue> stackValueMap = new HashMap<WrappedStack, EnergyValue>();

        /*
         *  Pre-assigned values
         */
        stackValueMap.putAll(preAssignedMappings);

        /*
         *  Auto-assignment
         */
        // Initialize the maps for the first pass to happen
        ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        stackMappings = stackMappingsBuilder.build();
        Map<WrappedStack, EnergyValue> computedStackValues = computeStackMappings();

        // Initialize the pass counter
        int passNumber = 0;

        while ((computedStackValues.size() > 0) && (passNumber < 16))
        {
            // Increment the pass counter
            passNumber++;

            // Compute stack mappings from existing stack mappings
            computedStackValues = computeStackMappings();
            for (WrappedStack keyStack : computedStackValues.keySet())
            {
                EnergyValue factoredExchangeEnergyValue = null;
                WrappedStack factoredKeyStack = null;

                if (keyStack != null && keyStack.getWrappedStack() != null && keyStack.getStackSize() > 0)
                {
                    if (computedStackValues.get(keyStack) != null && Float.compare(computedStackValues.get(keyStack).getEnergyValue(), 0f) > 0)
                    {
                        factoredExchangeEnergyValue = EnergyValueHelper.factorEnergyValue(computedStackValues.get(keyStack), keyStack.getStackSize());
                        factoredKeyStack = new WrappedStack(keyStack, 1);
                    }
                }

                if (factoredExchangeEnergyValue != null)
                {
                    if (stackValueMap.containsKey(factoredKeyStack))
                    {
                        if (factoredExchangeEnergyValue.compareTo(stackValueMap.get(factoredKeyStack)) == -1)
                        {
                            stackValueMap.put(factoredKeyStack, factoredExchangeEnergyValue);
                        }
                    }
                    else
                    {
                        stackValueMap.put(factoredKeyStack, factoredExchangeEnergyValue);
                    }
                }
            }
        }



        /*
         *  Post-assigned values
         */
        if (postAssignedMappings != null)
        {
            for (WrappedStack wrappedStack : postAssignedMappings.keySet())
            {
                stackValueMap.put(wrappedStack, postAssignedMappings.get(wrappedStack));
            }
        }

        /**
         * Finalize the stack to value map
         */
        stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        stackMappings = stackMappingsBuilder.build();


        /**
         *  Value map resolution
         */
        SortedMap<EnergyValue, List<WrappedStack>> tempValueMappings = new TreeMap<EnergyValue, List<WrappedStack>>();

        for (WrappedStack stack : stackMappings.keySet())
        {
            if (stack != null)
            {
                EnergyValue value = stackMappings.get(stack);

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

    private Map<WrappedStack, EnergyValue> computeStackMappings()
    {
        Map<WrappedStack, EnergyValue> computedStackMap = new HashMap<WrappedStack, EnergyValue>();

        for (WrappedStack recipeOutput : RecipeRegistry.getInstance().getRecipeMappings().keySet())
        {
            if (!hasEnergyValue(recipeOutput.getWrappedStack(), false) && !computedStackMap.containsKey(recipeOutput))
            {
                EnergyValue lowestValue = null;

                for (List<WrappedStack> recipeInputs : RecipeRegistry.getInstance().getRecipeMappings().get(recipeOutput))
                {
                    EnergyValue computedValue = EnergyValueHelper.computeEnergyValueFromList(recipeInputs);
                    computedValue = EnergyValueHelper.factorEnergyValue(computedValue, recipeOutput.getStackSize());

                    if (computedValue != null)
                    {
                        if (computedValue.compareTo(lowestValue) < 0)
                        {
                            lowestValue = computedValue;
                        }
                    }
                }

                if ((lowestValue != null) && (lowestValue.getEnergyValue() > 0f))
                {
                    computedStackMap.put(new WrappedStack(recipeOutput.getWrappedStack()), lowestValue);
                }
            }
        }

        return computedStackMap;
    }

    public List getStacksInRange(int start, int finish)
    {
        return getStacksInRange(new EnergyValue(start), new EnergyValue(finish));
    }

    public List getStacksInRange(float start, float finish)
    {
        return getStacksInRange(new EnergyValue(start), new EnergyValue(finish));
    }

    public List getStacksInRange(EnergyValue start, EnergyValue finish)
    {
        List stacksInRange = new ArrayList<WrappedStack>();

        SortedMap<EnergyValue, List<WrappedStack>> tailMap = energyValueRegistry.valueMappings.tailMap(start);
        SortedMap<EnergyValue, List<WrappedStack>> headMap = energyValueRegistry.valueMappings.headMap(finish);

        SortedMap<EnergyValue, List<WrappedStack>> smallerMap;
        SortedMap<EnergyValue, List<WrappedStack>> biggerMap;

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

            for (EnergyValue value : smallerMap.keySet())
            {
                if (biggerMap.containsKey(value))
                {
                    for (WrappedStack wrappedStack : energyValueRegistry.valueMappings.get(value))
                    {
                        if (wrappedStack.getWrappedStack() instanceof ItemStack || wrappedStack.getWrappedStack() instanceof FluidStack)
                        {
                            stacksInRange.add(wrappedStack.getWrappedStack());
                        }
                        else if (wrappedStack.getWrappedStack() instanceof OreStack)
                        {
                            for (ItemStack itemStack : OreDictionary.getOres(((OreStack) wrappedStack.getWrappedStack()).oreName))
                            {
                                stacksInRange.add(itemStack);
                            }
                        }
                    }
                }
            }
        }

        return stacksInRange;
    }
}

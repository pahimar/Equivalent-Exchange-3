package com.pahimar.ee3.exchange;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.util.EnergyValueHelper;
import com.pahimar.ee3.util.LogHelper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EnergyCalculationSession
{
    private final IRegistryContext context;
    private final IEnergyCalculationDataProvider dataProvider;

    public EnergyCalculationSession(IRegistryContext context, IEnergyCalculationDataProvider dataProvider)
    {
        this.context = context;
        this.dataProvider = dataProvider;
    }

    public Result runDynamicEnergyValueResolution()
    {
        Map<WrappedStack, EnergyValue> stackValueMap = this.prepopulateEnergyValues(this.context);

        final int maxPassDepth = 16; // TODO Make this a config value.
        LogHelper.info("Beginning dynamic value calculation");
        CalculationPass pass = new CalculationPass(1, context);
        while (pass.isFirstPass() || pass.getPassNumber() < maxPassDepth)
        {
            Map<WrappedStack, EnergyValue> computedStackMap = this.runCalculationPass(pass, stackValueMap);
            for (WrappedStack keyStack : computedStackMap.keySet())
            {
                EnergyValue value = computedStackMap.get(keyStack);
                EnergyValueStackMapping normalizedValue = this.normalize(keyStack, value, stackValueMap);
                if(normalizedValue != null) // TODO Check over override
                {
                    LogHelper.trace(String.format("")); // TODO Log message
                    stackValueMap.put(normalizedValue.wrappedStack, normalizedValue.energyValue);
                }
            }

            // If not new value were added there is no sense in more passes.
            if(!pass.calculatedNewValues())
                break;

            pass = pass.next();
        }

        this.appendPostCalculationValues(stackValueMap, context);
        return buildResult(stackValueMap);
    }

    private Map<WrappedStack, EnergyValue> prepopulateEnergyValues(IRegistryContext context)
    {
        TreeMap<WrappedStack, EnergyValue> stackValueMap = new TreeMap<WrappedStack, EnergyValue>();

        // Add in all mod specified pre-calculation values
        Map<WrappedStack, EnergyValue> preCalculationMappings = this.dataProvider.getPreCalculationMappings();
        if(preCalculationMappings != null)
            stackValueMap.putAll(preCalculationMappings); // TODO Logging

        // Add in all pre-calculation values
        for (IEnergyValuesSource source : this.dataProvider.getPreCalculationSources())
        {
            LogHelper.trace(String.format("BEGIN Adding EnergyValue mappings from %s.", source.toString()));
            Map<WrappedStack, EnergyValue> sourceValueMap = source.getValues(context);
            for (WrappedStack wrappedStack : sourceValueMap.keySet())
            {
                if (sourceValueMap.get(wrappedStack) != null)
                {
                    stackValueMap.put(wrappedStack, sourceValueMap.get(wrappedStack));
                    LogHelper.trace(String.format("Adding EnergyValue %s for %s", sourceValueMap.get(wrappedStack), wrappedStack));
                }
            }
            LogHelper.trace(String.format("END Adding EnergyValue mappings from %s.", source.toString()));
        }

        return stackValueMap;
    }

    private void appendPostCalculationValues(Map<WrappedStack, EnergyValue> stackValueMap, IRegistryContext context)
    {
        // Add in all mod specified post-calculation values
        Map<WrappedStack, EnergyValue> postCalculationMappings = this.dataProvider.getPostCalculationMappings();
        if(postCalculationMappings != null)
            stackValueMap.putAll(postCalculationMappings); // TODO Logging

        // Add in all post-calculation values
        for (IEnergyValuesSource source : this.dataProvider.getPostCalculationSources())
        {
            LogHelper.trace(String.format("BEGIN Adding EnergyValue mappings from %s.", source.toString()));
            Map<WrappedStack, EnergyValue> sourceValueMap = source.getValues(context);
            for (WrappedStack wrappedStack : sourceValueMap.keySet())
            {
                if (sourceValueMap.get(wrappedStack) != null)
                {
                    stackValueMap.put(wrappedStack, sourceValueMap.get(wrappedStack));
                    LogHelper.trace(String.format("Adding EnergyValue %s for %s", sourceValueMap.get(wrappedStack), wrappedStack));
                }
            }
            LogHelper.trace(String.format("END Adding EnergyValue mappings from %s.", source.toString()));
        }
    }

    private Map<WrappedStack, EnergyValue> runCalculationPass(CalculationPass pass, Map<WrappedStack, EnergyValue> entryValues)
    {
        return pass.run(entryValues);
    }

    private EnergyValueStackMapping normalize(WrappedStack stack, EnergyValue value,
                                              Map<WrappedStack, EnergyValue> entryValues)
    {
        if(stack == null || value == null || stack.isEmpty() || value.getValue() <= 0)
            return null;

        WrappedStack factoredKeyStack = WrappedStack.wrap(stack, 1);
        EnergyValue factoredEnergyValue = EnergyValueHelper.factorEnergyValue(value, stack.getStackSize());

        if (factoredEnergyValue != null && !entryValues.containsKey(factoredKeyStack))
            return new EnergyValueStackMapping(factoredKeyStack, factoredEnergyValue);

        return null;
    }

    private static Result buildResult(Map<WrappedStack, EnergyValue> stackValueMap)
    {
        ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        return new Result(stackMappingsBuilder.build());
    }

    public static class Result
    {
        private final ImmutableSortedMap<WrappedStack, EnergyValue> stackValueMap;

        public Result(ImmutableSortedMap<WrappedStack, EnergyValue> stackValueMap)
        {
            this.stackValueMap = stackValueMap;
        }

        public ImmutableSortedMap<WrappedStack, EnergyValue> getStackValueMap()
        {
            return stackValueMap;
        }
    }

    private class CalculationPass
    {
        private final int passNumber;
        private final IRegistryContext context;
        private Map<WrappedStack, EnergyValue> computedValues;

        private CalculationPass(int passNumber, IRegistryContext context)
        {
            this.passNumber = passNumber;
            this.context = context;
        }

        public Map<WrappedStack, EnergyValue> run(Map<WrappedStack, EnergyValue> entryValues)
        {
            if(this.computedValues == null)
                this.computedValues = this.processRecipes(entryValues);

            return this.computedValues;
        }

        private Map<WrappedStack, EnergyValue> processRecipes(Map<WrappedStack, EnergyValue> entryValues)
        {
            Map<WrappedStack, EnergyValue> computedStackMap = new TreeMap<WrappedStack, EnergyValue>();
            Multimap<WrappedStack, List<WrappedStack>> recipeMappings = RecipeRegistry.getInstance().getRecipeMappings();

            for (WrappedStack recipeOutput : recipeMappings.keySet())
            {
                // TODO Review: possible fault in the logic here that is preventing some values from being assigned?
                Object recipeOutputObj = recipeOutput.getWrappedObject();
                if (!this.context.hasEnergyValue(recipeOutputObj, false) && !computedStackMap.containsKey(recipeOutput))
                {
                    Collection<List<WrappedStack>> inputs = recipeMappings.get(recipeOutput);
                    EnergyValue lowestValue = calculateLowestValue(entryValues, recipeOutput, inputs);

                    // Only add positive values.
                    if ((lowestValue != null) && (lowestValue.getValue() > 0f))
                        computedStackMap.put(WrappedStack.wrap(recipeOutput.getWrappedObject()), lowestValue);

                    // TODO Report not computed values.
                }
            }

            return computedStackMap;
        }

        private EnergyValue calculateLowestValue(Map<WrappedStack, EnergyValue> entryValues,
                 WrappedStack recipeOutput, Collection<List<WrappedStack>> inputs)
        {
            EnergyValue lowestValue = null;
            for (List<WrappedStack> recipeInputs : inputs)
            {
                EnergyValue computedValue = EnergyValueHelper.computeEnergyValueFromRecipe(entryValues, recipeOutput, recipeInputs);

                if (computedValue != null)
                    if (computedValue.compareTo(lowestValue) < 0)
                        lowestValue = computedValue;
                else // If one recipe could not be calculated we don't provide a value to prevent exploits.
                    break;
            }

            return lowestValue;
        }

        public int getPassNumber()
        {
            return passNumber;
        }

        public boolean calculatedNewValues()
        {
            if(this.computedValues == null)
                return false;

            return this.computedValues.size() > 0;
        }

        public boolean isFirstPass()
        {
            return this.passNumber == 1;
        }

        public CalculationPass next()
        {
            return new CalculationPass(this.passNumber + 1, this.context);
        }
    }
}

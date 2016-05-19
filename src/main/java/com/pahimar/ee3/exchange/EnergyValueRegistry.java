package com.pahimar.ee3.exchange;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.pahimar.ee3.api.event.EnergyValueEvent;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.IEnergyValueProvider;
import com.pahimar.ee3.handler.ConfigurationHandler;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.util.LoaderHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy.Phase;

public class EnergyValueRegistry {

    public static final EnergyValueRegistry INSTANCE = new EnergyValueRegistry();

    private ImmutableSortedMap<WrappedStack, EnergyValue> stackValueMap;
    private ImmutableSortedMap<EnergyValue, List<WrappedStack>> valueStackMap;

    private final Map<WrappedStack, EnergyValue> preCalculationStackValueMap;
    private final Map<WrappedStack, EnergyValue> postCalculationStackValueMap;
    private transient SortedSet<WrappedStack> uncomputedStacks;
    private transient boolean loadedFromMap;

    public static File energyValuesDirectory;
    public static File energyValuesFile;
    public static File preCalculationValuesFile;
    public static File postCalculationValuesFile;

    public static final Marker ENERGY_VALUE_MARKER = MarkerManager.getMarker("EE3_ENERGY_VALUE", LogHelper.MOD_MARKER);

    private EnergyValueRegistry() {

        ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMapBuilder = ImmutableSortedMap.naturalOrder();
        stackValueMap = stackMapBuilder.build();

        preCalculationStackValueMap = new TreeMap<>();
        postCalculationStackValueMap = new TreeMap<>();
        uncomputedStacks = new TreeSet<>();
        loadedFromMap = false;
    }

    /**
     * Returns an {@link EnergyValue} for a {@link Object} in the provided {@link Map>} of {@link WrappedStack}s mapped
     * to EnergyValues
     *
     * <p>The order of checking is as follows;</p>
     * <ol>
     *     <li>{@link ItemStack}s whose {@link Item}s implement {@link IEnergyValueProvider}</li>
     *     <li>Direct EnergyValue mapping of the provided Object in the provided Map</li>
     *     <li>The following criteria are only checked (in order) in the event that this is a non-strict query;
     *         <ol>
     *             <li>
     *                 ItemStacks that are part of an {@link OreDictionary} entry are checked to see if
     *                 <strong>all</strong> Ores they are registered to have the same non-null EnergyValue assigned to
     *                 it
     *                     <ul>
     *                         <li>
     *                             e.g., ItemStack X is associated with OreDictionary entries A, B and C. An EnergyValue
     *                             would be returned for X only if A, B and C all had the same non-null EnergyValue
     *                         </li>
     *                     </ul>
     *             </li>
     *             <li>
     *                 ItemStacks are checked to see if there exist {@link OreDictionary#WILDCARD_VALUE} equivalents
     *             </li>
     *             <li>
     *                 {@link OreStack}s are checked to see if all members of the OreDictionary entry represented by the
     *                 OreStack have the same non-null EnergyValue (similar to the case for ItemStacks above)
     *             </li>
     *         </ol>
     *     </li>
     * </ol>
     *
     * @param valueMap a {@link Map} of {@link EnergyValue}'s mapped to {@link WrappedStack}'s
     * @param object the {@link Object} that is being checked for a corresponding {@link EnergyValue}
     * @param strict whether this is a strict (e.g., only looking for direct value assignment vs associative value
     *               assignments) query or not
     * @return an {@link EnergyValue} if there is one to be found for the provided {@link Object} in the provided Map, null otherwise
     */
    private static EnergyValue getEnergyValue(Map<WrappedStack, EnergyValue> valueMap, Object object, boolean strict) {

        if (WrappedStack.canBeWrapped(object)) {

            WrappedStack wrappedStack = WrappedStack.wrap(object, 1);
            Object wrappedObject = wrappedStack.getWrappedObject();

            if (wrappedObject instanceof ItemStack && ((ItemStack) wrappedObject).getItem() instanceof IEnergyValueProvider && !strict) {

                EnergyValue energyValue = ((IEnergyValueProvider) ((ItemStack) wrappedObject).getItem()).getEnergyValue(((ItemStack) wrappedObject));

                if (energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0) {
                    return energyValue;
                }
            }

            if (valueMap != null && !valueMap.isEmpty()) {

                // First check for a direct energy value mapping to the wrapped object
                if (valueMap.containsKey(wrappedStack)) {
                    return valueMap.get(wrappedStack);
                }
                else if (!strict) {

                    if (wrappedObject instanceof ItemStack) {

                        ItemStack unValuedItemStack = ItemStack.copyItemStack((ItemStack) wrappedObject);
                        EnergyValue minEnergyValue = null;

                        int[] oreIds = OreDictionary.getOreIDs(unValuedItemStack);
                        if (oreIds.length > 0) {

                            EnergyValue energyValue = null;
                            boolean allHaveSameValue = true;

                            for (int oreId : oreIds) {
                                String oreName = OreDictionary.getOreName(oreId);

                                if (!"Unknown".equalsIgnoreCase(oreName)) {

                                    WrappedStack oreStack = WrappedStack.wrap(new OreStack(oreName));

                                    if (oreStack != null && valueMap.containsKey(oreStack)) {

                                        if (energyValue == null) {
                                            energyValue = valueMap.get(oreStack);
                                        }
                                        else if (!energyValue.equals(valueMap.get(oreStack))) {
                                            allHaveSameValue = false;
                                        }
                                    }
                                    else {
                                        allHaveSameValue = false;
                                    }
                                }
                                else {
                                    allHaveSameValue = false;
                                }
                            }

                            if (allHaveSameValue) {
                                return energyValue;
                            }
                        }
                        else {
                            for (WrappedStack valuedWrappedStack : valueMap.keySet()) {
                                if (valuedWrappedStack.getWrappedObject() instanceof ItemStack) {
                                    if (Item.getIdFromItem(((ItemStack) valuedWrappedStack.getWrappedObject()).getItem()) == Item.getIdFromItem(unValuedItemStack.getItem())) {

                                        ItemStack valuedItemStack = (ItemStack) valuedWrappedStack.getWrappedObject();
                                        if (valuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || unValuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) {

                                            EnergyValue energyValue = valueMap.get(valuedWrappedStack);

                                            if (energyValue.compareTo(minEnergyValue) < 0) {
                                                minEnergyValue = energyValue;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if (wrappedObject instanceof OreStack) {

                        OreStack oreStack = (OreStack) wrappedObject;
                        List<ItemStack> itemStacks = OreDictionary.getOres(oreStack.oreName);

                        if (!itemStacks.isEmpty()) {

                            EnergyValue energyValue = null;
                            boolean allHaveSameValue = true;

                            for (ItemStack itemStack : itemStacks) {
                                WrappedStack wrappedItemStack = WrappedStack.wrap(itemStack, 1);

                                if (wrappedItemStack != null && valueMap.containsKey(wrappedItemStack)) {
                                    if (energyValue == null) {
                                        energyValue = valueMap.get(wrappedItemStack);
                                    }
                                    else if (!energyValue.equals(valueMap.get(wrappedItemStack))) {
                                        allHaveSameValue = false;
                                    }
                                }
                                else {
                                    allHaveSameValue = false;
                                }
                            }

                            if (allHaveSameValue) {
                                return energyValue;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Calculates an {@link EnergyValue} for the provided {@link WrappedStack} output from the provided {@link List} of
     * WrappedStack inputs and {@link Map} of energy value mappings to objects. We calculate the energy value for the
     * output by, for each input, summing the input's energy value * the input's stack size. That sum is then divided
     * by the stack size of the output. If <strong>any</strong> of the inputs do not have an energy value then no
     * energy value can be calculated for the output - therefore we return null
     *
     * @param valueMap a {@link Map} of {@link EnergyValue}'s mapped to {@link WrappedStack}'s
     * @param wrappedOutput the {@link WrappedStack} output for that the inputs "create"
     * @param wrappedInputs a {@link List} of {@link WrappedStack}s that "create" the output
     * @return an {@link EnergyValue} if there is one that can be calculated, null otherwise
     */
    private static EnergyValue computeFromInputs(Map<WrappedStack, EnergyValue> valueMap, WrappedStack wrappedOutput, List<WrappedStack> wrappedInputs) {

        float sumOfValues = 0f;

        for (WrappedStack wrappedInput : wrappedInputs) {

            EnergyValue inputValue;
            int stackSize = Integer.MIN_VALUE;

            if (wrappedInput.getWrappedObject() instanceof ItemStack) {

                ItemStack inputItemStack = (ItemStack) wrappedInput.getWrappedObject();

                // Check if we are dealing with a potential fluid
                if (FluidContainerRegistry.getFluidForFilledItem(inputItemStack) != null) {

                    if (inputItemStack.getItem().getContainerItem(inputItemStack) != null) {
                        stackSize = FluidContainerRegistry.getFluidForFilledItem(inputItemStack).amount * wrappedInput.getStackSize();
                        inputValue = getEnergyValue(valueMap, FluidContainerRegistry.getFluidForFilledItem(inputItemStack), false);
                    }
                    else {
                        inputValue = getEnergyValue(valueMap, wrappedInput, false);
                    }
                }
                else if (inputItemStack.getItem().getContainerItem(inputItemStack) != null) {

                    ItemStack inputContainerItemStack = inputItemStack.getItem().getContainerItem(inputItemStack);

                    if (getEnergyValue(valueMap, inputItemStack, false) != null && getEnergyValue(valueMap, inputContainerItemStack, false) != null) {
                        float itemStackValue = getEnergyValue(valueMap, inputItemStack, false).getValue();
                        float containerStackValue = getEnergyValue(valueMap, inputContainerItemStack, false).getValue();
                        inputValue = new EnergyValue(itemStackValue - containerStackValue);
                    }
                    else {
                        inputValue = new EnergyValue(0);
                    }
                }
                else if (!inputItemStack.getItem().doesContainerItemLeaveCraftingGrid(inputItemStack)) {
                    inputValue = new EnergyValue(0);
                }
                else if (OreDictionary.getOreIDs(inputItemStack).length > 0) {
                    inputValue = getEnergyValue(valueMap, wrappedInput, true);
                }
                else {
                    inputValue = getEnergyValue(valueMap, wrappedInput, false);
                }
            }
            else if (wrappedInput.getWrappedObject() instanceof OreStack) {

                OreStack inputOreStack = (OreStack) wrappedInput.getWrappedObject();
                inputValue = getEnergyValue(valueMap, wrappedInput, false);
                for (ItemStack itemStack : OreDictionary.getOres(inputOreStack.oreName)) {
                    if (!itemStack.getItem().doesContainerItemLeaveCraftingGrid(itemStack)) {
                        inputValue = new EnergyValue(0);
                    }
                }
            }
            else {
                inputValue = getEnergyValue(valueMap, wrappedInput, false);
            }

            if (inputValue != null) {

                if (stackSize == Integer.MIN_VALUE) {
                    stackSize = wrappedInput.getStackSize();
                }

                sumOfValues += inputValue.getValue() * stackSize;
            }
            else {
                return null;
            }
        }

        return EnergyValue.factor(new EnergyValue(sumOfValues), wrappedOutput.getStackSize());
    }

    /**
     * Returns an {@link ImmutableMap} containing the current energy value mappings
     *
     * @return an {@link ImmutableMap} containing the current energy value mappings
     */
    public ImmutableMap<WrappedStack, EnergyValue> getEnergyValues() {
        return stackValueMap;
    }

    /**
     * Returns a {@link Map} containing the pre-calculation energy value mappings
     *
     * @return a {link Map} containing the pre-calculation energy value mappings
     */
    public Map<WrappedStack, EnergyValue> getPreCalculationStackValueMap() {
        return preCalculationStackValueMap;
    }

    /**
     * Returns a {@link Map} containing the post-calculation energy value mappings
     *
     * @return a {@link Map} containing the post-calculation energy value mappings
     */
    public Map<WrappedStack, EnergyValue> getPostCalculationStackValueMap() {
        return postCalculationStackValueMap;
    }

    /**
     * Checks if there exists an {@link EnergyValue} associated with the provided {@link Object}.
     *
     * @param object the {@link Object} that is being checked for a corresponding {@link EnergyValue}
     * @return true if the provided object has an energy value, false otherwise
     */
    public boolean hasEnergyValue(Object object) {
        return hasEnergyValue(object, false);
    }

    /**
     * Checks if there exists an {@link EnergyValue} associated with the provided {@link Object}
     *
     * @param object the {@link Object} that is being checked for a corresponding {@link EnergyValue}
     * @param strict whether this is a strict (e.g., only looking for direct value assignment vs associative value
     *               assignments) query or not
     * @return true if the provided object has an energy value, false otherwise
     */
    public boolean hasEnergyValue(Object object, boolean strict) {
        return getEnergyValue(object, strict) != null;
    }

    /**
     * Returns an {@link EnergyValue} associated with the provided {@link Object} (if there is one)
     *
     * @param object the {@link Object} that is being checked for a corresponding {@link EnergyValue}
     * @return an {@link EnergyValue} if there is one to be found, null otherwise
     */
    public EnergyValue getEnergyValue(Object object) {
        return getEnergyValue(object, false);
    }

    /**
     * Returns an {@link EnergyValue} associated with the provided {@link Object} (if there is one)
     *
     * @param object the {@link Object} that is being checked for a corresponding {@link EnergyValue}
     * @param strict whether this is a strict (e.g., only looking for direct value assignment vs associative value
     *               assignments) query or not
     * @return an {@link EnergyValue} if there is one to be found, null otherwise
     */
    public EnergyValue getEnergyValue(Object object, boolean strict) {
        return getEnergyValue(stackValueMap, object, strict);
    }

    /**
     * Returns an {@link EnergyValue} associated with the provided {@link Object} (if there is one)
     *
     * @param object the {@link Object} that is being checked for a corresponding {@link EnergyValue}
     * @param strict whether this is a strict (e.g., only looking for direct value assignment vs associative value
     *               assignments) query or not
     * @return an {@link EnergyValue} if there is one to be found, null otherwise
     */
    public EnergyValue getEnergyValueForStack(Object object, boolean strict) {

        WrappedStack wrappedObject = WrappedStack.wrap(object);

        if (wrappedObject != null && getEnergyValue(object, strict) != null) {
            return new EnergyValue(getEnergyValue(object, strict).getValue() * wrappedObject.getStackSize());
        }

        return null;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param start
     * @param finish
     * @return
     */
    public List getStacksInRange(Number start, Number finish) {
        return getStacksInRange(new EnergyValue(start), new EnergyValue(finish));
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param start
     * @param finish
     * @return
     */
    public List getStacksInRange(EnergyValue start, EnergyValue finish) {

        List stacksInRange = new ArrayList<WrappedStack>();

        if (valueStackMap != null) {

            SortedMap<EnergyValue, List<WrappedStack>> tailMap = valueStackMap.tailMap(start);
            SortedMap<EnergyValue, List<WrappedStack>> headMap = valueStackMap.headMap(finish);

            SortedMap<EnergyValue, List<WrappedStack>> smallerMap;
            SortedMap<EnergyValue, List<WrappedStack>> biggerMap;

            if (!tailMap.isEmpty() && !headMap.isEmpty()) {

                if (tailMap.size() <= headMap.size()) {
                    smallerMap = tailMap;
                    biggerMap = headMap;
                }
                else {
                    smallerMap = headMap;
                    biggerMap = tailMap;
                }

                for (EnergyValue value : smallerMap.keySet()) {
                    if (biggerMap.containsKey(value)) {
                        for (WrappedStack wrappedStack : valueStackMap.get(value)) {
                            if (wrappedStack.getWrappedObject() instanceof ItemStack || wrappedStack.getWrappedObject() instanceof FluidStack) {
                                stacksInRange.add(wrappedStack.getWrappedObject());
                            }
                            else if (wrappedStack.getWrappedObject() instanceof OreStack) {
                                stacksInRange.addAll(OreDictionary.getOres(((OreStack) wrappedStack.getWrappedObject()).oreName));
                            }
                        }
                    }
                }
            }
        }

        return stacksInRange;
    }

    /**
     * Sets an {@link EnergyValue} for the provided {@link Object} (if it can be wrapped in a {@link WrappedStack}.
     * Depending on whether or not this is a pre-calculation value assignment it's also possible for the calculated
     * energy value map to be recomputed to take into account the new mapping.
     *
     * @param object the object the energy value is being assigned for
     * @param energyValue the energy value being setEnergyValue on the object
     * @param phase the {@link Phase} of energy value assignment to set this value for
     */
    public void setEnergyValue(Object object, EnergyValue energyValue, Phase phase) {
        setEnergyValue(object, energyValue, phase, false);
    }

    /**
     * Sets an {@link EnergyValue} for the provided {@link Object} (if it can be wrapped in a {@link WrappedStack}.
     * Depending on whether or not this is a pre-calculation value assignment it's also possible for the calculated
     * energy value map to be recomputed to take into account the new mapping.
     *
     * @param object the object the energy value is being assigned for
     * @param energyValue the energy value being setEnergyValue on the object
     * @param phase the {@link Phase} of energy value assignment to set this value for
     * @param doRegenValues whether or not the energy value map needs recomputing. Only an option if the energy value
     *                      is being assigned in the <code>PRE_CALCULATION</code> phase
     */
    public void setEnergyValue(Object object, EnergyValue energyValue, Phase phase, boolean doRegenValues) {

        if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0) {

            WrappedStack wrappedStack = WrappedStack.wrap(object, 1);
            EnergyValue factoredEnergyValue = EnergyValue.factor(energyValue, wrappedStack.getStackSize());

            if (phase == Phase.PRE_CALCULATION) {
                if (!FMLCommonHandler.instance().bus().post(new EnergyValueEvent.SetEnergyValueEvent(wrappedStack, factoredEnergyValue, Phase.PRE_CALCULATION))) {

                    preCalculationStackValueMap.put(wrappedStack, factoredEnergyValue);

                    if (doRegenValues) {
                        compute();
                    }
                }
            }
            else if (!FMLCommonHandler.instance().bus().post(new EnergyValueEvent.SetEnergyValueEvent(wrappedStack, factoredEnergyValue, Phase.POST_CALCULATION))) {

                TreeMap<WrappedStack, EnergyValue> valueMap = new TreeMap<>(stackValueMap);
                valueMap.put(wrappedStack, energyValue);
                ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
                stackValueMap = stackMappingsBuilder.putAll(valueMap).build();

                postCalculationStackValueMap.put(wrappedStack, factoredEnergyValue);
            }

            if (ConfigurationHandler.Settings.energyValueDebugLoggingEnabled) {
                LogHelper.info(ENERGY_VALUE_MARKER, "[{}] Mod '{}' set a {} value of {} on object '{}' with doRegen = {}", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), phase, energyValue, wrappedStack, doRegenValues);
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * This is where the magic happens
     */
    public void compute() {

        // Initialize the "working copy" energy value map
        final Map<WrappedStack, EnergyValue> stackValueMap = new TreeMap<>();
        uncomputedStacks = new TreeSet<>();

        // Add in all pre-calculation energy value mappings
        preCalculationStackValueMap.keySet().stream()
                .filter(wrappedStack -> wrappedStack != null && wrappedStack.getWrappedObject() != null && preCalculationStackValueMap.get(wrappedStack) != null)
                .forEach(wrappedStack -> stackValueMap.put(wrappedStack, preCalculationStackValueMap.get(wrappedStack)));

        // Calculate values from the known methods to create items, and the pre-calculation value mappings
        stackValueMap.putAll(calculateStackValueMap(stackValueMap));

        // Add in all post-calculation energy value mappings
        postCalculationStackValueMap.keySet().stream()
                .filter(wrappedStack -> wrappedStack != null && wrappedStack.getWrappedObject() != null && postCalculationStackValueMap.get(wrappedStack) != null)
                .forEach(wrappedStack -> stackValueMap.put(wrappedStack, postCalculationStackValueMap.get(wrappedStack)));

        // Bake the final calculated energy value maps
        ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        this.stackValueMap = stackMappingsBuilder.build();
        calculateValueStackMap();

        // Save the results to disk
        save();
    }

    /**
     *
     * @param stackValueMap
     * @return
     */
    private Map<WrappedStack, EnergyValue> calculateStackValueMap(Map<WrappedStack, EnergyValue> stackValueMap) {

        LogHelper.info(ENERGY_VALUE_MARKER, "Beginning energy value calculation");
        long startingTime = System.nanoTime();

        Map<WrappedStack, EnergyValue> computedMap = new TreeMap<>(stackValueMap);
        Map<WrappedStack, EnergyValue> tempComputedMap = new TreeMap<>();
        int passNumber = 0;

        while ((passNumber == 0 || tempComputedMap.size() != computedMap.size()) && passNumber < 16) {

            long passStartTime = System.nanoTime();
            passNumber++;
            computedMap.putAll(tempComputedMap);

            tempComputedMap = new TreeMap<>(computedMap);
            for (WrappedStack recipeOutput : RecipeRegistry.getInstance().getRecipeMappings().keySet()) {

                // We won't attempt to recalculate values that already have a pre-calculation value assignment
                if (!stackValueMap.containsKey(WrappedStack.wrap(recipeOutput, 1))) {
                    for (List<WrappedStack> recipeInputs : RecipeRegistry.getInstance().getRecipeMappings().get(recipeOutput)) {

                        EnergyValue currentOutputValue = getEnergyValue(tempComputedMap, WrappedStack.wrap(recipeOutput, 1), false);
                        EnergyValue computedOutputValue = computeFromInputs(tempComputedMap, recipeOutput, recipeInputs);

                        if (computedOutputValue != null && computedOutputValue.compareTo(currentOutputValue) < 0) {

                            uncomputedStacks.removeIf(wrappedStack -> uncomputedStacks.contains(WrappedStack.wrap(recipeOutput, 1)));

                            if (ConfigurationHandler.Settings.energyValueDebugLoggingEnabled) {
                                LogHelper.info(ENERGY_VALUE_MARKER, "Pass {}: Calculated value {} for object {}", passNumber, computedOutputValue, recipeOutput);
                            }

                            tempComputedMap.put(WrappedStack.wrap(recipeOutput), computedOutputValue);
                        }
                        else if (computedOutputValue != null) {
                            uncomputedStacks.add(WrappedStack.wrap(recipeOutput, 1));
                        }
                    }
                }
            }

            long passDuration = System.nanoTime() - passStartTime;
            if (ConfigurationHandler.Settings.energyValueDebugLoggingEnabled) {
                LogHelper.info(ENERGY_VALUE_MARKER, "Pass {}: Calculated {} values for objects in {} ms", passNumber, tempComputedMap.size(), passDuration / 100000);
            }
        }
        long endingTime = System.nanoTime() - startingTime;
        LogHelper.info(ENERGY_VALUE_MARKER, "Finished energy value calculation - calculated {} values for objects in {} ms", computedMap.size() - stackValueMap.size(), endingTime / 100000);

        return computedMap;
    }

    private void calculateValueStackMap() {

        SortedMap<EnergyValue, List<WrappedStack>> tempValueMap = new TreeMap<>();

        for (WrappedStack wrappedStack : getEnergyValues().keySet()) {

            if (wrappedStack != null) {

                EnergyValue energyValue = getEnergyValues().get(wrappedStack);

                if (energyValue != null) {
                    if (tempValueMap.containsKey(energyValue)) {
                        if (!(tempValueMap.get(energyValue).contains(wrappedStack))) {
                            tempValueMap.get(energyValue).add(wrappedStack);
                        }
                    }
                    else {
                        tempValueMap.put(energyValue, new ArrayList<>(Arrays.asList(wrappedStack)));
                    }
                }
            }
        }
        valueStackMap = ImmutableSortedMap.copyOf(tempValueMap);
    }

    /**
     * Saves the pre-calculation, post-calculation, and calculated energy value maps to disk
     */
    public void save() {

        /**
         * If the current values were synched to us from a server, do not save them to disk as they would override
         * the local ones
         */
        if (!loadedFromMap) {
            SerializationHelper.writeMapToFile(stackValueMap, energyValuesFile);
        }
        SerializationHelper.writeMapToFile(preCalculationStackValueMap, preCalculationValuesFile);
        SerializationHelper.writeMapToFile(postCalculationStackValueMap, postCalculationValuesFile);
    }

    /**
     * Loads the pre-calculation, post-calculation, and calculated energy value maps from disk. In the event that either
     * the pre/post calculation maps can not be loaded from disk they will be initialized as empty maps. If the
     * calculated energy value map can not be loaded from disk then the values will be computed from the pre/post
     * calculation maps
     */
    public void load() {

        try {
            preCalculationStackValueMap.putAll(SerializationHelper.readMapFromFile(preCalculationValuesFile));
        } catch (FileNotFoundException e) {
            // TODO Log that no pre-calculation values were loaded from file because file wasn't found
        }

        try {
            postCalculationStackValueMap.putAll(SerializationHelper.readMapFromFile(postCalculationValuesFile));
        } catch (FileNotFoundException e) {
            // TODO Log that no post-calculation values were loaded from file because file wasn't found
        }

        try {
            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMapBuilder = ImmutableSortedMap.naturalOrder();
            stackMapBuilder.putAll(SerializationHelper.readMapFromFile(energyValuesFile));
            stackValueMap = stackMapBuilder.build();
            calculateValueStackMap();
        } catch (FileNotFoundException e) {
            LogHelper.warn("No calculated energy value file found, regenerating"); // TODO Better log message
            compute();
        }
    }

    /**
     *
     *
     * @param valueMap
     */
    public void load(Map<WrappedStack, EnergyValue> valueMap){

        if (stackValueMap != null) {

            loadedFromMap = true;
            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(valueMap);
            stackValueMap = stackMappingsBuilder.build();
            calculateValueStackMap();
        }
    }
}

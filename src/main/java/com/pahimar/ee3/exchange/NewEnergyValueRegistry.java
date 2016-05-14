package com.pahimar.ee3.exchange;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.util.EnergyValueHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class NewEnergyValueRegistry {

    public static final NewEnergyValueRegistry INSTANCE = new NewEnergyValueRegistry();

    private ImmutableSortedMap<WrappedStack, EnergyValue> energyValueMap;

    // TODO Should we have API values and files loaded from file as separate maps (runtime vs file)
    private final Map<WrappedStack, EnergyValue> preCalculationValueMap;
    private final Map<WrappedStack, EnergyValue> postCalculationValueMap;
    private transient SortedSet<WrappedStack> uncomputedStacks;

    public static File energyValuesDirectory;
    public static File energyValuesFile;
    public static File preCalculationValuesFile;
    public static File postCalculationValuesFile;

    public static final Marker ENERGY_VALUE_MARKER = MarkerManager.getMarker("EE3_ENERGY_VALUE", LogHelper.MOD_MARKER);

    private NewEnergyValueRegistry() {

        ImmutableSortedMap.Builder<WrappedStack, EnergyValue> energyValueMapBuilder = ImmutableSortedMap.naturalOrder();
        energyValueMap = energyValueMapBuilder.build();

        preCalculationValueMap = new TreeMap<>();
        postCalculationValueMap = new TreeMap<>();

        // Loading up some dummy values for testing serialization
        preCalculationValueMap.put(WrappedStack.wrap(Items.apple), new EnergyValue(1));
        preCalculationValueMap.put(WrappedStack.wrap(Items.arrow), new EnergyValue(2));
        preCalculationValueMap.put(WrappedStack.wrap(Items.baked_potato), new EnergyValue(3));
        preCalculationValueMap.put(WrappedStack.wrap(Items.bed), new EnergyValue(4));
        preCalculationValueMap.put(WrappedStack.wrap(new OreStack("oreIron")), new EnergyValue(5));
        preCalculationValueMap.put(WrappedStack.wrap(new FluidStack(FluidRegistry.WATER, 500)), new EnergyValue(6));
        preCalculationValueMap.put(WrappedStack.wrap(new ItemStack(Items.carrot, 1, 1)), new EnergyValue(7));
        preCalculationValueMap.put(WrappedStack.wrap(new ItemStack(Items.chainmail_boots, 1, 2)), new EnergyValue(8));
    }

    /**
     * Returns an {@link ImmutableMap} containing the current energy value mappings
     *
     * @return an {@link ImmutableMap} containing the current energy value mappings
     */
    public ImmutableMap<WrappedStack, EnergyValue> getEnergyValues() {
        return energyValueMap;
    }

    /**
     * Returns a {@link Map} containing the pre-calculation energy value mappings
     *
     * @return a {link Map} containing the pre-calculation energy value mappings
     */
    public Map<WrappedStack, EnergyValue> getPreCalculationValueMap() {
        return preCalculationValueMap;
    }

    /**
     * Returns a {@link Map} containing the post-calculation energy value mappings
     *
     * @return a {link Map} containing the post-calculation energy value mappings
     */
    public Map<WrappedStack, EnergyValue> getPostCalculationValueMap() {
        return postCalculationValueMap;
    }

    /**
     * Sets an {@link EnergyValue} for the provided {@link Object} (if it can be wrapped in a {@link WrappedStack}.
     * Depending on whether or not this is a pre-calculation value assignment it's also possible for the calculated
     * energy value map to be recomputed to take into account the new mapping.
     *
     * @param object the object the energy value is being assigned for
     * @param energyValue the energy value being set on the object
     * @param isPreCalculationAssignment whether or not the calculated energy value assignment is a pre-calculation
     *                                   value assignment or not
     * @param doRegenValues whether or not the energy value map needs recomputing. Only an option if
     *                      <code>isPreCalculationAssignment</code> is true
     */
    public void set(Object object, EnergyValue energyValue, boolean isPreCalculationAssignment, boolean doRegenValues) {

        if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0) {

            WrappedStack wrappedStack = WrappedStack.wrap(object, 1);
            EnergyValue factoredEnergyValue = EnergyValueHelper.factor(energyValue, wrappedStack.getStackSize());

            if (isPreCalculationAssignment) {
                preCalculationValueMap.put(wrappedStack, factoredEnergyValue);

                if (doRegenValues) {
                    // TODO Regen values
                }
            }
            else {

                TreeMap<WrappedStack, EnergyValue> valueMap = new TreeMap<>(energyValueMap);
                valueMap.put(wrappedStack, energyValue);
                ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
                energyValueMap = stackMappingsBuilder.putAll(valueMap).build();

                postCalculationValueMap.put(wrappedStack, factoredEnergyValue);
            }
        }
    }

    public void compute() {

        // TODO Should we load in values from file?

        // Initialize the "working copy" energy value map
        TreeMap<WrappedStack, EnergyValue> stackValueMap = new TreeMap<>();
        uncomputedStacks = new TreeSet<>();

        // Add in all pre-calculation energy value mappings
        // TODO Should we be extra vigilant about possible nulls here?
        stackValueMap.putAll(preCalculationValueMap);

        // Calculate values from the known methods to create items, and the pre-calculation value mappings
        // TODO Re-implement DynEMC here

        // Add in all post-calculation energy value mappings
        // TODO Should we be extra vigilant about possible nulls here?
        stackValueMap.putAll(postCalculationValueMap);

        // Bake the final calculated energy value map
        ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        energyValueMap = stackMappingsBuilder.build();

        // TODO Should we save the results to file now?
    }

    /**
     * Saves the pre-calculation, post-calculation, and calculated energy value maps to disk
     */
    public void save() {
        SerializationHelper.writeToJsonFile(energyValueMap, energyValuesFile);
        SerializationHelper.writeToJsonFile(preCalculationValueMap, preCalculationValuesFile);
        SerializationHelper.writeToJsonFile(postCalculationValueMap, postCalculationValuesFile);
    }

    /**
     * Loads the pre-calculation, post-calculation, and calculated energy value maps from disk. In the event that either
     * the pre/post calculation maps can not be loaded from disk they will be initialized as empty maps. If the
     * calculated energy value map can not be loaded from disk then the values will be computed from the pre/post
     * calculation maps
     */
    public void load() {

        try {
            preCalculationValueMap.putAll(SerializationHelper.readFromJsonFile(preCalculationValuesFile));
        } catch (FileNotFoundException e) {
            // TODO Log that no pre-calculation values were loaded from file because file wasn't found
        }

        try {
            postCalculationValueMap.putAll(SerializationHelper.readFromJsonFile(postCalculationValuesFile));
        } catch (FileNotFoundException e) {
            // TODO Log that no pre-calculation values were loaded from file because file wasn't found
        }

        try {
            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> energyValueMapBuilder = ImmutableSortedMap.naturalOrder();
            energyValueMapBuilder.putAll(SerializationHelper.readFromJsonFile(energyValuesFile));
            energyValueMap = energyValueMapBuilder.build();
        } catch (FileNotFoundException e) {
            LogHelper.warn("No calculated energy value file found, regenerating");
            // TODO Gen new values from the loaded pre/post value maps
        }
    }
}

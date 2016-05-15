package com.pahimar.ee3.exchange;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.JsonParseException;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.util.EnergyValueHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.*;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class NewEnergyValueRegistry {

    public static final NewEnergyValueRegistry INSTANCE = new NewEnergyValueRegistry();

    private ImmutableSortedMap<WrappedStack, EnergyValue> energyValueMap;

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

    public boolean hasEnergyValue(Object object) {
        return hasEnergyValue(object, false);
    }

    public boolean hasEnergyValue(Object object, boolean strict) {
        // TODO This
        return false;
    }

    public EnergyValue getEnergyValue(Object object) {
        return getEnergyValue(object, false);
    }

    public EnergyValue getEnergyValue(Object object, boolean strict) {
        // TODO This
        return null;
    }

    /**
     * Sets an {@link EnergyValue} for the provided {@link Object} (if it can be wrapped in a {@link WrappedStack}.
     * Depending on whether or not this is a pre-calculation value assignment it's also possible for the calculated
     * energy value map to be recomputed to take into account the new mapping.
     *
     * @param object the object the energy value is being assigned for
     * @param energyValue the energy value being setEnergyValue on the object
     * @param isPreCalculationAssignment whether or not the calculated energy value assignment is a pre-calculation
     *                                   value assignment or not
     */
    public void setEnergyValue(Object object, EnergyValue energyValue, boolean isPreCalculationAssignment) {
        setEnergyValue(object, energyValue, isPreCalculationAssignment, false);
    }

    /**
     * Sets an {@link EnergyValue} for the provided {@link Object} (if it can be wrapped in a {@link WrappedStack}.
     * Depending on whether or not this is a pre-calculation value assignment it's also possible for the calculated
     * energy value map to be recomputed to take into account the new mapping.
     *
     * @param object the object the energy value is being assigned for
     * @param energyValue the energy value being setEnergyValue on the object
     * @param isPreCalculationAssignment whether or not the calculated energy value assignment is a pre-calculation
     *                                   value assignment or not
     * @param doRegenValues whether or not the energy value map needs recomputing. Only an option if
     *                      <code>isPreCalculationAssignment</code> is true
     */
    public void setEnergyValue(Object object, EnergyValue energyValue, boolean isPreCalculationAssignment, boolean doRegenValues) {

        if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0) {

            WrappedStack wrappedStack = WrappedStack.wrap(object, 1);
            EnergyValue factoredEnergyValue = EnergyValueHelper.factor(energyValue, wrappedStack.getStackSize());

            if (isPreCalculationAssignment) {
                preCalculationValueMap.put(wrappedStack, factoredEnergyValue);

                if (doRegenValues) {
                    compute();
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

    /**
     * This is where the magic happens
     */
    public void compute() {

        // Initialize the "working copy" energy value map
        TreeMap<WrappedStack, EnergyValue> stackValueMap = new TreeMap<>();
        uncomputedStacks = new TreeSet<>();

        // Add in all pre-calculation energy value mappings
        preCalculationValueMap.keySet().stream()
                .filter(wrappedStack -> wrappedStack != null && wrappedStack.getWrappedObject() != null && preCalculationValueMap.get(wrappedStack) != null)
                .forEach(wrappedStack -> stackValueMap.put(wrappedStack, preCalculationValueMap.get(wrappedStack)));

        // Calculate values from the known methods to create items, and the pre-calculation value mappings
        calculate();

        // Add in all post-calculation energy value mappings
        postCalculationValueMap.keySet().stream()
                .filter(wrappedStack -> wrappedStack != null && wrappedStack.getWrappedObject() != null && postCalculationValueMap.get(wrappedStack) != null)
                .forEach(wrappedStack -> stackValueMap.put(wrappedStack, postCalculationValueMap.get(wrappedStack)));

        // Bake the final calculated energy value map
        ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        energyValueMap = stackMappingsBuilder.build();

        // Save the results to disk
        save();
    }

    private void calculate() {

        Map<WrappedStack, EnergyValue> computedMap;
        int passNumber, passComputed, totalComputed;
        passNumber = passComputed = totalComputed = 0;
        boolean firstPass = true;

        LogHelper.info(ENERGY_VALUE_MARKER, "Beginning energy value calculation");
        long startingTime = System.nanoTime();
        while ((firstPass || passComputed > 0) && passNumber < 16) {

            long passStartTime = System.nanoTime();
            passNumber++;
            passComputed = 0;
            if (firstPass) {
                firstPass = false;
            }
            long passDuration = System.nanoTime() - passStartTime;

            // TODO Tie this extra logging into a debug config option
            boolean debug = false;
            if (debug) {
                LogHelper.info(ENERGY_VALUE_MARKER, "Pass {}: Calculated {} values for objects in {} ns", passNumber, passComputed, passDuration);
            }
        }
        long endingTime = System.nanoTime() - startingTime;
        LogHelper.info(ENERGY_VALUE_MARKER, "Finished dynamic value calculation (calculated {} values for objects in {} ns)", totalComputed, endingTime);
    }

    /**
     * Saves the pre-calculation, post-calculation, and calculated energy value maps to disk
     */
    public void save() {
        writeToJsonFile(energyValueMap, energyValuesFile);
        writeToJsonFile(preCalculationValueMap, preCalculationValuesFile);
        writeToJsonFile(postCalculationValueMap, postCalculationValuesFile);
    }

    /**
     * Loads the pre-calculation, post-calculation, and calculated energy value maps from disk. In the event that either
     * the pre/post calculation maps can not be loaded from disk they will be initialized as empty maps. If the
     * calculated energy value map can not be loaded from disk then the values will be computed from the pre/post
     * calculation maps
     */
    public void load() {

        try {
            preCalculationValueMap.putAll(readFromJsonFile(preCalculationValuesFile));
        } catch (FileNotFoundException e) {
            // TODO Log that no pre-calculation values were loaded from file because file wasn't found
        }

        try {
            postCalculationValueMap.putAll(readFromJsonFile(postCalculationValuesFile));
        } catch (FileNotFoundException e) {
            // TODO Log that no post-calculation values were loaded from file because file wasn't found
        }

        try {
            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> energyValueMapBuilder = ImmutableSortedMap.naturalOrder();
            energyValueMapBuilder.putAll(readFromJsonFile(energyValuesFile));
            energyValueMap = energyValueMapBuilder.build();
        } catch (FileNotFoundException e) {
            LogHelper.warn("No calculated energy value file found, regenerating"); // TODO Better log message
            compute();
        }
    }

    /**
     *  @see net.minecraft.nbt.CompressedStreamTools#safeWrite(NBTTagCompound, File)
     */
    private static void writeToJsonFile(Map<WrappedStack, EnergyValue> valueMap, File file) {

        File tempFile = new File(file.getAbsolutePath() + "_tmp");

        if (tempFile.exists()) {
            tempFile.delete();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {

            bufferedWriter.write(SerializationHelper.GSON.toJson(valueMap, SerializationHelper.ENERGY_VALUE_MAP_TYPE));
            bufferedWriter.close();
        }
        catch (IOException exception) {
            exception.printStackTrace(); // TODO Better logging of the exception
        }

        if (file.exists()) {
            file.delete();
        }

        if (file.exists()) {
            LogHelper.warn("Failed to delete " + file);
        }
        else {
            tempFile.renameTo(file);
        }
    }

    private static Map<WrappedStack, EnergyValue> readFromJsonFile(File file) throws FileNotFoundException {

        Map<WrappedStack, EnergyValue> valueMap = new TreeMap<>();

        StringBuilder jsonStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        }
        catch (IOException exception) {
            if (exception instanceof FileNotFoundException) {
                throw (FileNotFoundException) exception;
            }
            else {
                exception.printStackTrace(); // TODO Better logging of the exception (other)
            }
        }

        try {
            valueMap = SerializationHelper.GSON.fromJson(jsonStringBuilder.toString(), SerializationHelper.ENERGY_VALUE_MAP_TYPE);
        }
        catch (JsonParseException exception) {
            // TODO Better logging of the exception (failed parsing so no values loaded)
        }

        return valueMap;
    }
}

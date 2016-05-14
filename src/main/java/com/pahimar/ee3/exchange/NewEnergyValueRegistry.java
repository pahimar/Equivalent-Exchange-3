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
import java.util.TreeMap;

public class NewEnergyValueRegistry {

    public static final NewEnergyValueRegistry INSTANCE = new NewEnergyValueRegistry();

    private ImmutableSortedMap<WrappedStack, EnergyValue> energyValueMap;
    private final Map<WrappedStack, EnergyValue> preCalculationValueMap;
    private final Map<WrappedStack, EnergyValue> postCalculationValueMap;

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

    public ImmutableMap<WrappedStack, EnergyValue> getEnergyValues() {
        return energyValueMap;
    }

    public Map<WrappedStack, EnergyValue> getPreCalculationValueMap() {
        return preCalculationValueMap;
    }

    public Map<WrappedStack, EnergyValue> getPostCalculationValueMap() {
        return postCalculationValueMap;
    }

    public void set(Object object, EnergyValue energyValue, boolean isPreCalculationAssignment) {

        if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0) {

            WrappedStack wrappedStack = WrappedStack.wrap(object, 1);
            EnergyValue factoredEnergyValue = EnergyValueHelper.factor(energyValue, wrappedStack.getStackSize());

            if (isPreCalculationAssignment) {
                if (preCalculationValueMap.containsKey(wrappedStack) && factoredEnergyValue.compareTo(preCalculationValueMap.get(wrappedStack)) < 0) {
                    preCalculationValueMap.put(wrappedStack, factoredEnergyValue);
                }
                else {
                    preCalculationValueMap.put(wrappedStack, factoredEnergyValue);
                }
            }
            else {
                if (postCalculationValueMap.containsKey(wrappedStack) && factoredEnergyValue.compareTo(postCalculationValueMap.get(wrappedStack)) < 0) {
                    postCalculationValueMap.put(wrappedStack, factoredEnergyValue);
                }
                else {
                    postCalculationValueMap.put(wrappedStack, factoredEnergyValue);
                }
            }
        }
    }

    public void save() {
        SerializationHelper.writeToJsonFile(energyValueMap, energyValuesFile);
        SerializationHelper.writeToJsonFile(preCalculationValueMap, preCalculationValuesFile);
        SerializationHelper.writeToJsonFile(postCalculationValueMap, postCalculationValuesFile);
    }

    public void load() {

        try {
            preCalculationValueMap.clear();
            preCalculationValueMap.putAll(SerializationHelper.readFromJsonFile(preCalculationValuesFile));
        } catch (FileNotFoundException e) {
            // TODO Log that no pre-calculation values were loaded from file because file wasn't found
        }

        try {
            postCalculationValueMap.clear();
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

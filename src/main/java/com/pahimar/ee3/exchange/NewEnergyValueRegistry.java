package com.pahimar.ee3.exchange;

import com.google.common.reflect.TypeToken;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

public class NewEnergyValueRegistry {

    public static final Marker ENERGY_VALUE_MARKER = MarkerManager.getMarker("EE3_ENERGY_VALUE", LogHelper.MOD_MARKER);
    public static final NewEnergyValueRegistry INSTANCE = new NewEnergyValueRegistry();
    public static final Type ENERGY_VALUE_MAP_TYPE = new TypeToken<Map<WrappedStack, EnergyValue>>(){}.getType();

    public final Map<WrappedStack, EnergyValue> preCalculationMappings;
    public final Map<WrappedStack, EnergyValue> postCalculationMappings;

    public static File energyValuesDataDirectory;
    public static File energyValuesDataFile;

    private NewEnergyValueRegistry() {
        preCalculationMappings = new TreeMap<>();
        // Loading up some dummy values for testing serialization
        preCalculationMappings.put(WrappedStack.wrap(Items.apple), new EnergyValue(1));
        preCalculationMappings.put(WrappedStack.wrap(Items.arrow), new EnergyValue(2));
        preCalculationMappings.put(WrappedStack.wrap(Items.baked_potato), new EnergyValue(3));
        preCalculationMappings.put(WrappedStack.wrap(Items.bed), new EnergyValue(4));
        preCalculationMappings.put(WrappedStack.wrap(new OreStack("oreIron")), new EnergyValue(5));


        postCalculationMappings = new TreeMap<>();
    }

    public String toJson() {
        LogHelper.info(SerializationHelper.GSON.toJson(this));
        return SerializationHelper.GSON.toJson(this);
    }

    /**
     *  @see net.minecraft.nbt.CompressedStreamTools#safeWrite(NBTTagCompound, File)
     */
    public void save() throws IOException {

        File file = energyValuesDataFile;
        File tempFile = new File(file.getAbsolutePath() + "_tmp");

        if (tempFile.exists()) {
            tempFile.delete();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
        bufferedWriter.write(this.toJson());
        bufferedWriter.close();

        if (file.exists()) {
            file.delete();
        }

        if (file.exists()) {
            throw new IOException("Failed to delete " + file);
        }
        else {
            tempFile.renameTo(file);
        }
    }

    public void load() {

    }
}

package com.pahimar.ee3.exchange;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.util.LogHelper;
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

public class NewEnergyValueRegistry implements JsonSerializer<NewEnergyValueRegistry>, JsonDeserializer<NewEnergyValueRegistry> {

    public static final Marker ENERGY_VALUE_MARKER = MarkerManager.getMarker("EE3_ENERGY_VALUE", LogHelper.MOD_MARKER);
    public static final NewEnergyValueRegistry INSTANCE = new NewEnergyValueRegistry();
    private static final Type ENERGY_VALUE_MAP_TYPE = new TypeToken<Map<WrappedStack, EnergyValue>>(){}.getType();

    private final Map<WrappedStack, EnergyValue> preCalculationMappings;
    private final Map<WrappedStack, EnergyValue> postCalculationMappings;

    private NewEnergyValueRegistry() {
        preCalculationMappings = new TreeMap<>();
        // Loading up some dummy values for testing serialization
        preCalculationMappings.put(WrappedStack.wrap(Items.apple), new EnergyValue(1));
        preCalculationMappings.put(WrappedStack.wrap(Items.arrow), new EnergyValue(2));
        preCalculationMappings.put(WrappedStack.wrap(Items.baked_potato), new EnergyValue(3));
        preCalculationMappings.put(WrappedStack.wrap(Items.bed), new EnergyValue(4));

        postCalculationMappings = new TreeMap<>();
    }

    public String toJson() {
        // FIXME This shouldn't be a new object all the time - we should be using an instance of a single serializer
        return new GsonBuilder().setPrettyPrinting().registerTypeAdapter(NewEnergyValueRegistry.class, new NewEnergyValueRegistry()).create().toJson(this);
    }

    /**
     *  @see net.minecraft.nbt.CompressedStreamTools#safeWrite(NBTTagCompound, File)
     */
    public void save() throws IOException {

        // TODO We should probably keep the references to the energy value data dir and file in this class and not in Files. You know, anti-patterns and stuff.
        File file = Files.energyValuesDataFile;
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

    @Override
    public NewEnergyValueRegistry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {
            JsonObject jsonRegistry = new JsonObject();

            if (jsonRegistry.getAsJsonObject("pre_calculation_value_mappings") instanceof JsonObject) {                                 // TODO String constant for property name
                Map<WrappedStack, EnergyValue> tempMap = context.deserialize(jsonRegistry.getAsJsonObject("pre_calculation_value_mappings"), ENERGY_VALUE_MAP_TYPE);
                this.preCalculationMappings.clear();
                this.preCalculationMappings.putAll(tempMap);
            }
        }

        return null;
    }

    @Override
    public JsonElement serialize(NewEnergyValueRegistry src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject jsonRegistry = new JsonObject();
        jsonRegistry.add("pre_calculation_value_mappings", context.serialize(src.preCalculationMappings, ENERGY_VALUE_MAP_TYPE));       // TODO String constant for property name
        jsonRegistry.add("post_calculation_value_mappings", context.serialize(src.postCalculationMappings, ENERGY_VALUE_MAP_TYPE));     // TODO String constant for property name
        return jsonRegistry;
    }
}

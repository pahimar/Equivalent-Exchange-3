package com.pahimar.ee3.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.knowledge.PlayerKnowledge;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.util.serialize.*;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class SerializationHelper {

    public static final Type ENERGY_VALUE_MAP_TYPE = new TypeToken<Map<WrappedStack, EnergyValue>>(){}.getType();
    public static final Type WRAPPED_STACK_SET_TYPE = new TypeToken<Set<WrappedStack>>(){}.getType();
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .registerTypeAdapter(ItemStack.class, new ItemStackSerializer())
            .registerTypeAdapter(OreStack.class, new OreStackSerializer())
            .registerTypeAdapter(FluidStack.class, new FluidStackSerializer())
            .registerTypeAdapter(WrappedStack.class, new WrappedStackSerializer())
            .registerTypeAdapter(PlayerKnowledge.class, new PlayerKnowledgeSerializer())
            .registerTypeAdapter(ENERGY_VALUE_MAP_TYPE, new EnergyValueMapSerializer())
            .registerTypeAdapter(ENERGY_VALUE_MAP_TYPE, new EnergyValueMapSerializer())
            .create();

    private static File instanceDataDirectory;
    private static File instancePlayerDataDirectory;

    /**
     * Returns a File reference to the mod specific directory in the data directory
     *
     * @return
     */
    @Deprecated
    public static File getInstanceDataDirectory()
    {
        return instanceDataDirectory;
    }

    /**
     * TODO Move this to {@link com.pahimar.ee3.reference.Files}
     *
     * Creates (if one does not exist already) and initializes a mod specific File reference inside of the current world's playerdata directory
     */
    public static void initModDataDirectories() {

        instanceDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + Reference.LOWERCASE_MOD_ID);
        instanceDataDirectory.mkdirs();

        instancePlayerDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "playerdata" + File.separator + Reference.LOWERCASE_MOD_ID);
        instancePlayerDataDirectory.mkdirs();
    }

    public static Set<WrappedStack> readSetFromFile(File file) {

        Set<WrappedStack> wrappedStackSet = new TreeSet<>();

        try {
            wrappedStackSet = GSON.fromJson(readJsonFile(file), WRAPPED_STACK_SET_TYPE);
        }
        catch (JsonParseException exception) {
            LogHelper.error("Unable to parse contents from file '{}'", file.getAbsoluteFile());
        }
        catch (FileNotFoundException e) {
            LogHelper.warn("Unable to find file '{}'", file.getAbsoluteFile());
        }

        return wrappedStackSet;
    }

    public static void writeSetToFile(Set<WrappedStack> wrappedStackSet, File file) {
        writeJsonFile(file, GSON.toJson(wrappedStackSet));
    }

    public static Map<WrappedStack, EnergyValue> readMapFromFile(File file) throws FileNotFoundException {

        Map<WrappedStack, EnergyValue> valueMap = new TreeMap<>();

        try {
            valueMap = GSON.fromJson(readJsonFile(file), ENERGY_VALUE_MAP_TYPE);
        }
        catch (JsonParseException exception) {
            // TODO Better logging of the exception (failed parsing so no values loaded)
        }

        return valueMap;
    }

    public static void writeMapToFile(Map<WrappedStack, EnergyValue> valueMap, File file) {
        writeJsonFile(file, GSON.toJson(valueMap, ENERGY_VALUE_MAP_TYPE));
    }

    public static String readJsonFile(File file) throws FileNotFoundException {

        StringBuilder jsonStringBuilder = new StringBuilder();
        if (file != null) {
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
                    exception.printStackTrace(); // TODO Better logging of the exception
                }
            }
        }
        return jsonStringBuilder.toString();
    }

    public static void writeJsonFile(File file, String fileContents) {

        if (file != null) {

            file.getParentFile().mkdirs();
            File tempFile = new File(file.getAbsolutePath() + "_tmp");

            if (tempFile.exists()) {
                tempFile.delete();
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {

                bufferedWriter.write(fileContents);
                bufferedWriter.close();
            } catch (IOException exception) {
                exception.printStackTrace(); // TODO Better logging of the exception
            }

            if (file.exists()) {
                file.delete();
            }

            if (file.exists()) {
                LogHelper.warn("Failed to delete " + file);
            } else {
                tempFile.renameTo(file);
            }
        }
    }
}

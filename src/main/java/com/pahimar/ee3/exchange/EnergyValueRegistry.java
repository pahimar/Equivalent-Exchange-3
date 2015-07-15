package com.pahimar.ee3.exchange;

import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.exchange.IEnergyValueProvider;
import com.pahimar.ee3.configuration.EnergyRegenOption;
import com.pahimar.ee3.filesystem.FileSystem;
import com.pahimar.ee3.filesystem.IFileSystem;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.serialization.EnergyValueStackMappingSerializer;
import com.pahimar.ee3.serialization.JsonSerialization;
import com.pahimar.ee3.util.EnergyValueHelper;
import com.pahimar.ee3.util.LoaderHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.naming.OperationNotSupportedException;
import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

public class EnergyValueRegistry implements JsonSerializer<EnergyValueRegistry>, JsonDeserializer<EnergyValueRegistry>
{
    // TODO Rethink serialization here
    private static final Gson JSON_SERIALIZER = (new GsonBuilder()).setPrettyPrinting()
            .registerTypeAdapter(EnergyValueRegistry.class, new EnergyValueRegistry())
            .registerTypeAdapter(EnergyValueStackMapping.class, new EnergyValueStackMappingSerializer()).create();

    private static EnergyValueRegistry currentInstance;
    private static final Object singletonSyncRoot = new Object();

    private boolean shouldRegenNextRestart = false;
    private ImmutableSortedMap<WrappedStack, EnergyValue> stackMappings;
    private ImmutableSortedMap<EnergyValue, List<WrappedStack>> valueMappings;
    private SortedSet<WrappedStack> uncomputedStacks;

    private EnergyValueRegistry()
    {
    }

    public static EnergyValueRegistry getInstance()
    {
        if (currentInstance == null)
        {
            synchronized (singletonSyncRoot) {
                if(currentInstance == null)
                    currentInstance = new EnergyValueRegistry();
            }
        }

        return currentInstance;
    }

    public static void invalidateInstance()
    {
        currentInstance = null;
    }

    public static boolean hasEnergyValue(Object object)
    {
        return hasEnergyValue(object, false);
    }

    public static boolean hasEnergyValue(Object object, boolean strict)
    {
        return getEnergyValue(object, strict) != null;
    }

    public static EnergyValue getEnergyValue(Object object)
    {
        return getEnergyValue(EnergyValueRegistryProxy.Phase.ALL, object, false);
    }

    public static EnergyValue getEnergyValue(Object object, boolean strict)
    {
        return getEnergyValue(EnergyValueRegistryProxy.Phase.ALL, object, strict);
    }

    public static EnergyValue getEnergyValue(EnergyValueRegistryProxy.Phase phase, Object object, boolean strict)
    {
        return getEnergyValueFromMap(getEnergyValueMap(phase), object, strict);
    }

    public static EnergyValue getEnergyValueForStack(Object object, boolean strict)
    {
        WrappedStack wrappedObject = WrappedStack.wrap(object);

        if (wrappedObject != null && getEnergyValue(object, strict) != null)
        {
            return new EnergyValue(getEnergyValue(object, strict).getValue() * wrappedObject.getStackSize());
        }

        return null;
    }

    public static EnergyValue getEnergyValueFromMap(Map<WrappedStack, EnergyValue> stackEnergyValueMap, Object object)
    {
        return getEnergyValueFromMap(stackEnergyValueMap, object, false);
    }

    public static EnergyValue getEnergyValueFromMap(Map<WrappedStack, EnergyValue> stackEnergyValueMap, Object object, boolean strict)
    {
        EnergyValueQuery query = new EnergyValueQuery(object, strict);
        return query.getEnergyValueFromMap(stackEnergyValueMap);
    }

    public static Map<WrappedStack, EnergyValue> getEnergyValueMap(EnergyValueRegistryProxy.Phase phase)
    {
        switch (phase)
        {
            case PRE_ASSIGNMENT:
            case PRE_CALCULATION:
                return Factory.preCalculationMappings;
            case POST_ASSIGNMENT:
            case POST_CALCULATION:
                return Factory.postCalculationMappings;
            default:
                return getInstance().getStackValueMap();
        }
    }

    protected final void init()
            throws OperationNotSupportedException
    {
        if(this.shouldRegenerateEnergyValue())
            this.runDynamicEnergyValueResolution();

        this.shouldRegenNextRestart = false;
    }

    protected boolean shouldRegenerateEnergyValue()
    {
        if(Settings.DynamicEnergyValueGeneration.regenerateEnergyValuesWhen == EnergyRegenOption.Always)
            return true;

        return !this.loadEnergyValueRegistryFromFile();
    }

    private void runDynamicEnergyValueResolution()
            throws OperationNotSupportedException
    {
        IRegistryContext context = new Context(this);
        IEnergyCalculationDataProvider dataProvider = new CalculationDataProvider();
        EnergyCalculationSession session = new EnergyCalculationSession(context, dataProvider);

        EnergyCalculationSession.Result result = session.runDynamicEnergyValueResolution();
        this.stackMappings = result.getStackValueMap();

        /**
         *  Value map resolution
         */
        generateValueStackMappings();

        // Serialize values to disk
        LogHelper.info("Saving energy values to disk");
        save();

        // TODO Make this make "sense" and also ensure it's added as an option to the debug command
        if(this.uncomputedStacks != null)
        {
            LogHelper.info("BEGIN UNCOMPUTED OBJECT LIST");
            for (WrappedStack wrappedStack : uncomputedStacks)
            {
                if (!hasEnergyValue(wrappedStack))
                {
                    LogHelper.info(wrappedStack);
                }
            }
            LogHelper.info("END UNCOMPUTED OBJECT LIST");
        }
    }

    private void generateValueStackMappings()
    {
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

        if (valueMappings != null)
        {
            SortedMap<EnergyValue, List<WrappedStack>> tailMap = currentInstance.valueMappings.tailMap(start);
            SortedMap<EnergyValue, List<WrappedStack>> headMap = currentInstance.valueMappings.headMap(finish);

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
                        for (WrappedStack wrappedStack : currentInstance.valueMappings.get(value))
                        {
                            if (wrappedStack.getWrappedObject() instanceof ItemStack || wrappedStack.getWrappedObject() instanceof FluidStack)
                            {
                                stacksInRange.add(wrappedStack.getWrappedObject());
                            }
                            else if (wrappedStack.getWrappedObject() instanceof OreStack)
                            {
                                for (ItemStack itemStack : OreDictionary.getOres(((OreStack) wrappedStack.getWrappedObject()).oreName))
                                {
                                    stacksInRange.add(itemStack);
                                }
                            }
                        }
                    }
                }
            }
        }

        return stacksInRange;
    }

    public void loadFromMap(Map<WrappedStack, EnergyValue> stackValueMap)
    {
        if (stackValueMap != null)
        {
            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(stackValueMap);
            stackMappings = stackMappingsBuilder.build();

            /**
             *  Resolve value stack mappings from the newly loaded stack mappings
             */
            generateValueStackMappings();
        }
    }

    public void setEnergyValue(WrappedStack wrappedStack, EnergyValue energyValue)
    {
        if (wrappedStack != null && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0)
        {
            TreeMap<WrappedStack, EnergyValue> stackValueMap = new TreeMap<WrappedStack, EnergyValue>(stackMappings);
            stackValueMap.put(wrappedStack, energyValue);

            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(stackValueMap);
            stackMappings = stackMappingsBuilder.build();

            generateValueStackMappings();
        }
    }

    public boolean getShouldRegenNextRestart()
    {
        return shouldRegenNextRestart;
    }

    public void setShouldRegenNextRestart(boolean shouldRegenNextRestart)
    {
        this.shouldRegenNextRestart = shouldRegenNextRestart;
    }

    public ImmutableSortedMap<WrappedStack, EnergyValue> getStackValueMap()
    {
        return stackMappings;
    }

    public ImmutableSortedMap<EnergyValue, List<WrappedStack>> getValueStackMap()
    {
        return valueMappings;
    }

    public void save()
    {
        World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
        File energyValuesDataDirectory = FileSystem.getWorld(world).getEnergyValuesDirectory();
        energyValuesDataDirectory.mkdirs();

        if (shouldRegenNextRestart)
        {
            File staticEnergyValuesJsonFile = new File(energyValuesDataDirectory, Files.STATIC_ENERGY_VALUES_JSON);
            File md5EnergyValuesJsonFile = new File(energyValuesDataDirectory, SerializationHelper.getModListMD5() + ".json");

            // JSON
            if (staticEnergyValuesJsonFile.exists())
            {
                staticEnergyValuesJsonFile.delete();
            }
            if (md5EnergyValuesJsonFile.exists())
            {
                md5EnergyValuesJsonFile.delete();
            }

            shouldRegenNextRestart = false;
        }
        else
        {
            SerializationHelper.compressEnergyValueStackMapToFile(new File(energyValuesDataDirectory, Files.STATIC_ENERGY_VALUES_JSON), currentInstance.stackMappings);
            SerializationHelper.compressEnergyValueStackMapToFile(new File(energyValuesDataDirectory, SerializationHelper.getModListMD5() + ".json.gz"), currentInstance.stackMappings);
        }
    }

    public boolean loadEnergyValueRegistryFromFile()
    {
        World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
        IFileSystem fileSystem = FileSystem.getWorld(world);
        File energyValuesDataDirectory = fileSystem.getEnergyValuesDirectory();
        energyValuesDataDirectory.mkdirs();

        File staticEnergyValuesFile = fileSystem.getStaticEnergyValueFile();
        File md5EnergyValuesFile = fileSystem.getEnergyValueFile(SerializationHelper.getModListMD5() + ".json.gz");

        Map<WrappedStack, EnergyValue> stackValueMap = null;
        if (Settings.DynamicEnergyValueGeneration.regenerateEnergyValuesWhen != EnergyRegenOption.Always)
        {
            if (Settings.DynamicEnergyValueGeneration.regenerateEnergyValuesWhen == EnergyRegenOption.ModsChange)
            {
                if (md5EnergyValuesFile.exists())
                {
                    LogHelper.info("Attempting to load energy values from file: " + md5EnergyValuesFile.getAbsolutePath());
                    stackValueMap = SerializationHelper.decompressEnergyValueStackMapFromFile(md5EnergyValuesFile);
                }
            }
            else if (Settings.DynamicEnergyValueGeneration.regenerateEnergyValuesWhen == EnergyRegenOption.Never)
            {
                if (staticEnergyValuesFile.exists())
                {
                    LogHelper.info("Attempting to load energy values from file: " + staticEnergyValuesFile.getAbsolutePath());
                    stackValueMap = SerializationHelper.decompressEnergyValueStackMapFromFile(staticEnergyValuesFile);
                }
                else if (md5EnergyValuesFile.exists())
                {
                    LogHelper.info("Attempting to load energy values from file: " + md5EnergyValuesFile.getAbsolutePath());
                    stackValueMap = SerializationHelper.decompressEnergyValueStackMapFromFile(md5EnergyValuesFile);
                }
            }

            if (stackValueMap != null)
            {
                loadFromMap(stackValueMap);
                LogHelper.info("Successfully loaded energy values from file");
                return true;
            }
            else
            {
                LogHelper.info("No energy value file to load values from, generating new values");
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public String toJson()
    {
        return JSON_SERIALIZER.toJson(this);
    }

    @Override
    public EnergyValueRegistry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (json.isJsonArray())
        {
            JsonArray jsonArray = (JsonArray) json;
            Map<WrappedStack, EnergyValue> stackValueMap = new TreeMap<WrappedStack, EnergyValue>();
            Iterator<JsonElement> iterator = jsonArray.iterator();

            while (iterator.hasNext())
            {
                JsonElement jsonElement = iterator.next();
                EnergyValueStackMapping energyValueStackMapping = new EnergyValueStackMappingSerializer().deserialize(jsonElement, typeOfT, context);

                if (energyValueStackMapping != null)
                {
                    stackValueMap.put(energyValueStackMapping.wrappedStack, energyValueStackMapping.energyValue);
                }
            }

            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(stackValueMap);
            stackMappings = stackMappingsBuilder.build();

            generateValueStackMappings();
        }

        return null;
    }

    @Override
    public JsonElement serialize(EnergyValueRegistry energyValueRegistry, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonArray jsonEnergyValueRegistry = new JsonArray();

        for (WrappedStack wrappedStack : energyValueRegistry.stackMappings.keySet())
        {
            jsonEnergyValueRegistry.add(JsonSerialization.jsonSerializer.toJsonTree(new EnergyValueStackMapping(wrappedStack, energyValueRegistry.stackMappings.get(wrappedStack))));
        }

        return jsonEnergyValueRegistry;
    }

    public static void dumpEnergyValueRegistryToLog()
    {
        dumpEnergyValueRegistryToLog(EnergyValueRegistryProxy.Phase.ALL);
    }

    public static void dumpEnergyValueRegistryToLog(EnergyValueRegistryProxy.Phase phase)
    {
        LogHelper.info(String.format("BEGIN DUMPING %s ENERGY VALUE MAPPINGS", phase));
        dumpEnergyValueRegistryToLog(getEnergyValueMap(phase));
        LogHelper.info(String.format("END DUMPING %s ENERGY VALUE MAPPINGS", phase));
    }

    private static void dumpEnergyValueRegistryToLog(Map<WrappedStack, EnergyValue> values)
    {
        final String format = "- Object: %s, Value: %s";

        if (values != null)
        {
            for (WrappedStack wrappedStack : values.keySet())
                LogHelper.info(String.format(format, wrappedStack, values.get(wrappedStack)));
        }
    }

    private final class Context implements IRegistryContext
    {
        private final EnergyValueRegistry registry;
        private final IFileSystem globalFs;
        private final IFileSystem worldFs;

        private Context(EnergyValueRegistry registry) throws OperationNotSupportedException
        {
            this.registry = registry;
            this.globalFs = FileSystem.getGlobal();

            World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
            this.worldFs = FileSystem.getWorld(world);
        }

        @Override
        public boolean hasEnergyValue(Object object, boolean strict)
        {
            return this.registry.hasEnergyValue(object, strict);
        }

        @Override
        public IFileSystem getGlobal()
        {
            return this.globalFs;
        }

        @Override
        public IFileSystem getWorld()
        {
            return this.worldFs;
        }
    }

    private final class CalculationDataProvider implements IEnergyCalculationDataProvider
    {
        private CalculationDataProvider()
        {
        }

        @Override
        public Map<WrappedStack, EnergyValue> getPreCalculationMappings()
        {
            return EnergyValueRegistry.Factory.preCalculationMappings;
        }

        @Override
        public Map<WrappedStack, EnergyValue> getPostCalculationMappings()
        {
            return EnergyValueRegistry.Factory.postCalculationMappings;
        }

        @Override
        public IEnergyValuesSource[] getPreCalculationSources()
        {
            return EnergyValueRegistry.Factory.preCalculationSources;
        }

        @Override
        public IEnergyValuesSource[] getPostCalculationSources()
        {
            return EnergyValueRegistry.Factory.postCalculationSources;
        }
    }

    public static final class Factory
    {
        // TODO Expose to API
        private static final IEnergyValuesSource[] preCalculationSources = {
                new FileSystemEnergyValuesSource(Files.PRE_CALCULATION_ENERGY_VALUES),
                new FileSystemEnergyValuesSource(Files.PRE_CALCULATION_ENERGY_VALUES, true)
        };

        private static final IEnergyValuesSource[] postCalculationSources = {
                new FileSystemEnergyValuesSource(Files.POST_CALCULATION_ENERGY_VALUES),
                new FileSystemEnergyValuesSource(Files.POST_CALCULATION_ENERGY_VALUES, true)
        };

        // Fields are now final, in no case they will be null.
        private final static Map<WrappedStack, EnergyValue> preCalculationMappings;
        private final static Map<WrappedStack, EnergyValue> postCalculationMappings;

        static
        {
            preCalculationMappings = new TreeMap<WrappedStack, EnergyValue>();
            postCalculationMappings = new TreeMap<WrappedStack, EnergyValue>();
        }

        public static void addPreCalculationEnergyValue(Object object, float energyValue)
        {
            addPreCalculationEnergyValue(object, new EnergyValue(energyValue));
        }

        public static void addPreCalculationEnergyValue(Object object, EnergyValue energyValue)
        {
            if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0)
            {
                WrappedStack wrappedStack = WrappedStack.wrap(object);

                if (wrappedStack.getStackSize() > 0)
                {
                    WrappedStack factoredWrappedStack = WrappedStack.wrap(wrappedStack, 1);
                    EnergyValue factoredEnergyValue = EnergyValueHelper.factorEnergyValue(energyValue, wrappedStack.getStackSize());

                    if (preCalculationMappings.containsKey(factoredWrappedStack))
                    {
                        if (factoredEnergyValue.compareTo(preCalculationMappings.get(factoredWrappedStack)) < 0)
                        {
                            LogHelper.trace(String.format("EnergyValueRegistry[%s]: Mod with ID '%s' added a pre-assignment energy value of %s for object %s", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), energyValue, wrappedStack));
                            preCalculationMappings.put(factoredWrappedStack, factoredEnergyValue);
                        }
                    }
                    else
                    {
                        LogHelper.trace(String.format("EnergyValueRegistry[%s]: Mod with ID '%s' added a pre-assignment energy value of %s for object %s", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), energyValue, wrappedStack));
                        preCalculationMappings.put(factoredWrappedStack, factoredEnergyValue);
                    }
                }
            }
        }

        public static void addPostCalculationExactEnergyValue(Object object, float energyValue)
        {
            addPostCalculationExactEnergyValue(object, new EnergyValue(energyValue));
        }

        public static void addPostCalculationExactEnergyValue(Object object, EnergyValue energyValue)
        {
            if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0)
            {
                WrappedStack wrappedStack = WrappedStack.wrap(object);

                if (wrappedStack.getStackSize() > 0)
                {
                    WrappedStack factoredWrappedStack = WrappedStack.wrap(wrappedStack, 1);
                    EnergyValue factoredEnergyValue = EnergyValueHelper.factorEnergyValue(energyValue, wrappedStack.getStackSize());

                    LogHelper.trace(String.format("EnergyValueRegistry[%s]: Mod with ID '%s' added a post-assignment energy value of %s for object %s", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), energyValue, wrappedStack));
                    postCalculationMappings.put(factoredWrappedStack, factoredEnergyValue);
                }
            }
        }
    }

    // TODO Introduce caching.
    private static final class EnergyValueQuery
    {
        private final Object object;
        private final boolean strict;

        public EnergyValueQuery(Object object, boolean strict)
        {
            this.object = object;
            this.strict = strict;
        }

        public EnergyValue getEnergyValueFromMap(Map<WrappedStack, EnergyValue> stackEnergyValueMap)
        {
            WrappedStack wrappedStackObject = WrappedStack.wrap(object);
            if(wrappedStackObject == null)
                return null;

            WrappedStack unitWrappedStackObject = WrappedStack.wrap(object);
            unitWrappedStackObject.setStackSize(1);
            Object wrappedObject = wrappedStackObject.getWrappedObject();

            EnergyValue energyValue = tryGetAsEnergyValueProvider(object, strict);
            if(isValidValue(energyValue))
                return energyValue;

            if(stackEnergyValueMap == null)
                return null;

            energyValue = tryGetFromMap(unitWrappedStackObject, stackEnergyValueMap);
            if(isValidValue(energyValue))
                return energyValue;

            if (strict)
                return null;

            energyValue = tryGetAsItemStack(wrappedObject, stackEnergyValueMap);
            if(isValidValue(energyValue))
                return energyValue;

            energyValue = tryGetAsOreStack(wrappedObject, stackEnergyValueMap);
            if(isValidValue(energyValue))
                return energyValue;

            return null;
        }

        private static EnergyValue tryGetAsEnergyValueProvider(Object object, boolean strict)
        {
            /**
             *  In the event that an Item has an IEnergyValueProvider implementation, route the call to the implementation
             */
            if(strict || !(object instanceof ItemStack))
                return null;

            ItemStack itemStack = (ItemStack) object;
            if(!(itemStack.getItem() instanceof IEnergyValueProvider))
                return null;

            IEnergyValueProvider energyValueProvider =(IEnergyValueProvider) itemStack.getItem();
            return energyValueProvider.getEnergyValue(itemStack);
        }

        private static EnergyValue tryGetFromMap(WrappedStack unitStack, Map<WrappedStack, EnergyValue> map)
        {
            return map.containsKey(unitStack) ? map.get(unitStack) : null;
        }

        private static EnergyValue tryGetAsItemStack(Object object, Map<WrappedStack, EnergyValue> map)
        {
            if(!(object instanceof ItemStack))
                return null;

            ItemStack itemStack = (ItemStack) object;
            EnergyValue oreDictionaryResult = tryGetFromOreDictionary(itemStack, map);
            if(isValidValue(oreDictionaryResult))
                return oreDictionaryResult;

            EnergyValue metaValueResult = tryGetByMetaValue(itemStack, map);
            if(isValidValue(metaValueResult))
                return metaValueResult;

            return null;
        }

        private static EnergyValue tryGetFromOreDictionary(ItemStack itemStack, Map<WrappedStack, EnergyValue> map)
        {
            /**
             *  The ItemStack does not have a direct mapping, so check if it is a member of an OreDictionary
             *  entry. If it is a member of an OreDictionary entry, check if every ore name it is associated
             *  with has 1) a direct mapping, and 2) the same mapping value
             */
            int[] oreDictionaryIDs = OreDictionary.getOreIDs(itemStack);
            if (oreDictionaryIDs.length < 1)
                return null;

            return scanOreDictionary(oreDictionaryIDs, map);
        }

        private static EnergyValue scanOreDictionary(int[] oreIDs, Map<WrappedStack, EnergyValue> map)
        {
            EnergyValue energyValue = null;
            boolean allHaveSameValueFlag = true;

            // Scan all valid ore dictionary values, if they ALL have the same value, then return it
            for (int oreID : oreIDs)
            {
                String oreName = OreDictionary.getOreName(oreID);
                if (oreName.equals("Unknown") || !allHaveSameValueFlag)
                    return null;

                WrappedStack oreStack = WrappedStack.wrap(new OreStack(oreName));
                if (oreStack == null || !map.containsKey(oreStack))
                    return null;

                if (energyValue == null)
                    energyValue = map.get(oreStack);
                else if (!energyValue.equals(map.get(oreStack)))
                    allHaveSameValueFlag = false;
            }

            return energyValue != null && allHaveSameValueFlag ? energyValue : null;
        }

        private static EnergyValue scanOreDictionary(List<ItemStack> oreStacks, Map<WrappedStack, EnergyValue> map)
        {
            EnergyValue energyValue = null;
            boolean allHaveSameValueFlag = true;

            // Scan all valid ore dictionary values, if they ALL have the same value, then return it
            for (ItemStack itemStack : oreStacks)
            {
                WrappedStack wrappedItemStack = WrappedStack.wrap(itemStack);
                if (wrappedItemStack == null || !map.containsKey(wrappedItemStack))
                    return null;

                if (energyValue == null)
                    energyValue = map.get(wrappedItemStack);
                else if (!energyValue.equals(map.get(wrappedItemStack)))
                    allHaveSameValueFlag = false;
            }

            return energyValue != null && allHaveSameValueFlag ? energyValue : null;
        }

        // TODO Huge performance overhead here, the function is checking for wildcard meta values
        //      and damageable items, while only 5% percent of items fall into this category we're
        //      iterating through the whole map.
        private static EnergyValue tryGetByMetaValue(ItemStack itemStack, Map<WrappedStack, EnergyValue> map)
        {
            /**
             *  Scan the stack value map for ItemStacks that have the same Item. If one is found, check
             *  if it has a wildcard meta value (and therefore is considered the same). Otherwise, check
             *  if the ItemStack is "damageable" and calculate the value for the damaged stack.
             */
            EnergyValue lowestValue = null;
            boolean shouldCheckDamage = itemStack.getItem().isDamageable() && itemStack.isItemDamaged();
            for (WrappedStack valuedStack : map.keySet())
            {
                if (valuedStack.getWrappedObject() instanceof ItemStack)
                {
                    ItemStack valuedItemStack = (ItemStack) valuedStack.getWrappedObject();
                    if (Item.getIdFromItem(valuedItemStack.getItem()) != Item.getIdFromItem(itemStack.getItem()))
                        continue;

                    if (valuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE)
                    {
                        EnergyValue stackValue = map.get(valuedStack);
                        if (stackValue.compareTo(lowestValue) < 0)
                            lowestValue = stackValue;
                    } else if (shouldCheckDamage)
                    {
                        EnergyValue stackValue = new EnergyValue(calculateEnergyValue(itemStack, map.get(valuedStack)));
                        if (stackValue.compareTo(lowestValue) < 0)
                            lowestValue = stackValue;
                    }
                }
            }

            return lowestValue;
        }

        private static float calculateEnergyValue(ItemStack itemStack, EnergyValue mapping)
        {
            float baseValue = mapping.getValue();
            return baseValue * (1 - (itemStack.getItemDamage() * 1.0F / itemStack.getMaxDamage()));
        }

        private static EnergyValue tryGetAsOreStack(Object object, Map<WrappedStack, EnergyValue> map)
        {
            if (!(object instanceof OreStack))
                return null;

            OreStack oreStack = (OreStack) object;
            List<ItemStack> oreDictionaryStacks = CachedOreDictionary.getInstance().getItemStacksForOreName(oreStack.oreName);
            if (oreDictionaryStacks.size() < 1)
                return null;

            return scanOreDictionary(oreDictionaryStacks, map);
        }

        private static boolean isValidValue(EnergyValue energyValue)
        {
            return energyValue != null && energyValue.getValue() > 0f;
        }
    }
}

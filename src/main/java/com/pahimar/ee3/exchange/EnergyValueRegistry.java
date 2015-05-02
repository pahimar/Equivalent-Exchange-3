package com.pahimar.ee3.exchange;

import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.*;
import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.api.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.IEnergyValueProvider;
import com.pahimar.ee3.knowledge.AbilityRegistry;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.util.EnergyValueHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

public class EnergyValueRegistry implements JsonSerializer<EnergyValueRegistry>, JsonDeserializer<EnergyValueRegistry>
{
    private static final Gson jsonSerializer = (new GsonBuilder()).registerTypeAdapter(EnergyValueRegistry.class, new EnergyValueRegistry()).registerTypeAdapter(EnergyValueStackMapping.class, new EnergyValueStackMapping()).create();
    private static final Gson prettyJsonSerializer = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(EnergyValueRegistry.class, new EnergyValueRegistry()).registerTypeAdapter(EnergyValueStackMapping.class, new EnergyValueStackMapping()).create();

    private boolean shouldRegenNextRestart = false;
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

    public void addPreAssignedEnergyValue(Object object, Number energyValue)
    {
        addPreAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public void addPreAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        if (preAssignedMappings == null)
        {
            preAssignedMappings = new TreeMap<WrappedStack, EnergyValue>();
        }

        if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0)
        {
            WrappedStack wrappedStack = WrappedStack.wrap(object);

            if (wrappedStack.getStackSize() > 0)
            {
                WrappedStack factoredWrappedStack = WrappedStack.wrap(wrappedStack, 1);
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

    public void addPostAssignedExactEnergyValue(Object object, Number energyValue)
    {
        addPostAssignedExactEnergyValue(object, new EnergyValue(energyValue));
    }

    public void addPostAssignedExactEnergyValue(Object object, EnergyValue energyValue)
    {
        if (postAssignedMappings == null)
        {
            postAssignedMappings = new TreeMap<WrappedStack, EnergyValue>();
        }

        if (WrappedStack.canBeWrapped(object) && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0)
        {
            WrappedStack wrappedStack = WrappedStack.wrap(object);

            if (wrappedStack.getStackSize() > 0)
            {
                WrappedStack factoredWrappedStack = WrappedStack.wrap(wrappedStack, 1);
                EnergyValue factoredEnergyValue = EnergyValueHelper.factorEnergyValue(energyValue, wrappedStack.getStackSize());

                postAssignedMappings.put(factoredWrappedStack, factoredEnergyValue);
            }
        }
    }

    public boolean hasEnergyValue(Object object)
    {
        return hasEnergyValue(object, false);
    }

    public boolean hasEnergyValue(Object object, boolean strict)
    {
        return getEnergyValue(object, strict) != null;
    }

    public EnergyValue getEnergyValue(Object object)
    {
        return getEnergyValueFromMap(energyValueRegistry.stackMappings, object, false);
    }

    public EnergyValue getEnergyValue(Object object, boolean strict)
    {
        return getEnergyValueFromMap(energyValueRegistry.stackMappings, object, strict);
    }

    public EnergyValue getEnergyValueForStack(Object object)
    {
        return getEnergyValueForStack(object, false);
    }

    public EnergyValue getEnergyValueForStack(Object object, boolean strict)
    {
        WrappedStack wrappedObject = WrappedStack.wrap(object);

        if (wrappedObject != null && getEnergyValue(object, strict) != null)
        {
            return new EnergyValue(getEnergyValue(object, strict).getValue() * wrappedObject.getStackSize());
        }

        return null;
    }

    public EnergyValue getEnergyValueFromMap(Map<WrappedStack, EnergyValue> stackEnergyValueMap, Object object)
    {
        return getEnergyValueFromMap(stackEnergyValueMap, object, false);
    }

    public EnergyValue getEnergyValueFromMap(Map<WrappedStack, EnergyValue> stackEnergyValueMap, Object object, boolean strict)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack wrappedStackObject = WrappedStack.wrap(object);
            WrappedStack unitWrappedStackObject = WrappedStack.wrap(object);
            unitWrappedStackObject.setStackSize(1);
            Object wrappedObject = wrappedStackObject.getWrappedObject();

            /**
             *  In the event that an Item has an IEnergyValueProvider implementation, route the call to the implementation
             */
            if (wrappedObject instanceof ItemStack && ((ItemStack) wrappedObject).getItem() instanceof IEnergyValueProvider && !strict)
            {
                ItemStack itemStack = (ItemStack) wrappedObject;
                IEnergyValueProvider iEnergyValueProvider = (IEnergyValueProvider) itemStack.getItem();
                EnergyValue energyValue = iEnergyValueProvider.getEnergyValue(itemStack);

                if (energyValue != null && energyValue.getValue() > 0f)
                {
                    return energyValue;
                }
            }
            else if (stackEnergyValueMap != null)
            {
                /**
                 *  Check for a direct value mapping for the object
                 */
                if (stackEnergyValueMap.containsKey(unitWrappedStackObject))
                {
                    return stackEnergyValueMap.get(unitWrappedStackObject);
                }
                else if (!strict)
                {
                    if (wrappedObject instanceof ItemStack)
                    {
                        EnergyValue lowestValue = null;
                        ItemStack wrappedItemStack = (ItemStack) wrappedObject;

                        /**
                         *  The ItemStack does not have a direct mapping, so check if it is a member of an OreDictionary
                         *  entry. If it is a member of an OreDictionary entry, check if every ore name it is associated
                         *  with has 1) a direct mapping, and 2) the same mapping value
                         */
                        if (OreDictionary.getOreIDs(wrappedItemStack).length >= 1)
                        {
                            EnergyValue energyValue = null;
                            boolean allHaveSameValueFlag = true;

                            // Scan all valid ore dictionary values, if they ALL have the same value, then return it
                            for (int oreID : OreDictionary.getOreIDs(wrappedItemStack))
                            {
                                String oreName = OreDictionary.getOreName(oreID);
                                if (!oreName.equals("Unknown"))
                                {
                                    WrappedStack oreStack = WrappedStack.wrap(new OreStack(oreName));

                                    if (oreStack != null && stackEnergyValueMap.containsKey(oreStack))
                                    {
                                        if (energyValue == null)
                                        {
                                            energyValue = stackEnergyValueMap.get(oreStack);
                                        }
                                        else if (!energyValue.equals(stackEnergyValueMap.get(oreStack)))
                                        {
                                            allHaveSameValueFlag = false;
                                        }
                                    }
                                    else
                                    {
                                        allHaveSameValueFlag = false;
                                    }
                                }
                                else
                                {
                                    allHaveSameValueFlag = false;
                                }
                            }

                            if (energyValue != null && allHaveSameValueFlag)
                            {
                                return energyValue;
                            }
                        }
                        else
                        {
                            /**
                             *  Scan the stack value map for ItemStacks that have the same Item. If one is found, check
                             *  if it has a wildcard meta value (and therefore is considered the same). Otherwise, check
                             *  if the ItemStack is "damageable" and calculate the value for the damaged stack.
                             */
                            for (WrappedStack valuedStack : stackEnergyValueMap.keySet())
                            {
                                if (valuedStack.getWrappedObject() instanceof ItemStack)
                                {
                                    ItemStack valuedItemStack = (ItemStack) valuedStack.getWrappedObject();

                                    if (Item.getIdFromItem(valuedItemStack.getItem()) == Item.getIdFromItem(wrappedItemStack.getItem()))
                                    {
                                        if (valuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || wrappedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE)
                                        {
                                            EnergyValue stackValue = stackEnergyValueMap.get(valuedStack);

                                            if (stackValue.compareTo(lowestValue) < 0)
                                            {
                                                lowestValue = stackValue;
                                            }
                                        }
                                        else if (wrappedItemStack.getItem().isDamageable() && wrappedItemStack.isItemDamaged())
                                        {
                                            EnergyValue stackValue = new EnergyValue(stackEnergyValueMap.get(valuedStack).getValue() * (1 - (wrappedItemStack.getItemDamage() * 1.0F / wrappedItemStack.getMaxDamage())));

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
                    else if (wrappedObject instanceof OreStack)
                    {
                        OreStack oreStack = (OreStack) wrappedObject;

                        if (CachedOreDictionary.getInstance().getItemStacksForOreName(oreStack.oreName).size() >= 1)
                        {
                            EnergyValue energyValue = null;
                            boolean allHaveSameValueFlag = true;

                            // Scan all valid ore dictionary values, if they ALL have the same value, then return it
                            for (ItemStack itemStack : CachedOreDictionary.getInstance().getItemStacksForOreName(oreStack.oreName))
                            {
                                WrappedStack wrappedItemStack = WrappedStack.wrap(itemStack);

                                if (wrappedItemStack != null && stackEnergyValueMap.containsKey(wrappedItemStack))
                                {
                                    if (energyValue == null)
                                    {
                                        energyValue = stackEnergyValueMap.get(wrappedItemStack);
                                    }
                                    else if (!energyValue.equals(stackEnergyValueMap.get(wrappedItemStack)))
                                    {
                                        allHaveSameValueFlag = false;
                                    }
                                }
                                else
                                {
                                    allHaveSameValueFlag = false;
                                }
                            }

                            if (energyValue != null && allHaveSameValueFlag)
                            {
                                return energyValue;
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
        if (!loadEnergyValueRegistryFromFile())
        {
            runDynamicEnergyValueResolution();
        }
        AbilityRegistry.getInstance().discoverAllLearnableItemStacks();
        this.shouldRegenNextRestart = false;
    }

    private void runDynamicEnergyValueResolution()
    {
        TreeMap<WrappedStack, EnergyValue> stackValueMap = new TreeMap<WrappedStack, EnergyValue>();

        /*
         *  Pre-assigned values
         */
        stackValueMap.putAll(preAssignedMappings);

        // Grab custom pre-assigned values from file
        Map<WrappedStack, EnergyValue> preAssignedValueMap = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.PRE_ASSIGNED_ENERGY_VALUES);
        for (WrappedStack wrappedStack : preAssignedValueMap.keySet())
        {
            if (preAssignedValueMap.get(wrappedStack) != null)
            {
                stackValueMap.put(wrappedStack, preAssignedValueMap.get(wrappedStack));
            }
        }

        /*
         *  Auto-assignment
         */
        // Initialize the maps for the first pass to happen
        ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        stackMappings = stackMappingsBuilder.build();
        Map<WrappedStack, EnergyValue> computedStackValues = new TreeMap<WrappedStack, EnergyValue>();

        // Initialize the pass counter
        int passNumber = 0;
        long computationStartTime = System.currentTimeMillis();
        long passStartTime;
        int computedValueCount = 0;
        int totalComputedValueCount = 0;
        LogHelper.info("Beginning dynamic value computation");
        boolean isFirstPass = true;
        while ((isFirstPass || computedValueCount > 0) && (passNumber < 16))
        {
            if (isFirstPass)
            {
                isFirstPass = false;
            }

            computedValueCount = 0;
            passStartTime = System.currentTimeMillis();
            // Increment the pass counter
            passNumber++;

            // Compute stack mappings from existing stack mappings
            computedStackValues = computeStackMappings(stackValueMap);

            for (WrappedStack keyStack : computedStackValues.keySet())
            {
                EnergyValue factoredExchangeEnergyValue = null;
                WrappedStack factoredKeyStack = null;

                if (keyStack != null && keyStack.getWrappedObject() != null && keyStack.getStackSize() > 0)
                {
                    if (computedStackValues.get(keyStack) != null && Float.compare(computedStackValues.get(keyStack).getValue(), 0f) > 0)
                    {
                        factoredExchangeEnergyValue = EnergyValueHelper.factorEnergyValue(computedStackValues.get(keyStack), keyStack.getStackSize());
                        factoredKeyStack = WrappedStack.wrap(keyStack, 1);
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
                        computedValueCount++;
                        totalComputedValueCount++;
                    }
                }
            }
            LogHelper.info(String.format("Pass %s: Computed %s values for objects in %s ms", passNumber, computedValueCount, System.currentTimeMillis() - passStartTime));
        }
        LogHelper.info(String.format("Finished dynamic value computation (computed %s values for objects in %s ms)", totalComputedValueCount, System.currentTimeMillis() - computationStartTime));

        if (postAssignedMappings != null)
        {
            for (WrappedStack wrappedStack : postAssignedMappings.keySet())
            {
                if (postAssignedMappings.get(wrappedStack) != null)
                {
                    stackValueMap.put(wrappedStack, postAssignedMappings.get(wrappedStack));
                }
            }
        }
        else
        {
            postAssignedMappings = new TreeMap<WrappedStack, EnergyValue>();
        }

        // Grab custom post-assigned values from file
        Map<WrappedStack, EnergyValue> postAssignedValueMap = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.POST_ASSIGNED_ENERGY_VALUES);
        for (WrappedStack wrappedStack : postAssignedValueMap.keySet())
        {
            if (postAssignedValueMap.get(wrappedStack) != null)
            {
                stackValueMap.put(wrappedStack, postAssignedValueMap.get(wrappedStack));
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
        generateValueStackMappings();

        // Serialize values to disk
        LogHelper.info("Saving energy values to disk");
        save();
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

    private Map<WrappedStack, EnergyValue> computeStackMappings(Map<WrappedStack, EnergyValue> stackValueMappings)
    {
        Map<WrappedStack, EnergyValue> computedStackMap = new TreeMap<WrappedStack, EnergyValue>();

        for (WrappedStack recipeOutput : RecipeRegistry.getInstance().getRecipeMappings().keySet())
        {
            if (!hasEnergyValue(recipeOutput.getWrappedObject(), false) && !computedStackMap.containsKey(recipeOutput))
            {
                EnergyValue lowestValue = null;

                for (List<WrappedStack> recipeInputs : RecipeRegistry.getInstance().getRecipeMappings().get(recipeOutput))
                {
                    EnergyValue computedValue = EnergyValueHelper.computeEnergyValueFromRecipe(stackValueMappings, recipeOutput, recipeInputs);

                    if (computedValue != null)
                    {
                        if (computedValue.compareTo(lowestValue) < 0)
                        {
                            lowestValue = computedValue;
                        }
                    }
                }

                if ((lowestValue != null) && (lowestValue.getValue() > 0f))
                {
                    computedStackMap.put(WrappedStack.wrap(recipeOutput.getWrappedObject()), lowestValue);
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

        if (valueMappings != null)
        {
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

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey("stackMappingList"))
        {
            TreeMap<WrappedStack, EnergyValue> stackValueMap = new TreeMap<WrappedStack, EnergyValue>();

            /**
             *  Read stack value mappings from NBTTagCompound
             */
            NBTTagList stackMappingTagList = nbtTagCompound.getTagList("stackMappingList", 10);
            for (int i = 0; i < stackMappingTagList.tagCount(); i++)
            {
                NBTTagCompound tagCompound = stackMappingTagList.getCompoundTagAt(i);
                WrappedStack wrappedStack = WrappedStack.fromNBTTagCompound(tagCompound.getCompoundTag("wrappedStack"));
                EnergyValue energyValue = EnergyValue.loadEnergyValueFromNBT(tagCompound.getCompoundTag("energyValue"));
                stackValueMap.put(wrappedStack, energyValue);
            }

            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(stackValueMap);
            stackMappings = stackMappingsBuilder.build();

            /**
             *  Resolve value stack mappings from the newly loaded stack mappings
             */
            generateValueStackMappings();
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        NBTTagList stackMappingTagList = new NBTTagList();
        for (WrappedStack wrappedStack : stackMappings.keySet())
        {
            if (wrappedStack != null && stackMappings.get(wrappedStack) != null)
            {
                NBTTagCompound stackMappingCompound = new NBTTagCompound();
                stackMappingCompound.setTag("wrappedStack", WrappedStack.toNBTTagCompound(wrappedStack));
                stackMappingCompound.setTag("energyValue", EnergyValue.writeEnergyValueToNBT(stackMappings.get(wrappedStack)));
                stackMappingTagList.appendTag(stackMappingCompound);
            }
        }
        nbtTagCompound.setTag("stackMappingList", stackMappingTagList);
        nbtTagCompound.setString("modListMD5", SerializationHelper.getModListMD5());
    }

    public void setEnergyValue(WrappedStack wrappedStack, EnergyValue energyValue)
    {
        if (wrappedStack != null && energyValue != null && Float.compare(energyValue.getValue(), 0f) > 0)
        {
            TreeMap<WrappedStack, EnergyValue> stackValueMap = new TreeMap<WrappedStack, EnergyValue>();

            /**
             *  Read stack value mappings from NBTTagCompound
             */
            stackValueMap.putAll(stackMappings);
            stackValueMap.put(wrappedStack, energyValue);

            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(stackValueMap);
            stackMappings = stackMappingsBuilder.build();

            /**
             *  Resolve value stack mappings from the newly loaded stack mappings
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
        File energyValuesDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + Reference.LOWERCASE_MOD_ID + File.separator + "energyvalues");
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
            SerializationHelper.compressEnergyValueStackMapToFile(new File(energyValuesDataDirectory, Files.STATIC_ENERGY_VALUES_JSON), energyValueRegistry.stackMappings);
            SerializationHelper.compressEnergyValueStackMapToFile(new File(energyValuesDataDirectory, SerializationHelper.getModListMD5() + ".json.gz"), energyValueRegistry.stackMappings);
        }
    }

    public boolean loadEnergyValueRegistryFromFile()
    {
        File energyValuesDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + Reference.LOWERCASE_MOD_ID + File.separator + "energyvalues");
        energyValuesDataDirectory.mkdirs();

        File staticEnergyValuesFile = new File(energyValuesDataDirectory, Files.STATIC_ENERGY_VALUES_JSON);
        File md5EnergyValuesFile = new File(energyValuesDataDirectory, SerializationHelper.getModListMD5() + ".json.gz");

        Map<WrappedStack, EnergyValue> stackValueMap = null;

        if (!Settings.DynamicEnergyValueGeneration.regenerateEnergyValuesWhen.equalsIgnoreCase("Always"))
        {
            if (md5EnergyValuesFile.exists())
            {
                LogHelper.info("Attempting to load energy values from file: " + md5EnergyValuesFile.getAbsolutePath());
                stackValueMap = SerializationHelper.decompressEnergyValueStackMapFromFile(md5EnergyValuesFile);
            }

            if (staticEnergyValuesFile.exists())
            {
                LogHelper.info("Attempting to load energy values from file: " + staticEnergyValuesFile.getAbsolutePath());
                stackValueMap = SerializationHelper.decompressEnergyValueStackMapFromFile(staticEnergyValuesFile);
            }

            if (stackValueMap != null)
            {
                LogHelper.info("Successfully loaded energy values from file");
                for (WrappedStack wrappedStack : stackValueMap.keySet())
                {
                    LogHelper.info(String.format("Stack: %s, Value: %s", wrappedStack, stackValueMap.get(wrappedStack)));
                }
                return false;
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
        return prettyJsonSerializer.toJson(this);
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
                EnergyValueStackMapping energyValueStackMapping = new EnergyValueStackMapping().deserialize(jsonElement, typeOfT, context);

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
            jsonEnergyValueRegistry.add(EnergyValueStackMapping.jsonSerializer.toJsonTree(new EnergyValueStackMapping(wrappedStack, energyValueRegistry.stackMappings.get(wrappedStack))));
        }

        return jsonEnergyValueRegistry;
    }

    public void dumpEnergyValueRegistryToLog()
    {
        dumpEnergyValueRegistryToLog(EnergyValueRegistryProxy.Phase.ALL);
    }

    public void dumpEnergyValueRegistryToLog(EnergyValueRegistryProxy.Phase phase)
    {
        LogHelper.info(String.format("BEGIN DUMPING %s ENERGY VALUE MAPPINGS", phase));
        if (phase == EnergyValueRegistryProxy.Phase.PRE_ASSIGNMENT)
        {
            for (WrappedStack wrappedStack : this.preAssignedMappings.keySet())
            {
                LogHelper.info(String.format("- Object: %s, Value: %s", wrappedStack, EnergyValueRegistry.getInstance().getStackValueMap().get(wrappedStack)));
            }
        }
        else if (phase == EnergyValueRegistryProxy.Phase.POST_ASSIGNMENT)
        {
            if (this.postAssignedMappings != null)
            {
                for (WrappedStack wrappedStack : this.postAssignedMappings.keySet())
                {
                    LogHelper.info(String.format("- Object: %s, Value: %s", wrappedStack, EnergyValueRegistry.getInstance().getStackValueMap().get(wrappedStack)));
                }
            }
        }
        else if (phase == EnergyValueRegistryProxy.Phase.ALL)
        {
            for (WrappedStack wrappedStack : EnergyValueRegistry.getInstance().getStackValueMap().keySet())
            {
                LogHelper.info(String.format("- Object: %s, Value: %s", wrappedStack, EnergyValueRegistry.getInstance().getStackValueMap().get(wrappedStack)));
            }
        }
        LogHelper.info(String.format("END DUMPING %s ENERGY VALUE MAPPINGS", phase));
    }
}

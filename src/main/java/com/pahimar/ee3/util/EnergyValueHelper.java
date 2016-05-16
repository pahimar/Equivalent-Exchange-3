package com.pahimar.ee3.util;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.IEnergyValueProvider;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class EnergyValueHelper {

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
    public static EnergyValue getEnergyValue(Map<WrappedStack, EnergyValue> valueMap, Object object, boolean strict) {

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

    public static EnergyValue computeEnergyValueFromRecipe(Map<WrappedStack, EnergyValue> stackValueMappings, WrappedStack outputStack, List<WrappedStack> inputStacks)
    {
        float computedValue = 0f;

        for (WrappedStack wrappedStack : inputStacks)
        {
            EnergyValue wrappedStackValue;
            int stackSize = -1;
            if (wrappedStack.getWrappedObject() instanceof ItemStack)
            {
                ItemStack itemStack = (ItemStack) wrappedStack.getWrappedObject();

                // Check if we are dealing with a potential fluid
                if (FluidContainerRegistry.getFluidForFilledItem(itemStack) != null)
                {
                    if (itemStack.getItem().getContainerItem(itemStack) != null)
                    {
                        stackSize = FluidContainerRegistry.getFluidForFilledItem(itemStack).amount * wrappedStack.getStackSize();
                        wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValueFromMap(stackValueMappings, FluidContainerRegistry.getFluidForFilledItem(itemStack));
                    }
                    else
                    {
                        wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValueFromMap(stackValueMappings, wrappedStack);
                    }
                }
                else if (itemStack.getItem().getContainerItem(itemStack) != null)
                {
                    ItemStack containerItemStack = itemStack.getItem().getContainerItem(itemStack);

                    if (EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && EnergyValueRegistry.getInstance().hasEnergyValue(containerItemStack))
                    {
                        float itemStackValue = EnergyValueRegistry.getInstance().getEnergyValueFromMap(stackValueMappings, itemStack).getValue();
                        float containerStackValue = EnergyValueRegistry.getInstance().getEnergyValueFromMap(stackValueMappings, containerItemStack).getValue();
                        wrappedStackValue = new EnergyValue(itemStackValue - containerStackValue);
                    }
                    else
                    {
                        wrappedStackValue = new EnergyValue(0);
                    }
                }
                else if (!itemStack.getItem().doesContainerItemLeaveCraftingGrid(itemStack))
                {
                    wrappedStackValue = new EnergyValue(0);
                }
                else if (OreDictionary.getOreIDs(itemStack).length > 0)
                {
                    wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValueFromMap(stackValueMappings, wrappedStack, true);
                }
                else
                {
                    wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValueFromMap(stackValueMappings, wrappedStack);
                }
            }
            else if (wrappedStack.getWrappedObject() instanceof OreStack)
            {
                OreStack oreStack = (OreStack) wrappedStack.getWrappedObject();
                wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValueFromMap(stackValueMappings, wrappedStack);
                for (ItemStack itemStack : OreDictionary.getOres(oreStack.oreName))
                {
                    if (!itemStack.getItem().doesContainerItemLeaveCraftingGrid(itemStack))
                    {
                        wrappedStackValue = new EnergyValue(0);
                    }
                }
            }
            else
            {
                wrappedStackValue = EnergyValueRegistry.getInstance().getEnergyValueFromMap(stackValueMappings, wrappedStack);
            }

            if (wrappedStackValue != null)
            {
                if (stackSize == -1)
                {
                    stackSize = wrappedStack.getStackSize();
                }

                computedValue += wrappedStackValue.getValue() * stackSize;
            }
            else
            {
                return null;
            }
        }

        return factor(new EnergyValue(computedValue), outputStack.getStackSize());
    }

    public static EnergyValue factor(EnergyValue energyValue, int factor)
    {
        return factor(energyValue, (float) factor);
    }

    public static EnergyValue factor(EnergyValue energyValue, float factor)
    {
        if ((Float.compare(factor, 0f) != 0) && (energyValue != null))
        {
            return new EnergyValue(new BigDecimal(energyValue.getValue() * 1f / factor).setScale(3, BigDecimal.ROUND_HALF_EVEN).floatValue());
        }
        else
        {
            return null;
        }
    }
} 

package com.pahimar.ee3.emc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.pahimar.ee3.core.util.EnergyStack;
import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcRegistry {

    private static EmcRegistry emcRegistry = null;

    private Map<CustomWrappedStack, EmcValue> stackMappings;
    private TreeMap<EmcValue, List<CustomWrappedStack>> valueMappings;

    private EmcRegistry() {

        stackMappings = new HashMap<CustomWrappedStack, EmcValue>();
        valueMappings = new TreeMap<EmcValue, List<CustomWrappedStack>>();
    }

    public static EmcRegistry getInstance() {

        if (emcRegistry == null) {
            emcRegistry = new EmcRegistry();
            emcRegistry.init();
        }

        return emcRegistry;
    }

    private void init() {

        // Grab the default value map
        Map<CustomWrappedStack, EmcValue> defaultValueMap = EmcDefaultValues.getInstance().getDefaultValueMap();
        
        // stackMappings.put(new CustomWrappedStack(), new EmcValue());
        
        // Vanilla Smelting Energy
        stackMappings.put(new CustomWrappedStack(Block.cobblestone), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        stackMappings.put(new CustomWrappedStack(Block.wood), new EmcValue(32, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        stackMappings.put(new CustomWrappedStack(Block.oreIron), new EmcValue(256, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        stackMappings.put(new CustomWrappedStack(Block.oreGold), new EmcValue(2048, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        stackMappings.put(new CustomWrappedStack(Block.oreDiamond), new EmcValue(8192, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        stackMappings.put(new CustomWrappedStack(Item.coal), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.KINETIC, 1))));
        
        stackMappings.put(
                new CustomWrappedStack(new EnergyStack(EnergyStack.VANILLA_SMELTING_ENERGY_NAME)), 
                new EmcValue(
                        getEmcValue(new CustomWrappedStack(Item.coal)).getComponentValueByType(EmcType.KINETIC) / (8 * EnergyStack.VANILLA_SMELTING_ENERGY_THRESHOLD), 
                        EmcComponent.KINETIC_UNIT_COMPONENT));
    }
    
    public boolean hasEmcValue(CustomWrappedStack wrappedStack) {
        
        return stackMappings.containsKey(wrappedStack) ? stackMappings.get(wrappedStack) instanceof EmcValue : false;
    }
    
    public boolean hasStacksForValue(EmcValue emcValue) {
        
        return valueMappings.containsKey(emcValue) ? valueMappings.get(emcValue).size() > 0 : false;
    }
    
    public EmcValue getEmcValue(CustomWrappedStack wrappedStack) {
        
        return stackMappings.get(wrappedStack);
    }
    
    public List<CustomWrappedStack> getStacksFromValue(EmcValue emcValue) {
        
        return valueMappings.get(emcValue);
    }
    
    public List<CustomWrappedStack> getStacksInEmcRange(EmcValue fromValue, EmcValue toValue) {
        
        return null;
    }
    
    public void addEmcValueMapping(CustomWrappedStack wrappedStack, EmcValue emcValue) {
        
        
    }
    
    public static void printDebugDump() {
        
        Iterator<CustomWrappedStack> stackIter = EmcRegistry.getInstance().stackMappings.keySet().iterator();
        CustomWrappedStack stack = null;
        while (stackIter.hasNext()) {
            stack = stackIter.next();
            LogHelper.debug(stack + ": " + EmcRegistry.getInstance().stackMappings.get(stack));
        }
    }
}

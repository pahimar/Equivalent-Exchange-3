package com.pahimar.ee3.emc;

import net.minecraft.block.Block;

import com.google.common.collect.ImmutableMap;
import com.pahimar.ee3.core.util.EnergyStack;
import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcDefaultValues {
    
    private static EmcDefaultValues defaultValues = null;
    
    private ImmutableMap<CustomWrappedStack, EmcValue> defaultValueMap;

    private EmcDefaultValues() {

    }
    
    public static EmcDefaultValues getInstance() {
        
        if (defaultValues == null) {
            defaultValues = new EmcDefaultValues();
            defaultValues.init();
        }
        
        return defaultValues;
    }
    
    private void init() {
        
        ImmutableMap.Builder<CustomWrappedStack, EmcValue> valueMapBuilder = ImmutableMap.builder();
        
        valueMapBuilder.put(new CustomWrappedStack(new EnergyStack(EnergyStack.VANILLA_SMELTING_ENERGY_NAME)), 
                new EmcValue((32 * 0.2F / 1600), EmcComponent.KINETIC_UNIT_COMPONENT));
        
        valueMapBuilder.put(new CustomWrappedStack(Block.cobblestone), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMapBuilder.put(new CustomWrappedStack(Block.wood), new EmcValue(32, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMapBuilder.put(new CustomWrappedStack(Block.oreIron), new EmcValue(256, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMapBuilder.put(new CustomWrappedStack(Block.oreGold), new EmcValue(2048, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMapBuilder.put(new CustomWrappedStack(Block.oreDiamond), new EmcValue(8192, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        
        defaultValueMap = valueMapBuilder.build();
    }
    
    public ImmutableMap<CustomWrappedStack, EmcValue> getDefaultValueMap() {
        
        return defaultValueMap;
    }
}

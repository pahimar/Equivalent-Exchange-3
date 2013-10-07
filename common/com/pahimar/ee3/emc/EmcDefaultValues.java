package com.pahimar.ee3.emc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;

import com.pahimar.ee3.core.util.EnergyStack;
import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcDefaultValues {

    private static EmcDefaultValues defaultValues = null;
    private Map<CustomWrappedStack, EmcValue> defaultValueMap;

    private EmcDefaultValues() {

        defaultValueMap = new HashMap<CustomWrappedStack, EmcValue>();
    }

    public static EmcDefaultValues getInstance() {

        if (defaultValues == null) {
            defaultValues = new EmcDefaultValues();
            defaultValues.init();
        }

        return defaultValues;
    }

    private void init() {

        defaultValueMap.put(new CustomWrappedStack(Block.cobblestone), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        defaultValueMap.put(new CustomWrappedStack(Block.wood), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.ESSENTIA, 1))));
        defaultValueMap.put(new CustomWrappedStack(Block.oreIron), new EmcValue(256, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        defaultValueMap.put(new CustomWrappedStack(Block.oreGold), new EmcValue(2048, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        defaultValueMap.put(new CustomWrappedStack(Block.oreDiamond), new EmcValue(8192, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        defaultValueMap.put(new CustomWrappedStack(Block.oreCoal), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.KINETIC, 1))));

        defaultValueMap.put(new CustomWrappedStack(new EnergyStack(EnergyStack.VANILLA_SMELTING_ENERGY_NAME)), new EmcValue(defaultValueMap.get(new CustomWrappedStack(Block.oreCoal)).getComponentValueByType(EmcType.KINETIC) / (8 * EnergyStack.VANILLA_SMELTING_ENERGY_THRESHOLD), EmcComponent.KINETIC_UNIT_COMPONENT));
    }

    public Map<CustomWrappedStack, EmcValue> getDefaultValueMap() {

        return defaultValueMap;
    }
}

package com.pahimar.ee3.emc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.OreStack;

public class EmcDefaultValues {

    private static EmcDefaultValues emcDefaultValues = null;
    private Map<CustomWrappedStack, EmcValue> valueMap;

    private EmcDefaultValues() {

        valueMap = new HashMap<CustomWrappedStack, EmcValue>();
    }

    private static void lazyInit() {

        if (emcDefaultValues == null) {
            emcDefaultValues = new EmcDefaultValues();
            emcDefaultValues.init();
        }
    }

    private void init() {

        valueMap.put(new CustomWrappedStack(Block.stone), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.grass), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.ESSENTIA_UNIT_COMPONENT)));
        valueMap.put(new CustomWrappedStack(Block.dirt), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.cobblestone), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.sand), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.AMORPHOUS_UNIT_COMPONENT)));
        valueMap.put(new CustomWrappedStack(Block.leaves), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.ESSENTIA_UNIT_COMPONENT)));
        valueMap.put(new CustomWrappedStack(Block.glass), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.glass.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.tallGrass), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.ESSENTIA_UNIT_COMPONENT)));
        for (int meta = 0; meta < 16; meta++) {
            valueMap.put(new CustomWrappedStack(new ItemStack(Block.tallGrass.blockID, 1, meta)), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.ESSENTIA_UNIT_COMPONENT)));
        }
        valueMap.put(new CustomWrappedStack(Block.deadBush), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.ice), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));

        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.wood))), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), EmcComponent.ESSENTIA_UNIT_COMPONENT)));
        valueMap.put(new CustomWrappedStack(Block.oreIron), new EmcValue(256, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.oreGold), new EmcValue(2048, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.oreDiamond), new EmcValue(8192, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Item.diamond), new EmcValue(8192, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.oreCoal), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.KINETIC, 1))));

    }

    public static Map<CustomWrappedStack, EmcValue> getDefaultValueMap() {

        lazyInit();
        return emcDefaultValues.valueMap;
    }
}

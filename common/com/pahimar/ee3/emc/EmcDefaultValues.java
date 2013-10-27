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

        valueMap.put(new CustomWrappedStack(Block.stone), new EmcValue(1, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.stone))), new EmcValue(1, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.waterStill), new EmcValue(0.1f, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.AMORPHOUS, 1))));
        valueMap.put(new CustomWrappedStack(Block.grass), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(Block.dirt), new EmcValue(1, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.cobblestone), new EmcValue(1, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.sand.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.AMORPHOUS, 1))));
        valueMap.put(new CustomWrappedStack(Block.leaves), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(Block.glass), new EmcValue(1, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.glass.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.tallGrass), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.tallGrass.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(Block.deadBush), new EmcValue(1, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.ice), new EmcValue(1, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.sandStone.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(4, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.wood))), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.planks))), new EmcValue(8, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.ingotIron), new EmcValue(256, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.oreIron), new EmcValue(256, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.ingotGold), new EmcValue(2048, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.oreGold), new EmcValue(2048, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.oreDiamond), new EmcValue(8192, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.diamond), new EmcValue(8192, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.oreCoal), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.KINETIC, 1))));
        valueMap.put(new CustomWrappedStack(new ItemStack(Item.dyePowder, 1, 4)), new EmcValue(864, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Item.dyePowder, 1, 0))), new EmcValue(8, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.silk), new EmcValue(12, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.cloth.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(48, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.gravel), new EmcValue(4, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.AMORPHOUS, 1))));
        
        valueMap.put(new CustomWrappedStack(Item.reed), new EmcValue(32, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.paper), new EmcValue(32, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.redstone), new EmcValue(32, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.oreRedstone), new EmcValue(32, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Block.oreRedstoneGlowing), new EmcValue(32, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.flint), new EmcValue(4, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.clay), new EmcValue(64, EmcType.CORPOREAL));
        
        
        // Potion reagents
        //valueMap.put(new CustomWrappedStack(Item.glassBottle), new EmcValue(1, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.spiderEye), new EmcValue(128, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.appleRed), new EmcValue(128, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(Item.fermentedSpiderEye), new EmcValue(192, EmcType.CORPOREAL));
        
        // Single slabs
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.stoneSingleSlab, 1, 0)), new EmcValue(0.5f, EmcType.CORPOREAL));

    }

    public static Map<CustomWrappedStack, EmcValue> getDefaultValueMap() {

        lazyInit();
        return emcDefaultValues.valueMap;
    }
}

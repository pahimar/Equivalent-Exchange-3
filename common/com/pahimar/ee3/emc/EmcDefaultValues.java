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

        // OreDictionary assignment
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.cobblestone))), new EmcValue(1));
        for (int meta = 0; meta < 16; meta++) {
            valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Item.dyePowder, 1, meta))), new EmcValue(8));
        }
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.wood))), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.oreDiamond))), new EmcValue(8192));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.oreEmerald))), new EmcValue(8192));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.oreGold))), new EmcValue(2048));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.oreIron))), new EmcValue(256));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.oreLapis))), new EmcValue(864));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.oreNetherQuartz))), new EmcValue(256));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.oreRedstone))), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.oreRedstoneGlowing))), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.planks))), new EmcValue(8));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Item.record11))), new EmcValue(2048));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.woodSingleSlab))), new EmcValue(4));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.stairsWoodOak))), new EmcValue(12));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Item.stick))), new EmcValue(4));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.stone))), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.leaves))), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(new OreStack(new ItemStack(Block.sapling))), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.ESSENTIA, 1))));
        
        // To be sorted        
        valueMap.put(new CustomWrappedStack(Block.waterStill), new EmcValue(0.1f, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.AMORPHOUS, 1))));        
        valueMap.put(new CustomWrappedStack(Block.tallGrass), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.tallGrass.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(Block.deadBush), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Item.ingotIron), new EmcValue(256));
        valueMap.put(new CustomWrappedStack(Item.ingotGold), new EmcValue(2048));
        valueMap.put(new CustomWrappedStack(Item.diamond), new EmcValue(8192));
        valueMap.put(new CustomWrappedStack(Item.silk), new EmcValue(12));
        valueMap.put(new CustomWrappedStack(Item.redstone), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(Block.mushroomBrown), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(Block.mushroomRed), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(Item.slimeBall), new EmcValue(24));
        
        /* Building Blocks */
        valueMap.put(new CustomWrappedStack(Block.stone), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.grass), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(Block.dirt), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.cobblestone), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.sand), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.AMORPHOUS, 1))));
        valueMap.put(new CustomWrappedStack(Block.gravel), new EmcValue(4, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.AMORPHOUS, 1))));
        valueMap.put(new CustomWrappedStack(Block.oreCoal), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.KINETIC, 1))));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.glass.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.sandStone.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(4, EmcType.CORPOREAL));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.cloth.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(48));
        // Moss Stone (48:0)
        // Obsidian (49:0)
        valueMap.put(new CustomWrappedStack(Block.ice), new EmcValue(1));
        // Snow (80:0)
        valueMap.put(new CustomWrappedStack(Block.pumpkin), new EmcValue(144));
        valueMap.put(new CustomWrappedStack(Block.netherrack), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.slowSand), new EmcValue(49));
        // Mossy Stone Bricks (98:1)
        // Cracked Stone Bricks (98:2)
        // Chiseled Stone Bricks (98:3)
        // Mycelium (110:0)
        // End Stone (121:0)
        // Mossy Cobblestone Wall (139:1)
        valueMap.put(new CustomWrappedStack(Block.hardenedClay), new EmcValue(256));
        
        /* Decoration Blocks */
        
        /* Redstone */
        
        /* Transportation */
        
        /* Miscellaneous */
        
        /* Foodstuffs */
        valueMap.put(new CustomWrappedStack(Item.appleRed), new EmcValue(128));
        valueMap.put(new CustomWrappedStack(Item.porkRaw), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.porkCooked), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.fishRaw), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.fishCooked), new EmcValue(64));
        // Cake
        // Cookie
        valueMap.put(new CustomWrappedStack(Item.melon), new EmcValue(16));
        valueMap.put(new CustomWrappedStack(Item.beefRaw), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.beefCooked), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.chickenRaw), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.chickenCooked), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.rottenFlesh), new EmcValue(24));
        valueMap.put(new CustomWrappedStack(Item.spiderEye), new EmcValue(128));
        valueMap.put(new CustomWrappedStack(Item.carrot), new EmcValue(24));
        valueMap.put(new CustomWrappedStack(Item.potato), new EmcValue(24));
        valueMap.put(new CustomWrappedStack(Item.bakedPotato), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.poisonousPotato), new EmcValue(24));
        
        /* Tools */
        
        /* Combat */
        
        /* Brewing */
        valueMap.put(new CustomWrappedStack(Item.ghastTear), new EmcValue(4096));
        
        /* Materials */
        valueMap.put(new CustomWrappedStack(new ItemStack(Item.coal, 1, 0)), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(new ItemStack(Item.coal, 1, 1)), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(Item.feather), new EmcValue(48));
        valueMap.put(new CustomWrappedStack(Item.gunpowder), new EmcValue(192));
        valueMap.put(new CustomWrappedStack(Item.seeds), new EmcValue(16));
        valueMap.put(new CustomWrappedStack(Item.wheat), new EmcValue(24));
        valueMap.put(new CustomWrappedStack(Item.flint), new EmcValue(4));
        valueMap.put(new CustomWrappedStack(Item.leather), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.brick), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.clay), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.reed), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(Item.egg), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(Item.glowstone), new EmcValue(384));
        
        valueMap.put(new CustomWrappedStack(new ItemStack(Item.dyePowder, 1, 4)), new EmcValue(864));
        valueMap.put(new CustomWrappedStack(Item.blazeRod), new EmcValue(1536));
        valueMap.put(new CustomWrappedStack(Item.netherStalkSeeds), new EmcValue(24));
        valueMap.put(new CustomWrappedStack(Item.emerald), new EmcValue(8192));
        // Nether Star
        valueMap.put(new CustomWrappedStack(Item.netherrackBrick), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Item.netherQuartz), new EmcValue(256));
        
        /* Equivalent Exchange 3 */
    }

    public static Map<CustomWrappedStack, EmcValue> getDefaultValueMap() {

        lazyInit();
        return emcDefaultValues.valueMap;
    }
}

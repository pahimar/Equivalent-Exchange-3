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
        
        // Fluids      
        valueMap.put(new CustomWrappedStack(Block.waterStill), new EmcValue(0.1f, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 1), new EmcComponent(EmcType.AMORPHOUS, 1))));
        valueMap.put(new CustomWrappedStack(Block.lavaStill), new EmcValue(64, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.AMORPHOUS, 1))));

        /* Building Blocks */
        valueMap.put(new CustomWrappedStack(Block.stone), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.grass), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(Block.dirt), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.cobblestone), new EmcValue(1));
        // Bedrock (7:0)
        valueMap.put(new CustomWrappedStack(Block.sand), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.AMORPHOUS, 1))));
        valueMap.put(new CustomWrappedStack(Block.gravel), new EmcValue(4, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.AMORPHOUS, 1))));
        valueMap.put(new CustomWrappedStack(Block.oreCoal), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.KINETIC, 1))));
        // Sponge (19:0)
        valueMap.put(new CustomWrappedStack(Block.glass), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.sandStone), new EmcValue(4));
        valueMap.put(new CustomWrappedStack(Block.cobblestoneMossy), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.obsidian), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Block.ice), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.pumpkin), new EmcValue(144));
        valueMap.put(new CustomWrappedStack(Block.netherrack), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.slowSand), new EmcValue(49));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.stoneBrick, 1, 1)), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.stoneBrick, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.mycelium), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.whiteStone), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.hardenedClay), new EmcValue(256));
        
        /* Decoration Blocks */
        valueMap.put(new CustomWrappedStack(Block.web), new EmcValue(12));
        valueMap.put(new CustomWrappedStack(new ItemStack(Block.tallGrass.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), new EmcComponent(EmcType.ESSENTIA, 1))));
        valueMap.put(new CustomWrappedStack(Block.deadBush), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Block.plantYellow), new EmcValue(16));
        valueMap.put(new CustomWrappedStack(Block.plantRed), new EmcValue(16));
        valueMap.put(new CustomWrappedStack(Block.mushroomBrown), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(Block.mushroomRed), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(Block.snow), new EmcValue(0.5f));
        valueMap.put(new CustomWrappedStack(Block.cactus), new EmcValue(8));
        // Stone Monster Egg (97:0)
        // Cobblestone Monster Egg (97:1)
        // Stone Brick Monster Egg (97:2)
        valueMap.put(new CustomWrappedStack(Block.vine), new EmcValue(8));
        valueMap.put(new CustomWrappedStack(Block.waterlily), new EmcValue(16));
        // End Portal (120:0)
        // Skeleton Skull (397:0)
        // Wither Skeleton Skull (391:1)
        // Zombie Head (397:2)
        // Head (397:3)
        // Creeper Head (397:4)
        
        /* Redstone */
        valueMap.put(new CustomWrappedStack(Item.redstone), new EmcValue(32));
        
        /* Transportation */
        valueMap.put(new CustomWrappedStack(Item.saddle), new EmcValue(192));
        
        /* Miscellaneous */
        valueMap.put(new CustomWrappedStack(Item.snowball), new EmcValue(0.25f));
        valueMap.put(new CustomWrappedStack(Item.bucketMilk), new EmcValue(832)); 
        valueMap.put(new CustomWrappedStack(Item.slimeBall), new EmcValue(24));
        valueMap.put(new CustomWrappedStack(Item.bone), new EmcValue(24));
        valueMap.put(new CustomWrappedStack(Item.enderPearl), new EmcValue(1024));
        // Bottle o'Enchanting (384:0)
        // Firework Star (402:0)
        
        /* Foodstuffs */
        valueMap.put(new CustomWrappedStack(Item.appleRed), new EmcValue(128));
        valueMap.put(new CustomWrappedStack(Item.porkRaw), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.porkCooked), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.fishRaw), new EmcValue(64));
        valueMap.put(new CustomWrappedStack(Item.fishCooked), new EmcValue(64));
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
        // Name Tag (421:0)
        
        /* Combat */
        // Chain Helmet (302:0)
        // Chain Chestplate (303:0)
        // Chain Leggings (304:0)
        // Chain Boots (305:0)
        
        /* Brewing */
        valueMap.put(new CustomWrappedStack(Item.ghastTear), new EmcValue(4096));
        
        /* Materials */
        valueMap.put(new CustomWrappedStack(new ItemStack(Item.coal, 1, 0)), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(new ItemStack(Item.coal, 1, 1)), new EmcValue(32));
        valueMap.put(new CustomWrappedStack(Item.diamond), new EmcValue(8192));
        valueMap.put(new CustomWrappedStack(Item.ingotIron), new EmcValue(256));
        valueMap.put(new CustomWrappedStack(Item.ingotGold), new EmcValue(2048));
        valueMap.put(new CustomWrappedStack(Item.silk), new EmcValue(12));
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
        valueMap.put(new CustomWrappedStack(Item.netherStar), new EmcValue(24576));
        valueMap.put(new CustomWrappedStack(Item.netherrackBrick), new EmcValue(1));
        valueMap.put(new CustomWrappedStack(Item.netherQuartz), new EmcValue(256));
        
        /* Equivalent Exchange 3 */
    }

    public static Map<CustomWrappedStack, EmcValue> getDefaultValueMap() {

        lazyInit();
        return emcDefaultValues.valueMap;
    }
}

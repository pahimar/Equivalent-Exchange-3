package com.pahimar.ee3.emc;

import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

public class EmcValuesDefault
{
    private static EmcValuesDefault emcDefaultValues = null;
    private Map<WrappedStack, EmcValue> valueMap;

    private EmcValuesDefault()
    {
        valueMap = new HashMap<WrappedStack, EmcValue>();
    }

    private static void lazyInit()
    {
        if (emcDefaultValues == null)
        {
            emcDefaultValues = new EmcValuesDefault();
            emcDefaultValues.init();
        }
    }

    private void init()
    {
        // OreDictionary assignment
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.cobblestone))), new EmcValue(1));
        for (int meta = 0; meta < 16; meta++)
        {
            valueMap.put(new WrappedStack(new OreStack(new ItemStack(Item.dyePowder, 1, meta))), new EmcValue(8));
        }
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.wood))), new EmcValue(32));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.oreDiamond))), new EmcValue(8192));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.oreEmerald))), new EmcValue(8192));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.oreGold))), new EmcValue(2048));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.oreIron))), new EmcValue(256));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.oreLapis))), new EmcValue(864));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.oreNetherQuartz))), new EmcValue(256));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.oreRedstone))), new EmcValue(32));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.oreRedstoneGlowing))), new EmcValue(32));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.planks))), new EmcValue(8));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Item.record11))), new EmcValue(2048));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.woodSingleSlab))), new EmcValue(4));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.stairsWoodOak))), new EmcValue(12));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Item.stick))), new EmcValue(4));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.stone))), new EmcValue(1));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.leaves))), new EmcValue(1));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Block.sapling))), new EmcValue(32));

        // Fluids
        valueMap.put(new WrappedStack(FluidRegistry.WATER), new EmcValue(1));
        valueMap.put(new WrappedStack(FluidRegistry.LAVA), new EmcValue(64));
        valueMap.put(new WrappedStack(FluidRegistry.getFluid("milk")), new EmcValue(64));

        /* Building Blocks */
        valueMap.put(new WrappedStack(Block.stone), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.grass), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.dirt), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.cobblestone), new EmcValue(1));
        // Bedrock (7:0)
        valueMap.put(new WrappedStack(Block.sand), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.gravel), new EmcValue(4));
        valueMap.put(new WrappedStack(Block.oreCoal), new EmcValue(32));
        // Sponge (19:0)
        valueMap.put(new WrappedStack(Block.glass), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.sandStone), new EmcValue(4));
        valueMap.put(new WrappedStack(Block.cobblestoneMossy), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.obsidian), new EmcValue(64));
        valueMap.put(new WrappedStack(Block.ice), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.pumpkin), new EmcValue(144));
        valueMap.put(new WrappedStack(Block.netherrack), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.slowSand), new EmcValue(49));
        valueMap.put(new WrappedStack(new ItemStack(Block.stoneBrick, 1, 1)), new EmcValue(1));
        valueMap.put(new WrappedStack(new ItemStack(Block.stoneBrick, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.mycelium), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.whiteStone), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.hardenedClay), new EmcValue(256));
        
        /* Decoration Blocks */
        valueMap.put(new WrappedStack(Block.web), new EmcValue(12));
        valueMap.put(new WrappedStack(new ItemStack(Block.tallGrass.blockID, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.deadBush), new EmcValue(1));
        valueMap.put(new WrappedStack(Block.plantYellow), new EmcValue(16));
        valueMap.put(new WrappedStack(Block.plantRed), new EmcValue(16));
        valueMap.put(new WrappedStack(Block.mushroomBrown), new EmcValue(32));
        valueMap.put(new WrappedStack(Block.mushroomRed), new EmcValue(32));
        valueMap.put(new WrappedStack(Block.snow), new EmcValue(0.5f));
        valueMap.put(new WrappedStack(Block.cactus), new EmcValue(8));
        // Stone Monster Egg (97:0)
        // Cobblestone Monster Egg (97:1)
        // Stone Brick Monster Egg (97:2)
        valueMap.put(new WrappedStack(Block.vine), new EmcValue(8));
        valueMap.put(new WrappedStack(Block.waterlily), new EmcValue(16));
        // End Portal (120:0)
        // Skeleton Skull (397:0)
        // Wither Skeleton Skull (391:1)
        // Zombie Head (397:2)
        // Head (397:3)
        // Creeper Head (397:4)
        
        /* Redstone */
        valueMap.put(new WrappedStack(Item.redstone), new EmcValue(32));
        
        /* Transportation */
        valueMap.put(new WrappedStack(Item.saddle), new EmcValue(192));
        
        /* Miscellaneous */
        valueMap.put(new WrappedStack(Item.snowball), new EmcValue(0.25f));
        valueMap.put(new WrappedStack(Item.slimeBall), new EmcValue(24));
        valueMap.put(new WrappedStack(Item.bone), new EmcValue(24));
        valueMap.put(new WrappedStack(Item.enderPearl), new EmcValue(1024));
        // Bottle o'Enchanting (384:0)
        // Firework Star (402:0)
        
        /* Foodstuffs */
        valueMap.put(new WrappedStack(Item.appleRed), new EmcValue(128));
        valueMap.put(new WrappedStack(Item.porkRaw), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.porkCooked), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.fishRaw), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.fishCooked), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.melon), new EmcValue(16));
        valueMap.put(new WrappedStack(Item.beefRaw), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.beefCooked), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.chickenRaw), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.chickenCooked), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.rottenFlesh), new EmcValue(24));
        valueMap.put(new WrappedStack(Item.spiderEye), new EmcValue(128));
        valueMap.put(new WrappedStack(Item.carrot), new EmcValue(24));
        valueMap.put(new WrappedStack(Item.potato), new EmcValue(24));
        valueMap.put(new WrappedStack(Item.bakedPotato), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.poisonousPotato), new EmcValue(24));
        
        /* Tools */
        // Name Tag (421:0)
        
        /* Combat */
        // Chain Helmet (302:0)
        // Chain Chestplate (303:0)
        // Chain Leggings (304:0)
        // Chain Boots (305:0)
        
        /* Brewing */
        valueMap.put(new WrappedStack(Item.ghastTear), new EmcValue(4096));
        
        /* Materials */
        valueMap.put(new WrappedStack(new ItemStack(Item.coal, 1, 0)), new EmcValue(32));
        valueMap.put(new WrappedStack(new ItemStack(Item.coal, 1, 1)), new EmcValue(32));
        valueMap.put(new WrappedStack(Item.diamond), new EmcValue(8192));
        valueMap.put(new WrappedStack(Item.ingotIron), new EmcValue(256));
        valueMap.put(new WrappedStack(Item.ingotGold), new EmcValue(2048));
        valueMap.put(new WrappedStack(Item.silk), new EmcValue(12));
        valueMap.put(new WrappedStack(Item.feather), new EmcValue(48));
        valueMap.put(new WrappedStack(Item.gunpowder), new EmcValue(192));
        valueMap.put(new WrappedStack(Item.seeds), new EmcValue(16));
        valueMap.put(new WrappedStack(Item.wheat), new EmcValue(24));
        valueMap.put(new WrappedStack(Item.flint), new EmcValue(4));
        valueMap.put(new WrappedStack(Item.leather), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.brick), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.clay), new EmcValue(64));
        valueMap.put(new WrappedStack(Item.reed), new EmcValue(32));
        valueMap.put(new WrappedStack(Item.egg), new EmcValue(32));
        valueMap.put(new WrappedStack(Item.glowstone), new EmcValue(384));
        valueMap.put(new WrappedStack(new ItemStack(Item.dyePowder, 1, 4)), new EmcValue(864));
        valueMap.put(new WrappedStack(Item.blazeRod), new EmcValue(1536));
        valueMap.put(new WrappedStack(Item.netherStalkSeeds), new EmcValue(24));
        valueMap.put(new WrappedStack(Item.emerald), new EmcValue(8192));
        valueMap.put(new WrappedStack(Item.netherStar), new EmcValue(24576));
        valueMap.put(new WrappedStack(Item.netherrackBrick), new EmcValue(1));
        valueMap.put(new WrappedStack(Item.netherQuartz), new EmcValue(256));
        
        /* Equivalent Exchange 3 */
        /**
         *  Alchemical Dusts
         */
        valueMap.put(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 0)), new EmcValue(1));
        valueMap.put(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 1)), new EmcValue(64));
        valueMap.put(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 2)), new EmcValue(2048));
        valueMap.put(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 3)), new EmcValue(8192));

        /**
         *  Minium Shard
         */
        valueMap.put(new WrappedStack(new ItemStack(ModItems.miniumShard)), new EmcValue(8192));
    }

    public static Map<WrappedStack, EmcValue> getDefaultValueMap()
    {
        lazyInit();
        return emcDefaultValues.valueMap;
    }
}

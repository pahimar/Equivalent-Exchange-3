package com.pahimar.ee3.emc;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.item.ModItems;

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
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.cobblestone))), new EmcValue(1));
        for (int meta = 0; meta < 16; meta++)
        {
            valueMap.put(new WrappedStack(new OreStack(new ItemStack(Items.dye, 1, meta))), new EmcValue(8));
        }
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.log))), new EmcValue(32));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.diamond_ore))), new EmcValue(8192));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.emerald_ore))), new EmcValue(8192));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.gold_ore))), new EmcValue(2048));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.iron_ore))), new EmcValue(256));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.lapis_ore))), new EmcValue(864));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.quartz_ore))), new EmcValue(256));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.redstone_ore))), new EmcValue(32));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.planks))), new EmcValue(8));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Items.record_11))), new EmcValue(2048));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.wooden_slab))), new EmcValue(4));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.oak_stairs))), new EmcValue(12));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Items.stick))), new EmcValue(4));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.stone))), new EmcValue(1));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.leaves))), new EmcValue(1));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.sapling))), new EmcValue(32));

        // Fluids
        valueMap.put(new WrappedStack(FluidRegistry.WATER), new EmcValue(1));
        valueMap.put(new WrappedStack(FluidRegistry.LAVA), new EmcValue(64));
        valueMap.put(new WrappedStack(FluidRegistry.getFluid("milk")), new EmcValue(64));

        /* Building Blockss */
        valueMap.put(new WrappedStack(Blocks.stone), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.grass), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.dirt), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.cobblestone), new EmcValue(1));
        // Bedrock (7:0)
        valueMap.put(new WrappedStack(Blocks.sand), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.gravel), new EmcValue(4));
        valueMap.put(new WrappedStack(Blocks.coal_ore), new EmcValue(32));
        // Sponge (19:0)
        valueMap.put(new WrappedStack(Blocks.glass), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.sandstone), new EmcValue(4));
        valueMap.put(new WrappedStack(Blocks.mossy_cobblestone), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.obsidian), new EmcValue(64));
        valueMap.put(new WrappedStack(Blocks.ice), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.pumpkin), new EmcValue(144));
        valueMap.put(new WrappedStack(Blocks.netherrack), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.soul_sand), new EmcValue(49));
        valueMap.put(new WrappedStack(new ItemStack(Blocks.stonebrick, 1, 1)), new EmcValue(1));
        valueMap.put(new WrappedStack(new ItemStack(Blocks.stonebrick, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.mycelium), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.end_stone), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.hardened_clay), new EmcValue(256));
        
        /* Decoration Blockss */
        valueMap.put(new WrappedStack(Blocks.web), new EmcValue(12));
        valueMap.put(new WrappedStack(new ItemStack(Blocks.tallgrass, 1, OreDictionary.WILDCARD_VALUE)), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.deadbush), new EmcValue(1));
        valueMap.put(new WrappedStack(Blocks.yellow_flower), new EmcValue(16));
        valueMap.put(new WrappedStack(Blocks.red_flower), new EmcValue(16));
        valueMap.put(new WrappedStack(Blocks.brown_mushroom), new EmcValue(32));
        valueMap.put(new WrappedStack(Blocks.red_mushroom), new EmcValue(32));
        valueMap.put(new WrappedStack(Blocks.snow), new EmcValue(0.5f));
        valueMap.put(new WrappedStack(Blocks.cactus), new EmcValue(8));
        // Stone Monster Egg (97:0)
        // Cobblestone Monster Egg (97:1)
        // Stone Brick Monster Egg (97:2)
        valueMap.put(new WrappedStack(Blocks.vine), new EmcValue(8));
        valueMap.put(new WrappedStack(Blocks.waterlily), new EmcValue(16));
        // End Portal (120:0)
        // Skeleton Skull (397:0)
        // Wither Skeleton Skull (391:1)
        // Zombie Head (397:2)
        // Head (397:3)
        // Creeper Head (397:4)
        
        /* Redstone */
        valueMap.put(new WrappedStack(Items.redstone), new EmcValue(32));
        
        /* Transportation */
        valueMap.put(new WrappedStack(Items.saddle), new EmcValue(192));
        
        /* Miscellaneous */
        valueMap.put(new WrappedStack(Items.snowball), new EmcValue(0.25f));
        valueMap.put(new WrappedStack(Items.slime_ball), new EmcValue(24));
        valueMap.put(new WrappedStack(Items.bone), new EmcValue(24));
        valueMap.put(new WrappedStack(Items.ender_pearl), new EmcValue(1024));
        // Bottle o'Enchanting (384:0)
        // Firework Star (402:0)
        
        /* Foodstuffs */
        valueMap.put(new WrappedStack(Items.apple), new EmcValue(128));
        valueMap.put(new WrappedStack(Items.porkchop), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.cooked_porkchop), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.fish), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.cooked_fished), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.melon), new EmcValue(16));
        valueMap.put(new WrappedStack(Items.beef), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.cooked_beef), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.chicken), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.cooked_chicken), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.rotten_flesh), new EmcValue(24));
        valueMap.put(new WrappedStack(Items.spider_eye), new EmcValue(128));
        valueMap.put(new WrappedStack(Items.carrot), new EmcValue(24));
        valueMap.put(new WrappedStack(Items.potato), new EmcValue(24));
        valueMap.put(new WrappedStack(Items.baked_potato), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.poisonous_potato), new EmcValue(24));
        
        /* Tools */
        // Name Tag (421:0)
        
        /* Combat */
        // Chain Helmet (302:0)
        // Chain Chestplate (303:0)
        // Chain Leggings (304:0)
        // Chain Boots (305:0)
        
        /* Brewing */
        valueMap.put(new WrappedStack(Items.ghast_tear), new EmcValue(4096));
        
        /* Materials */
        valueMap.put(new WrappedStack(new ItemStack(Items.coal, 1, 0)), new EmcValue(32));
        valueMap.put(new WrappedStack(new ItemStack(Items.coal, 1, 1)), new EmcValue(32));
        valueMap.put(new WrappedStack(Items.diamond), new EmcValue(8192));
        valueMap.put(new WrappedStack(Items.iron_ingot), new EmcValue(256));
        valueMap.put(new WrappedStack(Items.gold_ingot), new EmcValue(2048));
        valueMap.put(new WrappedStack(Items.string), new EmcValue(12));
        valueMap.put(new WrappedStack(Items.feather), new EmcValue(48));
        valueMap.put(new WrappedStack(Items.gunpowder), new EmcValue(192));
        valueMap.put(new WrappedStack(Items.wheat_seeds), new EmcValue(16));
        valueMap.put(new WrappedStack(Items.wheat), new EmcValue(24));
        valueMap.put(new WrappedStack(Items.flint), new EmcValue(4));
        valueMap.put(new WrappedStack(Items.leather), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.brick), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.clay_ball), new EmcValue(64));
        valueMap.put(new WrappedStack(Items.reeds), new EmcValue(32));
        valueMap.put(new WrappedStack(Items.egg), new EmcValue(32));
        valueMap.put(new WrappedStack(Items.glowstone_dust), new EmcValue(384));
        valueMap.put(new WrappedStack(new ItemStack(Items.dye, 1, 4)), new EmcValue(864));
        valueMap.put(new WrappedStack(Items.blaze_rod), new EmcValue(1536));
        valueMap.put(new WrappedStack(Items.nether_wart), new EmcValue(24));
        valueMap.put(new WrappedStack(Items.emerald), new EmcValue(8192));
        valueMap.put(new WrappedStack(Items.nether_star), new EmcValue(24576));
        valueMap.put(new WrappedStack(Items.netherbrick), new EmcValue(1));
        valueMap.put(new WrappedStack(Items.quartz), new EmcValue(256));
        
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

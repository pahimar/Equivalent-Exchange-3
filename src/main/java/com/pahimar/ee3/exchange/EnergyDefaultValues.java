package com.pahimar.ee3.exchange;

import com.pahimar.ee3.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

public class EnergyDefaultValues
{
    private static EnergyDefaultValues energyDefaultValues = null;
    private Map<WrappedStack, EnergyValue> valueMap;

    private EnergyDefaultValues()
    {
        valueMap = new HashMap<WrappedStack, EnergyValue>();
    }

    private static void lazyInit()
    {
        if (energyDefaultValues == null)
        {
            energyDefaultValues = new EnergyDefaultValues();
            energyDefaultValues.init();
        }
    }

    public static Map<WrappedStack, EnergyValue> getDefaultValueMap()
    {
        lazyInit();
        return energyDefaultValues.valueMap;
    }

    private void init()
    {
        // OreDictionary assignment
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.cobblestone))), new EnergyValue(1));
        for (int meta = 0; meta < 16; meta++)
        {
            valueMap.put(new WrappedStack(new OreStack(new ItemStack(Items.dye, 1, meta))), new EnergyValue(8));
        }
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.log))), new EnergyValue(32));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.diamond_ore))), new EnergyValue(8192));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.emerald_ore))), new EnergyValue(8192));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.gold_ore))), new EnergyValue(2048));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.iron_ore))), new EnergyValue(256));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.lapis_ore))), new EnergyValue(864));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.quartz_ore))), new EnergyValue(256));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.redstone_ore))), new EnergyValue(32));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.planks))), new EnergyValue(8));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Items.record_11))), new EnergyValue(2048));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.wooden_slab))), new EnergyValue(4));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.oak_stairs))), new EnergyValue(12));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Items.stick))), new EnergyValue(4));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.stone))), new EnergyValue(1));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.leaves))), new EnergyValue(1));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.sapling))), new EnergyValue(32));
        valueMap.put(new WrappedStack(new OreStack(new ItemStack(Blocks.sandstone))), new EnergyValue(4));

        // Fluids
        valueMap.put(new WrappedStack(FluidRegistry.WATER), new EnergyValue(1));
        valueMap.put(new WrappedStack(FluidRegistry.LAVA), new EnergyValue(64));
        valueMap.put(new WrappedStack(FluidRegistry.getFluid("milk")), new EnergyValue(64));

        /* Building Blocks */
        valueMap.put(new WrappedStack(Blocks.stone), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.grass), new EnergyValue(1));
        valueMap.put(new WrappedStack(new ItemStack(Blocks.dirt, 1, OreDictionary.WILDCARD_VALUE)), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.cobblestone), new EnergyValue(1));
        // Bedrock
        valueMap.put(new WrappedStack(new ItemStack(Blocks.sand, 1, OreDictionary.WILDCARD_VALUE)), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.gravel), new EnergyValue(4));
        valueMap.put(new WrappedStack(Blocks.coal_ore), new EnergyValue(32));
        // Sponge
        valueMap.put(new WrappedStack(Blocks.glass), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.sandstone), new EnergyValue(4));
        valueMap.put(new WrappedStack(Blocks.mossy_cobblestone), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.obsidian), new EnergyValue(64));
        valueMap.put(new WrappedStack(Blocks.ice), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.pumpkin), new EnergyValue(144));
        valueMap.put(new WrappedStack(Blocks.netherrack), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.soul_sand), new EnergyValue(49));
        valueMap.put(new WrappedStack(new ItemStack(Blocks.stonebrick, 1, 1)), new EnergyValue(1));
        valueMap.put(new WrappedStack(new ItemStack(Blocks.stonebrick, 1, OreDictionary.WILDCARD_VALUE)), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.mycelium), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.end_stone), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.hardened_clay), new EnergyValue(256));

        /* Decoration Blocks */
        valueMap.put(new WrappedStack(Blocks.web), new EnergyValue(12));
        valueMap.put(new WrappedStack(new ItemStack(Blocks.tallgrass, 1, OreDictionary.WILDCARD_VALUE)), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.deadbush), new EnergyValue(1));
        valueMap.put(new WrappedStack(Blocks.yellow_flower), new EnergyValue(16));
        valueMap.put(new WrappedStack(new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE)), new EnergyValue(16));
        valueMap.put(new WrappedStack(Blocks.brown_mushroom), new EnergyValue(32));
        valueMap.put(new WrappedStack(Blocks.red_mushroom), new EnergyValue(32));
        valueMap.put(new WrappedStack(Blocks.snow), new EnergyValue(0.5f));
        valueMap.put(new WrappedStack(Blocks.cactus), new EnergyValue(8));
        // Stone Monster Egg
        // Cobblestone Monster Egg
        // Stone Brick Monster Egg
        valueMap.put(new WrappedStack(Blocks.vine), new EnergyValue(8));
        valueMap.put(new WrappedStack(Blocks.waterlily), new EnergyValue(16));
        // End Portal
        valueMap.put(new WrappedStack(new ItemStack(Blocks.double_plant, 1, OreDictionary.WILDCARD_VALUE)), new EnergyValue(16));
        // Skeleton Skull
        // Wither Skeleton Skull
        // Zombie Head
        // Head
        // Creeper Head

        /* Redstone */
        valueMap.put(new WrappedStack(Items.redstone), new EnergyValue(32));

        /* Transportation */
        valueMap.put(new WrappedStack(Items.saddle), new EnergyValue(192));

        /* Miscellaneous */
        valueMap.put(new WrappedStack(Items.snowball), new EnergyValue(0.25f));
        // Milk
        valueMap.put(new WrappedStack(Items.slime_ball), new EnergyValue(24));
        valueMap.put(new WrappedStack(Items.bone), new EnergyValue(24));
        valueMap.put(new WrappedStack(Items.ender_pearl), new EnergyValue(1024));
        // Bottle o'Enchanting
        // Firework Star

        /* Foodstuffs */
        valueMap.put(new WrappedStack(Items.apple), new EnergyValue(128));
        valueMap.put(new WrappedStack(Items.porkchop), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.cooked_porkchop), new EnergyValue(64));
        valueMap.put(new WrappedStack(new ItemStack(Items.fish, 1, OreDictionary.WILDCARD_VALUE)), new EnergyValue(64));
        valueMap.put(new WrappedStack(new ItemStack(Items.cooked_fished, 1, OreDictionary.WILDCARD_VALUE)), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.melon), new EnergyValue(16));
        valueMap.put(new WrappedStack(Items.beef), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.cooked_beef), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.chicken), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.cooked_chicken), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.rotten_flesh), new EnergyValue(24));
        valueMap.put(new WrappedStack(Items.spider_eye), new EnergyValue(128));
        valueMap.put(new WrappedStack(Items.carrot), new EnergyValue(24));
        valueMap.put(new WrappedStack(Items.potato), new EnergyValue(24));
        valueMap.put(new WrappedStack(Items.baked_potato), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.poisonous_potato), new EnergyValue(24));

        /* Tools */
        // Name Tag

        /* Combat */
        // Chain Helmet
        // Chain Chestplate
        // Chain Leggings
        // Chain Boots

        /* Brewing */
        valueMap.put(new WrappedStack(Items.ghast_tear), new EnergyValue(4096));

        /* Materials */
        valueMap.put(new WrappedStack(new ItemStack(Items.coal, 1, 0)), new EnergyValue(32));
        valueMap.put(new WrappedStack(new ItemStack(Items.coal, 1, 1)), new EnergyValue(32));
        valueMap.put(new WrappedStack(Items.diamond), new EnergyValue(8192));
        valueMap.put(new WrappedStack(Items.iron_ingot), new EnergyValue(256));
        valueMap.put(new WrappedStack(Items.gold_ingot), new EnergyValue(2048));
        valueMap.put(new WrappedStack(Items.string), new EnergyValue(12));
        valueMap.put(new WrappedStack(Items.feather), new EnergyValue(48));
        valueMap.put(new WrappedStack(Items.gunpowder), new EnergyValue(192));
        valueMap.put(new WrappedStack(Items.wheat_seeds), new EnergyValue(16));
        valueMap.put(new WrappedStack(Items.wheat), new EnergyValue(24));
        valueMap.put(new WrappedStack(Items.flint), new EnergyValue(4));
        valueMap.put(new WrappedStack(Items.leather), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.brick), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.clay_ball), new EnergyValue(64));
        valueMap.put(new WrappedStack(Items.reeds), new EnergyValue(32));
        valueMap.put(new WrappedStack(Items.egg), new EnergyValue(32));
        valueMap.put(new WrappedStack(Items.glowstone_dust), new EnergyValue(384));
        valueMap.put(new WrappedStack(new ItemStack(Items.dye, 1, 4)), new EnergyValue(864));
        valueMap.put(new WrappedStack(Items.blaze_rod), new EnergyValue(1536));
        valueMap.put(new WrappedStack(Items.nether_wart), new EnergyValue(24));
        valueMap.put(new WrappedStack(Items.emerald), new EnergyValue(8192));
        valueMap.put(new WrappedStack(Items.nether_star), new EnergyValue(24576));
        valueMap.put(new WrappedStack(Items.netherbrick), new EnergyValue(1));
        valueMap.put(new WrappedStack(Items.quartz), new EnergyValue(256));

        /* Equivalent Exchange 3 */
        /**
         *  Alchemical Dusts
         */
        valueMap.put(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 0)), new EnergyValue(1));
        valueMap.put(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 1)), new EnergyValue(64));
        valueMap.put(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 2)), new EnergyValue(2048));
        valueMap.put(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 3)), new EnergyValue(8192));

        /**
         *  Minium Shard
         */
        valueMap.put(new WrappedStack(new ItemStack(ModItems.shardMinium)), new EnergyValue(8192));
    }
}

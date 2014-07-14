package com.pahimar.ee3.init;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class EnergyValues
{
    public static void init()
    {
        // OreDictionary assignment
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.cobblestone))), 1);
        for (int meta = 0; meta < 16; meta++)
        {
            EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Items.dye, 1, meta))), 8);
        }
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.log))), 32);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.diamond_ore))), 8192);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.emerald_ore))), 8192);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.gold_ore))), 2048);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.iron_ore))), 256);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.lapis_ore))), 864);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.quartz_ore))), 256);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.redstone_ore))), 32);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.planks))), 8);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Items.record_11))), 2048);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.wooden_slab))), 4);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.oak_stairs))), 12);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Items.stick))), 4);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.stone))), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.leaves))), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.sapling))), 32);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new OreStack(new ItemStack(Blocks.sandstone))), 4);

        // Fluids
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(FluidRegistry.WATER), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(FluidRegistry.LAVA), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(FluidRegistry.getFluid("milk")), 64);

        /* Building Blocks */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.stone), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.grass), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.dirt, 1, OreDictionary.WILDCARD_VALUE)), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.cobblestone), 1);
        // Bedrock
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.sand, 1, OreDictionary.WILDCARD_VALUE)), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.gravel), 4);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.coal_ore), 32);
        // Sponge
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.glass), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.sandstone), 4);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.mossy_cobblestone), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.obsidian), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.ice), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.pumpkin), 144);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.netherrack), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.soul_sand), 49);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.stonebrick, 1, 1)), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.stonebrick, 1, OreDictionary.WILDCARD_VALUE)), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.mycelium), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.end_stone), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.hardened_clay), 256);

        /* Decoration Blocks */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.web), 12);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.tallgrass, 1, OreDictionary.WILDCARD_VALUE)), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.deadbush), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.yellow_flower), 16);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE)), 16);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.brown_mushroom), 32);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.red_mushroom), 32);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.snow), 0.5f);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.cactus), 8);
        // Stone Monster Egg
        // Cobblestone Monster Egg
        // Stone Brick Monster Egg
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.vine), 8);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Blocks.waterlily), 16);
        // End Portal
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.double_plant, 1, OreDictionary.WILDCARD_VALUE)), 16);
        // Skeleton Skull
        // Wither Skeleton Skull
        // Zombie Head
        // Head
        // Creeper Head

        /* Redstone */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.redstone), 32);

        /* Transportation */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.saddle), 192);

        /* Miscellaneous */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.snowball), 0.25f);
        // Milk
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.slime_ball), 24);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.bone), 24);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.ender_pearl), 1024);
        // Bottle o'Enchanting
        // Firework Star

        /* Foodstuffs */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.apple), 128);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.porkchop), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.cooked_porkchop), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.fish, 1, OreDictionary.WILDCARD_VALUE)), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.cooked_fished, 1, OreDictionary.WILDCARD_VALUE)), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.melon), 16);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.beef), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.cooked_beef), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.chicken), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.cooked_chicken), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.rotten_flesh), 24);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.spider_eye), 128);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.carrot), 24);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.potato), 24);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.baked_potato), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.poisonous_potato), 24);

        /* Tools */
        // Name Tag

        /* Combat */
        // Chain Helmet
        // Chain Chestplate
        // Chain Leggings
        // Chain Boots

        /* Brewing */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.ghast_tear), 4096);

        /* Materials */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.coal, 1, 0)), 32);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.coal, 1, 1)), 32);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.diamond), 8192);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.iron_ingot), 256);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.gold_ingot), 2048);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.string), 12);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.feather), 48);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.gunpowder), 192);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.wheat_seeds), 16);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.wheat), 24);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.flint), 4);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.leather), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.brick), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.clay_ball), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.reeds), 32);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.egg), 32);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.glowstone_dust), 384);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.dye, 1, 4)), 864);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.blaze_rod), 1536);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.nether_wart), 24);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.emerald), 8192);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.nether_star), 24576);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.netherbrick), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(Items.quartz), 256);

        /* Equivalent Exchange 3 */
        /**
         *  Alchemical Dusts
         */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 0)), 1);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 1)), 64);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 2)), 2048);
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 3)), 8192);

        /**
         *  Minium Shard
         */
        EnergyValueRegistry.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.shardMinium)), 8192);
    }
}

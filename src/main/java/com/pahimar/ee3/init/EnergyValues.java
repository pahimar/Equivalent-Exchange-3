package com.pahimar.ee3.init;

import com.pahimar.ee3.api.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class EnergyValues
{
    public static void addDefaultEnergyValues()
    {
        // OreDictionary assignment
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("cobblestone")), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("stone")), 1);

        String[] dyes = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};
        for (int i = 0; i < dyes.length; i++)
        {
            EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("dye" + dyes[i])), 8);
        }

        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("logWood")), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("oreDiamond")), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("gemDiamond")), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("oreEmerald")), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("gemEmerald")), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("oreGold")), 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("ingotGold")), 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("oreIron")), 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("ingotIron")), 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("oreLapis")), 864);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("gemLapis")), 864);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("oreQuartz")), 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("gemQuartz")), 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("oreRedstone")), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("plankWood")), 8);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("record")), 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("slabWood")), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("stairWood")), 12);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("stickWood")), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("treeLeaves")), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("treeSapling")), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("sandstone")), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new OreStack("sand")), 1);

        // Fluids
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(FluidRegistry.WATER), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(FluidRegistry.LAVA), 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(FluidRegistry.getFluid("milk")), 64);

        /* Building Blocks */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.stone), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.grass), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.dirt, 1, OreDictionary.WILDCARD_VALUE)), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.cobblestone), 1);
        // Bedrock
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.sand, 1, OreDictionary.WILDCARD_VALUE)), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.gravel), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.coal_ore), 32);
        // Sponge
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.glass), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE)), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.mossy_cobblestone), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.obsidian), 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.ice), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.pumpkin), 144);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.netherrack), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.soul_sand), 49);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.stonebrick, 1, OreDictionary.WILDCARD_VALUE)), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.mycelium), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.end_stone), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.hardened_clay), 256);

        /* Decoration Blocks */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.web), 12);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.tallgrass, 1, OreDictionary.WILDCARD_VALUE)), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.deadbush), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.yellow_flower), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE)), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.brown_mushroom), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.red_mushroom), 32);
        // Snow Layer
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.cactus), 8);
        // Stone Monster Egg
        // Cobblestone Monster Egg
        // Stone Brick Monster Egg
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.vine), 8);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Blocks.waterlily), 16);
        // End Portal
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Blocks.double_plant, 1, OreDictionary.WILDCARD_VALUE)), 16);
        // Skeleton Skull
        // Wither Skeleton Skull
        // Zombie Head
        // Head
        // Creeper Head

        /* Redstone */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.redstone), 32);

        /* Transportation */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.saddle), 192);

        /* Miscellaneous */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.snowball), 0.25f);
        // Milk
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.slime_ball), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.bone), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.ender_pearl), 1024);
        // Bottle o'Enchanting
        // Firework Star

        /* Foodstuffs */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.apple), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.porkchop), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.cooked_porkchop), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.fish, 1, OreDictionary.WILDCARD_VALUE)), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.cooked_fished, 1, OreDictionary.WILDCARD_VALUE)), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.melon), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.beef), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.cooked_beef), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.chicken), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.cooked_chicken), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.rotten_flesh), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.spider_eye), 128);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.carrot), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.potato), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.baked_potato), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.poisonous_potato), 24);

        /* Tools */
        // Name Tag

        /* Combat */
        // Chain Helmet
        // Chain Chestplate
        // Chain Leggings
        // Chain Boots

        /* Brewing */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.ghast_tear), 4096);

        /* Materials */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.coal, 1, 0)), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.coal, 1, 1)), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.diamond), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.iron_ingot), 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.gold_ingot), 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.string), 12);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.feather), 48);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.gunpowder), 192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.wheat_seeds), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.wheat), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.flint), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.leather), 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.brick), 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.clay_ball), 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.reeds), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.egg), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.glowstone_dust), 384);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(Items.dye, 1, 4)), 864);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.blaze_rod), 1536);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.nether_wart), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.emerald), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.nether_star), 24576);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.netherbrick), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(Items.quartz), 256);

        /* Equivalent Exchange 3 */
        /**
         *  Alchemical Dusts
         */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 0)), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 1)), 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 2)), 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.alchemicalDust, 1, 3)), 8192);

        /**
         *  Minium Shard
         */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new WrappedStack(new ItemStack(ModItems.shardMinium)), 8192);
    }
}

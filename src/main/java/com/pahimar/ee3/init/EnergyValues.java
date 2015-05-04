package com.pahimar.ee3.init;

import com.pahimar.ee3.api.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
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
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("cobblestone"), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("dustRedstone"), 32);
        String[] dyes = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};
        for (int i = 0; i < dyes.length; i++)
        {
            EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("dye" + dyes[i]), 16);
        }
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("gemDiamond"), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("gemEmerald"), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("gemLapis"), 864);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("gemQuartz"), 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("ingotGold"), 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("ingotIron"), 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("logWood"), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreCoal"), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreDiamond"), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreEmerald"), 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreGold"), 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreIron"), 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreLapis"), 864);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreQuartz"), 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreRedstone"), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("plankWood"), 8);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("record"), 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("sand"), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("sandstone"), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("slabWood"), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("stairWood"), 12);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("stickWood"), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("stone"), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("treeLeaves"), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("treeSapling"), 32);

        // Fluids
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(FluidRegistry.WATER, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(FluidRegistry.LAVA, 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(FluidRegistry.getFluid("milk"), 64);

        /* Building Blocks */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.stone, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.grass, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.dirt, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.cobblestone, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.sand, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.gravel, 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.glass, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE), 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.mossy_cobblestone, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.obsidian, 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.ice, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.pumpkin, 144);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.netherrack, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.soul_sand, 49);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.stonebrick, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.mycelium, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.end_stone, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.hardened_clay, 256);

        /* Decoration Blocks */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.web, 12);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.tallgrass, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.deadbush, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.yellow_flower, 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.brown_mushroom, 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.red_mushroom, 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.red_mushroom, 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.snow_layer, 0.125f);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.cactus, 8);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.vine, 8);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Blocks.waterlily, 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.anvil, 1, 1), 5290.667f);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.anvil, 1, 2), 2645.333f);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Blocks.double_plant, 1, OreDictionary.WILDCARD_VALUE), 32);

        /* Redstone */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.redstone, 32);

        /* Transportation */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.saddle, 192);

        /* Miscellaneous */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.snowball, 0.25f);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.slime_ball, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.bone, 48);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.ender_pearl, 1024);

        /* Foodstuffs */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.apple, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.porkchop, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.cooked_porkchop, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.fish, 1, OreDictionary.WILDCARD_VALUE), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.cooked_fished, 1, OreDictionary.WILDCARD_VALUE), 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.melon, 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.beef, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.cooked_beef, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.chicken, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.cooked_chicken, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.rotten_flesh, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.spider_eye, 128);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.carrot, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.potato, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.baked_potato, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.poisonous_potato, 24);

        /* Brewing */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.ghast_tear, 4096);

        /* Materials */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.coal, 1, 0), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.coal, 1, 1), 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.diamond, 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.iron_ingot, 256);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.gold_ingot, 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.string, 12);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.feather, 48);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.gunpowder, 192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.wheat_seeds, 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.wheat, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.flint, 4);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.leather, 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.brick, 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.clay_ball, 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.reeds, 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.egg, 32);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.glowstone_dust, 384);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.dye, 1, 0), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.dye, 1, 2), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.dye, 1, 3), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.dye, 1, 4), 864);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.dye, 1, 5), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(Items.dye, 1, 6), 16);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.blaze_rod, 1536);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.nether_wart, 24);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.emerald, 8192);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.nether_star, 24576);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.netherbrick, 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(Items.quartz, 256);

        /* Equivalent Exchange 3 */
        /**
         *  Alchemical Dusts
         */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 0), 1);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 1), 64);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 2), 2048);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 3), 8192);

        /**
         *  Minium Shard
         */
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ModItems.shardMinium), 8192);
    }
}

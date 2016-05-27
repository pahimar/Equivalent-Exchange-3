package com.pahimar.ee3.init;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import static com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy.Phase;

public class EnergyValues {

    private static final String[] DYES = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};

    public static void init() {

        // OreDictionary assignment
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("cobblestone"), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("dustRedstone"), 32, Phase.PRE_CALCULATION);
        for (int i = 0; i < DYES.length; i++) {
            EnergyValueRegistryProxy.setEnergyValue(new OreStack("dye" + DYES[i]), 16, Phase.PRE_CALCULATION);
        }
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("gemDiamond"), 8192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("gemEmerald"), 8192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("gemLapis"), 864, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("gemQuartz"), 256, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("ingotGold"), 2048, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("ingotIron"), 256, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("logWood"), 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("oreCoal"), 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("oreDiamond"), 8192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("oreEmerald"), 8192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("oreGold"), 2048, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("oreIron"), 256, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("oreLapis"), 864, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("oreQuartz"), 256, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("oreRedstone"), 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("plankWood"), 8, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("record"), 2048, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("sand"), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("sandstone"), 4, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("slabWood"), 4, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("slimeball"), 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("stairWood"), 12, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("stickWood"), 4, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("stone"), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("treeLeaves"), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("treeSapling"), 32, Phase.PRE_CALCULATION);

        // Fluids
        EnergyValueRegistryProxy.setEnergyValue(FluidRegistry.WATER, 0.001, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(FluidRegistry.LAVA, 0.064, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(FluidRegistry.getFluid("milk"), 0.064, Phase.PRE_CALCULATION);

        /* Building Blocks */
        EnergyValueRegistryProxy.setEnergyValue(Blocks.stone, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.grass, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.dirt, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.cobblestone, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.sand, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.gravel, 4, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.glass, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE), 4, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.mossy_cobblestone, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.obsidian, 64, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.ice, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.pumpkin, 144, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.netherrack, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.soul_sand, 49, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.stonebrick, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.mycelium, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.end_stone, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.hardened_clay, 256, Phase.PRE_CALCULATION);

        /* Decoration Blocks */
        EnergyValueRegistryProxy.setEnergyValue(Blocks.web, 12, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.tallgrass, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.deadbush, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.yellow_flower, 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.brown_mushroom, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.red_mushroom, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.red_mushroom, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.snow_layer, 0.125f, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.cactus, 8, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.vine, 8, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.waterlily, 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.anvil, 1, 1), 5290.667f, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.anvil, 1, 2), 2645.333f, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.double_plant, 1, OreDictionary.WILDCARD_VALUE), 32, Phase.PRE_CALCULATION);

        /* Redstone */
        EnergyValueRegistryProxy.setEnergyValue(Items.redstone, 32, Phase.PRE_CALCULATION);

        /* Transportation */
        EnergyValueRegistryProxy.setEnergyValue(Items.saddle, 192, Phase.PRE_CALCULATION);

        /* Miscellaneous */
        EnergyValueRegistryProxy.setEnergyValue(Items.snowball, 0.25f, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.bone, 48, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.ender_pearl, 1024, Phase.PRE_CALCULATION);

        /* Foodstuffs */
        EnergyValueRegistryProxy.setEnergyValue(Items.apple, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.porkchop, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.cooked_porkchop, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.fish, 1, OreDictionary.WILDCARD_VALUE), 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.cooked_fished, 1, OreDictionary.WILDCARD_VALUE), 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.melon, 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.beef, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.cooked_beef, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.chicken, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.cooked_chicken, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.rotten_flesh, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.spider_eye, 128, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.carrot, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.potato, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.baked_potato, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.poisonous_potato, 24, Phase.PRE_CALCULATION);

        /* Brewing */
        EnergyValueRegistryProxy.setEnergyValue(Items.ghast_tear, 4096, Phase.PRE_CALCULATION);

        /* Materials */
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.coal, 1, 0), 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.coal, 1, 1), 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.diamond, 8192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.iron_ingot, 256, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.gold_ingot, 2048, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.string, 12, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.feather, 48, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.gunpowder, 192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.wheat_seeds, 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.wheat, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.flint, 4, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.leather, 64, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.brick, 64, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.clay_ball, 64, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.reeds, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.egg, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.glowstone_dust, 384, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.dye, 1, 0), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.dye, 1, 2), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.dye, 1, 3), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.dye, 1, 4), 864, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.dye, 1, 5), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.dye, 1, 6), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.blaze_rod, 1536, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.nether_wart, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.emerald, 8192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.nether_star, 24576, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.netherbrick, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.quartz, 256, Phase.PRE_CALCULATION);

        /* Equivalent Exchange 3 */
        /**
         *  Alchemical Dusts
         */
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 0), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 1), 64, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 2), 2048, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 3), 8192, Phase.PRE_CALCULATION);

        /**
         *  Minium Shard
         */
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.shardMinium), 8192, Phase.PRE_CALCULATION);
    }
}

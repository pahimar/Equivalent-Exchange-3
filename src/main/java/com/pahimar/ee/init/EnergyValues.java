package com.pahimar.ee.init;

import com.pahimar.ee.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee.exchange.OreStack;
import com.pahimar.ee.reference.Colors;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import static com.pahimar.ee.api.exchange.EnergyValueRegistryProxy.Phase;

// TODO Uncomment out dusts once they are re-implemented
public class EnergyValues {

    public static void init() {

        EnergyValueRegistryProxy.setEnergyValue(ItemStack.EMPTY, 0, Phase.PRE_CALCULATION);

        // OreDictionary assignment
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("cobblestone"), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new OreStack("dustRedstone"), 32, Phase.PRE_CALCULATION);
        for (String dyeName : Colors.DYE_NAMES) {
            EnergyValueRegistryProxy.setEnergyValue(new OreStack(dyeName), 16, Phase.PRE_CALCULATION);
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

        /* Building Blocks */
        EnergyValueRegistryProxy.setEnergyValue(Blocks.STONE, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.GRASS, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.DIRT, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.COBBLESTONE, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.SAND, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.GRAVEL, 4, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.GLASS, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.SANDSTONE, 1, OreDictionary.WILDCARD_VALUE), 4, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.MOSSY_COBBLESTONE, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.OBSIDIAN, 64, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.ICE, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.PUMPKIN, 144, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.NETHERRACK, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.SOUL_SAND, 49, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.STONEBRICK, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.MYCELIUM, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.END_STONE, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.HARDENED_CLAY, 256, Phase.PRE_CALCULATION);

        /* Decoration Blocks */
        EnergyValueRegistryProxy.setEnergyValue(Blocks.WEB, 12, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE), 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.DEADBUSH, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.YELLOW_FLOWER, 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.RED_FLOWER, 1, OreDictionary.WILDCARD_VALUE), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.BROWN_MUSHROOM, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.RED_MUSHROOM, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.SNOW_LAYER, 0.125f, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.CACTUS, 8, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.VINE, 8, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Blocks.WATERLILY, 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.ANVIL, 1, 1), 5290.667f, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.ANVIL, 1, 2), 2645.333f, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Blocks.DOUBLE_PLANT, 1, OreDictionary.WILDCARD_VALUE), 32, Phase.PRE_CALCULATION);

        /* Redstone */
        EnergyValueRegistryProxy.setEnergyValue(Items.REDSTONE, 32, Phase.PRE_CALCULATION);

        /* Transportation */
        EnergyValueRegistryProxy.setEnergyValue(Items.SADDLE, 192, Phase.PRE_CALCULATION);

        /* Miscellaneous */
        EnergyValueRegistryProxy.setEnergyValue(Items.SNOWBALL, 0.25f, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.BONE, 48, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.ENDER_PEARL, 1024, Phase.PRE_CALCULATION);

        /* Foodstuffs */
        EnergyValueRegistryProxy.setEnergyValue(Items.APPLE, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.GOLDEN_APPLE, 1, 1), 147480, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.PORKCHOP, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.COOKED_PORKCHOP, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.FISH, 1, OreDictionary.WILDCARD_VALUE), 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.COOKED_FISH, 1, OreDictionary.WILDCARD_VALUE), 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.MELON, 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.BEEF, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.COOKED_BEEF, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.CHICKEN, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.COOKED_CHICKEN, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.ROTTEN_FLESH, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.SPIDER_EYE, 128, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.CARROT, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.POTATO, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.BAKED_POTATO, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.POISONOUS_POTATO, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.RABBIT, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.COOKED_RABBIT, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.MUTTON, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.COOKED_MUTTON, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.BEETROOT, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.BEETROOT_SEEDS, 16, Phase.PRE_CALCULATION);

        /* Brewing */
        EnergyValueRegistryProxy.setEnergyValue(Items.GHAST_TEAR, 4096, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.RABBIT_FOOT, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.DRAGON_BREATH, 769, Phase.PRE_CALCULATION);

        /* Materials */
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.COAL, 1, 0), 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.COAL, 1, 1), 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.DIAMOND, 8192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.IRON_INGOT, 256, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.GOLD_INGOT, 2048, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.STRING, 12, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.FEATHER, 48, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.GUNPOWDER, 192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.WHEAT_SEEDS, 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.WHEAT, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.FLINT, 4, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.LEATHER, 64, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.BRICK, 64, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.CLAY_BALL, 64, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.REEDS, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.EGG, 32, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.GLOWSTONE_DUST, 384, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.DYE, 1, 0), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.DYE, 1, 2), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.DYE, 1, 3), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.DYE, 1, 4), 864, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.DYE, 1, 5), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(Items.DYE, 1, 6), 16, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.BLAZE_ROD, 1536, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.NETHER_WART, 24, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.EMERALD, 8192, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.NETHER_STAR, 24576, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.NETHERBRICK, 1, Phase.PRE_CALCULATION);
        EnergyValueRegistryProxy.setEnergyValue(Items.QUARTZ, 256, Phase.PRE_CALCULATION);

        /* Equivalent Exchange 3 */
        /**
         *  Alchemical Dusts
         */
//        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 0), 1, Phase.PRE_CALCULATION);
//        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 1), 64, Phase.PRE_CALCULATION);
//        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 2), 2048, Phase.PRE_CALCULATION);
//        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 3), 8192, Phase.PRE_CALCULATION);

        /**
         *  Minium Shard
         */
//        EnergyValueRegistryProxy.setEnergyValue(new ItemStack(ModItems.shardMinium), 8192, Phase.PRE_CALCULATION);
    }
}

package com.pahimar.ee3.test;

import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.reference.Files;
import net.minecraft.item.ItemStack;

import java.io.File;

public class EETestSuite extends EnergyValueTestSuite {

    public EETestSuite() {
        super();
    }

    public EETestSuite build() {

        add(ModBlocks.calcinator, 772);
        add(ModBlocks.aludel, 1794);
        add(ModBlocks.glassBell, 7);
        add(ModBlocks.researchStation, 520);
        add(ModBlocks.augmentationTable, 284);
        add(new ItemStack(ModBlocks.alchemicalChest, 1, 0), 576);
        add(new ItemStack(ModBlocks.alchemicalChest, 1, 1), 16448);
        add(new ItemStack(ModBlocks.alchemicalChest, 1, 2), 65600);
        add(ModBlocks.chalkBlock, 320);
        add(new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 0), 18720);
        add(new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 1), 147744);
        add(new ItemStack(ModBlocks.alchemicalFuelBlock, 1, 2), 1179936);
        add(ModBlocks.ashInfusedStone, 2);
        add(ModBlocks.ashInfusedStoneSlab, 1);
        add(new ItemStack(ModItems.alchemicalBag, 1, 0), 560);
        add(new ItemStack(ModItems.alchemicalBag, 1, 1), 16432);
        add(new ItemStack(ModItems.alchemicalBag, 1, 2), 65584);
        add(new ItemStack(ModItems.alchemicalDust, 1, 0), 1);
        add(new ItemStack(ModItems.alchemicalDust, 1, 1), 64);
        add(new ItemStack(ModItems.alchemicalDust, 1, 2), 2048);
        add(new ItemStack(ModItems.alchemicalDust, 1, 3), 8192);
        add(new ItemStack(ModItems.alchemicalFuel, 1, 0), 2080);
        add(new ItemStack(ModItems.alchemicalFuel, 1, 1), 16416);
        add(new ItemStack(ModItems.alchemicalFuel, 1, 2), 131104);
        add(ModItems.stoneInert, 3076);
        add(ModItems.shardMinium, 8192);
        add(ModItems.stoneMinium, 68612);
        add(ModItems.stonePhilosophers, null);
        add(ModItems.chalk, 80);
        add(new ItemStack(ModItems.alchemicalUpgrade, 1, 0), null);
        add(new ItemStack(ModItems.alchemicalUpgrade, 1, 1), null);
        add(new ItemStack(ModItems.alchemicalUpgrade, 1, 2), null);
        add(ModItems.diviningRod, 16);
        add(ModItems.alchenomicon, 8352);
        add(new ItemStack(ModItems.matter, 1, 0), null);
        add(new ItemStack(ModItems.matter, 1, 1), null);
        add(new ItemStack(ModItems.matter, 1, 2), null);
        add(new ItemStack(ModItems.matter, 1, 3), null);
        add(new ItemStack(ModItems.matter, 1, 4), null);
        add(new ItemStack(ModItems.matter, 1, 5), null);
        add(new ItemStack(ModItems.matter, 1, 6), null);
        add(new ItemStack(ModItems.matter, 1, 7), null);
        add(new ItemStack(ModItems.matter, 1, 8), null);
        add(new ItemStack(ModItems.gem, 1, 0), null);
        add(new ItemStack(ModItems.gem, 1, 1), null);
        add(new ItemStack(ModItems.gem, 1, 2), null);
        add(new ItemStack(ModItems.gem, 1, 3), null);
        add(new ItemStack(ModItems.gem, 1, 4), null);
        add(new ItemStack(ModItems.gem, 1, 5), null);
        add(new ItemStack(ModItems.gem, 1, 6), null);
        add(ModItems.lootBall, null);
        add(ModItems.knowledgeScroll, null);
        add(ModItems.potionLethe, null);
        add(ModItems.shovelDarkMatter, null);
        add(ModItems.pickAxeDarkMatter, null);
        add(ModItems.hammerDarkMatter, null);
        add(ModItems.axeDarkMatter, null);
        add(ModItems.hoeDarkMatter, null);
        add(ModItems.fishingRodDarkMatter, null);
        add(ModItems.shearsDarkMatter, null);
        add(ModItems.bowDarkMatter, null);
        add(ModItems.swordDarkMatter, null);

        return this;
    }

    public void save() {
        this.save(new File(Files.globalTestDirectory, "ee3-v1710-test-suite.json"));
    }
}

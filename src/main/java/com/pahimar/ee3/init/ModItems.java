package com.pahimar.ee3.init;

import com.pahimar.ee3.item.*;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemEE alchemicalBag = new ItemAlchemicalBag();
    public static final ItemEE alchemicalDust = new ItemAlchemicalDust();
    public static final ItemEE alchemicalFuel = new ItemAlchemicalFuel();
    public static final ItemEE stoneInert = new ItemInertStone();
    public static final ItemEE shardMinium = new ItemMiniumShard();
    public static final ItemEE stoneMinium = new ItemMiniumStone();
    public static final ItemEE stonePhilosophers = new ItemPhilosophersStone();
    public static final ItemEE alchemicalUpgrade = new ItemAlchemicalInventoryUpgrade();
    public static final ItemEE chalk = new ItemChalk();
    public static final ItemEE diviningRod = new ItemDiviningRod();
    public static final ItemEE alchemicalTome = new ItemAlchemicalTome();
    public static final ItemEE guide = new ItemGuide();
    public static final ItemEE matter = new ItemMatter();

    public static final ItemDarkMatterShovel shovelDarkMatter = new ItemDarkMatterShovel();
    public static final ItemDarkMatterPickAxe pickaxeDarkMatter = new ItemDarkMatterPickAxe();
    public static final ItemDarkMatterFishingRod fishingrodDarkMatter = new ItemDarkMatterFishingRod();

    public static void init()
    {
        GameRegistry.registerItem(alchemicalBag, Names.Items.ALCHEMICAL_BAG);
        GameRegistry.registerItem(alchemicalDust, Names.Items.ALCHEMICAL_DUST);
        GameRegistry.registerItem(alchemicalFuel, Names.Items.ALCHEMICAL_FUEL);
        GameRegistry.registerItem(stoneInert, Names.Items.INERT_STONE);
        GameRegistry.registerItem(shardMinium, Names.Items.MINIUM_SHARD);
        GameRegistry.registerItem(stoneMinium, Names.Items.MINIUM_STONE);
        GameRegistry.registerItem(stonePhilosophers, Names.Items.PHILOSOPHERS_STONE);
        GameRegistry.registerItem(chalk, Names.Items.CHALK);
        GameRegistry.registerItem(alchemicalUpgrade, Names.Items.ALCHEMICAL_UPGRADE);
        GameRegistry.registerItem(diviningRod, Names.Items.DIVINING_ROD);
        GameRegistry.registerItem(alchemicalTome, Names.Items.ALCHEMICAL_TOME);
        GameRegistry.registerItem(guide, Names.Items.GUIDE);
        GameRegistry.registerItem(matter, Names.Items.MATTER);

        GameRegistry.registerItem(shovelDarkMatter, Names.Tools.DARK_MATTER_SHOVEL);
        GameRegistry.registerItem(pickaxeDarkMatter, Names.Tools.DARK_MATTER_PICKAXE);
        // Axe
        // Hoe
        GameRegistry.registerItem(fishingrodDarkMatter, Names.Tools.DARK_MATTER_FISHING_ROD);
        // Shears

        // Bow
        // Arrow
        // Sword

        // Helm
        // Chest
        // Leggings
        // Boots
    }
}

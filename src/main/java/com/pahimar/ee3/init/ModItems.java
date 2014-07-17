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

    public static final ItemToolEE shovelDarkMatter = new ItemDarkMatterShovel();
    public static final ItemToolEE pickAxeDarkMatter = new ItemDarkMatterPickAxe();
    public static final ItemEE hammerDarkMatter = new ItemDarkMatterHammer();
    public static final ItemToolEE axeDarkMatter = new ItemDarkMatterAxe();
    public static final ItemDarkMatterHoe hoeDarkMatter = new ItemDarkMatterHoe();
    public static final ItemDarkMatterFishingRod fishingRodDarkMatter = new ItemDarkMatterFishingRod();
    public static final ItemDarkMatterShears shearsDarkMatter = new ItemDarkMatterShears();

    public static final ItemDarkMatterBow bowDarkMatter = new ItemDarkMatterBow();
    public static final ItemDarkMatterArrow arrowDarkMatter = new ItemDarkMatterArrow();
    public static final ItemDarkMatterSword swordDarkMatter = new ItemDarkMatterSword();

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
        GameRegistry.registerItem(pickAxeDarkMatter, Names.Tools.DARK_MATTER_PICKAXE);
        GameRegistry.registerItem(hammerDarkMatter, Names.Tools.DARK_MATTER_HAMMER);
        GameRegistry.registerItem(axeDarkMatter, Names.Tools.DARK_MATTER_AXE);
        GameRegistry.registerItem(hoeDarkMatter, Names.Tools.DARK_MATTER_HOE);
        GameRegistry.registerItem(fishingRodDarkMatter, Names.Tools.DARK_MATTER_FISHING_ROD);
        GameRegistry.registerItem(shearsDarkMatter, Names.Tools.DARK_MATTER_SHEARS);

        GameRegistry.registerItem(bowDarkMatter, Names.Weapons.DARK_MATTER_BOW);
        GameRegistry.registerItem(arrowDarkMatter, Names.Weapons.DARK_MATTER_ARROW);
        GameRegistry.registerItem(swordDarkMatter, Names.Weapons.DARK_MATTER_SWORD);

        // Helm
        // Chest
        // Leggings
        // Boots
    }
}

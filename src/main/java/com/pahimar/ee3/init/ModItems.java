package com.pahimar.ee3.init;

import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

import com.pahimar.ee3.item.*;
import com.pahimar.ee3.item.tool.*;
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
    public static final ItemEE darkMatter = new ItemDarkMatter();
    
    public static final ItemTool axeDarkMatter = new ItemAxeDarkMatter();
    public static final ItemTool pickaxeDarkMatter = new ItemPickaxeDarkMatter();
    public static final ItemTool shovelDarkMatter = new ItemShovelDarkMatter();
    
    public static final ItemHoe hoeDarkMatter = new ItemHoeDarkMatter();
    public static final ItemSword swordDarkMatter = new ItemSwordDarkMatter();

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
        GameRegistry.registerItem(darkMatter, Names.Items.DARK_MATTER);

        
        GameRegistry.registerItem(axeDarkMatter, Names.Tools.AXE_DARK_MATTER);
        GameRegistry.registerItem(hoeDarkMatter, Names.Tools.HOE_DARK_MATTER);
        GameRegistry.registerItem(pickaxeDarkMatter, Names.Tools.PICKAXE_DARK_MATTER);
        GameRegistry.registerItem(shovelDarkMatter, Names.Tools.SHOVEL_DARK_MATTER);
        GameRegistry.registerItem(swordDarkMatter, Names.Tools.SWORD_DARK_MATTER);
        
    }
}

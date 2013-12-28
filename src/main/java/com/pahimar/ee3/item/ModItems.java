package com.pahimar.ee3.item;

import com.pahimar.ee3.lib.ItemIds;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ModItems
 *
 * @author pahimar
 */
public class ModItems
{
    // Mod item instances
    public static ItemEE miniumShard;
    public static ItemEE inertStone;
    public static ItemEE miniumStone;
    public static ItemEE philStone;
    public static ItemEE alchemicalDust;
    public static ItemEE alchemicalBag;
    public static ItemEE alchemicalChalk;
    public static ItemEE diviningRod;
    public static ItemEE alchemicalCoal;
    public static ItemEE mobiusFuel;
    public static ItemEE aeternalisFuel;

    public static void init()
    {
        // Initialize each mod item individually
        miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD);
        inertStone = new ItemInertStone(ItemIds.INERT_STONE);
        miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE);
        philStone = new ItemPhilosophersStone(ItemIds.PHILOSOPHERS_STONE);
        alchemicalDust = new ItemAlchemicalDust(ItemIds.ALCHEMICAL_DUST);
        alchemicalBag = new ItemAlchemicalBag(ItemIds.ALCHEMICAL_BAG);
        alchemicalChalk = new ItemAlchemicalChalk(ItemIds.ALCHEMICAL_CHALK);
        diviningRod = new ItemDiviningRod(ItemIds.DIVINING_ROD);
        alchemicalCoal = new ItemAlchemicalCoal(ItemIds.ALCHEMICAL_COAL);
        mobiusFuel = new ItemMobiusFuel(ItemIds.MOBIUS_FUEL);
        aeternalisFuel = new ItemAeternalisFuel(ItemIds.AETERNALIS_FUEL);

        // Set container items
        miniumStone.setContainerItem(miniumStone);
        philStone.setContainerItem(philStone);

        // Register items with the GameRegistry
        GameRegistry.registerItem(miniumShard, "item." + Strings.MINIUM_SHARD_NAME);
        GameRegistry.registerItem(inertStone, "item." + Strings.INERT_STONE_NAME);
        GameRegistry.registerItem(miniumStone, "item." + Strings.MINIUM_STONE_NAME);
        GameRegistry.registerItem(philStone, "item." + Strings.PHILOSOPHERS_STONE_NAME);
        GameRegistry.registerItem(alchemicalDust, "item." + Strings.ALCHEMICAL_DUST_NAME);
        GameRegistry.registerItem(alchemicalBag, "item." + Strings.ALCHEMICAL_BAG_NAME);
        GameRegistry.registerItem(alchemicalChalk, "item." + Strings.ALCHEMICAL_CHALK_NAME);
        GameRegistry.registerItem(diviningRod, "item." + Strings.DIVINING_ROD_NAME);
        GameRegistry.registerItem(alchemicalCoal, "item." + Strings.ALCHEMICAL_COAL_NAME);
        GameRegistry.registerItem(mobiusFuel, "item." + Strings.MOBIUS_FUEL_NAME);
        GameRegistry.registerItem(aeternalisFuel, "item." + Strings.AETERNALIS_FUEL_NAME);
    }
}

package com.pahimar.ee3.item;

import com.pahimar.ee3.lib.ItemIds;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemMultiTextureTile;

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
    public static ItemEE chalk;
    public static ItemEE diviningRod;
    public static ItemEE alchemicalFuel;
    public static ItemEE alchemicalUpgrade;

    public static ItemMultiTextureTile alchemicalFuelBlock;

    public static void init()
    {
        // Initialize each mod item individually
        miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD);
        inertStone = new ItemInertStone(ItemIds.INERT_STONE);
        miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE);
        philStone = new ItemPhilosophersStone(ItemIds.PHILOSOPHERS_STONE);
        alchemicalDust = new ItemAlchemicalDust(ItemIds.ALCHEMICAL_DUST);
        alchemicalBag = new ItemAlchemicalBag(ItemIds.ALCHEMICAL_BAG);
        chalk = new ItemChalk(ItemIds.CHALK);
        diviningRod = new ItemDiviningRod(ItemIds.DIVINING_ROD);
        alchemicalFuel = new ItemAlchemicalFuel(ItemIds.ALCHEMICAL_FUEL);
        alchemicalUpgrade = new ItemAlchemicalUpgrade(ItemIds.ALCHEMICAL_UPGRADE);

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
        GameRegistry.registerItem(chalk, "item." + Strings.CHALK_NAME);
        GameRegistry.registerItem(diviningRod, "item." + Strings.DIVINING_ROD_NAME);
        GameRegistry.registerItem(alchemicalFuel, "item." + Strings.ALCHEMICAL_FUEL_NAME);
        GameRegistry.registerItem(alchemicalUpgrade, "item." + Strings.ALCHEMICAL_UPGRADE_NAME);
    }
}

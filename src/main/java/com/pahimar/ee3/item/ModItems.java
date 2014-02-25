package com.pahimar.ee3.item;

import net.minecraft.item.ItemMultiTexture;

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
    public static ItemEE chalk;
    public static ItemEE diviningRod;
    public static ItemEE alchemicalFuel;

    public static ItemMultiTexture alchemicalFuelBlock;

    public static void init()
    {
        // Initialize each mod item individually
        miniumShard = new ItemMiniumShard();
        inertStone = new ItemInertStone();
        miniumStone = new ItemMiniumStone();
        philStone = new ItemPhilosophersStone();
        alchemicalDust = new ItemAlchemicalDust();
        alchemicalBag = new ItemAlchemicalBag();
        chalk = new ItemChalk();
        diviningRod = new ItemDiviningRod();
        alchemicalFuel = new ItemAlchemicalFuel();

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
    }
}

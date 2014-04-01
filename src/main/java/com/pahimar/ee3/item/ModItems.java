package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems
{
    public static final Item alchemicalBag = new ItemAlchemicalBag();
    public static final Item alchemicalDust = new ItemAlchemicalDust();
    public static final Item alchemicalFuel = new ItemAlchemicalFuel();
    public static final Item inertStone = new ItemInertStone();
    public static final Item miniumShard = new ItemMiniumShard();
    public static final Item miniumStone = new ItemMiniumStone();
    public static final Item philosophersStone = new ItemPhilosophersStone();
    public static final Item alchemicalInventoryUpgrade = new ItemAlchemicalInventoryUpgrade();
    public static final Item chalk = new ItemChalk();

    public static void init()
    {
        GameRegistry.registerItem(alchemicalBag, "item." + Names.Items.ALCHEMICAL_BAG);
        GameRegistry.registerItem(alchemicalDust, "item." + Names.Items.ALCHEMICAL_DUST);
        GameRegistry.registerItem(alchemicalFuel, "item." + Names.Items.ALCHEMICAL_FUEL);
        GameRegistry.registerItem(inertStone, "item." + Names.Items.INERT_STONE);
        GameRegistry.registerItem(miniumShard, "item." + Names.Items.MINIUM_SHARD);
        GameRegistry.registerItem(miniumStone, "item." + Names.Items.MINIUM_STONE);
        GameRegistry.registerItem(philosophersStone, "item." + Names.Items.PHILOSOPHERS_STONE);
        GameRegistry.registerItem(chalk, "item." + Names.Items.CHALK);
        GameRegistry.registerItem(alchemicalInventoryUpgrade, "item." + Names.Items.ALCHEMICAL_UPGRADE);
    }
}

package com.pahimar.ee3.init;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.item.*;
import com.pahimar.ee3.item.base.ItemBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@GameRegistry.ObjectHolder(EquivalentExchange3.MOD_ID)
public class ModItems {

    private static final List<ItemBase> ITEMS = new ArrayList<>();

    public static final ItemBase CHALK = new ItemChalk();
    public static final ItemBase ALCHEMICAL_FUEL = new ItemAlchemicalFuel();
    public static final ItemBase ALCHENOMICON = new ItemAlchenomicon();
    public static final ItemBase ALCHEMICAL_DUST = new ItemAlchemicalDust();
    public static final ItemBase ALCHEMICAL_BAG = new ItemAlchemicalBag();

    private ModItems() {}

    public static Collection<ItemBase> getItems() {
        return ITEMS;
    }

    public static void register(ItemBase item) {
        ITEMS.add(item);
    }
}

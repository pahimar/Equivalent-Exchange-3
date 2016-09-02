package com.pahimar.ee3.init;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.item.*;
import com.pahimar.ee3.item.base.ItemEE;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@GameRegistry.ObjectHolder(EquivalentExchange3.MOD_ID)
public class ModItems {

    private static final List<ItemEE> ITEMS = new ArrayList<>();

    public static final ItemEE CHALK = new ItemChalk();
    public static final ItemEE ALCHEMICAL_FUEL = new ItemAlchemicalFuel();
    public static final ItemEE ALCHENOMICON = new ItemAlchenomicon();
    public static final ItemEE ALCHEMICAL_DUST = new ItemAlchemicalDust();
    public static final ItemEE ALCHEMICAL_BAG = new ItemAlchemicalBag();

    private ModItems() {}

    public static Collection<ItemEE> getItems() {
        return ITEMS;
    }

    public static void register(ItemEE item) {
        ITEMS.add(item);
    }
}

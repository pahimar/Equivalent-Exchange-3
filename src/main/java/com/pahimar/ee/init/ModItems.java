package com.pahimar.ee.init;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.item.base.ItemBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Finish Javadoc
 *
 * @author      pahimar
 *
 * @since       3.0.0
 */
@GameRegistry.ObjectHolder(EquivalentExchange.MOD_ID)
public class ModItems {

    private static final List<ItemBase> ITEMS = new ArrayList<>();

    private ModItems() {
        // NO-OP
    }

    public static List<ItemBase> getItems() {
        return ITEMS;
    }

    public static void register(ItemBase itemBase) {
        ITEMS.add(itemBase);
    }
}

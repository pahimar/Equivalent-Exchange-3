package com.pahimar.ee.init;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.item.ItemAlchenomicon;
import com.pahimar.ee.item.base.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
@Mod.EventBusSubscriber
public class ModItems {

    private static final List<ItemBase> ITEMS = new ArrayList<>();

    public static final ItemBase ALCHENOMICON = new ItemAlchenomicon();

    private ModItems() {
        // NO-OP
    }

    public static List<ItemBase> getItems() {
        return ITEMS;
    }

    public static void register(ItemBase itemBase) {
        ITEMS.add(itemBase);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (ItemBase itemBase : ITEMS) {
            event.getRegistry().register(itemBase);
        }
    }
}

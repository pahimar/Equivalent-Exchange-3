package com.pahimar.ee.handler;

import com.pahimar.ee.util.IOwnable;
import com.pahimar.ee.util.ItemStackUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class CraftingHandler {

    public static void init() {
        // TODO Reimplement when bags are done
//        CraftingManager.getInstance().getRecipeList().add(new RecipeAlchemicalBagDyes());
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem() instanceof IOwnable) {
            ItemStackUtils.setOwner(event.crafting, event.player);
        }
    }
}

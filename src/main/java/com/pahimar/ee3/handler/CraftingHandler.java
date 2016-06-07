package com.pahimar.ee3.handler;

import com.pahimar.ee3.item.crafting.RecipesAlchemicalBagDyes;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemStackUtils;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class CraftingHandler {

    public static void init() {
        CraftingManager.getInstance().getRecipeList().add(new RecipesAlchemicalBagDyes());
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem() instanceof IOwnable) {
            ItemStackUtils.setOwner(event.crafting, event.player);
        }
    }
}

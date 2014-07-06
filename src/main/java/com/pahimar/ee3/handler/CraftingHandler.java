package com.pahimar.ee3.handler;

import com.pahimar.ee3.item.crafting.RecipesAlchemicalBagDyes;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.crafting.CraftingManager;

public class CraftingHandler
{
    public static void init()
    {
        // Add in the ability to dye Alchemical Bags
        CraftingManager.getInstance().getRecipeList().add(new RecipesAlchemicalBagDyes());
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
    {
        // TODO Set owner on who crafted the item (make sure it's not a FakePlayer)
    }
}

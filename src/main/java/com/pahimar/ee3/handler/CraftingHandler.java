package com.pahimar.ee3.handler;

import com.pahimar.ee3.item.crafting.RecipesAlchemicalBagDyes;
import com.pahimar.ee3.recipe.RecipesAludel;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.crafting.CraftingManager;

public class CraftingHandler
{
    public static void init()
    {
        // Add in the ability to dye Alchemical Bags
        CraftingManager.getInstance().getRecipeList().add(new RecipesAlchemicalBagDyes());
        RecipesAludel.getInstance();
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
    {
        // TODO Set owner on who crafted the item (make sure it's not a FakePlayer)
    }
}

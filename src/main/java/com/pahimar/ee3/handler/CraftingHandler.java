package com.pahimar.ee3.handler;

import com.pahimar.ee3.item.crafting.RecipesAlchemicalBagDyes;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.util.FakePlayer;

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
        if (event.crafting.getItem() instanceof IOwnable)
        {
            ItemHelper.setOwner(event.crafting, event.player);
        }
    }
    
    public boolean isFakePlayer(EntityPlayer player)
    {
        if (player.getGameProfile() == null || player.getGameProfile().getName() == null)
            return true;
        if (player instanceof FakePlayer)
            return true;
        if (player instanceof EntityPlayerMP)
        {
            EntityPlayerMP mp = (EntityPlayerMP) player;
            if (mp.playerNetServerHandler == null)
                return true;
            try
            {
                mp.getPlayerIP();
                mp.playerNetServerHandler.netManager.getSocketAddress().toString();
            }
            catch (Exception e)
            {
                return true;
            }
            return !MinecraftServer.getServer().getConfigurationManager().playerEntityList.contains(player);
        }
        return false;
    }
}

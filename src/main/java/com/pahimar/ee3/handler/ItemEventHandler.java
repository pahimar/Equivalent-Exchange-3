package com.pahimar.ee3.handler;

import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.util.NBTHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

public class ItemEventHandler
{
    @SubscribeEvent
    public void onItemTossEvent(ItemTossEvent itemTossEvent)
    {
        NBTHelper.clearStatefulNBTTags(itemTossEvent.entityItem.getEntityItem());

        //Close the Alchemical Bag GUI when the Alchemical bag is tossed
        if (itemTossEvent.player.openContainer instanceof ContainerAlchemicalBag)
        {
            if (((ContainerAlchemicalBag) itemTossEvent.player.openContainer).isItemStackParent(itemTossEvent.entityItem.getEntityItem()))
            {
                //We have to remove the itemstack we are throwing from the inventory now to prevent a loop (will also happen after this event has been fired)
                itemTossEvent.player.inventory.setItemStack(null);
                itemTossEvent.player.closeScreen();
            }
        }
    }

    @SubscribeEvent
    public void onItemPickupEvent(PlayerEvent.ItemPickupEvent itemPickupEvent)
    {
        NBTHelper.clearStatefulNBTTags(itemPickupEvent.pickedUp.getEntityItem());
    }

    @SubscribeEvent
    public void onEntityItemPickupEvent(EntityItemPickupEvent entityItemPickupEvent)
    {
        NBTHelper.clearStatefulNBTTags(entityItemPickupEvent.item.getEntityItem());
    }

    @SubscribeEvent
    public void onPlayerDropsEvent(PlayerDropsEvent playerDropsEvent)
    {
        for (EntityItem entityItem : playerDropsEvent.drops)
        {
            NBTHelper.clearStatefulNBTTags(entityItem.getEntityItem());
        }
    }
}

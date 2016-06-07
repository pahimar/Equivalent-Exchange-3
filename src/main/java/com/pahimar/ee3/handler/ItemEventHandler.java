package com.pahimar.ee3.handler;

import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.util.NBTUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class ItemEventHandler
{
    @SubscribeEvent
    public void onItemTossEvent(ItemTossEvent itemTossEvent)
    {
        NBTUtils.clearStatefulNBTTags(itemTossEvent.getEntityItem().getEntityItem());

        //Close the Alchemical Bag GUI when the Alchemical bag is tossed
        if (itemTossEvent.getPlayer().openContainer instanceof ContainerAlchemicalBag)
        {
            if (((ContainerAlchemicalBag) itemTossEvent.getPlayer().openContainer).isItemStackParent(itemTossEvent.getEntityItem().getEntityItem()))
            {
                //We have to remove the itemstack we are throwing from the inventory now to prevent a loop (will also happen after this event has been fired)
                itemTossEvent.getPlayer().inventory.setItemStack(null);
                itemTossEvent.getPlayer().closeScreen();
            }
        }
    }

    @SubscribeEvent
    public void onItemPickupEvent(PlayerEvent.ItemPickupEvent itemPickupEvent)
    {
        NBTUtils.clearStatefulNBTTags(itemPickupEvent.pickedUp.getEntityItem());
    }

    @SubscribeEvent
    public void onEntityItemPickupEvent(EntityItemPickupEvent entityItemPickupEvent)
    {
        NBTUtils.clearStatefulNBTTags(entityItemPickupEvent.getItem().getEntityItem());
    }

    @SubscribeEvent
    public void onPlayerDropsEvent(PlayerDropsEvent playerDropsEvent)
    {
        for (EntityItem entityItem : playerDropsEvent.getDrops())
        {
            NBTUtils.clearStatefulNBTTags(entityItem.getEntityItem());
        }
    }
}

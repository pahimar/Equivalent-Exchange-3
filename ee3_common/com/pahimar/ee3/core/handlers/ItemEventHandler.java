package com.pahimar.ee3.core.handlers;

import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.lib.Strings;

/**
 * ItemPickupHandler
 * 
 * Listens for item pickup events and executes custom code on receiving these
 * events
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemEventHandler {

    @ForgeSubscribe
    public void onItemPickup(EntityItemPickupEvent event) {

        if (NBTHelper.hasTag(event.item.getEntityItem(), Strings.NBT_ITEM_TRANS_GUI_OPEN)) {
            NBTHelper.removeTag(event.item.getEntityItem(), Strings.NBT_ITEM_TRANS_GUI_OPEN);
        }
    }

    @ForgeSubscribe
    public void onItemToss(ItemTossEvent event) {

        if (NBTHelper.hasTag(event.entityItem.getEntityItem(), Strings.NBT_ITEM_TRANS_GUI_OPEN)) {
            NBTHelper.removeTag(event.entityItem.getEntityItem(), Strings.NBT_ITEM_TRANS_GUI_OPEN);
        }
    }

    @ForgeSubscribe
    public void onPlayerDrop(PlayerDropsEvent event) {

        for (EntityItem entityItem : event.drops) {
            if (NBTHelper.hasTag(entityItem.getEntityItem(), Strings.NBT_ITEM_TRANS_GUI_OPEN)) {
                NBTHelper.removeTag(entityItem.getEntityItem(), Strings.NBT_ITEM_TRANS_GUI_OPEN);
            }
        }
    }
}

package com.pahimar.ee3.core.handler;

import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

import com.pahimar.ee3.core.helper.nbt.GeneralNBTHelper;
import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * ItemEventHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemEventHandler {

    @ForgeSubscribe
    public void onItemPickup(EntityItemPickupEvent event) {

        if (GeneralNBTHelper.hasTag(event.item.getEntityItem(), Strings.NBT_ITEM_CRAFTING_GUI_OPEN)) {
            GeneralNBTHelper.removeTag(event.item.getEntityItem(), Strings.NBT_ITEM_CRAFTING_GUI_OPEN);
        }
        else if (GeneralNBTHelper.hasTag(event.item.getEntityItem(), Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN)) {
            GeneralNBTHelper.removeTag(event.item.getEntityItem(), Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN);
        }
        else if (GeneralNBTHelper.hasTag(event.item.getEntityItem(), Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
            GeneralNBTHelper.removeTag(event.item.getEntityItem(), Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
        }
    }

    @ForgeSubscribe
    public void onItemToss(ItemTossEvent event) {

        if (GeneralNBTHelper.hasTag(event.entityItem.getEntityItem(), Strings.NBT_ITEM_CRAFTING_GUI_OPEN)) {
            GeneralNBTHelper.removeTag(event.entityItem.getEntityItem(), Strings.NBT_ITEM_CRAFTING_GUI_OPEN);
        }
        else if (GeneralNBTHelper.hasTag(event.entityItem.getEntityItem(), Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN)) {
            GeneralNBTHelper.removeTag(event.entityItem.getEntityItem(), Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN);
        }
        else if (GeneralNBTHelper.hasTag(event.entityItem.getEntityItem(), Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
            GeneralNBTHelper.removeTag(event.entityItem.getEntityItem(), Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
        }
    }

    @ForgeSubscribe
    public void onPlayerDrop(PlayerDropsEvent event) {

        for (EntityItem entityItem : event.drops) {
            if (GeneralNBTHelper.hasTag(entityItem.getEntityItem(), Strings.NBT_ITEM_CRAFTING_GUI_OPEN)) {
                GeneralNBTHelper.removeTag(entityItem.getEntityItem(), Strings.NBT_ITEM_CRAFTING_GUI_OPEN);
            }
            else if (GeneralNBTHelper.hasTag(entityItem.getEntityItem(), Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN)) {
                GeneralNBTHelper.removeTag(entityItem.getEntityItem(), Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN);
            }
            else if (GeneralNBTHelper.hasTag(entityItem.getEntityItem(), Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
                GeneralNBTHelper.removeTag(entityItem.getEntityItem(), Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
            }
        }
    }
}

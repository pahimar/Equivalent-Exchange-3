package com.pahimar.ee3.core.handlers;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.item.CustomWrappedStack;

/**
 * Equivalent-Exchange-3
 * 
 * ItemTooltipEventHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemTooltipEventHandler {

    @ForgeSubscribe
    public void handleItemTooltipEvent(ItemTooltipEvent event) {

        if (EmcRegistry.hasEmcValue(new CustomWrappedStack(event.itemStack))) {
            event.toolTip.add("EMC: " + EmcRegistry.getEmcValue(new CustomWrappedStack(event.itemStack)).value);
        }
    }
}

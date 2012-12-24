package com.pahimar.ee3.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.pahimar.ee3.core.helper.ItemDropHelper;

/**
 * EntityLivingHandler
 * 
 * Class containing all EE3 custom event related logic for EntityLiving events
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EntityLivingHandler {

    @ForgeSubscribe
    public void onEntityLivingUpdate(LivingUpdateEvent event) {

    }

    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event) {

        if (event.source.getDamageType().equals("player")) {
            ItemDropHelper.dropMiniumShard((EntityPlayer) event.source.getSourceOfDamage(), event.entityLiving);
        }
    }

}

package com.pahimar.ee3.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.pahimar.ee3.core.helper.ItemHelper;

/**
 * Equivalent-Exchange-3
 * 
 * EntityLivingHandler
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
            ItemHelper.dropMiniumShard((EntityPlayer) event.source.getSourceOfDamage(), event.entityLiving);
        }
        if (event.source.getSourceOfDamage() instanceof EntityArrow) {
            if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity != null) {
                if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer) {
                    ItemHelper.dropMiniumShard((EntityPlayer) ((EntityArrow) event.source.getSourceOfDamage()).shootingEntity, event.entityLiving);
                }
            }
        }
    }

}

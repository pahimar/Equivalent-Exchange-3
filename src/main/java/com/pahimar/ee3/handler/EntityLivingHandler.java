package com.pahimar.ee3.handler;

import com.pahimar.ee3.helper.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

/**
 * Equivalent-Exchange-3
 * <p/>
 * EntityLivingHandler
 *
 * @author pahimar
 */
public class EntityLivingHandler
{

    @ForgeSubscribe
    public void onEntityLivingUpdate(LivingUpdateEvent event)
    {

    }

    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event)
    {
        if (event.source.getDamageType().equals("player"))
        {
            ItemHelper.dropMiniumShard((EntityPlayer) event.source.getSourceOfDamage(), event.entityLiving);
        }
        if (event.source.getSourceOfDamage() instanceof EntityArrow)
        {
            if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity != null)
            {
                if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer)
                {
                    ItemHelper.dropMiniumShard((EntityPlayer) ((EntityArrow) event.source.getSourceOfDamage()).shootingEntity, event.entityLiving);
                }
            }
        }
    }
}

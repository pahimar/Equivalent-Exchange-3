package com.pahimar.ee3.api.event;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

public class EnergyValueEvent extends Event
{
    public final Object object;
    public final EnergyValueRegistryProxy.Phase phase;
    public final EntityPlayer entityPlayer;

    public EnergyValueEvent(Object object, EnergyValueRegistryProxy.Phase phase, EntityPlayer entityPlayer)
    {
        this.object = object;
        this.phase = phase;
        this.entityPlayer = entityPlayer;
    }

    @Override
    public boolean isCancelable()
    {
        return true;
    }

    public static class SetEnergyValueEvent extends EnergyValueEvent
    {
        public final EnergyValue newEnergyValue;

        public SetEnergyValueEvent(Object object, EnergyValue newEnergyValue, EnergyValueRegistryProxy.Phase phase, EntityPlayer entityPlayer)
        {
            super(object, phase, entityPlayer);
            this.newEnergyValue = newEnergyValue;
        }
    }

    public static class RemoveEnergyValueEvent extends EnergyValueEvent
    {
        public RemoveEnergyValueEvent(Object object, EnergyValueRegistryProxy.Phase phase, EntityPlayer entityPlayer)
        {
            super(object, phase, entityPlayer);
        }
    }
}

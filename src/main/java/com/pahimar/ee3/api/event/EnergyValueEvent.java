package com.pahimar.ee3.api.event;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EnergyValueEvent extends Event {

    public final Object object;
    public final EnergyValueRegistryProxy.Phase phase;

    public EnergyValueEvent(Object object, EnergyValueRegistryProxy.Phase phase) {

        this.object = object;
        this.phase = phase;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    public static class SetEnergyValueEvent extends EnergyValueEvent {

        public final EnergyValue newEnergyValue;

        public SetEnergyValueEvent(Object object, EnergyValue newEnergyValue, EnergyValueRegistryProxy.Phase phase) {

            super(object, phase);
            this.newEnergyValue = newEnergyValue;
        }
    }

    public static class RemoveEnergyValueEvent extends EnergyValueEvent {

        public RemoveEnergyValueEvent(Object object, EnergyValueRegistryProxy.Phase phase) {
            super(object, phase);
        }
    }
}

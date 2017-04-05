package com.pahimar.ee.api.event;

import com.pahimar.ee.api.exchange.EnergyValue;
import com.pahimar.ee.api.exchange.EnergyValueRegistryProxy;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EnergyValueEvent extends Event {

    private final Object object;
    private final EnergyValueRegistryProxy.Phase phase;

    public EnergyValueEvent(Object object, EnergyValueRegistryProxy.Phase phase) {

        this.object = object;
        this.phase = phase;
    }

    public final Object getObject() {
        return object;
    }

    public final EnergyValueRegistryProxy.Phase getRegistrationPhase() {
        return phase;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    public static class SetEnergyValueEvent extends EnergyValueEvent {

        private final EnergyValue energyValue;

        public SetEnergyValueEvent(Object object, EnergyValue energyValue, EnergyValueRegistryProxy.Phase phase) {

            super(object, phase);
            this.energyValue = energyValue;
        }

        public final EnergyValue getEnergyValue() {
            return energyValue;
        }
    }
}

package com.pahimar.ee3.exchange;

import com.pahimar.ee3.api.exchange.EnergyValue;

import java.util.Map;

// TODO Move to api, WrappedStack or a mirror should be moved too.
public interface IEnergyValuesSource
{
    Map<WrappedStack, EnergyValue> getValues(IRegistryContext context);
}

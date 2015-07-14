package com.pahimar.ee3.exchange;

import com.pahimar.ee3.api.exchange.EnergyValue;

import java.util.Map;

public interface IEnergyCalculationDataProvider
{
    Map<WrappedStack, EnergyValue> getPreCalculationMappings();
    Map<WrappedStack, EnergyValue> getPostCalculationMappings();

    IEnergyValuesSource[] getPreCalculationSources();
    IEnergyValuesSource[] getPostCalculationSources();
}

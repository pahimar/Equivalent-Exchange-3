package com.pahimar.ee3.configuration;

public final class ValueTransformers
{
    public static final ValueTransformer<EnergyRegenOption> energyRegenOptionTransformer;

    static {
        energyRegenOptionTransformer = new ValueTransformer<EnergyRegenOption>(EnergyRegenOption.Always);
    }
}

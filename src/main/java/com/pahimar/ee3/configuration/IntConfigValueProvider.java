package com.pahimar.ee3.configuration;

import net.minecraftforge.common.config.Configuration;

public class IntConfigValueProvider implements IConfigValueProvider
{
    private final IConfigEntry entry;
    private final int defaultValue;
    private final int min;
    private final int max;

    public IntConfigValueProvider(IConfigEntry entry, int defaultValue, int min, int max) {
        this.entry = entry;
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
    }

    public int getValue(Configuration configuration)
    {
        return configuration.getInt(
                this.entry.getName(), this.entry.getCategory(),
                this.defaultValue, this.min, this.max,
                this.entry.getComment(),this.entry.getLabel());
    }

    @Override
    public Object getRawValue(Configuration configuration)
    {
        return this.getValue(configuration);
    }
}


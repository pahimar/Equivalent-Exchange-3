package com.pahimar.ee3.configuration;

import net.minecraftforge.common.config.Configuration;

public class BooleanConfigValueProvider implements IConfigValueProvider
{
    private final IConfigEntry entry;
    private final boolean defaultValue;

    public BooleanConfigValueProvider(IConfigEntry entry, boolean defaultValue) {
        this.entry = entry;
        this.defaultValue = defaultValue;
    }

    public boolean getValue(Configuration configuration)
    {
        return configuration.getBoolean(
                this.entry.getName(), this.entry.getCategory(),
                this.defaultValue, this.entry.getComment(),
                this.entry.getLabel());
    }

    @Override
    public Object getRawValue(Configuration configuration)
    {
        return this.getValue(configuration);
    }
}

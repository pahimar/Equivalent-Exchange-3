package com.pahimar.ee3.configuration;

public class IntConfigEntry extends ConfigEntry
{
    private final int defaultValue;
    private final int min;
    private final int max;

    protected IntConfigEntry(String name, String category, String comment, String label,
                             int defaultValue, int min, int max) {
        super(name, category, comment, label);
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
    }

    @Override
    protected IConfigValueProvider getValueProviderCore() {
        return new IntConfigValueProvider(this, this.defaultValue, this.min, this.max);
    }
}


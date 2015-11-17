package com.pahimar.ee3.configuration;

public class BooleanConfigEntry extends ConfigEntry
{
    private final Boolean defaultValue;

    protected BooleanConfigEntry(String name, String category, String comment, String label,
                                 boolean defaultValue) {
        super(name, category, comment, label);
        this.defaultValue = defaultValue;
    }

    @Override
    protected IConfigValueProvider getValueProviderCore() {
        return new BooleanConfigValueProvider(this, this.defaultValue);
    }
}

package com.pahimar.ee3.configuration;

public class StringConfigEntry extends ConfigEntry
{
    private final String defaultValue;
    private final String[] options;

    protected StringConfigEntry(String name, String category, String comment, String label,
        String defaultValue, String[] options) {
        super(name, category, comment, label);
        this.defaultValue = defaultValue;
        this.options = options;
    }

    @Override
    protected IConfigValueProvider getValueProviderCore() {
        return new StringConfigValueProvider(this, this.defaultValue, this.options);
    }
}


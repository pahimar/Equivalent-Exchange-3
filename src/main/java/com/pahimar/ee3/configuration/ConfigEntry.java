package com.pahimar.ee3.configuration;

import net.minecraftforge.common.config.Configuration;

public abstract class ConfigEntry implements IConfigEntry
{
    private final String name;
    private final String category;
    private final String comment;
    private final String label;
    private IConfigValueProvider provider;

    protected ConfigEntry(String name, String category, String comment, String label) {
        this.name = name;
        this.category = category;
        this.comment = comment;
        this.label = label;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public <T> T getValue(Configuration configuration) {
        return (T) this.getValueProvider().getRawValue(configuration);
    }

    public <T extends IConfigValueProvider> T getValueProvider() {
        if(this.provider == null)
            this.provider = getValueProviderCore();

        return (T) this.provider;
    }

    protected abstract IConfigValueProvider getValueProviderCore();

    public static StringConfigEntry Create(String name, String category, String comment, String label,
         String defaultValue, String[] options)
    {
        return new StringConfigEntry(name, category, comment, label, defaultValue, options);
    }

    public static <T> EnumConfigEntry<T> Create(String name, String category, String comment, String label,
                                     ValueTransformer<T> transformer)
    {
        return new EnumConfigEntry<T>(name, category, comment, label, transformer);
    }
}

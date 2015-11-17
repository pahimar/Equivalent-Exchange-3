package com.pahimar.ee3.configuration;

public class EnumConfigEntry<T> extends StringConfigEntry
{
    private final ValueTransformer<T> transformer;

    public EnumConfigEntry(String name, String category, String comment, String label,
                           ValueTransformer<T> transformer)
    {
        super(name, category, comment, label,
                transformer.getFallbackValue().toString(),
                transformer.getKeys());

        this.transformer = transformer;
    }

    @Override
    protected IConfigValueProvider getValueProviderCore()
    {
        StringConfigValueProvider source =
                (StringConfigValueProvider) super.getValueProviderCore();

        return new EnumConfigValueProvider<T>(this, this.transformer, source);
    }
}

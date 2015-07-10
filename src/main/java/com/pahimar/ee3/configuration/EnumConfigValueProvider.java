package com.pahimar.ee3.configuration;

import com.ibm.icu.impl.IllegalIcuArgumentException;
import net.minecraftforge.common.config.Configuration;

public class EnumConfigValueProvider<T> implements IConfigValueProvider
{
    private final IConfigEntry entry;
    private final ValueTransformer<T> transformer;
    private final StringConfigValueProvider source;

    public EnumConfigValueProvider(IConfigEntry entry, ValueTransformer<T> transformer, StringConfigValueProvider source) {
        this.entry = entry;
        this.transformer = transformer;
        this.source = source;
    }

    public T getValue(Configuration configuration)
    {
        String valueBase = this.source.getValue(configuration);
        try {
            return this.transformer.getValue(valueBase);
        } catch (IllegalIcuArgumentException ex) {
            // TODO: Output to log that the value in the config is invalid
            throw ex;
        }
    }

    @Override
    public Object getRawValue(Configuration configuration)
    {
        return this.getValue(configuration);
    }
}

package com.pahimar.ee3.configuration;

import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.util.ConfigurationHelper;
import net.minecraftforge.common.config.Configuration;

public class StringConfigValueProvider implements IConfigValueProvider
{
    private final IConfigEntry entry;
    private final String defaultValue;
    private final String[] options;

    public StringConfigValueProvider(IConfigEntry entry, String defaultValue, String options[]) {
        this.entry = entry;
        this.defaultValue = defaultValue;
        this.options = options;
    }

    public String getValue(Configuration configuration)
    {
        return ConfigurationHelper.getString(configuration,
                this.entry.getName(), this.entry.getCategory(),
                this.defaultValue, this.entry.getComment(),
                this.options, this.entry.getLabel());
    }

    @Override
    public Object getRawValue(Configuration configuration)
    {
        return this.getValue(configuration);
    }
}


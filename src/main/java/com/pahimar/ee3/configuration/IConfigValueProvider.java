package com.pahimar.ee3.configuration;

import net.minecraftforge.common.config.Configuration;

public interface IConfigValueProvider {
    Object getRawValue(Configuration configuration);
}

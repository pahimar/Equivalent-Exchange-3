package com.pahimar.ee3.configuration;

import net.minecraftforge.common.config.Configuration;

public interface IConfigEntry
{
    String getName();
    String getCategory();
    String getComment();
    String getLabel();

    <T> T getValue(Configuration configuration);
}
